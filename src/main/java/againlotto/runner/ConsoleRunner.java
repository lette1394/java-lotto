package againlotto.runner;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

import againlotto.domain.LottoList;
import againlotto.domain.LottoStore;
import againlotto.domain.RandomLottoStore;
import againlotto.view.IO;

public class ConsoleRunner {
  private final IO io;
  private final LottoStore lottoStore;

  public ConsoleRunner() {
    this(IO.STANDARD, RandomLottoStore.INSTANCE);
  }

  public ConsoleRunner(IO io, LottoStore lottoStore) {
    this.io = io;
    this.lottoStore = lottoStore;
  }

  public void run() {
    final int amount = requestMoney() / 1000;
    showPurchaseCount(amount);

    final LottoList lottoList = lottoList(amount);

    io.println(format(lottoList));
    io.print(""
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

  private LottoList lottoList(int amount) {
    return new LottoList(range(0, amount)
      .mapToObj(__ -> lottoStore.nextLotto())
      .collect(toList()));
  }

  private String format(LottoList lottoList) {
    return lottoList
      .map(lotto -> lotto.sorted()
        .map(String::valueOf)
        .collect(joining(", ", "[", "]")))
      .collect(joining("\n"));
  }

  private int requestMoney() {
    io.println("구입금액을 입력해 주세요.");
    return io.nextInt();
  }

  private void showPurchaseCount(int amount) {
    io.println(String.format("%s개를 구매했습니다.", amount));
  }
}
