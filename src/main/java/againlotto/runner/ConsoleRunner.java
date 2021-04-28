package againlotto.runner;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

import againlotto.domain.LottoList;
import againlotto.domain.LottoMatcher;
import againlotto.domain.LottoStore;
import againlotto.domain.RandomLottoStore;
import againlotto.view.IO;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;

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
    io.println(pretty(lottoList));
    io.println();

    final List<Integer> winningNumbers = requestWinningNumbers();
    io.println();

    final LottoMatcher lottoMatcher = lottoList.match(winningNumbers);
    showStatistics(lottoMatcher);
  }

  private List<Integer> requestWinningNumbers() {
    io.println("지난 주 당첨 번호를 입력해 주세요.");
    return io.nextIntList();
  }

  private int requestMoney() {
    io.println("구입금액을 입력해 주세요.");
    return io.nextInt();
  }

  private void showPurchaseCount(int amount) {
    io.println(String.format("%s개를 구매했습니다.", amount));
  }

  private LottoList lottoList(int amount) {
    return new LottoList(range(0, amount)
      .mapToObj(__ -> lottoStore.nextLotto())
      .collect(toList()));
  }

  private String pretty(LottoList lottoList) {
    return lottoList
      .map(lotto -> lotto.sorted()
        .map(String::valueOf)
        .collect(joining(", ", "[", "]")))
      .collect(joining("\n"));
  }

  private void showStatistics(LottoMatcher lottoMatcher) {
    io.println("당첨 통계");
    io.println("---------");
    io.println(format("3개 일치 (5000원)- %s개", lottoMatcher.countMatched(3)));
    io.println(format("4개 일치 (50000원)- %s개", lottoMatcher.countMatched(4)));
    io.println(format("5개 일치 (1500000원)- %s개", lottoMatcher.countMatched(5)));
    io.println(format("6개 일치 (2000000000원)- %s개", lottoMatcher.countMatched(6)));

    NumberFormat formatter = NumberFormat.getInstance();
    formatter.setMaximumFractionDigits(2);
    formatter.setRoundingMode(RoundingMode.DOWN);
    io.print(format("총 수익률은 %s입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)", formatter.format(lottoMatcher.computeReturnRate())));
  }
}
