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
    	

        public class cardClass {
        	
        	private int cardValue;
        
    		public int getCardValue() {
    			return cardValue;
    		}

    		public void setCardValue(int cardValue) {
    			this.cardValue = cardValue;
    		}

    		
        	private String[] cardName = new String[51];
        	
        	private void cardName() {
            	cardName[0] = "hearths2";
            	cardName[1] = "hearths3";
            	cardName[2] = "hearths4";
            	cardName[3] = "hearths5";
            	cardName[4] = "hearths6";
            	cardName[5] = "hearths7";
            	cardName[6] = "hearths8";
            	cardName[7] = "hearths9";
            	cardName[8] = "hearths10";
            	cardName[9] = "hearthsJack";
            	cardName[10] = "hearthsQueen";
            	cardName[11] = "hearthsKing";
            	cardName[12] = "hearthsAce";
            	
            	cardName[13] = "spades2";
            	cardName[14] = "spades3";
            	cardName[15] = "spades4";
            	cardName[16] = "spades5";
            	cardName[17] = "spades6";
            	cardName[18] = "spades7";
            	cardName[19] = "spades8";
            	cardName[20] = "spades9";
            	cardName[21] = "spades10";
            	cardName[22] = "spadesJack";
            	cardName[23] = "spadesQueen";
            	cardName[24] = "spadesKing";
            	cardName[25] = "spadesAce";
            	
            	cardName[26] = "diamonds2";
            	cardName[27] = "diamonds3";
            	cardName[28] = "diamonds4";
            	cardName[29] = "diamonds5";
            	cardName[30] = "diamonds6";
            	cardName[31] = "diamonds7";
            	cardName[32] = "diamonds8";
            	cardName[33] = "diamonds9";
            cardName[34] = "diamonds10";
            	cardName[35] = "diamondsJack";
            	cardName[36] = "diamondsQueen";
            	cardName[37] = "diamondsKing";
            	cardName[38] = "diamondsAce";
            	
            	cardName[39] = "clubs2";
            	cardName[40] = "clubs3";
            	cardName[41] = "clubs4";
            	cardName[42] = "clubs5";
            	cardName[43] = "clubs6";
            	cardName[44] = "clubs7";
            	cardName[45] = "clubs8";
            	cardName[46] = "clubs9";
            cardName[47] = "clubs10";
            	cardName[48] = "clubsJack";
            	cardName[49] = "clubsQueen";
            	cardName[50] = "clubsKing";
            	cardName[51] = "clubsAce";
            	
        	}
            	
            	public void getCardName() {
        			return cardName;
        		}

        		public void setCardName(String cardName) {
        			this.cardName = cardName;
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
