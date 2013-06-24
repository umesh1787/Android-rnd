package com.carapp.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.carapp.util.PdfInfo;
import com.example.carappnew.R;

public class splashActivity extends Activity {
	   public static String Client="none";
       public static String Branch="none";
       public static String Serveraddress="http://techsoftlabs.com/client/branch/InitConfig.txt";
       public static String Licenserequestemailaddress;
       public static int Today;
	   public static boolean LicenseCheck=false;
     
     
      
	   private Context context;
	   public static String messagecheck="Please wait while performing initial configuration";
	   public static String messagechecksucces="Initial configuration completed successfully";
	   public static String messagecheckerror=" Could not find initial configuration information! CarApp is now exiting";
		  
	   public static  String t="splashActivity";
	   private ProgressDialog mProgressDialog;
	   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.splash);
		context=this;
		//deleteFiles();
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK); 
		  Log.i(t, ""+day);
		  Today=day;
		if (Client.equals("none")||Branch.equals("none")) {
			new Check().execute();
		//new UploadFile(context, PdfInfo.path).execute("");
		}
		
		
	}
	private class Check extends AsyncTask<Void, String, String> {
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			  mProgressDialog = new ProgressDialog(context);
			  mProgressDialog.setMessage(messagecheck);
			  mProgressDialog.setCancelable(false);
			  mProgressDialog.show();
		}

		@Override
		protected void onProgressUpdate(String... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			String response=null;
			try {
			    // Create a URL for the desired page
			    URL url = new URL(Serveraddress);

			    // Read all the text returned by the server
			    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			    String str;
			    int i=0;
			    while ((str = in.readLine()) != null) {
			       if (i==0) {
			    	   Client=str;
			    	   PdfInfo.client=Client;
			    	   i++;
				    }else if (i==1) {
				    	 Branch=str;
				    	 PdfInfo.branch=Branch;
					}
			    }
			   in.close();
			   response=messagechecksucces; 
			   Log.i(t, Client+" "+Branch);
			   
			} catch (MalformedURLException e) {
				
				response=messagecheckerror;
			Log.i(t, "mal "+e);
			} catch (IOException e) {
				response=messagecheckerror;
				Log.i(t, "io "+e);
			}
			
			return response;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			mProgressDialog.dismiss();
			if (result.equals(messagechecksucces)) {
				showDialog(result,1);	
			}else {
				showDialog(result,0);	
			}
			
			//Toast.makeText(context, "hello", 3).show();
			
		}

		
		
	}
	
	private void showDialog(String msg,final int i) {
		
		AlertDialog.Builder dlg = new AlertDialog.Builder(context);
		dlg.setCancelable(false);
		//dlg.setTitle(strTitle);
		dlg.setMessage(msg);
		final AlertDialog alert=dlg.create();
		alert.show();
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
			
				alert.dismiss();
				if (i==1) {
				startActivity(new Intent(context, CheckDate.class));
				//finish();
				}else {
					finish();
				}
			}
		}, 5000);
					
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		 Client="none";
	     Branch="none";
	}
	
}
