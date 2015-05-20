import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class SeConnecter extends JFrame implements ActionListener {
	
	private JTextField ip, login;
	JButton connexion;
	Boolean isRunning = true ;

	

	public SeConnecter(){
		super("Connexion");
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setLocation(700,400);
		this.creerChamps();
		this.pack();
		this.setVisible(true);
		
		
		
		//this.setResizable(false);
	}
	
	public void creerChamps(){
		login = new JTextField("Login");
		ip = new JTextField("Adresse IPv4");
		connexion = new JButton("Connexion");
		
		login.setPreferredSize(new Dimension(100,20));
		
		this.add(login);
		this.add(ip);
		this.add(connexion);
		
		connexion.addActionListener(this);
	}
	
	public String getLogin(){
		return this.login.getText();
	}
	
	public String getIp(){
		return this.ip.getText();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==this.connexion){			
			this.setVisible(false);
			this.kill() ;
		}
		
		
		
	}
	
	public void waiting() {
		
		Thread t = new Thread() ;
		while (isRunning) {
			
			try {
				t.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void kill() {
		isRunning = false ;
	}
	
	
	/*public static void main(String[] args) {
		SeConnecter fen = new SeConnecter();
	}*/
}
