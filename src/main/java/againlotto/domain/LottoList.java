package againlotto.domain;

import java.util.List;
import java.util.stream.Stream;

public class LottoList implements StreamMixin<Lotto> {
  private final List<Lotto> lottoList;

  public LottoList(List<Lotto> lottoList) {
    this.lottoList = lottoList;
  }

  @Override
  public Stream<Lotto> getStream() {
    return lottoList.stream();
  }
}
