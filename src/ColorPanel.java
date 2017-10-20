import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class ColorPanel extends JPanel
{
  private Color color;
  public ColorPanel(Color color)
  {
    this.color = color;
  }
  public ColorPanel()
  {
    this.color = Color.WHITE;
  }
  public void setColor(Color c)
  {
    color = c;
  }
  public Color getColor()
  {
    return color;
  }
  @Override
  public void paintComponent(Graphics g)
  {
    g.setColor(color);
    g.fillRect(0,0,getWidth(),getHeight());
  }
}