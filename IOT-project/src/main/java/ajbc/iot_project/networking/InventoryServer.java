package ajbc.iot_project.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import ajbc.iot_project.networking.threads.InventoryServerRunnable;

public class InventoryServer {

	private final int PORT;

	public InventoryServer(int port) {
		this.PORT = port;
	}

	public void startServer() throws InterruptedException {

		ExecutorService executorService = Executors.newCachedThreadPool();

		try (ServerSocket serverSocket = new ServerSocket(PORT);) {

			System.out.println("App Server started on port " + PORT);

			while (true) {
				Socket clientSocket = serverSocket.accept();
				executorService.execute(new InventoryServerRunnable(clientSocket));
			}
		} catch (IOException e) {
			System.err.println("Failed to start server on port " + PORT);
			e.printStackTrace();
		} finally {
			executorService.shutdown();
			executorService.awaitTermination(2, TimeUnit.SECONDS);
		}

	}

	public static void main(String[] args) throws InterruptedException {

		int port = 8098;

		InventoryServer inventoryServer = new InventoryServer(port);
		
		inventoryServer.startServer();

	}

}
