package Main;

import EchoServer.EchoServer;
import WazzupClient.WazzupClient;
import MyEchoException.MyEchoException;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Scanner;

public class EchoMain {

    public static void main(String[] args) throws IOException, MyEchoException {
        Scanner in = new Scanner(System.in);
        EchoServer server = new EchoServer(54321);

        server.start();
//
//        server.stop();

    }
}
