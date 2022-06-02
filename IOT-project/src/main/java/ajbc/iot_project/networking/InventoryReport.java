package ajbc.iot_project.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


import com.google.gson.Gson;

import ajbc.iot_project.models.IOTThing;

public class InventoryReport implements Runnable{
	
	private IOTThing thing;
	private String serverName;
	private int serverPort;
	
	public InventoryReport(IOTThing thing, String serverName, int serverPort) {
		this.thing = thing;
		this.serverName = serverName;
		this.serverPort = serverPort;
	}
	
	
	@Override
	public void run() {
		try(Socket clientSocket = new Socket(serverName,serverPort);
				BufferedReader bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);){
			
			System.out.println("Client connected to server");
			
			thing.simulateInventoryChange();
			
			Gson gson = new Gson();
			String thingJson = gson.toJson(thing, IOTThing.class);
			
			
			writer.println(thingJson);
			System.out.println("IOT thing "+ thing.getUuid() +" sent to the server");
			

			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("prp");
		}
		
	}
	


}
