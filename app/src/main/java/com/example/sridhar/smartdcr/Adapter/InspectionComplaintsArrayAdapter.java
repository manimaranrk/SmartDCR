package com.example.sridhar.smartdcr.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sridhar.smartdcr.DBHandler.SmartDCRDBHelper;
import com.example.sridhar.smartdcr.DTO.InspectionDataDto;
import com.example.sridhar.smartdcr.DTO.InspectionListByUniqId;
import com.example.sridhar.smartdcr.R;
import com.example.sridhar.smartdcr.Util.CustomProgressDialog;
//import com.example.sridhar.smartdcr.Util.ImageLoader;
import com.example.sridhar.smartdcr.Util.ImageLoader;
import com.example.sridhar.smartdcr.Util.NetworkConnection;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import com.omneagate.inspection.DTO.InspectionActivitiesDto;
//import com.omneagate.inspection.activity.R;
//import com.omneagate.inspection.activity.ScheduledInspectionActivity;
//import com.omneagate.inspection.activity.ViewInspectionActivity;
//import com.omneagate.inspection.dialog.InformationDialog;


public class InspectionComplaintsArrayAdapter extends ArrayAdapter<InspectionListByUniqId> {

    Connection conn = null;
    String connString = "jdbc:jtds:sqlserver://182.18.165.141:1433/MobileApp;user=Hetro;password=Genous@1;";
    String username = "Hetro";
    String password = "Genous@1";

    public ImageLoader imageLoader;

    ProgressDialog dialog;
    NetworkConnection networkConnection;
    Activity context;
    CustomProgressDialog progressBar;
    InspectionListByUniqId inspectionListByUniqId=null;
    private List<InspectionListByUniqId> inspectionListByUniqIds = new ArrayList<InspectionListByUniqId>();


        static class InspectionViewHolder {

            TextView uniqidTextview,parameterTextview,measuredTextview,severityTextview,typeTextview;
            ImageView inspectionImage;


        }


        public InspectionComplaintsArrayAdapter(Activity context, int textViewResourceId) {
            super(context, textViewResourceId);
            networkConnection = new NetworkConnection(context);
            this.context = context;

        }

    @Override
    public void add(InspectionListByUniqId object) {

        inspectionListByUniqIds.add(object);

        super.add(object);
    }

    @Override
    public int getCount() {
        return this.inspectionListByUniqIds.size();
    }

    @Override
    public InspectionListByUniqId getItem(int index) {
        return this.inspectionListByUniqIds.get(index);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final InspectionViewHolder viewHolder;
        if (row == null) {


            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.column_complaints, parent, false);
            viewHolder = new InspectionViewHolder();

            viewHolder.uniqidTextview=(TextView) row.findViewById(R.id.sno_textview);
            viewHolder.parameterTextview=(TextView) row.findViewById(R.id.items_textview);
            viewHolder.measuredTextview=(TextView) row.findViewById(R.id.measured_textview);
            viewHolder.severityTextview=(TextView) row.findViewById(R.id.severity_textview);
            viewHolder.typeTextview=(TextView) row.findViewById(R.id.type_textview);
            viewHolder.inspectionImage=(ImageView) row.findViewById(R.id.inspection_imageview);




            row.setTag(viewHolder);

        } else {
            viewHolder = (InspectionViewHolder)row.getTag();
        }
        inspectionListByUniqId = getItem(position);

        Log.e("profilepage----", inspectionListByUniqId + "");

//        if(inspectionDataDto.getId()!=0) {
//
//         Log.e("idddddd", inspectionActivitiesDto.getId().toString());
//            viewHolder.sNoTxtview.setText(""+(position+1));
////            viewHolder.sNoTxtview.setText(position);
//        }

