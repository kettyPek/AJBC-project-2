package ajbc.iot_project.networking.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import ajbc.iot_project.DBservice.DBService;
import ajbc.iot_project.models.IOTThing;

/**
 * This class implements Runnable interface and handles DB updates
 * @author ketty
 *
 */
public class InventoryServerRunnable implements Runnable {

	private Socket clientSocket;
	private DBService dbService;
	
	public InventoryServerRunnable(Socket clientSocket) {
		this.clientSocket = clientSocket;
		dbService = new DBService();
	}
	
	
	@Override
	public void run() {

		try(BufferedReader bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);){
			
			JsonReader jasonReader = new JsonReader(bufferReader);
			
			Gson gson = new Gson();
			IOTThing thing = gson.fromJson(jasonReader, IOTThing.class);
			
			System.out.println(thing);
			
			dbService.updateDB(thing);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}