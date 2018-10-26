package singlePlayerBlackjackClient;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

public class Card {

	private int cardNumber; //Number of the card in the array, 0...51
	private BufferedImage cardImage; //Image representing the card
	public static Card[] cardArray = new Card[52]; //Array containing card objects
	
	
	// Default constructor
	public Card() {
	}
	
	public Card(int cardNumber, BufferedImage cardImage) {
		this.cardNumber = cardNumber;
		this.cardImage = cardImage;
	}
	
	public void createArray() throws IOException, URISyntaxException {
		
		//Hearts
		cardArray[0] = new Card(0,ImageIO.read(Card.class.getResource("/cards/cardImages/2_of_hearts.png")));
		cardArray[1] = new Card(1,ImageIO.read(Card.class.getResource("/cards/cardImages/3_of_hearts.png")));
		cardArray[2] = new Card(2,ImageIO.read(Card.class.getResource("/cards/cardImages/4_of_hearts.png")));
		cardArray[3] = new Card(3,ImageIO.read(Card.class.getResource("/cards/cardImages/5_of_hearts.png")));
		cardArray[4] = new Card(4,ImageIO.read(Card.class.getResource("/cards/cardImages/6_of_hearts.png")));
		cardArray[5] = new Card(5,ImageIO.read(Card.class.getResource("/cards/cardImages/7_of_hearts.png")));
		cardArray[6] = new Card(6,ImageIO.read(Card.class.getResource("/cards/cardImages/8_of_hearts.png")));
		cardArray[7] = new Card(7,ImageIO.read(Card.class.getResource("/cards/cardImages/9_of_hearts.png")));
		cardArray[8] = new Card(8,ImageIO.read(Card.class.getResource("/cards/cardImages/10_of_hearts.png")));
		cardArray[9] = new Card(9,ImageIO.read(Card.class.getResource("/cards/cardImages/jack_of_hearts.png")));
		cardArray[10] = new Card(10,ImageIO.read(Card.class.getResource("/cards/cardImages/queen_of_hearts.png")));
		cardArray[11] = new Card(11,ImageIO.read(Card.class.getResource("/cards/cardImages/king_of_hearts.png")));
		cardArray[12] = new Card(12,ImageIO.read(Card.class.getResource("/cards/cardImages/ace_of_hearts.png")));
		
		//Spades
		cardArray[13] = new Card(13,ImageIO.read(Card.class.getResource("/cards/cardImages/2_of_spades.png")));
		cardArray[14] = new Card(14,ImageIO.read(Card.class.getResource("/cards/cardImages/3_of_spades.png")));
		cardArray[15] = new Card(15,ImageIO.read(Card.class.getResource("/cards/cardImages/4_of_spades.png")));
		cardArray[16] = new Card(16,ImageIO.read(Card.class.getResource("/cards/cardImages/5_of_spades.png")));
		cardArray[17] = new Card(17,ImageIO.read(Card.class.getResource("/cards/cardImages/6_of_spades.png")));
		cardArray[18] = new Card(18,ImageIO.read(Card.class.getResource("/cards/cardImages/7_of_spades.png")));
		cardArray[19] = new Card(19,ImageIO.read(Card.class.getResource("/cards/cardImages/8_of_spades.png")));
		cardArray[20] = new Card(20,ImageIO.read(Card.class.getResource("/cards/cardImages/9_of_spades.png")));
		cardArray[21] = new Card(21,ImageIO.read(Card.class.getResource("/cards/cardImages/10_of_spades.png")));
		cardArray[22] = new Card(22,ImageIO.read(Card.class.getResource("/cards/cardImages/jack_of_spades.png")));
		cardArray[23] = new Card(23,ImageIO.read(Card.class.getResource("/cards/cardImages/queen_of_spades.png")));
		cardArray[24] = new Card(24,ImageIO.read(Card.class.getResource("/cards/cardImages/king_of_spades.png")));
		cardArray[25] = new Card(25,ImageIO.read(Card.class.getResource("/cards/cardImages/ace_of_spades.png")));
		
		//Diamonds
		cardArray[26] = new Card(26,ImageIO.read(Card.class.getResource("/cards/cardImages/2_of_diamonds.png")));
		cardArray[27] = new Card(27,ImageIO.read(Card.class.getResource("/cards/cardImages/3_of_diamonds.png")));
		cardArray[28] = new Card(28,ImageIO.read(Card.class.getResource("/cards/cardImages/4_of_diamonds.png")));
		cardArray[29] = new Card(29,ImageIO.read(Card.class.getResource("/cards/cardImages/5_of_diamonds.png")));
		cardArray[30] = new Card(30,ImageIO.read(Card.class.getResource("/cards/cardImages/6_of_diamonds.png")));
		cardArray[31] = new Card(31,ImageIO.read(Card.class.getResource("/cards/cardImages/7_of_diamonds.png")));
		cardArray[32] = new Card(32,ImageIO.read(Card.class.getResource("/cards/cardImages/8_of_diamonds.png")));
		cardArray[33] = new Card(33,ImageIO.read(Card.class.getResource("/cards/cardImages/9_of_diamonds.png")));
		cardArray[34] = new Card(34,ImageIO.read(Card.class.getResource("/cards/cardImages/10_of_diamonds.png")));
		cardArray[35] = new Card(35,ImageIO.read(Card.class.getResource("/cards/cardImages/jack_of_diamonds.png")));
		cardArray[36] = new Card(36,ImageIO.read(Card.class.getResource("/cards/cardImages/queen_of_diamonds.png")));
		cardArray[37] = new Card(37,ImageIO.read(Card.class.getResource("/cards/cardImages/king_of_diamonds.png")));
		cardArray[38] = new Card(38,ImageIO.read(Card.class.getResource("/cards/cardImages/ace_of_diamonds.png")));
		
		//Clubs
		cardArray[39] = new Card(39,ImageIO.read(Card.class.getResource("/cards/cardImages/2_of_clubs.png")));
		cardArray[40] = new Card(40,ImageIO.read(Card.class.getResource("/cards/cardImages/3_of_clubs.png")));
		cardArray[41] = new Card(41,ImageIO.read(Card.class.getResource("/cards/cardImages/4_of_clubs.png")));
		cardArray[42] = new Card(42,ImageIO.read(Card.class.getResource("/cards/cardImages/5_of_clubs.png")));
		cardArray[43] = new Card(43,ImageIO.read(Card.class.getResource("/cards/cardImages/6_of_clubs.png")));
		cardArray[44] = new Card(44,ImageIO.read(Card.class.getResource("/cards/cardImages/7_of_clubs.png")));
		cardArray[45] = new Card(45,ImageIO.read(Card.class.getResource("/cards/cardImages/8_of_clubs.png")));
		cardArray[46] = new Card(46,ImageIO.read(Card.class.getResource("/cards/cardImages/9_of_clubs.png")));
		cardArray[47] = new Card(47,ImageIO.read(Card.class.getResource("/cards/cardImages/10_of_clubs.png")));
		cardArray[48] = new Card(48,ImageIO.read(Card.class.getResource("/cards/cardImages/jack_of_clubs.png")));
		cardArray[49] = new Card(49,ImageIO.read(Card.class.getResource("/cards/cardImages/queen_of_clubs.png")));
		cardArray[50] = new Card(50,ImageIO.read(Card.class.getResource("/cards/cardImages/king_of_clubs.png")));
		cardArray[51] = new Card(51,ImageIO.read(Card.class.getResource("/cards/cardImages/ace_of_clubs.png")));
		
	}
	//Get the number of the card
	public int getCardNumber() {
		return cardNumber;
	}
	
	public Image getCardImage() {
		Image resizeImage = cardImage.getScaledInstance(100, 145, Image.SCALE_SMOOTH);
		return resizeImage;
	}
	
	public Card[] getCardArray() {
		return cardArray;
	}
}