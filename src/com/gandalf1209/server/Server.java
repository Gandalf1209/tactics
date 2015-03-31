package com.gandalf1209.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server extends Thread {

	private static int port;
	private static DatagramSocket socket;
	private static Server server;
	
	public static void main(String[] args) {
		try {
			System.out.println("Loading...");
			server = new Server();
			port = 12098;
			socket = new DatagramSocket(port);
			server.start();
			System.out.println("Server started on port " + port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while (true) {
			try {
				byte[] data = new byte[1024];
				DatagramPacket packet = new DatagramPacket(data, data.length);
				socket.receive(packet);
				String message = new String(data).trim();
				String[] args = message.split("~");
				if (args[0].equals("/c/")) {
					System.out.println("Connection from: " + packet.getAddress().getHostAddress() + ":" + packet.getPort());
					sendData("/c/~OK", packet.getAddress(), packet.getPort());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void sendData(String data, InetAddress ip, int port) {
		try {
			DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length, ip, port);
			socket.send(packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}