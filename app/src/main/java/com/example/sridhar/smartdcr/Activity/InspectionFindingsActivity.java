package com.example.sridhar.smartdcr.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sridhar.smartdcr.DBHandler.SmartDCRConstants;
import com.example.sridhar.smartdcr.DBHandler.SmartDCRDBHelper;
import com.example.sridhar.smartdcr.R;
import com.example.sridhar.smartdcr.Util.CustomProgressDialog;
import com.example.sridhar.smartdcr.Util.Inspection;
import com.example.sridhar.smartdcr.Util.NetworkConnection;
import com.example.sridhar.smartdcr.Util.Unique;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.os.Handler;


public class InspectionFindingsActivity extends BaseActivity {

    byte[] imageBytes;

    private final int requestCode = 20;

    Spinner parameterSpinner,severityStatusSpinner;

    EditText mearsedEdittext,providedEdittext;

    Button inspectionSubmit,captureImageButton;

    String imageFilePath;
    String imageFilename;

    private ProgressDialog pDialog;

    private Handler handler;

    AlertDialog.Builder alertDialogBuilder;


    //  EditText userNameEdittext,passwordEdittext;
  //  Button signInButton;

    SmartDCRDBHelper smartDCRDBHelper;
    Connection conn = null;
    String connString = "jdbc:jtds:sqlserver://182.18.165.141:1433/MobileApp;user=Hetro;password=Genous@1;";
    String username = "Hetro";
    String password = "Genous@1";

    private static InspectionFindingsActivity parent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inspectionfindings);

        networkConnection = new NetworkConnection(this);

        parameterSpinner=(Spinner) findViewById(R.id.parameter_spinner);

        severityStatusSpinner=(Spinner) findViewById(R.id.severitystatus_spinner);

        mearsedEdittext=(EditText) findViewById(R.id.measured_edittext);

        providedEdittext=(EditText)findViewById(R.id.provided_edittext);

        inspectionSubmit=(Button) findViewById(R.id.inspection_submit);

        captureImageButton=(Button) findViewById(R.id.capture_image_button);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        progressBar = new CustomProgressDialog(this);

        smartDCRDBHelper= SmartDCRDBHelper.getInstance(this);

        loadSeverityStatus();

        loadParameter();

        providedEdittext.setKeyListener(null);

        handler = new Handler();


        captureImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoCaptureIntent, requestCode);



            }
        });

        parameterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position>0){

                    String parameter=parent.getItemAtPosition(position).toString();
                    String uniqId= Unique.getInstance().getUniqId();

                    Cursor cursor = smartDCRDBHelper.getData(parameter, uniqId);
                    cursor.moveToFirst();
                    int count=cursor.getCount();

//                    Toast.makeText(InspectionFindingsActivity.this,
//                            "parameter="+count,
//                            Toast.LENGTH_SHORT).show();

                    if (cursor.moveToFirst()) {
                        do {

                            String inspectionId=cursor.getString(cursor.getColumnIndex(SmartDCRConstants.KEY_INSPECTION_ID));

                            Log.e("++inspectionId--",inspectionId);
                            Inspection.getInstance().setInspectionId(inspectionId);

                            String provided= cursor.getString(cursor.getColumnIndex(SmartDCRConstants.KEY_INSPECTION_ACTUAL));

                            providedEdittext.setText(provided);

                        } while (cursor.moveToNext());
                    }
                    cursor.close();
                }
                else{

              //      Looper.prepare();
                    Toast.makeText(InspectionFindingsActivity.this,
                            "Please Select the Parameter.",
                            Toast.LENGTH_SHORT).show();
                //    Looper.loop();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

     //   dbConnection();
    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(InspectionFindingsActivity.this, MainMenuActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.requestCode == requestCode && resultCode == RESULT_OK){

            try {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");

                String partFilename = currentDateFormat();
                File outputFile=storeCameraPhotoInSDCard(bitmap, partFilename);

                Log.e("outputFile====",outputFile.toString());

                imageFilePath =outputFile.toString();

                // display the image from SD Card to ImageView Control
                imageFilename = "Site_" + partFilename + ".jpg";
           //     Bitmap mBitmap = getImageFileFromSDCard(imageFilename);


                imageBytes=getBytes(outputFile);

                ImageView image = new ImageView(this);

                Bitmap bmp = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
               // ImageView image = (ImageView) findViewById(R.id.imageView1);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);
                image.setLayoutParams(layoutParams);

                image.setImageBitmap(bmp);

              //  image.setImageResource(R.drawable.YOUR_IMAGE_ID);

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(this).
                                setMessage("Cuptured image").
                                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).
                                setView(image);
                builder.create().show();

           //     ImageBytes = getByteArrayFromImage(outputFile);

         //       Log.e("ImageBytes----",ImageBytes+"");
              //  imageHolder.setImageBitmap(mBitmap);
            }
            catch (Exception e){
                Log.e("imageeeeeeeee",e.toString());
            }
        }
    }

    private byte[] getBytes(File filePath) {

        Bitmap bm = BitmapFactory.decodeFile(filePath.toString());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] imageByte = baos.toByteArray();

        return imageByte;
    }

    private byte[] getByteArrayFromImage(File filePath) throws FileNotFoundException, IOException {

     //   File file = new File(imageFilePath);
     //   System.out.println(file.exists() + "!!");

        FileInputStream fis = new FileInputStream(filePath);
        //create FileInputStream which obtains input bytes from a file in a file system
        //FileInputStream is meant for reading streams of raw bytes such as image data. For reading streams of characters, consider using FileReader.

        //InputStream in = resource.openStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
                //no doubt here is 0
                /*Writes len bytes from the specified byte array starting at offset
                off to this byte array output stream.*/
                System.out.println("read " + readNum + " bytes,");

                Log.e("byteeee",readNum+"");
            }
        } catch (IOException ex) {
            Log.d("error","error");
        }
        byte[] bytes = bos.toByteArray();
        return bytes;
    }

