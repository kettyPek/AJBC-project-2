package ajbc.iot_project.networking;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MultiThreadedServerRunner implements ServletContextListener {

	private final int PORT = 9090;
	InventoryServerThread server;

	public void contextInitialized(ServletContextEvent event) {

		server = new InventoryServerThread(PORT);
		server.start();

	}

	public void contextDestroyed(ServletContextEvent event) {
		server.kill();
	}
}
