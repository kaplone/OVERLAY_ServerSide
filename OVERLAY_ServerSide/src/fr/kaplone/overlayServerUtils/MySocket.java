package fr.kaplone.overlayServerUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
//import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MySocket {
	
    private Socket conn;
    private PrintWriter outWriter;
    private BufferedReader inReader;
    
	public MySocket(ServerSocket sv) throws IOException {
		
         System.out.println("Server socket created.Waiting for connection...");
         this.conn = sv.accept();
         System.out.println("Connection received from " + conn.getInetAddress().getHostName() + " : " + conn.getPort());  
         this.outWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(conn.getOutputStream())), true);
         this.inReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
         System.out.println("waiting ...");
	}

	public Socket getConn() {
		return conn;
	}

	public PrintWriter getOutWriter() {
		return outWriter;
	}

	public BufferedReader getInReader() {
		return inReader;
	}
	
	public OutputStream getSocketOutputStream() throws IOException {
		return conn.getOutputStream();
	}
  
		
}
	
