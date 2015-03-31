package com.gandalf1209.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import com.gandalf1209.game.Player;
import com.gandalf1209.game.User;

public class Server extends Thread {

	private static int port;
	private static DatagramSocket socket;
	private static Server server;
	
	private static List<InetAddress> ips = new ArrayList<InetAddress>();
	private static List<Integer> ports = new ArrayList<Integer>();
	private static List<User> users = new ArrayList<User>();
	
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
					sendData("/c/~" + addConnection(packet.getAddress(), packet.getPort()), packet.getAddress(), packet.getPort());
				}
				if (args[0].equals("/login/")) {
					User u = new User(args[1]);
					u.initPlayers(4);
					users.add(u);
					sendData("/login/~4", packet.getAddress(), packet.getPort());
				}
				if (args[0].equals("/upd/")) {
					int l = getLocByName(args[1]);
					if (l != -1) {
						User u = users.get(l);
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < u.players.size(); i++) {
							Player p = u.players.get(i);
							sb.append(Player.playerToData(p));
							if (i != u.players.size() - 1) {
								sb.append("%");
							}
						}
						sendData("/upd/~" + sb.toString(), packet.getAddress(), packet.getPort());
					} else {
						sendData("/err/~Username not found!", packet.getAddress(), packet.getPort());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static int getLocByName(String name) {
		int loc = -1;
		for (int i = 0; i < users.size(); i++) {
			User u = users.get(i);
			if (u.getName().equals(name)) {
				loc = i;
				break;
			}
		}
		return loc;
	}
	
	private static String addConnection(InetAddress ip, int port) {
		String status = "OK";
		ips.add(ip);
		ports.add(port);
		return status;
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