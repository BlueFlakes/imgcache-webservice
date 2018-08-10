package imgcache;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EntryPoint {
    private final static String CLIENT_CONFIG = "ignite-config/client-config.xml";

	public static void main(String[] args) {
	    SpringApplication.run(EntryPoint.class, args);
        Ignition.start(CLIENT_CONFIG);
	}
}