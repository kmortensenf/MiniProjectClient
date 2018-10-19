package testerClient10;

import java.awt.*;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

import javax.swing.*;

public class testerClient10 extends Card {
	
	public static Socket socket;
	public static DataInputStream in;
	public static DataOutputStream out;
	public static int playerCard1 = -1;
	public static int playerCard2 = -1;
	public static int playerCard3 = -1;
	public static int playerCard4 = -1;
	public static int playerCard5 = -1;
	public static int playerCards = -1;
	public static int dealerCard1 = -1;
	public static int dealerCard2 = -1;
	public static int dealerCard3 = -1;
	public static int dealerCard4 = -1;
	public static int dealerCard5 = -1;
	public static int dealerCards = -1;
	public static int dealerCard1Value = 0;
	public static boolean standBoolean = false;
	public static boolean dealBoolean = false;
	public static JFrame frame;
	public static JFrame menu;
	public static JTextArea TextArea;
	public static JPanel panel;
	public static JPanel menuPanel;
	
	public testerClient10() throws IOException {
		createArray();
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0,128,0));
		TextArea = new JTextArea("Blackjack!",25,40);
		Dimension sizeTextArea = TextArea.getPreferredSize();
		TextArea.setBounds(750, 300, sizeTextArea.width, sizeTextArea.height);
		JLabel blackjackName = new JLabel("Blackjack!");
		blackjackName.setFont(new Font("Arial",Font.PLAIN,35));
		Dimension sizeBlackJackName = blackjackName.getPreferredSize();
		blackjackName.setBounds(900, 100, sizeBlackJackName.width, sizeBlackJackName.height);
		panel.add(blackjackName);
		panel.add(TextArea);
		frame.add(panel);
		JButton Deal = new JButton("Deal Cards");
		Dimension sizeDeal = Deal.getPreferredSize();
		Deal.setBounds(750, 750, sizeDeal.width, sizeDeal.height);
		JButton Hit = new JButton("Hit");
		Dimension sizeHit = Hit.getPreferredSize();
		Hit.setBounds(900, 750, sizeHit.width, sizeHit.height);
		JButton Stand = new JButton("Stand");
		Dimension sizeStand = Stand.getPreferredSize();
		Stand.setBounds(1000, 750, sizeStand.width, sizeStand.height);
		JButton Reset = new JButton("Reset");
		Dimension sizeReset = Reset.getPreferredSize();
		Reset.setBounds(1115, 750, sizeReset.width, sizeReset.height);
		JButton backMenu = new JButton("Main Menu");
		Dimension sizeBackMenu = backMenu.getPreferredSize();
		backMenu.setBounds(1700, 900, sizeBackMenu.width, sizeBackMenu.height);
		panel.add(backMenu);
		panel.add(Deal);
		panel.add(Hit);
		panel.add(Stand);
		panel.add(Reset);
		Deal.addActionListener(new dealCards());
		Hit.addActionListener(new hit());
		Stand.addActionListener(new stand());
		Reset.addActionListener(new reset());
		backMenu.addActionListener(new backMenu());
		frame.setVisible(true);
	}
	
	public testerClient10(int cardNumber, BufferedImage cardImage) throws UnknownHostException, IOException {
		super(cardNumber, cardImage);
	}

	public static void main(String [] args) throws IOException {
		socket = new Socket("localhost",8087);
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		menu = new JFrame("Blackjack Menu");
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(300,200);
		menuPanel = new JPanel();
		menuPanel.setLayout(null);
		menuPanel.setBackground(new Color(0,128,0));
		menu.add(menuPanel);
		JLabel blackjackMenu = new JLabel("Main Menu");
		blackjackMenu.setFont(new Font("Arial",Font.PLAIN,35));
		Dimension sizeBlackJackMenu = blackjackMenu.getPreferredSize();
		blackjackMenu.setBounds(menu.getWidth()/5, menu.getHeight()/15, sizeBlackJackMenu.width, sizeBlackJackMenu.height);
		JButton StartGame = new JButton("Start Game");
		StartGame.addActionListener(new startGame());
		menuPanel.add(StartGame);
		menuPanel.add(blackjackMenu);
		Dimension sizeStartGame = StartGame.getPreferredSize();
		StartGame.setBounds(menu.getWidth()/3,menu.getHeight()/3,sizeStartGame.width,sizeStartGame.height);
		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
	}
	
	public static void dealPlayerCards(Card cardArray[]) throws IOException {
		playerCard1 = in.readInt();
		playerCard2 = in.readInt();
		playerCards = in.readInt();
		
		System.out.println("The number of the first card is: " + cardArray[playerCard1].getCardNumber());
		System.out.println("The number of the second card is: " + cardArray[playerCard2].getCardNumber());
		
		System.out.println("Value of your cards: " + playerCards);
	}
	
	public static void dealDealerCards(Card cardArray[]) throws IOException {
		dealerCard1 = in.readInt();
		dealerCard1Value = in.readInt();
		dealerCard2 = in.readInt();
		dealerCards = in.readInt();
	}
	
	public static void displayPlayerCards(Card cardArray[]) throws IOException {

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
	}
	
	public static void displayDealerCards(Card cardArray[]) throws IOException {
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
	}
	
	public static void requestHit(Card cardArray[]) throws IOException {
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
	}
	
	public static void displayHit(Card cardArray[]) throws IOException {
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
	}
	
	public static void requestStand(Card cardArray[]) throws IOException {
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
	}
	
	public static void displayDealerStand(Card cardArray[]) throws IOException {
			JLabel cardLabel0 = new JLabel(new ImageIcon(cardArray[dealerCard3].getCardImage()));
			Dimension size1 = cardLabel0.getPreferredSize();
			cardLabel0.setBounds(1500, 100,size1.width, size1.height);
			panel.add(cardLabel0);
			
		 if (dealerCard4 != -1 && dealerCard5 == -1) {
			JLabel cardLabel1 = new JLabel(new ImageIcon(cardArray[dealerCard4].getCardImage()));
			Dimension size2 = cardLabel1.getPreferredSize();
			cardLabel1.setBounds(1625, 100,size2.width, size2.height);
			panel.add(cardLabel1);
			
		} else if (dealerCard5 != -1){
			JLabel cardLabel2 = new JLabel(new ImageIcon(cardArray[dealerCard5].getCardImage()));
			Dimension size3 = cardLabel2.getPreferredSize();
			cardLabel2.setBounds(1750, 300,size3.width, size3.height);
			panel.add(cardLabel2);
			
		}
		panel.repaint();
	}
	
	public static void resetHands() throws IOException {
		standBoolean = false;
		dealBoolean = false;
		playerCard1 = -1;
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
		dealerCards = -1;
		dealerCard1Value = 0;
		out.writeInt(0);
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
	
	public static void backMenu() throws IOException {
		menu = new JFrame("Blackjack Menu");
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(300,200);
		menuPanel = new JPanel();
		menuPanel.setLayout(null);
		menuPanel.setBackground(new Color(0,128,0));
		menu.add(menuPanel);
		JLabel blackjackMenu = new JLabel("Main Menu");
		blackjackMenu.setFont(new Font("Arial",Font.PLAIN,35));
		Dimension sizeBlackJackMenu = blackjackMenu.getPreferredSize();
		blackjackMenu.setBounds(menu.getWidth()/5, menu.getHeight()/15, sizeBlackJackMenu.width, sizeBlackJackMenu.height);
		JButton StartGame = new JButton("Start Game");
		Dimension sizeStartGame = StartGame.getPreferredSize();
		StartGame.setBounds(menu.getWidth()/3,menu.getHeight()/3,sizeStartGame.width,sizeStartGame.height);
		StartGame.addActionListener(new startGame());
		menuPanel.add(blackjackMenu);
		menuPanel.add(StartGame);
		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
	}
	
	static class dealCards implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
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
			}
			dealBoolean = true;
		}
	}
	
	static class hit implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
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
			}
		}
	}
	static class stand implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
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
			}
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
	static class backMenu implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			try {
				backMenu();
				frame.setVisible(false);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
	}
}



