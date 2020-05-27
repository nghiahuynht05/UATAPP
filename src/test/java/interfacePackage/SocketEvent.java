package interfacePackage;

import java.net.URISyntaxException;

public interface SocketEvent {

    public void connectSocket(String url) throws URISyntaxException;
    public void disconectSocketEvent(String url) throws URISyntaxException;
}
