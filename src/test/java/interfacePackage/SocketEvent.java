package interfacePackage;

import java.net.URISyntaxException;
import java.util.List;

public interface SocketEvent {

    public void connectSocket(List<String> table) throws URISyntaxException;
    public void acceptPreSocketEvent() throws URISyntaxException;
}
