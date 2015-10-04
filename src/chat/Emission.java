package chat;

import java.io.PrintWriter;

import IHM.Fenetre;

public class Emission {

	private PrintWriter out;
	private Fenetre fen;

	public Emission(PrintWriter out, Fenetre fen) {
		this.out = out;
		this.fen = fen;

	}

	public void send(String str) {
		out.println(str);
		fen.setText(str);
		out.flush();
	}
}