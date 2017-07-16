package WazzupClient;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class WazzupClient {
    private Socket serverSocket;
    private boolean isConnected;

    public WazzupClient() {
        this.isConnected = false;
    }

    public void connect(InetAddress address, int port) throws IOException {
        if (!isConnected()) {
            this.isConnected = !this.isConnected;
            this.serverSocket = new Socket(address, port); /* zavernut v try catch */
            System.out.println("Connected to " + serverSocket.toString());
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter userOut = new PrintWriter(System.out, true);

        String input;
        String response;


        while( ((input = userInput.readLine()) != null) && !input.contains("disconnect") ) {
            out.println(input);
            if ( ((response = in.readLine()) == null) ) {
                System.out.println("No response from : " + serverSocket.toString());

                this.serverSocket.close();
                this.isConnected = !this.isConnected;
                return;
            } else {
                userOut.println(response);
            }

        }
        System.out.println("Disconnected from " + serverSocket.toString());
    }





    public boolean isConnected() {
        return this.isConnected;
    }

//    public void disconnection {
//        this.serverSocket.close();
//    }


}
