package testerClient10;

import java.awt.*;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class testerClient10 extends Card {
	
	public static Socket socket;
	public static DataInputStream in;
	public static DataOutputStream out;
	public static int playerCard1 = 0;
	public static int playerCard2 = 0;
	public static int playerCard3 = 0;
	public static int playerCard4 = 0;
	public static int playerCards = 0;
	public static int dealerCard1 = 0;
	public static int dealerCard2 = 0;
	public static int dealerCard3 = 0;
	public static int dealerCards = 0;
	public static boolean standBoolean = false;
	public static boolean dealBoolean = false;
	public static JFrame frame;
	public static JTextArea TextArea;
	public static JPanel panel;
	
	public testerClient10() throws IOException {
		panel = new JPanel();
		panel.setBackground(new Color(0,128,0));
		TextArea = new JTextArea(10,40);
		JLabel blackjackName = new JLabel("Blackjack!");
		blackjackName.setFont(new Font("Arial",Font.PLAIN,35));
		panel.add(blackjackName);
		panel.add(TextArea);
		frame.add(panel);
		JButton Deal = new JButton("Deal Cards");
		JButton Hit = new JButton("Hit");
		JButton Stand = new JButton("Stand");
		JButton Reset = new JButton("Reset");
		panel.add(Deal);
		panel.add(Hit);
		panel.add(Stand);
		panel.add(Reset);
		Deal.addActionListener(new dealCards());
		Hit.addActionListener(new hit());
		Stand.addActionListener(new stand());
		Reset.addActionListener(new reset());
		frame.setVisible(true);
	}
	
	public testerClient10(int cardNumber, BufferedImage cardImage) throws UnknownHostException, IOException {
		super(cardNumber, cardImage);
	}

	public static void main(String [] args) throws IOException {
		//testerClient10 client1 = new testerClient10();
		socket = new Socket("localhost",8087);
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		createArray();
		frame = new JFrame("Blackjack");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GraphicsEnvironment applicationEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle maxBounds = applicationEnv.getMaximumWindowBounds();
		frame.setBounds(maxBounds);
		panel = new JPanel();
		panel.setBackground(new Color(0,128,0));
		TextArea = new JTextArea(10,40);
		JLabel blackjackName = new JLabel("Blackjack!");
		blackjackName.setFont(new Font("Arial",Font.PLAIN,35));
		panel.add(blackjackName);
		panel.add(TextArea);
		frame.add(panel);
		JButton Deal = new JButton("Deal Cards");
		JButton Hit = new JButton("Hit");
		JButton Stand = new JButton("Stand");
		JButton Reset = new JButton("Reset");
		panel.add(Deal);
		panel.add(Hit);
		panel.add(Stand);
		panel.add(Reset);
		Deal.addActionListener(new dealCards());
		Hit.addActionListener(new hit());
		Stand.addActionListener(new stand());
		Reset.addActionListener(new reset());
		frame.setVisible(true);
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
		dealerCard2 = in.readInt();
		dealerCards = in.readInt();
	}
	
	public static void displayPlayerCards(Card cardArray[]) throws IOException {
		JLabel cardLabel0 = new JLabel(new ImageIcon(cardArray[playerCard1].getCardImage()));
		JLabel cardLabel1 = new JLabel(new ImageIcon(cardArray[playerCard2].getCardImage()));
		JLabel cardString = new JLabel(new String("Player cards: "));

		cardLabel0.setSize(100,300);
		cardLabel1.setSize(400,600);

		panel.add(cardString);
		panel.add(cardLabel0);
		panel.add(cardLabel1);
	}
	
	public static void displayDealerCards(Card cardArray[]) throws IOException {
		JLabel cardLabel0 = new JLabel(new ImageIcon(cardArray[dealerCard1].getCardImage()));
		JLabel cardLabel1 = new JLabel(new ImageIcon(cardArray[dealerCard2].getCardImage()));
		JLabel cardString = new JLabel(new String("Dealer cards: "));

		cardLabel0.setSize(100,145);
		cardLabel1.setSize(300,145);
		
		panel.add(cardString);
		panel.add(cardLabel0);
		panel.add(cardLabel1);
	}
	
	public static void requestHit(Card cardArray[]) throws IOException {
		playerCard3 = in.readInt();
		playerCards = in.readInt();
		
		System.out.println("The number of the third card is: " + cardArray[playerCard3].getCardNumber());
		System.out.println("New value of your cards: " + playerCards);
	}
	
	public static void displayHit(Card cardArray[]) throws IOException {
		JLabel cardLabel0 = new JLabel(new ImageIcon(cardArray[playerCard3].getCardImage()));

		cardLabel0.setSize(100,300);

		panel.add(cardLabel0);
	}
	
	public static void requestStand(Card cardArray[]) throws IOException {
		dealerCard3 = in.readInt();
		dealerCards = in.readInt();
		System.out.println("3rd card number: " + dealerCard3);
		System.out.println("total value dealer: " + dealerCards);
	}
	
	public static void displayDealerStand(Card cardArray[]) throws IOException {
		JLabel cardLabel0 = new JLabel(new ImageIcon(cardArray[dealerCard3].getCardImage()));
		JLabel cardString = new JLabel(new String("Dealer extra cards: "));
		
		cardLabel0.setSize(100,300);

		panel.add(cardString);
		panel.add(cardLabel0);

	}
	
	public static void resetHands() throws IOException {
		standBoolean = false;
		dealBoolean = false;
		playerCard1 = 0;
		playerCard2 = 0;
		playerCard3 = 0;
		playerCard4 = 0;
		playerCards = 0;
		dealerCard1 = 0;
		dealerCard2 = 0;
		dealerCard3 = 0;
		dealerCards = 0;
		out.writeInt(0);
		//frame.dispose();
		frame.remove(panel);
		new testerClient10();
		TextArea.append("New Game Starting:");
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
					TextArea.append("\nValue of dealer cards: " + Integer.toString(dealerCards));
				} else if (playerCards > 21 && dealerCards > 21 || playerCards > 21 && dealerCards < 21 || playerCards < 21 && dealerCards > 21) {
					TextArea.append("\nYou need to reset!");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if (playerCards == 21 && playerCard3 == 0) {
				TextArea.append("\nYou got blackjack!\nYou win!");
				TextArea.append("\nYou need to reset!");
			}
			if (dealerCards == 21 && dealerCard3 == 0) {
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
				} if (playerCards == 21 && playerCard3 == 0) {
					TextArea.append("\nYou got blackjack!\nYou win!");
				}
				} else if (playerCards == 0){
					TextArea.append("\nYou need to deal cards first!");
				} else if (playerCards > 0) {
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
				if(dealerCards < 17 && playerCards < 21 && dealBoolean == true || dealerCards < 17 && playerCards == 21 && playerCard3 > 0 && dealBoolean == true) {
					while(dealerCards < 17) {
						out.writeInt(3);
						requestStand(cardArray);
						displayDealerStand(cardArray);
					}
					TextArea.append("\nYou chose to stand\nYour total value is: " + playerCards);
					TextArea.append("\nValue of dealer cards: " + Integer.toString(dealerCards));
					if (playerCards < 21 && dealerCards < playerCards || playerCards == 21 & dealerCards < playerCards) {
						TextArea.append("\nYou win!\nYou need to reset");
					}
					if (dealerCards > playerCards && dealerCards < 21 || dealerCards > playerCards && dealerCards == 21) {
						TextArea.append("\nDealer wins!");
					}
					if (dealerCards > 21 && playerCards < 21) {
						TextArea.append("\nDealer is over 21\nYou win!");
					}
					if (dealerCards == playerCards) {
						TextArea.append("\nIt is a draw!");
					} 
					standBoolean = true;
				} else if (playerCards > 21 || dealerCards > 21) {
					TextArea.append("\nYou need to reset!");
				} else if (dealerCards > playerCards && dealerCards < 21 || dealerCards > playerCards && dealerCards == 21) {
					TextArea.append("\nDealer wins!");
				} else if (playerCards < 21 && dealerCards < playerCards || playerCards == 21 & dealerCards < playerCards) {
					TextArea.append("\nYou win!\nYou need to reset");
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
	}

