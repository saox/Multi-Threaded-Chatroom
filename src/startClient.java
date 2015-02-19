
import java.awt.EventQueue;

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
 * This class starts a client
 */

public class startClient {

	public static void main(String[] args) {
		clientChat program = new clientChat();
		program.startRunning();
		
	}
}
