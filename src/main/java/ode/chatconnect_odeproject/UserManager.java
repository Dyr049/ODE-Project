package ode.chatconnect_odeproject;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, String> userDatabase = new HashMap<>();
    private final String filePath = "users.txt"; // Speicherort der Benutzerdaten

    public UserManager() {
        loadFromFile(); // Benutzerdaten beim Start laden
    }

    // Login-Funktion
    public boolean login(String email, String password) {
        return userDatabase.containsKey(email) && userDatabase.get(email).equals(password);
    }

    // Registrierung
    public boolean register(String email, String password) {
        if (!userDatabase.containsKey(email)) {
            userDatabase.put(email, password);
            saveToFile(); // Daten nach Registrierung speichern
            return true;
        }
        return false; // Benutzer existiert bereits
    }

    // Benutzerdaten in Datei speichern
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : userDatabase.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
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
                    if (parts.length == 2) {
                        userDatabase.put(parts[0], parts[1]);
                    }
                }
            } catch (IOException e) {
                System.err.println("Fehler beim Laden der Benutzerdaten: " + e.getMessage());
            }
        }
    }
}
