import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ui extends JPanel implements ActionListener {

  boolean inProgress;
  Session sess;

  public Ui() {
    inProgress = false;
    sess = new Session();

    JButton startButton = new JButton("Start");
    JButton stopButton = new JButton("Stop");
    startButton.addActionListener(this);
    stopButton.addActionListener(this);
    this.add(startButton);
    this.add(stopButton);
  }

  public void actionPerformed(ActionEvent e) {
    /*
      this.getComponent() is ugly and not stable if I add
      more components to my UI
    */
    if(e.getSource() == this.getComponent(0) && !inProgress) {
      sess.start();
      System.out.print("Session started... ");
      this.inProgress = true;
    }
    else if (e.getSource() == this.getComponent(1) && inProgress) {
      System.out.println("Session stopped");
      sess.stop();
      sess.save();
      this.inProgress = false;
      System.out.print(sess);
      this.sess = new Session();
    }
  }

}
