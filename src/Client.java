import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class Client {


    private String host;
    private int port;
    private GuiThreadExample callback;
    private Connection conn;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;

    }

    public void start(GuiThreadExample CallBack) {
        callback = CallBack;

        try {
            conn = new Connection(new Socket(host, port));
            conn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    getMessage(actionEvent.getActionCommand(), CallBack);
                }

            });
            conn.startConnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getMessage(String actionCommand, GuiThreadExample callBack) {
        System.out.println(actionCommand);
        System.out.println(actionCommand);

        if (actionCommand.contains("Congratulation, you win!")) {
            String winner = actionCommand.split(":")[1].split(";")[0];
            int winnerTime = Integer.parseInt(actionCommand.split("")[1].split(";")[1]);

        }
    }

    public void sendMessage(String actionCommand) {
        conn.sendMessage(actionCommand);
    }

    public void stop() {
        conn.close();
    }
}
