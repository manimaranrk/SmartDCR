package com.example.sridhar.smartdcr.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sridhar.smartdcr.DTO.InspectionDataDto;
import com.example.sridhar.smartdcr.R;
import com.example.sridhar.smartdcr.Util.CustomProgressDialog;


//import com.omneagate.inspection.DTO.InspectionActivitiesDto;
//import com.omneagate.inspection.activity.R;
//import com.omneagate.inspection.activity.ScheduledInspectionActivity;
//import com.omneagate.inspection.activity.ViewInspectionActivity;
//import com.omneagate.inspection.dialog.InformationDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class InspectionArrayAdapter extends ArrayAdapter<InspectionDataDto> {


    Activity context;
    CustomProgressDialog progressBar;
    InspectionDataDto inspectionDataDto=null;
    private List<InspectionDataDto> inspectionlist = new ArrayList<InspectionDataDto>();


        static class InspectionViewHolder {

            TextView uniqidtxt,drawintxt,statustxt;


        }


        public InspectionArrayAdapter(Activity context, int textViewResourceId) {
            super(context, textViewResourceId);

            this.context = context;

        }

    @Override
    public void add(InspectionDataDto object) {
        inspectionlist.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.inspectionlist.size();
    }

    @Override
    public InspectionDataDto getItem(int index) {
        return this.inspectionlist.get(index);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final InspectionViewHolder viewHolder;
        if (row == null) {


            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.listcolumn, parent, false);
            viewHolder = new InspectionViewHolder();

            viewHolder.uniqidtxt=(TextView) row.findViewById(R.id.uniqid);

            viewHolder.statustxt=(TextView) row.findViewById(R.id.status);



            row.setTag(viewHolder);

        } else {
            viewHolder = (InspectionViewHolder)row.getTag();
        }
        inspectionDataDto = getItem(position);

        Log.e("profilepage----", inspectionDataDto + "");

//        if(inspectionDataDto.getId()!=0) {
//
//         Log.e("idddddd", inspectionActivitiesDto.getId().toString());
//            viewHolder.sNoTxtview.setText(""+(position+1));
////            viewHolder.sNoTxtview.setText(position);
//        }

        if(inspectionDataDto!=null){

            if(inspectionDataDto.getUniqId()!=null){

             //   String uniqId=inspectionDataDto.getUniqId();
                viewHolder.uniqidtxt.setText(inspectionDataDto.getUniqId());


            }

            if(inspectionDataDto.getDrawing()!=null){

           //     String drawing=inspectionDataDto.getDrawing();

           //     Log.i("drr",drawing);

             //   viewHolder.drawintxt.setText("PDF");


            }

            if(inspectionDataDto.getStatus()!=null){

           //     String status=inspectionDataDto.getStatus();
                viewHolder.statustxt.setText(inspectionDataDto.getStatus());


            }

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


}
