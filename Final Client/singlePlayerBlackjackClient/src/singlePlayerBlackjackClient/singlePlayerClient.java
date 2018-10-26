package singlePlayerBlackjackClient;

	import java.awt.*;
	import java.awt.event.*;
	import java.awt.image.BufferedImage;
	import java.io.DataInputStream;
	import java.io.DataOutputStream;
	import java.io.IOException;
	import java.net.Socket;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
	import java.util.Arrays;

	import javax.swing.*;

	public class singlePlayerClient extends Card {
		
		private static Socket socket;
		private static DataInputStream in;
		private static DataOutputStream out;
		private static int playerNum;
		private static int [] playerCards = new int[26];
		private static int [] dealerCards = new int[26];
		private static int handsPlayed = 0;
		private static int dealerCard1Value = 0;
		private static int playerCardsValue;
		private static int dealerCardsValue;
		private static boolean standBoolean = false;
		private static boolean dealBoolean = false;
		private static JFrame frame;
		private static JFrame menu;
		private static JTextArea TextArea;
		private static JPanel panel;
		private static JPanel menuPanel;
		
		public static void main(String [] args) throws IOException {
			socket = new Socket("localhost",8087);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			
			// Player receiving their number
			playerNum = in.readInt();
			
			//Filling all card arrays with -1 before starting, since we have a cardNumber == 0
			Arrays.fill(playerCards, -1);
			Arrays.fill(dealerCards, -1);
			
			//Calling mainMenu() method displaying the small menu showing the player number and 'Start Game' button
			mainMenu();
		}

		public singlePlayerClient() throws IOException, URISyntaxException {
			createArray();
			panel = new JPanel();
			panel.setLayout(null);
			panel.setBackground(new Color(0,128,0));
			// Text area displaying the values of cards and more
			TextArea = new JTextArea("Blackjack!",25,40);
			Dimension sizeTextArea = TextArea.getPreferredSize();
			TextArea.setBounds(750, 300, sizeTextArea.width, sizeTextArea.height);
			// Blackjack text in the top of the window
			JLabel blackjackName = new JLabel("Blackjack!");
			// Player number displayed at the top
			JLabel playerNumGame = new JLabel("You are player: " + playerNum);
			// Number of hands played displayed at the top
			JLabel handsNum = new JLabel("Hands played: " + handsPlayed);
			blackjackName.setFont(new Font("Arial",Font.PLAIN,35));
			Dimension sizeName = blackjackName.getPreferredSize();
			blackjackName.setBounds(900, 100, sizeName.width, sizeName.height);
			playerNumGame.setFont(new Font("Arial",Font.PLAIN,20));
			playerNumGame.setBounds(900, 150, sizeName.width, sizeName.height);
			handsNum.setFont(new Font("Arial",Font.PLAIN,20));
			handsNum.setBounds(905, 200, sizeName.width, sizeName.height);
			// Adding TextArea and 'Blackjack!' and player number to the panel
			panel.add(blackjackName);
			panel.add(playerNumGame);
			panel.add(handsNum);
			panel.add(TextArea);
			// Adding the panel to the frame
			frame.add(panel);
			// Deal cards button
			JButton Deal = new JButton("Deal Cards");
			Dimension sizeDeal = Deal.getPreferredSize();
			Deal.setBounds(750, 750, sizeDeal.width, sizeDeal.height);
			// Hit button
			JButton Hit = new JButton("Hit");
			Dimension sizeHit = Hit.getPreferredSize();
			Hit.setBounds(900, 750, sizeHit.width, sizeHit.height);
			// Stand button
			JButton Stand = new JButton("Stand");
			Dimension sizeStand = Stand.getPreferredSize();
			Stand.setBounds(1000, 750, sizeStand.width, sizeStand.height);
			// Reset button
			JButton Reset = new JButton("Reset");
			Dimension sizeReset = Reset.getPreferredSize();
			Reset.setBounds(1115, 750, sizeReset.width, sizeReset.height);
			// Main menu button
			JButton mainMenu = new JButton("Main Menu");
			Dimension sizeBackMenu = mainMenu.getPreferredSize();
			mainMenu.setBounds(1700, 900, sizeBackMenu.width, sizeBackMenu.height);
			// Adding all buttons to the panel
			panel.add(mainMenu);
			panel.add(Deal);
			panel.add(Hit);
			panel.add(Stand);
			panel.add(Reset);
			// Adding ActionListeners to the buttons
			Deal.addActionListener(new dealCards());
			Hit.addActionListener(new hit());
			Stand.addActionListener(new stand());
			Reset.addActionListener(new reset());
			mainMenu.addActionListener(new mainMenu());
			// Setting the frame to visible
			frame.setVisible(true);
		}
		
		public singlePlayerClient(int cardNumber, BufferedImage cardImage) throws UnknownHostException, IOException {
			super(cardNumber, cardImage);
		}
		
		/**
		 * dealCards ActionListener.
		 * This is what happens when the 'Deal' button is pressed
		 */
		static class dealCards implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				try {
					if(dealBoolean == false) {
						handsPlayed++;
						out.writeInt(1);
						dealCards();
						displayPlayerCards();
						displayDealerCards();
						dealBoolean = true;
						if (playerCardsValue == 21 && playerCardsValue > dealerCardsValue) {
							TextArea.append("\nYou got blackjack!\nYou win!");
							standBoolean = true;
							displayDealerCards();
						} else if (dealerCardsValue == 21 && dealerCardsValue > playerCardsValue) {
							TextArea.append("\nDealer got blackjack!\nDealer wins!");
							standBoolean = true;
							displayDealerCards();
						} else {
							TextArea.append("\nThe value of your cards are: " + playerCardsValue);
							TextArea.append("\nThe value of the dealer cards are: " + dealerCard1Value + " + X");
						}
					} else {
						TextArea.append("\nYou already dealt cards!");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		/**
		 * hit ActionListener.
		 * This is what happens when the 'Hit' button is pressed
		 */
		static class hit implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				try {
					if (dealBoolean == true && standBoolean == false) {
						if (playerCardsValue < 21) {
							out.writeInt(2);
							requestHit();
							displayHit();
							if (playerCardsValue < 21) {
							TextArea.append("\nYou chose to hit\nYour new value is: " + playerCardsValue);
							} else if (playerCardsValue > 21) {
								TextArea.append("\nYou chose to hit\nYour new value is: " + playerCardsValue);
								TextArea.append("\nYou are over 21");
							}
						} else if (playerCardsValue > 21) {
							TextArea.append("\nYou are over 21\nDealer wins!");
						} else if (playerCardsValue == 21 && playerCards[2] == -1) {
							TextArea.append("\nYou have blackjack!");
						}
					} else if (dealBoolean == false) {
						TextArea.append("\nYou need to deal cards first");
					} else {
						TextArea.append("\nYou need to reset");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		/**
		 * stand ActionListener.
		 * This is what happens when the 'Stand' button is pressed
		 */
		static class stand implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				try {
					if (dealerCardsValue < 17 && dealBoolean == true && standBoolean == false && playerCardsValue < 21) {
						standBoolean = true;
						displayDealerCards();
						while (dealerCardsValue < 17) {
							out.writeInt(3);
							requestStand();
							displayDealerStand();
						}
						TextArea.append("\nDealer total value is: " + dealerCardsValue);
						if (dealerCardsValue > playerCardsValue) {
							TextArea.append("\nDealer wins!");
						} else if (playerCardsValue > dealerCardsValue) {
							TextArea.append("\nYou win!");
						} else if (playerCardsValue == dealerCardsValue) {
							TextArea.append("\nIt is a draw");
						}
					} else if (dealerCardsValue > 17 && dealBoolean == true && standBoolean == false && playerCardsValue < 21 
							|| dealerCardsValue == 17 && dealBoolean == true && standBoolean == false && playerCardsValue < 21) {
						standBoolean = true;
						displayDealerCards();
						TextArea.append("\nDealer total value is: " + dealerCardsValue);
						if (dealerCardsValue > playerCardsValue) {
							TextArea.append("\nDealer wins!");
						} else if (playerCardsValue > dealerCardsValue) {
							TextArea.append("\nYou win!");
						} else if (playerCardsValue == dealerCardsValue) {
							TextArea.append("\nIt is a draw");
						}
					} else if (playerCardsValue > 21 && standBoolean == false) {
						standBoolean = true;
						displayDealerCards();
						TextArea.append("\nYou are over 21");
					} else if (playerCardsValue == 21 && playerCards[2] == -1 && standBoolean == false) {
						TextArea.append("\nYou have blackjack!");
					} 
					if (standBoolean == true) {
						TextArea.append("\nYou need to reset");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}	
		}
		/**
		 * reset ActionListener.
		 * This is what happens when the 'Reset' button is pressed
		 */
		static class reset implements ActionListener {
			
			public void actionPerformed(ActionEvent e) {
				try {
					resetHands();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		/**
		 * startGame ActionListener.
		 * This is what happens when the 'Start Game' button is pressed
		 */
		static class startGame implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				try {
					startGame();
					try {
						new singlePlayerClient();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					menu.setVisible(false);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		/**
		 * mainMenu ActionListener.
		 * This is what happens when the 'Main Menu' button is pressed
		 */
		static class mainMenu implements ActionListener {
			
			public void actionPerformed(ActionEvent e) {
				try {
					resetHands();
					mainMenu();
					frame.setVisible(false);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		}
		
		public static void dealCards() throws IOException {
			//Player card 1 & 2 being assigned as well as the total value
			playerCards[0] = in.readInt();
			playerCards[1] = in.readInt();
			playerCardsValue = in.readInt();
			
			//Dealer card 1 & 2 being assigned as well as the value of the first card and the total value
			dealerCards[0] = in.readInt();
			dealerCards[1] = in.readInt();
			dealerCard1Value = in.readInt();
			dealerCardsValue = in.readInt();
		}

		public static void displayPlayerCards() throws IOException {
			//Displaying player cards and indication to which ones are the player cards
			JLabel playerCard1 = new JLabel(new ImageIcon(cardArray[playerCards[0]].getCardImage()));
			Dimension sizeCards = playerCard1.getPreferredSize();
			playerCard1.setBounds(250, 100, sizeCards.width, sizeCards.height);
			
			JLabel playerCard2 = new JLabel(new ImageIcon(cardArray[playerCards[1]].getCardImage()));
			playerCard2.setBounds(375, 100, sizeCards.width, sizeCards.height);
			
			JLabel cardString1 = new JLabel(new String("Player cards: "));
			cardString1.setFont(new Font("Arial",Font.PLAIN,20));
			Dimension sizeString = cardString1.getPreferredSize();
			cardString1.setBounds(100, 150,sizeString.width, sizeString.height);
			
			panel.add(playerCard1);
			panel.add(playerCard2);
			panel.add(cardString1);
			panel.repaint();
		}
		
		public static void displayDealerCards() throws IOException {
			/**
			 * Dealer card 1 and name if standBoolean == false
			 * standBoolean == false when 'Stand' button has not been pressed
			 * It will therefore only display the first dealer card
			 */
			if (standBoolean == false) {
			JLabel dealerCard1 = new JLabel(new ImageIcon(cardArray[dealerCards[0]].getCardImage()));
			Dimension sizeCards = dealerCard1.getPreferredSize();
			dealerCard1.setBounds(1250, 100,sizeCards.width, sizeCards.height);
			
			JLabel cardString = new JLabel(new String("Dealer cards: "));
			cardString.setFont(new Font("Arial",Font.PLAIN,20));
			Dimension sizeString = cardString.getPreferredSize();
			cardString.setBounds(1125, 150,sizeString.width, sizeString.height);
			
			panel.add(dealerCard1);
			panel.add(cardString);
			
			/**
			 * Dealer card 2 if standBoolean == true
			 * standBoolean == true when 'Stand' button has been pressed
			 * It will therefore only display the second dealer card when the players have pressed stand
			 */
		} else if (standBoolean == true) {
			JLabel dealerCard2 = new JLabel(new ImageIcon(cardArray[dealerCards[1]].getCardImage()));
			Dimension sizeCards = dealerCard2.getPreferredSize();
			dealerCard2.setBounds(1375, 100,sizeCards.width, sizeCards.height);
			
			panel.add(dealerCard2);
			}
			
			panel.repaint();
		}
		
		
		public static void requestHit() throws IOException {
			/**
			 * Player card 3 assigning if 'Hit' button is pressed and playerCards[2] has not been assigned
			 * Player card 4 assigning if 'Hit' button is pressed and playerCards[3] has not been assigned
			 * Player card 5 assigning if 'Hit' button is pressed and playerCards[4] has not been assigned
			 */
				if (playerCards[2] == -1) {
					playerCards[2] = in.readInt();
					playerCardsValue = in.readInt();
				} else if (playerCards[2] != -1 && playerCards[3] == -1) {
					playerCards[3] = in.readInt();
					playerCardsValue = in.readInt();
				} else if (playerCards[2] != -1 && playerCards[3] != -1 && playerCards[4] == -1){
					playerCards[4] = in.readInt();
					playerCardsValue = in.readInt();
				} else {
					playerCards[5] = in.readInt();
					playerCardsValue = in.readInt();
				}
		}
		public static void displayHit() throws IOException {
			/**
			 * Player card 3 displaying if 'Hit' button is pressed
			 * Player card 4 displaying if 'Hit' button is pressed and player1Cards[3] has been assigned
			 * Player card 5 displaying if 'Hit' button is pressed and player1Cards[4] has been assigned
			 */
				JLabel playerCard3 = new JLabel(new ImageIcon(cardArray[playerCards[2]].getCardImage()));
				Dimension sizeCards = playerCard3.getPreferredSize();
				playerCard3.setBounds(500, 100,sizeCards.width, sizeCards.height);
				panel.add(playerCard3);
				
				if (playerCards[3] != -1 && playerCards[4] == -1) {
					JLabel playerCard4 = new JLabel(new ImageIcon(cardArray[playerCards[3]].getCardImage()));
					playerCard4.setBounds(625, 100,sizeCards.width, sizeCards.height);
					panel.add(playerCard4);
				} else if (playerCards[4] != -1) {
					JLabel playerCard5 = new JLabel(new ImageIcon(cardArray[playerCards[4]].getCardImage()));
					playerCard5.setBounds(750, 100,sizeCards.width, sizeCards.height);
					panel.add(playerCard5);
				} else if (playerCards[5] != -1) {
					JLabel playerCard6 = new JLabel(new ImageIcon(cardArray[playerCards[5]].getCardImage()));
					playerCard6.setBounds(250, 265,sizeCards.width, sizeCards.height);
					panel.add(playerCard6);
				}
			
			panel.repaint();
		}
		
		public static void requestStand() throws IOException {
			// Assigning dealerCards
			if (dealerCards[2] == -1) {
				dealerCards[2] = in.readInt();
				dealerCardsValue = in.readInt();
			} else if (dealerCards[2] != -1 && dealerCards[3] == -1) {
				dealerCards[3] = in.readInt();
				dealerCardsValue = in.readInt();
			} else if (dealerCards[2] != -1 && dealerCards[3] != -1){
				dealerCards[4] = in.readInt();
				dealerCardsValue = in.readInt();
			} else {
				dealerCards[5] = in.readInt();
				dealerCardsValue = in.readInt();
			}
		}
		
		public static void displayDealerStand() throws IOException {
			// Displaying dealer cards if they are assigned
				JLabel dealerCard3 = new JLabel(new ImageIcon(cardArray[dealerCards[2]].getCardImage()));
				Dimension size1 = dealerCard3.getPreferredSize();
				dealerCard3.setBounds(1500, 100,size1.width, size1.height);
				panel.add(dealerCard3);
				
			 if (dealerCards[3] != -1 && dealerCards[4] == -1) {
				JLabel dealerCard4 = new JLabel(new ImageIcon(cardArray[dealerCards[3]].getCardImage()));
				Dimension size2 = dealerCard4.getPreferredSize();
				dealerCard4.setBounds(1625, 100,size2.width, size2.height);
				panel.add(dealerCard4);
				
			} else if (dealerCards[4] != -1){
				JLabel dealerCard5 = new JLabel(new ImageIcon(cardArray[dealerCards[4]].getCardImage()));
				Dimension size3 = dealerCard5.getPreferredSize();
				dealerCard5.setBounds(1750, 100,size3.width, size3.height);
				panel.add(dealerCard5);	
				
			} else if (dealerCards[5] != -1){
				JLabel dealerCard6 = new JLabel(new ImageIcon(cardArray[dealerCards[5]].getCardImage()));
				Dimension size3 = dealerCard6.getPreferredSize();
				dealerCard6.setBounds(1250, 265,size3.width, size3.height);
				panel.add(dealerCard6);				
			} 
			 
			panel.repaint();
		}
		
		public static void resetHands() throws IOException {
			//Setting standBoolean and dealBoolean to false
			standBoolean = false;
			dealBoolean = false;
			
			//Filling all card arrays with -1 before starting, since we have a cardNumber == 0
			Arrays.fill(playerCards, -1);
			Arrays.fill(dealerCards, -1);
			playerCardsValue = 0;
			dealerCardsValue = 0;
			dealerCard1Value = -1;
			
			//Writing 0 to the server when 'Reset' button is pressed
			out.writeInt(0);
			
			//Removing the old 'panel' and creating a new instance of singlePlayerClient
			frame.remove(panel);
			try {
				new singlePlayerClient();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TextArea.append("\nNew Game Starting:");
		}
		
		public static void startGame() throws IOException {
			// Creating a frame called Blackjack with bounds set to maximum window size
			frame = new JFrame("Blackjack");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			GraphicsEnvironment applicationEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			Rectangle maxBounds = applicationEnv.getMaximumWindowBounds();
			frame.setBounds(maxBounds);
			frame.setResizable(false);
		}
		
		public static void mainMenu() throws IOException {
			// Creating main menu frame
			menu = new JFrame("Blackjack Menu");
			menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			menu.setSize(600,400);
			menu.setResizable(false);
			menuPanel = new JPanel();
			menuPanel.setLayout(null);
			menuPanel.setBackground(new Color(0,128,0));
			menu.add(menuPanel);
			
			//blackJackMenu and playerNumMenu is the text displayed on the Main Menu
			JLabel blackjackMenu = new JLabel("Main Menu");
			JLabel playerNumMenu = new JLabel("You are player: " + playerNum);
			JLabel gamesInProgress = new JLabel("There are currently " + (playerNum-1) + " game(s) in progress");
			blackjackMenu.setFont(new Font("Arial",Font.PLAIN,35));
			playerNumMenu.setFont(new Font("Arial",Font.PLAIN,20));
			gamesInProgress.setFont(new Font("Arial",Font.PLAIN,20));
			Dimension sizeMenu = blackjackMenu.getPreferredSize();
			Dimension sizeGIP = gamesInProgress.getPreferredSize();
			blackjackMenu.setBounds(200,25, sizeMenu.width, sizeMenu.height);
			playerNumMenu.setBounds(205, 70, sizeMenu.width, sizeMenu.height);
			gamesInProgress.setBounds(105, 125, sizeGIP.width, sizeGIP.height);
			
			JButton StartGame = new JButton("Start Game");
			Dimension sizeStartGame = StartGame.getPreferredSize();
			StartGame.setBounds(230,200,sizeStartGame.width,sizeStartGame.height);
			StartGame.addActionListener(new startGame());
			menuPanel.add(blackjackMenu);
			menuPanel.add(playerNumMenu);
			menuPanel.add(gamesInProgress);
			menuPanel.add(StartGame);
			menu.setLocationRelativeTo(null);
			menu.setVisible(true);
		}
	}



