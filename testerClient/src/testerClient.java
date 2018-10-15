import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

public class testerClient extends Card {
	
	static String dealCardsTwo = "dealCardsTwo";
	public static Socket socket;
	public static DataInputStream in;
	public static DataOutputStream out;
	public static int playerCards = 0;
	public static int playerCard1 = 0;
    public static int aces = 0;
	public static int playerCard2 = 0;
	public static int cardsValue = 0;
	public static int playerCard3 = 0;
	public static int playerCard4 = 0;
	public static int receiveInfo = 0;
	public static int cardsUpdateValue = 0;
	public static int cardsUpdateValue2 = 0;
	public static int dealerCard1 = 0;
	public static int dealerCard2 = 0;
	public static int dealerCardsValue = 0;
	public static JFrame frame;
	public static JTextArea TextArea;
	public static JPanel panel;
	public static int numberOfHits;
	
	public testerClient(int cardNumber, int cardValue, BufferedImage cardImage) {
		super(cardNumber, cardValue, cardImage);
		// TODO Auto-generated constructor stub
	}

	public static void main(String [] args) throws IOException {
		socket = new Socket("localhost",8086);
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		createArray();
		frame = new JFrame("Blackjack");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GraphicsEnvironment applicationEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle maxBounds = applicationEnv.getMaximumWindowBounds();
		frame.setBounds(maxBounds);
		panel = new JPanel();
		TextArea = new JTextArea(10,40);
		panel.add(TextArea);
		frame.add(panel);
		JButton Deal = new JButton("Deal Cards");
		JButton Hit = new JButton("Hit");
		JButton Stand = new JButton("Stand");
		panel.add(Deal);
		panel.add(Hit);
		panel.add(Stand);
		Deal.addActionListener(new dealCards());
		Hit.addActionListener(new hit());
		Stand.addActionListener(new stand());
		frame.setVisible(true);
	}
	
	public static void Deal(DataInputStream in, DataOutputStream out) {
		System.out.println(dealCardsTwo);
	}
	
	public static void dealCards(Card cardArray[], DataInputStream in, DataOutputStream out) throws IOException {
		//Random rand = new Random();
		//int number = rand.nextInt(51);
		out.writeInt(1);
		int receiveInfo = in.readInt();
		if (receiveInfo < 52) {
			playerCard1 = receiveInfo;
		}
		int receiveInfo2 = in.readInt();
		if (receiveInfo2 < 52) {
			dealerCard1 = receiveInfo2;
		}
		int receiveInfo3 = in.readInt();
		if (receiveInfo3 < 52) {
			playerCard2 = receiveInfo3;
		}
		int receiveInfo4 = in.readInt();
		if (receiveInfo4 < 52) {
			dealerCard2 = receiveInfo4;
		}
		cardsValue = 0;
		int aces = 0;
		System.out.println("The value of the first card is: " + cardArray[playerCard1].getCardValue() + "\n" + "The number of the first card is: " + cardArray[playerCard1].getCardNumber());
		System.out.println("The value of the second card is: " + cardArray[playerCard2].getCardValue() + "\n" + "The number of the second card is: " + cardArray[playerCard2].getCardNumber());
		if (cardArray[playerCard1].getCardNumber() == 12 || cardArray[playerCard1].getCardNumber() == 25 || cardArray[playerCard1].getCardNumber() == 38 || cardArray[playerCard1].getCardNumber() == 51) {
			aces++;
		} if (cardArray[playerCard2].getCardNumber() == 12 || cardArray[playerCard2].getCardNumber() == 25 || cardArray[playerCard2].getCardNumber() == 38 || cardArray[playerCard2].getCardNumber() == 51) {
			aces++;
		} if (aces == 2) {
			cardsValue = cardArray[playerCard1].getCardValue() + cardArray[playerCard2].getCardValue() - 10;
		} else {
			cardsValue = cardArray[playerCard1].getCardValue() + cardArray[playerCard2].getCardValue();
		}
		System.out.println("The value of the first dealer card is: " + cardArray[dealerCard1].getCardValue() + "\n" + "The number of the first dealer card is: " + cardArray[dealerCard1].getCardNumber());
		System.out.println("The value of the second dealer card is: " + cardArray[dealerCard2].getCardValue() + "\n" + "The number of the second dealer card is: " + cardArray[dealerCard2].getCardNumber());
		if (cardArray[dealerCard1].getCardNumber() == 12 || cardArray[dealerCard1].getCardNumber() == 25 || cardArray[dealerCard1].getCardNumber() == 38 || cardArray[dealerCard1].getCardNumber() == 51) {
			aces++;
		} if (cardArray[dealerCard2].getCardNumber() == 12 || cardArray[dealerCard2].getCardNumber() == 25 || cardArray[dealerCard2].getCardNumber() == 38 || cardArray[dealerCard2].getCardNumber() == 51) {
			aces++;
		} if (aces == 2) {
			dealerCardsValue = cardArray[dealerCard1].getCardValue() + cardArray[dealerCard2].getCardValue() - 10;
		} else {
			dealerCardsValue = cardArray[dealerCard1].getCardValue() + cardArray[dealerCard2].getCardValue();
		}
		System.out.println("Value of your cards: " + cardsValue);
	}
	
