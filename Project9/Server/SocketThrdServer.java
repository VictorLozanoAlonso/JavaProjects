package Server;

import Client.ClientWorker;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;

public class SocketThrdServer extends JFrame {
    Date objDate = new Date();
    JLabel label = new JLabel("MultiThreadServe started at: " + objDate.toString() +"\n\n");
    JPanel panel;
    JTextArea textArea = new JTextArea();
    ServerSocket server = null;

    SocketThrdServer() { //Begin Constructor
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        getContentPane().add(panel);
        panel.add("North", label);
        panel.add("Center", textArea);
    }    //End Constructor

    public void listenSocket() {
        try {
            server = new ServerSocket(4444);
        } catch (IOException e) {
            System.out.println("Could not listen on port 4444" + e.getMessage());
        }
        while (true) {
            ClientWorker w;
            try {
                w = new ClientWorker(server.accept(), textArea);
                Thread t = new Thread(w);
                t.start();
            } catch (IOException e) {
                System.out.println("Accept failed: 4444" + e.getMessage());
            }
        }
    }

    protected void finalize() {
        //Objects created in run method are finalized when program terminates and thread exits
        try {
            server.close();
        } catch (IOException e) {
            System.out.println("Could not close socket");
        }
    }

    public static void main(String[] args) {
        SocketThrdServer frame = new SocketThrdServer();
        frame.setTitle("Server Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 250);
        frame.setVisible(true);
        frame.listenSocket();
    }
}