//    private byte[] getByteArrayFromImage(String imageFilePath) throws FileNotFoundException, IOException {
//
//        File file = new File(imageFilePath);
//        System.out.println(file.exists() + "!!");
//
//        FileInputStream fis = new FileInputStream(file);
//        //create FileInputStream which obtains input bytes from a file in a file system
//        //FileInputStream is meant for reading streams of raw bytes such as image data. For reading streams of characters, consider using FileReader.
//
//        //InputStream in = resource.openStream();
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        byte[] buf = new byte[1024];
//        try {
//            for (int readNum; (readNum = fis.read(buf)) != -1;) {
//                bos.write(buf, 0, readNum);
//                //no doubt here is 0
//                /*Writes len bytes from the specified byte array starting at offset
//                off to this byte array output stream.*/
//                System.out.println("read " + readNum + " bytes,");
//            }
//        } catch (IOException ex) {
//            Log.d("error","error");
//        }
//        byte[] bytes = bos.toByteArray();
//        return bytes;
//    }

    private String currentDateFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String  currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
    }

    private File storeCameraPhotoInSDCard(Bitmap bitmap, String currentDate){

        File filepath= Environment.getExternalStorageDirectory();


        File dir = new File(filepath.getAbsolutePath()+"/SmartDCR/SmartDCR Images/");

        dir.mkdirs();

        File outputFile = new File(dir, "Site_" + currentDate + ".jpg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputFile;
    }
//    private Bitmap getImageFileFromSDCard(String filename){
//        Bitmap bitmap = null;
//        File imageFile = new File(Environment.getExternalStorageDirectory()+"/SmartDCR/" + filename);
//        try {
//            FileInputStream fis = new FileInputStream(imageFile);
//            bitmap = BitmapFactory.decodeStream(fis);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return bitmap;
//    }


    public void inspectionSubmit(View view) {
        try {

            String parameter= String.valueOf(parameterSpinner.getSelectedItem());
            String severity=String.valueOf(severityStatusSpinner.getSelectedItem());
            String measured=mearsedEdittext.getText().toString();
            String inspectionId=Inspection.getInstance().getInspectionId();

            String uniqId=Unique.getInstance().getUniqId();


            if(parameterSpinner.getSelectedItemPosition()==0){
                Toast.makeText(InspectionFindingsActivity.this, "Please Select Parameter.", Toast.LENGTH_LONG).show();
            }
            else if(measured.length()==0){
                Toast.makeText(InspectionFindingsActivity.this, "Please Enter Measured.", Toast.LENGTH_LONG).show();
            }
            else if(severityStatusSpinner.getSelectedItemPosition()==0){
                Toast.makeText(InspectionFindingsActivity.this, "Please Select severity.", Toast.LENGTH_LONG).show();
            }
            else {

                if(imageBytes.length>0) {


                    if (networkConnection.isNetworkAvailable()) {


                        new uploadSiteInspection().execute(parameter, severity, measured, inspectionId);

                        //   storeSiteInspection(parameter, severity, measured, inspectionId);

                        //      storeSiteData(uniqId,ImageBytes);

//                smartDCRDBHelper.updateServerSyncInspection(parameter, severity, measured, inspectionId,uniqId);
//                smartDCRDBHelper.insertServerSyncInspectionImage(uniqId, imageFilePath, imageFilename);

                    } else {

                        smartDCRDBHelper.updateInspection(parameter, severity, measured, inspectionId, uniqId);
                        smartDCRDBHelper.insertInspectionImage(uniqId, imageFilePath, imageFilename,parameter);

                    }
                }
                else{
                    Toast.makeText(InspectionFindingsActivity.this, "Please Capture the Image.", Toast.LENGTH_LONG).show();
                }
            }

        } catch (Exception e) {

        }
    }

    class uploadSiteInspection extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(InspectionFindingsActivity.this);
            pDialog.setMessage("Upload Site Details...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {

            String parameter = args[0];
            String severity = args[1];
            String measured = args[2];
            String inspectionId = args[3];

            String uniqId=Unique.getInstance().getUniqId();

            if (networkConnection.isNetworkAvailable()) {
                Log.d("network1===",networkConnection.isNetworkAvailable()+"");

                Log.d("parameter",parameter);
                Log.d("severity",severity);
                Log.d("measured",measured);
                Log.d("inspectionId",inspectionId);

                storeSiteInspection(parameter, severity, measured, inspectionId);

                smartDCRDBHelper.updateServerSyncInspection(parameter, severity, measured, inspectionId,uniqId);
                smartDCRDBHelper.insertServerSyncInspectionImage(uniqId, imageFilePath, imageFilename,parameter);

            }
            else {
                smartDCRDBHelper.updateInspection(parameter, severity, measured, inspectionId,uniqId);
                smartDCRDBHelper.insertInspectionImage(uniqId, imageFilePath, imageFilename,parameter);
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }



    public void storeSiteData(String uniqId,byte[] ImageBytes){



        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            String driver = "net.sourceforge.jtds.jdbc.Driver";
            Log.w("Con driver===", "open" + driver);
            Class.forName(driver).newInstance();
//test = com.microsoft.sqlserver.jdbc.SQLServerDriver.class;
            Log.w("Connection===", "class");
            conn = DriverManager.getConnection(connString, username, password);
            Log.w("Connection===", "open" + conn);
            Statement stmt = conn.createStatement();

         //   Blob blob = Hibernate.createBlob(bytes);
            int count = stmt.executeUpdate("UPDATE SiteData SET drawing= CAST('"+ ImageBytes +"' AS VARBINARY(MAX)) WHERE Uniqid='"+uniqId+"'");



//            int count=stmt.executeUpdate("UPDATE SiteInspection SET Measured='" + measured+
//                    "',SeverityStatus='"+severity+"'WHERE ID='"+inspectionid+"'");

            Log.e("count update---",count+"");

            if(count<0){

               // smartDCRDBHelper.updateInspection(parameter, severity, measured, inspectionid);

            }

            //  int count = stmt.executeQuery("select * from SiteInspection where UniqueId='" + uniqId + "'");

        }
        catch (Exception e){

            Log.i("Exception",e.toString());
        }



    }

    public void storeSiteInspection(String parameter,String severity,String measured, String inspectionid ){



        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            String driver = "net.sourceforge.jtds.jdbc.Driver";

            Class.forName(driver).newInstance();

            conn = DriverManager.getConnection(connString, username, password);

         //   Statement stmt = conn.createStatement();

            String uniqId = Unique.getInstance().getUniqId();

            Log.e("ins idd===", inspectionid);

//------------------------------------------------


//                    ImageView image = new ImageView(parent);
//
//                    Bitmap bmp = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
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
//                            new AlertDialog.Builder(parent).
//                                    setMessage("Cuptured image").
//                                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            dialog.dismiss();
//                                        }
//                                    }).
//                                    setView(image);
//                    builder.create().show();






            //------------------------------------------

            String updatequery="UPDATE SiteInspection SET Measured = ?, SeverityStatus = ? ,Image = ? WHERE ID =? AND Parameter = ?";

            PreparedStatement pstmt = conn.prepareStatement(updatequery);
            pstmt.setString(1, measured);
            pstmt.setString(2, severity);
            pstmt.setBytes(3, imageBytes);
            pstmt.setString(4, inspectionid);
            pstmt.setString(5, parameter);

            int count=pstmt.executeUpdate();


//            int count=stmt.executeUpdate("UPDATE SiteInspection SET Measured='" + measured+
//                    "',SeverityStatus='"+severity+"',Image= "+imageBytes+ " WHERE ID='"+inspectionid+"' AND Parameter='"+parameter+"'");


//            int count=stmt.executeUpdate("UPDATE SiteInspection SET Measured='" + measured+
//                    "',SeverityStatus='"+severity+"',Image= CAST('"+imageBytes+  "' AS VARBINARY(MAX)) WHERE ID='"+inspectionid+"' AND Parameter='"+parameter+"'");


            Log.e("count update---",count+"");

            if(count<0){

                smartDCRDBHelper.updateInspection(parameter, severity, measured, inspectionid);

            }
            else{

                handler.post(new Runnable() {
                    @Override
                    public void run() {

//                        ImageView image = new ImageView(InspectionFindingsActivity.this);
//
//                    Bitmap bmp = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
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
//                            new AlertDialog.Builder(InspectionFindingsActivity.this).
//                                    setMessage("Cuptured image").
//                                    setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            dialog.dismiss();
//                                        }
//                                    }).
//                                    setView(image);
//                    builder.create().show();



                        alertDialogBuilder = new AlertDialog.Builder(InspectionFindingsActivity.this);
                        alertDialogBuilder.setIcon(R.drawable.smartdcrlogo);
                        alertDialogBuilder.setTitle("SmartDCR");
                        alertDialogBuilder.setMessage("Store Succesfully, Do you want add another Findings ");

                        final AlertDialog alertdialog = alertDialogBuilder.show();

                        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                parameterSpinner.setSelection(0);

                                severityStatusSpinner.setSelection(0);
                                providedEdittext.setText("");
                                mearsedEdittext.setText("");
                                imageBytes=null;
                                alertdialog.cancel();




                            }
                        });

                        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                startActivity(new Intent(InspectionFindingsActivity.this, MainMenuActivity.class));
                                finish();

                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();

                    }
                });




            }

            pstmt.close();

          //  int count = stmt.executeQuery("select * from SiteInspection where UniqueId='" + uniqId + "'");

        }
        catch (Exception e){

            Log.i("Exception",e.toString());
        }
    }

    public void loadSeverityStatus(){

        String[] overallstatusList = {"Select","Low","Medium","High"};

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinnertext, overallstatusList);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        severityStatusSpinner.setAdapter(dataAdapter);

    }

    public void loadParameter(){

        String uniqId= Unique.getInstance().getUniqId();

        List parameter=smartDCRDBHelper.showInspectionById(uniqId);

        parameter.add(0,"Select");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinnertext, parameter);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        parameterSpinner.setAdapter(dataAdapter);

    }

