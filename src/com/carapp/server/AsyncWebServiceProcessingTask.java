package com.carapp.server;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.carapp.util.UIUtils;


public class AsyncWebServiceProcessingTask extends AsyncTask<String,Void , String>{
	
	private ProgressDialog bar; 
	private Context context;
	private MultipartEntity entity;
	private String message;
	private int key;
	public static final String BROADCAST_ACTION = "com.websmithing.broadcasttest.displayevent";
	
	public AsyncWebServiceProcessingTask(Context context,int key,MultipartEntity entity,String message){
	
		this.context=context;
		this.key=key;
	    this.entity=entity;
	    this.message=message;
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		 String responseData="";
	   
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(params[0]);
			
			  httppost.setEntity(entity);
			  HttpResponse response;
			  response = httpclient.execute(httppost);
			  HttpEntity getresponse=response.getEntity();
			 
			
		      responseData = EntityUtils.toString(getresponse);
		      Log.e("responseData", responseData);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	     
		
		return responseData;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		bar.dismiss();
		
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		bar = new ProgressDialog(context);
	    bar.setMessage(message);
	    bar.setCancelable(false);
	    bar.show();
	}

}
