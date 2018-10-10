import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Dimension;

public class RunningGame extends JFrame
{
		JButton Stand, Hit, Bet;
		JTextArea Area;
		JTextField txtF1;

    public static void createAndShowGUI()
    {
    	/*
        JFrame GameWindow = new JFrame("Black Jack");
        GameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel emptyLabel = new JLabel("");
        emptyLabel.setPreferredSize(new Dimension(800, 800));


        GameWindow.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        GameWindow.pack();
        GameWindow.setVisible(true);
    	 */
    	new RunningGame();
    }
    
    public RunningGame()
    {
    	Toolkit t = Toolkit.getDefaultToolkit();
		 
		
		Dimension d = t.getScreenSize();
		this.setSize(d.width / 2, d.height / 2);
		int xPos = (d.width / 2) - (this.getWidth() / 2);
		int yPos = (d.height / 2) - (this.getHeight() / 2);
		
		this.setLocation(xPos, yPos);
		
		//this.setResizable(false);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Test Frame");
		
		JPanel Frame = new JPanel();
		
		Area = new JTextArea(15, 40);
		Area.setLineWrap(true);
		Area.setWrapStyleWord(true);
		JScrollPane Scroll = new JScrollPane(Area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Frame.add(Scroll);
		
		Stand = new JButton("Stand");
		Bet = new JButton("Bet");
		Hit = new JButton("Hit");
		ListenForButton lFB = new ListenForButton();
		Stand.addActionListener(lFB);
		Bet.addActionListener(lFB);
		Hit.addActionListener(lFB);
		Frame.add(Stand);
		Frame.add(Bet);
		Frame.add(Hit);
		
		this.add(Frame);
		
		this.setVisible(true);
		
    }
    
    	private class ListenForButton implements ActionListener
    	{

			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == Stand)
				{
					Area.append("Tets for Stand");
					Area.append("\n");
				} else if (e.getSource() == Hit)
				{
					Area.append("Tets for Hit");
					Area.append("\n");
				} else if (e.getSource() == Bet)
				{
					Area.append("Tets for Bet");
					Area.append("\n");
				}
				
			}
    		
    	}
	
	public static void main (String[] args){

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            createAndShowGUI();
            }
        });
    }
}
