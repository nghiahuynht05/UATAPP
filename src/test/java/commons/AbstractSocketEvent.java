package commons;

import interfacePackage.SocketEvent;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.hamcrest.Matchers;
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

import static org.junit.Assert.assertThat;
import static org.openqa.selenium.remote.ErrorCodes.TIMEOUT;

@Service
public class AbstractSocketEvent implements SocketEvent {

    @Test(timeout = TIMEOUT)
    public void connectSocket(List<String> table) throws URISyntaxException {

        InputStream inputStream;
        IO.Options opts = new IO.Options();

        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = AbstractSocketEvent.class.getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            String uri = prop.getProperty("uriLab");
            opts.forceNew = true;
            opts.reconnection = false;
            Socket socket = IO.socket(uri, opts);

            final BlockingQueue<Object> values = new LinkedBlockingQueue<Object>();

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
                    System.out.println("register = " + obj);

                    // BlockingQueue offer(e): add new 1 values to queue
                    values.offer(args);
                }

            });
            socket.connect();

            try {
                // BlockingQueue take(): return value in queue if queue is empty it will waiting until an element becomes available.
                Object[] args = (Object[]) values.take();
                // Check args not empty and have data
                assertThat(args.length, Matchers.equalTo(1));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test(timeout = TIMEOUT)
    public void acceptPreSocketEvent() throws URISyntaxException {

        InputStream inputStream;
        IO.Options opts = new IO.Options();
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            inputStream = AbstractSocketEvent.class.getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            String uri = prop.getProperty("uriLab");
            opts.forceNew = true;
            opts.reconnection = false;
            Socket socket = IO.socket(uri, opts);

            final BlockingQueue<Object> values = new LinkedBlockingQueue<Object>();

            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                }
            }).on("rqJobPre", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    JSONObject obj = (JSONObject) objects[0];
                    System.out.println("rqJobPre = " + obj);

                    values.offer(objects);
                    try {
                        String bookId = obj.getString("bookId");
                        String bookFrom = obj.getString("bookFrom");
                        JSONObject params = new JSONObject();
                        params.put("bookId", bookId);
                        params.put("bookFrom", bookFrom);
                        socket.emit("acceptPre", params);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                ;
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
