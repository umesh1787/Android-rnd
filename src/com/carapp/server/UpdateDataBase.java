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
import com.carapp.util.UploadDataInfo;

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
			
		//*****************new*****************
		
				paramList.add(new BasicNameValuePair("company",UploadDataInfo.company));
				paramList.add(new BasicNameValuePair("email",UploadDataInfo.email));
				
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
				
				
				paramList.add(new BasicNameValuePair("observations",UploadDataInfo.observations));
				paramList.add(new BasicNameValuePair("wheel_nuts_torqued",UploadDataInfo.wheel_nuts_torqued));
				paramList.add(new BasicNameValuePair("wheels_cleaned",UploadDataInfo.wheels_cleaned));
				paramList.add(new BasicNameValuePair("wheels_balanced",UploadDataInfo.wheels_balanced));
				paramList.add(new BasicNameValuePair("alignment_done",UploadDataInfo.alignment_done));
				paramList.add(new BasicNameValuePair("tyre_pressure_front",UploadDataInfo.tyre_pressure_front));
				paramList.add(new BasicNameValuePair("tyre_pressure_back",UploadDataInfo.tyre_pressure_back));
				paramList.add(new BasicNameValuePair("tyres_polished",UploadDataInfo.tyres_polished));
				paramList.add(new BasicNameValuePair("lock_nut_returned",UploadDataInfo.lock_nut_returned));
				paramList.add(new BasicNameValuePair("car_tested_by_salesperson",UploadDataInfo.car_tested_by_salesperson));
				paramList.add(new BasicNameValuePair("work_inspected_by_salesperson",UploadDataInfo.work_inspected_by_salesperson));
				paramList.add(new BasicNameValuePair("work_approved_by_salesperson",UploadDataInfo.work_approved_by_salesperson));
				paramList.add(new BasicNameValuePair("customer_satisfied",UploadDataInfo.customer_satisfied)); 
				
				
				
				
				//**************************print all values*************************************
				
				Log.e("branch",UploadDataInfo.strBranch);
				Log.e("salesperson",UploadDataInfo.strSaleperson);
				Log.e("customer",UploadDataInfo.strCustomer);
				Log.e("contact",UploadDataInfo.strContactNo);
				Log.e("address",UploadDataInfo.strAddress);
				Log.e("make",UploadDataInfo.strMake);
				Log.e("model",UploadDataInfo.strModel);
				Log.e("year",UploadDataInfo.strYear);
				Log.e("odometer",UploadDataInfo.strOdomstrer);
				Log.e("reg_plate_no",UploadDataInfo.strRegistration);
				Log.e("date",UploadDataInfo.strDate);
				Log.e("time",UploadDataInfo.strTime);
				
				
				
				Log.e("tyer_condition_lf",UploadDataInfo.tyer_condition_lf);
				Log.e("tyer_condition_lb",UploadDataInfo.tyer_condition_lb);
				Log.e("tyer_condition_rf",UploadDataInfo.tyer_condition_rf);
				Log.e("tyer_condition_rb",UploadDataInfo.tyer_condition_rb);
				
				Log.e("tyre_size_lf",UploadDataInfo.tyre_size_lf);
				Log.e("tyre_size_lb",UploadDataInfo.tyre_size_lb);
				Log.e("tyre_size_rf",UploadDataInfo.tyre_size_rf);
				Log.e("tyre_size_rb",UploadDataInfo.tyre_size_rb);
				
				Log.e("tyre_depth_lf",UploadDataInfo.tyre_depth_lfx+" * "+UploadDataInfo.tyre_depth_lfy);
				Log.e("tyre_depth_lb",UploadDataInfo.tyre_depth_lbx+" * "+UploadDataInfo.tyre_depth_lby);
				Log.e("tyre_depth_rf",UploadDataInfo.tyre_depth_rfx+" * "+UploadDataInfo.tyre_depth_rfy);
				Log.e("tyre_depth_rb",UploadDataInfo.tyre_depth_rbx+" * "+UploadDataInfo.tyre_depth_rby);
				
				Log.e("brake_pad_lf",UploadDataInfo.brake_pad_lf);
				Log.e("brake_pad_lb",UploadDataInfo.brake_pad_lb);
				Log.e("brake_pad_rf",UploadDataInfo.brake_pad_rf);
				Log.e("brake_pad_rb",UploadDataInfo.brake_pad_rb);
				
				Log.e("brake_disk_lf",UploadDataInfo.brake_disk_lf);
				Log.e("brake_disk_lb",UploadDataInfo.brake_disk_lb);
				Log.e("brake_disk_rf",UploadDataInfo.brake_disk_rf);
				Log.e("brake_disk_rb",UploadDataInfo.brake_disk_rb);
				
				Log.e("shocker_lf",UploadDataInfo.shocker_lf);
				Log.e("shocker_lb",UploadDataInfo.shocker_lb);
				Log.e("shocker_rf",UploadDataInfo.shocker_rf);
				Log.e("shocker_rb",UploadDataInfo.shocker_rb);
				
				Log.e("wheel_lf",UploadDataInfo.wheel_lf);
				Log.e("wheel_lb",UploadDataInfo.wheel_lb);
				Log.e("wheel_rf",UploadDataInfo.wheel_rf);
				Log.e("wheel_rb",UploadDataInfo.wheel_rb);
				
				Log.e("immoblizer",UploadDataInfo.immoblizer_f);
			    Log.e("battery",UploadDataInfo.battery_f);
				
				Log.e("spare_wheel",UploadDataInfo.spare_wheel_b);
				Log.e("lock_nut",UploadDataInfo.lock_nut_b);
				
			
				
				Log.e("ph_damage_lf",UploadDataInfo.physical_damage_lf);
				Log.e("ph_damage_lb",UploadDataInfo.physical_damage_lb);
				Log.e("ph_damage_rf",UploadDataInfo.physical_damage_rf);
				Log.e("ph_damage_rb",UploadDataInfo.physical_damage_rb);
				Log.e("ir_by_ph_damage",UploadDataInfo.physical_damage_f);
				Log.e("sw_ln_ph_damage",UploadDataInfo.physical_damage_b);
				
			//***********************************************************************	
				if (UploadDataInfo.datacust_reson_for_visit.size()>0) {
					
				
				String str1=UploadDataInfo.datacust_reson_for_visit.get(0);
				for (int i = 1; i < UploadDataInfo.datacust_reson_for_visit.size(); i++) {
					
				str1=str1+","+UploadDataInfo.datacust_reson_for_visit.get(i);	
				}
				Log.e("cust_reson_for_visit",str1);
				}
				
				//***********************************************************************	
				if (UploadDataInfo.datadealer_recomendation.size()>0) {
							
				String str2=UploadDataInfo.datadealer_recomendation.get(0);
				for (int i = 1; i < UploadDataInfo.datadealer_recomendation.size(); i++) {
					
				str2=str2+","+UploadDataInfo.datadealer_recomendation.get(i);	
				}
				Log.e("dealer_recomendation",str2);
				}
				//***********************************************************************	
				if (UploadDataInfo.datacust_approved_work.size()>0) {
					
				String str3=UploadDataInfo.datacust_approved_work.get(0);
				for (int i = 1; i < UploadDataInfo.datacust_approved_work.size(); i++) {
					
				str3=str3+","+UploadDataInfo.datacust_approved_work.get(i);	
				}
				Log.e("cust_approved_work",str3);
				}
				
				Log.e("radiodata",UploadDataInfo.radiodata);
					
				//*****************new*****************
				
						Log.e("company",UploadDataInfo.company);
						Log.e("email",UploadDataInfo.email);
						
						Log.e("FF",UploadDataInfo.FF);
						Log.e("FL",UploadDataInfo.FL);
						Log.e("FR",UploadDataInfo.FR);
						Log.e("FM",UploadDataInfo.FM);
						Log.e("FSL",UploadDataInfo.FSL);
					    Log.e("FSR",UploadDataInfo.FSR);
						Log.e("BSL",UploadDataInfo.BSL);
						Log.e("BSR",UploadDataInfo.BSR);
						Log.e("BL",UploadDataInfo.BL);
						Log.e("BM",UploadDataInfo.BM);
						Log.e("BR",UploadDataInfo.BR);
						
						
						Log.e("observations",UploadDataInfo.observations);
						Log.e("wheel_nuts_torqued",UploadDataInfo.wheel_nuts_torqued);
						Log.e("wheels_cleaned",UploadDataInfo.wheels_cleaned);
						Log.e("wheels_balanced",UploadDataInfo.wheels_balanced);
						Log.e("alignment_done",UploadDataInfo.alignment_done);
						Log.e("tyre_pressure_front",UploadDataInfo.tyre_pressure_front);
						Log.e("tyre_pressure_back",UploadDataInfo.tyre_pressure_back);
						Log.e("tyres_polished",UploadDataInfo.tyres_polished);
						Log.e("lock_nut_returned",UploadDataInfo.lock_nut_returned);
						Log.e("car_tested_by_salesperson",UploadDataInfo.car_tested_by_salesperson);
						Log.e("work_inspected_by_salesperson",UploadDataInfo.work_inspected_by_salesperson);
						Log.e("work_approved_by_salesperson",UploadDataInfo.work_approved_by_salesperson);
						Log.e("customer_satisfied",UploadDataInfo.customer_satisfied); 
						
						
		//*************************************
		
						
						
						
		
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