	public static void displayCards(Card cardArray[]) throws IOException {
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
	
	public static void requestHit(Card cardArray[],DataInputStream in, DataOutputStream out) throws IOException {
		numberOfHits++;

		if (numberOfHits==1) {

			out.writeInt(2);
			receiveInfo = in.readInt();
			if (receiveInfo < 52) {
				playerCard3 = receiveInfo;
			}

			System.out.println("The value of the third card is: " + cardArray[playerCard3].getCardValue() + "\n" + "The number of the third card is: " + cardArray[playerCard3].getCardNumber());
			if (cardArray[playerCard3].getCardNumber() == 12 || cardArray[playerCard3].getCardNumber() == 25 || cardArray[playerCard3].getCardNumber() == 38 || cardArray[playerCard3].getCardNumber() == 51) {
				aces++;
			}
			if (cardsValue + cardArray[playerCard3].getCardValue() > 21 && aces > 0) {
				cardsUpdateValue = cardsValue + cardArray[playerCard3].getCardValue() - 10;
				aces--;
			} else {
				cardsUpdateValue = cardsValue + cardArray[playerCard3].getCardValue();
			}
			System.out.println("New value of your cards: " + cardsUpdateValue);
		}

		if (numberOfHits== 2){


            out.writeInt(3);
            receiveInfo = in.readInt();
		if (receiveInfo < 52) {
			playerCard4 = receiveInfo;
		}
		if (cardArray[playerCard4].getCardNumber() == 12 || cardArray[4].getCardNumber() == 25 || cardArray[playerCard4].getCardNumber() == 38 || cardArray[playerCard4].getCardNumber() == 51) {
			aces++;

		}
		if (cardsValue + cardArray[playerCard4].getCardValue() > 21 && aces > 0) {
			cardsUpdateValue2 = cardsValue + cardArray[playerCard4].getCardValue() -10;
			aces--;
		} else {
			cardsUpdateValue2 = cardsValue + cardArray[playerCard4].getCardValue();
		}
		System.out.println("New value of your cards: " + cardsUpdateValue2);

}
System.out.println(numberOfHits);
	}
	
	public static void displayHit(Card cardArray[], DataInputStream in) throws IOException {
		JLabel cardLabel0 = new JLabel(new ImageIcon(cardArray[receiveInfo].getCardImage()));

		cardLabel0.setSize(100,300);

		panel.add(cardLabel0);
	}
	
	static class dealCards implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				dealCards(cardArray,in,out);
				displayDealerCards(cardArray);
				displayCards(cardArray);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			TextArea.append("Value of cards: " + Integer.toString(cardsValue));
			TextArea.append("\nValue of dealer cards: " + Integer.toString(dealerCardsValue));
		}
		
	}
	
	static class hit implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				requestHit(cardArray,in,out);
				displayHit(cardArray,in);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if (cardsUpdateValue > 21) {
				TextArea.append("\nYou are over 21!\nDealer wins!");
			} else {
			TextArea.append("\nNew value of cards: " + Integer.toString(cardsUpdateValue));
			}
		
		}
	}
	static class stand implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				out.writeInt(1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println("Stand");
			TextArea.append("\nStand");
		}
		
	}
}
