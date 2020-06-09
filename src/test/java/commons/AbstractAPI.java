package commons;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import interfacePackage.APIRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.remote.ErrorCodes.TIMEOUT;

public class AbstractAPI implements APIRequest {
    private static final Logger LOGGER = LogManager.getLogger( AbstractSocketEvent.class );

    String uriAPI() {
        InputStream inputStream;
        Properties prop = new Properties();
        String propFileName = "config.properties";

        inputStream = AbstractSocketEvent.class.getClassLoader().getResourceAsStream( propFileName );
        if (inputStream != null) {
            try {
                prop.load( inputStream );
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                throw new FileNotFoundException( "property file '" + propFileName + "' not found in the classpath" );
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        String uriAPI = prop.getProperty( "apiServer" );
        return uriAPI;
    }

    @Test(timeout = TIMEOUT)
    public String httpRequestAPI() throws JSONException, UnirestException {
        final BlockingQueue<Object> values = new LinkedBlockingQueue<Object>();

        HttpResponse<JsonNode> response = null;
        response = Unirest.post( uriAPI() + "/api/user/login" )
                .header( "Content-Type", "application/json" )
                .body( "{\"username\":\"auto_user01\",\"password\":\"Qup@12345\"}" )
                .asJson();
        JSONObject jsonObject = response.getBody().getObject();
        values.offer( jsonObject );
        try {
            JSONObject args = (JSONObject) values.take();
            assertThat( args.length(), is( 1 ) );

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JSONObject resObj = jsonObject.getJSONObject( "res" );
        Gson gson = new Gson();
        String token = gson.toJson( resObj.get( "token" ) ).toString();
        LOGGER.info( "Token login: {}", token );

        if (token != null) {
            return token;
        } else {
            return null;
        }
    }

    public void deletedCustomer() throws JSONException, UnirestException {
        final BlockingQueue<Object> values = new LinkedBlockingQueue<Object>();
        String token = httpRequestAPI();

        Map<String, String> headers = new HashMap<>();
        headers.put( "Content-Type", "application/json" );
        headers.put( "Authorization", token );

        JSONObject objBody = new JSONObject();
        objBody.put( "limit", 50 );

        HttpResponse<JsonNode> response = null;
        response = Unirest.post( uriAPI() + "/api/customer/multi-delete" )
                .headers( headers )
                .body( objBody )
                .asJson();
        JSONObject jsonObject = response.getBody().getObject();
        values.offer( jsonObject );
        try {
            JSONObject args = (JSONObject) values.take();
            assertThat( args.length(), is( 1 ) );

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String resObj = jsonObject.getJSONObject( "error" ).toString();
    }
}
