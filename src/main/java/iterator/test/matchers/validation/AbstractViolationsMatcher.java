/**
 * Copyright © 2016 Iterator Ltd. (iteratoruk@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package iterator.test.matchers.validation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.ConstraintValidator;
import javax.validation.Validation;
import javax.validation.Validator;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public abstract class AbstractViolationsMatcher<T> extends TypeSafeMatcher<T> {

  private final Validator v;

  private List<Class<? extends ConstraintValidator>> actualViolations;

  protected final Optional<String> f;

  protected AbstractViolationsMatcher() {
    this(null);
  }

  protected AbstractViolationsMatcher(String field) {
    v = Validation.buildDefaultValidatorFactory().getValidator();
    f = Optional.ofNullable(field);
  }

  @Override
  public final void describeTo(Description description) {
    describeGenerally(description);
    maybeDescribeField(description);
  }

  private void maybeDescribeField(Description description) {
    f.ifPresent(field -> describeField(description, field));
  }

  protected abstract void describeGenerally(Description description);

  protected void describeField(Description description, String field) {
    description.appendText(" on field ").appendValue(field);
  }

  @Override
  protected final void describeMismatchSafely(T item, Description mismatchDescription) {
    describeMismatchGenerally(item, mismatchDescription, actualViolations);
    maybeDescribeField(mismatchDescription);
  }

  protected abstract void describeMismatchGenerally(
      T item,
      Description mismatchDescription,
      List<Class<? extends ConstraintValidator>> actualViolations);

  @Override
  protected final boolean matchesSafely(T item) {
    actualViolations =
        v.validate(item).stream()
            .filter(
                violation ->
                    f.map(n -> n.equals(violation.getPropertyPath().toString())).orElse(true))
            .map(violation -> violation.getConstraintDescriptor().getConstraintValidatorClasses())
            .flatMap(List::stream)
            .collect(Collectors.toList());
    return matches(actualViolations);
  }

  protected abstract boolean matches(List<Class<? extends ConstraintValidator>> actualViolations);
}
