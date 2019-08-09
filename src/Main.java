import javax.swing.*;

public class Main {

  public Main() {
  }

  public static void main(String[] args) {
    // Intro
    JFrame frame = new JFrame("SessionTimer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Ui contentPane = new Ui();
    // Outro
    frame.setContentPane(contentPane);
    frame.pack();
    frame.setVisible(true);
  }
}
