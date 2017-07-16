package EchoServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


import MyEchoException.MyEchoException;

import static com.sun.jndi.ldap.LdapCtx.DEFAULT_PORT;

public class EchoServer {
    private int port;
    public static int conectionsCounter = 0;
    private boolean isRunning;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    private boolean isRunning() {
        return isRunning;
    }

    public EchoServer() throws MyEchoException, IOException {
        this(DEFAULT_PORT);
        this.isRunning = false;
        this.serverSocket = null;
        this.clientSocket = null;
    }

    public EchoServer(int port) throws IOException {
        this.port = port;
        this.isRunning = false;
        this.serverSocket = null;
        this.clientSocket = null;
    }

    public int getPort() {
        return port;
    }

    public void start() throws IOException {
        MultiConnectionsHandler multiConnectionsHandler = null;
        BufferedReader in = null;

        System.out.println("Starting Echo server ...");

        if ( !isRunning() ) {
            this.isRunning = !this.isRunning;
            this.serverSocket = new ServerSocket(this.port);
            System.out.println("Echo Server started ...");

            while ( isRunning() ) {
                System.out.println("Waiting for next connection ...");
                this.clientSocket = serverSocket.accept();

                System.out.println("Client " + clientSocket.toString() + " was connected.");
                conectionsCounter++;
                System.out.println("Now connected: " + conectionsCounter);

                multiConnectionsHandler = new MultiConnectionsHandler(this.clientSocket);

                multiConnectionsHandler.start();
            }

//            in = new BufferedReader(new InputStreamReader(System.in));
//            String userInput;

//            while ((userInput = in.readLine()) != null ) {
//                if ( userInput.contains("stop_server") ) {
////                    multiServerSocket.interrupt();
//                    stop();
//                }
//                System.out.println("Server user input: " + userInput);
//            }

        }
    }

    public void stop() throws IOException {
        if ( isRunning() ) {
            this.isRunning = !this.isRunning;
            this.serverSocket.close();
        }
        System.out.println("Stop Echo Server");
    }
}
