package com.carapp.server;


import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.Log;

import com.carapp.util.UIUtils;
import com.carapp.util.UploadDataInfo;


public class AsyncWebServiceProcessingTask extends AsyncTask<String,Void , String>{
	
	private ProgressDialog bar; 
	private Context context;
	private Handler handler;
	private MultipartEntity entity;
	private String message;
	
	public AsyncWebServiceProcessingTask(Context context,Handler handler,MultipartEntity entity,String message){
	
		this.context=context;
		this.handler=handler;
	    this.entity=entity;
	    this.message=message;
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		 String responseData="no response";
	
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
		if (result.equals("no response")) {
			UIUtils.showNetworkErrorMessage(context);
		}else {
			//UploadDataInfo.serverData=result;
			Message msg=new Message();
			msg.obj=result;
			handler.sendMessage(msg);
		}
		/*long dtMili = System.currentTimeMillis();
		   long time=((5*60)+3)*60*1000;
		   long newlong=dtMili+time;
		   String dateString= DateFormat.format("MM/dd/yyyy_hh:mm:ss", new Date(newlong)).toString();
		    Log.e("time", ""+dateString);*/
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		bar = new ProgressDialog(context);
	    bar.setMessage(message);
	    bar.setCancelable(false);
	    bar.show();
	    long dtMili = System.currentTimeMillis();
	    Log.e("time", ""+dtMili);
	}

}
