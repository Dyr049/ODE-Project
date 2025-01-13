# Chat Connect


## Beschreibung

Dieses Projekt ist ein Java-basiertes Chat-Programm mit einer grafischen Benutzeroberfläche (GUI), die mit JavaFX erstellt 
wurde. Es ermöglicht Echtzeit-Kommunikation zwischen Benutzern und bietet Funktionen wie Nachrichtenübermittlung, 
Benutzerverwaltung und passwortgeschützte Konten.

## Funktionen

- Benutzer können sich anmelden und registrieren.

- Sicheres Passwort-Management mit der Möglichkeit, das Passwort zu ändern.

- Nachrichtenübermittlung zwischen Benutzern in Echtzeit.

- Unterstützung für private Nachrichten.

- Benutzeroberfläche basierend auf JavaFX.

- Suchfunktion für die Kontaktliste.

- Integrierte Funktion zur Änderung des Passworts direkt in der Anwendung.

## Architektur

- Client-Komponenten:

  - ChatClient

  - LoginManager: Verwaltung der Benutzeranmeldeinformationen.

- Server-Komponenten

  - ChatServer: Server zur Verwaltung der Client-Verbindungen und Nachrichtenweiterleitung.

  - ClientHandler: Verarbeitung von Client-Nachrichten und -Verbindungen auf Serverseite.

- GUI:

  - MainWindow: Hauptfenster der Anwendung, verwaltet Kontakte und Chat-Funktionalität.

  - LoginWindow: Login-Fenster für die Benutzeranmeldung.

  - UIElements

## Technologien

- Programmiersprache: Java

- GUI-Framework: JavaFX

- Netzwerk: Java Sockets (TCP/IP)

- Datenhaltung: Lokale Textdateien zur Speicherung von Benutzeranmeldedaten








