package com.carapp.activity;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.carapp.server.AsyncWebServiceProcessingTask;
import com.carapp.util.PdfInfo;
import com.example.carappnew.R;

public class NewJob extends Activity{

private	Context context;
private    EditText carnoplate;
private Button search;
private String CarNoPlatefinal;
private ListView listView;
private Handler handler,handler1;
private static String t="NewJob";
String[] text = { "One", "Two", "Three", "Four", "Five", "Six", "Seven",
		"Eight", "Nine", "Ten" };
ArrayList<String> text_sort = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.jobcard);
		context = this;
		carnoplate=(EditText)findViewById(R.id.car_no_plate);
		carnoplate.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				shortList();
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
		});
		listView=(ListView)findViewById(R.id.listView);
		 MultipartEntity entity=new MultipartEntity();
		 try {
			entity.addPart("action", new StringBody("getlist_reg_plate_no"));
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new AsyncWebServiceProcessingTask(context, handler1, entity, "Getting List of registration Plate…").execute(PdfInfo.newjobcard);

		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,R.layout.simplerow, text);
			    listView.setAdapter(listAdapter);
		handler = new Handler() { 
		     @Override public void handleMessage(Message msg) { 
		      String s=(String)msg.obj;
		      try
				{
					JSONObject obj = new JSONObject(s);
					Log.i(t, "status   "+obj);
					if(obj.getString("error").equals("no record found"))
					{
						
						ShowDialog();
						
					}
					
				}catch(JSONException e){}
				
		     
		    }
		  };
		  handler1 = new Handler() { 
			     @Override public void handleMessage(Message msg) { 
			      String s=(String)msg.obj;
			      try
					{
						JSONObject obj = new JSONObject(s);
						Log.i(t, "status   "+obj);
						if(obj.getString("error").equals("no record found"))
						{
							
							ShowDialog();
							
						}
						
					}catch(JSONException e){}
					
			     
			    }
			  };
		
	}
	private void shortList()
	{
		int textlength = carnoplate.getText().length();
		text_sort.clear();
		
			for (int i = 0; i < text.length; i++)
			{
				if (textlength <= text[i].length())
				{
					if (carnoplate.getText().toString().
							equalsIgnoreCase((String) text[i].subSequence(0, textlength)))
					{
						text_sort.add(text[i]);
						
					}
				}
			}
			ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,R.layout.simplerow, text_sort);
		    listView.setAdapter(listAdapter);

		
	}
private void blink(EditText et){
	    
		Animation anim = new AlphaAnimation(0.0f, 1.0f);
		anim.setDuration(5); //You can manage the time of the blink with this parameter
		anim.setStartOffset(20);
		anim.setRepeatMode(Animation.REVERSE);
		anim.setRepeatCount(5);
		et.startAnimation(anim);
	    }
public void check(View v) {
	
	String CarNoPlate=carnoplate.getText().toString();
	// TODO Auto-generated method stub
if(CarNoPlate.length()>0){

	CarNoPlate=CarNoPlate.replaceAll("\\s","");
	CarNoPlate=CarNoPlate.toUpperCase();
	carnoplate.setText(CarNoPlate);
	CarNoPlatefinal=carnoplate.getText().toString();
//new CheckNoPlate().execute(CarNoPlatefinal);
	
	 MultipartEntity entity=new MultipartEntity();
	 try {
		entity.addPart("action", new StringBody("search_reg_plate_no"));
		entity.addPart("reg_plate_no", new StringBody(CarNoPlatefinal));
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	new AsyncWebServiceProcessingTask(context, handler, entity, "Checking if entry already exists…").execute(PdfInfo.newjobcard);

}else {
blink(carnoplate);
}
}

public void checkOut(View v) {
	
}

	public void ShowDialog() {
    
		
		AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(
				context);

		alertDialogBuilder1

		.setCancelable(false)

		.setMessage("Entry "+CarNoPlatefinal +" not found!")
				.setPositiveButton("Retype entry", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int id) {
						

				dialog.cancel();		
				carnoplate.setText("");			 
						

					}

				})
			.setNegativeButton("This is the car’s first time at this shop.", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int id) {
						
						PdfInfo.deleteFiles();
							  Intent intent = new Intent(context,Firstscreen.class); 
							  intent.putExtra("CarNoPlatefinal", CarNoPlatefinal);
							  startActivity(intent);
							  overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
							  carnoplate.setText("");
							  dialog.cancel(); 
						

					}

				});		
		
		AlertDialog alertD = alertDialogBuilder1.create();

		alertD.show();
}
	
}