# ODE-Project


# Beschreibung

Dieses Projekt ist ein Java-basiertes Chat-Programm mit einer grafischen Benutzeroberfläche (GUI), die mit JavaFX erstellt 
wurde. Es ermöglicht Echtzeit-Kommunikation zwischen Benutzern und bietet Funktionen wie Nachrichtenübermittlung, 
Benutzerverwaltung und passwortgeschützte Konten.

# Funktionen

Benutzer können sich anmelden und registrieren.

Sicheres Passwort-Management mit der Möglichkeit, das Passwort zu ändern.

Nachrichtenübermittlung zwischen Benutzern in Echtzeit.

Unterstützung für private Nachrichten.

Benutzeroberfläche basierend auf JavaFX.

Suchfunktion für die Kontaktliste.

Integrierte Funktion zur Änderung des Passworts direkt in der Anwendung.

# Architektur

Client-Komponenten:

ChatClient

LoginManager: Verwaltung der Benutzeranmeldeinformationen.

Server-Komponenten

ChatServer: Server zur Verwaltung der Client-Verbindungen und Nachrichtenweiterleitung.

ClientHandler: Verarbeitung von Client-Nachrichten und -Verbindungen auf Serverseite.

UI:

MainWindow: Hauptfenster der Anwendung, verwaltet Kontakte und Chat-Funktionalität.

LoginWindow: Login-Fenster für die Benutzeranmeldung.

UIElements

# Technologien

Programmiersprache: Java

GUI-Framework: JavaFX

Netzwerk: Java Sockets (TCP/IP)

Datenhaltung: Lokale Textdateien zur Speicherung von Benutzeranmeldedaten


### Voraussetzungen
  - Projektdetails
      - Info
      - Feedback zu Info eingeholt und eingearbeitet
      - PP1
      - PP2
      - PP3
      - PP4
- JavaDoc
- readme.md
- Vererbung
- Overriding
- Zugriffsrechte
- Exception Handling
- File IO
- Multithreading
- Aufgeräumte GUI
- Networking

### Must have features

- [] User kann sich mit seinem Anmeldedaten registrieren oder einloggen
- [] Aus der Liste, wo sich alle User befinden, können User ausgewählt und Konversationen gestartet werden
- [x] In der Liste kann nach Usern gesucht werden --> Diyar

### Should have

- [] UserInnen können einen Status angeben, der neben dem Namen erscheint
- [] User kann sein Passwort/Benutzernamen ändern
- [] Zeitanzeige beim verschicktem Nachricht

### Nice to have

- [] UserInnen können Profilbilder hinterlegen und ändern
- [] Nachdem der User sich eingeloggt hat werden seine Konversationen geladen
- [] Man kann Nachrichten innerhalb eines Chats durchsuchen

### Overkill

- [] Emojis können abgeschickt werden mit API
- [] One Time Message, button der eine Nachricht abschickt aber nicht im Hintergrund speichert
- [] User können Bilder senden



#### Sonstiges

- Nachrichten können weitergeleitet werden in andere Channel/Rooms/Chats
- Nachrichten können im nachhinein editiert werden
- Nachrichten/User fixieren
- Kontakt neu erstellen/neu auswählen
- Die Gruppen haben einen Namen, den man editieren kann
- Es können Gruppen mit mehr als 2 User erstellt werden
- Die UI des Users wird gespeichert und bei einer neuanmeldung wiederhergestellt

### Todo bis Dienstag 26.11

- Mockup erstellen für die Anzeige (Alle komponente müssen ersichtlich sein, sollte schön aussehen, das Programm Figma könnte dabei verwendet werden, auch die AnmeldeGUI) --> kann von Chatgpt hilfe genommen werden
- Klassendiagramm
- JavaFX nachdem der Mockup erstellt wurde








