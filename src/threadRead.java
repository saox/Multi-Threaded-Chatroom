
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class threadRead implements Runnable{

	ObjectInputStream input;
	GUI gui;
	//Thread that reads and updates the GUI with new messages
	public threadRead(ObjectInputStream input, GUI gui) {
		this.input = input;
		this.gui = gui;
	}

	public void run() {
		while(!input.equals(null)){
			try {
				String msg = (String) input.readObject();
				System.out.println(msg);
				gui.updateWindow(msg);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
