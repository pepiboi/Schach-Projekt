package classes;

import controller.LoginController;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
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
            toServer = new Socket("172.20.10.2",1234);
            toServer = new Socket(LoginController.ipClient,1234);
            streamFromServer = new BufferedReader(new InputStreamReader((toServer.getInputStream())));
            streamToServer = new PrintStream(toServer.getOutputStream());
            System.out.println("Enter Connection Name");
            BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(LoginController.ipClient.getBytes())));
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

    }
}
