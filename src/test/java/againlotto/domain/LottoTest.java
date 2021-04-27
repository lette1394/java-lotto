package againlotto.domain;

import static againlotto.OptionalMatchers.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class LottoTest {
  private static final Integer[] NUMBERS_CONTAINS_DUPLICATE = {1, 2, 3, 4, 6, 6};
  private static final Integer[] ANY_NUMBERS = {1, 2, 3, 4, 5, 6};

  @Test
  void sameLotto() {
    final Lotto aLotto = lotto(ANY_NUMBERS);
    final Lotto other = lotto(ANY_NUMBERS);

    assertThat(aLotto, is(other));
  }

  @Test
  void checkDuplicate() {
    assertThat(lottoOptional(NUMBERS_CONTAINS_DUPLICATE), is(empty()));
  }

  private static Optional<Lotto> lottoOptional(Integer... numbers) {
    return Lotto.lotto(new HashSet<>(Arrays.asList(numbers)));
  }

  private static Lotto lotto(Integer... numbers) {
    return lottoOptional(numbers).get();
  }
}
