package com.example.sridhar.smartdcr.Controller;

import android.os.StrictMode;
import android.util.Log;

import com.example.sridhar.smartdcr.Adapter.InspectionArrayAdapter;
import com.example.sridhar.smartdcr.DTO.InspectionDataDto;
import com.example.sridhar.smartdcr.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Sridhar on 5/28/2015.
 */
public class SqlServerConnection {


    Connection conn = null;
    String connString = "jdbc:jtds:sqlserver://182.18.165.141:1433/MobileApp;user=Hetro;password=Genous@1;";
    String username = "Hetro";
    String password = "Genous@1";

    public void dbConnection(){



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
            ResultSet reset = stmt.executeQuery("select * from SiteData");



//            if(reset!=null){
//
//                Log.w("reset:------------",reset+"");
//            }

//Print the data to the console
//            while(reset.next()){
//
//                if(reset.getString("Uniqid")!=null) {
//                    Log.e("Uniqid::: ", reset.getString("Uniqid"));
//                    uniqId=reset.getString("Uniqid");
//                }
//                else{
//                    uniqId="null";
//                }
//
//
//                if(reset.getString("drawing")!=null) {
//                    Log.e("drawing::: ", reset.getString("drawing"));
//                    drawing=reset.getString("drawing");
//
//                }
//                else{
//                    drawing="null";
//                }
//
//
//                if(reset.getString("Status")!=null) {
//                    Log.e("Status::: ", reset.getString("Status"));
//                    status=reset.getString("Status");
//                }
//                else{
//                    status="null";
//                }
//
//
//
//                smartDCRDBHelper.insertSiteData(uniqId, drawing, status);
//
//                //       listViewAdapter=new ListViewAdapter(this,R.layout.listcolumn);
//
//                inspectionArrayAdapter = new InspectionArrayAdapter(this, R.layout.listcolumn);
//
//                List<InspectionDataDto> inspectionDataDtoArrayList=smartDCRDBHelper.showList();
//
////                temp.put("Uniqid", uniqId);
////                temp.put("drawing", drawing);
////                temp.put("Status", status);
//
//                //    list.add(temp);
//
//                for (int i = 0; i < inspectionDataDtoArrayList.size(); i++) {
//                    inspectionDataDto = inspectionDataDtoArrayList.get(i);
//
//
//                    inspectionArrayAdapter.add(inspectionDataDto);
//                    inspectionList.setAdapter(inspectionArrayAdapter);
//                    inspectionArrayAdapter.notifyDataSetChanged();
//
//
//                    //       listViewAdapter.add(inspectionDataDto);
//
//
//                    //        inspectionList.setAdapter(listViewAdapter);
//                    //     listViewAdapter.notifyDataSetChanged();
//                }
//                //       Log.e("temp----",temp.toString());
//
//
//            }


            conn.close();

        }
        catch (ClassNotFoundException ex) {Log.e(" class not found---", ex.toString());}
        catch (IllegalAccessException ex) {Log.e("illagal---", ex.toString());}
        catch (InstantiationException ex) {Log.e("instatiation---", ex.toString());}
        catch (SQLException ex)           {Log.e("sql Error ---", ex.toString());}
    }





}
