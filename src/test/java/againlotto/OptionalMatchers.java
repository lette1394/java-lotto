package againlotto;

import java.util.Optional;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class OptionalMatchers {
  public static Matcher<Optional<?>> empty() {
    return new TypeSafeDiagnosingMatcher<Optional<?>>() {
      @Override
      protected boolean matchesSafely(Optional<?> item, Description mismatchDescription) {
        if (item.isPresent()) {
          mismatchDescription.appendText("was ")
            .appendValue(item);
          return false;
        }
        return true;
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("empty");
      }
    };
  }
}
