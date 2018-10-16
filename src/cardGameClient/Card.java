package cardGameClient;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Card {

	private int cardNumber; //Number of the card in the array, 0...51
	private int cardValue; //Value of the card, 2,3...,9,10,11
	private BufferedImage cardImage; //Image representing the card
	static Card[] cardArray = new Card[52]; //Array containing card objects
	
	//Constructor
	public Card(int cardNumber, int cardValue, BufferedImage cardImage) {
		this.cardNumber = cardNumber;
		this.cardValue = cardValue;
		this.cardImage = cardImage;
	}
	
	public static void main(String [] args) throws IOException {
		
		//Hearts
		cardArray[0] = new Card(0,2,ImageIO.read(new File("cardImages\\2_of_hearts.png")));
		cardArray[1] = new Card(1,3,ImageIO.read(new File("cardImages\\3_of_hearts.png")));
		cardArray[2] = new Card(2,4,ImageIO.read(new File("cardImages\\4_of_hearts.png")));
		cardArray[3] = new Card(3,5,ImageIO.read(new File("cardImages\\5_of_hearts.png")));
		cardArray[4] = new Card(4,6,ImageIO.read(new File("cardImages\\6_of_hearts.png")));
		cardArray[5] = new Card(5,7,ImageIO.read(new File("cardImages\\7_of_hearts.png")));
		cardArray[6] = new Card(6,8,ImageIO.read(new File("cardImages\\8_of_hearts.png")));
		cardArray[7] = new Card(7,9,ImageIO.read(new File("cardImages\\9_of_hearts.png")));
		cardArray[8] = new Card(8,10,ImageIO.read(new File("cardImages\\10_of_hearts.png")));
		cardArray[9] = new Card(9,10,ImageIO.read(new File("cardImages\\jack_of_hearts.png")));
		cardArray[10] = new Card(10,10,ImageIO.read(new File("cardImages\\queen_of_hearts.png")));
		cardArray[11] = new Card(11,10,ImageIO.read(new File("cardImages\\king_of_hearts.png")));
		cardArray[12] = new Card(12,11,ImageIO.read(new File("cardImages\\ace_of_hearts.png")));
		
		//Spades
		cardArray[13] = new Card(13,2,ImageIO.read(new File("cardImages\\2_of_spades.png")));
		cardArray[14] = new Card(14,3,ImageIO.read(new File("cardImages\\3_of_spades.png")));
		cardArray[15] = new Card(15,4,ImageIO.read(new File("cardImages\\4_of_spades.png")));
		cardArray[16] = new Card(16,5,ImageIO.read(new File("cardImages\\5_of_spades.png")));
		cardArray[17] = new Card(17,6,ImageIO.read(new File("cardImages\\6_of_spades.png")));
		cardArray[18] = new Card(18,7,ImageIO.read(new File("cardImages\\7_of_spades.png")));
		cardArray[19] = new Card(19,8,ImageIO.read(new File("cardImages\\8_of_spades.png")));
		cardArray[20] = new Card(20,9,ImageIO.read(new File("cardImages\\9_of_spades.png")));
		cardArray[21] = new Card(21,10,ImageIO.read(new File("cardImages\\10_of_spades.png")));
		cardArray[22] = new Card(22,10,ImageIO.read(new File("cardImages\\jack_of_spades.png")));
		cardArray[23] = new Card(23,10,ImageIO.read(new File("cardImages\\queen_of_spades.png")));
		cardArray[24] = new Card(24,10,ImageIO.read(new File("cardImages\\king_of_spades.png")));
		cardArray[25] = new Card(25,11,ImageIO.read(new File("cardImages\\ace_of_spades.png")));
		
		//Diamonds
		cardArray[26] = new Card(26,2,ImageIO.read(new File("cardImages\\2_of_diamonds.png")));
		cardArray[27] = new Card(27,3,ImageIO.read(new File("cardImages\\3_of_diamonds.png")));
		cardArray[28] = new Card(28,4,ImageIO.read(new File("cardImages\\4_of_diamonds.png")));
		cardArray[29] = new Card(29,5,ImageIO.read(new File("cardImages\\5_of_diamonds.png")));
		cardArray[30] = new Card(30,6,ImageIO.read(new File("cardImages\\6_of_diamonds.png")));
		cardArray[31] = new Card(31,7,ImageIO.read(new File("cardImages\\7_of_diamonds.png")));
		cardArray[32] = new Card(32,8,ImageIO.read(new File("cardImages\\8_of_diamonds.png")));
		cardArray[33] = new Card(33,9,ImageIO.read(new File("cardImages\\9_of_diamonds.png")));
		cardArray[34] = new Card(34,10,ImageIO.read(new File("cardImages\\10_of_diamonds.png")));
		cardArray[35] = new Card(35,10,ImageIO.read(new File("cardImages\\jack_of_diamonds.png")));
		cardArray[36] = new Card(36,10,ImageIO.read(new File("cardImages\\queen_of_diamonds.png")));
		cardArray[37] = new Card(37,10,ImageIO.read(new File("cardImages\\king_of_diamonds.png")));
		cardArray[38] = new Card(38,11,ImageIO.read(new File("cardImages\\ace_of_diamonds.png")));
		
		//Clubs
		cardArray[39] = new Card(39,2,ImageIO.read(new File("cardImages\\2_of_clubs.png")));
		cardArray[40] = new Card(40,3,ImageIO.read(new File("cardImages\\3_of_clubs.png")));
		cardArray[41] = new Card(41,4,ImageIO.read(new File("cardImages\\4_of_clubs.png")));
		cardArray[42] = new Card(42,5,ImageIO.read(new File("cardImages\\5_of_clubs.png")));
		cardArray[43] = new Card(43,6,ImageIO.read(new File("cardImages\\6_of_clubs.png")));
		cardArray[44] = new Card(44,7,ImageIO.read(new File("cardImages\\7_of_clubs.png")));
		cardArray[45] = new Card(45,8,ImageIO.read(new File("cardImages\\8_of_clubs.png")));
		cardArray[46] = new Card(46,9,ImageIO.read(new File("cardImages\\9_of_clubs.png")));
		cardArray[47] = new Card(47,10,ImageIO.read(new File("cardImages\\10_of_clubs.png")));
		cardArray[48] = new Card(48,10,ImageIO.read(new File("cardImages\\jack_of_clubs.png")));
		cardArray[49] = new Card(49,10,ImageIO.read(new File("cardImages\\queen_of_clubs.png")));
		cardArray[50] = new Card(50,10,ImageIO.read(new File("cardImages\\king_of_clubs.png")));
		cardArray[51] = new Card(51,11,ImageIO.read(new File("cardImages\\ace_of_clubs.png")));
		
	}
	
	//Get the value of the card
	public int getCardValue() {
		return cardValue;
	}
	
	//Get the number of the card
	public int getCardNumber() {
		return cardNumber;
	}
	
	public Image getCardImage() {
		Image resizeImage = cardImage.getScaledInstance(100, 145, Image.SCALE_SMOOTH);
		return resizeImage;
	}
	
}
