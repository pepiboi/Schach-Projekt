package FunctionalClasses;

import Pieces.Board;
import controller.LoginController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
    static PrintStream streamToServer;
    static BufferedReader streamFromServer;
    static Socket toServer;
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

    public static void sendCurrentPosition(String witchPane){
        boolean running = true;
        while(running){
            if (Board.somethingMoved == true){
                System.out.println(Board.movedNodeToString);
                try {
                    toServer = new Socket(LoginController.ipClient,1234);
                    streamFromServer = new BufferedReader(new InputStreamReader((toServer.getInputStream())));
                    streamToServer = new PrintStream(toServer.getOutputStream());
                    streamToServer.println(Board.movedNodeToString);
                    streamToServer.println(witchPane);
                    System.out.println("Pane went through");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Board.somethingMoved = false;
                running = false;
                System.out.println("Running at sending Position set to false");
            }
        }
    }

    public static void clientHasOpenedBoardView(boolean isConnected){
        if (isConnected == true){
            try {
                //toServer = new Socket(LoginController.ipClient,1234);
                streamFromServer = new BufferedReader(new InputStreamReader((toServer.getInputStream())));
                streamToServer = new PrintStream(toServer.getOutputStream());
                streamToServer.println("boolean = "+isConnected);
                System.out.println("boolean = "+isConnected);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("isConnected = false");
        }
    }

    public static void main(String[] args) {
        //new Client();
    }
}
