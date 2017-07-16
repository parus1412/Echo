package EchoServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


import MyEchoException.MyEchoException;

import static com.sun.jndi.ldap.LdapCtx.DEFAULT_PORT;

public class EchoServer {
    private int port;
    private boolean isRunning;
    private ServerSocket serverSocket;

    private boolean isRunning() {
        return isRunning;
    }


    public EchoServer() throws MyEchoException, IOException {
        this(DEFAULT_PORT);
        this.isRunning = false;
    }

    public EchoServer(int port) throws IOException {
        this.port = port;
        this.isRunning = false;
    }

    public int getPort() {
        return port;
    }

    public void start() throws IOException {
        if ( !isRunning() ) {
            this.isRunning = !this.isRunning;
            this.serverSocket = new ServerSocket(this.port);
            MultiThreadingHandler serverSocket = new MultiThreadingHandler(this.serverSocket);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String userInput;

            serverSocket.start();

            while (((userInput = in.readLine()) != null) && !userInput.contains("shutdown\n")) {
//                userInput = in.readLine();
                System.out.println("Server user input: " + userInput);
            }
            serverSocket.interrupt();
            stop();


//            System.out.println(
//                "--------------------------------" + "\n" +
//                "Echo Server started" + "\n" +
//                "--------------------------------");
//
//        }
//
////        Socket clientSocket = this.serverSocket.accept();
////        System.out.println("Accepted connection from client");
//
//
//
//        while( isRunning() ) {
//            Socket clientSocket = this.serverSocket.accept();
//            System.out.println(
//                "--------------------------------" + "\n" +
//                "Accepted connection from client" + "\n" +
//                "--------------------------------");
//
//            String clientInput;
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            PrintWriter echo = new PrintWriter(clientSocket.getOutputStream(), true);
//
//            while (((clientInput = in.readLine()) != null) && !clientInput.contains("disconnect")) {
//                echo.println("Echo: " + clientInput);
//                System.out.println("Client input: " + clientInput);
//
//            }
//            System.out.println(
//                "--------------------------------" + "\n" +
//                "Client disconnected" + "\n" +
//                "--------------------------------");
//

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
