package com.example.sridhar.smartdcr.DBHandler;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sridhar.smartdcr.DTO.InspectionDataDto;
import com.example.sridhar.smartdcr.DTO.InspectionListByUniqId;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import com.omneAgate.DTO.AgeGroupDto;
//import com.omneAgate.DTO.AllotmentDto;
//import com.omneAgate.DTO.AllotmentMappingDto;
//import com.omneAgate.DTO.BeneficiaryDto;
//import com.omneAgate.DTO.BeneficiaryMemberDto;
//import com.omneAgate.DTO.BillDto;
//import com.omneAgate.DTO.BillItemDto;
//import com.omneAgate.DTO.CardTypeDto;
//import com.omneAgate.DTO.ChellanProductDto;
//import com.omneAgate.DTO.FPSStockDto;
//import com.omneAgate.DTO.FpsDataDto;
//import com.omneAgate.DTO.FpsStockInwardResquestDto;
//import com.omneAgate.DTO.FpsStoreDto;
//import com.omneAgate.DTO.Fps_Stock_InwardDto;
//import com.omneAgate.DTO.GodownDto;
//import com.omneAgate.DTO.GodownStockOutwardDto;
//import com.omneAgate.DTO.Godown_Stock_InwardDto;
//import com.omneAgate.DTO.LoginResponseDto;
//import com.omneAgate.DTO.MessageDto;
//import com.omneAgate.DTO.ProductDto;
//import com.omneAgate.DTO.TransactionDto;
//import com.omneAgate.DTO.UserDetailDto;
//import com.omneAgate.Util.Constants.SmartDCRConstants;
//
//import org.joda.time.DateTime;


/**
 * FPS database Helper
 */
public class SmartDCRDBHelper extends SQLiteOpenHelper {

    // All Static variables
    private static SmartDCRDBHelper dbHelper = null;
    private static SQLiteDatabase database = null;
    // Database Version
    private static final int DATABASE_VERSION = 1;


    public static final String DATABASE_NAME = "SmartDCR.db";


    // User table name
    private final String TABLE_USERS = "SmartDCRUsers";


    // Server datail table name
    private final String TABLE_SERVER_DETAILS = "SmartDCRServerDetails";

    //
    private final String TABLE_SITE_DATA = "SmartDCRSiteData";
    //
    private final String TABLE_SITE_INSPECTION = "SmartDCRSiteInspection";

    private final String TABLE_SITE_INSPECTED = "SmartDCRSiteInspected";

    private final String TABLE_SITE_IMAGE = "SmartDCRSiteImage";




    // Database Name
 //   public static final String DATABASE_NAME = "FPS.db";





//    // Contacts table name
//    private final String TABLE_USERS = "fpsUsers";
//
//    // Products table name
//    private final String TABLE_PRODUCTS = "fpsProducts";
//
//    // Products table name
//    private final String TABLE_BENEFICIARY = "fpsBeneficiary";
//
//    // Beneficiary Member table name
//    private final String TABLE_BENEFICIARY_MEMBER = "fpsBeneficiaryMember";
//
//
//    // STOCK table name
//    private final String TABLE_STOCK = "fpsStock";
//
//
//    // STORE table name
//    private final String TABLE_STORE = "fpsStore";
//
//    // STORE table name
//    private final String TABLE_GO_DOWN = "fpsGodown";
//
//
//    // CardType table name
//    private final String TABLE_CARD_TYPE = "cardType";
//
//    // Age group table name
//    private final String TABLE_AGE_GROUP = "ageGroup";
//
//    // CardType table name
//    private final String TABLE_BILL_ITEM = "billItem";
//
//    // allotment table name
//    private final String TABLE_ALLOTMENT = "allotment";
//
//    // allotment mapping table name
//    private final String TABLE_ALLOTMENT_MAPPING = "allotmentMapping";
//
//    // bill table name
//    private final String TABLE_BILL = "bill";
//
//
//    //Beneficiary Activity Table
//    private final String TABLE_BENEFICIARY_ACTIVATION = "beneficiaryActivity";
//
//    //Lanuage Database Table for Error Message
//    private final String TABLE_LANGUAGE = "language";
//
//    //Transaction Tyope table
//    private final String TABLE_TRANSACTION_TYPE = "transactionType";
//
//
//    //Godown Stock outward table
//    private final String TABLE_FPS_STOCK_INVARD = "fpsStockInvard";

    //Key for id in tables
    public final static String KEY_ID = "_id";


    private static Context contextValue;


    // Users table with username and passwordHash
    private final String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
            + SmartDCRConstants.KEY_USERS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL,"
            + SmartDCRConstants.KEY_USERS_NAME + " VARCHAR(150),"
            + SmartDCRConstants.KEY_USERS_PASSWORD + " VARCHAR(150),"
            + SmartDCRConstants.KEY_USERS_STATUS + " VARCHAR(30), "
            + SmartDCRConstants.KEY_USERS_CREATE_DATE + " INTEGER,"
            + SmartDCRConstants.KEY_USERS_MODIFIED_DATE + " INTEGER,"
            + SmartDCRConstants.KEY_USERS_CREATED_BY + " VARCHAR(150), "
            + SmartDCRConstants.KEY_USERS_MODIFIED_BY + " VARCHAR(150)" + " )";


    private final String CREATE_SITE_TABLE = "CREATE TABLE " + TABLE_SITE_DATA + "("
            + SmartDCRConstants.KEY_SITE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL,"
            + SmartDCRConstants.KEY_SITE_UNIQ_ID + " VARCHAR(50) UNIQUE,"
            + SmartDCRConstants.KEY_SITE_DRAWING + " blob,"
            + SmartDCRConstants.KEY_SITE_STATUS + " VARCHAR(20),"
            + SmartDCRConstants.KEY_SITE_CREATE_DATE + " INTEGER,"
            + SmartDCRConstants.KEY_SITE_MODIFIED_DATE + " INTEGER,"
            + SmartDCRConstants.KEY_SITE_CREATED_BY + " VARCHAR(150),"
            + SmartDCRConstants.KEY_SITE_MODIFIED_BY + " VARCHAR(150)" + " ) ";


    private final String CREATE_INSPECTION_TABLE = "CREATE TABLE " + TABLE_SITE_INSPECTION + "("
            + SmartDCRConstants.KEY_INSPECTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL,"
            + SmartDCRConstants.KEY_INEPECTION_UNIQUE_ID + " VARCHAR(100),"
            + SmartDCRConstants.KEY_INSPECTION_FILENUMBER + " VARCHAR(255),"
            + SmartDCRConstants.KEY_INSPECTION_SECTION + " VARCHAR(50),"
            + SmartDCRConstants.KEY_INSPECTION_PARAMETER + " VARCHAR(50),"
            + SmartDCRConstants.KEY_INSPECTION_MIN + " VARCHAR(50),"
            + SmartDCRConstants.KEY_INSPECTION_MAX + " VARCHAR(50),"
            + SmartDCRConstants.KEY_INSPECTION_ACTUAL + " VARCHAR(50),"
            + SmartDCRConstants.KEY_INSPECTION_MEASURED + " VARCHAR(50),"
            + SmartDCRConstants.KEY_INSPECTION_VIOLATION + " VARCHAR(50),"
            + SmartDCRConstants.KEY_INSPECTION_VIOLATION_PERCENTAGE + " VARCHAR(50),"
            + SmartDCRConstants.KEY_INSPECTION_RESULT + " VARCHAR(50),"
            + SmartDCRConstants.KEY_INSPECTION_SEVERITY_STATUS + " VARCHAR(150),"
            + SmartDCRConstants.KEY_INSPECTION_SUBMISSIONTYPE + " VARCHAR(50),"
            + SmartDCRConstants.KEY_INSPECTION_IMAGE + " BLOB,"
            + SmartDCRConstants.KEY_INSPECTION_CREATE_DATE + " INTEGER,"
            + SmartDCRConstants.KEY_INSPECTION_MODIFIED_DATE + " INTEGER,"
            + SmartDCRConstants.KEY_INSPECTION_CREATED_BY + " VARCHAR(150),"
            + SmartDCRConstants.KEY_INSPECTION_MODIFIED_BY + " VARCHAR(150)" + " ) ";


    private final String CREATE_INSPECTED_TABLE = "CREATE TABLE " + TABLE_SITE_INSPECTED + "("
            + SmartDCRConstants.KEY_INSPECTED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL,"
            + SmartDCRConstants.KEY_INEPECTED_UNIQUE_ID + " VARCHAR(100),"
            + SmartDCRConstants.KEY_INSPECTED_FILENUMBER + " VARCHAR(255),"
            + SmartDCRConstants.KEY_INSPECTED_SECTION + " VARCHAR(50),"
            + SmartDCRConstants.KEY_INSPECTED_PARAMETER + " VARCHAR(50),"
            + SmartDCRConstants.KEY_INSPECTED_MIN + " VARCHAR(50),"
            + SmartDCRConstants.KEY_INSPECTED_MAX + " VARCHAR(50),"
            + SmartDCRConstants.KEY_INSPECTED_ACTUAL + " VARCHAR(50),"
            + SmartDCRConstants.KEY_INSPECTED_MEASURED + " VARCHAR(50),"
            + SmartDCRConstants.KEY_INSPECTED_VIOLATION + " VARCHAR(50),"
            + SmartDCRConstants.KEY_INSPECTED_VIOLATION_PERCENTAGE + " VARCHAR(50),"
            + SmartDCRConstants.KEY_INSPECTED_RESULT + " VARCHAR(50),"
            + SmartDCRConstants.KEY_INSPECTED_SEVERITY_STATUS + " VARCHAR(150),"
            + SmartDCRConstants.KEY_INSPECTED_SERVER_SYNC + " VARCHAR(10),"
            + SmartDCRConstants.KEY_INSPECTED_SUBMISSIONTYPE + " VARCHAR(50),"
            + SmartDCRConstants.KEY_INSPECTED_CREATE_DATE + " INTEGER,"
            + SmartDCRConstants.KEY_INSPECTED_MODIFIED_DATE + " INTEGER,"
            + SmartDCRConstants.KEY_INSPECTED_CREATED_BY + " VARCHAR(150),"
            + SmartDCRConstants.KEY_INSPECTED_MODIFIED_BY + " VARCHAR(150)" + " ) ";


    private final String CREATE_SITE_IMAGE = "CREATE TABLE " + TABLE_SITE_IMAGE + "("
            + SmartDCRConstants.KEY_INSPECTED_IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL,"
            + SmartDCRConstants.KEY_INSPECTED_IMAGE_INSPECTION_UNIQ_ID + " VARCHAR(100),"
            + SmartDCRConstants.KEY_INSPECTED_IMAGE_PATH + " VARCHAR(255),"
            + SmartDCRConstants.KEY_INSPECTED_IMAGE_NAME + " VARCHAR(150),"
            + SmartDCRConstants.KEY_INSPECTED_IMAGE_SYNC + " VARCHAR(10),"
            + SmartDCRConstants.KEY_INSPECTED_IMAGE_PARAMETER + " VARCHAR(50),"
            + SmartDCRConstants.KEY_INSPECTED_IMAGE_CREATE_DATE + " INTEGER,"
            + SmartDCRConstants.KEY_INSPECTED_IMAGE_MODIFIED_DATE + " INTEGER,"
            + SmartDCRConstants.KEY_INSPECTED_IMAGE_CREATED_BY + " VARCHAR(150),"
            + SmartDCRConstants.KEY_INSPECTED_IMAGE_MODIFIED_BY + " VARCHAR(150)" + " ) ";





