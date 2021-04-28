package againlotto.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertThrows;

import againlotto.domain.Contracts.ContractViolationException;
import java.util.Arrays;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

class LottoTest {
  private static final Integer[] CONTAINS_DUPLICATE = {1, 2, 3, 4, 6, 6};
  private static final Integer[] ANY_NUMBERS = {1, 2, 3, 4, 5, 6};

  @Test
  void lotto_are_recognized_as_different__if_all_the_numbers_are_the_same() {
    final Lotto aLotto = lotto(ANY_NUMBERS);
    final Lotto other = lotto(ANY_NUMBERS);

    assertThat(aLotto, is(not(other)));
  }

  @Test
  void cannot_make_lotto__if_a_duplicate_exists() {
    assertThrows(ContractViolationException.class, () -> lotto(CONTAINS_DUPLICATE));
  }

  private static Lotto lotto(Integer... numbers) {
    return new Lotto(new HashSet<>(Arrays.asList(numbers)));
  }
}
