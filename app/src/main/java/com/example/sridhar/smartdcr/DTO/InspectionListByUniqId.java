package com.example.sridhar.smartdcr.DTO;


import android.database.Cursor;

import com.example.sridhar.smartdcr.DBHandler.SmartDCRConstants;

import lombok.Getter;
import lombok.Setter;

public class InspectionListByUniqId {


    String uniqId;
    String parameter;
   // String drawing;
    String measured;
    String severity;
    String type;
    byte[] image;


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }




    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUniqId() {
        return uniqId;
    }

    public void setUniqId(String uniqId) {
        this.uniqId = uniqId;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

//    public String getDrawing() {
//        return drawing;
//    }
//
//    public void setDrawing(String drawing) {
//        this.drawing = drawing;
//    }

    public String getMeasured() {
        return measured;
    }

    public void setMeasured(String measured) {
        this.measured = measured;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }


    public InspectionListByUniqId() {

    }


    public InspectionListByUniqId(Cursor cur) {

        uniqId =  cur.getString(cur
                .getColumnIndex(SmartDCRConstants.KEY_INEPECTION_UNIQUE_ID));

        parameter = cur.getString(cur
                .getColumnIndex(SmartDCRConstants.KEY_INSPECTION_PARAMETER));

        measured = cur.getString(cur
                .getColumnIndex(SmartDCRConstants.KEY_INSPECTION_MEASURED));

        severity= cur.getString(cur.getColumnIndex(SmartDCRConstants.KEY_INSPECTION_SEVERITY_STATUS));

        type=cur.getString(cur.getColumnIndex(SmartDCRConstants.KEY_INSPECTION_SUBMISSIONTYPE));

        image=cur.getBlob(cur.getColumnIndex(SmartDCRConstants.KEY_INSPECTION_IMAGE));




    }





}
