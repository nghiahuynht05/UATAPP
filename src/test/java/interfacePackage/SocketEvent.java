package interfacePackage;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;

import java.net.URISyntaxException;
import java.util.List;

public interface SocketEvent {

    public void connectSocket(String string, List<String> table) throws URISyntaxException;
    public String httpRequestAPI() throws JSONException, UnirestException;
}
