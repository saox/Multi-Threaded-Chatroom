
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;
/*
 * Wait for connections in a loop.
 * If someone connects, create a new socket and add it to the list of chat users (chatters) 
 * and start a new server thread for that user.
 * 
 * This allows multi-threading and handling of many clients simultaneously connected
 */
public class Server {

	private ServerSocket connection;
	private Socket clientSocket;
	public ArrayList<serverThread> chatters = new ArrayList<serverThread>();
	private ObjectOutputStream output;
	
	public void start() {
		try {
		waitForConnection();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void waitForConnection() throws IOException{
		System.out.println("Waiting for connections");
		connection = new ServerSocket(1234);
		while(true){
			clientSocket = connection.accept();
			
			serverThread st = new serverThread(clientSocket, chatters);
			chatters.add(st);
			Thread t1 = new Thread(st);
			t1.start();
		}
	}
	

	

}
