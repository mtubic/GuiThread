import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class Client {


    private String host;
    private int port;
    private Game callback;
    private myConnection conn;

    public Client(String host, int port){
        this.host = host;
        this.port = port;

    }
    public void start (Game CallBack) {
        callback = CallBack;
        try {
            conn= new myConnection(new Socket(host,port));

            conn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    getMessage(actionEvent.getActionCommand(), CallBack);
                }

                private void getMessage(String actionCommand, Game callBack) {
                    System.out.println(actionCommand);
                    System.out.println(actionCommand);
                    
                    if(actionCommand.contains("Congratulation, you win!")){
                        String winner = actionCommand.split(":")[1].split(";")[0];
                        int winnerTime = Integer.parseInt(actionCommand.split("")[1].split(";")[1]);
                        
                    }
                }
            });
            conn.startConn();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class myConnection {
        public myConnection(Socket socket) {
        }

        public void addActionListener(ActionListener actionListener) {
        }

        public void startConn() {
        }

        public void close() {
        }
    }

    private class Game {
    }
    
    public void stop(){
        conn.close();
    }
}
