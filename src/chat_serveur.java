import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class chat_serveur implements Runnable {

	private Socket socket;

	private BufferedReader in;
	private PrintWriter out;
	private String msg;
	private Affichage task ;
	
	boolean connected = true;

	public chat_serveur(Socket s) {
		socket = s;
	}

	public void run() {

		try {

			PrintWriter out = new PrintWriter(socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			Scanner sc = new Scanner(System.in);
			
			task = new Affichage(in) ;
			
			Thread t = new Thread(task);
			t.start();

			System.out.println("On va rentrer dans la boucle de chat");
			
			Thread thread = new Thread() ;
			try {
				thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println("Thomas");
			
			out.println("\\SYSCODE\\Connecté au serveur.");
			out.flush() ;
			while (connected) {
				
				msg = sc.nextLine();

				if (msg.equals("quit")) {
					System.out.println("Déconnection du serveur en cours");
					out.println("Le serveur se déconnecte");
					out.flush();
					connected = false;
				} else {
					out.println("\\LOGCODE:SERVEUR\\" + msg);
					out.flush();
				}

			}
			
			socket.close();
			task.kill();
			
			
			System.out.println("Vous êtes déconnecté");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}

class Affichage implements Runnable {
	private BufferedReader in;
	private String msg;
	private boolean isRunning = true ;

	public Affichage(BufferedReader arg1) {
		in = arg1;

	}

	public void run() {

		System.out.println("Boucle d'affichage");

		while (isRunning) {

			try {

				msg = in.readLine();
				if (msg != null) {
					System.out.println("[Client] : " + msg);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void kill () {
		isRunning = false ;
	}
}
