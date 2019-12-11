import ch.hevs.cloudio.endpoint.CloudioEndpoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DemoHeaterApplication {

    static CloudioEndpoint myEndpoint;

    static Logger logger = LogManager.getLogger(DemoHeaterApplication.class);

    public static void main(String[] args) {

        Heater heater = new Heater(23.2, 25.1);
        heater.run();

    }
}
