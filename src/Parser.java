
public class Parser {

	public static String parser(String str) {
		String codeSystem = "\\SYSCODE\\";
		String[] tab = str.split("\\\\");

		// str = codeSystem + "bla";

		switch (tab.length) {
		case 2:

			break;

		case 3:
			System.out.println(tab[1]);
			if (tab[1].equals("SYSCODE")) {
				str = "[SERVEUR] : " + tab[2];
			}
			break;
		default:
			break;
		}

		return str;
	}

	public static void main(String[] args) {
		parser("\\SYSCODE\\bla");
	}
}
