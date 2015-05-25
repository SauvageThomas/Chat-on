import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Serveur {

	public static void main(String[] zero) {

		ServerSocket socket;
		try {
			socket = new ServerSocket(2009);
			Thread t = new Thread(new Accepter_clients(socket));
			t.start();
			System.out.println("Le serveur est � l'�coute du port 2009 ... ");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}

class Accepter_clients implements Runnable {

	private ServerSocket socketserver;
	private Socket socket;
	private int nbrclient = 1;

	BufferedReader in;
	PrintWriter out;

	public Accepter_clients(ServerSocket s) {
		socketserver = s;
	}

	public void run() {

		try {
			while (true) {
				socket = socketserver.accept(); // Un client se connecte on
												// l'accepte
				System.out.println("Un z�ro veut se connecter");

				PrintWriter out = new PrintWriter(socket.getOutputStream());

				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));

				// out.println("Wsh, t'es qui ?");
				// out.flush();
				String login = in.readLine();
				System.out.println(login);
				
				// System.out.println(login);

				// out.println("Et ton code c'est oik ?");
				// out.flush();

				//String pass = in.readLine();

				// out.println("Bon, �a va pour cette fois !\n connexion au chat en cours ...");
				// out.flush();

				Thread t = new Thread(new chat_serveur(socket));
				t.start();

				nbrclient++;

				// socket.close();

			}

			// socketserver.close() ;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
