package org.example;

import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String serverUrl = "http://localhost:5055/chat";

        try (HubConnection hubConnection = HubConnectionBuilder.create(serverUrl).build()) {

            System.out.println("Welcome to the chat!");

            Scanner scanner = new Scanner(System.in);
            System.out.println("What is your name? : ");

            String userName = scanner.nextLine();
            System.out.println("Hi, : " + userName + "!");

            while(true) {

                Scanner messageScanner = new Scanner(System.in);
                System.out.println("\nMessage : ");

                String userMessage = messageScanner.nextLine();
                System.out.println(userMessage);

                // Send message handling
                hubConnection.on("Broadcast", (user, message) ->
                        System.out.println(user + ": " + message), String.class, String.class);

                // Receive message handling
                hubConnection.on("ReceiveMessage", (user, message) ->
                        System.out.println("[Private] " + user + ": " + message), String.class, String.class);

                hubConnection.start().blockingAwait();

                if (userMessage.equals("q")) {
                    break;
                }

                // Send a test message
                hubConnection.send("Broadcast", userName, userMessage);
            }
            hubConnection.stop();
            System.out.println("Disconnected from chat.");
        }
    }
}