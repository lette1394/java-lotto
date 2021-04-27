package againlotto.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import againlotto.domain.Contracts.ContractViolationException;
import java.util.Arrays;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

class LottoTest {
  private static final Integer[] CONTAINS_DUPLICATE = {1, 2, 3, 4, 6, 6};
  private static final Integer[] ANY_NUMBERS = {1, 2, 3, 4, 5, 6};

  @Test
  void sameLotto() {
    final Lotto aLotto = lotto(ANY_NUMBERS);
    final Lotto other = lotto(ANY_NUMBERS);

    assertThat(aLotto, is(other));
  }

  @Test
  void throwWhenDuplicate() {
    assertThrows(ContractViolationException.class, () -> lotto(CONTAINS_DUPLICATE));
  }

  private static Lotto lotto(Integer... numbers) {
    return new Lotto(new HashSet<>(Arrays.asList(numbers)));
  }
}
