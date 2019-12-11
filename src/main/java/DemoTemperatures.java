import ch.hevs.cloudio.endpoint.CloudioAttribute;
import ch.hevs.cloudio.endpoint.CloudioObject;
import ch.hevs.cloudio.endpoint.Measure;
import ch.hevs.cloudio.endpoint.SetPoint;

public class DemoTemperatures extends CloudioObject {

    @Measure
    public CloudioAttribute<Double> temperature;

    @SetPoint
    public CloudioAttribute<Double> setPointTemperature;

}