package ode.chatconnect_odeproject.server;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class ChatServer {

    private static byte[] incoming = new byte[256];
    private static final int PORT = 8000;

    private static DatagramSocket socket;

    static {
        try {
            socket = new DatagramSocket(PORT);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList<Integer> users = new ArrayList<>();

    private static final InetAddress address;

    static {
        try {
            address = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {

        System.out.println("Server started on port " + PORT); //Server wurde auf dem PORT gestartet

        while (true) {
            DatagramPacket packet = new DatagramPacket(incoming, incoming.length); // packet vorbereiten
            try {
                socket.receive(packet); // Packet erhalten
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String message = new String(packet.getData(), 0, packet.getLength()); // String erstellen
            System.out.println("Server received: " + message);

            //Wenn ein neuer Client initialisiert wird, wird dieser in den Gruppenchat geladen
            if (message.contains("init; ")) {
                users.add(packet.getPort());
            }
            // Weiterleitung der Nachrichten
            else {
                int userPort = packet.getPort();  // Port von der Nachricht holen
                byte[] byteMessage = message.getBytes(); // String zu Bytes convertieren

                // Nachricht an alle weiterleiten (außer dem Absender)
                for (int forward_port : users) { //das : ist einfach ein for-each-loop
                    if (forward_port != userPort) {
                        DatagramPacket forward = new DatagramPacket(byteMessage, byteMessage.length, address, forward_port);
                        try {
                            socket.send(forward);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }


        }
    }
}