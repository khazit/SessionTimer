import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;

public class Session {

  private int id;
  private Date t_start;
  private Date t_end;
  private String message;

  public Session() {
    this.message = "";
  }

  public int getId() {
    return this.id;
  }

  public Date getStart() {
    return this.t_start;
  }

  public Date getEnd() {
    return this.t_end;
  }

  public void start() {
    this.filesInit();
    this.t_start = new Date();
  }

  public void stop() {
    this.t_end = new Date();
  }

  public void save() {
    try {
      File file = new File("/home/kh4zit/.sessiontimer/.myData");
      BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
      writer.append(this.toString());
      writer.close();
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println(e);
    }
  }

  public void end() {
    this.t_end = new Date();
  }

  public void addMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }

  public  void filesInit() {
    try {
      // Get Session id from config file
      File configFile = new File("/home/kh4zit/.sessiontimer/.data.config");
      if (configFile.isFile()) {
        FileReader fr = new FileReader(configFile);
        this.id = fr.read();
        fr.close();
      } else {
        this.id = 1;
        configFile.createNewFile();
      }
      FileWriter fw = new FileWriter(configFile);
      fw.write(this.id+1);
      fw.close();

      // Check if data file exists
      File dataFile = new File("/home/kh4zit/.sessiontimer/.myData");
      if (!dataFile.isFile()) {
        dataFile.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile, true));
        writer.append("id, start, end, message\n");
        writer.close();
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println(e);
    }
  }

  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append(this.getId());
    str.append(", ");
    str.append(this.getStart().getTime());
    str.append(", ");
    str.append(this.getEnd().getTime());
    str.append(", ");
    str.append(this.getMessage());
    str.append("\n");
    return str.toString();
  }
}
