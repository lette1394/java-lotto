package againlotto.runner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import againlotto.domain.LottoStore;
import againlotto.domain.PredefinedLottoStore;
import againlotto.view.IO;
import againlotto.view.User;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.HashSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConsoleRunnerTest {
  private User anUser;
  private ByteArrayOutputStream rawOutput = new ByteArrayOutputStream();

  @BeforeEach
  void beforeEach() {
    anUser = new User();
    rawOutput = new ByteArrayOutputStream();
  }

  @Test
  void simulate() {
    anUser.enterPurchaseAmount(14000);
    anUser.enterLastWeekWinningNumber(1, 2, 3, 4, 5, 6);

    playLotto();

    String expected = ""
      + "구입금액을 입력해 주세요.\n"
      + "14개를 구매했습니다.\n"
      + "[8, 21, 23, 41, 42, 43]\n"
      + "[3, 5, 11, 16, 32, 38]\n"
      + "[7, 11, 16, 35, 36, 44]\n"
      + "[1, 8, 11, 31, 41, 42]\n"
      + "[13, 14, 16, 38, 42, 45]\n"
      + "[7, 11, 30, 40, 42, 43]\n"
      + "[2, 13, 22, 32, 38, 45]\n"
      + "[23, 25, 33, 36, 39, 41]\n"
      + "[1, 3, 5, 14, 22, 45]\n"
      + "[5, 9, 38, 41, 43, 44]\n"
      + "[2, 8, 9, 18, 19, 21]\n"
      + "[13, 14, 18, 21, 23, 35]\n"
      + "[17, 21, 29, 37, 42, 45]\n"
      + "[3, 8, 27, 30, 35, 44]\n"
      + "\n"
      + "지난 주 당첨 번호를 입력해 주세요.\n"
      + "\n"
      + "당첨 통계\n"
      + "---------\n"
      + "3개 일치 (5000원)- 1개\n"
      + "4개 일치 (50000원)- 0개\n"
      + "5개 일치 (1500000원)- 0개\n"
      + "6개 일치 (2000000000원)- 0개\n"
      + "총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    assertThat(consoleOutput(), is(expected));
  }

  private void playLotto() {
    final IO io = new IO(anUser.toInputStream(), rawOutput);
    final LottoStore lottoStore = new PredefinedLottoStore(Arrays.asList(
      new HashSet<>(Arrays.asList(8, 21, 23, 41, 42, 43)),
      new HashSet<>(Arrays.asList(3, 5, 11, 16, 32, 38)),
      new HashSet<>(Arrays.asList(7, 11, 16, 35, 36, 44)),
      new HashSet<>(Arrays.asList(1, 8, 11, 31, 41, 42)),
      new HashSet<>(Arrays.asList(13, 14, 16, 38, 42, 45)),
      new HashSet<>(Arrays.asList(7, 11, 30, 40, 42, 43)),
      new HashSet<>(Arrays.asList(2, 13, 22, 32, 38, 45)),
      new HashSet<>(Arrays.asList(23, 25, 33, 36, 39, 41)),
      new HashSet<>(Arrays.asList(1, 3, 5, 14, 22, 45)),
      new HashSet<>(Arrays.asList(5, 9, 38, 41, 43, 44)),
      new HashSet<>(Arrays.asList(2, 8, 9, 18, 19, 21)),
      new HashSet<>(Arrays.asList(13, 14, 18, 21, 23, 35)),
      new HashSet<>(Arrays.asList(17, 21, 29, 37, 42, 45)),
      new HashSet<>(Arrays.asList(3, 8, 27, 30, 35, 44))
    ));

    final ConsoleRunner consoleRunner = new ConsoleRunner(io, lottoStore);
    consoleRunner.run();
  }

  private String consoleOutput() {
    return rawOutput.toString();
  }
}