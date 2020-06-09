package interfacePackage;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;

public interface APIRequest {
    public String httpRequestAPI()throws JSONException, UnirestException;
    public void deletedCustomer() throws JSONException, UnirestException;
}
