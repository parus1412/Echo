package EchoServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import MyEchoException.MyEchoException;

import static com.sun.jndi.ldap.LdapCtx.DEFAULT_PORT;

public class EchoServer {
    private int port;
    private boolean isRuning;
    private ServerSocket serverSocket;

    public EchoServer() throws MyEchoException, IOException {
        this(DEFAULT_PORT);
        this.isRuning = false;
        //this.serverSocket = new ServerSocket(this.port);
    }

    public EchoServer(int port) throws IOException {
        this.port = port;
        this.isRuning = false;
        //this.socket = new ServerSocket(this.port);
    }

    public boolean isRunning() {
        return isRuning;
    }

    public int getPort() {
        return port;
    }

    public void start() throws IOException {
        if ( !isRuning ) {
            this.isRuning = !this.isRuning;
            System.out.println("Start Echo Server");

            this.serverSocket = new ServerSocket(this.port);

        }


        while(isRuning) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Accepted connection from client");

            while ( isRuning ) {
                String s;
                Scanner in = new Scanner(clientSocket.getInputStream());
                    s = in.next();


                System.out.println(s);
            }
            System.out.println("Disconnected from client");

        }
    }

    public void stop() throws IOException {
        if ( isRuning ) {
            //serverSocket.close();
            this.isRuning = !this.isRuning;
        }
        System.out.println("Stop Echo Server");
    }
}
