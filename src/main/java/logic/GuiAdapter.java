package logic;


import java.io.IOException;

import javax.swing.JTextArea;

import easyClientSocket.EasyClientSocket;
import easyClientSocket.EasyClientSocketMessageReceiver;
import textAreaWriter.TextAreaWriter;


public class GuiAdapter {
	private static TextAreaWriter textAreaWriter;
	private static EasyClientSocket socket;
	
	
	private static EasyClientSocketMessageReceiver receiver = new EasyClientSocketMessageReceiver() {
		
		@Override
		public void onMessageReceived(String message) {
			if(message == null) {
				closeSocket();
				return;
			}
			
			textAreaWriter.write("RECV : ");
			textAreaWriter.writeLine(message);
		}
	};
	
	
	public static void init(JTextArea textArea) {
		textAreaWriter = new TextAreaWriter(textArea);
	}

	
	public static void openSocket(String address, int port) {
		try {

			socket = new EasyClientSocket(address, port, receiver);
			textAreaWriter.writeLine("SOCKET CONNECTED TO " + socket.getRemoteAddress().getAddress() + ":" + socket.getRemoteAddress().getPort());
			textAreaWriter.writeLine("SOCKET LOCAL ADDRESS: " + socket.getLocalAddress().getAddress() + ":" + socket.getLocalAddress().getPort());
		
		} catch (IOException e) {
			
			textAreaWriter.writeLine("SOCKET COULD NOT BE OPENED");
			e.printStackTrace();
		}
	}
	
	
	public static void send(String message) {
		textAreaWriter.write("SEND: ");
		textAreaWriter.writeLine(message);

		socket.send(message);
	}

	
	public static void closeSocket() {
		try {
			
			if(socket == null) {
				textAreaWriter.writeLine("SOCKET COULD NOT BE CLOSED");
				return;
			}

			socket.close();
			textAreaWriter.writeLine("SOCKET CLOSED");

		} catch (IOException e) {
			
			textAreaWriter.writeLine("SOCKET COULD NOT BE CLOSED");
			e.printStackTrace();
		}
	}
	
	
	public static void clearTextArea() {
		textAreaWriter.clear();
	}

}
