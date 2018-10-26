package testerClient10;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

import javax.swing.*;

public class testerClient10 extends Card {
	
	private static Socket socket;
	private static DataInputStream in;
	private static DataOutputStream out;
	private static int playerNum;
	private static boolean threePlayers = false;
	private static int [] player1Cards = new int[13];
	private static int [] player2Cards = new int[13];
	private static int [] player3Cards = new int[13];
	private static int [] dealerCards1 = new int[13];
	/*private static int playerCard1 = -1;
	private static int playerCard2 = -1;
	private static int playerCard3 = -1;
	private static int playerCard4 = -1;
	private static int playerCard5 = -1;
	private static int playerCards = -1;
	private static int dealerCard1 = -1;
	private static int dealerCard2 = -1;
	private static int dealerCard3 = -1;
	private static int dealerCard4 = -1;
	private static int dealerCard5 = -1;
	private static int dealerCards = -1;*/
	private static int dealerCard1Value = 0;
	private static boolean standBoolean = false;
	private static boolean dealBoolean = false;
	private static JFrame frame;
	private static JFrame menu;
	private static JTextArea TextArea;
	private static JPanel panel;
	private static JPanel menuPanel;
	private static int player1CardsValue;
	private static int player2CardsValue;
	private static int player3CardsValue;
	private static int dealerCardsValue;
	
