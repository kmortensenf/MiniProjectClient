import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Image;
import java.awt.image.BufferedImage;
//tænker at billederne skal være under runningGame, ikke sandt?

import cardGameClient.cardGameClient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.beans.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class RunningGame extends JFrame
{
		JButton Stand, Hit, Bet;
		JLabel Say;
		JTextArea Area;
		JTextField txtF1;
		JSlider howMuchBet;
		double i = 1000;
		double bet;

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
		
		Area = new JTextArea(15, 60);
		//Frame.WIDTH * 18, Frame.HEIGHT * 28 -- alternativ måde for size
		Area.setLineWrap(true);
		Area.setWrapStyleWord(true);
		JScrollPane Scroll = new JScrollPane(Area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Frame.add(Scroll);
		
		Stand = new JButton("Stand");
		Hit = new JButton("Hit");
		ListenForButton lFB = new ListenForButton();
		Stand.addActionListener(lFB);
		Hit.addActionListener(lFB);
		Bet = new JButton("Bet");
		Bet.addActionListener(lFB);
		
		/* for Slider
		howMuchBet = new JSlider(0, 100, 1);
		howMuchBet.setMinorTickSpacing(10);
		howMuchBet.setMajorTickSpacing(10);
		howMuchBet.setPaintTicks(true);
		howMuchBet.setPaintLabels(true);
		ListenForSlider lFS = new ListenForSlider();
		howMuchBet.addChangeListener(lFS);
		*/
		
		Frame.add(Stand);
		Frame.add(Hit);
		Frame.add(Bet);
		
		Say = new JLabel("how much do you wish to bet");
		Frame.add(Say);
		
		txtF1 = new JTextField("", 8);
		Frame.add(txtF1);
		
		//Frame.add(howMuchBet); --Slider
		
		this.add(Frame);
		
		this.setVisible(true);
		txtF1.requestFocus();
		
    }
		/* Test for importeret billede
        public class ImageInFrame {
            @SuppressWarnings("unused")
			public void main(String[] args) throws IOException {
                String path = "HestHest.jpg";
                File file = new File(path);
                BufferedImage image = ImageIO.read(file);
                JLabel label = new JLabel(new ImageIcon(image));
                JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.getContentPane().add(label);
                f.pack();
                f.setLocation(WIDTH,HEIGHT);
                f.setVisible(true);
            	final ImageIcon icon = new ImageIcon("C:\\Users\\you\\Desktop\\HestHest.jpg");

            }
        }
        */
        
		
    
    
    	private class ListenForButton extends cardGameClient implements ActionListener
    	{
    		
			public void actionPerformed(ActionEvent e) 
			{
				try {
					Socket socket = new Socket("localhost",8085);
					in = new DataInputStream(socket.getInputStream());
					out = new DataOutputStream(socket.getOutputStream());
					playerCards = 0;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(e.getSource() == Stand)
				{
					{
					Area.append("Tets for Stand");
					try {
						requestCards(in,out);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Area.append(" Card: " + Integer.toString(playerCards));
					Area.append("\n");
					}
				} else if (e.getSource() == Hit)
				{
					Area.append("Tets for Hit");
					Area.append("\n");
				} else if (e.getSource() == Bet)
				{
					try {
						bet = Double.parseDouble(txtF1.getText());
						//i -= howMuchBet.getValue();
						//Area.append("Your bet: " + howMuchBet.getValue() + " - You now Have " + i);
						//Area.append("\n");
						
						i -= bet;
						String getValue = txtF1.getText();
						Area.append("You bet: " + getValue + " You now have " + i + "\n");
						txtF1.setText("");
						txtF1.requestFocus();
					}
					
					catch(NumberFormatException excep)
					{
						JOptionPane.showMessageDialog(RunningGame.this, 
								"Please enter a number ", "Error", 
								JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
			
    	}
    	/*
    	private class ListenForSlider implements ChangeListener
    	{

    		@Override
    		public void stateChanged(ChangeEvent e) {
    			if (e.getSource() == howMuchBet)
    			{
    				Say.setText("Multiply result by what? " + howMuchBet.getValue());
    				
    			}
    			
    		}
    		
    	}*/
    	

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

            	public String[] getCardName() {
        			return cardName;
        		}

        		public void setCardName(String cardName) {
        			//this.cardName = cardName;
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