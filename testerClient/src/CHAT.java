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
	
	boolean is = true;
	String nam;
	
	public CHAT()
	{

		Toolkit t = Toolkit.getDefaultToolkit();
		 
		
		Dimension d = t.getScreenSize();
		this.setSize(d.width / 4, d.height / 2);
		int xPos = (d.width / 2) - (this.getWidth() / 2);
		int yPos = (d.height / 2) - (this.getHeight() / 2);
		
		this.setLocation(xPos, yPos);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Chat");
		
		JLabel test = new JLabel(" ");
		panel.add(test);
		
		Submit = new JButton("Submit Name =>");
		panel.add(Submit);
		
		Name = new JTextField("", 20);
		panel.add(Name);		
		
		Write = new JTextField("", 20);
		Write.addActionListener(action);
		Name.addActionListener(faction);
		panel.add(Write);
		Write.setEnabled(false);
		
		send = new JButton("Send");
		ListneForButton lFB = new ListneForButton();
		send.addActionListener(lFB);
		Submit.addActionListener(lFB);
		panel.add(send);
		send.setEnabled(false);
		
		Field = new JTextArea(25, 40);
		Field.setLineWrap(true);
		Field.setWrapStyleWord(true);
		JScrollPane uyu = new JScrollPane(Field, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		DefaultCaret caret = (DefaultCaret)Field.getCaret();
		 caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		Field.setEnabled(false);
        Color c = new Color(0,0,0,250);
        Field.setBackground(c);
		
		panel.add(uyu);
		this.add(panel);
		
		this.setVisible(true);
		Name.requestFocus();
		
		JOptionPane.showMessageDialog(panel,
				"First, start by picking your name",
				"User Name",
				JOptionPane.PLAIN_MESSAGE);
	}
	
	Action faction = new AbstractAction()
	{
	    public void actionPerformed(ActionEvent e)
	    {
	    String User = Name.getText();
	    nam = User;
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
	
	Action action = new AbstractAction()
	{
	    public void actionPerformed(ActionEvent e)
	    {	
	    	if (is == false)
			{
	    		String getValue = Write.getText();
				out.println(Write.getText());
				Write.setText("");
				Write.requestFocus();
	    }
	    }
	};
	
	private class ListneForButton implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String User = Name.getText();
	    	nam = User;
			
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
	
/*
	private class ListenForWindow implements WindowListener
	{
		String User = Name.getText();
		
		
		public void windowActivated(WindowEvent e) 
		{
			Field.append(nam + ":" + " is back" + "\n");
			
		}

		
		public void windowClosed(WindowEvent arg0) 
		{
			
			
		}

		
		public void windowClosing(WindowEvent arg0) 
		{
			
			
		}

		
		public void windowDeactivated(WindowEvent arg0) 
		{
			Field.append("\n" + nam + ":" + " is away" + "\n");
		}

		
		public void windowDeiconified(WindowEvent arg0) 
		{
			Field.append("Window in normal state" + "\n");
			
		}

		
		public void windowIconified(WindowEvent arg0) 
		{
			Field.append("Window is minimized" + "\n");
			
		}

		
		public void windowOpened(WindowEvent arg0) 
		{
			Field.append("Window is created" + "\n");
			
		}
		
	}
*/
    
	private void run() throws IOException {

        // Make connection and initialize streams 
        Socket socket = new Socket("localhost", 9001);
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