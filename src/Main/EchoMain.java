package Main;

import EchoServer.EchoServer;
import MyEchoException.MyEchoException;

import java.io.IOException;
import java.lang.Object;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoMain {

    public static void main(String[] args) throws IOException, MyEchoException {
        System.out.println("HI!");

        EchoServer serverDefault = new EchoServer();
        EchoServer server = new EchoServer(54321);

        System.out.println(server.getPort());
        System.out.println(serverDefault.getPort());

        server.start();

        Scanner in = new Scanner(System.in);

        if ( in.next() == "dissconnect" ) {
            server.stop();
        }

        server.stop();

//        int port = 4444;
//        ServerSocket serverSocket = new ServerSocket(port);
//        System.err.println("Started server on port " + port);
//
//        // repeatedly wait for connections, and process
//        while (true) {
//
//            // a "blocking" call which waits until a connection is requested
//            Socket clientSocket = serverSocket.accept();
//            System.err.println("Accepted connection from client");
//
//            // open up IO streams
//            In  in  = new In (clientSocket);
//            Out out = new Out(clientSocket);
//
//            // waits for data and reads it in until connection dies
//            // readLine() blocks until the server receives a new line from client
//            String s;
//            while ((s = in.readLine()) != null) {
//                out.println(s);
//            }
//
//            // close IO streams, then socket
//            System.err.println("Closing connection with client");
//            out.close();
//            in.close();
//            clientSocket.close();
//        }
//    }

    }
}
