package againlotto.domain;

import static java.lang.Math.multiplyExact;
import static java.lang.Math.toIntExact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class LottoMatcher {
  private final LottoList lottoList;
  private final List<Integer> winningNumbers;

  public LottoMatcher(LottoList lottoList, List<Integer> winningNumbers) {
    this.lottoList = lottoList;
    this.winningNumbers = winningNumbers;
  }

  public int countMatched(int expectedCount) {
    return toIntExact(lottoList
      .filter(lotto -> lotto.countThatMatchesWith(winningNumbers) == expectedCount)
      .count());
  }

  public double computeReturnRate() {
    final Map<Integer, Long> mapper = new HashMap<Integer, Long>() {{
      put(3, 5000L);
      put(4, 50000L);
      put(5, 1500000L);
      put(6, 2000000000L);
    }};

    final long totalPrize = Stream.of(3, 4, 5, 6)
      .map(i -> {
        final Long win = mapper.get(i);
        final int count = countMatched(i);
        return multiplyExact(win, count);
      })
      .reduce(0L, Long::sum);

    return (double)totalPrize / (lottoList.count() * 1000);
  }
}
