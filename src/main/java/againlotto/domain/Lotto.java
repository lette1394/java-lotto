package againlotto.domain;


import static againlotto.domain.Contracts.requires;
import static java.util.stream.Collectors.joining;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class Lotto implements StreamMixin<Integer> {
  private static final int SELECT_COUNT = 6;
  private final Set<Integer> numbers;

  public Lotto(Set<Integer> numbers) {
    requires(numbers.size() == SELECT_COUNT, "numbers.size() == SELECT_COUNT");
    this.numbers = numbers;
  }

  @Override
  public Stream<Integer> getStream() {
    return numbers.stream();
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