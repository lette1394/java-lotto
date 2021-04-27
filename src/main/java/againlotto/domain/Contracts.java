package againlotto.domain;

public class Contracts {
  public static void requires(boolean truthy, String message) {
    if (truthy) {
      return;
    }
    throw new ContractViolationException(message);
  }

  public static class ContractViolationException extends RuntimeException {
    public ContractViolationException(String message) {
      super(message);
    }
  }
}
