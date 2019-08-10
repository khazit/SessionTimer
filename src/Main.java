import javax.swing.JFrame;
import javax.swing.UIManager;

public class Main {

  public Main() {
  }

  public static void main(String[] args) {
    // Intro
    JFrame frame = new JFrame("SessionTimer");
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println(e);
    }
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Ui contentPane = new Ui();
    // Outro
    frame.setBounds(400, 400, 390, 240);
    frame.setContentPane(contentPane);
    frame.setVisible(true);
  }
}
