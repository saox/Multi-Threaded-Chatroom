
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;

/*
 * This class is responsible for the Graphical window
 */

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField msgWindow;
	private JTextArea chatWindow;
	public JButton btnSend;
	public ObjectInputStream input;
	public ObjectOutputStream output;
	String name = "Anonymous";

	public GUI(ObjectOutputStream output, String name) {
		this.output = output;
		this.name = name;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		msgWindow = new JTextField();
		msgWindow.setBounds(10, 426, 326, 23);
		contentPane.add(msgWindow);
		msgWindow.setColumns(10);
		
		msgWindow.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
					sendMessage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
		

		
		chatWindow = new JTextArea();
		chatWindow.setBounds(10, 11, 422, 397);
		contentPane.add(chatWindow);
		chatWindow.setColumns(10);
		
		JScrollPane plainScroll = new JScrollPane(chatWindow);
        plainScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        plainScroll.setBounds(10, 11, 422, 397); 
        contentPane.add(plainScroll);
 

        btnSend = new JButton("Send");
		btnSend.setBounds(343, 426, 89, 23);
		contentPane.add(btnSend);
		
		btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	try {
            		sendMessage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });      

	}
	
	public void updateWindow(String msg){
		this.chatWindow.append(msg+"\n");
	}
	
	public String getMessage(){
		String msg = msgWindow.getText();
		msgWindow.setText("");
		return msg;
	}
	
	public void sendMessage() throws IOException{
		output.writeObject(this.name +": " + getMessage());
		output.flush();
	}
	
}