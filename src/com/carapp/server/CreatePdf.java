package com.carapp.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import com.carapp.util.PdfInfo;
import com.carapp.util.UploadDataInfo;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class CreatePdf extends AsyncTask<String, Void, String>{

	 private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
	 private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 15,Font.BOLD);
    Context context;
    ProgressDialog bar; 
    String filename,filepath,childfolder;
    static Image image1;
    
 public CreatePdf(Context context)
 {
	this.context=context; 
 }

  private static void addMetaData(Document document) {
    document.addTitle("CAR APP PDF");
    document.addSubject("Information");
    document.addKeywords("Java, PDF, Android");
   
  }

 

  private static void addContent(Document document) throws DocumentException {
	  

    document.newPage();
    Paragraph subPara=new Paragraph("Customer Data",catFont);
    addEmptyLine(subPara, 2);
    
    
    subPara.add(new Paragraph("          Branch- "+UploadDataInfo.strBranch+
    		"\n          Salesperson- "+UploadDataInfo.strSaleperson+
    		"\n          Customer- "+UploadDataInfo.strCustomer+
    		"\n          Contact number- "+UploadDataInfo.strContactNo+
    		"\n          Address- "+UploadDataInfo.strAddress+
    		"\n          Make- "+UploadDataInfo.strMake+
    		"\n          Model- "+UploadDataInfo.strModel+
    		"\n          Year- "+UploadDataInfo.strYear+
    		"\n          Odometer- "+UploadDataInfo.strOdomstrer+
    		"\n          Registration plate no.- "+UploadDataInfo.strRegistration+
    		"\n          Date- "+UploadDataInfo.strDate+
    		"\n          Time- "+UploadDataInfo.strTime
    		,
    		smallBold));
    		

   
    subPara.setAlignment(Element.ALIGN_CENTER);
    document.add(subPara);

    
    //***************************************************************************************************************************************   
    document.newPage();
    subPara = new Paragraph("Car Assisment",catFont);
    addEmptyLine(subPara, 2);
    
    
    
    
    
    subPara.add(new Paragraph(
    		"          Tyre Condition:- ",subFont));
    subPara.add(new Paragraph("            Left Front- "+UploadDataInfo.tyer_condition_lf+
    		"\n            Left Back- "+UploadDataInfo.tyer_condition_lb+
    		"\n            Right Front- "+UploadDataInfo.tyer_condition_rf+
    		"\n            Right Back- "+UploadDataInfo.tyer_condition_rb,smallBold));
    addEmptyLine(subPara, 1);
    subPara.add(new Paragraph(
    		"          Tyre size:- ",subFont));
    subPara.add(new Paragraph("            Left Front- "+UploadDataInfo.tyre_size_lf+
    		"\n            Left Back- "+UploadDataInfo.tyre_size_lb+
            "\n            Right Front- "+UploadDataInfo.tyre_size_rf+
    		"\n            Right Back- "+UploadDataInfo.tyre_size_rb,smallBold));
    addEmptyLine(subPara, 1);
    subPara.add(new Paragraph(
    		"          Tyre depth:- ",subFont));
    subPara.add(new Paragraph("            Left Front-  "+UploadDataInfo.tyre_depth_lfx+UploadDataInfo.tyre_depth_lfy+
    		"\n            Left Back-  "+UploadDataInfo.tyre_depth_lbx+UploadDataInfo.tyre_depth_lby+
            "\n            Right Front-  "+UploadDataInfo.tyre_depth_rfx+UploadDataInfo.tyre_depth_rfy+
    		"\n            Right Back-  "+UploadDataInfo.tyre_depth_rbx+UploadDataInfo.tyre_depth_rby,smallBold));
    addEmptyLine(subPara, 1);
    subPara.add(new Paragraph(
    		"          Brake Pads:- ",subFont));
    subPara.add(new Paragraph("            Left Front- "+UploadDataInfo.brake_pad_lf+
    		"\n            Left Back- "+UploadDataInfo.brake_pad_lb+
    		"\n            Right Front- "+UploadDataInfo.brake_pad_rf+
    		"\n            Right Back- "+UploadDataInfo.brake_pad_rb,smallBold));
    addEmptyLine(subPara, 1);
    subPara.add(new Paragraph(
    		"          Brake Disks:- ",subFont));
    subPara.add(new Paragraph("            Left Front- "+UploadDataInfo.brake_disk_lf+
    		"\n            Left Back- "+UploadDataInfo.brake_disk_lb+
    		"\n            Right Front- "+UploadDataInfo.brake_disk_rf+
    		"\n            Right Back- "+UploadDataInfo.brake_disk_rb,smallBold));
    addEmptyLine(subPara, 1);
    subPara.add(new Paragraph(
    		"          Shockers:- ",subFont));
    subPara.add(new Paragraph("            Left Front- "+UploadDataInfo.shocker_lf+
    		"\n            Left Back- "+UploadDataInfo.shocker_lb+
    		"\n            Right Front- "+UploadDataInfo.shocker_rf+
    		"\n            Right Back- "+UploadDataInfo.shocker_rb,smallBold));
    addEmptyLine(subPara, 1);
    subPara.add(new Paragraph(
    		"          Wheels:- ",subFont));
    subPara.add(new Paragraph("            Left Front- "+UploadDataInfo.wheel_lf+
    		"\n            Left Back- "+UploadDataInfo.wheel_lb+
    		"\n            Right Front- "+UploadDataInfo.wheel_rf+
    		"\n            Right Back- "+UploadDataInfo.wheel_rb,smallBold));
    addEmptyLine(subPara, 1);
    subPara.add(new Paragraph(
    		"          Physical Damage (Tyers):- ",subFont));
    subPara.add(new Paragraph("            Left Front- "+UploadDataInfo.physical_damage_lf+
    		"\n            Left Back- "+UploadDataInfo.physical_damage_lf+
    		"\n            Right Front- "+UploadDataInfo.physical_damage_rf+
    		"\n            Right Back- "+UploadDataInfo.physical_damage_rb,smallBold));
    addEmptyLine(subPara, 1);
    
    
    
    
    
    
   
    subPara.add(new Paragraph("            Immobilizer:- "+UploadDataInfo.immoblizer_f+
    		"\n            Battery- "+UploadDataInfo.battery_f+
    		"\n            Physical Damage:- "+UploadDataInfo.physical_damage_f,smallBold));
    addEmptyLine(subPara, 1);
     subPara.add(new Paragraph("            Spare Wheel:- "+UploadDataInfo.spare_wheel_b+
    		"\n             Lock Nut:- "+UploadDataInfo.lock_nut_b+
    		"\n            Physical Damage:- "+UploadDataInfo.physical_damage_b,smallBold));
    addEmptyLine(subPara, 1);
    	
  
     
    subPara.setAlignment(Element.ALIGN_CENTER);
    document.add(subPara);
 
    
    //******************************************************************************************************************************************  
    document.newPage();
    subPara = new Paragraph("Job Data", catFont);
    addEmptyLine(subPara, 3);
    
    
    
    subPara.add(new Paragraph("Customer reason for visit:",catFont));
    addEmptyLine(subPara, 1);
   for (int i = 0; i < UploadDataInfo.datacust_reson_for_visit.size(); i++) {
	   subPara.add(new Paragraph("\n"+UploadDataInfo.datacust_reson_for_visit.get(i),smallBold));
	  //  addEmptyLine(subPara, 1);
    } 
   subPara.add(new Paragraph("Dealer Recommendations:",catFont));
   addEmptyLine(subPara, 1);
   
   subPara.add(new Paragraph("Dealer Quotation R"+UploadDataInfo.Quotation1+".00",smallBold)); 
   
  for (int i = 0; i < UploadDataInfo.datadealer_recomendation.size(); i++) {
	   subPara.add(new Paragraph("\n"+UploadDataInfo.datadealer_recomendation.get(i),smallBold));
	  //  addEmptyLine(subPara, 1);
   } 
  subPara.add(new Paragraph("Customer Approved Work:",catFont));
  addEmptyLine(subPara, 1);
  
  subPara.add(new Paragraph("Customer Approved Quotation R"+UploadDataInfo.Quotation2+".00",smallBold)); 
  
 for (int i = 0; i < UploadDataInfo.datacust_approved_work.size(); i++) {
	   subPara.add(new Paragraph("\n"+UploadDataInfo.datacust_approved_work.get(i),smallBold));
	  //  addEmptyLine(subPara, 1);
  } 
     
    subPara.add(new Paragraph("Radio Data "+UploadDataInfo.radiodata,smallBold)); 
   
   
   
  
    subPara.setAlignment(Element.ALIGN_CENTER);
    document.add(subPara);

    
    
    //***********************************************************************************************************************************    
  
    
    
    
    
    document.newPage();
   subPara = new Paragraph("Signatures", catFont);  
   addEmptyLine(subPara, 2);
    subPara.add(new Paragraph("Customer Signature",smallBold));
    addEmptyLine(subPara, 1);
    try {
	    image1=Image.getInstance( PdfInfo.path+"PhotoCustSIG.jpeg");
	    image1.scaleAbsolute(400, 400);
    	image1.setAlignment(Element.ALIGN_CENTER);
    	subPara.add(image1);
    } catch (MalformedURLException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    } catch (IOException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    }	
    addEmptyLine(subPara, 1);
    subPara.add(new Paragraph("SalePerson Signature",catFont));
    addEmptyLine(subPara, 1);
    try {
	    image1=Image.getInstance(PdfInfo.path+"PhotoSaleSIG.jpeg");
	    image1.scaleAbsolute(400, 400);
    	image1.setAlignment(Element.ALIGN_CENTER);
    	subPara.add(image1);
    } catch (MalformedURLException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    } catch (IOException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    }	
  
 //*******************************************************************************************************************************
    
    File dir=new File(PdfInfo.path);
    File []  dirname= dir.listFiles();
   
   // ArrayList<String> filesname = new ArrayList<String>();
  
	for (int i = 0; i < dirname.length; i++) {
		if (dirname[i].getAbsolutePath().endsWith(".jpg")) {
			
		//	filesname.add(dirname[i].getAbsolutePath());
			Log.d("filepath", dirname[i].getAbsolutePath());
			 try {
			    	addEmptyLine(subPara, 1);
			        subPara.add(new Paragraph(""+dirname[i].getName(),catFont));
			        addEmptyLine(subPara, 1);
				    image1=Image.getInstance(dirname[i].getAbsolutePath());
			    	 image1.scaleAbsolute(400, 400);
			    	image1.setAlignment(Element.ALIGN_CENTER);
			    	subPara.add(image1);
			    } catch (MalformedURLException e) {
			    	// TODO Auto-generated catch block
			    	e.printStackTrace();
			    } catch (IOException e) {
			    	// TODO Auto-generated catch block
			    	e.printStackTrace();
			    }
		}
	
	}
    
    
   /*
    try {
    	addEmptyLine(subPara, 1);
        subPara.add(new Paragraph("Image Left Front",catFont));
        addEmptyLine(subPara, 1);
	    image1=Image.getInstance(PdfInfo.path+"takePicture_LF.jpg");
    	 image1.scaleAbsolute(300, 300);
    	image1.setAlignment(Element.ALIGN_CENTER);
    	subPara.add(image1);
    } catch (MalformedURLException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    } catch (IOException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    }
    try {
    	addEmptyLine(subPara, 1);
        subPara.add(new Paragraph("Image Left Back",catFont));
        addEmptyLine(subPara, 1);
	    image1=Image.getInstance(PdfInfo.path+"takePicture_LB.jpg");
	    image1.scaleAbsolute(300, 300);
	    //image1.scalePercent(50f);
    	image1.setAlignment(Element.ALIGN_CENTER);
    	subPara.add(image1);
    } catch (MalformedURLException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    } catch (IOException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    }
    try {
    	addEmptyLine(subPara, 1);
        subPara.add(new Paragraph("Image Right Front",catFont));
        addEmptyLine(subPara, 1);
	    image1=Image.getInstance(PdfInfo.path+"takePicture_RF.jpg");
    	 image1.scaleAbsolute(300, 300);
    	image1.setAlignment(Element.ALIGN_CENTER);
    	subPara.add(image1);
    } catch (MalformedURLException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    } catch (IOException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    }
    try {
    	addEmptyLine(subPara, 1);
        subPara.add(new Paragraph("Image Right Back",catFont));
        addEmptyLine(subPara, 1);
	    image1=Image.getInstance(PdfInfo.path+"takePicture_RB.jpg");
	    image1.scaleAbsolute(300, 300);
	    //image1.scalePercent(50f);
    	image1.setAlignment(Element.ALIGN_CENTER);
    	subPara.add(image1);
    } catch (MalformedURLException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    } catch (IOException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    }
    try {
    	addEmptyLine(subPara, 1);
        subPara.add(new Paragraph("Image Front",catFont));
        addEmptyLine(subPara, 1);
	    image1=Image.getInstance(PdfInfo.path+"takePicture_F.jpg");
	    image1.scaleAbsolute(300, 300);
    	image1.setAlignment(Element.ALIGN_CENTER);
    	subPara.add(image1);
    } catch (MalformedURLException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    } catch (IOException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    }
    try {
    	addEmptyLine(subPara, 1);
        subPara.add(new Paragraph("Image Back",catFont));
        addEmptyLine(subPara, 1);
	    image1=Image.getInstance(PdfInfo.path+"takePicture_B.jpg");
	    image1.scaleAbsolute(300, 300);
    	// image1.scaleAbsolute(300, 300);
    	image1.setAlignment(Element.ALIGN_CENTER);
    	subPara.add(image1);
    } catch (MalformedURLException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    } catch (IOException e) {
    	// TODO Auto-generated catch block
    	e.printStackTrace();
    }
*/    
    document.add(subPara);
    
    //**************************************************************************************************************************************  
  }

  
 

  private static void addEmptyLine(Paragraph paragraph, int number) {
    for (int i = 0; i < number; i++) {
      paragraph.add(new Paragraph(" "));
    }
  }

  @Override
  protected void onPreExecute() {
  	// TODO Auto-generated method stub
  	super.onPreExecute();
  	bar = new ProgressDialog(context);
      bar.setMessage("Creating pdf...\n Please wait...");
      bar.setCancelable(false);
      
      bar.show();
  }
  
