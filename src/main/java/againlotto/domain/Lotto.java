package againlotto.domain;


import static java.util.stream.Collectors.joining;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Lotto {
  private static final int SELECT_COUNT = 6;
  private final Set<Integer> numbers;

  private Lotto(Set<Integer> numbers) {
    this.numbers = numbers;
  }

  public static Optional<Lotto> lotto(Set<Integer> numbers) {
    if (numbers.size() != SELECT_COUNT) {
      return Optional.empty();
    }
    return Optional.of(new Lotto(numbers));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Lotto)) {
      return false;
    }
    Lotto lotto = (Lotto) o;
    return Objects.equals(numbers, lotto.numbers);
  }

  @Override
  public int hashCode() {
    return numbers.hashCode();
  }

  @Override
  public String toString() {
    return numbers.stream()
      .map(String::valueOf)
      .collect(joining(", ", "[", "]"));
  }
}
