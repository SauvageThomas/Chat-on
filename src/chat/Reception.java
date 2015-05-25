package chat;
import java.io.BufferedReader;
import java.io.IOException;

import IHM.Fenetre;


public class Reception implements Runnable {

	private BufferedReader in;
	private Fenetre fen;
	private String message = null;
	
	public Reception(BufferedReader in, Fenetre fen){
		
		this.in = in;
		this.fen = fen;
	}
	
	public void run() {
		
		while(true){
	        try {
	        	
			message = in.readLine();
			fen.setText(message);
			System.out.println("re�u");
			
		    } catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}

}