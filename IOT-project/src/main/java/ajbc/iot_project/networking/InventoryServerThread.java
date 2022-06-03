package ajbc.iot_project.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import ajbc.iot_project.networking.threads.InventoryServerRunnable;

public class InventoryServerThread extends Thread {

	private final int PORT;
	ExecutorService executorService;

	public InventoryServerThread(int port) {
		this.PORT = port;
		executorService = Executors.newCachedThreadPool();
	}

	@Override
	public void run() {

		try (ServerSocket serverSocket = new ServerSocket(PORT);) {

			System.out.println("App Server started on port " + PORT);

			while (true) {
				Socket clientSocket = serverSocket.accept();
				executorService.execute(new InventoryServerRunnable(clientSocket));
			}
		} catch (IOException e) {
			System.err.println("Failed to start server on port " + PORT);
			e.printStackTrace();
		}

	}

	public void kill() {

		try {
			executorService.shutdown();
			executorService.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
