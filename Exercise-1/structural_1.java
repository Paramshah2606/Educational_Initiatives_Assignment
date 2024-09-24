// Adapter pattern is one type of structural pattern. It helps us in working with incompatible interfaces together.
// Use case: Temperature adapter to convert temperature reading into fehrenheit from celsius.
// Language used: Java

interface FahrenheitTemperature {
    double getTemperature();
}

class CelsiusTemperature {
    private double temperature;

    public CelsiusTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getCelsiusTemperature() {
        return temperature;
    }
}

class TemperatureAdapter implements FahrenheitTemperature {
    private CelsiusTemperature celsiusTemperature;

    public TemperatureAdapter(CelsiusTemperature celsiusTemperature) {
        this.celsiusTemperature = celsiusTemperature;
    }

    @Override
    public double getTemperature() {
        return (celsiusTemperature.getCelsiusTemperature() * 9/5) + 32; 
    }
}

public class structural_1 {
    public static void main(String[] args) {
        CelsiusTemperature celsius = new CelsiusTemperature(25); 
        FahrenheitTemperature fahrenheit = new TemperatureAdapter(celsius);

        System.out.println("Temperature in Fahrenheit: " + fahrenheit.getTemperature()); 
    }
}


