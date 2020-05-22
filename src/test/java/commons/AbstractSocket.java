package commons;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;
import pagesObject.SocketEvent;

import java.net.URISyntaxException;


public class AbstractSocket {
    SocketEvent socket;

    public AbstractSocket(SocketEvent socket) {
        this.socket = socket;
    }

    public static void connectSocket(String url) throws URISyntaxException {
        String uri = url;
        Socket socket = IO.socket(uri);
        socket.on("connect", new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                System.out.println("connect = " + objects);

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
                socket.on("register", new Emitter.Listener() {
                    @Override
                    public void call(Object... register) {
                        System.out.println("register = " + register);
                        JSONObject obj = (JSONObject) register[0];
                        System.out.println("register = " + obj);

                    }
                });
            }
        });
        socket.connect();
    }

    public static void disconectSocketEvent(Socket socket) {
        socket.disconnect();
    }

//    public static void main(String[] args) throws URISyntaxException {
//        String uri = "https://dispatch.beta.qup.vn";
//        final Socket socket = IO.socket(uri);
//        connectSocket("https://dispatch.beta.qup.vn");
//        disconectSocketEvent(socket);
//    }
}
