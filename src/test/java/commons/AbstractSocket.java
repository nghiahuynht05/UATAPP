package commons;

import interfaces.AbstractPagesUI;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;


public class AbstractSocket {

    static Socket socket;
    AbstractPagesUI abstractUI;

    public AbstractSocket(Socket socket) {
        this.socket = socket;
        abstractUI = new AbstractPagesUI();
    }

    public static void connectSocket() {
        IO.Options opts = new IO.Options();
        opts.forceNew = true;
        opts.reconnection = true;
        try {
            socket = IO.socket("https://dispatch.beta.qup.vn", opts);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                System.out.println("connect = " + objects);
                JSONObject phone = new JSONObject();
                try {
                    phone.put("number", "7400123456");
                    phone.put("country", "GB");
                } catch (
                        JSONException e) {
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
                socket.disconnect();
                socket.on("register", new Emitter.Listener() {
                    @Override
                    public void call(Object... objects) {
                        JSONObject obj = (JSONObject) objects[0];
                        System.out.println("register = " + obj);
                    }
                });
            }
        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                System.out.println(objects);
            }
        });
        socket.connect();
    }

//    public void disconectSocketEvent(Socket socket) {
//        socket.disconnect();
//    }

    public static void main(String[] args) {
        connectSocket();
    }
}
