package com.example.sridhar.smartdcr.Util;

import com.example.sridhar.smartdcr.DTO.InspectionIdDto;
import com.example.sridhar.smartdcr.DTO.UniqueIdDto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Sridhar on 6/2/2015.
 */
public class Inspection {


    private static InspectionIdDto mInstance = null;

    @Getter
    @Setter
    private InspectionIdDto inspectionIdDto;

    private Inspection() {
        inspectionIdDto = new InspectionIdDto();
    }

    public static InspectionIdDto getInstance() {
        if (mInstance == null) {
            mInstance = new InspectionIdDto();
        }
        return mInstance;
    }
}
