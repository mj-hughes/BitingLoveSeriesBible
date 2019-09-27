package springxml.services;

public class LogServiceToFile implements LogService {

    @Override
    public void log(String message) {
        System.out.println("Will log to file: "+message);
    }

    @Override
    public void error(String message) {
        System.out.println("Will print error to file: "+message);

    }
}
