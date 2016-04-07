package com.example.sridhar.smartdcr.Util;

import com.example.sridhar.smartdcr.DTO.UniqueIdDto;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;

@Data
public class Unique {

    private static UniqueIdDto mInstance = null;

    @Getter
    @Setter
    private UniqueIdDto unique;

    private Unique() {
        unique = new UniqueIdDto();
    }

    public static UniqueIdDto getInstance() {
        if (mInstance == null) {
            mInstance = new UniqueIdDto();
        }
        return mInstance;
    }

}
