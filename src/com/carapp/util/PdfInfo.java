package com.carapp.util;

import java.io.File;
import java.io.IOException;

import android.graphics.Bitmap;
import android.os.Environment;

public class PdfInfo {

	public static String title;
	public static String firstimagepath;
	public static Bitmap image1;
	public static String YesofNo;
	public static String comment;
	
	public static String secondPart1;
	public static String secondPart2;
	public static String secondIcon;
	public static String secondImage;
	
	public static String thirdPart3;
	public static String thiedSigneture;
	
	public static String path= Environment.getExternalStorageDirectory()+"/CarTemp/";
	
	
	public static String name;
	public static String client;
	public static String branch;
	public static String carnoplate;

	
	public static String dateaddress="http://techsoftlabs.com/client/branch/date.php";
	public static String newjobcard="http://techsoftlabs.com/taskdev/carapp/mobile_webservices/mobile_webservice.php";
	
	
	
	
	
	public static void deleteFiles() {

	    File file = new File(PdfInfo.path);

	    if (file.exists()) {
	        String deleteCmd = "rm -r " + PdfInfo.path;
	        Runtime runtime = Runtime.getRuntime();
	        try {
	            runtime.exec(deleteCmd);
	        } catch (IOException e) { }
	    }
	}
	
}
