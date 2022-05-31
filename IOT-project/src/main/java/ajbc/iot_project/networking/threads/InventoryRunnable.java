package ajbc.iot_project.networking.threads;

import java.net.Socket;

import ajbc.iot_project.DBservice.DBService;

/**
 * This class implements Runnable interface and handles DB updates
 * @author ketty
 *
 */
public class InventoryRunnable implements Runnable {

	private Socket clientSocket;
	private DBService dbService;
	
	public InventoryRunnable(Socket clientSocket) {
		this.clientSocket = clientSocket;
		dbService = new DBService();
	}
	
	
	@Override
	public void run() {
		//TODO write run method for server

	}

}