    // Users table with username and passwordHash
//    private final String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
//            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL," + SmartDCRConstants.KEY_USERS_NAME + " VARCHAR(150),"
//            + SmartDCRConstants.KEY_USERS_PASS_HASH + " VARCHAR(150)," + SmartDCRConstants.KEY_USERS_FPS_ID + " VARCHAR(30), " + SmartDCRConstants.KEY_USERS_PROFILE + " VARCHAR(150)," + SmartDCRConstants.KEY_USERS_CREATE_DATE + " INTEGER," + SmartDCRConstants.KEY_USERS_MODIFIED_DATE + " INTEGER," + SmartDCRConstants.KEY_USERS_CREATED_BY + " VARCHAR(150), " + SmartDCRConstants.KEY_USERS_MODIFIED_BY + " VARCHAR(150)" + " )";
//
//    // Products  table with unique product name and unique product code
//    private final String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
//            + KEY_ID + " INTEGER PRIMARY KEY," + SmartDCRConstants.KEY_PRODUCT_NAME + " VARCHAR(150),"
//            + SmartDCRConstants.KEY_PRODUCT_PRICE + " DOUBLE," + SmartDCRConstants.KEY_PRODUCT_UNIT + " VARCHAR(150),"
//            + SmartDCRConstants.KEY_PRODUCT_CODE + " VARCHAR(150),"  + SmartDCRConstants.KEY_LPRODUCT_UNIT + " VARCHAR(150),"  + SmartDCRConstants.KEY_LPRODUCT_NAME + " VARCHAR(250)," + SmartDCRConstants.KEY_NEGATIVE_INDICATOR + " INTEGER," + SmartDCRConstants.KEY_PRODUCT_MODIFIED_DATE + " INTEGER," + SmartDCRConstants.KEY_MODIFIED_BY + " VARCHAR(150)," + SmartDCRConstants.KEY_CREATED_DATE + " INTEGER," + SmartDCRConstants.KEY_CREATED_BY + " VARCHAR(150)" + ")";
//
//
//    // Beneficiary  table with unique UFC code, FPS id ,QRCode  and unique mobile number
//    private final String CREATE_BENEFICIARY_TABLE = "CREATE TABLE " + TABLE_BENEFICIARY + "("
//            + KEY_ID + " INTEGER PRIMARY KEY," + SmartDCRConstants.KEY_BENEFICIARY_UFC + " INTEGER NOT NULL UNIQUE,"
//            + SmartDCRConstants.KEY_BENEFICIARY_TIN + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_FPS_ID + " INTEGER NOT NULL," + SmartDCRConstants.KEY_BENEFICIARY_QR_CODE + " INTEGER NOT NULL UNIQUE,"
//            + SmartDCRConstants.KEY_BENEFICIARY_MOBILE + " VARCHAR(20) NOT NULL UNIQUE ," + SmartDCRConstants.KEY_BENEFICIARY_VILLAGE_ID + " INTEGER," + SmartDCRConstants.KEY_BENEFICIARY_TALUK_ID + " INTEGER," + SmartDCRConstants.KEY_BENEFICIARY_DISTRICT_ID + " INTEGER," + SmartDCRConstants.KEY_BENEFICIARY_CARD_TYPE_ID + " VARCHAR(10)," + SmartDCRConstants.KEY_BENEFICIARY_STATE_ID + " INTEGER," + SmartDCRConstants.KEY_BENEFICIARY_CREATED_DATE + " INTEGER," + SmartDCRConstants.KEY_BENEFICIARY_MODIFIED_BY + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MODIFIED_DATE + " INTEGER," + SmartDCRConstants.KEY_BENEFICIARY_ACTIVE + " INTEGER " + ")";
//
//    // Beneficiary MEMBER
//    private final String CREATE_BENEFICIARY_MEMBER_TABLE = "CREATE TABLE " + TABLE_BENEFICIARY_MEMBER + "("
//            + KEY_ID + " INTEGER PRIMARY KEY," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_TIN + " VARCHAR(150), " + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_UID + " VARCHAR(150),"
//            + SmartDCRConstants.KEY_BENEFICIARY_QR_CODE + " VARCHAR(100),"
//
//            + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_EID + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_FIRST_NAME + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_MIDDLE_NAME + " VARCHAR(150),"
//
//            + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_LAST_NAME + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_FATHER_NAME + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_MOTHER_NAME + " VARCHAR(150),"
//
//            + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_GENDER + " VARCHAR(1)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_PERMANENT_ADDRESS + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_TEMP_ADDRESS + " VARCHAR(150),"
//
//            + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_CREATED_BY + " VARCHAR(150), " + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_CREATED_DATE + " INTEGER," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_MODIFIED_BY + " VARCHAR(150),"
//
//            + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_MODIFIED_DATE + " INTEGER," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_NAME + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_RESIDENT_ID + " VARCHAR(1),"
//
//            + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_REL_NAME + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_GENDER_ID + " VARCHAR(1)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_DOB + " VARCHAR(30) ,"
//
//            + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_DOB_TYPE + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_STATUS_ID + " VARCHAR(1)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_EDU_NAME + " VARCHAR(150),"
//
//            + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_OCCUPATION_NAME + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_FATHER_CODE + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_FATHER_NM + " VARCHAR(150),"
//
//            + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_MOTHER_CODE + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_MOTHER_NM + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_SPOUSE_CODE + " VARCHAR(150),"
//
//            + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_SPOUSE_NM + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_NAT_NAME + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_ADDRESS_LINE_1 + " VARCHAR(150),"
//
//            + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_ADDRESS_LINE_2 + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_ADDRESS_LINE_3 + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_ADDRESS_LINE_4 + " VARCHAR(150),"
//
//            + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_ADDRESS_LINE_5 + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_PIN_CODE + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_DURATION_IN_YEAR + " VARCHAR(150),"
//
//            + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_P_ADDRESS_LINE_1 + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_P_ADDRESS_LINE_2 + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_P_ADDRESS_LINE_3 + " VARCHAR(150),"
//
//            + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_P_ADDRESS_LINE_4 + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_P_ADDRESS_LINE_5 + " VARCHAR(150)," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_P_PIN_CODE + " VARCHAR(150),"
//
//            + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_DATE_DATA_ENTERED + " INTEGER," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_ALIVE_STATUS + " INTEGER ," + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_IS_ADULT + " INTEGER " + ")";
//
//
//    // Beneficiary  Activation Table
//    private final String CREATE_BENEFICIARY_ACTIVATION_TABLE = "CREATE TABLE " + TABLE_BENEFICIARY_ACTIVATION + "("
//            + KEY_ID + " INTEGER PRIMARY KEY," + SmartDCRConstants.KEY_BENEFICIARY_ACTIVATION_UFC + " VARCHAR(150) NOT NULL UNIQUE," + SmartDCRConstants.KEY_BENEFICIARY_ACTIVATION_OLD_RATION_CARD_NO + " INTEGER NOT NULL UNIQUE,"
//            + SmartDCRConstants.KEY_BENEFICIARY_ACTIVATION_CARD_TYPE + " VARCHAR(1)," + SmartDCRConstants.KEY_BENEFICIARY_ACTIVATION_NO_OF_CYLINDER + " INTEGER NOT NULL," + SmartDCRConstants.KEY_BENEFICIARY_ACTIVATION_RMN + "  VARCHAR(20) NOT NULL ,"
//            + SmartDCRConstants.KEY_BENEFICIARY_ACTIVATION_IS_SUBMITTED + " INTEGER," + SmartDCRConstants.KEY_BENEFICIARY_ACTIVATION_CREATED_BY + " INTEGER," + SmartDCRConstants.KEY_BENEFICIARY_ACTIVATION_MODIFIED_BY + " INTEGER" + ")";
//
//
//    // card type table with card types
//    private final String CREATE_CARD_TABLE = "CREATE TABLE " + TABLE_CARD_TYPE + "("
//            + KEY_ID + " INTEGER PRIMARY KEY,"
//            + SmartDCRConstants.KEY_CARD_TYPE + " VARCHAR(1)," + SmartDCRConstants.KEY_CARD_DESCRIPTION + " VARCHAR(150) " + " )";
//
//
//    // Bill item  table with unique bill item id, Quantity ,bill item cost  and transmitted ir not
//    private final String CREATE_BILL_ITEM_TABLE = "CREATE TABLE " + TABLE_BILL_ITEM + "("
//            + KEY_ID + " INTEGER  ," + SmartDCRConstants.KEY_BILL_ID + " INTEGER,"
//            + SmartDCRConstants.KEY_BILL_ITEM_BILL_REF + " INTEGER," + SmartDCRConstants.KEY_BILL_ITEM_DATE + " VARCHAR(150),"
//            + SmartDCRConstants.KEY_BILL_ITEM_PRODUCT_ID + " INTEGER," + SmartDCRConstants.KEY_BILL_TIME_MONTH + " INTEGER," + SmartDCRConstants.KEY_BILL_BENEFICIARY + " INTEGER," + SmartDCRConstants.KEY_BILL_ITEM_QUANTITY + " DOUBLE,"
//            + SmartDCRConstants.KEY_BILL_ITEM_COST + "  DOUBLE," + SmartDCRConstants.KEY_BILL_ITEM_IS_TRANSMIT + " INTEGER" + ")";
//
//
//    // Stock  table with unique bill item id, Quantity
//    private final String CREATE_STOCK_TABLE = "CREATE TABLE " + TABLE_STOCK + "("
//            + KEY_ID + " INTEGER," + SmartDCRConstants.KEY_STOCK_FPS_ID + " INTEGER UNIQUE,"
//            + SmartDCRConstants.KEY_STOCK_PRODUCT_ID + " INTEGER NOT NULL UNIQUE," + SmartDCRConstants.KEY_STOCK_QUANTITY + " DOUBLE, "
//            + SmartDCRConstants.KEY_STOCK_REORDER_LEVEL+" DOUBLE, "+ SmartDCRConstants.KEY_STOCK_EMAIL_ACTION +" INTEGER,"+ SmartDCRConstants.KEY_STOCK_SMSACTION + " INTEGER "+")";
//
//    // Stock  table with unique bill item id, Quantity
//    private final String CREATE_FPS_STOCK_INVARD_TABLE = "CREATE TABLE " + TABLE_FPS_STOCK_INVARD + "("
//            + SmartDCRConstants.KEY_FPS_STOCK_INWARD_GODOWNID+ " INTEGER ,"+ SmartDCRConstants.KEY_FPS_STOCK_INWARD_FPSID + " INTEGER,"+ SmartDCRConstants.KEY_FPS_STOCK_INWARD_OUTWARD_DATE+" INTEGER," + SmartDCRConstants.KEY_FPS_STOCK_INWARD_PRODUCTID +" INTEGER,"+ SmartDCRConstants.KEY_FPS_STOCK_INWARD_QUANTITY+" DOUBLE, "+ SmartDCRConstants.KEY_FPS_STOCK_INWARD_UNIT+" VARCHAR(150),"+ SmartDCRConstants.KEY_FPS_STOCK_INWARD_BATCH_NO +" INTEGER,"+ SmartDCRConstants.KEY_FPS_STOCK_INWARD_IS_FPSACKSTATUS + " INTEGER(1),"+
//            SmartDCRConstants.KEY_FPS_STOCK_INWARD_FPSACKDATE +" INTEGER," + SmartDCRConstants.KEY_FPS_STOCK_INWARD_FPSRECEIVEIQUANTITY + " DOUBLE,"+ SmartDCRConstants.KEY_FPS_STOCK_INWARD_CREATEDBY + " INTEGER,"+ SmartDCRConstants.KEY_FPS_STOCK_INWARD_DELIEVERY_CHELLANID+" INTEGER"+")";
//
//
//
//    // Store  table with unique goDown id
//    private final String CREATE_STORE_TABLE = "CREATE TABLE " + TABLE_STORE + "("
//            + KEY_ID + " INTEGER PRIMARY KEY," + SmartDCRConstants.KEY_STORE_GO_DOWN_ID + " VARCHAR(150) NOT NULL UNIQUE,"
//            + SmartDCRConstants.KEY_STORE_CODE + " VARCHAR(150)," + SmartDCRConstants.KEY_STORE_ACTIVE + " INTEGER," + SmartDCRConstants.KEY_STORE_CREATED_BY + " VARCHAR(150)," + SmartDCRConstants.KEY_STORE_MODIFIED_BY + " VARCHAR(150)," + SmartDCRConstants.KEY_STORE_CREATED_DATE + " INTEGER," + SmartDCRConstants.KEY_STORE_MODIFIED_DATE + " INTEGER" + ")";
//
//
//    // GoDown table with unique goDown_code
//    private final String CREATE_GO_DOWN_TABLE = "CREATE TABLE " + TABLE_GO_DOWN + "("
//            + KEY_ID + " INTEGER PRIMARY KEY," + SmartDCRConstants.KEY_GO_DOWN_GO_DOWN_CODE + " VARCHAR(150) UNIQUE," + SmartDCRConstants.KEY_GO_DOWN_NAME + " VARCHAR(150)," + SmartDCRConstants.KEY_GO_DOWN_DISTRICT + " VARCHAR(150)," + SmartDCRConstants.KEY_GO_DOWN_TALUK + " VARCHAR(150)," + SmartDCRConstants.KEY_GO_DOWN_CATEGORY_ID + " VARCHAR(150)," + SmartDCRConstants.KEY_GO_DOWN_CONTACT_PERSON_NAME + " VARCHAR(150)," + SmartDCRConstants.KEY_GO_DOWN_CONTACT_NUMBER + " VARCHAR(150),"
//            + SmartDCRConstants.KEY_GO_DOWN_PIN_CODE + " VARCHAR(150)," + SmartDCRConstants.KEY_GO_DOWN_ADDRESS + " VARCHAR(150)," + SmartDCRConstants.KEY_GO_DOWN_IS_STATUS + " INTEGER," + SmartDCRConstants.KEY_GO_DOWN_CREATED_DATE + " INTEGER," + SmartDCRConstants.KEY_GO_DOWN_MODIFIED_DATE + " INTEGER," + SmartDCRConstants.KEY_GO_DOWN_CREATED_BY + " VARCHAR(150)," + SmartDCRConstants.KEY_GO_DOWN_MODIFIED_BY + " VARCHAR(150)" + ")";
//
//
//    // Beneficiary  table with unique UFC code, FPS id ,QRCode  and unique mobile number
//    private final String CREATE_BILL_TABLE = "CREATE TABLE " + TABLE_BILL + "("
//            + SmartDCRConstants.KEY_BILL_REF_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL," + KEY_ID + " INTEGER," + SmartDCRConstants.KEY_BILL_FPS_ID + " INTEGER ,"
//            + SmartDCRConstants.KEY_BILL_DATE + " INTEGER,transactionId VARCHAR(150)," + SmartDCRConstants.KEY_BILL_MODE + " VARCHAR(1) NOT NULL," + SmartDCRConstants.KEY_BENEFICIARY_QR_CODE + " VARCHAR(100),"
//            + SmartDCRConstants.KEY_BILL_CHANNEL + " VARCHAR(1) NOT NULL," + SmartDCRConstants.KEY_BILL_STATUS + " VARCHAR(1) NOT NULL," + SmartDCRConstants.KEY_BILL_BENEFICIARY + " INTEGER,"
//            + SmartDCRConstants.KEY_BILL_AMOUNT + " DOUBLE," + SmartDCRConstants.KEY_BILL_TIME_STAMP + " INTEGER," + SmartDCRConstants.KEY_BILL_TIME_MONTH + " INTEGER," + SmartDCRConstants.KEY_BILL_CREATED_BY + " VARCHAR(150)," + SmartDCRConstants.KEY_BILL_CREATED_DATE + " INTEGER " + ")";
//
//
//    // Allotment  table with unique id, product id ,card type
//    private final String CREATE_ALLOTMENT_TABLE = "CREATE TABLE " + TABLE_ALLOTMENT + "("
//            + KEY_ID + " INTEGER PRIMARY KEY," + SmartDCRConstants.KEY_ALLOTMENT_PRODUCT_ID + " INTEGER,"
//            + SmartDCRConstants.KEY_ALLOTMENT_CARD_TYPE + " VARCHAR(150)," + SmartDCRConstants.KEY_ALLOTMENT_LIMIT + " VARCHAR(150),"
//            + SmartDCRConstants.KEY_ALLOTMENT_DISTRICT + " INTEGER," + SmartDCRConstants.KEY_CALC_ALLOTMENT_ALLOTMENT + " INTEGER, " + SmartDCRConstants.KEY_ALLOTMENT_AGE_GROUP_ID + " VARCHAR(150), " + SmartDCRConstants.KEY_ALLOTMENT_CREATED_BY + " VARCHAR(150)," + SmartDCRConstants.KEY_ALLOTMENT_MODIFIED_BY + " VARCHAR(150)," + SmartDCRConstants.KEY_ALLOTMENT_CREATE_DATE + " INTEGER," + SmartDCRConstants.KEY_ALLOTMENT_MODIFIED_DATE + " INTEGER " + ")";
//
//
//    // Age group table
//    private final String CREATE_AGE_GROUP_TABLE = "CREATE TABLE " + TABLE_AGE_GROUP + "("
//            + KEY_ID + " INTEGER PRIMARY KEY," + SmartDCRConstants.KEY_AGE_NAME + " VARCHAR(150),"
//            + SmartDCRConstants.KEY_AGE_PRODUCT_ID + " INTEGER,"
//            + SmartDCRConstants.KEY_AGE_FROM + " INTEGER," + SmartDCRConstants.KEY_AGE_TO + " INTEGER," + SmartDCRConstants.KEY_AGE_QUANTITY + " INTEGER"
//            + ")";
//
//
//    // Allotment  table with unique id, product id ,card type
//    private final String CREATE_ALLOTMENT_MAPPING_TABLE = "CREATE TABLE " + TABLE_ALLOTMENT_MAPPING + "("
//            + KEY_ID + " INTEGER PRIMARY KEY," + SmartDCRConstants.KEY_ALLOTMENT_MAP_MAP_ID + " VARCHAR(150),"
//            + SmartDCRConstants.KEY_ALLOTMENT_MAP_START + " DOUBLE," + SmartDCRConstants.KEY_ALLOTMENT_MAP_END + " DOUBLE,"
//            + SmartDCRConstants.KEY_ALLOTMENT_MAP_ALLOT + "  DOUBLE," + SmartDCRConstants.KEY_ALLOTMENT_MAP_DISTRICT + " INTEGER," + SmartDCRConstants.KEY_ALLOTMENT_MAP_DESCRIPTION + "  VARCHAR(150)," + SmartDCRConstants.KEY_ALLOTMENT_MAP_PRODUCT_ID + " INTEGER" + ")";
//
//
//    private final String CREATE_TABLE_LANGUAGE = "CREATE TABLE " + TABLE_LANGUAGE + "("
//            + KEY_ID + " INTEGER  PRIMARY KEY," + SmartDCRConstants.KEY_LANGUAGE_CODE + " INTEGER,"
//            + SmartDCRConstants.KEY_LANGUAGE_ID + "  VARCHAR(30)," + SmartDCRConstants.KEY_LANGUAGE_MESSAGE + " VARCHAR(1000)"
//            + ")";
//
//
//    private final String CREATE_TABLE_TRANSACTION_TYPE = "CREATE TABLE " + TABLE_TRANSACTION_TYPE + "("
//            + KEY_ID + " INTEGER  PRIMARY KEY," + SmartDCRConstants.KEY_TRANSACTION_TYPE_TXN_TYPE + " INTEGER UNIQUE,"
//            + SmartDCRConstants.KEY_TRANSACTION_TYPE_STATUS + "  INTEGER," + SmartDCRConstants.KEY_TRANSACTION_TYPE_DESCRIPTION + " VARCHAR(150),"
//            + SmartDCRConstants.KEY_TRANSACTION_TYPE_CREATED_BY + " INTEGER, " + SmartDCRConstants.KEY_TRANSACTION_TYPE_CREATED_DATE + " VARCHAR(30),"
//            + SmartDCRConstants.KEY_TRANSACTION_TYPE_LAST_MODIFIED_TIME + " VARCHAR(30)" + ")";


