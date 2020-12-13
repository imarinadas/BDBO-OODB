package transactions;

import java.util.*;
import com.db4o.activation.*;
import com.db4o.ta.*;

public class Car implements Activatable {
    private final String model;
    private Pilot pilot;
    private SensorReadout history;
    private transient Activator _activator;

    public Car(String model) {
        this.model=model;
        this.pilot=null;
        this.history=null;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public String getModel() {
    	activate(ActivationPurpose.READ);
        return model;
    }
    
    public SensorReadout getHistory() {
    	activate(ActivationPurpose.READ);
        return history;
    }
    
    public void snapshot() {
    	activate(ActivationPurpose.WRITE);
        appendToHistory(new TemperatureSensorReadout(
                new Date(),this,"oil",pollOilTemperature()));
        appendToHistory(new TemperatureSensorReadout(
                new Date(),this,"water",pollWaterTemperature()));
    }

    protected double pollOilTemperature() {
        return 0.1*countHistoryElements();
    }

    protected double pollWaterTemperature() {
        return 0.2*countHistoryElements();
    }

    @Override
    public String toString() {
    	activate(ActivationPurpose.READ);
        return model+"["+pilot+"]"+"/"+countHistoryElements();
    }
    
    private int countHistoryElements() {
    	activate(ActivationPurpose.READ);
        return (history==null ? 0 : history.countElements());
    }
    
    private void appendToHistory(SensorReadout readout) {
    	activate(ActivationPurpose.WRITE);
        if(history==null) {
            history=readout;
        }
        else {
            history.append(readout);
        }
    }

    @Override
    public void activate(ActivationPurpose purpose) {
        if(_activator != null) {
            _activator.activate(purpose);
        }
    }

    @Override
    public void bind(Activator activator) {
    	if (_activator == activator) {
    		return;
    	}
    	if (activator != null && _activator != null) {
            throw new IllegalStateException();
        }
        _activator = activator;
    }
}