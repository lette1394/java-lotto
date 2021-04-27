package againlotto.domain;

import java.util.List;
import java.util.Set;

public class PredefinedLottoStore implements LottoStore {
  private final List<Set<Integer>> numbersList;
  private int index = 0;

  public PredefinedLottoStore(List<Set<Integer>> numbersList) {
    this.numbersList = numbersList;
  }

  @Override
  public Lotto nextLotto() {
    if (index >= numbersList.size()) {
      throw new IndexOutOfBoundsException();
    }
    return new Lotto(numbersList.get(index++));
  }
}
