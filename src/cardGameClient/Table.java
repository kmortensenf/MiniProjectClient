package cardGameClient;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Table {
	private BufferedImage TableImage;
	public Table(BufferedImage TableImage) {
		this.TableImage = TableImage;
}
	public static void main(String [] args) throws IOException {
		ImageIO.read(new File("Tablegreen.png"));
		
		
		
		
		
	}
	public Image getTableImage() {
		Image resizeImage = TableImage.getScaledInstance(100, 145, Image.SCALE_SMOOTH);
		return resizeImage;
	}
}
