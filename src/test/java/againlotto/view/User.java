package againlotto.view;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class User {
  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  public void enter() {
    safeCall(() -> outputStream.write("\n".getBytes(StandardCharsets.UTF_8)));
  }

  public void enterPurchaseAmount(int amount) {
    outputStream.write(amount);
    enter();
  }

  public void enterLastWeekWinningNumber(int... winningNumbers) {
    Arrays.stream(winningNumbers)
      .forEach(number -> safeCall(() -> outputStream.write(number)));
    enter();
  }

  public InputStream toInputStream() {
    return new ByteArrayInputStream(outputStream.toString().getBytes(StandardCharsets.UTF_8));
  }

  private void safeCall(CheckedRunnable<?> runnable) {
    try {
      runnable.run();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @FunctionalInterface
  public interface CheckedRunnable<E extends Exception> extends Runnable {
    @Override
    default void run() {
      try {
        runThrows();
      } catch (Exception ex) {
        throw new RuntimeException(ex);
      }
    }

    void runThrows() throws E;
  }
}