    private SmartDCRDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        database = this.getWritableDatabase();
        dbHelper = this;
    }


    //Singleton to Instantiate the SQLiteOpenHelper
    public static SmartDCRDBHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new SmartDCRDBHelper(context);
            openConnection();
        }
        contextValue = context;
        return dbHelper;
    }

    // It is used to open database
    private static void openConnection() {
        if (database == null) {
            database = dbHelper.getWritableDatabase();

        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        Log.e("Inside DB", "DB Creation");
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_SITE_TABLE);
        db.execSQL(CREATE_INSPECTION_TABLE);
        db.execSQL(CREATE_INSPECTED_TABLE);
        db.execSQL(CREATE_SITE_IMAGE);


//        db.execSQL(CREATE_PRODUCTS_TABLE);
//        db.execSQL(CREATE_BENEFICIARY_TABLE);
//        db.execSQL(CREATE_BENEFICIARY_MEMBER_TABLE);
//        db.execSQL(CREATE_BENEFICIARY_ACTIVATION_TABLE);
//        db.execSQL(CREATE_CARD_TABLE);
//        db.execSQL(CREATE_BILL_ITEM_TABLE);
//        db.execSQL(CREATE_STOCK_TABLE);
//        db.execSQL(CREATE_STORE_TABLE);
//        db.execSQL(CREATE_GO_DOWN_TABLE);
//        db.execSQL(CREATE_BILL_TABLE);
//        db.execSQL(CREATE_ALLOTMENT_TABLE);
//        db.execSQL(CREATE_AGE_GROUP_TABLE);
//        db.execSQL(CREATE_ALLOTMENT_MAPPING_TABLE);
//        db.execSQL(CREATE_TABLE_LANGUAGE);
//        db.execSQL(CREATE_TABLE_TRANSACTION_TYPE);
//        db.execSQL(CREATE_FPS_STOCK_INVARD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

       // db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public void insertInspectionImage(String inspectionId,String imageFilePath,String imageFileName,String parameter){

        ContentValues values = new ContentValues();
        values.put(SmartDCRConstants.KEY_INSPECTED_IMAGE_INSPECTION_UNIQ_ID,inspectionId);
        values.put(SmartDCRConstants.KEY_INSPECTED_IMAGE_PATH,imageFilePath);
        values.put(SmartDCRConstants.KEY_INSPECTED_IMAGE_NAME,imageFileName);
        values.put(SmartDCRConstants.KEY_INSPECTED_IMAGE_PARAMETER,parameter);
        values.put(SmartDCRConstants.KEY_INSPECTED_IMAGE_SYNC,"no");
        database.insert(TABLE_SITE_IMAGE, null, values);
    }

    public void insertServerSyncInspectionImage(String inspectionId,String imageFilePath,String imageFileName,String parameter){

        ContentValues values = new ContentValues();
        values.put(SmartDCRConstants.KEY_INSPECTED_IMAGE_INSPECTION_UNIQ_ID,inspectionId);
        values.put(SmartDCRConstants.KEY_INSPECTED_IMAGE_PATH,imageFilePath);
        values.put(SmartDCRConstants.KEY_INSPECTED_IMAGE_NAME,imageFileName);
        values.put(SmartDCRConstants.KEY_INSPECTED_IMAGE_PARAMETER,parameter);
        values.put(SmartDCRConstants.KEY_INSPECTED_IMAGE_SYNC,"yes");
        database.insert(TABLE_SITE_IMAGE,null,values);
    }

    public void insertSiteInspection(String iD,String uniqId,String fileNumber,String section,String parameter,String min,String max,
                                     String actual,String measured,String violation,String violationPersentage,String result,
                                     String severityStatus,String submissionType,byte [] imageByte){

        ContentValues values = new ContentValues();
        values.put(SmartDCRConstants.KEY_INSPECTION_ID, iD);
        values.put(SmartDCRConstants.KEY_INEPECTION_UNIQUE_ID, uniqId);
        values.put(SmartDCRConstants.KEY_INSPECTION_FILENUMBER, fileNumber);

        values.put(SmartDCRConstants.KEY_INSPECTION_SECTION, section);
        values.put(SmartDCRConstants.KEY_INSPECTION_PARAMETER, parameter);
        values.put(SmartDCRConstants.KEY_INSPECTION_MIN, min);

        values.put(SmartDCRConstants.KEY_INSPECTION_MAX, max);
        values.put(SmartDCRConstants.KEY_INSPECTION_ACTUAL, actual);
        values.put(SmartDCRConstants.KEY_INSPECTION_MEASURED, measured);

        values.put(SmartDCRConstants.KEY_INSPECTION_VIOLATION, violation);
        values.put(SmartDCRConstants.KEY_INSPECTION_VIOLATION_PERCENTAGE, violationPersentage);
        values.put(SmartDCRConstants.KEY_INSPECTION_RESULT, result);

        values.put(SmartDCRConstants.KEY_INSPECTION_SEVERITY_STATUS, severityStatus);
        values.put(SmartDCRConstants.KEY_INSPECTION_SUBMISSIONTYPE, submissionType);

        values.put(SmartDCRConstants.KEY_INSPECTION_SUBMISSIONTYPE, submissionType);

        values.put(SmartDCRConstants.KEY_INSPECTION_IMAGE,imageByte);



//        ImageView image = new ImageView(this);
//
//        Bitmap bmp = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
//        // ImageView image = (ImageView) findViewById(R.id.imageView1);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);
//        image.setLayoutParams(layoutParams);
//
//        image.setImageBitmap(bmp);
//
//        //  image.setImageResource(R.drawable.YOUR_IMAGE_ID);
//
//        AlertDialog.Builder builder =
//                new AlertDialog.Builder(this).
//                        setMessage("Cuptured image").
//                        setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        }).
//                        setView(image);
//        builder.create().show();


        database.insert(TABLE_SITE_INSPECTION, null, values);
    //    showInspection();
    }

    public void updateSiteInspection(String iD,String uniqId,String fileNumber,String section,String parameter,String min,String max,
                                     String actual,String measured,String violation,String violationPersentage,String result,
                                     String severityStatus,String submissionType,byte[] imageByte){



        ContentValues values = new ContentValues();
        values.put(SmartDCRConstants.KEY_INSPECTION_ID, iD);
        values.put(SmartDCRConstants.KEY_INEPECTION_UNIQUE_ID, uniqId);
        values.put(SmartDCRConstants.KEY_INSPECTION_FILENUMBER, fileNumber);

        values.put(SmartDCRConstants.KEY_INSPECTION_SECTION, section);
        values.put(SmartDCRConstants.KEY_INSPECTION_PARAMETER, parameter);
        values.put(SmartDCRConstants.KEY_INSPECTION_MIN, min);

        values.put(SmartDCRConstants.KEY_INSPECTION_MAX, max);
        values.put(SmartDCRConstants.KEY_INSPECTION_ACTUAL, actual);
        values.put(SmartDCRConstants.KEY_INSPECTION_MEASURED, measured);

        values.put(SmartDCRConstants.KEY_INSPECTION_VIOLATION, violation);
        values.put(SmartDCRConstants.KEY_INSPECTION_VIOLATION_PERCENTAGE, violationPersentage);
        values.put(SmartDCRConstants.KEY_INSPECTION_RESULT, result);

        values.put(SmartDCRConstants.KEY_INSPECTION_SEVERITY_STATUS, severityStatus);
        values.put(SmartDCRConstants.KEY_INSPECTION_SUBMISSIONTYPE, submissionType);

        values.put(SmartDCRConstants.KEY_INSPECTION_SUBMISSIONTYPE, submissionType);

        values.put(SmartDCRConstants.KEY_INSPECTION_IMAGE, imageByte);

        Log.e("uniqId===", uniqId);
        if(imageByte!=null) {
        //    Log.e("imageByte===", imageByte.toString());
        }


        database.update(TABLE_SITE_INSPECTION, values, SmartDCRConstants.KEY_INEPECTION_UNIQUE_ID + "= '" + uniqId + "' AND " +
                SmartDCRConstants.KEY_INSPECTION_PARAMETER + "= '" + parameter + "'", null);

        Log.e("site ins update===", "site ins update===");

        //database.insert(TABLE_SITE_INSPECTION, null, values);

    }



    public void updateServerSyncInspection(String parameter,String severity,String measured,String inspectionid){

        ContentValues values = new ContentValues();
        values.put(SmartDCRConstants.KEY_INSPECTED_ID,inspectionid);
        values.put(SmartDCRConstants.KEY_INSPECTED_PARAMETER,parameter);
        values.put(SmartDCRConstants.KEY_INSPECTED_MEASURED,measured);
        values.put(SmartDCRConstants.KEY_INSPECTED_SEVERITY_STATUS,severity);
        values.put(SmartDCRConstants.KEY_INSPECTED_SERVER_SYNC,"yes");
        database.insert(TABLE_SITE_INSPECTED, null, values);
        Log.e("Update", "update");
    }


    public String getImagepath(String uniqId,String parameter){

        String filePath=null;

        String selectQuery = "SELECT  * FROM " + TABLE_SITE_IMAGE+" where "+SmartDCRConstants.KEY_INSPECTED_IMAGE_INSPECTION_UNIQ_ID +" = '"+uniqId+"' AND "+
                SmartDCRConstants.KEY_INSPECTED_IMAGE_PARAMETER+"= '"+parameter+"'";
        Cursor cursor = database.rawQuery(selectQuery,null);


        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {

            filePath=cursor.getString(cursor.getColumnIndex(SmartDCRConstants.KEY_INSPECTED_IMAGE_PATH));


            cursor.moveToNext();
        }

        cursor.close();


//        values.put(SmartDCRConstants.KEY_INSPECTED_IMAGE_INSPECTION_UNIQ_ID,inspectionId);
//        values.put(SmartDCRConstants.KEY_INSPECTED_IMAGE_PATH,imageFilePath);
//        values.put(SmartDCRConstants.KEY_INSPECTED_IMAGE_NAME,imageFileName);
//        values.put(SmartDCRConstants.KEY_INSPECTED_IMAGE_PARAMETER,parameter);
//        values.put(SmartDCRConstants.KEY_INSPECTED_IMAGE_SYNC,"no");
//

       return filePath;
    }

    public void updateServerSyncInspection(String parameter,String severity,String measured,String inspectionid,String uniqId){

    try {
    Log.d("parameter1--", parameter);
    Log.d("severity1--", severity);
    Log.d("measured1--", measured);
    Log.d("inspectionId1--", inspectionid);
    Log.d("uniqId1--", uniqId);

    ContentValues values = new ContentValues();
    values.put(SmartDCRConstants.KEY_INSPECTED_ID, inspectionid);
    values.put(SmartDCRConstants.KEY_INSPECTED_PARAMETER, parameter);
    values.put(SmartDCRConstants.KEY_INSPECTED_MEASURED, measured);
    values.put(SmartDCRConstants.KEY_INSPECTED_SEVERITY_STATUS, severity);
    values.put(SmartDCRConstants.KEY_INSPECTED_SERVER_SYNC, "yes");
    //database.insert(TABLE_SITE_INSPECTED, null, values);
    database.update(TABLE_SITE_INSPECTED, values, SmartDCRConstants.KEY_INEPECTED_UNIQUE_ID + " = '" + uniqId + "' AND "+
            SmartDCRConstants.KEY_INSPECTED_PARAMETER + " = '"+parameter+"'", null);

        showImage();

    Log.e("Update", "update");
    }
    catch (Exception e){
    Log.e("db err",e.toString());

    }

    }

    private void showImage(){


        String selectQuery = "SELECT  * FROM " + TABLE_SITE_INSPECTED+" where "
                +SmartDCRConstants.KEY_INSPECTED_SERVER_SYNC +" = 'no'";
        Cursor cursor = database.rawQuery(selectQuery, null);



    }


    public void updateInspection(String parameter,String severity,String measured,String inspectionid){

        ContentValues values = new ContentValues();
        values.put(SmartDCRConstants.KEY_INSPECTED_ID,inspectionid);
        values.put(SmartDCRConstants.KEY_INSPECTED_PARAMETER,parameter);
        values.put(SmartDCRConstants.KEY_INSPECTED_MEASURED,measured);
        values.put(SmartDCRConstants.KEY_INSPECTED_SEVERITY_STATUS,severity);
        values.put(SmartDCRConstants.KEY_INSPECTED_SERVER_SYNC,"no");
        database.insert(TABLE_SITE_INSPECTED, null, values);
        Log.e("Update","update");
    }

    public void updateInspection(String parameter,String severity,String measured,String inspectionid,String uniqId){

        ContentValues values = new ContentValues();
        values.put(SmartDCRConstants.KEY_INSPECTED_ID,inspectionid);
        values.put(SmartDCRConstants.KEY_INSPECTED_PARAMETER,parameter);
        values.put(SmartDCRConstants.KEY_INSPECTED_MEASURED,measured);
        values.put(SmartDCRConstants.KEY_INSPECTED_SEVERITY_STATUS,severity);
        values.put(SmartDCRConstants.KEY_INSPECTED_SERVER_SYNC,"no");
        database.update(TABLE_SITE_INSPECTED, values, SmartDCRConstants.KEY_INEPECTED_UNIQUE_ID + " = '" + uniqId + "' AND "+
                SmartDCRConstants.KEY_INSPECTED_PARAMETER + " = '"+parameter+"'", null);
        Log.e("Update2", "update2");
    }

    public Cursor inspectedSiteData(){

        String selectQuery = "SELECT  * FROM " + TABLE_SITE_INSPECTED+" where "
                +SmartDCRConstants.KEY_INSPECTED_SERVER_SYNC +" = 'no'";
        Cursor cursor = database.rawQuery(selectQuery, null);

    return cursor;
    }

    public void insertSiteData(String uniqId ,String status){

        ContentValues values = new ContentValues();
        values.put(SmartDCRConstants.KEY_SITE_UNIQ_ID, uniqId);
      //  values.put(SmartDCRConstants.KEY_SITE_DRAWING, drawing);
        values.put(SmartDCRConstants.KEY_SITE_STATUS, status);
        database.insert(TABLE_SITE_DATA, null, values);
        showData();
    }

    public void updateInspectedData(String inspectionId){

        ContentValues values = new ContentValues();
        values.put(SmartDCRConstants.KEY_INSPECTED_SERVER_SYNC, "yes");

        database.update(TABLE_SITE_INSPECTED, values, SmartDCRConstants.KEY_INSPECTED_ID + "='" + inspectionId + "'", null);
    }

    public Cursor getData(String parameter,String uniqId){

        String selectQuery = "SELECT  * FROM " + TABLE_SITE_INSPECTION+" where "
                +SmartDCRConstants.KEY_INEPECTION_UNIQUE_ID +" = '"+uniqId+"' AND "
                +SmartDCRConstants.KEY_INSPECTION_PARAMETER +" = '"+parameter+"'";
        Cursor cursor = database.rawQuery(selectQuery, null);
//        cursor.moveToFirst();
//        int count=cursor.getCount();
//
//        if (cursor.moveToFirst()) {
//            do {
//
//
//
//
//            } while (cursor.moveToNext());
//        }
//        cursor.close();

      return cursor;
    }

    public int parameterisPresent(String uniqId,String parameter){

        String selectQuery = "SELECT * FROM " + TABLE_SITE_INSPECTION+" WHERE "+SmartDCRConstants.KEY_INEPECTION_UNIQUE_ID +" = '"+uniqId+"' AND "+
                SmartDCRConstants.KEY_INSPECTION_PARAMETER + " = '"+parameter+"'";

        Cursor cursor = database.rawQuery(selectQuery,null);
        int count = cursor.getCount();

        return count;
    }



    public List<InspectionListByUniqId> showInspectionByUniqid(String uniqId) {


        List<InspectionListByUniqId> inspectionListByUniqIds=new ArrayList<InspectionListByUniqId>();
        InspectionListByUniqId inspectionListByUniqId;

        //String selectQuery = "SELECT  * FROM  " + TABLE_INSPECTION_GODOWN +" where "+InspectionConstants.KEY_GODOWN_TALUK_ID +" = "+talukId ;

        String selectQuery = "SELECT  * FROM " + TABLE_SITE_INSPECTION+" where "+SmartDCRConstants.KEY_INEPECTION_UNIQUE_ID +" = '"+uniqId+"'";
        Cursor cursor = database.rawQuery(selectQuery,null);


        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            inspectionListByUniqId=new InspectionListByUniqId(cursor);
            inspectionListByUniqIds.add(inspectionListByUniqId);
            //    Log.e("inspection Act",inspectionDataDtoArrayList+"");
            cursor.moveToNext();
        }

        cursor.close();

        return inspectionListByUniqIds;
    }


    public List showInspectionById(String uniqId) {
        List parameterList=new ArrayList();

        //String selectQuery = "SELECT  * FROM  " + TABLE_INSPECTION_GODOWN +" where "+InspectionConstants.KEY_GODOWN_TALUK_ID +" = "+talukId ;

        String selectQuery = "SELECT  * FROM " + TABLE_SITE_INSPECTION+" where "+SmartDCRConstants.KEY_INEPECTION_UNIQUE_ID +" = '"+uniqId+"'";
        Cursor cursor = database.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        int count=cursor.getCount();
        Log.e("evidence count", count + "");
        if (cursor.moveToFirst()) {
            do {
                Log.e(" ### uniq id=====", cursor.getString(cursor.getColumnIndex(SmartDCRConstants.KEY_INEPECTION_UNIQUE_ID)));
                Log.e(" ### status =====", cursor.getString(cursor.getColumnIndex(SmartDCRConstants.KEY_INSPECTION_ID)));

                String parameter=cursor.getString(cursor.getColumnIndex(SmartDCRConstants.KEY_INSPECTION_PARAMETER));

                parameterList.add(parameter);

            } while (cursor.moveToNext());
        }
        cursor.close();

        return parameterList;
    }

    public void showInspection() {

        String selectQuery = "SELECT  * FROM " + TABLE_SITE_INSPECTION;
        Cursor cursor = database.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        int count=cursor.getCount();
        Log.e("evidence count", count + "");
        if (cursor.moveToFirst()) {
            do {
                Log.e(" ### uniq id=====", cursor.getString(cursor.getColumnIndex(SmartDCRConstants.KEY_INEPECTION_UNIQUE_ID)));
                Log.e(" ### status =====", cursor.getString(cursor.getColumnIndex(SmartDCRConstants.KEY_INSPECTION_ID)));

            } while (cursor.moveToNext());
        }
        cursor.close();
    }


    public byte[] getPdfArray(String uniqId){

        byte[] bytePdf=null;

        String selectQuery = "SELECT  * FROM " + TABLE_SITE_DATA+" where "+SmartDCRConstants.KEY_SITE_UNIQ_ID +" = '"+uniqId+"'";
        Cursor cursor = database.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        int count=cursor.getCount();
        Log.e("evidence count--==++++", count + "");
        if (cursor.moveToFirst()) {
            do {

                bytePdf = cursor.getBlob(cursor.getColumnIndex(SmartDCRConstants.KEY_SITE_DRAWING));
                //okbytePdf =cursor.
//                Log.e(" ### uniq id=====", cursor.getString(cursor.getColumnIndex(SmartDCRConstants.KEY_SITE_UNIQ_ID)));
//                Log.e(" ### status =====", cursor.getString(cursor.getColumnIndex(SmartDCRConstants.KEY_SITE_STATUS)));
                //(assuming you have a ResultSet named RS)

               /* Blob blob = cursor.getBlob(cursor.getColumnIndex(SmartDCRConstants.KEY_SITE_DRAWING));

                int blobLength = (int) blob.length();
                byte[] blobAsBytes = blob.getBytes(1, blobLength);

//release the blob and free up memory. (since JDBC 4.0)
                blob.free();*/


            } while (cursor.moveToNext());
        }
        cursor.close();

       return bytePdf;

       //return blobAsBytes;
    }

    public byte[] getBlob(String uniqId){

        byte[] bytePdf=null;

        String selectQuery = "SELECT  * FROM " + TABLE_SITE_DATA+" where "+SmartDCRConstants.KEY_SITE_UNIQ_ID +" = '"+uniqId+"'";
        Cursor cursor = database.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        int count=cursor.getCount();
        Log.e("evidence count--==++++", count + "");
        if (cursor.moveToFirst()) {
            do {

                bytePdf = cursor.getBlob(cursor.getColumnIndex(SmartDCRConstants.KEY_SITE_DRAWING));
                //okbytePdf =cursor.
//                Log.e(" ### uniq id=====", cursor.getString(cursor.getColumnIndex(SmartDCRConstants.KEY_SITE_UNIQ_ID)));
//                Log.e(" ### status =====", cursor.getString(cursor.getColumnIndex(SmartDCRConstants.KEY_SITE_STATUS)));
                //(assuming you have a ResultSet named RS)

               /* Blob blob = cursor.getBlob(cursor.getColumnIndex(SmartDCRConstants.KEY_SITE_DRAWING));

                int blobLength = (int) blob.length();
                byte[] blobAsBytes = blob.getBytes(1, blobLength);

//release the blob and free up memory. (since JDBC 4.0)
                blob.free();*/


            } while (cursor.moveToNext());
        }
        cursor.close();

        return bytePdf;

        //return blobAsBytes;
    }

    public void showData() {

            String selectQuery = "SELECT  * FROM " + TABLE_SITE_DATA;
            Cursor cursor = database.rawQuery(selectQuery,null);
            cursor.moveToFirst();
            int count=cursor.getCount();
            Log.e("evidence count", count + "");
            if (cursor.moveToFirst()) {
                do {
                    Log.e(" ### uniq id=====", cursor.getString(cursor.getColumnIndex(SmartDCRConstants.KEY_SITE_UNIQ_ID)));
                    Log.e(" ### status =====", cursor.getString(cursor.getColumnIndex(SmartDCRConstants.KEY_SITE_STATUS)));



                } while (cursor.moveToNext());
            }
            cursor.close();
    }

    public List<InspectionDataDto> showList() {


        List<InspectionDataDto> inspectionDataDtoArrayList=new ArrayList<InspectionDataDto>();
        InspectionDataDto inspectionDataDto;
        String selectQuery = "SELECT  * FROM " + TABLE_SITE_DATA;
        Cursor cursor = database.rawQuery(selectQuery,null);

     //   int count=cursor.getCount();
     //   Log.e("evidence count",count+"");

        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            inspectionDataDto=new InspectionDataDto(cursor);
            inspectionDataDtoArrayList.add(inspectionDataDto);
        //    Log.e("inspection Act",inspectionDataDtoArrayList+"");
            cursor.moveToNext();
        }

        cursor.close();

    return inspectionDataDtoArrayList;
    }


    //insert login data inside database
