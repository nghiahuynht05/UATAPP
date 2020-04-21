package commons;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Arrays;


public class AbstractSocket {


    public static void main(String[] args) throws URISyntaxException {
        final Socket socket;
        String uri = "https://dispatch.beta.qup/vn";
        socket = IO.socket(uri);
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
                    obj.put("fleetId", "yumi");
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
                    }
                });
            }
        });
    }
}
