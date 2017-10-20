import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public class ColorCalculator extends JFrame implements Runnable
{
  private int red = 127;
  private int green = 127;
  private int blue = 127;
  private Color color = mixColor(red, green, blue);
  private ColorPanel cp;
  private JSlider redSlider;
  private JSlider greenSlider;
  private JSlider blueSlider;
  private JLabel redLabel;
  private JLabel greenLabel;
  private JLabel blueLabel;
  private JLabel hexLabel;
  private String hex = String.format("0x%02x%02x%02x", red, green, blue);
  private JTextField textField;
  public ColorCalculator()
  {
    cp = new ColorPanel(color);
  }
  private void addComponents()
  {
    redSlider = new JSlider(0,255);
    redSlider.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent e)
      {
        {
          red = redSlider.getValue();
          hex = String.format("0x%02x%02x%02x", red, green, blue);
          textField.setText(hex);
          color = mixColor(red, green, blue);
          cp.setColor(color);
          cp.repaint();
        }
      }
    });    
    greenSlider = new JSlider(0,255);
    greenSlider.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent e)
      {
        green = greenSlider.getValue();
        hex = String.format("0x%02x%02x%02x", red, green, blue);
        textField.setText(hex);
        color = mixColor(red, green, blue);
        cp.setColor(color);
        cp.repaint();
      }
    });
    blueSlider = new JSlider(0,255);
    blueSlider.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent e)
      {
        blue = blueSlider.getValue();
        hex = String.format("0x%02x%02x%02x", red, green, blue);
        textField.setText(hex);
        color = mixColor(red, green, blue);
        cp.setColor(color);
        cp.repaint();
      }
    });
    redLabel = new JLabel("<html><font color='red'>Red</font></html>");
    greenLabel = new JLabel("<html><font color='green'>Green</font></html>");
    blueLabel = new JLabel("<html><font color='blue'>Blue</font></html>");
    hexLabel = new JLabel("Hex Code");
    getContentPane().add(BorderLayout.CENTER, cp);
    JPanel controlPanel = new JPanel();
    getContentPane().add(BorderLayout.SOUTH, controlPanel);
    controlPanel.setLayout(new GridLayout(2,4));
    controlPanel.add(redLabel);
    redLabel.setHorizontalAlignment(JLabel.CENTER);
    greenLabel.setHorizontalAlignment(JLabel.CENTER);
    blueLabel.setHorizontalAlignment(JLabel.CENTER);
    hexLabel.setHorizontalAlignment(JLabel.CENTER);
    controlPanel.add(greenLabel);
    controlPanel.add(blueLabel);
    controlPanel.add(hexLabel);
    controlPanel.add(redSlider);
    processSliders(redSlider);
    controlPanel.add(greenSlider);
    processSliders(greenSlider);
    controlPanel.add(blueSlider);
    processSliders(blueSlider);
    textField = new JTextField(hex);
    textField.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        if(textField.getText().matches("[0-9A-f]{6}+"))
        {
          color = new Color((int)Long.parseLong(textField.getText(), 16));
          cp.setColor(color);
          cp.repaint();
          red = color.getRed();
          green = color.getGreen();
          blue = color.getBlue();
          hex = String.format("0x%02x%02x%02x", red, green, blue);
          redSlider.setValue(red);
          greenSlider.setValue(green);
          blueSlider.setValue(blue);
        }
        else if(textField.getText().matches("0x[0-9A-f]{6}+"))
        {
          String temp = textField.getText().substring(2);
          color = new Color((int)Long.parseLong(temp, 16));
          cp.setColor(color);
          cp.repaint();
          red = color.getRed();
          green = color.getGreen();
          blue = color.getBlue();
          hex = String.format("0x%02x%02x%02x", red, green, blue);
          redSlider.setValue(red);
          greenSlider.setValue(green);
          blueSlider.setValue(blue);
        }
        else
        {
          textField.setText("ERROR");
          System.out.println("ERROR");
        }
      }
    });
    textField.setFont(new Font("Arial", Font.BOLD, 20));
    textField.setAlignmentX(JTextField.CENTER_ALIGNMENT);
    textField.setAlignmentY(JTextField.CENTER_ALIGNMENT);
    controlPanel.add(textField);
  }
  private void makeMenus()
  {
    JMenuBar mbar = new JMenuBar();
    setJMenuBar(mbar);
    JMenu fileMenu = new JMenu("File");
    mbar.add(fileMenu);
    JMenuItem quit = new JMenuItem("Quit");
    fileMenu.add(quit);
    quit.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        System.exit(0);
      }
    });
  }
  private void processSliders(JSlider slider)
  {
    slider.setMajorTickSpacing(64);
    slider.setMinorTickSpacing(1);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
  }
  private static Color mixColor(int red, int green, int blue)
  {
    return new Color(blue + green*0x100 + red*0x10000);
  }
  public void run()
  {
    setSize(500,500);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("Color Calculator");
    makeMenus();
    addComponents();
    getContentPane().add(cp); 
    setVisible(true);
  }
  public static void main(String[] args)
  {
    ColorCalculator cc = new ColorCalculator();
    javax.swing.SwingUtilities.invokeLater(cc);
  }
}
