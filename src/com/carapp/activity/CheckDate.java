package com.carapp.activity;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.carapp.util.PdfInfo;
import com.example.carappnew.R;

public class CheckDate extends Activity{
	private static String messagecheckdate="Please wait while checking date";
	private static String messagecheckdateerror=" Warning: please correct today’s date in the device ";
	
	private static  String t="CheckDate";
	private Context context;
	private  TextView tv;
	private String response=null;
	private String sysdate=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.mmmm);
		context=this;
		tv=(TextView)findViewById(R.id.textView1);
		tv.setText(messagecheckdate);
		new Checkdateasync().execute("");
	}
private class Checkdateasync extends AsyncTask<String, String, String> {
	
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
		}

		@Override
		protected void onProgressUpdate(String... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
		
			 try {
			        HttpClient httpclient = new DefaultHttpClient();
			        HttpResponse hresponse = httpclient.execute(new HttpGet(PdfInfo.dateaddress));
			       response=EntityUtils.toString(hresponse.getEntity()); 
			       sysdate =android.text.format.DateFormat.format(
							"dd/MM/yyyy", new java.util.Date()).toString();
			       if (response.equals(sysdate)) {
					return "sucesses";
				}else {
					 return "faild";	
				}
			      
			      } catch (Exception e) {
			          e.printStackTrace();
			        return "server problem";
			      }
			
			
			
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result.equals("sucesses")) {
				
				
				finish();
				startActivity(new Intent(getApplicationContext(), CheckDay.class));
				Toast.makeText(context, "date match", Toast.LENGTH_SHORT).show();
			}else if (result.equals("faild")) {
				
					showDialog(messagecheckdateerror+" server date is "+response+" but device date is "+sysdate, 0);	
				
			} 
			
			
		}

		private void showDialog(String msg,final int i) {
			
			AlertDialog.Builder dlg = new AlertDialog.Builder(CheckDate.this);
			dlg.setCancelable(false);
			//dlg.setTitle(strTitle);
			dlg.setMessage(msg);
			final AlertDialog alert=dlg.create();
			alert.show();
			new Timer().schedule(new TimerTask() {
				
				@Override
				public void run() {
				
					alert.dismiss();
					if (i==0) {
						finish();
					}
				}
			}, 5000);
						
		}
		
	}
@Override
public void onBackPressed() {
	// TODO Auto-generated method stub
	//super.onBackPressed();
}



}
