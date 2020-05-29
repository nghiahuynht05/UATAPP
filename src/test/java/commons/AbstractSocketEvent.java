package commons;

import interfacePackage.SocketEvent;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
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

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.remote.ErrorCodes.TIMEOUT;

@Service
public class AbstractSocketEvent implements SocketEvent {
    private Socket socket;

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

    String nsp() {
        return "/";
    }

    IO.Options createOptions() {
        IO.Options opts = new IO.Options();
        opts.forceNew = true;
        return opts;
    }

    @Test(timeout = TIMEOUT)
    public void connectSocket(List<String> table) throws URISyntaxException {

        final BlockingQueue<Object> values = new LinkedBlockingQueue<Object>();
        socket = client();

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
                /*JSONObject obj = (JSONObject) args[0];
                System.out.println("register = " + obj);*/

                // BlockingQueue offer(e): add new 1 values to queue
                values.offer(args);
            }

        });
        try {
            socket.connect();

            Object[] args = (Object[])values.take();
            assertThat(args.length, is(1));
            socket.close();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout = TIMEOUT)
    public void acceptPreSocketEvent() throws URISyntaxException {

        final BlockingQueue<Object> values = new LinkedBlockingQueue<Object>();
        socket = client();

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... objects) {
                System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz" + objects);
            }
        }).on("rqJob", new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                JSONObject obj = (JSONObject) objects[0];
                System.out.println("rqJob = " + obj);
                try {
                    try {
                        assertThat(values.take(), instanceOf(String.class));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String bookId = obj.getString("bookId");
                    String bookFrom = obj.getString("bookFrom");
                    JSONObject params = new JSONObject();
                    params.put("bookId", bookId);
                    params.put("bookFrom", bookFrom);
                    socket.emit("accept", params);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).on("accept", new Emitter.Listener() {
            @Override
            public void call(Object... objects) {
                final BlockingQueue<Object> values = new LinkedBlockingQueue<Object>();
                try {
                    socket.connect();
                    values.take();
                    socket.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
