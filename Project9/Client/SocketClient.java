package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient extends JFrame implements ActionListener {
    JButton button;
    JPanel panel;
    JTextField textField;
    JTextArea chatLog;
    Socket socket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    String line;
    String userName;
    boolean isName = true;

    SocketClient() {  //Begin Constructor
        chatLog = new JTextArea("Enter your name: ");
        textField = new JTextField(20);
        button = new JButton("Send");
        button.addActionListener(this);
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        getContentPane().add(panel);
        panel.add("North", chatLog);
        panel.add("Center", textField);
        panel.add("South", button);
    }      //End Constructor

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == button) {
            //Send data over socket
            String text = textField.getText();
            if(isName) {
                userName = text;
                chatLog.append(text);
                isName = false;
            }else
                chatLog.append("\n" + userName + ": " + text);
            textField.setText("");
            out.println(userName + ": " + text);
            //Receive text from server
            try {
                line = in.readLine();
                System.out.println("Text received :" + line);//print console
            } catch (IOException e) {
                System.out.println("Read failed");
            }
        }
    }

    public void listenSocket() {
        try {
            socket = new Socket("localhost", 4444); //Create socket connection
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Unknown host :" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException" + e.getMessage());
        }
    }

    //Main method
    public static void main(String[] args) {
        SocketClient frame = new SocketClient();
        frame.setTitle(frame.userName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 250);
        frame.setVisible(true);
        frame.listenSocket();
    }
}