        if(inspectionListByUniqId!=null){

            if(inspectionListByUniqId.getUniqId()!=null){

                  viewHolder.uniqidTextview.setText(""+(position+1));

             //   String uniqId=inspectionDataDto.getUniqId();
             //   viewHolder.uniqidTextview.setText(inspectionListByUniqId.getUniqId());
            }


            if(inspectionListByUniqId.getMeasured()!=null){

                viewHolder.measuredTextview.setText(inspectionListByUniqId.getMeasured());

            }

            if(inspectionListByUniqId.getParameter()!=null){

                viewHolder.parameterTextview.setText(inspectionListByUniqId.getParameter());

            }

            if(inspectionListByUniqId.getSeverity()!=null){

                viewHolder.severityTextview.setText(inspectionListByUniqId.getSeverity());

            }

            if(inspectionListByUniqId.getImage()!=null) {




                Log.e("inspectionListBy--",inspectionListByUniqId.getImage().toString());

                ByteArrayInputStream imageStream = new ByteArrayInputStream(inspectionListByUniqId.getImage());
                Bitmap theImage = BitmapFactory.decodeStream(imageStream);
                viewHolder.inspectionImage.setImageBitmap(theImage);

                //----------------------------------------

//                String filepath= SmartDCRDBHelper.getInstance(context).getImagepath(inspectionListByUniqId.getUniqId(), inspectionListByUniqId.getParameter());
//
//                if(filepath!=null) {
//
//
//
//                    Bitmap bmp = BitmapFactory.decodeFile(filepath);
//
//                    // Set the decoded bitmap into ImageView
//                    viewHolder.inspectionImage.setImageBitmap(bmp);
//                }






            }
            else{
                Log.e("image empty==","image empty");
            }


//            if(inspectionListByUniqId.getType()!=null){
//
//                viewHolder.typeTextview.setText(inspectionListByUniqId.getType());
//
//            }else{
//
//                viewHolder.typeTextview.setText("nil");
//            }




            //  new DownloadSiteInspection().execute();





//            if(inspectionListByUniqId.getType()!=null){
//
//                viewHolder.typeTextview.setText(inspectionListByUniqId.getType());
//
//            }




//            if(inspectionActivitiesDto.getScheduledDate()!=null){
//
//                Date date=new Date(inspectionActivitiesDto.getScheduledDate());
//                SimpleDateFormat df2 = new SimpleDateFormat("dd/MMM/yy");
//                String dateText = df2.format(date);
//
//                Log.e("date---", dateText);
//
//                viewHolder.inspectionDateTxtview.setText(dateText);
//
//            }
//            if(inspectionActivitiesDto.getGodownOrFPSName()!=null){
//
//
//
//                    String name=inspectionActivitiesDto.getGodownOrFPSName();
//
//                    viewHolder.fpsGodownNameTxtview.setText(inspectionActivitiesDto.getGodownOrFPSName());
//
//
//            }





        }
        return row;
    }

    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte= Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
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


            dialog=new ProgressDialog(getContext());
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

            //      tv.setText("Completed the task, and the result is : " + myData);
        }
    }

    public void getSiteInspection(){

        if (networkConnection.isNetworkAvailable()) {

//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
//                    .permitAll().build();
//            StrictMode.setThreadPolicy(policy);

            try {
                String driver = "net.sourceforge.jtds.jdbc.Driver";

                Class.forName(driver).newInstance();


                conn = DriverManager.getConnection(connString, username, password);

                Statement stmt = conn.createStatement();

                String parametr = inspectionListByUniqId.getParameter();

                String uniq=inspectionListByUniqId.getUniqId();


                ResultSet reset = stmt.executeQuery("select * from SiteInspection" + " WHERE UniqueId = '"+uniq+"' AND Parameter = '"+parametr+"'");



                if(reset!=null){

                    Log.w("reset:------------",reset+"");
                }


                while(reset.next()){


                    if(reset.getString("Image")!=null) {
                        Log.e("=== Image adapter === ", reset.getString("Image"));


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
            Toast.makeText(getContext(),
                    "Network is not Available. Please Check Your Internet Connection ",
                    Toast.LENGTH_SHORT).show();
            Looper.loop();


        }

    }



}
