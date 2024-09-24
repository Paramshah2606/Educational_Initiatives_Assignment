// Singleton pattern is part of creational pattern which ensures that class has only one instance
// Use case: Logger system for application
// Language used: Java

class Logger {
    private static Logger instance;

    private Logger() {
        System.out.println("Logger initialized..");
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("Log message: " + message);
    }
}

public class creational_2 {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log("This is an example of logger.");
    }
}

