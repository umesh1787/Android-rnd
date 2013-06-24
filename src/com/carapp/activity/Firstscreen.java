package com.carapp.activity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.carapp.util.PdfInfo;
import com.carapp.util.UploadDataInfo;
import com.example.carappnew.R;

public class Firstscreen extends Activity {

	private EditText etBranch, etSaleperson, etCustomer, etContactNo,
			etAddress, etMake, etModel, etYear, etOdometer, etRegistration,
			etDate, etTime;
	private  String strBranch, strSaleperson, strCustomer, strContactNo, strAddress,
			strMake, strModel, strYear, strOdomstrer, strRegistration, strDate,
			strTime;
    private Button btnext;
    EditText[] edittext;
    DateFormat formatDateTime=DateFormat.getDateInstance();
	Calendar dateTime=Calendar.getInstance();
	static final int DATE_DIALOG_ID = 999;
	public static  Firstscreen customerdata ;
    Intent i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_data_1);
		customerdata=this;
        setContent();
	}

	void setContent() {
		
		i=getIntent();
		
		btnext=(Button)findViewById(R.id.customer_data_1_next);
		btnext.setOnClickListener(listener);
		etBranch = (EditText) findViewById(R.id.branch);
		etBranch.setText(PdfInfo.branch);
		//etBranch.setText("br");
		etSaleperson = (EditText) findViewById(R.id.saleperson);
		etSaleperson.setText(PdfInfo.name);
		//etSaleperson.setText("sal");
		etCustomer = (EditText) findViewById(R.id.customer);
	//	etCustomer.setText("");
		etCustomer.addTextChangedListener(textwatcher);
		etContactNo = (EditText) findViewById(R.id.contactno);
	//	etContactNo.setText("");
		etCustomer.addTextChangedListener(textwatcher);
		etAddress = (EditText) findViewById(R.id.address);
	//	etAddress.setText("");
		etAddress.addTextChangedListener(textwatcher);
		etMake = (EditText) findViewById(R.id.make);
	//	etMake.setText("");
		etMake.addTextChangedListener(textwatcher);
		etModel = (EditText) findViewById(R.id.model);
	//	etModel.setText("");
		etModel.addTextChangedListener(textwatcher);
		etYear = (EditText) findViewById(R.id.year);
	//	etYear.setText("");
		etYear.addTextChangedListener(textwatcher);
		etOdometer = (EditText) findViewById(R.id.odometer);
	//	etOdometer.setText("");
		etOdometer.addTextChangedListener(textwatcher);
		etRegistration = (EditText) findViewById(R.id.registration);
		etRegistration.setText(i.getStringExtra("CarNoPlatefinal"));
		etDate = (EditText) findViewById(R.id.date);
		 String sysdate = android.text.format.DateFormat.format(
					"dd/MM/yyyy", new java.util.Date()).toString();
		etDate.setText(sysdate);
		etTime = (EditText) findViewById(R.id.time);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String test = sdf.format(cal.getTime());
		etTime.setText(""+test);
		edittext=new EditText[]{etBranch, etSaleperson, etCustomer, etContactNo,
				etAddress, etMake, etModel, etYear, etOdometer, etRegistration,
				etDate, etTime};
		
		
		
	}

	private void nextScreen1(){
	
	strBranch=etBranch.getText().toString();
	strSaleperson=etSaleperson.getText().toString();
	strCustomer=etCustomer.getText().toString();
	strContactNo=etContactNo.getText().toString();
	strAddress=etAddress.getText().toString();
	strMake=etMake.getText().toString();
	strModel=etModel.getText().toString();
	strYear=etYear.getText().toString();
	strOdomstrer=etOdometer.getText().toString();
	strRegistration=etRegistration.getText().toString();
	strDate=etDate.getText().toString();
	strTime=etTime.getText().toString();
	
	
	if (strBranch.length()>0&&strSaleperson.length()>0&&strCustomer.length()>0&&strContactNo.length()>0&&strAddress.length()>0&&strMake.length()>0&&strModel.length()>0&&strYear.length()>0&&strOdomstrer.length()>0&&strRegistration.length()>0&&strDate.length()>0&&strTime.length()>0) {
	
		UploadDataInfo.strBranch=strBranch;
		UploadDataInfo.strSaleperson=strSaleperson;
		UploadDataInfo.strCustomer=strCustomer;
		UploadDataInfo.strContactNo=strContactNo;
		UploadDataInfo.strAddress=strAddress;
		UploadDataInfo.strMake=strMake;
		UploadDataInfo.strModel=strModel;
		UploadDataInfo.strYear=strYear;
		UploadDataInfo.strOdomstrer=strOdomstrer;
		UploadDataInfo.strRegistration=strRegistration;
		UploadDataInfo.strDate=strDate;
		UploadDataInfo.strTime=strTime;
				
		
		Intent i=new Intent(getApplicationContext(), MainActivity.class);
		startActivity(i);
		overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
    }else {
    	
	Toast.makeText(getApplicationContext(), "Enter All Field", Toast.LENGTH_LONG).show();
	
    }	
	
}
	
	  private OnClickListener listener        =   new View.OnClickListener(){
		    @Override
		        public void onClick(View v){
		            switch (v.getId()) {
					case R.id.customer_data_1_next:
						nextScreen1();
				
						break;
		           
					default:
						break;
					}
		           
		        }
		    };
    TextWatcher textwatcher= new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					// TODO Auto-generated method stub
					if (validate(edittext)) {
						btnext.setBackgroundResource(R.drawable.arrow_down_green);
					}else {
						btnext.setBackgroundResource(R.drawable.arrow_down_red);
					}
				}
				
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count,
						int after) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub
					
				}
			};
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	public void chooseDate(){
    	new DatePickerDialog(Firstscreen.this, d, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }
	DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
			monthOfYear=monthOfYear+1;
			dateTime.set(Calendar.YEAR,year);
			
		
		}
	};
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
	}
	private boolean validate(EditText[] fields){
		   boolean result=true;
        for(int i=0; i<fields.length; i++){
            EditText currentField=fields[i];
         
            if(currentField.getText().toString().length()<=0){
                result= false;
            }
        }
        return result;
}
}
