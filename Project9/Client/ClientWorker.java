package Client;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class ClientWorker implements Runnable {
    private Socket client;
    private JTextArea textArea;

    public ClientWorker(Socket client, JTextArea textArea) {
        this.client = client;
        this.textArea = textArea;
    }

    public void run() {
        String line;
        BufferedReader in = null;
        PrintWriter out = null;
        Date objDate = new Date();
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("in or out failed" + e.getMessage());
        }
        textArea.append("\nConnection from: " + client.toString() + " at " + objDate);
        while (true) {
            try {
                line = in.readLine();
                //Send data back to client
                out.println(line);
                textArea.append("\n" + line);
            } catch (IOException e) {
                System.out.println("Read failed" + e.getMessage());
            }
        }
    }
}
