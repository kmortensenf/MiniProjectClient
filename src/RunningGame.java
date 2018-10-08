import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class RunningGame {


    public static void createAndShowGUI(){
        JFrame GameWindow = new JFrame("AirHockey");
        GameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel emptyLabel = new JLabel("");
        emptyLabel.setPreferredSize(new Dimension(400, 400));


        GameWindow.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        GameWindow.pack();
        GameWindow.setVisible(true);

    }

    public static void main (String[] args){

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            createAndShowGUI();
            }
        });
    }
}
