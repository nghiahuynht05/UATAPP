package defineUIPackage;

/**
 * Created by nghia.huynht on 4/17/2020.
 */
public class DefineUI {


    //    -------Login form--------
    public static final String LOGIN_EDIT_HONEWC_TEXTBOX = "%seditPhoneWC";
    public static final String LOGIN_EDITPHONE_TEXTBOX = "%seditPhone";
    public static final String LOGIN_ACCEPTTERM_CHECKBOX = "%scbTerm";
    public static final String LOGIN_BUTTON = "%sbtnContinue";
    public static final String YES_NO_BUTTON = "//android.widget.Button[@text='%s']";
    public static final String LOGIN_TOAST_MESSAGE = "/hierarchy/android.widget.Toast";
    public static final String LOGIN_VERIFY_CODE = "%sedtCode";
    public static final String HOME_MENU_HOME = "%scst_main";

    // -------Home screen--------
    public static final String HOME_MENU_SERVICE = "//androidx.appcompat.app.ActionBar.Tab[@content-desc='%s']";
    public static final String HOME_BUTTON = "%sbtnHome";
    public static final String HOME_MENU_SETING = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout[2]/android.view.View/androidx.appcompat.widget.LinearLayoutCompat['%s']";
    public static final String HOME_SETING_PROFILE = "%snavigation_header_container";

    // -------Profile screen--------
    public static final String HOME_PROFILE_INFO = "%sctlPersonalInfo";
    public static final String HOME_PROFILE_PLACE = "%stvYourPlace";
    public static final String HOME_PROFILE_LOGOUT = "%stvLogout";
    public static final String HOME_PROFILE_INFO_BTAVATAR = "%simvAvatar";
    public static final String HOME_PROFILE_INFO_FIRSTNAME = "%stvFirstName";
    public static final String HOME_PROFILE_INFO_LASTNAME = "%stvLastName";
    public static final String HOME_PROFILE_INFO_PHONE = "%stvPhone";
    public static final String HOME_PROFILE_INFO_EMAIL = "%stvMail";
    public static final String HOME_PROFILE_INFO_GENDER = "%stvGenderValue";
    public static final String HOME_PROFILE_INFO_BIRTHDAY = "%stvDobValue";
    public static final String HOME_PROFILE_BTBACK = "//android.widget.ImageButton[@content-desc='Navigate up']";
    public static final String HOME_PROFILE_NATIONAL = "%sedtNationalIC";
    public static final String HOME_PROFILE_INFO_GENDER_MALE = "%stvGenderValue";
    public static final String HOME_PROFILE_INFO_GENDER_FEMALE = "%stvGenderValue";
    public static final String HOME_PROFILE_INFO_GENDER_OTHER = "%stvGenderValue";
    public static final String HOME_PROFILE_NAME = "%stvName";

    // -------Profile screen--------
    public static final String HOME_PLACE_HOME = "%sllHomeAddress";
    public static final String HOME_PLACE_WORK = "%sllWorkAddress";
    public static final String HOME_PLACE_TITELPLACE = "%sedtName";
    public static final String HOME_PLACE_EDITADDRESS = "%sedtAddress";
    public static final String HOME_PLACE_ADD = "%sactionAdd";
    public static final String HOME_PLACE_SAVE = "%sactionSave";

    // -------Other--------
    public static final String CONTENT_POPUP = "%stv_content_mess";
    public static final String ACTION_EDIT = "%sactionEdit";
    public static final String ACTION_SAVE = "%sactionSave";
    public static final String ACTION_CANCEL = "%sbtnCancel";

    public static final String FLEET_CODE_TEXTBOX_DEFAULT = "%sedtFleetCode";
    public static final String DEBUG_AREA = "%stoolbar";
    public static final String PASS_CODE_TEXTBOX = "%sedtPassCode";

    public static final String DYNAMIC_SERVERS_TAB = "//android.widget.TextView[@text='%s']";
    public static final String FLEET_ID_TEXTBOX = "%sedtFleetId";
    public static final String APPLY_BUTTON = "%sbtnApply";

    public static final String SELECT_PHONE_CODE_BUTTON = "%stvFlag";
    public static final String PHONE_CODE_LABEL = "%stvCountry";
    public static final String SEARCH_COUNTRY_PHONE_TEXTBOX = "%ssearch_src_text";
    public static final String COUNTRY_PHONE_CODE = "//android.widget.TextView[@text='%s']";
    public static final String SMS_DEFAULT_TEXBOX = "%sedtCode";
    public static final String WELCOME_MESSAGE = "%stv_content_mess";

    public static final String REGISTER_TEXTBOXES = "//android.widget.EditText[contains(@text, '%s')]";
    public static final String GENDER_BUTTONS = "//android.widget.Button[@text='%s']";
    public static final String INVITATION_CODE_TEXTBOX = "%sedtInvitationCode";
    public static final String SKIP_BUTTON = "%sbtnSkip";


    //MENUS
    public static final String MENU_BUTTONS = "//android.widget.CheckedTextView[contains(@text, '%s')]";

    //HOME SCREEN (CAR TYPE)
    public static final String CAR_IMAGE = "%simvVehicle";
    public static final String CAR_NAME = "%stv_vehicle";
    public static final String MAX_OF_SEAT = "%stv_seat";
    public static final String MAX_OF_LUGGAGE = "%stv_luggage";
    public static final String MINIMUM_FARE_LABEL = "%stvMinFare";
    public static final String BASE_FARE_LABEL = "%stvBaseFare";
    public static final String FARE_PER_KM_LABEL = "%stvPerKm";
    public static final String FARE_PER_MINUTE_LABEL = "%stvPerMin";
    public static final String NOTE_DESCRIPTION = "%stvDescription";
    public static final String SELECT_CAR_BUTTON = "%sbtn_select";
    public static final String PICKUP_TYPE_NOW = "%stv_time";
    public static final String PICKUP_TYPE = "%simvTime";
    public static final String BOOK_NOW_BUTTON = "%sbtnNo";

    public static final String VIEW_ALL_BUTTON = "%sview_all";
    public static final String CAR_TYPE_DYNAMIC = "//android.widget.TextView[@text='%s']";
    public static final String BACK_ICON = "%simvBack";
    public static final String PICKUP_TIME_NEXT_MONTH = "//android.widget.Button[2]";
    public static final String NEXT_BUTTON_SKIP_DO = "%sbtn_skip_des";


    //HOME SCREEN (MAP)
    public static final String CURRENT_LOCATION_LABEL = "%stvAddress";
    public static final String BACK_TO_CURRENT_GPS_BUTTON = "%simvLocation";
    public static final String MOVE_MAP_BUTTON = "%saction_map";
    public static final String PIN_ICON = "%spinView";

    //ADDRESS
    public static final String PICKUP_ADDRESS_TEXTBOX = "%stvPickup";
    public static final String DESTINATION_ADDRESS_TEXTBOX = "%stvDestination";
    public static final String ADDRESS_SUGGESTED = "//android.widget.TextView[contains(@text, '%s')]";
    public static final String ADDRESS_RESULT_NUMBER = "//android.view.ViewGroup[%s]/android.widget.TextView[@resource-id='com.mycar.passenger:id/tvNameLocation']";
    public static final String DO_PIN = "//android.view.View[@content-desc='Google Map']/android.view.View[2]";

    public static final String BANNER = "%simv_banner";
    public static final String DYNAMIC_BUTTON = "//android.widget.Button[@text='%s']";

}
