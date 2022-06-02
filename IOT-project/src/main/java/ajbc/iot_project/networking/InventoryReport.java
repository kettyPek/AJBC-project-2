package ajbc.iot_project.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


import com.google.gson.Gson;

import ajbc.iot_project.models.IOTThing;

public class InventoryReport implements Runnable{
	
	private Socket clientSocket;
	private IOTThing thing;
	
	public InventoryReport(IOTThing thing, Socket clientSocket) {
		this.thing = thing;
		this.clientSocket = clientSocket;
	}
	
	
	@Override
	public void run() {
		try(BufferedReader bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);){
			
			System.out.println("Client connected to server");
			
			Gson gson = new Gson();
			String thingJson = gson.toJson(thing, IOTThing.class);
			
			
			writer.println(thingJson);
			System.out.println("IOT thing "+ thing.getUuid() +" sent to the server");
			
//			String serverMsg = bufferReader.readLine();
//			System.out.println("Server message: " + serverMsg);

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}