@Override
protected String doInBackground(String... params) {
	// TODO Auto-generated method stub
	//childfolder=params[0];
	 try {
	    	
	      Document document = new Document();
	      filepath =PdfInfo.path;
	      File fp=new File(filepath);
	      if (!fp.exists()) {
			fp.mkdirs();
		}
	       filename=filepath+UploadDataInfo.strRegistration+"_"+android.text.format.DateFormat.format(
					"ddMMyyyy", new java.util.Date()).toString()+"_IN.pdf";
	       
	      PdfWriter.getInstance(document,new FileOutputStream(filename));
	      document.open();
	      addMetaData(document);
	     
	      addContent(document);
	      document.close();
	   //   copyDirectory(new File(PdfInfo.path), new File(filepath));
	    } catch (Exception e) {
	      e.printStackTrace();
	      Log.i("pdf", ""+e);
	    }
	return null;
}

@Override
protected void onPostExecute(String result) {
	// TODO Auto-generated method stub
	super.onPostExecute(result);
	bar.dismiss();
//	Toast.makeText(context, "Pdf is Created in SD Card ", Toast.LENGTH_LONG).show(); 

    AlertDialog.Builder alertDialogBuilder1 = new AlertDialog.Builder(context);
     alertDialogBuilder1
    .setTitle("Upload Data to server ?? ")
    .setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {

                        	//new UploadFile(context, filepath).execute(childfolder);
                        	new UpdateDataBase(context).execute("");

                        }

                    })

            .setNegativeButton("NO",

                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {
                       	
                        
                            dialog.cancel();
                            

                        }

                    });
AlertDialog alertD = alertDialogBuilder1.create();

alertD.show();
	
	
}

public void copyDirectory(File sourceLocation , File targetLocation)
throws IOException {

    if (sourceLocation.isDirectory()) {
        if (!targetLocation.exists() && !targetLocation.mkdirs()) {
            throw new IOException("Cannot create dir " + targetLocation.getAbsolutePath());
        }

        String[] children = sourceLocation.list();
        for (int i=0; i<children.length; i++) {
            copyDirectory(new File(sourceLocation, children[i]),
                    new File(targetLocation, children[i]));
        }
    } else {

        // make sure the directory we plan to store the recording in exists
        File directory = targetLocation.getParentFile();
        if (directory != null && !directory.exists() && !directory.mkdirs()) {
            throw new IOException("Cannot create dir " + directory.getAbsolutePath());
        }

        InputStream in = new FileInputStream(sourceLocation);
        OutputStream out = new FileOutputStream(targetLocation);

        // Copy the bits from instream to outstream
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }
}



} 