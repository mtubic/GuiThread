import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Connection {

    private DataOutputStream out;
    private DataInputStream in;
    private boolean running = false;
    private String name;

    public void startConnect() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                running = true;
                while (running) {
                    try {
                        String input = in.readUTF();
                    } catch (IOException e) {
                        Connection.this.close();
                    }
                }
            }
        }).start();
    }

    public Connection(Socket socket) {
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String msg) {
        try {
            out.writeUTF(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            Connection.this.close();
        }
    }

    public void close() {
        running = false;
        try {
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNameOfPlayer() {
        return this.name;
    }

    public void addActionListener(ActionListener serverListener) {
    }

}