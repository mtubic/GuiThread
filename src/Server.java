import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private boolean running = false;
    private int port;
    private List<Connection> connectList = new ArrayList<>();


    private ActionListener serverListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("Hallo");
        }
    };


    public Server(int port) {
        this.port = port;
    }

    public void start() {
        running = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try (ServerSocket server = new ServerSocket(port)) {
                    System.out.println("SERVER: Server started");
                    while (running) {
                        Connection connect = new Connection(server.accept());
                        connect.addActionListener(serverListener);
                        for (Connection Connect : connectList) {
                            connect.sendMessage("new partner:" + Connect.getNameOfPlayer());
                        }
                        connectList.add(connect);
                        System.out.println("SERVER: new connection");
                        connect.startConnect();
                    }
                    for (Connection connect : connectList) {
                        connect.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public static void main(String[] args) {
        Server s = new Server(1234);
        s.start();
    }

    public void stop(){
        running = false;
    }

}