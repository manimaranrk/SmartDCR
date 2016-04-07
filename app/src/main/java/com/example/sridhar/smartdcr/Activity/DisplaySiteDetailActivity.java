package com.example.sridhar.smartdcr.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.sridhar.smartdcr.Adapter.InspectionArrayAdapter;
import com.example.sridhar.smartdcr.Adapter.InspectionComplaintsArrayAdapter;
import com.example.sridhar.smartdcr.DBHandler.SmartDCRConstants;
import com.example.sridhar.smartdcr.DBHandler.SmartDCRDBHelper;
import com.example.sridhar.smartdcr.DTO.InspectionListByUniqId;
import com.example.sridhar.smartdcr.R;
import com.example.sridhar.smartdcr.Util.Unique;
import com.example.sridhar.smartdcr.R;
import java.util.List;


public class DisplaySiteDetailActivity extends Activity {

    ListView complaintsListview;

    String uniqId;

    ProgressDialog dialog;

    InspectionListByUniqId inspectionListByUniqId;

    InspectionComplaintsArrayAdapter inspectionComplaintsArrayAdapter;

    List<InspectionListByUniqId> inspectionListByUniqIds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaysitedetails);

        complaintsListview=(ListView)findViewById(R.id.complaints_listview);

        new LoadGrid().execute();

    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(DisplaySiteDetailActivity.this, MainMenuActivity.class));
        finish();
    }


    class LoadGrid extends AsyncTask<Void, Void, Void> {

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


            dialog=new ProgressDialog(DisplaySiteDetailActivity.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setTitle("Load Inspection Grid...");
            dialog.setCancelable(false);
            dialog.show();
        }

        // Executed on a special thread and all your
        // time taking tasks should be inside this method
        @Override
        protected Void doInBackground(Void... arg0) {


            inspectionComplaintsArrayAdapter = new InspectionComplaintsArrayAdapter(DisplaySiteDetailActivity.this,R.layout.column_complaints);
            uniqId = Unique.getInstance().getUniqId();
             inspectionListByUniqIds = SmartDCRDBHelper.getInstance(DisplaySiteDetailActivity.this).showInspectionByUniqid(uniqId);


            return null;
        }

        // Executed on the UI thread after the
        // time taking process is completed
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            dialog.dismiss();

            Log.e("inspectionListByUniqIds", inspectionListByUniqIds.toString());

            for (int i = 0; i < inspectionListByUniqIds.size(); i++) {
                inspectionListByUniqId = inspectionListByUniqIds.get(i);
                inspectionComplaintsArrayAdapter.add(inspectionListByUniqId);
                complaintsListview.setAdapter(inspectionComplaintsArrayAdapter);
                inspectionComplaintsArrayAdapter.notifyDataSetChanged();
                Log.e("inspectionListByUniqId", inspectionListByUniqId.getSeverity());

            }

            //      tv.setText("Completed the task, and the result is : " + myData);
        }
    }


    public void loadGrid(){

            inspectionComplaintsArrayAdapter = new InspectionComplaintsArrayAdapter(DisplaySiteDetailActivity.this,R.layout.column_complaints);
            uniqId = Unique.getInstance().getUniqId();
            List<InspectionListByUniqId> inspectionListByUniqIds = SmartDCRDBHelper.getInstance(DisplaySiteDetailActivity.this).showInspectionByUniqid(uniqId);

            Log.e("inspectionListByUniqIds", inspectionListByUniqIds.toString());

            for (int i = 0; i < inspectionListByUniqIds.size(); i++) {
                inspectionListByUniqId = inspectionListByUniqIds.get(i);
                inspectionComplaintsArrayAdapter.add(inspectionListByUniqId);
                complaintsListview.setAdapter(inspectionComplaintsArrayAdapter);
                inspectionComplaintsArrayAdapter.notifyDataSetChanged();
                Log.e("inspectionListByUniqId", inspectionListByUniqId.getSeverity());

            }


//        uniqId=Unique.getInstance().getUniqId();
//        Cursor cursor = SmartDCRDBHelper.getInstance(DisplaySiteDetailActivity.this).showInspectionByUniqid(uniqId);
//
//
//        cursor.moveToFirst();
//        int count=cursor.getCount();
//        Log.e("evidence count", count + "");
//        if (cursor.moveToFirst()) {
//            do {
//                Log.e(" ### uniq id=====", cursor.getString(cursor.getColumnIndex(SmartDCRConstants.KEY_INEPECTION_UNIQUE_ID)));
//                Log.e(" ### status =====", cursor.getString(cursor.getColumnIndex(SmartDCRConstants.KEY_INSPECTION_ID)));
//
//
//
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
    }
}