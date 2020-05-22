package pagesObject;

import commons.AbstractSocket;

import java.net.URISyntaxException;

public class SocketEvent extends AbstractSocket {
    public SocketEvent(SocketEvent socket) {
        super(socket);
    }

    public void connectSocketEvent(String url) throws URISyntaxException {
        connectSocket(url);
    }
}
