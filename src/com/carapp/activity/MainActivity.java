package com.carapp.activity;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.carapp.util.PdfInfo;
import com.carapp.util.UploadDataInfo;
import com.example.carappnew.R;

public class MainActivity extends Activity {

	WebView w;
	Intent intent;
	public static MainActivity mainactivity;
	/*private String 
	       tyer_condition_lf,      tyer_condition_lb,       tyer_condition_rf,      tyer_condition_rb,
	       tyre_size_lf,           tyre_size_lb,            tyre_size_rf,           tyre_size_rb,
	       tyre_depth_lf,          tyre_depth_lb,           tyre_depth_rf,          tyre_depth_rb,
	       brake_pad_lf,           brake_pad_lb,            brake_pad_rf,           brake_pad_rb, 
	       brake_disk_lf,          brake_disk_lb,           brake_disk_rf,          brake_disk_rb,
	       shocker_lf,             shocker_lb,              shocker_rf,             shocker_rb, 
       	   wheel_lf,               wheel_lb,                wheel_rf,               wheel_rb,
       	   spare_wheel_lf,         spare_wheel_lb,          spare_wheel_rf,         spare_wheel_rb, 
       	   immoblizer_lf,          immoblizer_lb,           immoblizer_rf,          immoblizer_rb,           immoblizer_f,         immoblizer_b,
	       battery_lf,             battery_lb,              battery_rf,             battery_rb,              battery_f,            battery_b,  
	       ir_by_ph_damage_lf,     ir_by_ph_damage_lb,      ir_by_ph_damage_rf,     ir_by_ph_damage_rb,      ir_by_ph_damage_f,    ir_by_ph_damage_b,
	       lock_nut_lf,            lock_nut_lb,             lock_nut_rf,            lock_nut_rb,             lock_nut_f,           lock_nut_b;  
	      
	*/       

	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		intent=getIntent();
	setContentView(R.layout.web_view);
		//w = new WebView(this);
	mainactivity=this;
		w=(WebView)findViewById(R.id.webview);
		w.getSettings().setLoadWithOverviewMode(true);
		w.getSettings().setUseWideViewPort(true);
		WebSettings webSettings = w.getSettings();
		webSettings.setBuiltInZoomControls(true);
		webSettings.setJavaScriptEnabled(true);
		
		
		
		//setContentView(w);
		
	
		//w.loadUrl("http://techsoftlabs.in/Client/carapp/carappnew.html");
		 
