package againlotto.domain;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class RandomLottoStore implements LottoStore {
  public static final LottoStore INSTANCE = new RandomLottoStore();

  private static final int MIN = 1;
  private static final int MAX = 46;
  private static final int COUNT = 6;
  private static final ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

  private RandomLottoStore() {
  }

  @Override
  public Lotto nextLotto() {
    final Set<Integer> numbers = threadLocalRandom
      .ints(MIN, MAX)
      .distinct()
      .limit(COUNT)
      .boxed()
      .collect(Collectors.toSet());
    return new Lotto(numbers);
  }
}
