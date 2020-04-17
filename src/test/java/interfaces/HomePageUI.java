package interfaces;

/**
 * Created by nghia.huynht on 4/17/2020.
 */
public class HomePageUI {

    public static final String HOME_BUTTON = "%sbtnHome";
    public static final String PERSONAL_BUTTON = "%simvAvatar";
    public static final String LOGOUT_BUTTON = "%stvLogout";

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
    public static final String BACK_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";
    public static final String PIN_ICON = "%spinView";

    //ADDRESS
    public static final String PICKUP_ADDRESS_TEXTBOX = "%stvPickup";
    public static final String DESTINATION_ADDRESS_TEXTBOX = "%stvDestination";
    public static final String ADDRESS_SUGGESTED = "//android.widget.TextView[contains(@text, '%s')]";
    public static final String ADDRESS_RESULT_NUMBER = "//android.view.ViewGroup[%s]/android.widget.TextView[@resource-id='com.mycar.passenger:id/tvNameLocation']";
    public static final String DO_PIN = "//android.view.View[@content-desc='Google Map']/android.view.View[2]";

}
