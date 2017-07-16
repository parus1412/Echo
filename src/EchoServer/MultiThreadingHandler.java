package EchoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadingHandler extends Thread {
    private ServerSocket ss;
    private boolean isRunung = true;

    public MultiThreadingHandler(ServerSocket ss) {
        this.ss = ss;
    }

    public void run() {
        System.out.println("Echo Server started");

        while( true ) {
            Socket clientSocket = null;
            try {
                clientSocket = ss.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Accepted connection from client" );

            String clientInput;

            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            PrintWriter echo = null;
            try {
                echo = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                while (((clientInput = in.readLine()) != null) && !clientInput.contains("disconnect")) {
                    if (!isRunung) {
                        return;
                    }
                    echo.println("Echo: " + clientInput);
                    System.out.println("Client input: " + clientInput);


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Client disconnected ..." );

        }


    }

    public void interrupt() {
            isRunung = false;
        try {
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//        Socket clientSocket = this.serverSocket.accept();
//        System.out.println("Accepted connection from client");



}
