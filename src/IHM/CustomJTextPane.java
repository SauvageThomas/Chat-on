package IHM;

import java.awt.Color;
import java.awt.Font;
import java.util.Enumeration;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class CustomJTextPane extends JTextPane {

	public CustomJTextPane() {
		super();
	}

	public void appendToPane(String msg, Color c, String font, int style,
			int fontSize) {

		if (font == null) {
			font = ("Lucida Console");
		}

		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY,
				StyleConstants.Foreground, c);

		aset = sc.addAttribute(aset, StyleConstants.FontSize, fontSize);

		aset = sc.addAttribute(aset, StyleConstants.FontFamily, font);
		aset = sc.addAttribute(aset, StyleConstants.Alignment,
				StyleConstants.ALIGN_JUSTIFIED);

		int len = this.getDocument().getLength();
		this.setCaretPosition(len);
		this.setCharacterAttributes(aset, false);
		this.replaceSelection(msg);
	}

	public void set(String msg, Color c, String font, int style, int fontSize) {

		this.init();

		this.appendToPane(msg, c, font, style, fontSize);

	}

	public void init() {
		this.setText(null);
		this.appendToPane("    Utilisateurs connectés\n", Color.MAGENTA,
				"Comic sans ms", Font.BOLD, 18);
	}

}