//    public void dbConnection(){
//
//        Log.i("Android", " SQL Server Connect Example.");
//
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
//                .permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//
//        try {
//            String driver = "net.sourceforge.jtds.jdbc.Driver";
//            Log.w("Con driver===", "open" + driver);
//            Class.forName(driver).newInstance();
////test = com.microsoft.sqlserver.jdbc.SQLServerDriver.class;
//            Log.w("Connection===","class");
//            conn = DriverManager.getConnection(connString, username, password);
//            Log.w("Connection===","open"+conn);
//            Statement stmt = conn.createStatement();
//
//            String uniqId= Unique.getInstance().getUniqId();
//
//            ResultSet reset = stmt.executeQuery("select * from SiteInspection where UniqueId='"+uniqId+"'");
//
//            if(reset!=null){
//
//                Log.w("reset:------------",reset+"");
//            }
//
////Print the data to the console
//            while(reset.next()){
//
//                if(reset.getString("ID")!=null) {
//                     //   Log.e("=== ID === ", reset.getString("ID"));
//
//                }
//
//                if(reset.getString("UniqueId")!=null) {
//                        Log.e("=== UniqueId === ", reset.getString("UniqueId"));
//
//                }
//
//                if(reset.getString("File_Number")!=null) {
//                 //       Log.e("=== File_Number === ", reset.getString("File_Number"));
//
//                }
//
//                if(reset.getString("Section")!=null) {
//                 //       Log.e("=== Section === ", reset.getString("Section"));
//
//                }
//
//                if(reset.getString("Parameter")!=null) {
//                            Log.e("=== Parameter === ", reset.getString("Parameter"));
//
//                }
//
//                if(reset.getString("Min")!=null) {
//                  //          Log.e("=== Min === ", reset.getString("Min"));
//
//                }
//
//                if(reset.getString("Max")!=null) {
//                  //          Log.e("=== Max === ", reset.getString("Max"));
//
//                }
//
//                if(reset.getString("Actual")!=null) {
//                 //           Log.e("=== Actual === ", reset.getString("Actual"));
//
//                }
//
//                if(reset.getString("Measured")!=null) {
//                 //           Log.e("=== Measured === ", reset.getString("Measured"));
//
//                }
//
//                if(reset.getString("Violation")!=null) {
//                 //           Log.e("=== Violation === ", reset.getString("Violation"));
//
//                }
//
//                if(reset.getString("Violationpercentage")!=null) {
//                  //          Log.e("=== percentage === ", reset.getString("Violationpercentage"));
//
//                }
//
//                if(reset.getString("Result")!=null) {
//                            Log.e("=== Result === ", reset.getString("Result"));
//
//                }
//
//
//                if(reset.getString("SeverityStatus")!=null) {
//                            Log.e("=== SeverityStatus === ", reset.getString("SeverityStatus"));
//
//                }
//
//                if(reset.getString("Submissiontype")!=null) {
//                 //           Log.e("=== Submissiontype === ", reset.getString("Submissiontype"));
//
//                }
//            }
//            conn.close();
//        }
//        catch (ClassNotFoundException ex) {Log.e(" class not found---", ex.toString());}
//        catch (IllegalAccessException ex) {Log.e("illagal---", ex.toString());}
//        catch (InstantiationException ex) {Log.e("instatiation---", ex.toString());}
//        catch (SQLException ex)           {Log.e("sql Error ---", ex.toString());}
//    }
}
