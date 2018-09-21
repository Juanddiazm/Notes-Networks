package chatMultiThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread extends Thread {

	Server server = null;
	Socket client = null;
	BufferedReader cin;
	PrintStream cout;
	Scanner sc = new Scanner(System.in);
	int id;
	String s;

	ServerThread(Socket client, int count, Server server, ThreadGroup thg) throws IOException {
		super(thg, "hilo");
		this.client = client;
		this.server = server;
		this.id = count;
		System.out.println("Connection " + id + "established with client " + client);

		cin = new BufferedReader(new InputStreamReader(client.getInputStream()));
		cout = new PrintStream(client.getOutputStream());

	}

	@Override
	public void run() {
		int x = 1;
		try {
			while (true) {
				s = cin.readLine();

				System.out.print("Client(" + id + ") :" + s + "\n");
				System.out.print("Server : ");
				// s=stdin.readLine();
				s = sc.nextLine();
				if (s.equalsIgnoreCase("bye")) {
					cout.println("BYE");
					x = 0;
					System.out.println("Connection ended by server");
					break;
				}
				cout.println(s);
			}

			cin.close();
			client.close();
			cout.close();
			if (x == 0) {
				System.out.println("Server cleaning up.");
				System.exit(0);
			}
		} catch (IOException ex) {
			System.out.println("Error : " + ex);
		}

	}
}
