package ode.chatconnect_odeproject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class UserManager {
    private Map<String, String[]> userDatabase = new HashMap<>();
    private final String filePath = "user.txt"; // Speicherort der Benutzerdaten

    public UserManager() {
        loadFromFile(); // Benutzerdaten beim Start laden
    }

    // Login-Funktion
    public boolean login(String username, String password) {
        return userDatabase.containsKey(username) && userDatabase.get(username)[1].equals(password);
    }

    public String getName(String username) {
        return userDatabase.containsKey(username) ? userDatabase.get(username)[0] : null;
    }

    // Registrierung
    public boolean register(String name, String username, String password) {
        if (!userDatabase.containsKey(username)) {
            userDatabase.put(username, new String[]{name, password});
            saveToFile(); // Daten nach Registrierung speichern
            return true;
        }
        return false; // Benutzer existiert bereits
    }

    // Benutzerdaten in Datei speichern
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String[]> entry : userDatabase.entrySet()) {
                writer.write(entry.getValue()[0] + "," + entry.getKey() + "," + entry.getValue()[1]);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern der Benutzerdaten: " + e.getMessage());
        }
    }

    // Benutzerdaten aus Datei laden
    private void loadFromFile() {
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        userDatabase.put(parts[1], new String[]{parts[0], parts[2]});
                    }
                }
            } catch (IOException e) {
                System.err.println("Fehler beim Laden der Benutzerdaten: " + e.getMessage());
            }
        }
    }
}
