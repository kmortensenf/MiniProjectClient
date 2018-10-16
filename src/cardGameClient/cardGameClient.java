package cardGameClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class cardGameClient {
	
	public static DataInputStream in;
	public static DataOutputStream out;
	public static int playerCards = 0;

	public static void main(String [] args) throws UnknownHostException, IOException {
		Scanner console = new Scanner(System.in);
		boolean connected = true;
		Socket socket = new Socket("localhost", 8085);
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		
		
		try {
			System.out.println("press 1 - request cards");
			while(connected) {
				if(connected == false) {
					out.writeInt(1);
				}
				//requestCards(in,out,console);
			}
		} catch(IOException ex) {
			System.out.println(ex);
		}
	}
	
	public static void requestCards(DataInputStream in, DataOutputStream out) throws IOException {
		/*if (console.nextInt() == 1) {
			out.writeInt(1);
		}*/
		out.writeInt(1);
		int receiveInfo = in.readInt();
		if (receiveInfo < 21) {
			playerCards = receiveInfo;
		}
	}
}
