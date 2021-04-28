package againlotto.view;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IO {
  public static final IO STANDARD = new IO(System.in, System.out);

  private final Scanner scanner;
  private final PrintStream printStream;

  public IO(InputStream inputStream, OutputStream outputStream) {
    this.scanner = new Scanner(inputStream);
    this.printStream = new PrintStream(outputStream);
  }

  public int nextInt() {
    return parseInt(scanner.nextLine());
  }

  public List<Integer> nextIntList() {
    return Arrays.stream(scanner.nextLine().split(","))
      .map(String::trim)
      .map(Integer::parseInt)
      .collect(toList());
  }

  public void println() {
    printStream.println();
  }

  public void println(String value) {
    printStream.println(value);
  }

  public void print(String value) {
    printStream.print(value);
  }
}
