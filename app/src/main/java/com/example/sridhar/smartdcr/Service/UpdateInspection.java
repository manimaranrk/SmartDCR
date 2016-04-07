package com.example.sridhar.smartdcr.Service;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.omneagate.inspection.DTO.InspectionActivitiesDto;
//import com.omneagate.inspection.DTO.InspectionDto;
//import com.omneagate.inspection.DTO.InspectionResultDto;
//import com.omneagate.inspection.DTO.LoginResponseDto;
//import com.omneagate.inspection.Util.InspectionDBHelper;
//import com.omneagate.inspection.Util.InspectionList;
//import com.omneagate.inspection.Util.LoginResponce;
//import com.omneagate.inspection.Util.NetworkConnection;
//import com.omneagate.inspection.Util.SessionId;
//import com.omneagate.inspection.activity.R;


import com.example.sridhar.smartdcr.DBHandler.SmartDCRConstants;
import com.example.sridhar.smartdcr.DBHandler.SmartDCRDBHelper;
import com.example.sridhar.smartdcr.R;
import com.example.sridhar.smartdcr.Util.NetworkConnection;
import com.example.sridhar.smartdcr.Util.Unique;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class UpdateInspection extends Service {

    NetworkConnection networkConnection;

    SmartDCRDBHelper controller;

    Context context;

//    InspectionDBHelper controller;
//    NetworkConnection networkConnection;
//
//    //HttpConnection service
//    HttpClientWrapper httpConnection;
//
//    InspectionDto inspectionDto=new InspectionDto();
//    InspectionResultDto inspectionResultDto=new InspectionResultDto();
//    LoginResponseDto loginResponse=new LoginResponseDto();

    @Override
    public void onCreate() {
        super.onCreate();

        controller= SmartDCRDBHelper.getInstance(this);
        //controller=new SmartDCRDBHelper(this);
        Thread notifyingThread = new Thread(null, serviceTask, "UpdateInspection");
        notifyingThread.start();

    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    /*Connection device Thread*/
    private final Runnable serviceTask = new Runnable() {
        public void run() {
            Looper.prepare();
            while (true) {
                try {
//                    if (SessionId.getInstance().getSessionId().length() > 0) {
                        NetworkConnection networkConnection = new NetworkConnection(UpdateInspection.this);
                        if (networkConnection.isNetworkAvailable()){
                            new ConnectionSenddata().execute("");
                    }
                    Integer timeout = Integer.parseInt(getString(R.string.serviceTimeout));
                    Thread.sleep(timeout);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    //Async   task for connection heartbeat
    private class ConnectionSenddata extends AsyncTask<String, String, String> {

        // Download Music File from Internet
        @Override
        protected String doInBackground(String... f_url) {
            BufferedReader in;
            try {

                Connection conn = null;
                String connString = "jdbc:jtds:sqlserver://182.18.165.141:1433/MobileApp;user=Hetro;password=Genous@1;";
                String username = "Hetro";
                String password = "Genous@1";

                try {

                    String driver = "net.sourceforge.jtds.jdbc.Driver";
                    Log.w("Con driver===", "open" + driver);
                    Class.forName(driver).newInstance();
//test = com.microsoft.sqlserver.jdbc.SQLServerDriver.class;
                    Log.w("Connection===", "class");
                    conn = DriverManager.getConnection(connString, username, password);
                    Log.w("Connection===", "open" + conn);
                    Statement stmt = conn.createStatement();


                  //  String uniqId = Unique.getInstance().getUniqId();


                    Cursor cursor=controller.inspectedSiteData();

                            cursor.moveToFirst();
                            int cursorCount=cursor.getCount();

                            if (cursor.moveToFirst()) {
                            do {

                                String measured=cursor.getString(cursor.getColumnIndex(SmartDCRConstants.KEY_INSPECTED_MEASURED));

                                String severity=cursor.getString(cursor.getColumnIndex(SmartDCRConstants.KEY_INSPECTED_SEVERITY_STATUS));

                                String inspectionid=cursor.getString(cursor.getColumnIndex(SmartDCRConstants.KEY_INSPECTED_ID));

                                int count=stmt.executeUpdate("UPDATE SiteInspection SET Measured='" + measured+
                                        "',SeverityStatus='"+severity+"'WHERE ID='"+inspectionid+"'");

                                Log.e("count update---",count+"");


                                if(count==1){

                                    controller.updateInspectedData(inspectionid);


                                }


                            } while (cursor.moveToNext());
                            }
                            cursor.close();







                    //  int count = stmt.executeQuery("select * from SiteInspection where UniqueId='" + uniqId + "'");

                }
                catch (Exception e){

                    Log.i("Exception",e.toString());
                }




                //  Log.e("survey",f_url.toString());
//                String urldata="http://192.168.1.72:9092";
//                String url1 = "/inspection/validatedevice";
//                String url = urldata + url1;
//                Log.e("service url---", url);
//                URI website = new URI(url);
//
//                String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
//                        Settings.Secure.ANDROID_ID);
//                Long lastSyncTime=InspectionDBHelper.getInstance(getApplicationContext()).showuser();
//
//                Log.e("lastSyncTime service--", lastSyncTime.toString());
//
//                String username=InspectionDBHelper.getInstance(getApplicationContext()).userName();
//                inspectionDto.setMacID(android_id);
//                inspectionDto.setUserName(username);
//                inspectionDto.setLastSyncTime(lastSyncTime);
//                Log.e("sync android id--", android_id);
//                Log.e("sync username--", username);
//
//                Log.e("service-inspectDto---", inspectionDto + "");
//                String requestDataSend = new Gson().toJson(inspectionDto);
//      //          Log.e("service request--", requestDataSend);
//                StringEntity entity = new StringEntity(requestDataSend, HTTP.UTF_8);
//                HttpResponse response = requestType(website, entity);
//                in = new BufferedReader(new InputStreamReader(response
//                        .getEntity().getContent()));
//                StringBuffer sb = new StringBuffer("");
//                String l;
//                String nl = System.getProperty("line.separator");
//                while ((l = in.readLine()) != null) {
//                    sb.append(l + nl);
//                }
//                in.close();
//                String responseData = sb.toString();
//                Log.e("service Response", responseData);
//                GsonBuilder gsonBuilder = new GsonBuilder();
//                Gson gson = gsonBuilder.create();
//                inspectionResultDto=gson.fromJson(responseData,InspectionResultDto.class);
//                if (inspectionResultDto.getStatusCode() == 0) {
//                   Log.e("inspection service", inspectionResultDto + "");
//                    if (LoginResponce.getInstance().isAuthenticationStatus()) {
//                        SessionId.getInstance().setSessionId(LoginResponce.getInstance().getSessionid());
//                        String userNme=LoginResponce.getInstance().getUserDetailDto().getUsername();
//                        String passWord=LoginResponce.getInstance().getUserDetailDto().getPassword();
//                        int count=InspectionDBHelper.getInstance(getApplicationContext()).selectUser();
//                        if(count==0) {
//                            Log.e("service ", "before  insert");
//                            InspectionDBHelper.getInstance(getApplicationContext()).insertUserService(userNme,passWord);
//                            Log.e("service ", "after user insert");
//                       }else{
//                            InspectionDBHelper.getInstance(getApplicationContext()).updateUserService(userNme,passWord);
//                            Log.e("service ", "after user update");
//                        }
//                      //  InspectionDBHelper.getInstance(getApplicationContext()).insertDataservice(userNme,passWord);
//                        Log.e("service sync time----", inspectionResultDto.getLastSyncTime() + "");
//                        for (InspectionActivitiesDto inspectionActivitiesDto : inspectionResultDto.getInspectionlist()) {
////                            Log.e("service for----",inspectionResultDto.getInspectionlist()+"");
//                            InspectionList.getInstance().setInspectionlist(inspectionResultDto.getInspectionlist());
//                            InspectionDBHelper.getInstance(getApplicationContext()).insertInspectionList(inspectionActivitiesDto);
//  //                          Log.e("add to db","db added");
//                            //------------------get last synctime-------------//
//                        }
//                        InspectionDBHelper.getInstance(getApplicationContext()).update_LastSyncTime(username, inspectionResultDto.getLastSyncTime());
//                    }
//                }

            } catch (Exception e) {
                Log.e("Error: ", e.toString());
            }
            return null;
        }

        // Once Music File is downloaded
        @Override
        protected void onPostExecute(String file_url) {

        }
    }

//    /*return http POST method using parameters*/
//    private HttpResponse requestType(URI website, StringEntity entity) {
//        try {
//            HttpParams httpParameters = new BasicHttpParams();
//            int timeoutConnection = 50000;
//            HttpConnectionParams.setConnectionTimeout(httpParameters,
//                    timeoutConnection);
//            int timeoutSocket = 50000;
//            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
//            HttpClient client = new DefaultHttpClient(httpParameters);
//            HttpPost postRequest = new HttpPost();
//            postRequest.setURI(website);
//            postRequest.setHeader("Content-Type", "application/json");
//            postRequest.setHeader("Cookie", "JSESSIONID=" + SessionId.getInstance().getSessionId());
//            postRequest.setEntity(entity);
//
//            return client.execute(postRequest);
//        } catch (Exception e) {
//            Log.e("Error", e.toString(), e);
//        }
//        return null;
//    }

    @Override
    public void onDestroy() {

    }
}