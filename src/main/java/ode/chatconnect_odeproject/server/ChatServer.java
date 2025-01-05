package ode.chatconnect_odeproject.server;
import java.io.*;
import java.net.*;
import java.util.*;

import ode.chatconnect_odeproject.client.*;
import ode.chatconnect_odeproject.ui.*;

public class ChatServer {
    private static Map<String, ObjectOutputStream> clients = new HashMap<>();
    private static Queue<String> availableNames = new LinkedList<>(Arrays.asList("Diyar", "Omar", "Mateusz"));

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server gestartet...");

            while (true) {
                // Akzeptiere eingehende Client-Verbindungen
                Socket clientSocket = serverSocket.accept();
                System.out.println("Neuer Client verbunden: " + clientSocket);

                // Starte einen neuen Thread für jeden Client
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Thread für jeden Client
    static class ClientHandler extends Thread {
        private Socket socket;
        private String username;
        private ObjectOutputStream out;
        private ObjectInputStream in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                // Streams für den Client
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());

                // Benutzername vom Server zuweisen
                synchronized (availableNames) {
                    if (availableNames.isEmpty()) {
                        out.writeObject("Keine Namen mehr verfügbar. Verbindung geschlossen.");
                        socket.close();
                        return;
                    }
                    username = availableNames.poll();
                }

                System.out.println("Benutzername zugewiesen: " + username);

                // Füge den Benutzer zur Client-Liste hinzu
                synchronized (clients) {
                    clients.put(username, out);
                }

                // Sende den zugewiesenen Namen an den Client
                out.writeObject(username);
                out.flush();

                // Benachrichtige alle Clients über die aktualisierte Benutzerliste
                broadcastUserList();

                // Nachrichten vom Client empfangen
                String message;
                while ((message = (String) in.readObject()) != null) {
                    System.out.println("Nachricht von " + username + ": " + message);

                    // Nachricht formatieren (z. B. "Empfänger: Nachricht")
                    if (message.contains(":")) {
                        String[] parts = message.split(":", 2);
                        String receiver = parts[0].trim();
                        String msg = parts[1].trim();

                        // Sende die Nachricht an den Empfänger
                        sendMessageToClient(receiver, username + ": " + msg);
                    } else {
                        out.writeObject("Falsches Format! Benutze 'Empfänger: Nachricht'");
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(username + " hat die Verbindung getrennt.");
            } finally {
                // Entferne den Benutzer aus der Client-Liste
                synchronized (clients) {
                    clients.remove(username);
                }
                // Name wieder verfügbar machen
                synchronized (availableNames) {
                    if (username != null) {
                        availableNames.add(username);
                    }
                }
                // Benachrichtige alle Clients über die aktualisierte Benutzerliste
                broadcastUserList();
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Nachricht an bestimmten Client senden
        private void sendMessageToClient(String receiver, String message) throws IOException {
            synchronized (clients) {
                if (clients.containsKey(receiver)) {
                    clients.get(receiver).writeObject(message);
                    clients.get(receiver).flush();
                } else {
                    out.writeObject("Benutzer " + receiver + " ist nicht online.");
                }
            }
        }

        // Aktualisierte Benutzerliste an alle Clients senden
        private void broadcastUserList() {
            synchronized (clients) {
                List<String> userList = new ArrayList<>(clients.keySet());
                for (Map.Entry<String, ObjectOutputStream> client : clients.entrySet()) {
                    try {
                        client.getValue().writeObject(userList);
                        client.getValue().flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
