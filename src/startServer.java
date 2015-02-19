/*
 * Author: Konrad Ilczuk
 * 
 * Date: May 2013
 * Project: Multithreaded Server for a Chat program
 * 
 * Description: A project for the Internet Programming course at KTH(Royal Institute Of Technology)
 *  
 */

/*
 * This class starts the server
 */
public class startServer {
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}

}
