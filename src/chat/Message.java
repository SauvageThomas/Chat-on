package chat;
import java.awt.Color;

public class Message {
	private String msg;
	private Color color;
	private final String codeSystem = "\\SYSCODE\\";

	public Message(String msg) {
		this.msg = msg;
		this.color = Color.black;
		this.parser();
	}

	public String getMsg() {
		return msg;
	}

	public Color getColor() {
		return color;
	}

	public void parser() {

		String[] tab = msg.split("\\\\");

		// str = codeSystem + "bla";

		switch (tab.length) {
		case 2:

			break;

		case 3:
			String start = tab[1];
			if (start.equals("SYSCODE")) {
				msg = "[SERVEUR] : " + tab[2];
				color = Color.red;
			}
			if (start.split(":").length != 1) {
				tab = start.split(":");
				if (tab[0].equals("LOGCODE")) {
					msg = tab[1] + " : " + msg.split("\\\\")[2];

				}

			}

			break;
		default:

			break;
		}
	}
}
