import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CHAT extends JFrame
{
	JButton send, Submit;
	JTextField Write;
	static JTextField Name;
	JTextArea Field;
	JPanel panel = new JPanel();
	
    BufferedReader in;
    PrintWriter out;
	
    //boolean is = true;
	String nam;
	String IP;
	
	public CHAT()
	{

		Toolkit t = Toolkit.getDefaultToolkit();
		 
		
		Dimension d = t.getScreenSize();
		this.setSize(d.width / 3, d.height / 2);
		int xPos = (d.width / 2) - (this.getWidth() / 2);
		int yPos = (d.height / 2) - (this.getHeight() / 2);
		
		this.setLocation(xPos, yPos);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Chat");
		
		JLabel test = new JLabel(" ");
		panel.add(test);
		
		Submit = new JButton("Submit Name =>");
		//panel.add(Submit);
		
		Name = new JTextField("", 20);
		//panel.add(Name);		
		
		Write = new JTextField("", 20);
		Write.addActionListener(action);
		//Name.addActionListener(faction);
		panel.add(Write);
		
		send = new JButton("Send");
		ListneForButton lFB = new ListneForButton();
		send.addActionListener(lFB);
		//Submit.addActionListener(lFB);
		panel.add(send);
		
		Field = new JTextArea(25, 40);
		Field.setLineWrap(true);
		Field.setWrapStyleWord(true);
		JScrollPane uyu = new JScrollPane(Field, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		DefaultCaret caret = (DefaultCaret)Field.getCaret();
		 caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		Field.setEditable(false);
		/*
		Field.setEnabled(false);
        Color c = new Color(0,0,0,250);
        Field.setBackground(c);
		*/
		
		panel.add(uyu);
		this.add(panel);
		
		this.setVisible(true);
		Name.requestFocus();
	
		IP = JOptionPane.showInputDialog(panel,
				"Write the IP-Adress of the server."
					+ "\n"
				+ "Write 127.0.0.1/localhost if server is the same machine",
				"IP-Connection",
				JOptionPane.INFORMATION_MESSAGE);
		if (IP.equals("localhost") || IP.equals("lan") || IP.equals("l"))
		{
			IP = "127.0.0.1";
		}
		
		nam = JOptionPane.showInputDialog(panel,
				"First, start by picking your name." + "\n" 
				+ "Submit by either pressing the button or enter.",
				"User Name",
				JOptionPane.INFORMATION_MESSAGE);
	}
	/*
	Action faction = new AbstractAction()
	{
	    public void actionPerformed(ActionEvent e)
	    {
	    String User = Name.getText();
	    	if (is == true)
	    	{   	
			Submit.setEnabled(false);
			Name.setEnabled(false);
			Field.append("Name registered: " + User + "\n");
			send.setEnabled(true);
			Write.setEnabled(true);
			Write.setText("");
			Write.requestFocus();
			is = false;
	    }
	    }
	};
	*/
	
	Action action = new AbstractAction()
	{
	    public void actionPerformed(ActionEvent e)
	    {
	    		String getValue = Write.getText();
				out.println(Write.getText());
				Write.setText("");
				Write.requestFocus();
	    }
	};
	
	private class ListneForButton implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String User = Name.getText();
			
			if(e.getSource() == Submit)
			{
				Submit.setEnabled(false);
				Name.setEnabled(false);
				
				send.setEnabled(true);
				Write.setEnabled(true);

				}
			
			if (e.getSource() == send)
			{
				String getValue = Write.getText();
				out.println( getValue);
				Write.setText("");
				Write.requestFocus();

			}
		}
	}
    
	private void run() throws IOException {

        // Make connection and initialize streams 
        Socket socket = new Socket(IP, 9001);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Process all messages from server, according to the protocol.
        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                out.println(nam);
            } else if (line.startsWith("NAMEACCEPTED")) {
                Write.setEditable(true);
            } else if (line.startsWith("MESSAGE")) {
                Field.append(line.substring(8) + "\n");
            }
        }
    }
    
	public static void main(String[]args) throws Exception
	{
		CHAT start = new CHAT();
		start.run();
		start.panel.setVisible(true);
	}
}