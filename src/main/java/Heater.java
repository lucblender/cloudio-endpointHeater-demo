import ch.hevs.cloudio.endpoint.CloudioAttribute;
import ch.hevs.cloudio.endpoint.CloudioAttributeListener;
import ch.hevs.cloudio.endpoint.CloudioEndpoint;
import ch.hevs.cloudio.endpoint.CloudioEndpointListener;

class Heater implements Runnable, CloudioEndpointListener {
    CloudioEndpoint myEndpoint;
    DemoHeater demoHeater;

    private double ambientTemperature;
    private double setPointTemperature;

    Heater(double ambientTemperature, double setPointTemperature){
        try{
            myEndpoint = new CloudioEndpoint("bc0f1bf8-bdae-11e9-9cb5-2a2ae2dbcce4");
            myEndpoint.addEndpointListener(this);
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            myEndpoint.addNode("myHeater", DemoHeater.class);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        demoHeater = myEndpoint.getNode("myHeater");


        demoHeater.temperatures.setPointTemperature.addListener(new CloudioAttributeListener() {
            @Override
            public void attributeHasChanged(CloudioAttribute attribute) {
                setSetPointTemperature((double)attribute.getValue());
            }
        });

        this.ambientTemperature = ambientTemperature;
        this.setPointTemperature = setPointTemperature;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
                double diff = Math.abs(ambientTemperature-setPointTemperature)/5;
                if(diff < 0.02)
                    diff = 0.02;
                diff = diff + (0.5-Math.random())*diff;
                if(ambientTemperature<setPointTemperature)
                    ambientTemperature+=diff;
                else if(ambientTemperature>setPointTemperature)
                    ambientTemperature-=diff;

                System.out.println(ambientTemperature+ " "+ setPointTemperature);
                demoHeater.temperatures.temperature.setValue(ambientTemperature);
            }
            catch (Exception e){

            }
        }
    }

    public void setAmbientTemperature(double ambientTemperature) {
        this.ambientTemperature = ambientTemperature;
    }

    public void setSetPointTemperature(double setPointTemperature) {
        this.setPointTemperature = setPointTemperature;
    }

    public double getAmbientTemperature() {
        return ambientTemperature;
    }

    public double getSetPointTemperature() {
        return setPointTemperature;
    }

    @Override
    public void endpointIsOnline(CloudioEndpoint endpoint) {
        System.out.println("online");
    }

    @Override
    public void endpointIsOffline(CloudioEndpoint endpoint) {
        System.out.println("offline");
    }
}