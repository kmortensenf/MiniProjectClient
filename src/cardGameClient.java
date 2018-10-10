package cardGameClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class cardGameClient {

	public static void main(String [] args) throws UnknownHostException, IOException {
		Scanner console = new Scanner(System.in);
		boolean connected = true;
		Socket socket = new Socket("localhost", 8085);
		DataInputStream in = new DataInputStream(socket.getInputStream());
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		
		
		try {
			
			while(connected) {
				System.out.println("Press 1 to request card");
				if (console.nextInt()==1) {
					out.writeInt(1);	
				}
			}
		} catch(IOException ex) {
			System.out.println(ex);
		}
	}
}
