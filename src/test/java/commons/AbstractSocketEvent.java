package commons;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import interfacePackage.SocketEvent;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.remote.ErrorCodes.TIMEOUT;

@Service
public class AbstractSocketEvent implements SocketEvent {
    private Socket socket;
    public String bookId;
    private static final Logger LOGGER = LogManager.getLogger(AbstractSocketEvent.class);

    Socket client() throws URISyntaxException {
        return client(createOptions());
    }

    Socket client(String path) throws URISyntaxException {
        return client(path, createOptions());
    }

    Socket client(IO.Options opts) throws URISyntaxException {
        return client(nsp(), opts);
    }

    Socket client(String path, IO.Options opts) throws URISyntaxException {
        return IO.socket(uri() + path, opts);
    }

    String uri() {
        InputStream inputStream;
        Properties prop = new Properties();
        String propFileName = "config.properties";

        inputStream = AbstractSocketEvent.class.getClassLoader().getResourceAsStream(propFileName);
        if (inputStream != null) {
            try {
                prop.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        String uri = prop.getProperty("uriLab");
        return uri;
    }
    String uriAPI() {
        InputStream inputStream;
        Properties prop = new Properties();
        String propFileName = "config.properties";

        inputStream = AbstractSocketEvent.class.getClassLoader().getResourceAsStream(propFileName);
        if (inputStream != null) {
            try {
                prop.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        String uriAPI = prop.getProperty("apiServer");
        return uriAPI;
    }

    String nsp() {
        return "/";
    }

    IO.Options createOptions() {
        IO.Options opts = new IO.Options();
        opts.forceNew = true;
        return opts;
    }

    @Test(timeout = TIMEOUT)
    public void connectSocket(String string, List<String> table) throws URISyntaxException {
        String rqEvent = "";
        String acceptEvent = "";

        final BlockingQueue<Object> values = new LinkedBlockingQueue<Object>();
        IO.Options opts = new IO.Options();
        opts.forceNew = true;
        opts.reconnection = false;
        socket = IO.socket(uri(), opts);

        if (string.equals("Now")) {
            rqEvent = "rqJob";
            acceptEvent = "accept";
        } else {
            rqEvent = "rqJobPre";
            acceptEvent = "acceptPre";
        }

        String finalAcceptEvent = acceptEvent;
        String finalRqEvent = rqEvent;

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... objects) {

                JSONObject phone = new JSONObject();
                try {
                    phone.put("number", table.get(8));
                    phone.put("country", table.get(9));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONObject obj = new JSONObject();
                try {
                    obj.put("platform", table.get(13));
                    obj.put("phone", phone);
                    obj.put("fleetId", table.get(12));
                    obj.put("appType", table.get(14));
                    obj.put("verifyCode", table.get(11));
                    obj.put("ime", table.get(10));
                    obj.put("password", table.get(15));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                socket.emit("register", obj);

            }
        }).on("register", new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                JSONObject obj = (JSONObject) args[0];
                LOGGER.info("Event: 'register': {}", obj);
                JSONObject location = new JSONObject();
                try {
                    location.put("geo", "[108.21174351895205,16.059379170460254]");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                socket.emit("updateLocation");
            }

        }).on(rqEvent, new Emitter.Listener() {
            @Override
            public void call(Object... objects) {

                JSONObject object = (JSONObject) objects[0];
                JSONObject params = new JSONObject();
                try {
                    String bookId = object.getString("bookId");
                    String bookFrom = object.getString("bookFrom");
                    params.put("bookId", bookId);
                    params.put("bookFrom", bookFrom);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                values.offer(objects);
                LOGGER.info("Event: '{}': {}", finalRqEvent, object);
                socket.emit(finalAcceptEvent, params);
            }
        }).on(finalAcceptEvent, new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                JSONObject response = (JSONObject) objects[0];
                LOGGER.info("Event: '{}': {}", finalAcceptEvent, response);
                try {
                    bookId = response.getString("BookId");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            socket.connect();
            Object[] args = (Object[])values.take();
            assertThat(args.length, is(1));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
}