import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GuiThreadExample extends JFrame {

    private static int x = 4;
    private static int y = 4;


    private JButton topButton = new JButton("Start");
    private JButton bottomButton = new JButton("Click me");
    // private ButtonThread [][] buttonField = new ButtonThread[x][y];
    private JPanel panel = new JPanel((new GridLayout(x, y)));
    private boolean fRun;//permanentes durchlaufen des Programmes

    public GuiThreadExample() {
        super("Threads und GUI");
        this.setSize(1200, 900);

        this.add(panel);
        this.setVisible(true);


        List<JButton> buttonList = new ArrayList<>();
        int i = 0;

        for (i = 1; i <= 16; i++) {
            JButton button = new JButton("");
            panel.add(button);
            button.setEnabled(true);
            button.setBackground(Color.GRAY);
        }


        Random random = new Random();
        int value = random.nextInt(5) + 2;

        this.setLayout(new BorderLayout());

        this.add(panel, BorderLayout.CENTER);
        this.add(topButton, BorderLayout.NORTH);
        this.add(bottomButton, BorderLayout.SOUTH);

        topButton.setBackground(Color.BLUE);

        bottomButton.setEnabled(false);


        topButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Thread() {
                    public void run() {
                        bottomButton.setEnabled(true);
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        bottomButton.setBackground(Color.BLUE);
                    }

                }.start();
            }
        });


        bottomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bottomButton.setBackground(null);
                bottomButton.setEnabled(false);

            }
        });

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        run(panel);
    }

    private void run (JPanel p) {
        fRun = true;
        int time = 0;
        while (fRun) {
            boolean noGreen = true;
            int checkGreen = 0;

            for (int i = 0; i < p.getComponentCount(); ++i) {
                    if (p.getComponent(i).getBackground() == Color.GREEN) {
                        checkGreen++;

                    }
            }
            if (checkGreen > 0) {
                noGreen = false;
            }
            if (noGreen) {
                try {
                    Thread.sleep(time);
                    Random rand = new Random();
                    int buttonAmount = rand.nextInt(3) + 1;

                    for (int k = 0; k < buttonAmount; k++) {
                        Random r = new Random();

                        panel.getComponent(r.nextInt(15)).setBackground(Color.GREEN);
                        //p[i][j].startThread();

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

