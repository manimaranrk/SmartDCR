package com.example.sridhar.smartdcr.DTO;

        import lombok.Getter;
        import lombok.Setter;


public class UniqueIdDto {


    @Getter
    @Setter
    String uniqId;

    public String getUniqId() {
        return uniqId;
    }

    public void setUniqId(String uniqId) {
        this.uniqId = uniqId;
    }
}
