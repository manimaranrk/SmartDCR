package com.example.sridhar.smartdcr.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;


import com.example.sridhar.smartdcr.Util.CustomProgressDialog;
import com.example.sridhar.smartdcr.Util.NetworkConnection;

//import com.omneagate.inspection.DTO.EnumDTO.RequestType;
//import com.omneagate.inspection.DTO.EnumDTO.ServiceListenerType;
//import com.omneagate.inspection.Util.CustomProgressDialog;
//import com.omneagate.inspection.Util.InspectionDBHelper;
//import com.omneagate.inspection.Util.NetworkConnection;
//import com.omneagate.inspection.Util.SessionId;
//import com.omneagate.inspection.service.HttpClientWrapper;
//import com.omneagate.inspection.service.InspectionListDownload;


/**
 * Created by user1 on 13/3/15.
 */
public abstract class BaseActivity extends Activity {

    NetworkConnection networkConnection;

    //HttpClientWrapper httpConnection;

//    GlobalAppState appState;

    CustomProgressDialog progressBar;

    ActionBar actionBar;


   // protected abstract void processMessage(Bundle message, ServiceListenerType what);

    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);

    }



//    public void actionBarCreation(String header) {
//        try {
//            actionBar = getActionBar();
//            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#34495e")));
//            actionBar.setTitle(header);
//            actionBar.setIcon(R.drawable.response_menu);
//            LayoutInflater mInflater = LayoutInflater.from(this);
//            View titleText = mInflater.inflate(R.layout.actionbar_custom,
//                    null);
//            actionBar.setCustomView(titleText);
//            ((TextView) titleText.findViewById(R.id.login_actionbar)).setText(header);
//            actionBar.setDisplayShowCustomEnabled(true);
//
//        } catch (Exception e) {
//            android.util.Log.e("Error", e.toString(), e);
//        }
//
//    }




//    protected final Handler SyncHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            ServiceListenerType type = (ServiceListenerType) msg.obj;
//            switch (type) {
//                case LOGIN_USER:
//                    processMessage(msg.getData(), ServiceListenerType.LOGIN_USER);
//                    break;
//                case CHECK_VALID_DEVICE:
//                    processMessage(msg.getData(), ServiceListenerType.CHECK_VALID_DEVICE);
//                    break;
//                case REGISTER_DEVICE:
//                    processMessage(msg.getData(), ServiceListenerType.REGISTER_DEVICE);
//                    break;
//                case GET_DISTRICTS:
//                    processMessage(msg.getData(), ServiceListenerType.GET_DISTRICTS);
//                    Log.e("dis", "dis");
//                    break;
//
//                case GET_TALUKS:
//                    processMessage(msg.getData(), ServiceListenerType.GET_TALUKS);
//                    break;
//
//                case GET_VILLAGES:
//                    processMessage(msg.getData(), ServiceListenerType.GET_VILLAGES);
//                    break;
//
//                case SCHEDULE_REPORT:
//                    processMessage(msg.getData(), ServiceListenerType.SCHEDULE_REPORT);
//                    break;
//                default:
//                    processMessage(msg.getData(), ServiceListenerType.ERROR_MSG);
//                    break;
//            }
//        }
//
//    };

//public  void setFooterText(){
//    TextView footerTextAt = (TextView) findViewById(R.id.tvfooterAt);
//    footerTextAt.setText("@");
//}


    //Logout request from user success and send to server
//    public void logOutSuccess() {
//        SessionId.getInstance().setSessionId("");
//        networkConnection = new NetworkConnection(this);
//        if (networkConnection.isNetworkAvailable()) {
//            String url = "/login/logmeout";
//            httpConnection = new HttpClientWrapper();
//            httpConnection.sendRequest(url, null, ServiceListenerType.LOGOUT_USER,
//                    SyncHandler, RequestType.GET, null, this);
//        }
//        InspectionDBHelper.getInstance(this).closeConnection();
//        Intent intent = new Intent(this, InspectionListDownload.class);
//        stopService(intent);
//        startActivity(new Intent(this,LoginActivity.class));
//        finish();
//
//
//    }



}
