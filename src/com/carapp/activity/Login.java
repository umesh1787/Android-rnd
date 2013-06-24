package com.carapp.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;

import com.carapp.util.PdfInfo;
import com.example.carappnew.R;

public class Login extends Activity {

	Context context;
	EditText username;
	Button login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.login);
		context = this;
		username = (EditText) findViewById(R.id.login_e_username);
		login = (Button) findViewById(R.id.login_b_login);
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (username.getText().length() > 0) {
					new Checklogin().execute(username.getText().toString());
				} else {
					blink(username);
				}

			}
		});
	}

	private void blink(EditText et) {

		Animation anim = new AlphaAnimation(0.0f, 1.0f);
		anim.setDuration(5); // You can manage the time of the blink with this
								// parameter
		anim.setStartOffset(20);
		anim.setRepeatMode(Animation.REVERSE);
		anim.setRepeatCount(5);
		et.startAnimation(anim);
	}

	private class Checklogin extends AsyncTask<String, String, String> {
		private static final String t = "Checklogin";
		private static final String LoginCheck = "http://techsoftlabs.com/client/branch/SalesManagers.txt";
		private ProgressDialog mProgressDialog;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(context);
			mProgressDialog.setMessage("Checking please wait...");
			mProgressDialog.show();

		}

		@Override
		protected void onProgressUpdate(String... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String response = null;

			try {
				// Create a URL for the desired page
				URL url = new URL(LoginCheck);

				// Read all the text returned by the server
				BufferedReader in = new BufferedReader(new InputStreamReader(
						url.openStream()));
				String str;
				Log.i(t, params[0]);
				while ((str = in.readLine()) != null) {

					Log.i(t, "line************* " + str);
					String[] value = str.split(",");

					if (value[0].equals(params[0])) {
						PdfInfo.name = value[1];
						Log.i(t, "userfound" + PdfInfo.name);
						response = "sucesses";
						break;
					} else {
						response = "faild";
					}

				}
				in.close();

			} catch (MalformedURLException e) {

				response = "server problem";
				Log.i(t, "mal " + e);
			} catch (IOException e) {
				response = "server problem";
				Log.i(t, "io " + e);
			}

			return response;

		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			mProgressDialog.dismiss();
			Log.i(t, result);
			if (result.equals("sucesses")) {
				ShowDialog("Welcome, " + PdfInfo.name, "sucesses");
			} else if (result.equals("faild")) {
				ShowDialog("Salesperson not found! Please try again", "faild");

			} else if (result.equals("server problem")) {
				ShowDialog("Server not Respond", "server");
			}

		}
	}

	public void ShowDialog(String message, final String result) {

		AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(
				context);

		alertDialogBuilder1

		.setCancelable(false)

		.setMessage(message)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int id) {
						if (result.equals("faild") || result.equals("server")) {
							username.setText("");
							dialog.cancel();
						} else {

							Intent intent = new Intent(context, NewJob.class);
							startActivity(intent);

						}

					}

				});

		AlertDialog alertD = alertDialogBuilder1.create();

		alertD.show();
	}
}
