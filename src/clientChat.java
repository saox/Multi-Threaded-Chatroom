
import java.awt.EventQueue;
import java.awt.Frame;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class clientChat{
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Socket connection;
	private String message = "";
	private Scanner keyboard;
	private String name;
	private GUI frame;

	/*
	 * Asks for name and sets up the connection with the server, streams and starts the GUI
	 */
	public void startRunning(){
		try {
			System.out.println("What's your name?");
			Scanner keyboard = new Scanner(System.in);
			name = keyboard.nextLine();
			
			connectToServer();
			setupStreams();
			whileChatting();
		} catch (UnknownHostException e) {
			System.out.println("X");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Y");
		}

	}


	private void connectToServer() throws IOException{
		System.out.println("Connecting ...");
		connection = new Socket("localhost", 1234);
		System.out.println("Connected! :-)");
	}

	private void setupStreams() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		System.out.println("Streams are now up!");
	}

	
	//The main chat loop for the client - the GUI window and its thread
	private void whileChatting(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI(output,name);
					frame.setVisible(true);
					
					threadRead read = new threadRead(input,frame);
					Thread t1 = new Thread(read);
					t1.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		

	}
	
}