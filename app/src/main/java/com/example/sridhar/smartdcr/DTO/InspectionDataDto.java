package com.example.sridhar.smartdcr.DTO;


import android.database.Cursor;

import com.example.sridhar.smartdcr.DBHandler.SmartDCRConstants;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class InspectionDataDto {



    @Getter @Setter
    String uniqId;


    @Getter @Setter
    String drawing;

    public String getUniqId() {
        return uniqId;
    }

    public void setUniqId(String uniqId) {
        this.uniqId = uniqId;
    }

    public String getDrawing() {
        return drawing;
    }

    public void setDrawing(String drawing) {
        this.drawing = drawing;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Getter @Setter
    String status;

    public InspectionDataDto() {

    }


    public InspectionDataDto(Cursor cur) {

        uniqId =  cur.getString(cur
                .getColumnIndex(SmartDCRConstants.KEY_SITE_UNIQ_ID));

        drawing = cur.getString(cur
                .getColumnIndex(SmartDCRConstants.KEY_SITE_DRAWING));

        status = cur.getString(cur
                .getColumnIndex(SmartDCRConstants.KEY_SITE_STATUS));

    }

}
