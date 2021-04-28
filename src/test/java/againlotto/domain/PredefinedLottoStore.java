package againlotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class PredefinedLottoStore implements LottoStore {
  private final List<Lotto> numbersList;
  private int index = 0;

  public PredefinedLottoStore(List<List<Integer>> numbersList) {
    this.numbersList = numbersList.stream()
      .map(HashSet::new)
      .map(Lotto::new)
      .collect(Collectors.toList());
  }

  @Override
  public Lotto nextLotto() {
    if (index >= numbersList.size()) {
      throw new IndexOutOfBoundsException();
    }
    return numbersList.get(index++);
  }
}