	public testerClient10() throws IOException {
		createArray();
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0,128,0));
		TextArea = new JTextArea("Blackjack!",25,40);
		Dimension sizeTextArea = TextArea.getPreferredSize();
		TextArea.setBounds(950, 300, sizeTextArea.width, sizeTextArea.height);
		JLabel blackjackName = new JLabel("Blackjack!");
		JLabel playerNumGame = new JLabel("You are player: " + playerNum);
		blackjackName.setFont(new Font("Arial",Font.PLAIN,35));
		Dimension sizeName = blackjackName.getPreferredSize();
		blackjackName.setBounds(900, 100, sizeName.width, sizeName.height);
		playerNumGame.setFont(new Font("Arial",Font.PLAIN,20));
		playerNumGame.setBounds(900, 150, sizeName.width, sizeName.height);
		panel.add(blackjackName);
		panel.add(playerNumGame);
		panel.add(TextArea);
		frame.add(panel);
		JButton Deal = new JButton("Deal Cards");
		Dimension sizeDeal = Deal.getPreferredSize();
		Deal.setBounds(950, 750, sizeDeal.width, sizeDeal.height);
		JButton Hit = new JButton("Hit");
		Dimension sizeHit = Hit.getPreferredSize();
		Hit.setBounds(1100, 750, sizeHit.width, sizeHit.height);
		JButton Stand = new JButton("Stand");
		Dimension sizeStand = Stand.getPreferredSize();
		Stand.setBounds(1200, 750, sizeStand.width, sizeStand.height);
		JButton Reset = new JButton("Reset");
		Dimension sizeReset = Reset.getPreferredSize();
		Reset.setBounds(1315, 750, sizeReset.width, sizeReset.height);
		JButton mainMenu = new JButton("Main Menu");
		Dimension sizeBackMenu = mainMenu.getPreferredSize();
		mainMenu.setBounds(1700, 900, sizeBackMenu.width, sizeBackMenu.height);
		panel.add(mainMenu);
		panel.add(Deal);
		panel.add(Hit);
		panel.add(Stand);
		panel.add(Reset);
		Deal.addActionListener(new dealCards());
		Hit.addActionListener(new hit());
		Stand.addActionListener(new stand());
		Reset.addActionListener(new reset());
		mainMenu.addActionListener(new mainMenu());
		frame.setVisible(true);
	}
	
	public testerClient10(int cardNumber, BufferedImage cardImage) throws UnknownHostException, IOException {
		super(cardNumber, cardImage);
	}

	public static void main(String [] args) throws IOException {
		socket = new Socket("localhost",8087);
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		
		// Player receiving their number
		playerNum = in.readInt();
		
		//Filling all card arrays with -1 before starting, since we have a cardNumber == 0
		Arrays.fill(player1Cards, -1);
		Arrays.fill(player2Cards, -1);
		Arrays.fill(player3Cards, -1);
		Arrays.fill(dealerCards1, -1);
		
		//Calling mainMenu() method displaying the small menu showing the player number and 'Start Game' button
		mainMenu();
	}
	
	public static void dealCards() throws IOException {
		//Player 1 card 1 & 2 being assigned as well as the total value
		player1Cards[0] = in.readInt();
		player1Cards[1] = in.readInt();
		player1CardsValue = in.readInt();
		
		//Player 2 card 1 & 2 being assigned as well as the total value
		player2Cards[0] = in.readInt();
		player2Cards[1] = in.readInt();
		player2CardsValue = in.readInt();
		
		//Player 3 card 1 & 2 being assigned as well as the total value
		player3Cards[0] = in.readInt();
		player3Cards[1] = in.readInt();
		player3CardsValue = in.readInt();
		
		//Dealer card 1 & 2 being assigned as well as the value of the first card and the total value
		dealerCards1[0] = in.readInt();
		dealerCards1[1] = in.readInt();
		dealerCard1Value = in.readInt();
		dealerCardsValue = in.readInt();
	}

	public static void displayPlayerCards(Card cardArray[]) throws IOException {
		//Displaying player 1 cards and player number
		if (playerNum == 1) {
		JLabel player1Card1 = new JLabel(new ImageIcon(cardArray[player1Cards[0]].getCardImage()));
		Dimension sizeCards = player1Card1.getPreferredSize();
		player1Card1.setBounds(250, 100, sizeCards.width, sizeCards.height);
		
		JLabel player1Card2 = new JLabel(new ImageIcon(cardArray[player1Cards[1]].getCardImage()));
		player1Card2.setBounds(375, 100, sizeCards.width, sizeCards.height);
		
		JLabel cardString1 = new JLabel(new String("Player cards 1: "));
		cardString1.setFont(new Font("Arial",Font.PLAIN,20));
		Dimension sizeString = cardString1.getPreferredSize();
		cardString1.setBounds(100, 150,sizeString.width, sizeString.height);
		
		panel.add(player1Card1);
		panel.add(player1Card2);
		panel.add(cardString1);
		
		} else if (playerNum == 2) {
		
		//Display player 2 cards and player number
		JLabel player2Card1 = new JLabel(new ImageIcon(cardArray[player2Cards[0]].getCardImage()));
		Dimension sizeCards = player2Card1.getPreferredSize();
		player2Card1.setBounds(250, 300, sizeCards.width, sizeCards.height);
		
		JLabel player2Card2 = new JLabel(new ImageIcon(cardArray[player2Cards[1]].getCardImage()));
		player2Card2.setBounds(375, 300, sizeCards.width, sizeCards.height);
	
		JLabel cardString2 = new JLabel(new String("Player cards 2: "));
		cardString2.setFont(new Font("Arial",Font.PLAIN,20));
		Dimension sizeString = cardString2.getPreferredSize();
		cardString2.setBounds(100, 350,sizeString.width, sizeString.height);
		
		panel.add(player2Card1);
		panel.add(player2Card2);
		panel.add(cardString2);
		
		} else if (playerNum == 3) {
			
		//Display player 3 cards and player number
		JLabel player3Card1 = new JLabel(new ImageIcon(cardArray[player3Cards[0]].getCardImage()));
		Dimension sizeCards = player3Card1.getPreferredSize();
		player3Card1.setBounds(250, 500, sizeCards.width, sizeCards.height);
		
		JLabel player3Card2 = new JLabel(new ImageIcon(cardArray[player3Cards[1]].getCardImage()));
		player3Card2.setBounds(375, 500, sizeCards.width, sizeCards.height);
		
		JLabel cardString3 = new JLabel(new String("Player cards 3: "));
		cardString3.setFont(new Font("Arial",Font.PLAIN,20));
		Dimension sizeString = cardString3.getPreferredSize();
		cardString3.setBounds(100, 550,sizeString.width, sizeString.height);
		
		panel.add(player3Card1);
		panel.add(player3Card2);
		panel.add(cardString3);
		
		}
		/*panel.add(player1Card1);
		panel.add(player1Card2);
		panel.add(cardString1);
		panel.add(player2Card1);
		panel.add(player2Card2);
		panel.add(cardString2);
		panel.add(player3Card1);
		panel.add(player3Card2);
		panel.add(cardString3);*/
		panel.repaint();
	}
	
	public static void displayDealerCards(Card cardArray[]) throws IOException {
		/**
		 * Dealer card 1 and name if standBoolean == false
		 * standBoolean == false when 'Stand' button has not been pressed
		 * It will therefore only display the first dealer card
		 */
		if (standBoolean == false) {
		JLabel dealerCard1 = new JLabel(new ImageIcon(cardArray[dealerCards1[0]].getCardImage()));
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
		JLabel dealerCard2 = new JLabel(new ImageIcon(cardArray[dealerCards1[1]].getCardImage()));
		Dimension sizeCards = dealerCard2.getPreferredSize();
		dealerCard2.setBounds(1375, 100,sizeCards.width, sizeCards.height);
		
		panel.add(dealerCard2);
		}
		
		panel.repaint();
	}
	
	/*public static void displayPlayerCards(Card cardArray[]) throws IOException {

		JLabel cardLabel0 = new JLabel(new ImageIcon(cardArray[playerCard1].getCardImage()));
		Dimension size1 = cardLabel0.getPreferredSize();
		cardLabel0.setBounds(250, 100,size1.width, size1.height);
		
		JLabel cardLabel1 = new JLabel(new ImageIcon(cardArray[playerCard2].getCardImage()));
		Dimension size2 = cardLabel0.getPreferredSize();
		cardLabel1.setBounds(375, 100,size2.width, size2.height);
		
		JLabel cardString = new JLabel(new String("Player cards: "));
		cardString.setFont(new Font("Arial",Font.PLAIN,20));
		Dimension size3 = cardString.getPreferredSize();
		cardString.setBounds(125, 150,size3.width, size3.height);

		panel.add(cardString);
		panel.add(cardLabel0);
		panel.add(cardLabel1);
		panel.repaint();
	}*/
	
	/*public static void displayDealerCards(Card cardArray[]) throws IOException {
		if (standBoolean == false) {
		JLabel cardLabel0 = new JLabel(new ImageIcon(cardArray[dealerCard1].getCardImage()));
		Dimension size1 = cardLabel0.getPreferredSize();
		cardLabel0.setBounds(1250, 100,size1.width, size1.height);
		
		JLabel cardString = new JLabel(new String("Dealer cards: "));
		cardString.setFont(new Font("Arial",Font.PLAIN,20));
		Dimension size3 = cardString.getPreferredSize();
		cardString.setBounds(1125, 150,size3.width, size3.height);
		
		panel.add(cardString);
		panel.add(cardLabel0);
		} else if (standBoolean == true) {
		JLabel cardLabel1 = new JLabel(new ImageIcon(cardArray[dealerCard2].getCardImage()));
		Dimension size2 = cardLabel1.getPreferredSize();
		cardLabel1.setBounds(1375, 100,size2.width, size2.height);
		
		panel.add(cardLabel1);
		}
		
		panel.repaint();	
	}*/
	
	public static void requestHit() throws IOException {
		/**
		 * Player 1 card 3 assigning if 'Hit' button is pressed and player1Cards[2] has not been assigned
		 * Player 1 card 4 assigning if 'Hit' button is pressed and player1Cards[3] has not been assigned
		 * Player 1 card 5 assigning if 'Hit' button is pressed and player1Cards[4] has not been assigned
		 */
		if(playerNum == 1) {
			if (player1Cards[2] == -1) {
				player1Cards[2] = in.readInt();
				player1CardsValue = in.readInt();
			} else if (player1Cards[2] != -1 && player1Cards[3] == -1) {
				player1Cards[3] = in.readInt();
				player1CardsValue = in.readInt();
			} else {
				player1Cards[4] = in.readInt();
				player1CardsValue = in.readInt();
			}
			/**
			 * Player 2 card 3 assigning if 'Hit' button is pressed and player2Cards[2] has not been assigned
			 * Player 2 card 4 assigning if 'Hit' button is pressed and player2Cards[3] has not been assigned
			 * Player 2 card 5 assigning if 'Hit' button is pressed and player2Cards[4] has not been assigned
			 */
		} else if(playerNum == 2) {
			if (player2Cards[2] == -1) {
				player2Cards[2] = in.readInt();
				player2CardsValue = in.readInt();
			} else if (player2Cards[2] != -1 && player2Cards[3] == -1) {
				player2Cards[3] = in.readInt();
				player2CardsValue = in.readInt();
			} else {
				player2Cards[4] = in.readInt();
				player2CardsValue = in.readInt();
			}
			/**
			 * Player 3 card 3 assigning if 'Hit' button is pressed and player3Cards[2] has not been assigned
			 * Player 3 card 4 assigning if 'Hit' button is pressed and player3Cards[3] has not been assigned
			 * Player 3 card 5 assigning if 'Hit' button is pressed and player3Cards[4] has not been assigned
			 */
		} else if (playerNum == 3) {
			if (player3Cards[2] == -1) {
				player3Cards[2] = in.readInt();
				player3CardsValue = in.readInt();
			} else if (player3Cards[2] != -1 && player3Cards[3] == -1) {
				player3Cards[3] = in.readInt();
				player3CardsValue = in.readInt();
			} else {
				player3Cards[4] = in.readInt();
				player3CardsValue = in.readInt();
			}
			
		}
	}
	
	/*public static void requestHit(Card cardArray[]) throws IOException {
		if (playerCard3 == -1) {
			playerCard3 = in.readInt();
			playerCards = in.readInt();
			System.out.println("The number of the third card is: " + cardArray[playerCard3].getCardNumber());
			System.out.println("New value of your cards: " + playerCards);
		} else if (playerCard4 == -1 && playerCard3 > -1) {
		 	playerCard4 = in.readInt();
		 	playerCards = in.readInt();
		 	System.out.println("The number of the fourth card is: " + cardArray[playerCard4].getCardNumber());
		 	System.out.println("New value of your cards: " + playerCards);
		} else {
			playerCard5 = in.readInt();
			playerCards = in.readInt();
			System.out.println("The number of the fifth card is: " + cardArray[playerCard5].getCardNumber());
			System.out.println("New value of your cards: " + playerCards);
			}
	}*/
	
	public static void displayHit(Card cardArray[]) throws IOException {
		/**
		 * Player 1 card 3 displaying if 'Hit' button is pressed
		 * Player 1 card 4 displaying if 'Hit' button is pressed and player1Cards[3] has been assigned
		 * Player 1 card 5 displaying if 'Hit' button is pressed and player1Cards[4] has been assigned
		 */
		if (playerNum == 1) {
			JLabel player1Card3 = new JLabel(new ImageIcon(cardArray[player1Cards[2]].getCardImage()));
			Dimension sizeCards = player1Card3.getPreferredSize();
			player1Card3.setBounds(500, 100,sizeCards.width, sizeCards.height);
			panel.add(player1Card3);
			if (player1Cards[3] != -1 && player1Cards[4] == -1) {
				JLabel player1Card4 = new JLabel(new ImageIcon(cardArray[player1Cards[3]].getCardImage()));
				player1Card4.setBounds(625, 100,sizeCards.width, sizeCards.height);
				panel.add(player1Card4);
			} else if (player1Cards[4] != -1) {
				JLabel player1Card5 = new JLabel(new ImageIcon(cardArray[player1Cards[4]].getCardImage()));
				player1Card5.setBounds(750, 100,sizeCards.width, sizeCards.height);
				panel.add(player1Card5);
			}
			/**
			 * Player 2 card 3 displaying if 'Hit' button is pressed
			 * Player 2 card 4 displaying if 'Hit' button is pressed and player2Cards[3] has been assigned
			 * Player 2 card 5 displaying if 'Hit' button is pressed and player2Cards[4] has been assigned
			 */
		} else if (playerNum == 2) {
			JLabel player2Card3 = new JLabel(new ImageIcon(cardArray[player2Cards[2]].getCardImage()));
			Dimension sizeCards = player2Card3.getPreferredSize();
			player2Card3.setBounds(500, 300,sizeCards.width, sizeCards.height);
			panel.add(player2Card3);
			if (player2Cards[3] != -1 && player2Cards[4] == -1) {
				JLabel player2Card4 = new JLabel(new ImageIcon(cardArray[player2Cards[3]].getCardImage()));
				player2Card4.setBounds(625, 300,sizeCards.width, sizeCards.height);
				panel.add(player2Card4);
			} else if (player2Cards[4] != -1) {
				JLabel player2Card5 = new JLabel(new ImageIcon(cardArray[player2Cards[4]].getCardImage()));
				player2Card5.setBounds(750, 300,sizeCards.width, sizeCards.height);
				panel.add(player2Card5);
			}
			/**
			 * Player 3 card 3 displaying if 'Hit' button is pressed
			 * Player 3 card 4 displaying if 'Hit' button is pressed and player2Cards[3] has been assigned
			 * Player 3 card 5 displaying if 'Hit' button is pressed and player2Cards[4] has been assigned
			 */
		} else if (playerNum == 3) {
			JLabel player3Card3 = new JLabel(new ImageIcon(cardArray[player3Cards[2]].getCardImage()));
			Dimension sizeCards = player3Card3.getPreferredSize();
			player3Card3.setBounds(500, 500,sizeCards.width, sizeCards.height);
			panel.add(player3Card3);
			if (player3Cards[3] != -1 && player3Cards[4] == -1) {
				JLabel player3Card4 = new JLabel(new ImageIcon(cardArray[player3Cards[3]].getCardImage()));
				player3Card4.setBounds(625, 500,sizeCards.width, sizeCards.height);
				panel.add(player3Card4);
			} else if (player3Cards[4] != -1) {
				JLabel player3Card5 = new JLabel(new ImageIcon(cardArray[player3Cards[4]].getCardImage()));
				player3Card5.setBounds(750, 500,sizeCards.width, sizeCards.height);
				panel.add(player3Card5);
			}
		}
		panel.repaint();
	}
	
	/*public static void displayHit(Card cardArray[]) throws IOException {
			JLabel cardLabel0 = new JLabel(new ImageIcon(cardArray[playerCard3].getCardImage()));
			Dimension size = cardLabel0.getPreferredSize();
			cardLabel0.setBounds(500, 100,size.width, size.height);
			panel.add(cardLabel0);
			
			
		if (playerCard4 != -1 && playerCard5 == -1) {
			JLabel cardLabel1 = new JLabel(new ImageIcon(cardArray[playerCard4].getCardImage()));
			Dimension size2 = cardLabel1.getPreferredSize();
			cardLabel1.setBounds(625, 100,size2.width, size2.height);
			panel.add(cardLabel1);
			
		} else if (playerCard5 != -1) {
			JLabel cardLabel2 = new JLabel(new ImageIcon(cardArray[playerCard5].getCardImage()));
			Dimension size3 = cardLabel2.getPreferredSize();
			cardLabel2.setBounds(750, 100,size3.width, size3.height);
			panel.add(cardLabel2);
		}
		
		panel.repaint();
	}*/
	
	public static void requestStand() throws IOException {
		if (dealerCards1[2] == -1) {
			dealerCards1[2] = in.readInt();
			dealerCardsValue = in.readInt();
		} else if (dealerCards1[2] != -1 && dealerCards1[3] == -1) {
			dealerCards1[3] = in.readInt();
			dealerCardsValue = in.readInt();
		} else {
			dealerCards1[4] = in.readInt();
			dealerCardsValue = in.readInt();
		}
	}
	/*public static void requestStand(Card cardArray[]) throws IOException {
		if (dealerCard3 == -1) {
			dealerCard3 = in.readInt();
			dealerCards = in.readInt();
			System.out.println("3rd card number: " + dealerCard3);
			System.out.println("total value dealer: " + dealerCards);
		} else if (dealerCard3 != -1 && dealerCard4 == -1){
			dealerCard4 = in.readInt();
			dealerCards = in.readInt();
			System.out.println("4th card number: " + dealerCard4);
			System.out.println("total value dealer: " + dealerCards);
		} else {
			dealerCard5 = in.readInt();
			dealerCards = in.readInt();
			System.out.println("5th card number: " + dealerCard4);
			System.out.println("total value dealer: " + dealerCards);
		}
	}*/
	
	public static void displayDealerStand(Card cardArray[]) throws IOException {
			JLabel dealerCard3 = new JLabel(new ImageIcon(cardArray[dealerCards1[2]].getCardImage()));
			Dimension size1 = dealerCard3.getPreferredSize();
			dealerCard3.setBounds(1500, 100,size1.width, size1.height);
			panel.add(dealerCard3);
			
		 if (dealerCards1[3] != -1 && dealerCards1[4] == -1) {
			JLabel dealerCard4 = new JLabel(new ImageIcon(cardArray[dealerCards1[3]].getCardImage()));
			Dimension size2 = dealerCard4.getPreferredSize();
			dealerCard4.setBounds(1625, 100,size2.width, size2.height);
			panel.add(dealerCard4);
			
		} else if (dealerCards1[4] != -1){
			JLabel dealerCard5 = new JLabel(new ImageIcon(cardArray[dealerCards1[4]].getCardImage()));
			Dimension size3 = dealerCard5.getPreferredSize();
			dealerCard5.setBounds(1750, 300,size3.width, size3.height);
			panel.add(dealerCard5);
			
		}
		panel.repaint();
	}
	
	public static void resetHands() throws IOException {
		//Setting standBoolean and dealBoolean to false
		standBoolean = false;
		dealBoolean = false;
		//Filling all card arrays with -1 before starting, since we have a cardNumber == 0
		Arrays.fill(player1Cards, -1);
		Arrays.fill(player2Cards, -1);
		Arrays.fill(player3Cards, -1);
		Arrays.fill(dealerCards1, -1);
		/*playerCard1 = -1;
		playerCard2 = -1;
		playerCard3 = -1;
		playerCard4 = -1;
		playerCard5 = -1;
		playerCards = -1;
		dealerCard1 = -1;
		dealerCard2 = -1;
		dealerCard3 = -1;
		dealerCard4 = -1;
		dealerCard5 = -1;
		dealerCards = -1;*/
		dealerCard1Value = -1;
		
		//Writing 0 to the server when 'Reset' button is pressed
		out.writeInt(0);
		
		//Removing the old 'panel' and creating a new instance of testerClient10
		frame.remove(panel);
		new testerClient10();
		TextArea.append("\nNew Game Starting:");
	}
	
	public static void startGame() throws IOException {
		frame = new JFrame("Blackjack");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GraphicsEnvironment applicationEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle maxBounds = applicationEnv.getMaximumWindowBounds();
		frame.setBounds(maxBounds);
	}
	
	public static void mainMenu() throws IOException {
		menu = new JFrame("Blackjack Menu");
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(300,200);
		menuPanel = new JPanel();
		menuPanel.setLayout(null);
		menuPanel.setBackground(new Color(0,128,0));
		menu.add(menuPanel);
		
		//blackJackMenu and playerNumMenu is the text displayed on the Main Menu
		JLabel blackjackMenu = new JLabel("Main Menu");
		JLabel playerNumMenu = new JLabel("You are player: " + playerNum);
		blackjackMenu.setFont(new Font("Arial",Font.PLAIN,35));
		playerNumMenu.setFont(new Font("Arial",Font.PLAIN,20));
		Dimension sizeMenu = blackjackMenu.getPreferredSize();
		blackjackMenu.setBounds(menu.getWidth()/5, menu.getHeight()/15, sizeMenu.width, sizeMenu.height);
		playerNumMenu.setBounds((int) (menu.getWidth()/4.5), menu.getHeight()/2, sizeMenu.width, sizeMenu.height);
		
		JButton StartGame = new JButton("Start Game");
		Dimension sizeStartGame = StartGame.getPreferredSize();
		StartGame.setBounds(menu.getWidth()/3,menu.getHeight()/3,sizeStartGame.width,sizeStartGame.height);
		StartGame.addActionListener(new startGame());
		menuPanel.add(blackjackMenu);
		menuPanel.add(playerNumMenu);
		menuPanel.add(StartGame);
		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
	}
	
	static class dealCards implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				if(dealBoolean == false) {
					out.writeInt(playerNum);
					out.writeBoolean(true);
					dealCards();
					displayPlayerCards(cardArray);
					displayDealerCards(cardArray);
					dealBoolean = true;
					if (playerNum == 1) {
						TextArea.append("\nThe value of your cards are: " + player1CardsValue);
					} else if (playerNum == 2) {
						TextArea.append("\nThe value of your cards are: " + player2CardsValue);
					} else if (playerNum == 3) {
						TextArea.append("\nThe value of your cards are: " + player3CardsValue);
					}
					TextArea.append("\nThe value of the dealer cards are: " + dealerCard1Value + " + X");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			/*try {
				if (playerCards < 21 && dealerCards < 21 && standBoolean == false && dealBoolean == false) {
					out.writeInt(1);
					dealPlayerCards(cardArray);
					dealDealerCards(cardArray);
					displayDealerCards(cardArray);
					displayPlayerCards(cardArray);
					TextArea.append("\nValue of cards: " + Integer.toString(playerCards));
					TextArea.append("\nValue of dealer cards: " + Integer.toString(dealerCard1Value) + " + X");
				} else if (playerCards > 21 && dealerCards > 21 || playerCards > 21 && dealerCards < 21 || playerCards < 21 && dealerCards > 21) {
					TextArea.append("\nYou need to reset!");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if (playerCards == 21 && playerCard3 == -1) {
				standBoolean = true;
				try {
					displayDealerCards(cardArray);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				TextArea.append("\nYou got blackjack!\nYou win!");
				TextArea.append("\nYou need to reset!");
			}
			if (dealerCards == 21 && dealerCard3 == -1) {
				standBoolean = true;
				try {
					displayDealerCards(cardArray);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				TextArea.append("\nDealer got blackjack!\nDealer wins!");
				TextArea.append("\nYou need to reset!");
			}*/
		}
	}
	
	static class hit implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				if (dealBoolean == true) {
					if (playerNum == 1) {
						if (player1CardsValue < 21) {
							out.writeInt(playerNum + 3);
							requestHit();
							displayHit(cardArray);
							TextArea.append("\nYou chose to hit\nYour new value is: " + player1CardsValue);
							if (player1CardsValue > 21) {
								TextArea.append("\nYou are over 21");
							}
						} else if (player1CardsValue > 21) {
							TextArea.append("\nYou are over 21");
						} else if (player1CardsValue == 21) {
							TextArea.append("\nYou have 21!");
						}
					} else if (playerNum == 2) {
						if (player2CardsValue < 21) {
							out.writeInt(playerNum + 3);
							requestHit();
							displayHit(cardArray);
							TextArea.append("\nYou chose to hit\nYour new value is: " + player2CardsValue);
							if (player2CardsValue > 21) {
								TextArea.append("\nYou are over 21");
							}
						} else if (player2CardsValue > 21) {
							TextArea.append("\nYou are over 21");
						} else if (player2CardsValue == 21) {
							TextArea.append("\nYou have 21!");
						}
					} else if (playerNum == 3) {
						if (player3CardsValue < 21) {
							out.writeInt(playerNum + 3);
							requestHit();
							displayHit(cardArray);
							TextArea.append("\nYou chose to hit\nYour new value is: " + player3CardsValue);
							if (player3CardsValue > 21) {
								TextArea.append("\nYou are over 21");
							}
						} else if (player3CardsValue > 21) {
							TextArea.append("\nYou are over 21");
						} else if (player3CardsValue == 21) {
							TextArea.append("\nYou have 21!");
						}
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			/*try {
				if (playerCards < 21 && dealerCards < 21 && standBoolean == false && dealBoolean == true) {
				out.writeInt(2);
				requestHit(cardArray);
				displayHit(cardArray);
				TextArea.append("\nYou chose to hit\nNew value of cards: " + Integer.toString(playerCards));
				if (playerCards > 21) {
					TextArea.append("\nYour total value is: " + Integer.toString(playerCards));
					TextArea.append("\nYou are over 21!\nDealer wins!");
					TextArea.append("\nYou need to reset!");
				} if (playerCards == 21 && playerCard3 == -1) {
					TextArea.append("\nYou got blackjack!\nYou win!");
				}
				} else if (playerCards == -1){
					TextArea.append("\nYou need to deal cards first!");
				} else if (playerCards > -1) {
					TextArea.append("\nYou need to reset!");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}*/
			System.out.println("Hit");
		}
	}
	static class stand implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				if (dealerCardsValue < 17 && dealBoolean == true) {
					standBoolean = true;
					displayDealerCards(cardArray);
					out.writeInt(7);
					requestStand();
					displayDealerStand(cardArray);
					TextArea.append("\nDealer total value is: " + dealerCardsValue);
				} else if (dealerCardsValue > 17 && dealBoolean == true || dealerCardsValue == 17 && dealBoolean == true) {
					standBoolean = true;
					displayDealerCards(cardArray);
					TextArea.append("\nDealer total value is: " + dealerCardsValue);
				}
				if(playerNum == 1) {
					if (player1CardsValue < 21  && dealerCardsValue < player1CardsValue || player1CardsValue == 21 && dealerCardsValue < player1CardsValue && player1Cards[2] > -1) {
						TextArea.append("\nDealer total value is: " + dealerCardsValue + "\nYou win!\nYou need to reset");
					}
				} else if (playerNum == 2) {
					if (player2CardsValue < 21  && dealerCardsValue < player2CardsValue || player2CardsValue == 21 && dealerCardsValue < player2CardsValue && player2Cards[2] > -1) {
						TextArea.append("\nDealer total value is: " + dealerCardsValue + "\nYou win!\nYou need to reset");
					}
				} else if (playerNum == 3) {
					if (player3CardsValue < 21  && dealerCardsValue < player3CardsValue || player3CardsValue == 21 && dealerCardsValue < player3CardsValue && player3Cards[2] > -1) {
						TextArea.append("\nDealer total value is: " + dealerCardsValue + "\nYou win!\nYou need to reset");
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			/*try {
				if(dealerCards < 17 && playerCards < 21 && dealBoolean == true || dealerCards < 17 && playerCards == 21 && playerCard3 > -1 && dealBoolean == true) {
					while(dealerCards < 17) {
						standBoolean = true;
						displayDealerCards(cardArray);
						out.writeInt(3);
						requestStand(cardArray);
						displayDealerStand(cardArray);
					}
					TextArea.append("\nYou chose to stand\nYour total value is: " + playerCards);
					TextArea.append("\nValue of dealer cards: " + Integer.toString(dealerCards));
					if (playerCards < 21 && dealerCards < playerCards || playerCards == 21 & dealerCards < playerCards) {
						TextArea.append("\nDealer total value is: " + dealerCards + "\nYou win!\nYou need to reset");
						standBoolean = true;
						displayDealerCards(cardArray);
					}
					if (dealerCards > playerCards && dealerCards < 21 || dealerCards > playerCards && dealerCards == 21) {
						TextArea.append("\nDealer total value is: " + dealerCards + "\nDealer wins!");
						standBoolean = true;
						displayDealerCards(cardArray);
					}
					if (dealerCards > 21 && playerCards < 21) {
						TextArea.append("\nDealer is over 21\nYou win!");
						standBoolean = true;
						displayDealerCards(cardArray);
					}
					if (dealerCards == playerCards) {
						TextArea.append("\nIt is a draw!");
						standBoolean = true;
						displayDealerCards(cardArray);
					} 
					
				} else if (playerCards > 21 || dealerCards > 21) {
					standBoolean = true;
					TextArea.append("\nYou need to reset!");
					displayDealerCards(cardArray);
				} else if (dealerCards > playerCards && dealerCards < 21 || dealerCards > playerCards && dealerCards == 21) {
					standBoolean = true;
					TextArea.append("\nDealer total value is: " + dealerCards + "\nDealer wins!");
					displayDealerCards(cardArray);
				} else if (playerCards < 21 && dealerCards < playerCards || playerCards == 21 & dealerCards < playerCards) {
					standBoolean = true;
					TextArea.append("\nDealer total value is: " + dealerCards + "\nYou win!\nYou need to reset");
					displayDealerCards(cardArray);
				} else if (dealerCards == -1 && playerCards == -1) {
					TextArea.append("\nYou need to deal cards first!");
				} else if (dealerCards == playerCards) {
					TextArea.append("\nIt is a draw!");
					standBoolean = true;
					displayDealerCards(cardArray);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}*/
			System.out.println("Stand");
		}	
	}
	static class reset implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			try {
				resetHands();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	static class startGame implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				startGame();
				new testerClient10();
				menu.setVisible(false);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
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
}



