import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonThread extends JButton {

    private boolean fRun;

    public ButtonThread() {
        this.setBackground(Color.WHITE);
        this.addActionListener();
    }

    private void addActionListener() {
        if (this.getBackground() == Color.GREEN) {
            killThread();
            this.setBackground(Color.WHITE);
        }
    }

    public void startThread() {
        Thread t = new Thread();
        t.start();
    }

    public void run() {
        fRun = true;
        this.setBackground(Color.GREEN);
    }

    private void killThread() {
        fRun = false;
    }


}
