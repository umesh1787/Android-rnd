package com.carapp.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.carapp.util.HTTPUtils;
import com.carapp.util.PdfInfo;

public class UpdateDataBase extends AsyncTask<String, Integer, String>{

	 Context context;
	 ProgressDialog bar;
	 private String t="UpdateDataBase";
	 
	  public UpdateDataBase(Context context)
	  {
	 	this.context=context; 
	 	 
	  }

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			
			bar = new ProgressDialog(context);
		    bar.setMessage("Updating database...\n Please wait...");
		    bar.setCancelable(false);
		    bar.show();
		   
		}
	  
	@Override
	protected String doInBackground(String... params) {
		
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		paramList.add(new BasicNameValuePair("action","storeindb" ));
		
		
		
		paramList.add(new BasicNameValuePair("branch",UploadDataInfo.strBranch));
		paramList.add(new BasicNameValuePair("salesperson",UploadDataInfo.strSaleperson));
		paramList.add(new BasicNameValuePair("customer",UploadDataInfo.strCustomer));
		paramList.add(new BasicNameValuePair("contact",UploadDataInfo.strContactNo));
		paramList.add(new BasicNameValuePair("address",UploadDataInfo.strAddress));
		paramList.add(new BasicNameValuePair("make",UploadDataInfo.strMake));
		paramList.add(new BasicNameValuePair("model",UploadDataInfo.strModel));
		paramList.add(new BasicNameValuePair("year",UploadDataInfo.strYear));
		paramList.add(new BasicNameValuePair("odometer",UploadDataInfo.strOdomstrer));
		paramList.add(new BasicNameValuePair("reg_plate_no",UploadDataInfo.strRegistration));
		paramList.add(new BasicNameValuePair("date",UploadDataInfo.strDate));
		paramList.add(new BasicNameValuePair("time",UploadDataInfo.strTime));
		
		
		
		paramList.add(new BasicNameValuePair("tyer_condition_lf",UploadDataInfo.tyer_condition_lf));
		paramList.add(new BasicNameValuePair("tyer_condition_lb",UploadDataInfo.tyer_condition_lb));
		paramList.add(new BasicNameValuePair("tyer_condition_rf",UploadDataInfo.tyer_condition_rf));
		paramList.add(new BasicNameValuePair("tyer_condition_rb",UploadDataInfo.tyer_condition_rb));
		
		paramList.add(new BasicNameValuePair("tyre_size_lf",UploadDataInfo.tyre_size_lf));
		paramList.add(new BasicNameValuePair("tyre_size_lb",UploadDataInfo.tyre_size_lb));
		paramList.add(new BasicNameValuePair("tyre_size_rf",UploadDataInfo.tyre_size_rf));
		paramList.add(new BasicNameValuePair("tyre_size_rb",UploadDataInfo.tyre_size_rb));
		
		paramList.add(new BasicNameValuePair("tyre_depth_lf",UploadDataInfo.tyre_depth_lfx+" * "+UploadDataInfo.tyre_depth_lfy));
		paramList.add(new BasicNameValuePair("tyre_depth_lb",UploadDataInfo.tyre_depth_lbx+" * "+UploadDataInfo.tyre_depth_lby));
		paramList.add(new BasicNameValuePair("tyre_depth_rf",UploadDataInfo.tyre_depth_rfx+" * "+UploadDataInfo.tyre_depth_rfy));
		paramList.add(new BasicNameValuePair("tyre_depth_rb",UploadDataInfo.tyre_depth_rbx+" * "+UploadDataInfo.tyre_depth_rby));
		
		paramList.add(new BasicNameValuePair("brake_pad_lf",UploadDataInfo.brake_pad_lf));
		paramList.add(new BasicNameValuePair("brake_pad_lb",UploadDataInfo.brake_pad_lb));
		paramList.add(new BasicNameValuePair("brake_pad_rf",UploadDataInfo.brake_pad_rf));
		paramList.add(new BasicNameValuePair("brake_pad_rb",UploadDataInfo.brake_pad_rb));
		
		paramList.add(new BasicNameValuePair("brake_disk_lf",UploadDataInfo.brake_disk_lf));
		paramList.add(new BasicNameValuePair("brake_disk_lb",UploadDataInfo.brake_disk_lb));
		paramList.add(new BasicNameValuePair("brake_disk_rf",UploadDataInfo.brake_disk_rf));
		paramList.add(new BasicNameValuePair("brake_disk_rb",UploadDataInfo.brake_disk_rb));
		
		paramList.add(new BasicNameValuePair("shocker_lf",UploadDataInfo.shocker_lf));
		paramList.add(new BasicNameValuePair("shocker_lb",UploadDataInfo.shocker_lb));
		paramList.add(new BasicNameValuePair("shocker_rf",UploadDataInfo.shocker_rf));
		paramList.add(new BasicNameValuePair("shocker_rb",UploadDataInfo.shocker_rb));
		
		paramList.add(new BasicNameValuePair("wheel_lf",UploadDataInfo.wheel_lf));
		paramList.add(new BasicNameValuePair("wheel_lb",UploadDataInfo.wheel_lb));
		paramList.add(new BasicNameValuePair("wheel_rf",UploadDataInfo.wheel_rf));
		paramList.add(new BasicNameValuePair("wheel_rb",UploadDataInfo.wheel_rb));
		
		paramList.add(new BasicNameValuePair("immoblizer",UploadDataInfo.immoblizer_f));
	    paramList.add(new BasicNameValuePair("battery",UploadDataInfo.battery_f));
		
		paramList.add(new BasicNameValuePair("spare_wheel",UploadDataInfo.spare_wheel_b));
		paramList.add(new BasicNameValuePair("lock_nut",UploadDataInfo.lock_nut_b));
		
	
		
		paramList.add(new BasicNameValuePair("ph_damage_lf",UploadDataInfo.physical_damage_lf));
		paramList.add(new BasicNameValuePair("ph_damage_lb",UploadDataInfo.physical_damage_lb));
		paramList.add(new BasicNameValuePair("ph_damage_rf",UploadDataInfo.physical_damage_rf));
		paramList.add(new BasicNameValuePair("ph_damage_rb",UploadDataInfo.physical_damage_rb));
		paramList.add(new BasicNameValuePair("ir_by_ph_damage",UploadDataInfo.physical_damage_f));
		paramList.add(new BasicNameValuePair("sw_ln_ph_damage",UploadDataInfo.physical_damage_b));
		
	//***********************************************************************	
		if (UploadDataInfo.datacust_reson_for_visit.size()>0) {
			
		
		String str1=UploadDataInfo.datacust_reson_for_visit.get(0);
		for (int i = 1; i < UploadDataInfo.datacust_reson_for_visit.size(); i++) {
			
		str1=str1+","+UploadDataInfo.datacust_reson_for_visit.get(i);	
		}
		paramList.add(new BasicNameValuePair("cust_reson_for_visit",str1));
		}
		
		//***********************************************************************	
		if (UploadDataInfo.datadealer_recomendation.size()>0) {
					
		String str2=UploadDataInfo.datadealer_recomendation.get(0);
		for (int i = 1; i < UploadDataInfo.datadealer_recomendation.size(); i++) {
			
		str2=str2+","+UploadDataInfo.datadealer_recomendation.get(i);	
		}
		paramList.add(new BasicNameValuePair("dealer_recomendation",str2));
		}
		//***********************************************************************	
		if (UploadDataInfo.datacust_approved_work.size()>0) {
			
		String str3=UploadDataInfo.datacust_approved_work.get(0);
		for (int i = 1; i < UploadDataInfo.datacust_approved_work.size(); i++) {
			
		str3=str3+","+UploadDataInfo.datacust_approved_work.get(i);	
		}
		paramList.add(new BasicNameValuePair("cust_approved_work",str3));
		}
		
		paramList.add(new BasicNameValuePair("radiodata",UploadDataInfo.radiodata));
		
		paramList.add(new BasicNameValuePair("FF",UploadDataInfo.FF));
		paramList.add(new BasicNameValuePair("FL",UploadDataInfo.FL));
		paramList.add(new BasicNameValuePair("FR",UploadDataInfo.FR));
		paramList.add(new BasicNameValuePair("FM",UploadDataInfo.FM));
		paramList.add(new BasicNameValuePair("FSL",UploadDataInfo.FSL));
	    paramList.add(new BasicNameValuePair("FSR",UploadDataInfo.FSR));
		paramList.add(new BasicNameValuePair("BSL",UploadDataInfo.BSL));
		paramList.add(new BasicNameValuePair("BSR",UploadDataInfo.BSR));
		paramList.add(new BasicNameValuePair("BL",UploadDataInfo.BL));
		paramList.add(new BasicNameValuePair("BM",UploadDataInfo.BM));
		paramList.add(new BasicNameValuePair("BR",UploadDataInfo.BR));
		
		
		String response = HTTPUtils.executeHttpGet(paramList,"http://techsoftlabs.com/taskdev/carapp/mobile_webservices/mobile_webservice.php");
	
		Log.i(t, response);
		
		
		return "response";
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		bar.dismiss();
		new UploadFile(context, PdfInfo.path).execute("");
		
	}



	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		// bar.setProgress((int)((values[0]*100)/(totalsize+1)));
		
	}

}
