package againlotto;

import againlotto.domain.RandomLottoStore;
import againlotto.runner.ConsoleRunner;
import againlotto.view.IO;

public class Main {
  public static void main(String[] args) {
    final IO io = new IO(System.in, System.out);
    final ConsoleRunner consoleRunner = new ConsoleRunner(io, RandomLottoStore.INSTANCE);
    consoleRunner.run();
  }
}