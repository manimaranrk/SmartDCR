package com.example.sridhar.smartdcr.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sridhar.smartdcr.Adapter.InspectionArrayAdapter;
import com.example.sridhar.smartdcr.Adapter.ListViewAdapter;
import com.example.sridhar.smartdcr.DBHandler.SmartDCRDBHelper;
import com.example.sridhar.smartdcr.DTO.InspectionDataDto;
import com.example.sridhar.smartdcr.DTO.UniqueIdDto;

import com.example.sridhar.smartdcr.Util.CustomProgressDialog;
import com.example.sridhar.smartdcr.Util.NetworkConnection;
import com.example.sridhar.smartdcr.Util.Unique;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.example.sridhar.smartdcr.R;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;


public class MainMenuActivity extends BaseActivity{

    ListView inspectionList;

    Connection conn = null;
    String connString = "jdbc:jtds:sqlserver://182.18.165.141:1433/MobileApp;user=Hetro;password=Genous@1;";
    String username = "Hetro";
    String password = "Genous@1";

    SmartDCRDBHelper smartDCRDBHelper;
    InspectionDataDto inspectionDataDto;
    ListViewAdapter listViewAdapter;
    InspectionArrayAdapter inspectionArrayAdapter;
    UniqueIdDto uniqueIdDto;
    String uniqId,drawing,status;

    ProgressDialog dialog;

    private Handler handler;

    byte [] imageByte = null;

    private ArrayList<HashMap<String, String>> list;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);


            networkConnection = new NetworkConnection(this);
            uniqueIdDto =new UniqueIdDto();
            progressBar = new CustomProgressDialog(this);
            smartDCRDBHelper= SmartDCRDBHelper.getInstance(this);
            if (progressBar != null) {
                progressBar.dismiss();
            }

            inspectionList=(ListView)findViewById(R.id.inspectionlist);

            inspectionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    TextView uniqIdTextview = (TextView) view.findViewById(R.id.uniqid);
                    String uniqId = uniqIdTextview.getText().toString();
                    //      Toast.makeText(MainMenuActivity.this, uniqId, Toast.LENGTH_SHORT).show();
                    Unique.getInstance().setUniqId(uniqId);

                    //     pdfRender(uniqId);

                    startActivity(new Intent(MainMenuActivity.this, InspectionFindingsActivity.class));

                }
            });


            inspectionList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    TextView uniqIdTextview = (TextView) view.findViewById(R.id.uniqid);
                    String uniqId = uniqIdTextview.getText().toString();
                    //      Toast.makeText(MainMenuActivity.this, uniqId, Toast.LENGTH_SHORT).show();
                    Unique.getInstance().setUniqId(uniqId);

                    startActivity(new Intent(MainMenuActivity.this, DisplaySiteDetailActivity.class));



                    return true;
                }
            });

            inspectionDataDto=new InspectionDataDto();
            if (networkConnection.isNetworkAvailable()) {

                new DownloadSiteData().execute();
                new DownloadSiteInspection().execute();
            }
            else{
                Toast.makeText(MainMenuActivity.this,
                        "Network is not Available. Please Check Your Internet Connection ",
                        Toast.LENGTH_SHORT).show();
            }



      //  getSiteData();
   //     getSiteInspection();

    }

    @Override
    public void onBackPressed()
    {
        finish();
    }

    public void loadList(){

                    inspectionArrayAdapter = new InspectionArrayAdapter(MainMenuActivity.this,R.layout.listcolumn);

                    List<InspectionDataDto> inspectionDataDtoArrayList=smartDCRDBHelper.showList();

                    for (int i = 0; i < inspectionDataDtoArrayList.size(); i++) {
                        inspectionDataDto = inspectionDataDtoArrayList.get(i);

                        inspectionArrayAdapter.add(inspectionDataDto);
                        inspectionList.setAdapter(inspectionArrayAdapter);
                        inspectionArrayAdapter.notifyDataSetChanged();

                    }

    }

