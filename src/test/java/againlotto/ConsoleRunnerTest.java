package againlotto;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import againlotto.runner.ConsoleRunner;
import againlotto.view.IO;
import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConsoleRunnerTest {
  private User aUser;
  private ByteArrayOutputStream rawOutput = new ByteArrayOutputStream();

  @BeforeEach
  void beforeEach() {
    rawOutput = new ByteArrayOutputStream();
    aUser = new User();
  }

  @Test
  void simulate() {
    aUser.enterPurchaseAmount(14000);
    aUser.enterLastWeekWinningNumber(1, 2, 3, 4, 5, 6);

    playLotto();

    String expected = ""
      + "";
    assertThat(rawOutput, is(expected));
  }

  private void playLotto() {
    final IO io = new IO(aUser.toInputStream(), rawOutput);
    final ConsoleRunner consoleRunner = new ConsoleRunner(io);
    consoleRunner.run();
  }
}