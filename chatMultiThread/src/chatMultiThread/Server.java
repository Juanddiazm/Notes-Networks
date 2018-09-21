package chatMultiThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author SHUBHAM
 */
public class Server {

	int port;
	ServerSocket server = null;
	Socket client = null;
	// ExecutorService pool = null;
	ThreadGroup pool;
	int clientcount = 0;

	public static void main(String[] args) throws IOException {
		Server serverobj = new Server(5000);
		serverobj.startServer();
	}

	Server(int port) {
		this.port = port;

		// pool = Executors.newFixedThreadPool(5);
	}

	public void startServer() throws IOException {

		server = new ServerSocket(5000);
		System.out.println("Server Booted");
		System.out.println("Any client can stop the server by sending -1");
		while (true) {
			client = server.accept();
			clientcount++;
			ServerThread runnable = new ServerThread(client, clientcount, this, pool);
			runnable.start();

		}

	}

}