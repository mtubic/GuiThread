import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiThreadExample extends JFrame{

        public JButton topButton = new JButton("Start");
        public JButton bottomButton = new JButton("Click me");

        public GuiThreadExample (){
            super(" Threads und GUI");
            this.setSize(400, 400);



            this.setLayout(new BorderLayout());

            this.add(topButton, BorderLayout.NORTH);
            this.add(bottomButton, BorderLayout.SOUTH);

            bottomButton.setEnabled(false);

            topButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    new Thread(){
                        @Override
                        public void run() {
                            bottomButton.setEnabled(true);

                            try {
                                Thread.sleep(5000);
                            }catch (Exception e) {


                            }
                            bottomButton.setBackground(Color.GREEN);
                        }
                    }.start();
                }
            });

            bottomButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    bottomButton.setBackground(Color.red);
                    bottomButton.setEnabled(false);
                }
            });

            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.setVisible(true);
        }
    }
