
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
/*
 * This is a Server Thread, meaning that this code is run whenever a client successfully connects.
 * 
 */
public class serverThread implements Runnable{

	private Socket clientSocket;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	ArrayList<serverThread> chatters;
	//Constructor
	public serverThread(Socket clientSocket, ArrayList<serverThread> chatters) {
		this.clientSocket = clientSocket;
		this.chatters = chatters;
	}
	//This is the "main" method of the thread
	public void run() {
		try {
			setupStreams();
			whileChatting();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Opens connections
	private void setupStreams() throws IOException{
		output = new ObjectOutputStream(clientSocket.getOutputStream());
		output.flush();
		input = new ObjectInputStream(clientSocket.getInputStream());
	}

	//The main loop which is active as long as we chat
	private void whileChatting() throws IOException {
		do{
			try{
				broadcast((String) input.readObject());
			} catch(ClassNotFoundException Err){
			}
		}while(true);
	}
	
	public void sendMessage(String msg) throws IOException
	{
		output.writeObject(msg);
		output.flush();
	}
	
	//Broadcast the message to all users active in the chatroom
	public void broadcast(String msg){
		for(int i = 0; i<chatters.size();i++){
			try {
				chatters.get(i).sendMessage(msg);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
