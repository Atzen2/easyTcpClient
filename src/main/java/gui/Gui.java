package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import logic.GuiAdapter;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Gui {

	private JFrame frmSimpleTcpClient;
	private JTextField txtAddress;
	private JTextField txtPort;
	private JTextField txtMessage;
	private JButton btnOpenSocket;
	private JButton btnSend;
	private JTextArea textArea;
	private JButton btnClear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frmSimpleTcpClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSimpleTcpClient = new JFrame();
		frmSimpleTcpClient.setTitle("Easy TCP Client v0.1");
		frmSimpleTcpClient.setBounds(100, 100, 481, 498);
		frmSimpleTcpClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmSimpleTcpClient.getContentPane().setLayout(springLayout);
		
		JLabel lblAddress = new JLabel("Address");
		springLayout.putConstraint(SpringLayout.NORTH, lblAddress, 10, SpringLayout.NORTH, frmSimpleTcpClient.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblAddress, 10, SpringLayout.WEST, frmSimpleTcpClient.getContentPane());
		frmSimpleTcpClient.getContentPane().add(lblAddress);
		
		txtAddress = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, txtAddress, 155, SpringLayout.EAST, lblAddress);
		txtAddress.setText("localhost");
		springLayout.putConstraint(SpringLayout.NORTH, txtAddress, 0, SpringLayout.NORTH, lblAddress);
		springLayout.putConstraint(SpringLayout.WEST, txtAddress, 8, SpringLayout.EAST, lblAddress);
		frmSimpleTcpClient.getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		
		JLabel lblPort = new JLabel("Port");
		springLayout.putConstraint(SpringLayout.NORTH, lblPort, 11, SpringLayout.SOUTH, lblAddress);
		springLayout.putConstraint(SpringLayout.WEST, lblPort, 0, SpringLayout.WEST, lblAddress);
		frmSimpleTcpClient.getContentPane().add(lblPort);
		
		txtPort = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtPort, 6, SpringLayout.SOUTH, txtAddress);
		springLayout.putConstraint(SpringLayout.WEST, txtPort, 36, SpringLayout.EAST, lblPort);
		springLayout.putConstraint(SpringLayout.EAST, txtPort, 0, SpringLayout.EAST, txtAddress);
		txtPort.setText("80");
		frmSimpleTcpClient.getContentPane().add(txtPort);
		txtPort.setColumns(10);
		
		btnOpenSocket = new JButton("open Socket");
		springLayout.putConstraint(SpringLayout.NORTH, btnOpenSocket, -5, SpringLayout.NORTH, lblAddress);
		springLayout.putConstraint(SpringLayout.EAST, btnOpenSocket, -10, SpringLayout.EAST, frmSimpleTcpClient.getContentPane());
		btnOpenSocket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sport = txtPort.getText();
				int port = Integer.parseInt(sport);
				GuiAdapter.openSocket(txtAddress.getText(), port);
			}
		});
		frmSimpleTcpClient.getContentPane().add(btnOpenSocket);
		
		JLabel lblMessage = new JLabel("Message");
		springLayout.putConstraint(SpringLayout.NORTH, lblMessage, 44, SpringLayout.SOUTH, lblPort);
		springLayout.putConstraint(SpringLayout.WEST, lblMessage, 0, SpringLayout.WEST, lblAddress);
		frmSimpleTcpClient.getContentPane().add(lblMessage);
		
		txtMessage = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtMessage, 2, SpringLayout.EAST, lblMessage);
		txtMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiAdapter.send(txtMessage.getText());
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, txtMessage, -2, SpringLayout.NORTH, lblMessage);
		txtMessage.setText("hello");
		frmSimpleTcpClient.getContentPane().add(txtMessage);
		txtMessage.setColumns(10);
		
		btnSend = new JButton("send");
		springLayout.putConstraint(SpringLayout.EAST, txtMessage, -6, SpringLayout.WEST, btnSend);
		springLayout.putConstraint(SpringLayout.NORTH, btnSend, 61, SpringLayout.SOUTH, btnOpenSocket);
		springLayout.putConstraint(SpringLayout.EAST, btnSend, -10, SpringLayout.EAST, frmSimpleTcpClient.getContentPane());
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiAdapter.send(txtMessage.getText());
			}
		});
		frmSimpleTcpClient.getContentPane().add(btnSend);
		
		JButton btnCloseSocket = new JButton("close Socket");
		btnCloseSocket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiAdapter.closeSocket();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnCloseSocket, 0, SpringLayout.NORTH, lblPort);
		springLayout.putConstraint(SpringLayout.EAST, btnCloseSocket, 0, SpringLayout.EAST, btnOpenSocket);
		frmSimpleTcpClient.getContentPane().add(btnCloseSocket);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 28, SpringLayout.SOUTH, btnSend);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, frmSimpleTcpClient.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, btnOpenSocket);
		frmSimpleTcpClient.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		GuiAdapter.init(textArea);
		
		btnClear = new JButton("clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiAdapter.clearTextArea();
			}
		});
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -6, SpringLayout.NORTH, btnClear);
		springLayout.putConstraint(SpringLayout.SOUTH, btnClear, -10, SpringLayout.SOUTH, frmSimpleTcpClient.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnClear, 0, SpringLayout.EAST, btnOpenSocket);
		frmSimpleTcpClient.getContentPane().add(btnClear);
	}
}
