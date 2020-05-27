package commons;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.stereotype.Service;
import interfacePackage.SocketEvent;

import java.net.URISyntaxException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.assertThat;
import static org.openqa.selenium.remote.ErrorCodes.TIMEOUT;

@Service
public class AbstractSocket implements SocketEvent {

    @Test(timeout = TIMEOUT)
    public void connectSocket(String url) throws URISyntaxException {
        String uri = url;
        Socket socket = IO.socket(uri);

        final BlockingQueue<Object> values = new LinkedBlockingQueue<Object>();

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... objects) {

                JSONObject phone = new JSONObject();
                try {
                    phone.put("number", "7400123456");
                    phone.put("country", "GB");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONObject obj = new JSONObject();
                try {
                    obj.put("platform", "android");
                    obj.put("phone", phone);
                    obj.put("fleetId", "haidr");
                    obj.put("appType", "driver");
                    obj.put("verifyCode", "3210");
                    obj.put("ime", "xxx");
                    obj.put("password", "password");
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
    }

    @Override
    public void disconectSocketEvent(String url) throws URISyntaxException {
        String uri = url;
        Socket socket = IO.socket(uri);
        socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                System.out.println("EVENT_DISCONNECT");
            }
        });
    }

}