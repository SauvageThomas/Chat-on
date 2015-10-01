package chat;

import java.io.*;
import java.net.*;
import java.util.Scanner;

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
		ip = "192.168.1.96";
		login = "AttiDDOS";

		login = connect.getLogin();
		// ip = connect.getIp() ;

		System.out.println(ip + " et " + login);

		Fenetre fen = new Fenetre("Chat'On");
		fen.setLogin(login);

		try {
			socket = new Socket(ip, port);

			PrintWriter out = new PrintWriter(socket.getOutputStream());

			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			Scanner sc = new Scanner(System.in);

			// fen.setText(in.readLine());

			out.println(login);
			out.flush();

			String contacts = in.readLine();
			

			fen.setText("Bienvenue sur Chat'On 1.0 !");
			fen.setText("Je suis " + login);
			
			System.out.println("mes contacts" + contacts);
			for (String cursor : contacts.split(":")) {
				fen.setContact(cursor);
			}
			

			Emission emission = new Emission(out, fen);

			fen.setEmission(emission);

			// Thread t4 = new Thread();
			// t4.start();

			Thread t3 = new Thread(new Reception(in, fen));
			t3.start();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}