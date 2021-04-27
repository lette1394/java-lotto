package againlotto.runner;

import againlotto.domain.Lotto;
import againlotto.domain.LottoStore;
import againlotto.domain.RandomLottoStore;
import againlotto.view.IO;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConsoleRunner {
  private final IO io;
  private final LottoStore lottoStore;

  public ConsoleRunner(IO io) {
    this(io, RandomLottoStore.INSTANCE);
  }

  public ConsoleRunner(IO io, LottoStore lottoStore) {
    this.io = io;
    this.lottoStore = lottoStore;
  }

  public void run() {
    final int amount = requestPurchaseAmount();
    showPurchaseCount(amount);

    final String join = IntStream.range(0, amount / 1000)
      .mapToObj(__ -> lottoStore.nextLotto())
      .map(this::format)
      .collect(Collectors.joining("\n"));

    io.print(""
      + join
      + "\n"
      + "\n"
      + "지난 주 당첨 번호를 입력해 주세요.\n"
      + "\n"
      + "당첨 통계\n"
      + "---------\n"
      + "3개 일치 (5000원)- 1개\n"
      + "4개 일치 (50000원)- 0개\n"
      + "5개 일치 (1500000원)- 0개\n"
      + "6개 일치 (2000000000원)- 0개\n"
      + "총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
  }

  private String format(Lotto lotto) {
    return lotto.sorted()
      .map(String::valueOf)
      .collect(Collectors.joining(", ", "[", "]"));
  }

  private int requestPurchaseAmount() {
    io.println("구입금액을 입력해 주세요.");
    return io.nextInt();
  }

  private void showPurchaseCount(int amount) {
    final int count = amount / 1000;
    io.println(String.format("%s개를 구매했습니다.", count));
  }

}
