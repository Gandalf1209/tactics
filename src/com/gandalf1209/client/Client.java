package com.gandalf1209.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.gandalf1209.game.MainGame;

public class Client extends Thread {

	private static InetAddress ip;
	private static int port;
	private static DatagramSocket socket;
	private static Client client;
	
	public static void main(String[] args) {
		try {
			System.out.println("Loading...");
			client = new Client();
			ip = InetAddress.getByName("localhost");
			port = 12098;
			socket = new DatagramSocket();
			client.start();
			sendData("/c/");
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
					if (args[1].equals("OK")) {
						System.out.println("Connected!");
						new MainGame().init();
					} else {
						System.out.println("Error: " + args[1]);
						System.exit(1);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void sendData(String data) {
		try {
			DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length, ip, port);
			socket.send(packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}