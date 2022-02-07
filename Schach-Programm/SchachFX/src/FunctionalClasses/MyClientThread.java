package FunctionalClasses;

public class MyClientThread implements Runnable{
    @Override
    public void run() {
        Client client = new Client();
        client.connectionToServer();
    }
}
