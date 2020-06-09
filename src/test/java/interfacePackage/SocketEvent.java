package interfacePackage;

import java.net.URISyntaxException;
import java.util.List;

public interface SocketEvent {

    public void connectSocket(String string, List<String> table) throws URISyntaxException;
    public void resetRegiter(List<String> table) throws URISyntaxException;
}
