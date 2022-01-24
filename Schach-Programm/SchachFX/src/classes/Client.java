package classes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
    PrintStream streamToServer;
    BufferedReader streamFromServer;
    Socket toServer;
    public Client()
    {
        connectionToServer();
    }
    private void connectionToServer()
    {
        try{//im Socket muss die IP geändert werden
            String name;
            toServer = new Socket("localhost",1234);
            streamFromServer = new BufferedReader(new InputStreamReader((toServer.getInputStream())));
            streamToServer = new PrintStream(toServer.getOutputStream());
            System.out.println("Enter Connection Name");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            name = reader.readLine();
            streamToServer.println(name);
            String str = streamFromServer.readLine();
            System.out.println("The Server Says "+str);
        }
        catch(Exception e)
        {
            System.out.println("Exception "+e);
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}
