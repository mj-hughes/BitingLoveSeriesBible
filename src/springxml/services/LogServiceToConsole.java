package springxml.services;

public class LogServiceToConsole implements LogService {
    @Override
    public void log(String message) {
        System.out.println("Logging to console: "+message);
    }

    @Override
    public void error(String message) {
        System.out.println("Error to console: "+message);
    }
}