//    public void pdfRender(String uniqId){
//
//        byte[] byteArrayPdf=smartDCRDBHelper.getPdfArray(uniqId);
//        Document doc = new Document();
//        //----------------
//
//
//    try{
//
////        File mFolder = new File("/sdcard/SmartDCRPDF/"+ uniqId+".pdf");
////
////        File filepath = new File(mFolder.getAbsolutePath() + uniqId+".pdf");
////
////        if (!mFolder.exists()) {
////            mFolder.mkdir();
////        }
////        if (!filepath.exists()) {
////            filepath.createNewFile();
////        }
//
//
//
//
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SmartDCRPDF";
//
//        File dir = new File(path);
//        if(!dir.exists())
//            dir.mkdirs();
//
//        Log.d("PDFCreator", "PDF Path: " + path);
//
//        File file = new File(dir, "aaa.pdf");
//        FileOutputStream fOut = new FileOutputStream(file);
//
//        PdfWriter.getInstance(doc, fOut);
//
//        //open the document
//        doc.open();
//
//
//
//      //  String filepath = "/sdcard/SmartDCRPDF/"+uniqId+".pdf";
//        OutputStream pdffos = new FileOutputStream(file);
//        pdffos.write(byteArrayPdf);
//        //pdffos.flush();
//        pdffos.close();
//        Toast.makeText(getApplicationContext(), ""+byteArrayPdf, Toast.LENGTH_LONG).show();
//
//    } catch (DocumentException de) {
//        Log.e("PDFCreator", "DocumentException:" + de);
//    }
//    catch (FileNotFoundException f){
//        Log.e("file error--",f.toString());
//    }
//    catch (IOException i){
//        Log.e("io error--",i.toString());
//    }
//
//
//    }

    public void getSiteInspection(){

        if (networkConnection.isNetworkAvailable()) {

        String iD=null,uniqId=null,fileNumber=null,section=null,parameter=null,min=null,max=null,
                actual=null,measured=null,violation=null,violationPersentage=null,result=null,
                severityStatus=null,submissionType=null;




        Log.i("Android", " SQL Server Connect Example.");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            String driver = "net.sourceforge.jtds.jdbc.Driver";
            Log.w("Con driver===","open"+driver);
            Class.forName(driver).newInstance();
//test = com.microsoft.sqlserver.jdbc.SQLServerDriver.class;
            Log.w("Connection===","class");
            conn = DriverManager.getConnection(connString,username,password);
            Log.w("Connection===","open"+conn);
            Statement stmt = conn.createStatement();
            ResultSet reset = stmt.executeQuery("select * from SiteInspection");



            if(reset!=null){

                Log.w("reset:------------",reset+"");
            }

//Print the data to the console
            while(reset.next()){

                if(reset.getString("ID")!=null) {
                        Log.e("=== ID === ", reset.getString("ID"));

                    iD=reset.getString("ID");


                }

                if(reset.getString("UniqueId")!=null) {
                        Log.e("=== UniqueId === ", reset.getString("UniqueId"));
                    uniqId=reset.getString("UniqueId");

                }

                if(reset.getString("File_Number")!=null) {
                        Log.e("=== File_Number === ", reset.getString("File_Number"));
                    fileNumber=reset.getString("File_Number");


                }

                if(reset.getString("Section")!=null) {
                        Log.e("=== Section === ", reset.getString("Section"));
                    section=reset.getString("Section");

                }

                if(reset.getString("Parameter")!=null) {
                            Log.e("=== Parameter === ", reset.getString("Parameter"));
                    parameter=reset.getString("Parameter");

                }

                if(reset.getString("Min")!=null) {
                            Log.e("=== Min === ", reset.getString("Min"));
                    min=reset.getString("Min");

                }

                if(reset.getString("Max")!=null) {
                            Log.e("=== Max === ", reset.getString("Max"));
                    max=reset.getString("Max");

                }

                if(reset.getString("Actual")!=null) {
                            Log.e("=== Actual === ", reset.getString("Actual"));
                    actual=reset.getString("Actual");

                }

                if(reset.getString("Measured")!=null) {
                            Log.e("=== Measured === ", reset.getString("Measured"));
                    measured=reset.getString("Measured");

                }

                if(reset.getString("Violation")!=null) {
                            Log.e("=== Violation === ", reset.getString("Violation"));
                    violation=reset.getString("Violation");

                }

                if(reset.getString("Violationpercentage")!=null) {
                            Log.e("=== percentage === ", reset.getString("Violationpercentage"));
                    violationPersentage=reset.getString("Violationpercentage");

                }

                if(reset.getString("Result")!=null) {
                            Log.e("=== Result === ", reset.getString("Result"));
                    result=reset.getString("Result");

                }


                if(reset.getString("SeverityStatus")!=null) {
                            Log.e("=== SeverityStatus === ", reset.getString("SeverityStatus"));
                    severityStatus=reset.getString("SeverityStatus");

                }


                if(reset.getString("Submissiontype")!=null) {
                            Log.e("=== Submissiontype === ", reset.getString("Submissiontype"));
                    submissionType=reset.getString("Submissiontype");

                }

                if(reset.getBytes("Image")!=null) {
                    Log.e("=== Image++++", reset.getBytes("Image")+"");
                  //  imageByte = reset.getString("Image");
                    imageByte = reset.getBytes("Image");

                    //------------------------------

//                    handler.post(new Runnable() {
//                                     @Override
//                                     public void run() {
//
//                    ImageView image = new ImageView(MainMenuActivity.this);
////
//                    Bitmap bmp = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
//                    // ImageView image = (ImageView) findViewById(R.id.imageView1);
//                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);
//                    image.setLayoutParams(layoutParams);
//
//                    image.setImageBitmap(bmp);
//
//                    //  image.setImageResource(R.drawable.YOUR_IMAGE_ID);
//
//
//                    AlertDialog.Builder builder =
//                            new AlertDialog.Builder(MainMenuActivity.this).
//                                    setMessage("Cuptured image").
//                                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            dialog.dismiss();
//                                        }
//                                    }).
//                                    setView(image);
//                    builder.create().show();
//                                     }
//                    });

                }

                int count = SmartDCRDBHelper.getInstance(MainMenuActivity.this).parameterisPresent(uniqId,parameter);

                Log.e("counttt--",count+"");

                if(count==0) {

                    SmartDCRDBHelper.getInstance(MainMenuActivity.this).insertSiteInspection(iD, uniqId, fileNumber, section, parameter, min, max,
                            actual, measured, violation, violationPersentage, result,
                            severityStatus, submissionType, imageByte);
                }
                else{

                    SmartDCRDBHelper.getInstance(MainMenuActivity.this).updateSiteInspection(iD, uniqId, fileNumber, section, parameter, min, max,
                            actual, measured, violation, violationPersentage, result,
                            severityStatus, submissionType, imageByte);

                }

            }
            conn.close();

        }
        catch (ClassNotFoundException ex) {Log.e(" class not found---", ex.toString());}
        catch (IllegalAccessException ex) {Log.e("illagal---", ex.toString());}
        catch (InstantiationException ex) {Log.e("instatiation---", ex.toString());}
        catch (SQLException ex)           {Log.e("sql Error ---", ex.toString());}

        }
        else{
            dialog.dismiss();
            Looper.prepare();
            Toast.makeText(MainMenuActivity.this,
                    "Network is not Available. Please Check Your Internet Connection ",
                    Toast.LENGTH_SHORT).show();
            Looper.loop();


        }

    }



    public void getSiteData(){

        if (networkConnection.isNetworkAvailable()) {


        Log.i("Android", " SQL Server Connect Example.");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            String driver = "net.sourceforge.jtds.jdbc.Driver";
            Log.w("Con driver===","open"+driver);
            Class.forName(driver).newInstance();
//test = com.microsoft.sqlserver.jdbc.SQLServerDriver.class;
            Log.w("Connection===","class");
            conn = DriverManager.getConnection(connString, username, password);
            Log.w("Connection===","open"+conn);
            Statement stmt = conn.createStatement();
            ResultSet reset = stmt.executeQuery("select Uniqid, Status from SiteData");

            if(reset!=null){

                Log.w("reset:------------",reset+"");
            }

            while(reset.next()){

                if(reset.getString("Uniqid")!=null) {
                    Log.e("Uniqid::: ", reset.getString("Uniqid"));
                    uniqId=reset.getString("Uniqid");
                }
                else{
                    uniqId="null";
                }

//                if(reset.getString("drawing")!=null) {
//                    Log.e("drawing::: ", reset.getString("drawing"));
//                    drawing=reset.getString("drawing");
//                }
//                else{
//                    drawing="null";
//                }

                if(reset.getString("Status")!=null) {
                    Log.e("Status::: ", reset.getString("Status"));
                    status=reset.getString("Status");
                }
                else{
                    status="null";
                }

             //   smartDCRDBHelper.insertSiteData(uniqId, drawing, status);
                smartDCRDBHelper.insertSiteData(uniqId, status);

//                inspectionArrayAdapter = new InspectionArrayAdapter(this,R.layout.listcolumn);
//
//                List<InspectionDataDto> inspectionDataDtoArrayList=smartDCRDBHelper.showList();
//
//                for (int i = 0; i < inspectionDataDtoArrayList.size(); i++) {
//                    inspectionDataDto = inspectionDataDtoArrayList.get(i);
//
//                    inspectionArrayAdapter.add(inspectionDataDto);
//                    inspectionList.setAdapter(inspectionArrayAdapter);
//                    inspectionArrayAdapter.notifyDataSetChanged();
//
//                }
            }
            conn.close();
        }
        catch (ClassNotFoundException ex) {Log.e(" class not found---", ex.toString());}
        catch (IllegalAccessException ex) {Log.e("illagal---", ex.toString());}
        catch (InstantiationException ex) {Log.e("instatiation---", ex.toString());}
        catch (SQLException ex)           {Log.e("sql Error ---", ex.toString());}


        }
        else{
            dialog.dismiss();
            Looper.prepare();
            Toast.makeText(MainMenuActivity.this,
                    "Network is not Available. Please Check Your Internet Connection ",
                    Toast.LENGTH_SHORT).show();

            Looper.loop();
        }

    }

    class DownloadSiteInspection extends AsyncTask<Void, Void, Void> {

        //    TextView tv;

      //  myAsyncTask()    {
       //     tv = (TextView)findViewById(R.id.tv);
    //    }

        // Executed on the UI thread before the
        // time taking task begins
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
    //        tv.setText("Ready to start async task....");


            dialog=new ProgressDialog(MainMenuActivity.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setTitle("Site Inspection Downloading...");
            dialog.setCancelable(false);
            dialog.show();
        }

        // Executed on a special thread and all your
        // time taking tasks should be inside this method
        @Override
        protected Void doInBackground(Void... arg0) {

    //        getSiteData();
            getSiteInspection();
   //         tv.setText("Running task....");
      //      myData = myParser.getDataFromWeb();
            return null;
        }

        // Executed on the UI thread after the
        // time taking process is completed
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            dialog.dismiss();
            loadList();
      //      tv.setText("Completed the task, and the result is : " + myData);
        }
    }


    class DownloadSiteData extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog;
        //    TextView tv;

        //  myAsyncTask()    {
        //     tv = (TextView)findViewById(R.id.tv);
        //    }

        // Executed on the UI thread before the
        // time taking task begins
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //        tv.setText("Ready to start async task....");


            dialog=new ProgressDialog(MainMenuActivity.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setTitle("Site Data Downloading...");
            dialog.setMax(100);
            dialog.show();
        }

        // Executed on a special thread and all your
        // time taking tasks should be inside this method
        @Override
        protected Void doInBackground(Void... arg0) {

            getSiteData();
       //     getSiteInspection();
          //  getSiteInspection();
            //         tv.setText("Running task....");
            //      myData = myParser.getDataFromWeb();
            return null;
        }

        // Executed on the UI thread after the
        // time taking process is completed
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            dialog.dismiss();
            //      tv.setText("Completed the task, and the result is : " + myData);
        }
    }

}
