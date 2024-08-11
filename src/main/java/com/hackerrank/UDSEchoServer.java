package com.hackerrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// TODO : to fix properly
public class UDSEchoServer {

	public static void main(String[] args) {

		try {
			// create socket
			int port = 4444;
			ServerSocket serverSocket = new ServerSocket(port);
			System.err.println("Started server on port " + port);

			// repeatedly wait for connections, and process
			while (true) {

				// a "blocking" call which waits until a connection is requested
				Socket clientSocket = serverSocket.accept();
				System.err.println("Accepted connection from client");

				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

				// waits for data and reads it in until connection dies
				// readLine() blocks until the server receives a new line from client
				String s;
				while ((s = stdIn.readLine()) != null) {
					out.println(s);
				}

				// close IO streams, then socket
				System.err.println("Closing connection with client");
				out.close();
				in.close();
				clientSocket.close();
				serverSocket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
