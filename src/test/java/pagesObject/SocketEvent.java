package pagesObject;

import commons.AbstractSocket;
import io.socket.client.Socket;

public class SocketEvent extends AbstractSocket {
    public SocketEvent(Socket socket) {
        super(socket);
    }

    public static void connectSocketEvent() {
        connectSocket();
    }
}
