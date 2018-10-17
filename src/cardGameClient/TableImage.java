package cardGameClient;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.io.IOException;

public class TableImage {
	public TableImage() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Table Image");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				BufferedImage img = null;
				try {
					img = ImageIO.read(getClass().getResource("/Image.png"));
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(1);
				}
				ImageIcon imgIcon = new ImageIcon(img);
				JLabel lbl = new JLabel();
				lbl.setIcon(imgIcon);
				frame.getContentPane().add(lbl, BorderLayout.CENTER);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}	
			
		});
	}
	public static void main(String [] args) throws IOException {

new TableImage();
}
}