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
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carappnew.R;

public class CheckDay extends Activity{
	
	  private static String dateaddress="http://techsoftlabs.com/client/branch/day.php";
	  private static String messagecheckday="Please wait while checking day";
	  private static String messagecheckdayerror="Warning: day is different from server";
	  private static  String t="CheckDay";
	  private  TextView tv;
	  private Context context;
	  private int day;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.mmmm);
		context=this;
		tv=(TextView)findViewById(R.id.textView1);
		tv.setText(messagecheckday);
		
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
			String response=null;
			
			 try {
			        HttpClient httpclient = new DefaultHttpClient();
			        HttpResponse hresponse = httpclient.execute(new HttpGet(dateaddress));
			       response=EntityUtils.toString(hresponse.getEntity()); 
			       Log.i(t, response);
			       day=Integer.parseInt(response);
			       day++;
			       if (day==splashActivity.Today) {
			    	   Log.i(t, splashActivity.Today+" "+day);
			    	  
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
			Toast.makeText(context,"day is correct", Toast.LENGTH_SHORT).show();
			 startActivity(new Intent(getApplicationContext(), LicenseCheck.class));
			finish();
			}else if (result.equals("faild")) {
				showDialog(messagecheckdayerror+" server day is "+day+" but your device is"+splashActivity.Today, 0);
			}else if (result.equals("server problem")) {
				showDialog(messagecheckdayerror+" server day is "+day+" but your device is"+splashActivity.Today, 0);
			}
			
			
		}

		private void showDialog(String msg,final int i) {
			
			AlertDialog.Builder dlg = new AlertDialog.Builder(CheckDay.this);
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
