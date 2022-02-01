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
        try{
            String name;
            toServer = new Socket(LoginController.ipClient,1234);
            streamFromServer = new BufferedReader(new InputStreamReader((toServer.getInputStream())));
            streamToServer = new PrintStream(toServer.getOutputStream());
            System.out.println("Enter Connection Name");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            //name = reader.readLine();
            //BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(LoginController.ipClient.getBytes())));
            name = toServer.getRemoteSocketAddress().toString();
            //name = LoginController.ipAddressID.toString();
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
