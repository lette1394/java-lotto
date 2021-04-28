package againlotto.view;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

public class User {
  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  public void enter() {
    write("\n");
  }

  public void enterPurchaseAmount(int amount) {
    write(amount);
    enter();
  }

  public void enterLastWeekWinningNumber(int... winningNumbers) {
    write(Arrays.stream(winningNumbers)
      .mapToObj(String::valueOf)
      .collect(Collectors.joining(", ")));
    enter();
  }

  public InputStream toInputStream() {
    return new ByteArrayInputStream(outputStream.toString().getBytes(StandardCharsets.UTF_8));
  }

  private void write(int value) {
    write(Integer.toString(value));
  }

  private void write(String value) {
    try {
      outputStream.write(value.getBytes(StandardCharsets.UTF_8));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
