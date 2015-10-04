package chat;

import java.io.*;
import java.net.*;

import IHM.Fenetre;

public class Client {

	public static void main(String[] zero) {

		String login = "";
		String ip = "";
		int port = 2009;
		Socket socket;

		SeConnecter connect = new SeConnecter();
		connect.waiting();

		// CEST LA QUIL FAUT METTRE L IP
		ip = "localhost";
		login = "AttiDDOS";

		login = connect.getLogin();
		// ip = connect.getIp() ;

		System.out.println(ip + " et " + login);

		Fenetre fen = new Fenetre("Chat'On");
		fen.setLogin(login);
		fen.setText("Bienvenue sur Chat'On 1.0 !");

		try {
			socket = new Socket(ip, port);

			PrintWriter out = new PrintWriter(socket.getOutputStream());

			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			out.println(login);
			out.flush();

			String contacts = in.readLine();
			
			System.out.println("mes contacts" + contacts);
			for (String cursor : contacts.split(":")) {
				fen.addContact(cursor);
			}
			
			Emission emission = new Emission(out, fen);

			fen.setEmission(emission);

			Thread t3 = new Thread(new Reception(in, fen));
			t3.start();

		} catch (IOException e) {
			fen.setText("Erreur le serveur n'est pas accessible");
			e.printStackTrace();
		}
	}
}