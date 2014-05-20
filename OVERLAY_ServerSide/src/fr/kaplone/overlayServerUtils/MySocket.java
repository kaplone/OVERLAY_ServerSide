package fr.kaplone.overlayServerUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MySocket {
	
    private Socket conn = null;
    //private PrintStream outStream = null;
    private BufferedReader inStream = null;
    
	public MySocket(ServerSocket sv) throws IOException {
		
		 
         System.out.println("Server socket created.Waiting for connection...");
         this.conn = sv.accept();
         System.out.println("Connection received from " + conn.getInetAddress().getHostName() + " : " + conn.getPort());  
         //this.outStream = new PrintStream(conn.getOutputStream());
         //this.outStream.flush();
         this.inStream = new BufferedReader(new InputStreamReader(conn.getInputStream()));

	}

	public Socket getConn() {
		return conn;
	}

//	public PrintStream getOutStream() {
//		return outStream;
//	}

	public BufferedReader getInStream() {
		return inStream;
	}
	

}
