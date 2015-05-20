import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Emission implements Runnable {

	private PrintWriter out;
	private Fenetre fen;
	private String login = null, message = null;
	private Scanner sc = null;
	
	public Emission(PrintWriter out, Fenetre fen) {
		this.out = out;
		this.fen = fen;
		
	}

	
	public void run() {
		
		  sc = new Scanner(System.in);
		  
		  while(true){
				message = fen.getTexte();
				out.println(message);
				fen.setText(message);
			    out.flush();
			  }
	}
	
	public void send(String str) {
		out.println(str);
		System.out.println("J'envois");
		fen.setText(str);
	    out.flush();
	}
}