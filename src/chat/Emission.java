package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import IHM.Fenetre;

public class Emission implements Runnable {

	private PrintWriter out;
	private Fenetre fen;
	private String login = null, message = null;
	private Scanner sc = null;

	public Emission(PrintWriter out, Fenetre fen2) {
		this.out = out;
		this.fen = fen2;

	}

	public void run() {

		sc = new Scanner(System.in);

		while (true) {
			message = fen.getTexte();

			out.println(message);
			fen.setText(message);
			out.flush();
		}
	}

	public void send(String str) {
		out.println(str);
		fen.setText(str);
		out.flush();
	}
}