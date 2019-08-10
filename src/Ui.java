import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Component;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.BoxLayout;

public class Ui extends JPanel {

  private boolean inProgress;
  private Session sess;
  private JLabel stopWatch;
  private JLabel sessionNumber;
  private long lastTickTime;
  private Timer timer;

  public Ui() {
    inProgress = false;

    sessionNumber = new JLabel("Session no.");

    stopWatch = new JLabel(String.format("%02d:%02d:%02d", 0, 0, 0));
    timer = new Timer(1000, new ActionListener() {
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
              String.format("%02d:%02d:%02d", hours, minutes, seconds)
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
          sess.stop();
          timer.stop();
          sess.save();
          inProgress = false;
          sess = new Session();
        }
      }
    });

    BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
    this.setLayout(boxLayout);

    sessionNumber.setFont(new Font("Calibri", Font.PLAIN, 16));
    JPanel titlePanel = new JPanel();
    titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    titlePanel.add(sessionNumber);

    stopWatch.setFont(new Font("Calibri", Font.BOLD, 54));
    JPanel stopWatchPanel = new JPanel();
    stopWatchPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    stopWatchPanel.add(stopWatch);

    startButton.setFont(new Font("Calibri", Font.PLAIN, 12));
    startButton.setPreferredSize(new Dimension(58, 36));
    stopButton.setFont(new Font("Calibri", Font.PLAIN, 12));
    stopButton.setPreferredSize(new Dimension(58, 36));
    JPanel buttonPanel = new JPanel();
    BoxLayout buttonLayout = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
    buttonPanel.add(startButton);
    buttonPanel.add(stopButton);

    this.add(titlePanel);
    this.add(stopWatchPanel);
    this.add(buttonPanel);
  }
}