		w.loadUrl("file:///android_asset/car.html");
		 JsInterface jsInterface = new JsInterface();
	     	w.addJavascriptInterface(jsInterface, "android");
	}

	 private class JsInterface {
		 
		 String selectedcolor;
		 public void choosColor() {
			 Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
			 w.loadUrl("javascript:callFromActivity");
			
				
			
			
		}
		 
		  public void takePicture(String filename) {
		   Log.d("MSG FROM JAVASCRIPT", filename);
		//   Toast.makeText(getApplicationContext(), filename, Toast.LENGTH_LONG).show();
			Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			try {
				File path= new File(PdfInfo.path);
        		if (!path.exists()) {
        			path.mkdir();
        		}
        		 String sysdate = android.text.format.DateFormat.format(
     					"ddMMyyyy", new java.util.Date()).toString();
					File file = new File(PdfInfo.path+UploadDataInfo.strRegistration+"_"+filename+"_"+sysdate+".jpg");
        		//File file = new File(PdfInfo.path+"kk.jpg");
							 Uri outputFileUri = Uri.fromFile(file);
					cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
				// 	 Toast.makeText(getApplicationContext(), PdfInfo.path+"krishna"+"_"+filename+"_"+UploadDataInfo.strDate+".jpg", Toast.LENGTH_LONG).show();		
			        startActivityForResult(cameraIntent, 1111); 
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		  }
		  public void setValueFromWabePage(
				 String tyer_condition_lf,String  tyre_size_lf,String  tyre_depth_lfx,String  tyre_depth_lfy,String  brake_pad_lf,String  brake_disk_lf,String  shocker_lf,String  wheel_lf,String  physical_damage_lf,String  tyer_condition_lb,String  tyre_size_lb,String  tyre_depth_lbx,String  tyre_depth_lby,String  brake_pad_lb,String  brake_disk_lb,String  shocker_lb,String  wheel_lb,String  physical_damage_lb,String  tyer_condition_rf,String  tyre_size_rf,String  tyre_depth_rfx,String  tyre_depth_rfy,String  brake_pad_rf,String  brake_disk_rf,String  shocker_rf,String  wheel_rf,String  physical_damage_rf,String  tyer_condition_rb,String  tyre_size_rb,String  tyre_depth_rbx,String  tyre_depth_rby,String  brake_pad_rb,String  brake_disk_rb,String  shocker_rb,String  wheel_rb,String  physical_damage_rb,String  immoblizer_f,String  battery_f,String  physical_damage_f,String  spare_wheel_b,String  lock_nut_b,String  physical_damage_b)
		  {
			//  Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_LONG).show();
			  Log.d("tttttttttttt", "setValueFromWabePage");
			 Log.i("setValueFromWabePage", " reaching in android");
			  UploadDataInfo.tyer_condition_lf=tyer_condition_lf;    UploadDataInfo.tyre_size_lf= tyre_size_lf;    UploadDataInfo.tyre_depth_lfx= tyre_depth_lfx;
			  UploadDataInfo.tyre_depth_lfy= tyre_depth_lfy;         UploadDataInfo.brake_pad_lf= brake_pad_lf;    UploadDataInfo.brake_disk_lf=brake_disk_lf;
			  UploadDataInfo.shocker_lf=shocker_lf;                  UploadDataInfo.wheel_lf=wheel_lf;
			  UploadDataInfo.physical_damage_lf= physical_damage_lf;
			  
			  UploadDataInfo.tyer_condition_lb=tyer_condition_lb;    UploadDataInfo.tyre_size_lb=tyre_size_lb;     UploadDataInfo.tyre_depth_lbx=tyre_depth_lbx;
			  UploadDataInfo.tyre_depth_lby=tyre_depth_lby;          UploadDataInfo.brake_pad_lb=brake_pad_lb;     UploadDataInfo.brake_disk_lb=brake_disk_lb;
			  UploadDataInfo.shocker_lb=shocker_lb;                  UploadDataInfo.wheel_lb=wheel_lb;
			  UploadDataInfo.physical_damage_lb=physical_damage_lb;
			  
			  
			  UploadDataInfo.tyer_condition_rf=tyer_condition_rf;    UploadDataInfo.tyre_size_rf=tyre_size_rf;     UploadDataInfo.tyre_depth_rfx=tyre_depth_rfx;
			  UploadDataInfo.tyre_depth_rfy=tyre_depth_rfy;          UploadDataInfo.brake_pad_rf=brake_pad_rf;     UploadDataInfo.brake_disk_rf=brake_disk_rf;
			  UploadDataInfo.shocker_rf=shocker_rf;                  UploadDataInfo.wheel_rf=wheel_rf;
			  UploadDataInfo.physical_damage_rf=physical_damage_rf;
			  
			  UploadDataInfo.tyer_condition_rb=tyer_condition_rb;    UploadDataInfo.tyre_size_rb=tyre_size_rb;     UploadDataInfo.tyre_depth_rbx=tyre_depth_rbx; 
			  UploadDataInfo.tyre_depth_rby=tyre_depth_rby;          UploadDataInfo.brake_pad_rb=brake_pad_rb;     UploadDataInfo.brake_disk_rb=brake_disk_rb;
			  UploadDataInfo.shocker_rb=shocker_rb;                  UploadDataInfo.wheel_rb=wheel_rb;
			  UploadDataInfo.physical_damage_rb=physical_damage_rb;
			  
			  UploadDataInfo.immoblizer_f=immoblizer_f;              UploadDataInfo.battery_f=battery_f;           UploadDataInfo.physical_damage_f=physical_damage_f;
			  UploadDataInfo.spare_wheel_b=spare_wheel_b;            UploadDataInfo.lock_nut_b=lock_nut_b;         UploadDataInfo.physical_damage_b=physical_damage_b;
		     
		     
	   if (tyer_condition_lf.equals("Tyre Condition")||tyre_size_lf.equals("")||tyre_depth_lfx.equals("Tyre Depth X")||tyre_depth_lfy.equals("Tyre Depth Y")||brake_pad_lf.equals("Brake Pads")||brake_disk_lf.equals("Brake Disks")||shocker_lf.equals("Shocks")||wheel_lf.equals("Wheels")||physical_damage_lf.equals("Physical Damage")||tyer_condition_lb.equals("Tyre Condition")||tyre_size_lb.equals("")||tyre_depth_lbx.equals("Tyre Depth X")||tyre_depth_lby.equals("Tyre Depth Y")||brake_pad_lb.equals("Brake Pads")||brake_disk_lb.equals("Brake Disks")||shocker_lb.equals("Shocks")||wheel_lb.equals("Wheels")||physical_damage_lb.equals("Physical Damage")||tyer_condition_rf.equals("Tyre Condition")||tyre_size_rf.equals("")||tyre_depth_rfx.equals("Tyre Depth X")||tyre_depth_rfy.equals("Tyre Depth Y")||brake_pad_rf.equals("Brake Pads")||brake_disk_rf.equals("Brake Disks")||shocker_rf.equals("Shocks")||wheel_rf.equals("Wheels")||physical_damage_rf.equals("Physical Damage")||tyer_condition_rb.equals("Tyre Condition")||tyre_size_rb.equals("")||tyre_depth_rbx.equals("Tyre Depth X")||tyre_depth_rby.equals("Tyre Depth Y")||brake_pad_rb.equals("Brake Pads")||brake_disk_rb.equals("Brake Disks")||shocker_rb.equals("Shocks")||wheel_rb.equals("Wheels")||physical_damage_rb.equals("Physical Damage")||immoblizer_f.equals("Immobilizer")||battery_f.equals("Battery")||physical_damage_f.equals("Physical Damage")||spare_wheel_b.equals("Spare Wheel")||lock_nut_b.equals("Lock Nut")||physical_damage_b.equals("Physical Damage")) {
	    		Toast.makeText(getApplicationContext(), "Enter All Fields", Toast.LENGTH_LONG).show();
		}else {
			Intent i=new Intent(getApplicationContext(), ThirdScreen.class);
		       startActivity(i);
		       overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
		}          
	           
		       
		}
		 }
	 @Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			
			
		}
}