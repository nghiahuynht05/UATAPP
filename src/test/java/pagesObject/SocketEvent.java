package pagesObject;

import commons.AbstractSocket;

import java.net.URISyntaxException;

public class SocketEvent extends AbstractSocket {

    public void connectSocketEvent(String url) throws URISyntaxException {
        connectSocket(url);
    }
}
