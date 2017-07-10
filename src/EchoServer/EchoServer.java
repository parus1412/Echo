package EchoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import MyEchoException.MyEchoException;

import static com.sun.jndi.ldap.LdapCtx.DEFAULT_PORT;

public class EchoServer {
    private int port;
    private boolean isRunning;
    private ServerSocket serverSocket;
//    private ArrayList connections;

    public EchoServer() throws MyEchoException, IOException {
        this(DEFAULT_PORT);
        this.isRunning = false;
//        this.connections = new ArrayList();
    }

    public EchoServer(int port) throws IOException {
        this.port = port;
        this.isRunning = false;
//        this.connections = new ArrayList();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public int getPort() {
        return port;
    }

    public void start() throws IOException {
        if ( !isRunning() ) {
            this.isRunning = !this.isRunning;
            this.serverSocket = new ServerSocket(this.port);
            System.out.println("Echo Server started");
        }


        while( isRunning() ) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Accepted connection from client");

                BufferedReader out = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String clientInput;
                System.out.println(clientSocket.isConnected());

                while ((clientInput = out.readLine()) != null) {
                    System.out.println("Client input: " + clientInput);
                }




//                BufferedReader in = new InputStreamReader(clientSocket.getInputStream());
//                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//                String userInput;
//
//                while ((userInput = stdIn.readLine()) != null) {
//                    out.println(userInput);
//                    System.out.println("echo: " + in.readLine());
//                }
            System.out.println("Disconnected from client");

        }
    }

    public void stop() throws IOException {
        if ( isRunning ) {
            serverSocket.close();
            this.isRunning = !this.isRunning;
        }
        System.out.println("Stop Echo Server");
    }
}
