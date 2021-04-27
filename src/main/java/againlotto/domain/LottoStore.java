package againlotto.domain;

@FunctionalInterface
public interface LottoStore {
  Lotto nextLotto();
}
