package imgcache;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;

public class NodesStarter {
    public final static String SERVER_CONFIG = "ignite-config/server-config.xml";

    public static void main(String[] args) {
        Ignition.start(SERVER_CONFIG);
    }
}
