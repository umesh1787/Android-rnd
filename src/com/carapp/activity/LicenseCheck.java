package com.carapp.activity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.carapp.server.Mail;
import com.example.carappnew.R;

public class LicenseCheck extends Activity{
	
	  private static String LicenseCheckaddress="http://techsoftlabs.com/client/branch/MAC.txt";
	  private static String messagecheckLicense="Please wait while checking License";
	  private static String messagecheckLicenseerror="Warning: License not found, requesting new license";
	  private static  String t="LicenseCheck";
	  private String ma_a="";
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
		tv.setText(messagecheckLicense);
		
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
				WifiManager wiman = (WifiManager) getSystemService(Context.WIFI_SERVICE);
				ma_a = wiman.getConnectionInfo().getMacAddress();
				Log.i(t, ""+ma_a);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				Log.i(t, " "+e1);
			}
			try {
			    // Create a URL for the desired page
			    URL url = new URL(LicenseCheckaddress);

			    // Read all the text returned by the server
			    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			    String str;
			 
			    while ((str = in.readLine()) != null) {
			    	Log.i(t, str+" "+ma_a);
			      if (ma_a.equals(str)) {
			    	  response="sucesses";
			    	  break;
				}else {
					 response="faild";
				}
			    }
			   in.close();
			   
			 
			   
			} catch (Exception e) {
				
				response="server problem";
			Log.i(t, "mal "+e);
			}
			
			return response;
		
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result.equals("sucesses")) {
			Toast.makeText(context,"LicenseCheck sucesses", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(context,Login.class));
			finish();
			}else if (result.equals("faild")) {
				showDialog(messagecheckLicenseerror+" your mac address is- "+ma_a);
				sendMail("Client value is- "+splashActivity.Client+"\nBranch value is- "+splashActivity.Branch+"\n your Device MAC Address is- "+ma_a);
				
			}else if (result.equals("server problem")) {
				showDialog(messagecheckLicenseerror+" server not respond");
			}
			
			
		}



public void  showDialog(String message) {
	
  
 
                 AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(context);
 
  
 
                
                         alertDialogBuilder1
 
                         .setCancelable(false)
                         
                         .setMessage(message)
                         .setPositiveButton("OK", new DialogInterface.OnClickListener() {
 
                                     public void onClick(DialogInterface dialog, int id) {
                                    	 //startActivity(new Intent(context,Login.class));
                                    	   dialog.cancel();
                                           finish();
                                     }
 
                                 });
 
                       
  AlertDialog alertD = alertDialogBuilder1.create();
 
 alertD.show();
}
private void sendMail(String message) {

            	
        		 Mail m = new Mail("carappk@gmail.com", "1111222233334444"); 
        		
        	     String[] toArr = {"gustavo@learn3d.co.za"}; 
        	     Log.i(t,"gustavo@learn3d.co.za"); 
        	     m.setTo(toArr); 
        	     m.setFrom("carappk@gmail.com"); 
        	     m.setSubject("License request"); 
        	     m.setBody(message); 

        	 
        		      
        		       try {
						if(m.send()) { 
							   Log.i(t,  "mail send");
							   Toast.makeText(context, "Mail Send", Toast.LENGTH_SHORT).show();
							  // stopSelf();
						     } else {
						    	  Log.i(t,  "mail not send(else)");
						    	//  stopSelf();
							}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		      
}             
        		
         
}
}
