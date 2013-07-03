package com.carapp.activity;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.carapp.server.CreatePdf;
import com.carapp.server.UploadDataInfo;
import com.carapp.util.JobDataDetail;
import com.carapp.util.PdfInfo;
import com.example.carappnew.R;

public class ThirdScreen extends Activity{
	
private Button btcust_reson_for_visit,btdealer_recomendation,btcust_approved_work;

private Button btsubmit,customer_signature,saleperson_signature,attendall;
private ImageView img_cust,img_sal;
private LinearLayout layoutlinear_cust_reson_for_visit,layoutlinear_deaer_recomendation,layoutlinear_cust_approved_work;
//private LinearLayout layoutcustomer_signature,layoutsaleperson_signature;

private RadioButton rbradioButton1;
private RadioGroup  rbgradioButtongroup; 

private EditText Quotation1,Quotation2;
/*public static ArrayList<String>  alcust_reson_for_visit;
public static ArrayList<String>  aldealer_recomendation;
public static ArrayList<String>  alcust_approved_work;
*/
private ArrayList<String>   datacust_reson_for_visit;
private ArrayList<String>   datadealer_recomendation;
private ArrayList<String>   datacust_approved_work;

private boolean isCustomerSignaturedraw;
private boolean isSalePersonSignaturedraw;

String radiogrouptext;
 Intent i;
Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.customer_data_3);
	    i=getIntent();
	    setcontent();
	}
	
	private void setcontent() {
		context=this;
		radiogrouptext="";
		isCustomerSignaturedraw=false;
		isSalePersonSignaturedraw=false;
		/*datacust_reson_for_visit.clear();
		datadealer_recomendation.clear();
		datacust_approved_work.clear(); */
		 
		 
		 btcust_reson_for_visit=(Button)findViewById(R.id.cust_reson_for_visit);
		 btcust_reson_for_visit.setOnClickListener(listener);
		 btdealer_recomendation=(Button)findViewById(R.id.deaer_recomendation);
		 btdealer_recomendation.setOnClickListener(listener);
		 btcust_approved_work=(Button)findViewById(R.id.cust_approved_work);
		 btcust_approved_work.setOnClickListener(listener);
		
		 btsubmit=(Button)findViewById(R.id.submit);
		 btsubmit.setOnClickListener(listener);
		 attendall=(Button)findViewById(R.id.attend_All);
		 attendall.setOnClickListener(listener);
		 
		 Quotation1=(EditText)findViewById(R.id.quotation1);
		 Quotation2=(EditText)findViewById(R.id.quotation2);
		 
		 customer_signature=(Button)findViewById(R.id.customer_sig);
		 customer_signature.setOnClickListener(listener);
		 saleperson_signature=(Button)findViewById(R.id.saleperson_sig);
		 saleperson_signature.setOnClickListener(listener);
		 img_cust=(ImageView)findViewById(R.id.image_cust);
		 img_sal=(ImageView)findViewById(R.id.image_sale);
		 
		 layoutlinear_cust_reson_for_visit=(LinearLayout)findViewById(R.id.linear_cust_reson_for_visit);
		 layoutlinear_deaer_recomendation=(LinearLayout)findViewById(R.id.linear_deaer_recomendation);
		 layoutlinear_cust_approved_work=(LinearLayout)findViewById(R.id.linear_cust_approved_work);
		 
		 
		 rbradioButton1=(RadioButton)findViewById(R.id.radioButton1);
		 rbradioButton1.setEnabled(false);
		 rbgradioButtongroup=(RadioGroup)findViewById(R.id.radioGroup1);
		 rbradioButton1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					radiogrouptext=rbradioButton1.getText().toString();
					radioGroupdisable();
				}else {
					radiogrouptext="";
					radioGroupenable();
				}
			}
		});
		 rbgradioButtongroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				//int checked =group.getCheckedRadioButtonId();
				switch (checkedId) {
				case R.id.radio0:
					radiogrouptext="Work Declined";
					
					break;
				case R.id.radio1:
					radiogrouptext="No Work Required";
					
					break;
				case R.id.radio2:
					radiogrouptext="Unable to Assist";
					
					break;

				default:
					break;
				}
			}
		});
	/*	 
		 alcust_reson_for_visit=JobDataDetail.getResonForVisit();
		 aldealer_recomendation=JobDataDetail.getDealerRecomendation();
		 alcust_approved_work=JobDataDetail.getCustApprovedWork();*/
		 
		   datacust_reson_for_visit=new ArrayList<String>();
		   datadealer_recomendation=new ArrayList<String>();
		   datacust_approved_work=new ArrayList<String>();
		
	}

	  private OnClickListener listener        =   new View.OnClickListener(){
		    @Override
		        public void onClick(View v){
		    	
		            switch (v.getId()) {
		            
		            
		            
					case R.id.cust_reson_for_visit:
						
						Intent i=new Intent(getApplicationContext(), CustResonForVisitList.class);
						i.putExtra("listname", 0);
						startActivityForResult(i, 1001);
						break;
					case R.id.deaer_recomendation:
						Intent i1=new Intent(getApplicationContext(), CustResonForVisitList.class);
						i1.putExtra("listname", 1);
						startActivityForResult(i1, 1002);
						break;
						
					case R.id.cust_approved_work:
						Intent i11=new Intent(getApplicationContext(), CustResonForVisitList.class);
						i11.putExtra("listname", 2);
						startActivityForResult(i11, 1003);
						break;
					case R.id.customer_sig:
						Intent i111=new Intent(getApplicationContext(), CaptureSignature.class);
						i111.putExtra("For", "customer");
						startActivityForResult(i111, 1004);
						break;
					case R.id.saleperson_sig:
						Intent i1111=new Intent(getApplicationContext(), CaptureSignature.class);
						i1111.putExtra("For", "saleperson");
						startActivityForResult(i1111, 1005);
						break;
					
					
					
					case R.id.submit:
						if (!isCustomerSignaturedraw||!isSalePersonSignaturedraw) {
							
							Toast.makeText(context, "Draw Signature", Toast.LENGTH_LONG).show();
							return;
						}else if (Quotation1.getText().length()<1||Quotation2.getText().length()<1) {
							Toast.makeText(context, "Fill all quotation fields", Toast.LENGTH_LONG).show();
							return;
						}
						else if (isCustomerSignaturedraw&&isSalePersonSignaturedraw) {
							if (radiogrouptext.length()>0) {
							Toast.makeText(context, "completed", Toast.LENGTH_LONG).show();	
							
							UploadDataInfo.datacust_approved_work=datacust_approved_work;
							UploadDataInfo.datacust_reson_for_visit=datacust_reson_for_visit;
							UploadDataInfo.datadealer_recomendation=datadealer_recomendation;
							UploadDataInfo.radiodata=radiogrouptext;
							UploadDataInfo.Quotation1=Quotation1.getText().toString();
							UploadDataInfo.Quotation2=Quotation2.getText().toString();
							
							new CreatePdf(context).execute("");
							
							}else {
								Toast.makeText(context, "select radio", Toast.LENGTH_LONG).show();	
									return;
							}
							
						}
						
						break;
						case R.id.attend_All:
							if (datadealer_recomendation.size()>0) {
								for (int j = 0; j < datadealer_recomendation.size(); j++) {
									if (!isStringAdded(datadealer_recomendation.get(j), datacust_approved_work)) {

										inflateEditRow(datadealer_recomendation.get(j),layoutlinear_cust_approved_work,datacust_approved_work);
										 datacust_approved_work.add(datadealer_recomendation.get(j));
										rbradioButton1.setEnabled(true);
										
										//radioGroupdisable();
									}
										
									}
								}	
							
						
							
							break;	
					default:
						break;
					}
		           
		        }
		    };
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		String value;
		if (requestCode==1001&&resultCode==RESULT_OK) {
			
			//int position=data.getIntExtra("position", 0);
			//value=alcust_reson_for_visit.get(position);
			value=data.getStringExtra("position");
			if (!isStringAdded(value, datacust_reson_for_visit)) {
				datacust_reson_for_visit.add(value);
				inflateEditRow(value,layoutlinear_cust_reson_for_visit,datacust_reson_for_visit);
			}else {
				Toast.makeText(context, "Allready Added", Toast.LENGTH_SHORT).show();
			}
			
			//alcust_reson_for_visit.remove(position);
			
		}else if (requestCode==1002&&resultCode==RESULT_OK) {
			//int position=data.getIntExtra("position", 0);
			// value=aldealer_recomendation.get(position);
			 value=data.getStringExtra("position");
			 if (!isStringAdded(value, datadealer_recomendation)) {
				 datadealer_recomendation.add(value);
					inflateEditRow(value,layoutlinear_deaer_recomendation,datadealer_recomendation);
				}else {
					Toast.makeText(context, "Allready Added", Toast.LENGTH_SHORT).show();
				}
			 
			//aldealer_recomendation.remove(position);
			
		}else if (requestCode==1003&&resultCode==RESULT_OK) {
		//	int position=data.getIntExtra("position", 0);
		//	 value=alcust_approved_work.get(position);
			value=data.getStringExtra("position");
			
			if (value.equals("all")) {
				layoutlinear_cust_approved_work.removeViews(0, layoutlinear_cust_approved_work.getChildCount()-1);
				datacust_approved_work.clear();
				for (int i = 0; i < JobDataDetail.getCustApprovedWork().size(); i++) {
					datacust_approved_work.add(JobDataDetail.getCustApprovedWork().get(i)); 
					inflateEditRow(JobDataDetail.getCustApprovedWork().get(i),layoutlinear_cust_approved_work,datacust_approved_work);
				}
			      /*while(itr.hasNext()) {
			       //  Object element = itr.next();
			    	    try {
							datacust_approved_work.add(itr.next()); 
							inflateEditRow(itr.next(),layoutlinear_cust_approved_work,datacust_approved_work);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					Log.d("value", itr.next())	;
			      }*/
			        rbradioButton1.setEnabled(true);
				
					//radioGroupdisable();
				
			}else {
				 if (!isStringAdded(value, datacust_approved_work)) {
					
						inflateEditRow(value,layoutlinear_cust_approved_work,datacust_approved_work);
						 datacust_approved_work.add(value);
						rbradioButton1.setEnabled(true);
						
						//radioGroupdisable();
					}else {
						Toast.makeText(context, "Allready Added", Toast.LENGTH_SHORT).show();
					}
			}
			
		   
		//	alcust_approved_work.remove(position);
			
		}else if (requestCode==1004&&resultCode==RESULT_OK) {
			File imgFile = new  File(PdfInfo.path+"PhotoCustSIG.jpeg");
			if(imgFile.exists()){

			    try {
					Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
					Bitmap  b=Bitmap.createScaledBitmap(myBitmap, 100, 100, true);
					img_cust.setImageBitmap(b);
					
					isCustomerSignaturedraw=true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
		}else if (requestCode==1005&&resultCode==RESULT_OK) {
			
			File imgFile = new  File(PdfInfo.path+"PhotoSaleSIG.jpeg");
			if(imgFile.exists()){

			    try {
					Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    Bitmap  b=Bitmap.createScaledBitmap(myBitmap, 100, 100, true);
					img_sal.setImageBitmap(b);
					isSalePersonSignaturedraw=true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}	
		}
	}

	private void inflateEditRow(final String value,final LinearLayout layout,final ArrayList<String> list) {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View rowView = inflater.inflate(R.layout.list_item_1, null);
		
		final TextView textvalue = (TextView) rowView
				.findViewById(R.id.list_item_1_textview);
		textvalue.setText(value);
		final Button delete = (Button) rowView
				.findViewById(R.id.list_item_1_delete);
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				removeFromlist(value,list);	
			layout.removeView(rowView);	
			if (datacust_approved_work.size()<1) {
				rbradioButton1.setChecked(false);
				rbradioButton1.setEnabled(false);
			}
			
			}
		});

		// Inflate at the end of all rows but before the "Add new" button
		layout.addView(rowView, layout.getChildCount() - 1);
	}

	

		private void radioGroupdisable() {
		for (int i = 0; i <rbgradioButtongroup.getChildCount(); i++) 
		{
			rbgradioButtongroup.getChildAt(i).setEnabled(false); 
			rbgradioButtongroup.clearCheck();
			
			
		}
	}
		private void radioGroupenable() {
			for (int i = 0; i <rbgradioButtongroup.getChildCount(); i++) 
			{
				rbgradioButtongroup.getChildAt(i).setEnabled(true); 
				//rbgradioButtongroup.clearCheck();
				
				
			}
		}
		@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			Log.i("jobdata", "onDestroy");
			
	     	MainActivity.mainactivity.finish();
			Firstscreen.customerdata.finish();
		//	MainActivity.mainactivity=null;
		//	Firstscreen.customerdata=null;
			
			
			//setResult(RESULT_OK, i);
		}
		private boolean isStringAdded(String value,ArrayList<String> list) {
			boolean added=false;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(value)) {
					
					added=true;
				}
				
			}
			return added;
			
		}
		private void removeFromlist(String value,ArrayList<String> list) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(value)) {
					
					list.remove(i);
				}
				
			}
		}
}
