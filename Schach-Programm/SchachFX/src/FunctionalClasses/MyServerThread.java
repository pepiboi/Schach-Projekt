package FunctionalClasses;

public class MyServerThread implements Runnable{
    @Override
    public void run() {
        Server server = new Server();
        server.connect();
    }
}
