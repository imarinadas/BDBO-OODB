package transactions;

import java.util.*;
import com.db4o.activation.*;

public class TemperatureSensorReadout extends transactions.SensorReadout {
    private final double temperature;
    
    public TemperatureSensorReadout(
            Date time,transactions.Car car,
            String description,double temperature) {
        super(time,car,description);
        this.temperature=temperature;
    }
    
    public double getTemperature() {
    	activate(ActivationPurpose.READ);
        return temperature;
    }

    @Override
    public String toString() {
        return super.toString()+" temp : "+temperature;
    }
}
