package ajbc.iot_project.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import ajbc.iot_project.DB.DBMock;
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
			
			System.out.println("Connected to server");
			
			Gson gson = new Gson();
			String thingJson = gson.toJson(thing, thing.getClass());
			
			
			writer.println(thingJson);
			System.out.println("IOT thing sent to the server");

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	


}
