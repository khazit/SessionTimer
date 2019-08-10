import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Ui extends JPanel {

  private boolean inProgress;
  private Session sess;
  private JLabel stopWatch;
  private JLabel sessionNumber;
  private long lastTickTime;
  private Timer timer;

  public Ui() {
    inProgress = false;

    sessionNumber = new JLabel("Session no.    ");

    stopWatch = new JLabel(String.format("%02d:%02d:%02d", 0, 0, 0));
    timer = new Timer(100, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            long runningTime = System.currentTimeMillis() - lastTickTime;
            Duration duration = Duration.ofMillis(runningTime);
            long hours = duration.toHours();
            duration = duration.minusHours(hours);
            long minutes = duration.toMinutes();
            duration = duration.minusMinutes(minutes);
            long millis = duration.toMillis();
            long seconds = millis / 1000;
            stopWatch.setText(
              String.format("%04d:%02d:%02d", hours, minutes, seconds)
            );
        }
    });

    JButton startButton = new JButton("Start");
    JButton stopButton = new JButton("Stop");
    startButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (!inProgress) {
          sess = new Session();
          sess.start();
          sessionNumber.setText(String.format("Session no. %d", sess.getId()));
          System.out.print("Session started... ");
          lastTickTime = System.currentTimeMillis();
          timer.start();
          inProgress = true;
        }
      }
    });
    stopButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (inProgress) {
          System.out.println("Session stopped");
          sess.stop();
          timer.stop();
          sess.save();
          inProgress = false;
          System.out.print(sess);
          sess = new Session();
        }
      }
    });

    this.add(sessionNumber);
    this.add(startButton);
    this.add(stopButton);
    this.add(stopWatch);
  }
}