//    public void insertData(LoginResponseDto loginResponse) {
//
//        ContentValues values = new ContentValues();
//        values.put(SmartDCRConstants.KEY_USERS_NAME, loginResponse.getUserDetailDto().getUsername());
//        values.put(SmartDCRConstants.KEY_USERS_PASS_HASH, loginResponse.getUserDetailDto().getPassword());
//        database.insertWithOnConflict(TABLE_USERS, SmartDCRConstants.KEY_USERS_NAME, values, SQLiteDatabase.CONFLICT_REPLACE);
//        Log.i("P", loginResponse.getUserDetailDto().getPassword());
//
//    }
//
//    public int showData() {
//        SQLiteDatabase database = this.getWritableDatabase();
//        String selectQuery = "SELECT  * FROM " + TABLE_USERS;
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        cursor.moveToFirst();
//        return cursor.getCount();
//    }
//
//    // Used to retrieve data when no network available in device
//    public String retrieveData(String userName) {
//        String selectQuery = "SELECT  * FROM " + TABLE_USERS + " where " + SmartDCRConstants.KEY_USERS_NAME + " = " + "\"" + userName + "\"";
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        LoginResponseDto loginResponse;
//        if (cursor.moveToFirst()) {
//            loginResponse = new LoginResponseDto(cursor);
//            loginResponse.setUserDetailDto(new UserDetailDto(cursor));
//            return loginResponse.getUserDetailDto().getPassword();
//        }
//        cursor.close();
//        return null;
//    }
//
//    //  This function returns true if  Bill ,Product ,Beneficiary  and Allotment tables are empty.
//    public boolean getBillProductBeneficiaryAllotData() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        return (db.rawQuery("SELECT * FROM " + TABLE_BILL, null).getCount()) == 0 && (db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS, null).getCount()) == 0 && (db.rawQuery("SELECT * FROM " + TABLE_BENEFICIARY, null).getCount()) == 0 && (db.rawQuery("SELECT * FROM " + TABLE_ALLOTMENT, null).getCount()) == 0 && (db.rawQuery("SELECT * FROM " + TABLE_TRANSACTION_TYPE, null).getCount()) == 0;
//    }
//
//    //This function inserts details to TABLE_BILL,;
//
//    public void insertBillData(FpsDataDto fpsDataDto) {
//        try {
//
//
//            List<BillDto> billList = new ArrayList<BillDto>(fpsDataDto.getBillDto());
//            for (BillDto bill : billList) {
//                ContentValues values = new ContentValues();
//                values.put(KEY_ID, bill.getId());
//                values.put(SmartDCRConstants.KEY_BILL_FPS_ID, bill.getFpsId());
//                values.put(SmartDCRConstants.KEY_BILL_DATE, bill.getBillDate());
//                values.put(SmartDCRConstants.KEY_BILL_CREATED_BY, bill.getCreatedby());
//                values.put(SmartDCRConstants.KEY_BILL_TIME_STAMP, bill.getBillDate());
//               // values.put("transactionId", bill.getTransactionId());
//                DateTime dates = new DateTime(Long.parseLong(bill.getBillDate()));
//                if (dates.getYear() == new DateTime().getYear()) {
//                    Log.e("Date", dates + ":" + dates.getYear() + ":" + dates.getMonthOfYear());
//                    values.put(SmartDCRConstants.KEY_BILL_TIME_MONTH, dates.getMonthOfYear());
//                }
//                values.put(SmartDCRConstants.KEY_BILL_AMOUNT, bill.getAmount());
//                values.put(SmartDCRConstants.KEY_BILL_MODE, bill.getBillDate());
//                values.put(SmartDCRConstants.KEY_BILL_CHANNEL, String.valueOf(bill.getChannel()));
//                values.put(SmartDCRConstants.KEY_BILL_BENEFICIARY, bill.getBeneficiaryId());
//                values.put(SmartDCRConstants.KEY_BILL_CREATED_DATE, bill.getCreatedDate());
//                values.put(SmartDCRConstants.KEY_BILL_STATUS, "T");
//                //  database.insert(TABLE_BILL, null, values);
//                database.insertWithOnConflict(TABLE_BILL, KEY_ID, values, SQLiteDatabase.CONFLICT_REPLACE);
//                String selectQuery = "SELECT  * FROM " + TABLE_BILL + " order by " + SmartDCRConstants.KEY_BILL_REF_ID + " DESC limit 1";
//                Cursor cursor = database.rawQuery(selectQuery, null);
//                cursor.moveToFirst();
//                BillDto billNew = new BillDto(cursor);
//                insertBillItems(bill.getBillItemDto(), billNew.getBillReferenceId(), billNew.getBillMonth(), billNew.getBeneficiaryId(),bill.getBillDate());
//            }
//        } catch (Exception e) {
//
//        }
//    }
//
//    //This function inserts details to TABLE_BILL;
//
//    public boolean insertBill(BillDto bill) {
//        try {
//            ContentValues values = new ContentValues();
//            values.put(KEY_ID, bill.getId());
//            values.put(SmartDCRConstants.KEY_BILL_FPS_ID, bill.getFpsId());
//            values.put(SmartDCRConstants.KEY_BILL_DATE, bill.getBillDate());
//            values.put(SmartDCRConstants.KEY_BILL_CREATED_BY, bill.getCreatedby());
//            values.put(SmartDCRConstants.KEY_BILL_AMOUNT, bill.getAmount());
//            values.put(SmartDCRConstants.KEY_BILL_MODE, String.valueOf(bill.getMode()));
//            values.put(SmartDCRConstants.KEY_BILL_CHANNEL, String.valueOf(bill.getChannel()));
//            values.put(SmartDCRConstants.KEY_BILL_TIME_STAMP, new Date().getTime());
//            values.put(SmartDCRConstants.KEY_BILL_CREATED_DATE, bill.getCreatedDate());
//            values.put(SmartDCRConstants.KEY_BILL_TIME_MONTH, new DateTime().getMonthOfYear());
//            values.put(SmartDCRConstants.KEY_BILL_BENEFICIARY, bill.getBeneficiaryId());
//          //  values.put("transactionId", bill.getTransactionId());
//            values.put(SmartDCRConstants.KEY_BILL_STATUS, "R");
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_QR_CODE, bill.getQrCode());
//            if (!database.isOpen()) {
//                database = dbHelper.getWritableDatabase();
//            }
//            //   database.insert(TABLE_BILL, null, values);
//
//            database.insertWithOnConflict(TABLE_BILL, KEY_ID, values, SQLiteDatabase.CONFLICT_REPLACE);
//            String selectQuery = "SELECT  * FROM " + TABLE_BILL + " order by " + SmartDCRConstants.KEY_BILL_REF_ID + " DESC limit 1";
//            Cursor cursor = database.rawQuery(selectQuery, null);
//            cursor.moveToFirst();
//            Log.e("Bills",bill.getBillDate());
//            BillDto billNew = new BillDto(cursor);
//            insertBillItems(bill.getBillItemDto(), billNew.getBillReferenceId(), billNew.getBillMonth(), billNew.getBeneficiaryId(),bill.getBillDate());
//            cursor.close();
//            return true;
//        } catch (Exception e) {
//            Util.LoggingQueue(contextValue, "Error", e.toString());
//            Log.e("Error", e.toString(), e);
//            return false;
//        }
//
//    }
//
//    //Get Beneficiary data by QR Code
//    public BillDto lastGenBill() {
//        String selectQuery = "SELECT  * FROM " + TABLE_BILL + " order by refId DESC limit 1";
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        cursor.moveToFirst();
//        if (cursor.getCount() == 0) {
//            return null;
//        } else {
//            BillDto beneficiary = new BillDto(cursor);
//            return beneficiary;
//        }
//    }
//
//   //Update the bill
//    public void billUpdate(BillDto bill){
//        ContentValues values = new ContentValues();
//        values.put(KEY_ID, bill.getId());
//        values.put(SmartDCRConstants.KEY_BILL_FPS_ID, bill.getFpsId());
//        values.put(SmartDCRConstants.KEY_BILL_DATE, bill.getBillDate());
//        values.put(SmartDCRConstants.KEY_BILL_CREATED_BY, bill.getCreatedby());
//        values.put(SmartDCRConstants.KEY_BILL_AMOUNT, bill.getAmount());
//        values.put(SmartDCRConstants.KEY_BILL_MODE, String.valueOf(bill.getMode()));
//        values.put(SmartDCRConstants.KEY_BILL_CHANNEL, String.valueOf(bill.getChannel()));
//        values.put(SmartDCRConstants.KEY_BILL_CREATED_DATE, bill.getCreatedDate());
//        values.put(SmartDCRConstants.KEY_BILL_BENEFICIARY, bill.getBeneficiaryId());
//        values.put("transactionId", bill.getTransactionId());
//        values.put(SmartDCRConstants.KEY_BILL_STATUS, "T");
//        database.update(TABLE_BILL,values, "transactionId='"+bill.getTransactionId()+"'" ,null);
//
//    }
//
//    //This function inserts details to TABLE_BILL_ITEM,;
//
//    private void insertBillItems(Set<BillItemDto> billItem, long refId, int month, long beneficiaryId,String billDate) {
//        ContentValues values = new ContentValues();
//        List<BillItemDto> billList = new ArrayList<BillItemDto>(billItem);
//        for (BillItemDto billItems : billList) {
//            values.put(KEY_ID, billItems.getId());
//            values.put(SmartDCRConstants.KEY_BILL_ITEM_PRODUCT_ID, billItems.getProductId());
//            values.put(SmartDCRConstants.KEY_BILL_ITEM_QUANTITY, billItems.getQuantity());
//            values.put(SmartDCRConstants.KEY_BILL_ITEM_COST, billItems.getCost());
//            values.put(SmartDCRConstants.KEY_BILL_ITEM_BILL_REF, refId);
//            values.put(SmartDCRConstants.KEY_BILL_TIME_MONTH, month);
//            values.put(SmartDCRConstants.KEY_BILL_BENEFICIARY, beneficiaryId);
//            values.put(SmartDCRConstants.KEY_BILL_ITEM_DATE, billDate);
//
//
//            //database.insert(TABLE_BILL_ITEM, null, values);
//            database.insertWithOnConflict(TABLE_BILL_ITEM, KEY_ID, values, SQLiteDatabase.CONFLICT_REPLACE);
//
//        }
//    }
//
//    //Bill for background sync
//    public List<BillDto> getAllBillsForSync() {
//        List<BillDto> bills = new ArrayList<BillDto>();
//        String selectQuery = "SELECT  * FROM " + TABLE_BILL + " where " + SmartDCRConstants.KEY_BILL_STATUS + "='R'";
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        cursor.moveToFirst();
//        for (int i = 0; i < cursor.getCount(); i++) {
//            BillDto bill = new BillDto(cursor);
//            bill.setBillItemDto(getBillItems(bill.getBillReferenceId()));
//            bills.add(bill);
//            cursor.moveToNext();
//        }
//        return bills;
//    }
//
//    //Bill for background sync
//    public void getAllBillItemsToday(String toDate) {
//        List<BillItemDto> bills = new ArrayList<BillItemDto>();
//        String selectQuery = "SELECT  productId,SUM(quantity) as total FROM " + TABLE_BILL_ITEM + " where " + SmartDCRConstants.KEY_BILL_ITEM_DATE + " LIKE '"+toDate+" %' group by productId";
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        cursor.moveToFirst();
//        for (int i = 0; i < cursor.getCount(); i++) {
//            BillItemDto billItemDto = new BillItemDto();
//            billItemDto.setProductId(cursor.getLong(cursor.getColumnIndex(SmartDCRConstants.KEY_BILL_ITEM_PRODUCT_ID)));
//            billItemDto.setQuantity(cursor.getDouble(cursor.getColumnIndex("total")));
//            bills.add(billItemDto);
//            cursor.moveToNext();
//        }
//        Log.e("Bill Item",bills.toString());
//    }
//
//
//    //Bill from local db
//    public List<BillDto> getAllBills(long id, int month) {
//        List<BillDto> bills = new ArrayList<BillDto>();
//        String selectQuery = "SELECT  * FROM " + TABLE_BILL + " where " + SmartDCRConstants.KEY_BILL_BENEFICIARY + " = " + id + " AND " + SmartDCRConstants.KEY_BILL_TIME_MONTH + " = " + month;
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        cursor.moveToFirst();
//        for (int i = 0; i < cursor.getCount(); i++) {
//            BillDto bill = new BillDto(cursor);
//            bill.setBillItemDto(getBillItems(bill.getBillReferenceId()));
//            bills.add(bill);
//            cursor.moveToNext();
//        }
//        return bills;
//    }
//
//    //Bill from local db
//    public List<BillItemDto> getAllBillItems(long id, int month) {
//        List<BillItemDto> billItems = new ArrayList<BillItemDto>();
//        String selectQuery = "SELECT productId,SUM(quantity) as total FROM " + TABLE_BILL_ITEM + " where " + SmartDCRConstants.KEY_BILL_BENEFICIARY + " = " + id + " AND " + SmartDCRConstants.KEY_BILL_TIME_MONTH + " = " + month + " group by " + SmartDCRConstants.KEY_BILL_ITEM_PRODUCT_ID;
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        cursor.moveToFirst();
//        for (int i = 0; i < cursor.getCount(); i++) {
//            BillItemDto billItemDto = new BillItemDto();
//            billItemDto.setProductId(cursor.getLong(cursor.getColumnIndex(SmartDCRConstants.KEY_BILL_ITEM_PRODUCT_ID)));
//            billItemDto.setQuantity(cursor.getDouble(cursor.getColumnIndex("total")));
//            billItems.add(billItemDto);
//            cursor.moveToNext();
//        }
//        return billItems;
//    }
//
//    public Set<BillItemDto> getBillItems(long referenceId) {
//        List<BillItemDto> billItems = new ArrayList<BillItemDto>();
//        String selectQuery = "SELECT  * FROM " + TABLE_BILL_ITEM + " where " + SmartDCRConstants.KEY_BILL_ITEM_BILL_REF + "=" + referenceId;
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        cursor.moveToFirst();
//        for (int i = 0; i < cursor.getCount(); i++) {
//            billItems.add(new BillItemDto(cursor));
//            cursor.moveToNext();
//        }
//        Set<BillItemDto> billItemSet = new HashSet<>(billItems);
//        return billItemSet;
//    }
//
//    //This function inserts details to TABLE_BILL_ITEM,;
//    private void setBillItems(BillDto fpsDataDto) {
//        ContentValues billItemValues = new ContentValues();
//        List<BillItemDto> billItemList = new ArrayList<BillItemDto>(fpsDataDto.getBillItemDto());
//        for (BillItemDto billItem : billItemList) {
//            billItemValues.put(KEY_ID, billItem.getId());
//            billItemValues.put(SmartDCRConstants.KEY_BILL_ID, billItem.getBillId());
//            billItemValues.put(SmartDCRConstants.KEY_BILL_ITEM_PRODUCT_ID, billItem.getProductId());
//            billItemValues.put(SmartDCRConstants.KEY_BILL_ITEM_QUANTITY, billItem.getQuantity());
//            billItemValues.put(SmartDCRConstants.KEY_BILL_ITEM_COST, billItem.getCost());
//            if (billItem.isTransmittedToPos()) {
//                billItemValues.put(SmartDCRConstants.KEY_BILL_ITEM_IS_TRANSMIT, 0);
//            } else {
//                billItemValues.put(SmartDCRConstants.KEY_BILL_ITEM_IS_TRANSMIT, 1);
//            }
//            //database.insert(TABLE_BILL_ITEM, null, billItemValues);
//            database.insertWithOnConflict(TABLE_BILL_ITEM, KEY_ID, billItemValues, SQLiteDatabase.CONFLICT_REPLACE);
//        }
//
//    }
//
//    //This function inserts details to TABLE_PRODUCTS;
//
//    public void insertProductData(FpsDataDto fpsDataDto) {
//        List<ProductDto> productList = new ArrayList<ProductDto>(fpsDataDto.getProductDto());
//        for (ProductDto products : productList) {
//            ContentValues values = new ContentValues();
//            values.put(KEY_ID, products.getId());
//            values.put(SmartDCRConstants.KEY_PRODUCT_NAME, products.getName());
//            values.put(SmartDCRConstants.KEY_LPRODUCT_NAME, products.getLproductName());
//            values.put(SmartDCRConstants.KEY_PRODUCT_CODE, products.getCode());
//            values.put(SmartDCRConstants.KEY_LPRODUCT_UNIT, products.getLproductUnit());
//            values.put(SmartDCRConstants.KEY_PRODUCT_UNIT, products.getProductUnit());
//            values.put(SmartDCRConstants.KEY_PRODUCT_PRICE, products.getProductPrice());
//            if (products.isNegativeIndicator())
//                values.put(SmartDCRConstants.KEY_NEGATIVE_INDICATOR, 0);
//            else {
//                values.put(SmartDCRConstants.KEY_NEGATIVE_INDICATOR, 1);
//            }
//            values.put(SmartDCRConstants.KEY_PRODUCT_MODIFIED_DATE, products.getModifiedDate());
//            if (products.getModifiedby() != null)
//                values.put(SmartDCRConstants.KEY_MODIFIED_BY, products.getModifiedby());
//            values.put(SmartDCRConstants.KEY_CREATED_DATE, products.getCreatedDate());
//            if (products.getCreatedby() != null)
//                values.put(SmartDCRConstants.KEY_CREATED_BY, products.getCreatedby());
//            // database.insert(TABLE_PRODUCTS, null, values);
//            database.insertWithOnConflict(TABLE_PRODUCTS, KEY_ID, values, SQLiteDatabase.CONFLICT_REPLACE);
//        }
//
//
//    }
//
//    //This function inserts details to TABLE_BENEFICIARY;
//    public void insertBeneficiaryData(FpsDataDto fpsDataDto) {
//        try {
//            ContentValues values = new ContentValues();
//            List<BeneficiaryDto> beneficiaryDto = new ArrayList<BeneficiaryDto>(fpsDataDto.getBeneficiaryDto());//get Beneficiary Details
//            for (BeneficiaryDto beneficiary : beneficiaryDto) {
//                values.put(KEY_ID, beneficiary.getId());
//                values.put(SmartDCRConstants.KEY_BENEFICIARY_UFC, beneficiary.getUfc());
//                values.put(SmartDCRConstants.KEY_BENEFICIARY_FPS_ID, beneficiary.getFpsId());
//                values.put(SmartDCRConstants.KEY_BENEFICIARY_TIN, beneficiary.getTin());
//                values.put(SmartDCRConstants.KEY_BENEFICIARY_QR_CODE, beneficiary.getQrCode());
//                values.put(SmartDCRConstants.KEY_BENEFICIARY_MOBILE, beneficiary.getMobileNumber());
//                values.put(SmartDCRConstants.KEY_BENEFICIARY_CREATED_DATE, beneficiary.getCreatedDate());
//                values.put(SmartDCRConstants.KEY_BENEFICIARY_MODIFIED_DATE, beneficiary.getModifiedDate());
//                values.put(SmartDCRConstants.KEY_BENEFICIARY_MODIFIED_BY, beneficiary.getModifiedBy());
//                values.put(SmartDCRConstants.KEY_BENEFICIARY_STATE_ID, beneficiary.getStateId());
//                values.put(SmartDCRConstants.KEY_BENEFICIARY_CARD_TYPE_ID, beneficiary.getCardTypeId());
//                values.put(SmartDCRConstants.KEY_BENEFICIARY_DISTRICT_ID, beneficiary.getDistrictId());
//                values.put(SmartDCRConstants.KEY_BENEFICIARY_TALUK_ID, beneficiary.getTalukId());
//                values.put(SmartDCRConstants.KEY_BENEFICIARY_VILLAGE_ID, beneficiary.getVillageId());
//
//                int active = 0;
//                if (beneficiary.isActive()) {
//                    active = 1;
//                }
//                values.put(SmartDCRConstants.KEY_BENEFICIARY_ACTIVE, active);
//
//                setBeneficiaryMemberData(beneficiary);
//                //database.insert(TABLE_BENEFICIARY, null, values);
//                database.insertWithOnConflict(TABLE_BENEFICIARY, KEY_ID, values, SQLiteDatabase.CONFLICT_REPLACE);
//
//            }
//        } catch (Exception e) {
//            Log.e("Error", e.toString(), e);
//        }
//
//    }
//
//    //This function inserts details to TABLE_BENEFICIARY_MEMBER;
//    private void setBeneficiaryMemberData(BeneficiaryDto fpsDataDto) {
//
//        ContentValues values = new ContentValues();
//        List<BeneficiaryMemberDto> beneficiaryMemberList = new ArrayList<BeneficiaryMemberDto>(fpsDataDto.getBenefMemberDto());
//        for (BeneficiaryMemberDto beneficiaryMember : beneficiaryMemberList) {
//            values.put(KEY_ID, beneficiaryMember.getId());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_QR_CODE, fpsDataDto.getQrCode());
//            if (beneficiaryMember.getTin() != null) {
//                values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_UID, beneficiaryMember.getTin());
//
//            } else {
//                Log.e("BM", "" + TABLE_BENEFICIARY_MEMBER + "TIN should not null");
//            }
//
//            if (beneficiaryMember.getUid() != null) {
//                values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_TIN, beneficiaryMember.getUid());
//            } else {
//                Log.e("BM", "" + TABLE_BENEFICIARY_MEMBER + "UID should not null");
//            }
//
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_EID, beneficiaryMember.getEid());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_FIRST_NAME, beneficiaryMember.getFirstname());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_MIDDLE_NAME, beneficiaryMember.getMiddlename());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_LAST_NAME, beneficiaryMember.getLastname());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_FATHER_NAME, beneficiaryMember.getFatherName());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_MOTHER_NAME, beneficiaryMember.getMotherName());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_GENDER, String.valueOf(beneficiaryMember.getGender()));//gender char
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_PERMANENT_ADDRESS, beneficiaryMember.getPermanentAddress());
//
//
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_TEMP_ADDRESS, beneficiaryMember.getTempAddress());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_CREATED_BY, beneficiaryMember.getCreatedBy());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_CREATED_DATE, beneficiaryMember.getCreatedDate());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_MODIFIED_BY, beneficiaryMember.getModifiedby());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_MODIFIED_DATE, beneficiaryMember.getModifiedDate());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_NAME, beneficiaryMember.getName());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_RESIDENT_ID, String.valueOf(beneficiaryMember.getResidentid()));  //Resident
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_REL_NAME, beneficiaryMember.getRelname());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_GENDER_ID, String.valueOf(beneficiaryMember.getGenderid())); //gender id
//
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_DOB, beneficiaryMember.getDob());
//
//
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_DOB_TYPE, String.valueOf(beneficiaryMember.getDobType())); //dob type
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_STATUS_ID, String.valueOf(beneficiaryMember.getMstatusid()));
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_EDU_NAME, beneficiaryMember.getEduname());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_OCCUPATION_NAME, beneficiaryMember.getOccuname());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_FATHER_CODE, beneficiaryMember.getFathercode());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_FATHER_NM, beneficiaryMember.getFathernm());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_MOTHER_CODE, beneficiaryMember.getMothercode());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_MOTHER_NM, beneficiaryMember.getMothernm());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_SPOUSE_CODE, beneficiaryMember.getSpousecode());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_SPOUSE_NM, beneficiaryMember.getSpousenm());
//
//
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_NAT_NAME, beneficiaryMember.getNatname());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_ADDRESS_LINE_1, beneficiaryMember.getAddressline1());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_ADDRESS_LINE_2, beneficiaryMember.getAddressline2());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_ADDRESS_LINE_3, beneficiaryMember.getAddressline3());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_ADDRESS_LINE_4, beneficiaryMember.getAddressline4());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_ADDRESS_LINE_5, beneficiaryMember.getAddressline5());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_PIN_CODE, beneficiaryMember.getPincode());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_DURATION_IN_YEAR, beneficiaryMember.getDurationinyear());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_P_ADDRESS_LINE_1, beneficiaryMember.getPaddressline1());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_P_ADDRESS_LINE_2, beneficiaryMember.getPaddressline2());
//
//
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_P_ADDRESS_LINE_3, beneficiaryMember.getPaddressline3());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_P_ADDRESS_LINE_4, beneficiaryMember.getPaddressline4());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_P_ADDRESS_LINE_5, beneficiaryMember.getPaddressline5());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_P_PIN_CODE, beneficiaryMember.getPpincode());
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_DATE_DATA_ENTERED, beneficiaryMember.getDateDataEntered());
//
//            if (beneficiaryMember.isAliveStatus())
//                values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_ALIVE_STATUS, 0);
//            else {
//                values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_ALIVE_STATUS, 1);
//            }
//
//            if (beneficiaryMember.isAdult())
//                values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_IS_ADULT, 0);
//            else {
//                values.put(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_IS_ADULT, 1);
//            }
//            //database.insert(TABLE_BENEFICIARY_MEMBER, null, values);
//            Log.i("Gender", String.valueOf(beneficiaryMember.getGender()));
//
//            database.insertWithOnConflict(TABLE_BENEFICIARY_MEMBER, KEY_ID, values, SQLiteDatabase.CONFLICT_REPLACE);
//
//        }
//
//
//    }
//
//    private List<BeneficiaryMemberDto> getAllBeneficiaryMembers(String qrCode) {
//        List<BeneficiaryMemberDto> beneficiaryMembers = new ArrayList<BeneficiaryMemberDto>();
//        String selectQuery = "SELECT  * FROM " + TABLE_BENEFICIARY_MEMBER + " where " + SmartDCRConstants.KEY_BENEFICIARY_QR_CODE + "='" + qrCode + "' AND " + SmartDCRConstants.KEY_BENEFICIARY_MEMBER_ALIVE_STATUS + "=0";
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        cursor.moveToFirst();
//        for (int i = 0; i < cursor.getCount(); i++) {
//            beneficiaryMembers.add(new BeneficiaryMemberDto(cursor));
//
//            cursor.moveToNext();
//        }
//        return beneficiaryMembers;
//        //new BeneficiaryDto(cursor);
//    }
//
//    //Get Beneficiary data by QR Code
//    public BeneficiaryDto beneficiaryDto(String qrCode) {
//        String selectQuery = "SELECT  * FROM " + TABLE_BENEFICIARY + " where " + SmartDCRConstants.KEY_BENEFICIARY_QR_CODE + "='" + qrCode + "'";//+" AND " +SmartDCRConstants.KEY_BENEFICIARY_ACTIVE +"=0";
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        cursor.moveToFirst();
//        if (cursor.getCount() == 0) {
//            return null;
//        } else {
//            BeneficiaryDto beneficiary = new BeneficiaryDto(cursor);
//            beneficiary.setFamilyMembers(getAllBeneficiaryMembers(qrCode));
//            return beneficiary;
//        }
//    }
//
//    //Get Product data by Product Id
//    public ProductDto getProductDetails(long _id) {
//        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS + " where " + KEY_ID + "=" + _id;//+" AND " +SmartDCRConstants.KEY_BENEFICIARY_ACTIVE +"=0";
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        cursor.moveToFirst();
//        if (cursor.getCount() == 0) {
//            return null;
//        } else
//            return new ProductDto(cursor);
//    }
//
//    //Get Product data by Product Id
//    public List<ProductDto> getAllProductDetails() {
//        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS;
//        List<ProductDto> products = new ArrayList<ProductDto>();
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        cursor.moveToFirst();
//        for (int i = 0; i < cursor.getCount(); i++) {
//            products.add(new ProductDto(cursor));
//            cursor.moveToNext();
//        }
//        return products;
//    }
//
//    //Get Allotments by district and card
//    public List<AllotmentDto> getAllAllotments(long districtId, String cardTypeId) {
//        List<AllotmentDto> allotmentProduct = new ArrayList<AllotmentDto>();
//        String selectQuery = "SELECT  * FROM " + TABLE_ALLOTMENT + " where " + SmartDCRConstants.KEY_ALLOTMENT_DISTRICT + "=" + districtId + " AND " + SmartDCRConstants.KEY_ALLOTMENT_CARD_TYPE + "='" + cardTypeId + "'";//+" AND " +SmartDCRConstants.KEY_BENEFICIARY_ACTIVE +"=0";
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        cursor.moveToFirst();
//        for (int i = 0; i < cursor.getCount(); i++) {
//            allotmentProduct.add(new AllotmentDto(cursor));
//
//            cursor.moveToNext();
//        }
//        return allotmentProduct;
//    }
//
//    //Get AgeGroup by age and productId
//    public AgeGroupDto getAgeGroup(long productId, int age) {
//        AgeGroupDto ageGroupDto = new AgeGroupDto();
//        String selectQuery = "SELECT  * FROM " + TABLE_AGE_GROUP + " where " + SmartDCRConstants.KEY_AGE_PRODUCT_ID + "=" + productId + " AND (" + SmartDCRConstants.KEY_AGE_TO + " >=" + age + " AND " + SmartDCRConstants.KEY_AGE_FROM + " <=" + age + ")";
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        cursor.moveToFirst();
//        ageGroupDto = new AgeGroupDto(cursor);
//
//        return ageGroupDto;
//    }
//
//
//    //Get Allotment Mapping by product and productId
//    public AllotmentMappingDto getAllotmentMap(long productId, long districtId, double quantity) {
//        AllotmentMappingDto allotmentMappingDto = new AllotmentMappingDto();
//        String selectQuery = "SELECT  * FROM " + TABLE_ALLOTMENT_MAPPING + " where " + SmartDCRConstants.KEY_ALLOTMENT_MAP_PRODUCT_ID + "=" + productId + " AND " + SmartDCRConstants.KEY_ALLOTMENT_MAP_DISTRICT + " =" + districtId + " AND (" + SmartDCRConstants.KEY_ALLOTMENT_MAP_END + " >=" + quantity + " AND " + SmartDCRConstants.KEY_ALLOTMENT_MAP_START + " <=" + quantity + ")";
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        cursor.moveToFirst();
//        allotmentMappingDto = new AllotmentMappingDto(cursor);
//        return allotmentMappingDto;
//    }
//
//    //This function inserts details to TABLE_ALLOTMENT_MAPPING;
//    public void insertAllotmentData(FpsDataDto fpsDataDto) {
//
//        ContentValues values = new ContentValues();
//        List<AllotmentDto> allotmentDtoList = new ArrayList<AllotmentDto>(fpsDataDto.getAllotmentDto());//get Beneficiary Details
//        if (!allotmentDtoList.isEmpty()) {
//            for (AllotmentDto allotment : allotmentDtoList) {
//                values.put(KEY_ID, allotment.getId());
//                values.put(SmartDCRConstants.KEY_ALLOTMENT_PRODUCT_ID, allotment.getProductId());
//                values.put(SmartDCRConstants.KEY_ALLOTMENT_LIMIT, allotment.getAllotmentLimit());
//                if (allotment.isCalcAllotment())
//                    values.put(SmartDCRConstants.KEY_CALC_ALLOTMENT_ALLOTMENT, 0);
//                else {
//                    values.put(SmartDCRConstants.KEY_CALC_ALLOTMENT_ALLOTMENT, 1);
//                }
//
//                values.put(SmartDCRConstants.KEY_ALLOTMENT_DISTRICT, allotment.getDistrictId());
//                // values.put(SmartDCRConstants.KEY_ALLOTMENT_AGE_GROUP_ID, allotment.get);
//                values.put(SmartDCRConstants.KEY_ALLOTMENT_CREATED_BY, allotment.getCreatedBy());
//                values.put(SmartDCRConstants.KEY_ALLOTMENT_MODIFIED_BY, allotment.getModifiedBY());
//                values.put(SmartDCRConstants.KEY_ALLOTMENT_CREATE_DATE, allotment.getCreateDate());
//                values.put(SmartDCRConstants.KEY_ALLOTMENT_MODIFIED_DATE, allotment.getModifiedDate());
//                values.put(SmartDCRConstants.KEY_ALLOTMENT_CARD_TYPE, allotment.getCardtypeId());
//
//                database.insertWithOnConflict(TABLE_ALLOTMENT, KEY_ID, values, SQLiteDatabase.CONFLICT_REPLACE);
//
//            }
//        }
//    }
//
//    //This function inserts details to TABLE_ALLOTMENT_MAPPING;
//    public void insertAllotmentMapData(FpsDataDto fpsDataDto) {
//
//        ContentValues values = new ContentValues();
//        List<AllotmentMappingDto> allotmentMappingDtoList = new ArrayList<AllotmentMappingDto>(fpsDataDto.getAllomappingDto());//get Beneficiary Details
//        if (!allotmentMappingDtoList.isEmpty()) {
//            for (AllotmentMappingDto allotmentMapping : allotmentMappingDtoList) {
//                values.put(KEY_ID, allotmentMapping.getId());
//                //  values.put(SmartDCRConstants.KEY_ALLOTMENT_MAP_MAP_ID, allotmentMapping.get);
//                values.put(SmartDCRConstants.KEY_ALLOTMENT_MAP_START, allotmentMapping.getStartRange());
//                values.put(SmartDCRConstants.KEY_ALLOTMENT_MAP_END, allotmentMapping.getEndRange());
//                values.put(SmartDCRConstants.KEY_ALLOTMENT_MAP_ALLOT, allotmentMapping.getAllotedQuantity());
//                values.put(SmartDCRConstants.KEY_ALLOTMENT_MAP_DISTRICT, allotmentMapping.getDistrictId());
//                values.put(SmartDCRConstants.KEY_ALLOTMENT_MAP_DESCRIPTION, allotmentMapping.getDescription());
//                values.put(SmartDCRConstants.KEY_ALLOTMENT_MAP_PRODUCT_ID, allotmentMapping.getProductId());
//                //database.insert(TABLE_ALLOTMENT_MAPPING, null, values);
//                database.insertWithOnConflict(TABLE_ALLOTMENT_MAPPING, KEY_ID, values, SQLiteDatabase.CONFLICT_REPLACE);
//            }
//        }
//
//    }
//
//    //This function inserts details to TABLE_AGE_GROUP.
//    public void insertAgeGroupData(FpsDataDto fpsDataDto) {
//        ContentValues values = new ContentValues();
//        List<AgeGroupDto> ageGroupList = new ArrayList<AgeGroupDto>(fpsDataDto.getAgegroupDto());
//        Log.e("Age", ageGroupList.toString());
//        if (!ageGroupList.isEmpty()) {
//            for (AgeGroupDto ageGroup : ageGroupList) {
//                values.put(KEY_ID, ageGroup.getId());
//                values.put(SmartDCRConstants.KEY_AGE_NAME, ageGroup.getName());
//                values.put(SmartDCRConstants.KEY_AGE_PRODUCT_ID, ageGroup.getProductId());
//                values.put(SmartDCRConstants.KEY_AGE_FROM, ageGroup.getFromRange());
//                values.put(SmartDCRConstants.KEY_AGE_TO, ageGroup.getToRange());
//                values.put(SmartDCRConstants.KEY_AGE_QUANTITY, ageGroup.getQuantity());
//                // database.insert(TABLE_AGE_GROUP, null, values);
//
//                database.insertWithOnConflict(TABLE_AGE_GROUP, KEY_ID, values, SQLiteDatabase.CONFLICT_REPLACE);
//            }
//        }
//    }
//
//
//    //This function inserts details to TABLE_CARD_TYPE.
//    public void insertCardTypeData(FpsDataDto fpsDataDto) {
//
//
//        ContentValues values = new ContentValues();
//        List<CardTypeDto> cardTypeList = new ArrayList<CardTypeDto>(fpsDataDto.getCardtypeDto());
//        if (!cardTypeList.isEmpty()) {
//            for (CardTypeDto cardType : cardTypeList) {
//                values.put(KEY_ID, cardType.getId());
//                if (String.valueOf(cardType.getType()) != null) {
//                    values.put(SmartDCRConstants.KEY_CARD_TYPE, String.valueOf(cardType.getType()));
//                }
//                if (String.valueOf(cardType.getDescription()) != null) {
//                    values.put(SmartDCRConstants.KEY_CARD_DESCRIPTION, cardType.getDescription());
//                }
//
//                //  database.insert(TABLE_CARD_TYPE, null, values);
//
//                database.insertWithOnConflict(TABLE_CARD_TYPE, KEY_ID, values, SQLiteDatabase.CONFLICT_REPLACE);
//            }
//        } else {
//            Log.e("CT", TABLE_CARD_TYPE + "is empty");
//        }
//    }
//
//    //This function inserts details to TABLE_USERS,
//    public void insertUserDetailData(FpsDataDto fpsDataDto) {
//
//        ContentValues values = new ContentValues();
//        List<UserDetailDto> userDetailList = new ArrayList<UserDetailDto>(fpsDataDto.getUserdetailDto());
//        if (!userDetailList.isEmpty()) {
//            for (UserDetailDto userDetail : userDetailList) {
//                values.put(KEY_ID, userDetail.getId());
//                values.put(SmartDCRConstants.KEY_USERS_NAME, userDetail.getUsername());
//                values.put(SmartDCRConstants.KEY_USERS_PASS_HASH, userDetail.getPassword());
//                values.put(SmartDCRConstants.KEY_USERS_PROFILE, userDetail.getProfile());
//                values.put(SmartDCRConstants.KEY_USERS_CREATE_DATE, userDetail.getCreatedDate());
//                values.put(SmartDCRConstants.KEY_USERS_MODIFIED_DATE, userDetail.getModifiedDate());
//                values.put(SmartDCRConstants.KEY_USERS_CREATED_BY, userDetail.getCreatedBy());
//                values.put(SmartDCRConstants.KEY_USERS_MODIFIED_BY, userDetail.getModifiedBy());
//                values.put(SmartDCRConstants.KEY_USERS_FPS_ID, userDetail.getFpsStore().getId());
//
//                // database.insert(TABLE_USERS, null, values);
//
//                database.insertWithOnConflict(TABLE_USERS, KEY_ID, values, SQLiteDatabase.CONFLICT_REPLACE);
//
//            }
//        } else {
//            Log.e("UD", TABLE_USERS + "is empty");
//        }
//
//    }
//
//    // This function inserts details to TABLE_STORE
//    public void insertFbsStoreData(FpsDataDto fpsDataDto) {
//
//        ContentValues values = new ContentValues();
//        List<FpsStoreDto> fpsStoreList = new ArrayList<FpsStoreDto>(fpsDataDto.getFpsstoreDto());
//        if (!fpsStoreList.isEmpty()) {
//            for (FpsStoreDto fpsStore : fpsStoreList) {
//                values.put(KEY_ID, fpsStore.getId());
//                values.put(SmartDCRConstants.KEY_STORE_GO_DOWN_ID, fpsStore.getGodownId());
//                values.put(SmartDCRConstants.KEY_STORE_CODE, fpsStore.getCode());
//                if (fpsStore.isActive()) {
//                    values.put(SmartDCRConstants.KEY_STORE_ACTIVE, 0);
//                } else {
//                    values.put(SmartDCRConstants.KEY_STORE_ACTIVE, 1);
//                }
//                values.put(SmartDCRConstants.KEY_STORE_CREATED_BY, fpsStore.getCreatedBy());
//                values.put(SmartDCRConstants.KEY_STORE_MODIFIED_BY, fpsStore.getModifiedBY());
//                values.put(SmartDCRConstants.KEY_STORE_CREATED_DATE, fpsStore.getCreateDate());
//                values.put(SmartDCRConstants.KEY_STORE_MODIFIED_DATE, fpsStore.getModofiedDate());
//                setGoDownDetails(fpsStore.getGodown());
//                // database.insert(TABLE_STORE, null, values);
//                database.insertWithOnConflict(TABLE_STORE, KEY_ID, values, SQLiteDatabase.CONFLICT_REPLACE);
//            }
//        } else {
//            Log.e("FS", TABLE_STORE + "is empty");
//        }
//
//    }
//
//
//    // This function inserts details to TABLE_STOCK
//    public void insertFpsStockData(FpsDataDto fpsDataDto) {
//
//
//        List<FPSStockDto> fpsStockList = new ArrayList<FPSStockDto>(fpsDataDto.getFpsStockDto());
//        if (!fpsStockList.isEmpty()) {
//            for (FPSStockDto fpsStock : fpsStockList) {
//                ContentValues values = new ContentValues();
//                values.put(KEY_ID, fpsStock.getFpsId());
//                values.put(SmartDCRConstants.KEY_STOCK_PRODUCT_ID, fpsStock.getProductId());
//                values.put(SmartDCRConstants.KEY_STOCK_QUANTITY, fpsStock.getQuantity());
//                values.put(SmartDCRConstants.KEY_STOCK_REORDER_LEVEL, fpsStock.getReorderLevel());
//
//                if(fpsStock.isEmailAction()) {
//                    values.put(SmartDCRConstants.KEY_STOCK_EMAIL_ACTION, 0);
//                }
//
//                else {
//                    values.put(SmartDCRConstants.KEY_STOCK_EMAIL_ACTION, 1);
//                }
//
//                if(fpsStock.isSmsMSAction()) {
//                    values.put(SmartDCRConstants.KEY_STOCK_SMSACTION, 0);
//                }
//
//                else{
//                    values.put(SmartDCRConstants.KEY_STOCK_SMSACTION, 1);
//                }
//                Log.e("FSTOCK", TABLE_STOCK +fpsStock.toString());
//
//                 database.insert(TABLE_STOCK, null, values);
//            }
//        } else {
//            Log.e("FSTOCK", TABLE_STOCK + "is empty");
//        }
//
//    }
//
//
//
//    // This function inserts details to TABLE_GO_DOWN
//
//    private void setGoDownDetails(GodownDto goDown) {
//
//        if (goDown != null) {
//
//            ContentValues values = new ContentValues();
//            if (goDown.getId() != 0) {
//                values.put(KEY_ID, goDown.getId());
//            }
//
//            values.put(SmartDCRConstants.KEY_GO_DOWN_GO_DOWN_CODE, goDown.getGodownCode());
//            values.put(SmartDCRConstants.KEY_GO_DOWN_NAME, goDown.getName());
//            values.put(SmartDCRConstants.KEY_GO_DOWN_DISTRICT, goDown.getDistrict());
//            values.put(SmartDCRConstants.KEY_GO_DOWN_TALUK, goDown.getTaluk());
//            values.put(SmartDCRConstants.KEY_GO_DOWN_CATEGORY_ID, goDown.getCategoryId());
//            values.put(SmartDCRConstants.KEY_GO_DOWN_CONTACT_PERSON_NAME, goDown.getContactPersonName());
//            values.put(SmartDCRConstants.KEY_GO_DOWN_CONTACT_NUMBER, goDown.getContactNumber());
//            values.put(SmartDCRConstants.KEY_GO_DOWN_PIN_CODE, goDown.getPincode());
//            values.put(SmartDCRConstants.KEY_GO_DOWN_ADDRESS, goDown.getAddress());
//
//            if (goDown.isStatus()) {
//                values.put(SmartDCRConstants.KEY_GO_DOWN_IS_STATUS, 0);
//            } else {
//                values.put(SmartDCRConstants.KEY_GO_DOWN_IS_STATUS, 1);
//            }
//
//
//            values.put(SmartDCRConstants.KEY_GO_DOWN_CREATED_DATE, goDown.getCreatedDate());
//            values.put(SmartDCRConstants.KEY_GO_DOWN_MODIFIED_DATE, goDown.getModifiedDate());
//            values.put(SmartDCRConstants.KEY_GO_DOWN_CREATED_BY, goDown.getCreatedby());
//            values.put(SmartDCRConstants.KEY_GO_DOWN_MODIFIED_BY, goDown.getModifiedby());
//
//            // database.insert(TABLE_GO_DOWN, null, values);
//
//            database.insertWithOnConflict(TABLE_GO_DOWN, KEY_ID, values, SQLiteDatabase.CONFLICT_REPLACE);
//        }
//    }
//
//    // This function returns cursor  Max Date of Product
//
//    private Cursor getMaxModifiedDateProduct() {
//
//        String queryString = "productModifiedDate =(SELECT MAX(productModifiedDate) FROM  " + TABLE_PRODUCTS + ")";
//        return database.query(TABLE_PRODUCTS, null, queryString, null, null, null, null);
//    }
//
//    // This function returns cursor  Max Date of Beneficiary
//
//    private Cursor getMaxModifiedDateBeneficiary() {
//
//        String queryString = "beneficiaryModifiedDate =(SELECT MAX(beneficiaryModifiedDate) FROM  " + TABLE_BENEFICIARY + ")";
//        return database.query(TABLE_BENEFICIARY, null, queryString, null, null, null, null);
//    }
//
//    // This function returns cursor  Max Date of Bill
//
//    private Cursor getMaxModifiedDateBill() {
//        String queryString = "date =(SELECT MAX(date) FROM  " + TABLE_BILL + ")";
//        return database.query(TABLE_BILL, null, queryString, null, null, null, null);
//    }
//
//    // This function returns cursor  Max Date of Allotment
//    private Cursor getMaxModifiedDateAllotment() {
//        String queryString = "aModifiedDate =(SELECT MAX(aModifiedDate) FROM  " + TABLE_ALLOTMENT + ")";
//        return database.query(TABLE_ALLOTMENT, null, queryString, null, null, null, null);
//    }
//
//
//    // This function returns cursor  Max Date of Transaction
//    private Cursor getMaxModifiedDateTransaction() {
//        String queryString = SmartDCRConstants.KEY_TRANSACTION_TYPE_LAST_MODIFIED_TIME + " =" + "(SELECT MAX(tlastModifiedTime) FROM  " + TABLE_TRANSACTION_TYPE + ")";
//        return database.query(TABLE_TRANSACTION_TYPE, null, queryString, null, null, null, null);
//    }
//
//
//    // This function returns Max Date of Beneficiary
//
//    private String getBeneficiaryModifiedDate() {
//        Cursor cursorBeneficiary = getMaxModifiedDateBeneficiary();
//        String beneficiaryDate = null;
//        if (cursorBeneficiary != null) {
//            cursorBeneficiary.moveToFirst();
//            beneficiaryDate = cursorBeneficiary.getString(cursorBeneficiary.getColumnIndex(SmartDCRConstants.KEY_BENEFICIARY_MODIFIED_DATE));
//            cursorBeneficiary.close();
//            return beneficiaryDate;
//
//        } else {
//            Log.e("BM", "Beneficiary Max date cursor is null");
//
//        }
//        return beneficiaryDate;
//    }
//
//    // This function returns Max Date of Bill
//    private String getBillDate() {
//        Cursor cursorBill = getMaxModifiedDateBill();
//        String billDate = null;
//        if (cursorBill != null) {
//            cursorBill.moveToFirst();
//            billDate = cursorBill.getString(cursorBill.getColumnIndex(SmartDCRConstants.KEY_BILL_DATE));
//            cursorBill.close();
//            return billDate;
//        } else {
//            Log.e("BillM", "Bill Max date cursor is null");
//
//        }
//        return billDate;
//    }
//
//    // This function returns Max Date of Allotment
//    private String getAllotmentDate() {
//        Cursor cursorAllotment = getMaxModifiedDateAllotment();
//        String allotDate = null;
//        if (cursorAllotment != null) {
//            cursorAllotment.moveToFirst();
//            allotDate = cursorAllotment.getString(cursorAllotment.getColumnIndex(SmartDCRConstants.KEY_ALLOTMENT_MODIFIED_DATE));
//            cursorAllotment.close();
//            return allotDate;
//        } else {
//            Log.e("AllotM", "Allot Max date cursor is null");
//
//        }
//        return allotDate;
//    }
//
//    // This function returns Max Date of Product
//    private String getProductDate() {
//        Cursor cursorProduct = getMaxModifiedDateProduct();
//        String productDate = null;
//        if (cursorProduct != null) {
//            cursorProduct.moveToFirst();
//            productDate = cursorProduct.getString(cursorProduct.getColumnIndex(SmartDCRConstants.KEY_PRODUCT_MODIFIED_DATE));
//            cursorProduct.close();
//            return productDate;
//        } else {
//            Log.e("ProductM", "Product Max date cursor is null");
//
//        }
//        return productDate;
//    }
//
//    private String getTransactionDate() {
//        Cursor cursorTransaction = getMaxModifiedDateTransaction();
//        String transactionDate = null;
//        if (cursorTransaction != null) {
//            cursorTransaction.moveToFirst();
//            transactionDate = cursorTransaction.getString(cursorTransaction.getColumnIndex(SmartDCRConstants.KEY_TRANSACTION_TYPE_LAST_MODIFIED_TIME));
//            cursorTransaction.close();
//            return transactionDate;
//        } else {
//            Log.e("TMD", "Transaction Max date cursor is null");
//
//        }
//        return transactionDate;
//    }
//
//    // This function returns List for  max modified date of Product,Beneficiary,Bill and Allotment
//    public List<String> getMaxModifiedDate() {
//        List<String> valueList = new ArrayList<String>();
//
//        if (getProductDate() != null || getBeneficiaryModifiedDate() != null || getBillDate() != null || getAllotmentDate() != null || getTransactionDate() != null)
//            valueList.add(getProductDate());
//        valueList.add(getBeneficiaryModifiedDate());
//        valueList.add(getBillDate());
//        valueList.add(getAllotmentDate());
//        valueList.add(getTransactionDate());
//        return valueList;
//    }
//
//    // This function return beneficiary details for beneficiary activation
//    public List<String> retrieveBeneficiaryActivation(String ufc) {
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        int fpsId = 0;
//        int createdBy = 0;
//        int modifiedBy = 0;
//        int active = 0;
//        List<String> valueList = new ArrayList<String>();
//
//        if (!valueList.isEmpty()) {
//
//
//            String selectQuery = "SELECT  * FROM " + TABLE_BENEFICIARY + " where " + SmartDCRConstants.KEY_BENEFICIARY_UFC + "=?";
//            Cursor c = db.rawQuery(selectQuery, new String[]{ufc});
//            if (c.moveToFirst()) {
//                fpsId = c.getInt(c.getColumnIndex(SmartDCRConstants.KEY_BENEFICIARY_FPS_ID));
//                createdBy = c.getInt(c.getColumnIndex(SmartDCRConstants.KEY_BENEFICIARY_MEMBER_CREATED_BY));
//                modifiedBy = c.getInt(c.getColumnIndex(SmartDCRConstants.KEY_BENEFICIARY_MODIFIED_BY));
//                active = c.getInt(c.getColumnIndex(SmartDCRConstants.KEY_BENEFICIARY_ACTIVE));
//            }
//            valueList.add(String.valueOf(fpsId));
//            valueList.add(String.valueOf(createdBy));
//            valueList.add(String.valueOf(modifiedBy));
//            valueList.add(String.valueOf(active));
//            Log.i("ID", "" + "FPS_ID" + fpsId);
//            Log.i("BY", "" + "CREATED " + createdBy);
//            Log.i("BY", "" + "MODIFIED " + modifiedBy);
//            Log.i("AC", "" + "ACTIVE" + active);
//            c.close();
//            return valueList;
//        } else {
//            Log.e("BA", "Beneficiary Activation List is empty");
//        }
//        return valueList;
//    }
//
//    //This function inserts Beneficiary activation details into Beneficiary Activation Table
//
//    public void insertBeneficiaryActivation(String ufc, String oldRationCardNo, char cardType, int noOfCylinder, String rmn, int createdBy, int modifiedBy) {
//
//        ContentValues values = new ContentValues();
//        values.put(SmartDCRConstants.KEY_BENEFICIARY_ACTIVATION_UFC, ufc);
//        values.put(SmartDCRConstants.KEY_BENEFICIARY_ACTIVATION_OLD_RATION_CARD_NO, oldRationCardNo);
//        values.put(SmartDCRConstants.KEY_BENEFICIARY_ACTIVATION_CARD_TYPE, String.valueOf(cardType));
//        values.put(SmartDCRConstants.KEY_BENEFICIARY_ACTIVATION_NO_OF_CYLINDER, noOfCylinder);
//        values.put(SmartDCRConstants.KEY_BENEFICIARY_ACTIVATION_RMN, rmn);
//        /*if (isSubmitted) {
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_ACTIVATION_IS_SUBMITTED, 0);
//        } else {
//            values.put(SmartDCRConstants.KEY_BENEFICIARY_ACTIVATION_IS_SUBMITTED, 1);
//        }*/
//        values.put(SmartDCRConstants.KEY_BENEFICIARY_ACTIVATION_CREATED_BY, createdBy);
//        values.put(SmartDCRConstants.KEY_BENEFICIARY_ACTIVATION_MODIFIED_BY, modifiedBy);
//        if (!database.isOpen()) {
//            database = dbHelper.getWritableDatabase();
//        }
//        database.insert(TABLE_BENEFICIARY_ACTIVATION, null, values);
//
//        Log.i("Beneficiary", "" + oldRationCardNo);
//    }
//
//
//    //This function loads data to language table
//    public void insertLanguageTable(MessageDto message) {
//        ContentValues values = new ContentValues();
//        values.put(KEY_ID, message.getId());
//        values.put(SmartDCRConstants.KEY_LANGUAGE_CODE, message.getLanguageCode());
//        values.put(SmartDCRConstants.KEY_LANGUAGE_ID, message.getLanguageId());
//        values.put(SmartDCRConstants.KEY_LANGUAGE_MESSAGE, message.getDescription());
//
//        /*if (!database.isOpen()) {
//            database = dbHelper.getWritableDatabase();
//        }*/
////        database.insert(TABLE_LANGUAGE, null, values);
//
//        database.insertWithOnConflict(TABLE_LANGUAGE, SmartDCRConstants.KEY_LANGUAGE_ID, values,
//                SQLiteDatabase.CONFLICT_REPLACE);
//
//    }
//
//    //This function retrieve error description from language table
//    public MessageDto retrieveLanguageTable(int errorCode, String language) {
//        String selectQuery = "SELECT  * FROM " + TABLE_LANGUAGE + " where  " + SmartDCRConstants.KEY_LANGUAGE_CODE + " = " + errorCode + " AND " + SmartDCRConstants.KEY_LANGUAGE_ID + " ='" + language + "'";
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        MessageDto messageDto = new MessageDto();
//        cursor.moveToFirst();
//        for (int i = 0; i < cursor.getCount(); i++) {
//            messageDto = new MessageDto(cursor);
//            break;
//        }
//        cursor.close();
//        return messageDto;
//    }
//
//    // This function loads transaction type details
//    public void insertTransactionTypeDetails(FpsDataDto fpsDataDto) {
//
//        List<TransactionDto> fpsTransactionTypeDtoList = new ArrayList<TransactionDto>(fpsDataDto.getTransactionDto());
//        if (!fpsTransactionTypeDtoList.isEmpty()) {
//            for (TransactionDto transactionDto : fpsTransactionTypeDtoList) {
//                ContentValues values = new ContentValues();
//                values.put(KEY_ID, transactionDto.getId());
//                values.put(SmartDCRConstants.KEY_TRANSACTION_TYPE_TXN_TYPE, transactionDto.getTxnType());
//
//                if (transactionDto.isStatus()) {
//                    values.put(SmartDCRConstants.KEY_TRANSACTION_TYPE_STATUS, 0);
//                } else {
//                    values.put(SmartDCRConstants.KEY_TRANSACTION_TYPE_STATUS, 1);
//                }
//                values.put(SmartDCRConstants.KEY_TRANSACTION_TYPE_DESCRIPTION, transactionDto.getDescription());
//                values.put(SmartDCRConstants.KEY_TRANSACTION_TYPE_CREATED_BY, transactionDto.getCreatedBy());
//                values.put(SmartDCRConstants.KEY_TRANSACTION_TYPE_CREATED_DATE, transactionDto.getCreatedDate());
//                values.put(SmartDCRConstants.KEY_TRANSACTION_TYPE_LAST_MODIFIED_TIME, transactionDto.getLastModifiedTime());
//                database.insertWithOnConflict(TABLE_TRANSACTION_TYPE, KEY_ID, values, SQLiteDatabase.CONFLICT_REPLACE);
//
//                Log.e("TD", transactionDto.getDescription());
//            }
//        } else {
//            Log.e("TD", TABLE_TRANSACTION_TYPE + "is empty");
//        }
//    }
//    /*public void showDataTransaction() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String selectQuery = "SELECT  * FROM " + TABLE_TRANSACTION_TYPE;
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        TransactionDto transaction = new TransactionDto();
//        cursor.moveToFirst();
//
//        for(int i= 0; i< cursor.getCount();i++)
//        {
//            TransactionDto transactionDto = new TransactionDto(cursor);
//
//             long transactionType = transactionDto.getTxnType();
//
//              cursor.moveToNext();
//             Log.i("TT",""+transactionType);
//        }
//        cursor.close();
//
//    }*/
//
//
//
//
//
//    // This function inserts details to TABLE_STOCK
//    public void insertFpsStockInwardDetails(FpsDataDto fpsDataDto) {
//
//
//        List<GodownStockOutwardDto> fpsStockInwardList = new ArrayList<GodownStockOutwardDto>(fpsDataDto.getFpsStoInwardDto());
//
//        if (!fpsStockInwardList.isEmpty()) {
//            for (GodownStockOutwardDto fpsStockInvard : fpsStockInwardList) {
//                ContentValues values = new ContentValues();
//
//                values.put(SmartDCRConstants.KEY_FPS_STOCK_INWARD_GODOWNID,fpsStockInvard.getGodownId());
//                values.put(SmartDCRConstants.KEY_FPS_STOCK_INWARD_FPSID, fpsStockInvard.getFpsId());
//                values.put(SmartDCRConstants.KEY_FPS_STOCK_INWARD_OUTWARD_DATE, fpsStockInvard.getOutwardDate());
//                values.put(SmartDCRConstants.KEY_FPS_STOCK_INWARD_PRODUCTID, fpsStockInvard.getProductId());
//
//                values.put(SmartDCRConstants.KEY_FPS_STOCK_INWARD_QUANTITY, fpsStockInvard.getQuantity());
//                values.put(SmartDCRConstants.KEY_FPS_STOCK_INWARD_UNIT, fpsStockInvard.getUnit());
//                values.put(SmartDCRConstants.KEY_FPS_STOCK_INWARD_BATCH_NO, fpsStockInvard.getBatchno());
//
//                if(fpsStockInvard.isFpsAckStatus()==false){
//                    values.put(SmartDCRConstants.KEY_FPS_STOCK_INWARD_IS_FPSACKSTATUS, 0);
//                }
//                else{
//                    values.put(SmartDCRConstants.KEY_FPS_STOCK_INWARD_IS_FPSACKSTATUS, 1);
//                }
//
//
//                values.put(SmartDCRConstants.KEY_FPS_STOCK_INWARD_FPSACKDATE, fpsStockInvard.getFpsAckDate());
//                values.put(SmartDCRConstants.KEY_FPS_STOCK_INWARD_FPSRECEIVEIQUANTITY,fpsStockInvard.getFpsReceiQuantity());
//                values.put(SmartDCRConstants.KEY_FPS_STOCK_INWARD_CREATEDBY, fpsStockInvard.getCreatedby());
//                values.put(SmartDCRConstants.KEY_FPS_STOCK_INWARD_DELIEVERY_CHELLANID,fpsStockInvard.getDeliveryChallanId());
//
//                database.insert(TABLE_FPS_STOCK_INVARD, null, values);
//                Log.e("ertert","Input data");
//              // database.insertWithOnConflict(TABLE_FPS_STOCK_INVARD, KEY_ID, values, SQLiteDatabase.CONFLICT_REPLACE);
//            }
//        } else {
//            Log.e("FPSI", TABLE_STOCK + "is empty");
//        }
//
//    }
//
//    public  List<GodownStockOutwardDto> showFpsStockInvard(long fpsId) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String selectQuery = "SELECT  * FROM " + TABLE_FPS_STOCK_INVARD +" where " + SmartDCRConstants.KEY_FPS_STOCK_INWARD_FPSRECEIVEIQUANTITY+" = 0.0 group by "+SmartDCRConstants.KEY_FPS_STOCK_INWARD_DELIEVERY_CHELLANID ;
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        Log.i("Fpsdate",""+""+cursor.getCount());
//        Log.i("query",""+""+selectQuery);
//        List<GodownStockOutwardDto> fpsInwardList = new ArrayList<GodownStockOutwardDto>();
//        GodownStockOutwardDto fpsStockOutwardDto   = new GodownStockOutwardDto();
//        cursor.moveToFirst();
//        for(int i= 0; i< cursor.getCount();i++){
//            fpsStockOutwardDto = new GodownStockOutwardDto(cursor);
//            fpsInwardList.add(fpsStockOutwardDto);
//            cursor.moveToNext();
//            Log.i("Fpsdate",""+fpsStockOutwardDto.getOutwardDate());
//        }
//        cursor.close();
//        Log.i("FPI",""+fpsStockOutwardDto.toString());
//        Log.i("FPSSIZE",""+fpsInwardList.size());
//        return fpsInwardList;
//    }
//
//
//    public  List<GodownStockOutwardDto> showFpsStockInvardDetail(long fpsId,long chellanId)
//    {
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        List<GodownStockOutwardDto> fpsInwardList = new ArrayList<GodownStockOutwardDto>();
//        try
//        {
//            String selectQuery = "SELECT  * FROM " + TABLE_FPS_STOCK_INVARD+" where " +SmartDCRConstants.KEY_FPS_STOCK_INWARD_DELIEVERY_CHELLANID +"=" +chellanId;
//            Cursor cursor = database.rawQuery(selectQuery, null);
//            Set<ChellanProductDto> chellanProductDtoSet = new HashSet<ChellanProductDto>();
//            cursor.moveToFirst();
//            String productName= null;
//            Log.e("TABLE_FPS_STOCK_INVARD","TABLE_FPS_STOCK_INVARD"+cursor.getCount());
//            for(int i= 0; i< cursor.getCount();i++)
//            {
//                GodownStockOutwardDto fpsStockOutwardDto  = new GodownStockOutwardDto(cursor);
//                Log.i("ChellanId",""+fpsStockOutwardDto.getDeliveryChallanId());
//                Log.i("FPID",""+fpsStockOutwardDto.toString());
//
//                productName= getProductName(fpsStockOutwardDto.getProductId());
//
//                Log.i("PNAME",""+productName);
//
//                ChellanProductDto chellanProductDto = new ChellanProductDto();
//                chellanProductDto.setName(productName);
//                chellanProductDto.setProductId(fpsStockOutwardDto.getProductId());
//                chellanProductDto.setQuantity(fpsStockOutwardDto.getQuantity());
//
//                chellanProductDtoSet.add(chellanProductDto);
//
//                fpsStockOutwardDto.setProductDto(chellanProductDtoSet);
//
//                fpsInwardList.add(fpsStockOutwardDto);
//                Log.i("GSOD",""+ fpsInwardList.toString());
//                cursor.moveToNext();
//            }
//            cursor.close();
//
//        }
//
//        catch(Exception e)
//        {
//            Log.e("Exception",e.toString());
//
//        }
//
//        return fpsInwardList;
//    }
//
//
//    /*   db.update(TABLE_NAME,
//                contentValues,
//                        NAME + " = ? AND " + LASTNAME + " = ?",
//                        new String[]{"Manas", "Bajaj"});*/
//    public void updateReceivedQuantity(GodownStockOutwardDto godownStockOutwardDto)
//    {
//        ContentValues values = new ContentValues();
//        values.put(SmartDCRConstants.KEY_FPS_STOCK_INWARD_FPSACKDATE, godownStockOutwardDto.getFpsAckDate());
//
//       /* long productId =0;*/
//        double receivedQuantity = 0.0;
//        for(ChellanProductDto chellanProductDto:godownStockOutwardDto.getProductDto())
//        {
//         receivedQuantity = chellanProductDto.getReceiProQuantity();
//         values.put(SmartDCRConstants.KEY_FPS_STOCK_INWARD_FPSRECEIVEIQUANTITY, receivedQuantity);
//         database.update(TABLE_FPS_STOCK_INVARD,values,SmartDCRConstants.KEY_FPS_STOCK_INWARD_PRODUCTID + " = ? AND " + SmartDCRConstants.KEY_FPS_STOCK_INWARD_DELIEVERY_CHELLANID + " = ?",new String[]{String.valueOf(godownStockOutwardDto.getDeliveryChallanId()),String.valueOf(chellanProductDto.getProductId())});
//         Log.i("update",""+database.update(TABLE_FPS_STOCK_INVARD,values,SmartDCRConstants.KEY_FPS_STOCK_INWARD_PRODUCTID + " = ? AND " + SmartDCRConstants.KEY_FPS_STOCK_INWARD_DELIEVERY_CHELLANID + " = ?",new String[]{String.valueOf(chellanProductDto.getProductId()),String.valueOf(godownStockOutwardDto.getDeliveryChallanId())}));
//        }
//
//
//
//
//    }
//
//
//    //Get Product Name by Product Id
//    public String getProductName(long _id) {
//        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTS + " where " + KEY_ID + "=" + _id;//+" AND " +SmartDCRConstants.KEY_BENEFICIARY_ACTIVE +"=0";
//        Cursor cursor = database.rawQuery(selectQuery, null);
//        cursor.moveToFirst();
//        if (cursor.getCount() == 0) {
//            return null;
//        } else
//            return new ProductDto(cursor).getName();
//    }


    // database connection  close
    public synchronized void closeConnection() {
        if (dbHelper != null) {
            dbHelper.close();
            database.close();
            dbHelper = null;
            database = null;
        }
    }


}