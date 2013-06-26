package com.carapp.activity;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.carapp.server.AsyncWebServiceProcessingTask;
import com.carapp.util.PdfInfo;
import com.example.carappnew.R;

public class NewJob extends Activity{

private	Context context;
private    EditText carnoplate;
private Button search;
private String CarNoPlatefinal;
private ListView listView;
private static String t="NewJob";
private String selectedListItem="";
private static final int getRegNoList=0;
private static final int checkRegNo=1;
ArrayList<String> list;
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
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				selectedListItem=(String) listView.getItemAtPosition(arg2);
				
			}
		});
		 MultipartEntity entity=new MultipartEntity();
		 try {
			entity.addPart("action", new StringBody("getlist_reg_plate_no"));
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new AsyncWebServiceProcessingTask(context,getRegNoList, entity, "Getting List of registration Plate�").execute(PdfInfo.newjobcard,"registration Plate");

		
	
		
	}
	private void shortList()
	{
		int textlength = carnoplate.getText().length();
		text_sort.clear();
		
			for (int i = 0; i < list.size(); i++)
			{
				if (textlength <= list.get(i).length())
				{
					if (carnoplate.getText().toString().
							equalsIgnoreCase((String)list.get(i).subSequence(0, textlength)))
					{
						text_sort.add(list.get(i));
						
					}
				}
			}
			ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(context,R.layout.simplerow, text_sort);
		    listView.setAdapter(listAdapter);
		    listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
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
	MultipartEntity entity=new MultipartEntity();
	 try {
		entity.addPart("action", new StringBody("search_reg_plate_no"));
		entity.addPart("reg_plate_no", new StringBody(CarNoPlatefinal));
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	new AsyncWebServiceProcessingTask(context, checkRegNo, entity, "Checking if entry already exists?").execute(PdfInfo.newjobcard);

}else {
blink(carnoplate);
}
}

public void checkOut(View v) {

	Log.e(t, selectedListItem);
	if (selectedListItem.length()>1) {
		MultipartEntity entity=new MultipartEntity();
		 try {
			entity.addPart("action", new StringBody("search_reg_plate_no"));
			entity.addPart("reg_plate_no", new StringBody(selectedListItem));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new AsyncWebServiceProcessingTask(context, checkRegNo, entity, "Checking..").execute(PdfInfo.newjobcard);

	} else {
Toast.makeText(context, "No item selected", Toast.LENGTH_SHORT).show();
	}
	
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
			.setNegativeButton("This is the car�s first time at this shop.", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int id) {
						
						PdfInfo.deleteFiles();
							  Intent intent = new Intent(context,Firstscreen.class); 
							  intent.putExtra("CarNoPlatefinal", CarNoPlatefinal);
							  intent.putExtra("for",0);
							  startActivity(intent);
							  overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
							  carnoplate.setText("");
							  dialog.cancel(); 
						

					}

				});		
		
		AlertDialog alertD = alertDialogBuilder1.create();

		alertD.show();
}

	  private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
	        @Override
	        public void onReceive(Context context, Intent intent) {
	        	int key=intent.getIntExtra("key",0);
	        	String response=intent.getStringExtra("response");
	        	JSONObject jsonObject = null;
				try {
					jsonObject = new JSONObject(response);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	
	        	Log.e(t,""+key);
	        	Log.e(t,response);
	        	ArrayAdapter<String> listAdapter;
	        	if (key==getRegNoList) {
	        		
					try {
						list=new ArrayList<String>();
						JSONArray jsonArray=jsonObject.optJSONArray("list");
						
						for (int i = 0; i < jsonArray.length(); i++) {
							list.add(jsonArray.optString(i));
						}
	                    listAdapter = new ArrayAdapter<String>(context,R.layout.simplerow, list);
						
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						list=new ArrayList<String>();
						list.add("No Record Found");
						 listAdapter = new ArrayAdapter<String>(context,R.layout.simplerow, list);
						 
					}
					listView.setAdapter(listAdapter);
					listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
				}
	        	if (key==checkRegNo) {
	        		if (jsonObject.optString("error").equals("no record found")) {
						
	        			ShowDialog();
	        			
					} else {
						 Intent intent1 = new Intent(context,Firstscreen.class); 
						 // intent1.putExtra("CarNoPlatefinal", CarNoPlatefinal);
						  intent1.putExtra("response", response);
						  intent1.putExtra("for",1);
						  startActivity(intent1);
						  overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
						  carnoplate.setText("");
					}
	        		 
	        		
	        		
				}
	        	
	        	
			    
	        	
	        	
	        }
	    };    
	    
		@Override
		public void onResume() {
			super.onResume();		
		    Log.e(t, "onResume");
		    registerReceiver(broadcastReceiver, new IntentFilter(AsyncWebServiceProcessingTask.BROADCAST_ACTION));
		}
		
		@Override
		public void onPause() {
			super.onPause();
			Log.e(t, "onPause");
			
			unregisterReceiver(broadcastReceiver);	
		}
		@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			
		}	
	
	
}