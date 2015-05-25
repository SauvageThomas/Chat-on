package IHM;
import java.awt.*;
import java.awt.image.*;

import javax.imageio.*;

import java.io.*;

import javax.swing.*;
 

public class JavaTextPaneWithBackgroundImage extends CustomJTextPane{
  private final BufferedImage bufferedImage;
  private final TexturePaint texturePaint;
 
  public JavaTextPaneWithBackgroundImage(File file) throws IOException
  {
    super();
    bufferedImage = ImageIO.read(file);
    Rectangle rect = new Rectangle(0, 0, bufferedImage.getWidth(null), bufferedImage.getHeight(null));
    texturePaint = new TexturePaint(bufferedImage, rect);
    setOpaque(false);
  }
 
 
  public void paintComponent(Graphics g)
  {
    Graphics2D g2 = (Graphics2D) g;
    g2.setPaint(texturePaint);
    g.fillRect(0, 0, getWidth(), getHeight());
    super.paintComponent(g);
  }
}