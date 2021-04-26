package againlotto.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IO {
  private final Scanner scanner;
  private final PrintStream printStream;

  public IO(InputStream inputStream, OutputStream outputStream) {
    this.scanner = new Scanner(inputStream);
    this.printStream = new PrintStream(outputStream);
  }

  public int nextInt() {
    return scanner.nextInt();
  }

  public void println(String value) {
    printStream.println(value);
  }

  public void print(String value) {
    printStream.print(value);
  }
}
