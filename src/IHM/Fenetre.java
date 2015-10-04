package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import chat.Emission;
import chat.Message;
import chat.SeConnecter;

//A Modifier : -passer un tableau de String en param pour contacts
public class Fenetre extends JFrame implements ActionListener, KeyListener {
	private JMenuBar menus;
	private JMenu file, help;
	private JMenuItem connexion, quitter;
	private JPanel zoneSaisie, zoneContacts, zoneChat;
	private JLabel labelContacts;
	private JButton envoyer;
	private JTextArea zoneTexteMessage, zoneTexteChat;
	// private JavaTextAreaWithBackgroundImage zoneTexte;
	private SeConnecter fenetre_connexion;
	private Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit()
			.getScreenSize();
	private int hauteur = (int) tailleEcran.getHeight();
	private int largeur = (int) tailleEcran.getWidth();
	private static String ip = "";
	private int port = 2009;
	private Boolean isRunning = true;
	private String texteSaisie = "";
	private Emission emission;
	private ImageIcon apple;
	private JavaTextPaneWithBackgroundImage pane;
	private CustomJTextPane contactPane;
	private String login;

	public Fenetre(String titre) {
		super(titre);
		// this.setSize(500, 500);
		try {
			this.creerZones();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.creerMenu();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocation(largeur / 4, hauteur / 12);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
	}

	// Initialise et d�finit la taille des composants
	public void creerZones() throws IOException {

		// zone de contacts
		zoneContacts = new JPanel();
		// labelContacts = new JLabel("Liste des contacts");
		// labelContacts.setFont(new Font("Comic sans ms", Font.BOLD, 18));
		// labelContacts.setForeground(Color.MAGENTA);

		// zoneContacts.setPreferredSize(new Dimension(230, 500));
		contactPane = new CustomJTextPane();
		contactPane.setPreferredSize(new Dimension(230, 500));
		// zoneContacts.setBackground(Color.red);
		// zoneContacts.setBorder(BorderFactory.createLineBorder(Color.black,
		// 1));
		contactPane.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		// zoneContacts.setLayout(new GridLayout());

		// zoneContacts.add(labelContacts);
		// contactPane.add(labelContacts);
		contactPane.init();
		contactPane.appendToPane("\n", Color.MAGENTA, "Comic sans ms",
				Font.BOLD, 18);
		// this.add(zoneContacts, BorderLayout.WEST);
		this.add(new JScrollPane(contactPane), BorderLayout.WEST);

		// zone de chat
		zoneChat = new JPanel();
		apple = new ImageIcon();

		BoxLayout box = new BoxLayout(zoneChat, BoxLayout.PAGE_AXIS);

		zoneChat.setLayout(box);

		pane = new JavaTextPaneWithBackgroundImage(new File("apple.png"));
		// zoneTexte = new JavaTextAreaWithBackgroundImage(new
		// File("apple.png"));
		// zoneTexte.setLineWrap(true);
		// zoneTexte.setEditable(false);

		zoneChat.setPreferredSize(new Dimension(680, 680));
		// zoneChat.setBackground(Color.blue);
		zoneChat.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		// zoneChat.setBorder();

		// zoneChat.add(zoneTexte);
		pane.setEditable(false);
		zoneChat.add(new JScrollPane(pane));
		this.add(zoneChat, BorderLayout.CENTER);

		// Composants de la zone de saisie
		zoneSaisie = new JPanel();
		envoyer = new JButton("Send");
		zoneTexteMessage = new JTextArea();

		zoneSaisie.setPreferredSize(new Dimension(680, 180));
		// zoneSaisie.setBackground(Color.white);

		zoneTexteMessage.setPreferredSize(new Dimension(800, 150));
		zoneTexteMessage.setBackground(Color.pink);
		zoneTexteMessage.setLineWrap(true);
		zoneTexteMessage.addKeyListener(this);
		zoneTexteMessage.setMargin(new Insets(5, 5, 5, 5));

		// zoneTexteMessage.setBackground(Color.YELLOW);
		envoyer.setPreferredSize(new Dimension(100, 170));
		envoyer.setBackground(new Color(59, 89, 182));
		envoyer.setForeground(Color.WHITE);
		envoyer.setFocusPainted(false);
		envoyer.setFont(new Font("Tahoma", Font.BOLD, 18));
		// envoyer.setBorder(BorderFactory.createLineBorder(Color.black,1,true));
		zoneSaisie.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		/*
		 * zoneTexteMessage.setBorder(BorderFactory.createLineBorder(Color.black,
		 * 1));
		 */

		zoneSaisie.add(zoneTexteMessage);
		zoneSaisie.add(envoyer);

		this.add(zoneSaisie, BorderLayout.SOUTH);

	}

	// Initialise la barre de menu
	public void creerMenu() {

		menus = new JMenuBar();
		file = new JMenu("File");
		help = new JMenu("Help");
		connexion = new JMenuItem("Se connecter");
		quitter = new JMenuItem("Quitter");

		quitter.addActionListener(this);
		connexion.addActionListener(this);
		envoyer.addActionListener(this);

		file.add(connexion);
		menus.add(file);
		menus.add(help);
		file.add(quitter);

		this.setJMenuBar(menus);
	}

	public String getTexte() {
		return texteSaisie;
	}

	public void setText(String texte) {
		pane.setEditable(true);
		/*
		 * JTextField tempField = new JTextField(texte) ;
		 * //tempField.setBackground(Color.red);
		 * 
		 * tempField.
		 * 
		 * JPanel tempPanel = new JPanel() ; tempPanel.setBackground(Color.red);
		 * 
		 * tempPanel.add(tempField);
		 * 
		 * 
		 * //zoneTexte.setText(texte+"\n");
		 */
		// zoneTexte.setText(zoneTexte.getText() + "\n" + texte);

		Message msg = new Message(texte);
		// msg.parser();
		if (msg.isContact()) {
			this.setContact(msg.getMsg());
		} else if (msg.removeContact()) {
			this.removeContact(msg.getMsg());
		} else {
			pane.appendToPane(msg.getMsg() + "\n", msg.getColor(), null, 0, 13);
			pane.setEditable(false);
		}
	}

	public void setContact(String contact) {

		// JLabel label = new JLabel(contact);
		// zoneContacts.add(label);
		contactPane.setEditable(true);
		contactPane.appendToPane(contact + "\n", Color.BLACK, null, 0, 13);
		contactPane.setEditable(false);

	}

	public void removeContact(String contact) {

		System.out.println("Je dois supprimer !");
		contactPane.setEditable(true);
		System.out.println(contactPane.getText());
		
		String msg = new String();
		boolean passed = false;
		for (String cursor : contactPane.getText().split("\n\r")[1].split("\r\n")) {

			System.out.println("Iterate");

			if (cursor.equals(contact) && !passed) {
				passed = true;
				continue;
			}

			System.out.println(cursor + "/" + contact);
			msg += cursor + "\n";
		}
		contactPane.set(msg, Color.BLACK, null, 0, 13);
		contactPane.setEditable(false);

	}

	public String getLogin() {
		return this.login;
	}

	public String getIp() {
		return this.ip;
	}

	public void setLogin(String log) {
		this.login = log;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void waiting() {

		Thread t = new Thread();
		while (isRunning) {

			try {
				t.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void kill() {
		isRunning = false;
	}

	public void setEmission(Emission em) {
		emission = em;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		/*
		 * if (e.getSource() == connexion) { fenetre_connexion = new
		 * SeConnecter();
		 * 
		 * }
		 */

		if (e.getSource() == quitter) {
			System.exit(0);
		}

		if (e.getSource() == envoyer) {
			// zoneTexte.setEditable(true);
			pane.setEditable(true);

			send(zoneTexteMessage.getText());

			emission.send(texteSaisie);
			zoneTexteMessage.setText(null);
			// zoneTexte.setEditable(false);
			pane.setEditable(false);

		}

	}

	public void send(String str) {

		if (str.isEmpty()) {
			str = " ";
		}
		str.replaceAll("\n", "");
		if (str != null && str.charAt(0) == '\\') {
			this.setText("D�sol�, votre message ne peut pas commencer par le symbole \"\\\".");
		} else {
			texteSaisie = login + " : " + str;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// zoneTexte.setEditable(true);
			pane.setEditable(true);

			System.out.println("Vous avez tap� <" + zoneTexteMessage.getText()
					+ ">");

			send(zoneTexteMessage.getText());

			texteSaisie = texteSaisie.replace("\n", "");
			emission.send(texteSaisie);

			zoneTexteMessage.setColumns(0);
			zoneTexteMessage.setRows(0);
			e.consume();
			zoneTexteMessage.setText(null);

			pane.setEditable(false);
			// zoneTexte.setEditable(false);
		}
	}

	public static void main(String[] args) {
		Fenetre fen = new Fenetre("Chat'On");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	/*
	 * private void appendToPane(JTextPane tp, String msg, Color c) {
	 * StyleContext sc = StyleContext.getDefaultStyleContext(); AttributeSet
	 * aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
	 * StyleConstants.Foreground, c);
	 * 
	 * aset = sc.addAttribute(aset, StyleConstants.FontFamily,
	 * "Lucida Console"); aset = sc.addAttribute(aset, StyleConstants.Alignment,
	 * StyleConstants.ALIGN_JUSTIFIED);
	 * 
	 * int len = tp.getDocument().getLength(); tp.setCaretPosition(len);
	 * tp.setCharacterAttributes(aset, false); tp.replaceSelection(msg); }
	 */
}
