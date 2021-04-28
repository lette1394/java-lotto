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
    // FIXME (jaeeun) 2021/04/29:
    //  별도 클래스로 빼기
    //  "현재 금이 얼마냐" 정도로 빼면 되는데, 과도하게 할 필요는 없고 중복만 없애면 될 듯 하다
    //  여러군데에서 쓰거나 기능 확장이 되지 않고 단순히 mapping만 하면되서...
    //  과하지 않게
    final Map<Integer, Long> mapper = new HashMap<Integer, Long>() {{
      put(3, 5000L);
      put(4, 50000L);
      put(5, 1500000L);
      put(6, 2000000000L);
    }};

    // FIXME (jaeeun) 2021/04/29:
    //  - 변수명 수정
    //  - (3,4,5,6) 같은 naive 구현 수정
    final long totalPrize = Stream.of(3, 4, 5, 6)
      .map(i -> {
        final Long win = mapper.get(i);
        final int count = countMatched(i);
        return multiplyExact(win, count);
      })
      .reduce(0L, Long::sum);

    // FIXME (jaeeun) 2021/04/29:
    //  - magic number 1000 수정
    //  - 이거는 로도 한 게임의 가격이라서 중복되면 안된다
    return (double)totalPrize / (lottoList.count() * 1000);
  }
}
