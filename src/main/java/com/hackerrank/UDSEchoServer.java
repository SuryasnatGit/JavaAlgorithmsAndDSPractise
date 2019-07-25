package com.hackerrank;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class UDSEchoServer {
	
	public static void main(String[] args){
		try {
			ServerSocket socket = new ServerSocket(8888, 5, InetAddress.getLocalHost());
			Socket incoming = socket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
