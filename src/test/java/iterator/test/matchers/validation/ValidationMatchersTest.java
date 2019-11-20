package iterator.test.matchers.validation;

import static iterator.test.matchers.validation.ValidationMatchers.hasAssertFalseAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasAssertTrueAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasCreditCardNumberAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasDecimalMaxAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasDecimalMinAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasDigitsAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasEanAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasEmailAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasFutureAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasLengthAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasLuhnCheckAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasMaxAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasMinAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasMod10CheckAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasMod11CheckAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasNotBlankAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasNotEmptyAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasNotNullAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasPastAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasPatternAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasRangeAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasSafeHtmlAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasScriptAssertAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasSizeAnnotation;
import static iterator.test.matchers.validation.ValidationMatchers.hasUrlAnnotation;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

import iterator.test.matchers.type.annotation.AnnotationMap;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.EAN;
import org.hibernate.validator.constraints.EAN.Type;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.LuhnCheck;
import org.hibernate.validator.constraints.Mod10Check;
import org.hibernate.validator.constraints.Mod11Check;
import org.hibernate.validator.constraints.Mod11Check.ProcessingDirection;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.ScriptAssert;
import org.hibernate.validator.constraints.URL;
import org.junit.jupiter.api.Test;

@ScriptAssert(lang = "foo", script = "bar")
class ValidationMatchersTest {

  @ScriptAssert(script = "bar", lang = "foo", alias = "baz")
  private static class AnotherType {}

  @AssertFalse(message = "foo")
  private String assertFalseAnnotation;

  @AssertFalse private String assertFalseAnnotationWithDefaults;

  @AssertTrue(message = "bar")
  private String assertTrueAnnotation;

  @AssertTrue private String assertTrueAnnotationWithDefaults;

  @CreditCardNumber(message = "baz")
  private String creditCardNumberAnnotation;

  @CreditCardNumber private String creditCardNumberAnnotationWithDefaults;

  @DecimalMax(value = "42.24")
  private String decimalMaxAnnotation;

  @DecimalMax(value = "42.24", inclusive = false)
  private String decimalMaxAnnotationNonInclusive;

  @DecimalMin(value = "24.42")
  private String decimalMinAnnotation;

  @DecimalMin(value = "24.42", inclusive = false)
  private String decimalMinAnnotationNonInclusive;

  @Digits(integer = 42, fraction = 24)
  private String digitsAnnotation;

  @EAN(message = "quix")
  private String eanAnnotation;

  @EAN(type = Type.EAN8)
  private String eanAnnotationWithType;

  @Email(message = "foo")
  private String emailAnnotation;

  @Email private String emailAnnotationWithDefaults;

  @Future(message = "bar")
  private String futureAnnotation;

  @Future private String futureAnnotationWithDefaults;

  @Length(max = 42)
  private String lengthAnnotation;

  @Length(min = 24)
  private String lengthAnnotationWithMin;

  @Length(min = 24, max = 42)
  private String lengthAnnotationWithMinAndMax;

  @LuhnCheck(checkDigitIndex = 24)
  private String luhnCheckAnnotation;

  @LuhnCheck(startIndex = 24)
  private String luhnCheckAnnotationWithStart;

  @LuhnCheck(startIndex = 24, endIndex = 42)
  private String luhnCheckAnnotationWithStartAndEnd;

  @LuhnCheck(startIndex = 24, endIndex = 42, checkDigitIndex = 32)
  private String luhnCheckAnnotationWithStartEndAndCheckDigit;

  @LuhnCheck(startIndex = 24, endIndex = 42, checkDigitIndex = 32, ignoreNonDigitCharacters = false)
  private String luhnCheckAnnotationWithStartEndCheckDigitAndIgnore;

  @Max(42)
  private String maxAnnotation;

  @Min(24)
  private String minAnnotation;

  @Mod10Check(checkDigitIndex = 42)
  private String mod10CheckAnnotation;

  @Mod10Check(startIndex = 24)
  private String mod10CheckAnnotationWithStart;

  @Mod10Check(startIndex = 24, endIndex = 42)
  private String mod10CheckAnnotationWithStartAndEnd;

  @Mod10Check(startIndex = 24, endIndex = 42, checkDigitIndex = 32)
  private String mod10CheckAnnotationWithStartEndAndCheckDigit;

  @Mod10Check(
      startIndex = 24,
      endIndex = 42,
      checkDigitIndex = 32,
      ignoreNonDigitCharacters = false)
  private String mod10CheckAnnotationWithStartEndCheckDigitAndIgnore;

  @Mod10Check(
      startIndex = 24,
      endIndex = 42,
      checkDigitIndex = 32,
      ignoreNonDigitCharacters = false,
      multiplier = 4)
  private String mod10CheckAnnotationWithStartEndCheckDigitIgnoreAndMultiplier;

  @Mod10Check(
      startIndex = 24,
      endIndex = 42,
      checkDigitIndex = 32,
      ignoreNonDigitCharacters = false,
      multiplier = 4,
      weight = 2)
  private String mod10CheckAnnotationWithStartEndCheckDigitIgnoreMultiplierAndWeight;

  @Mod11Check(checkDigitIndex = 24)
  private String mod11CheckAnnotation;

  @Mod11Check private String mod11CheckAnnotationWithDefaults;

  @NotBlank(message = "baz")
  private String notBlankAnnotation;

  @NotBlank private String notBlankAnnotationWithDefaults;

  @NotEmpty(message = "quix")
  private String notEmptyAnnotation;

  @NotEmpty private String notEmptyAnnotationWithDefaults;

  @NotNull(message = "foo")
  private String notNullAnnotation;

  @NotNull private String notNullAnnotationWithDefaults;

  @Past(message = "bar")
  private String pastAnnotation;

  @Past private String pastAnnotationWithDefaults;

  @Pattern(regexp = "baz")
  private String patternAnnotation;

  @Pattern(
      regexp = "baz",
      flags = {Flag.CASE_INSENSITIVE, Flag.MULTILINE})
  private String patternAnnotationWithFlags;

  @Range(min = 24, max = 42)
  private String rangeAnnotation;

  @Range(min = 24)
  private String rangeAnnotationWithMin;

  @SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
  private String safeHtmlAnnotation;

  @SafeHtml(
      whitelistType = WhiteListType.SIMPLE_TEXT,
      additionalTags = {"foo", "bar", "baz"})
  private String safeHtmlAnnotationWithAdditionalTags;

  @Size(max = 42)
  private String sizeAnnotation;

  @Size(min = 24)
  private String sizeAnnotationWithMin;

  @Size(min = 24, max = 42)
  private String sizeAnnotationWithMinAndMax;

  @URL(protocol = "quix")
  private String urlAnnotation;

  @URL(protocol = "quix", host = "foo")
  private String urlAnnotationWithProtocolAndHost;

  @URL(protocol = "quix", host = "foo", port = 42)
  private String urlAnnotationWithProtocolHostAndPort;

  @Test
  void shouldNotBeAbleToInstantiateViaReflection() throws Exception {
    Constructor<ValidationMatchers> constructor = ValidationMatchers.class.getDeclaredConstructor();
    constructor.setAccessible(true);
    try {
      constructor.newInstance();
    } catch (InvocationTargetException e) {
      assertThat(e.getCause(), instanceOf(IllegalStateException.class));
    }
  }

  @Test
  void shouldMatchAssertFalseAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasAssertFalseAnnotation(
            "assertFalseAnnotation", AnnotationMap.from(AssertFalse.class).set("message", "foo")));
  }

  @Test
  void shouldMatchAssertFalseAnnotationWithDefaults() {
    assertThat(
        ValidationMatchersTest.class,
        hasAssertFalseAnnotation("assertFalseAnnotationWithDefaults"));
  }

  @Test
  void shouldNotMatchAssertFalseAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasAssertFalseAnnotation(
                "assertFalseAnnotation",
                AnnotationMap.from(AssertFalse.class).set("message", "bar"))));
  }

  @Test
  void shouldMatchAssertTrueAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasAssertTrueAnnotation(
            "assertTrueAnnotation", AnnotationMap.from(AssertTrue.class).set("message", "bar")));
  }

  @Test
  void shouldMatchAssertTrueAnnotationWithDefaults() {
    assertThat(
        ValidationMatchersTest.class, hasAssertTrueAnnotation("assertTrueAnnotationWithDefaults"));
  }

  @Test
  void shouldNotMatchAssertTrueAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasAssertTrueAnnotation(
                "assertTrueAnnotation",
                AnnotationMap.from(AssertTrue.class).set("message", "baz"))));
  }

  @Test
  void shouldMatchCreditCardNumberAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasCreditCardNumberAnnotation(
            "creditCardNumberAnnotation",
            AnnotationMap.from(CreditCardNumber.class).set("message", "baz")));
  }

  @Test
  void shouldMatchCreditCardNumberAnnotationWithDefaults() {
    assertThat(
        ValidationMatchersTest.class,
        hasCreditCardNumberAnnotation("creditCardNumberAnnotationWithDefaults"));
  }

  @Test
  void shouldNotMatchCreditCardNumberAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasCreditCardNumberAnnotation(
                "creditCardNumberAnnotation",
                AnnotationMap.from(CreditCardNumber.class).set("message", "quix"))));
  }

  @Test
  void shouldMatchDecimalMaxAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasDecimalMaxAnnotation(
            "decimalMaxAnnotation", AnnotationMap.from(DecimalMax.class).set("value", "42.24")));
  }

  @Test
  void shouldMatchDecimalMaxAnnotationGivenValue() {
    assertThat(
        ValidationMatchersTest.class, hasDecimalMaxAnnotation("decimalMaxAnnotation", "42.24"));
  }

  @Test
  void shouldMatchDecimalMaxAnnotationNonInclusive() {
    assertThat(
        ValidationMatchersTest.class,
        hasDecimalMaxAnnotation("decimalMaxAnnotationNonInclusive", "42.24", false));
  }

  @Test
  void shouldNotMatchDecimalMaxAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasDecimalMaxAnnotation(
                "decimalMaxAnnotation",
                AnnotationMap.from(DecimalMax.class).set("value", "24.42"))));
  }

  @Test
  void shouldMatchDecimalMinAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasDecimalMinAnnotation(
            "decimalMinAnnotation", AnnotationMap.from(DecimalMin.class).set("value", "24.42")));
  }

  @Test
  void shouldMatchDecimalMinAnnotationGivenValue() {
    assertThat(
        ValidationMatchersTest.class, hasDecimalMinAnnotation("decimalMinAnnotation", "24.42"));
  }

  @Test
  void shouldMatchDecimalMinAnnotationNonInclusive() {
    assertThat(
        ValidationMatchersTest.class,
        hasDecimalMinAnnotation("decimalMinAnnotationNonInclusive", "24.42", false));
  }

  @Test
  void shouldNotMatchDecimalMinAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasDecimalMinAnnotation(
                "decimalMinAnnotation",
                AnnotationMap.from(DecimalMin.class).set("value", "42.24"))));
  }

  @Test
  void shouldMatchDigitsAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasDigitsAnnotation(
            "digitsAnnotation",
            AnnotationMap.from(Digits.class).set("integer", 42).set("fraction", 24)));
  }

  @Test
  void shouldMatchDigitsAnnotationGivenIntegerAndFraction() {
    assertThat(ValidationMatchersTest.class, hasDigitsAnnotation("digitsAnnotation", 42, 24));
  }

  @Test
  void shouldNotMatchDigitsAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasDigitsAnnotation(
                "digitsAnnotation",
                AnnotationMap.from(Digits.class).set("integer", 24).set("fraction", 42))));
  }

  @Test
  void shouldMatchEanAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasEanAnnotation("eanAnnotation", AnnotationMap.from(EAN.class).set("message", "quix")));
  }

  @Test
  void shouldMatchEanAnnotationGivenType() {
    assertThat(ValidationMatchersTest.class, hasEanAnnotation("eanAnnotationWithType", Type.EAN8));
  }

  @Test
  void shouldNotMatchEanAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasEanAnnotation(
                "eanAnnotation", AnnotationMap.from(EAN.class).set("message", "foo"))));
  }

  @Test
  void shouldMatchEmailAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasEmailAnnotation(
            "emailAnnotation", AnnotationMap.from(Email.class).set("message", "foo")));
  }

  @Test
  void shouldMatchEmailAnnotationWithDefaults() {
    assertThat(ValidationMatchersTest.class, hasEmailAnnotation("emailAnnotationWithDefaults"));
  }

  @Test
  void shouldNotMatchEmailAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasEmailAnnotation(
                "emailAnnotation", AnnotationMap.from(Email.class).set("message", "bar"))));
  }

  @Test
  void shouldMatchFutureAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasFutureAnnotation(
            "futureAnnotation", AnnotationMap.from(Future.class).set("message", "bar")));
  }

  @Test
  void shouldMatchFutureAnnotationWithDefaults() {
    assertThat(ValidationMatchersTest.class, hasFutureAnnotation("futureAnnotationWithDefaults"));
  }

  @Test
  void shouldNotMatchFutureAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasFutureAnnotation(
                "futureAnnotation", AnnotationMap.from(Future.class).set("message", "baz"))));
  }

  @Test
  void shouldMatchLengthAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasLengthAnnotation("lengthAnnotation", AnnotationMap.from(Length.class).set("max", 42)));
  }

  @Test
  void shouldMatchLengthAnnotationGivenMin() {
    assertThat(ValidationMatchersTest.class, hasLengthAnnotation("lengthAnnotationWithMin", 24));
  }

  @Test
  void shouldMatchLengthAnnotationGivenMinAndMax() {
    assertThat(
        ValidationMatchersTest.class, hasLengthAnnotation("lengthAnnotationWithMinAndMax", 24, 42));
  }

  @Test
  void shouldNotMatchLengthAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasLengthAnnotation(
                "lengthAnnotation", AnnotationMap.from(Length.class).set("max", 24))));
  }

  @Test
  void shouldMatchLuhnCheckAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasLuhnCheckAnnotation(
            "luhnCheckAnnotation", AnnotationMap.from(LuhnCheck.class).set("checkDigitIndex", 24)));
  }

  @Test
  void shouldMatchLuhnCheckAnnotationWithStart() {
    assertThat(
        ValidationMatchersTest.class, hasLuhnCheckAnnotation("luhnCheckAnnotationWithStart", 24));
  }

  @Test
  void shouldMatchLuhnCheckAnnotationWithStartAndEnd() {
    assertThat(
        ValidationMatchersTest.class,
        hasLuhnCheckAnnotation("luhnCheckAnnotationWithStartAndEnd", 24, 42));
  }

  @Test
  void shouldMatchLuhnCheckAnnotationWithStartEndAndCheckDigit() {
    assertThat(
        ValidationMatchersTest.class,
        hasLuhnCheckAnnotation("luhnCheckAnnotationWithStartEndAndCheckDigit", 24, 42, 32));
  }

  @Test
  void shouldMatchLuhnCheckAnnotationWithStartEndCheckDigitAndIgnore() {
    assertThat(
        ValidationMatchersTest.class,
        hasLuhnCheckAnnotation(
            "luhnCheckAnnotationWithStartEndCheckDigitAndIgnore", 24, 42, 32, false));
  }

  @Test
  void shouldNotMatchLuhnCheckAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasLuhnCheckAnnotation(
                "luhnCheckAnnotation",
                AnnotationMap.from(LuhnCheck.class).set("checkDigitIndex", 42))));
  }

  @Test
  void shouldMatchMaxAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasMaxAnnotation("maxAnnotation", AnnotationMap.from(Max.class).set("value", 42L)));
  }

  @Test
  void shouldNotMatchMaxAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(hasMaxAnnotation("maxAnnotation", AnnotationMap.from(Max.class).set("value", 24L))));
  }

  @Test
  void shouldNotMatchMaxAnnotationGivenValue() {
    assertThat(ValidationMatchersTest.class, not(hasMaxAnnotation("maxAnnotation", 24L)));
  }

  @Test
  void shouldMatchMinAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasMinAnnotation("minAnnotation", AnnotationMap.from(Min.class).set("value", 24L)));
  }

  @Test
  void shouldMatchMinAnnotationGivenValue() {
    assertThat(ValidationMatchersTest.class, hasMinAnnotation("minAnnotation", 24L));
  }

  @Test
  void shouldNotMatchMinAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(hasMinAnnotation("minAnnotation", AnnotationMap.from(Min.class).set("value", 42L))));
  }

  @Test
  void shouldMatchMod10CheckAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasMod10CheckAnnotation(
            "mod10CheckAnnotation",
            AnnotationMap.from(Mod10Check.class).set("checkDigitIndex", 42)));
  }

  @Test
  void shouldMatchMod10CheckAnnotationGivenStart() {
    assertThat(
        ValidationMatchersTest.class, hasMod10CheckAnnotation("mod10CheckAnnotationWithStart", 24));
  }

  @Test
  void shouldMatchMod10CheckAnnotationGivenStartAndEnd() {
    assertThat(
        ValidationMatchersTest.class,
        hasMod10CheckAnnotation("mod10CheckAnnotationWithStartAndEnd", 24, 42));
  }

  @Test
  void shouldMatchMod10CheckAnnotationGivenStartEndAndCheckDigit() {
    assertThat(
        ValidationMatchersTest.class,
        hasMod10CheckAnnotation("mod10CheckAnnotationWithStartEndAndCheckDigit", 24, 42, 32));
  }

  @Test
  void shouldMatchMod10CheckAnnotationGivenStartEndCheckDigitAndIgnore() {
    assertThat(
        ValidationMatchersTest.class,
        hasMod10CheckAnnotation(
            "mod10CheckAnnotationWithStartEndCheckDigitAndIgnore", 24, 42, 32, false));
  }

  @Test
  void shouldMatchMod10CheckAnnotationGivenStartEndCheckDigitIgnoreAndMultiplier() {
    assertThat(
        ValidationMatchersTest.class,
        hasMod10CheckAnnotation(
            "mod10CheckAnnotationWithStartEndCheckDigitIgnoreAndMultiplier", 24, 42, 32, false, 4));
  }

  @Test
  void shouldMatchMod10CheckAnnotationGivenStartEndCheckDigitIgnoreMultiplierAndWeight() {
    assertThat(
        ValidationMatchersTest.class,
        hasMod10CheckAnnotation(
            "mod10CheckAnnotationWithStartEndCheckDigitIgnoreMultiplierAndWeight",
            24,
            42,
            32,
            false,
            4,
            2));
  }

  @Test
  void shouldNotMatchMod10CheckAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasMod10CheckAnnotation(
                "mod10CheckAnnotation",
                AnnotationMap.from(Mod10Check.class).set("checkDigitIndex", 24))));
  }

  @Test
  void shouldMatchMod11CheckAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasMod11CheckAnnotation(
            "mod11CheckAnnotation",
            AnnotationMap.from(Mod11Check.class).set("checkDigitIndex", 24)));
  }

  @Test
  void shouldMatchMod11CheckAnnotationGivenStart() {
    assertThat(
        ValidationMatchersTest.class,
        hasMod11CheckAnnotation("mod11CheckAnnotationWithDefaults", 0));
  }

  @Test
  void shouldMatchMod11CheckAnnotationGivenStartAndEnd() {
    assertThat(
        ValidationMatchersTest.class,
        hasMod11CheckAnnotation("mod11CheckAnnotationWithDefaults", 0, Integer.MAX_VALUE));
  }

  @Test
  void shouldMatchMod11CheckAnnotationGivenStartEndAndCheckDigit() {
    assertThat(
        ValidationMatchersTest.class,
        hasMod11CheckAnnotation("mod11CheckAnnotation", 0, Integer.MAX_VALUE, 24));
  }

  @Test
  void shouldMatchMod11CheckAnnotationGivenStartEndCheckDigitAndIgnore() {
    assertThat(
        ValidationMatchersTest.class,
        hasMod11CheckAnnotation("mod11CheckAnnotation", 0, Integer.MAX_VALUE, 24, false));
  }

  @Test
  void shouldMatchMod11CheckAnnotationGivenStartEndCheckDigitIgnoreAndThreshold() {
    assertThat(
        ValidationMatchersTest.class,
        hasMod11CheckAnnotation(
            "mod11CheckAnnotation", 0, Integer.MAX_VALUE, 24, false, Integer.MAX_VALUE));
  }

  @Test
  void shouldMatchMod11CheckAnnotationGivenStartEndCheckDigitIgnoreThresholdAnd10As() {
    assertThat(
        ValidationMatchersTest.class,
        hasMod11CheckAnnotation(
            "mod11CheckAnnotation", 0, Integer.MAX_VALUE, 24, false, Integer.MAX_VALUE, 'X'));
  }

  @Test
  void shouldMatchMod11CheckAnnotationGivenStartEndCheckDigitIgnoreThreshold10AsAnd11As() {
    assertThat(
        ValidationMatchersTest.class,
        hasMod11CheckAnnotation(
            "mod11CheckAnnotation", 0, Integer.MAX_VALUE, 24, false, Integer.MAX_VALUE, 'X', '0'));
  }

  @Test
  void shouldMatchMod11CheckAnnotationGivenStartEndCheckDigitIgnoreThreshold10As11AsAndDirection() {
    assertThat(
        ValidationMatchersTest.class,
        hasMod11CheckAnnotation(
            "mod11CheckAnnotation",
            0,
            Integer.MAX_VALUE,
            24,
            false,
            Integer.MAX_VALUE,
            'X',
            '0',
            ProcessingDirection.RIGHT_TO_LEFT));
  }

  @Test
  void shouldNotMatchMod11CheckAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasMod11CheckAnnotation(
                "mod11CheckAnnotation",
                AnnotationMap.from(Mod11Check.class).set("checkDigitIndex", 42))));
  }

  @Test
  void shouldMatchNotBlankAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasNotBlankAnnotation(
            "notBlankAnnotation", AnnotationMap.from(NotBlank.class).set("message", "baz")));
  }

  @Test
  void shouldMatchNotBlankAnnotationWithDefaults() {
    assertThat(
        ValidationMatchersTest.class, hasNotBlankAnnotation("notBlankAnnotationWithDefaults"));
  }

  @Test
  void shouldNotMatchNotBlankAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasNotBlankAnnotation(
                "notBlankAnnotation", AnnotationMap.from(NotBlank.class).set("message", "quix"))));
  }

  @Test
  void shouldMatchNotEmptyAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasNotEmptyAnnotation(
            "notEmptyAnnotation", AnnotationMap.from(NotEmpty.class).set("message", "quix")));
  }

  @Test
  void shouldMatchNotEmptyAnnotationWithDefaults() {
    assertThat(
        ValidationMatchersTest.class, hasNotEmptyAnnotation("notEmptyAnnotationWithDefaults"));
  }

  @Test
  void shouldNotMatchNotEmptyAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasNotEmptyAnnotation(
                "notEmptyAnnotation", AnnotationMap.from(NotEmpty.class).set("message", "foo"))));
  }

  @Test
  void shouldMatchNotNullAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasNotNullAnnotation(
            "notNullAnnotation", AnnotationMap.from(NotNull.class).set("message", "foo")));
  }

  @Test
  void shouldMatchNotNullAnnotationWithDefaults() {
    assertThat(ValidationMatchersTest.class, hasNotNullAnnotation("notNullAnnotationWithDefaults"));
  }

  @Test
  void shouldNotMatchNotNullAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasNotNullAnnotation(
                "notNullAnnotation", AnnotationMap.from(NotNull.class).set("message", "bar"))));
  }

  @Test
  void shouldMatchPastAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasPastAnnotation("pastAnnotation", AnnotationMap.from(Past.class).set("message", "bar")));
  }

  @Test
  void shouldMatchPastAnnotationWithDefaults() {
    assertThat(ValidationMatchersTest.class, hasPastAnnotation("pastAnnotationWithDefaults"));
  }

  @Test
  void shouldNotMatchPastAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasPastAnnotation(
                "pastAnnotation", AnnotationMap.from(Past.class).set("message", "baz"))));
  }

  @Test
  void shouldMatchPatternAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasPatternAnnotation(
            "patternAnnotation", AnnotationMap.from(Pattern.class).set("regexp", "baz")));
  }

  @Test
  void shouldMatchPatternAnnotationGivenRegexp() {
    assertThat(ValidationMatchersTest.class, hasPatternAnnotation("patternAnnotation", "baz"));
  }

  @Test
  void shouldMatchPatternAnnotationGivenRegexpAndFlags() {
    assertThat(
        ValidationMatchersTest.class,
        hasPatternAnnotation(
            "patternAnnotationWithFlags", "baz", Flag.CASE_INSENSITIVE, Flag.MULTILINE));
  }

  @Test
  void shouldNotMatchPatternAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasPatternAnnotation(
                "patternAnnotation", AnnotationMap.from(Pattern.class).set("message", "quix"))));
  }

  @Test
  void shouldMatchRangeAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasRangeAnnotation(
            "rangeAnnotation", AnnotationMap.from(Range.class).set("min", 24L).set("max", 42L)));
  }

  @Test
  void shouldMatchRangeAnnotationGivenMin() {
    assertThat(ValidationMatchersTest.class, hasRangeAnnotation("rangeAnnotationWithMin", 24));
  }

  @Test
  void shouldMatchRangeAnnotationGivenMinAndMax() {
    assertThat(ValidationMatchersTest.class, hasRangeAnnotation("rangeAnnotation", 24L, 42L));
  }

  @Test
  void shouldNotMatchRangeAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasRangeAnnotation(
                "rangeAnnotation",
                AnnotationMap.from(Range.class).set("min", 42L).set("max", 24L))));
  }

  @Test
  void shouldMatchSafeHtmlAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasSafeHtmlAnnotation(
            "safeHtmlAnnotation",
            AnnotationMap.from(SafeHtml.class).set("whitelistType", WhiteListType.SIMPLE_TEXT)));
  }

  @Test
  void shouldMatchSafeHtmlAnnotationGivenWhitelistType() {
    assertThat(
        ValidationMatchersTest.class,
        hasSafeHtmlAnnotation("safeHtmlAnnotation", WhiteListType.SIMPLE_TEXT));
  }

  @Test
  void shouldMatchSafeHtmlAnnotationGivenWhitelistTypeAndTags() {
    assertThat(
        ValidationMatchersTest.class,
        hasSafeHtmlAnnotation(
            "safeHtmlAnnotationWithAdditionalTags",
            WhiteListType.SIMPLE_TEXT,
            "foo",
            "bar",
            "baz"));
  }

  @Test
  void shouldNotMatchSafeHtmlAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasSafeHtmlAnnotation(
                "safeHtmlAnnotation",
                AnnotationMap.from(SafeHtml.class).set("whitelistType", WhiteListType.NONE))));
  }

  @Test
  void shouldMatchScriptAssertAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasScriptAssertAnnotation(
            AnnotationMap.from(ScriptAssert.class).set("lang", "foo").set("script", "bar")));
  }

  @Test
  void shouldMatchScriptAssertAnnotationGivenScriptAndLang() {
    assertThat(ValidationMatchersTest.class, hasScriptAssertAnnotation("bar", "foo"));
  }

  @Test
  void shouldMatchScriptAssertAnnotationGivenScriptLangAndAlias() {
    assertThat(AnotherType.class, hasScriptAssertAnnotation("bar", "foo", "baz"));
  }

  @Test
  void shouldNotMatchScriptAssertAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasScriptAssertAnnotation(
                AnnotationMap.from(ScriptAssert.class).set("lang", "baz").set("script", "quix"))));
  }

  @Test
  void shouldMatchSizeAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasSizeAnnotation("sizeAnnotation", AnnotationMap.from(Size.class).set("max", 42)));
  }

  @Test
  void shouldMatchSizeAnnotationWithMin() {
    assertThat(ValidationMatchersTest.class, hasSizeAnnotation("sizeAnnotationWithMin", 24));
  }

  @Test
  void shouldMatchSizeAnnotationWithMinAndMax() {
    assertThat(
        ValidationMatchersTest.class, hasSizeAnnotation("sizeAnnotationWithMinAndMax", 24, 42));
  }

  @Test
  void shouldNotMatchSizeAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(hasSizeAnnotation("sizeAnnotation", AnnotationMap.from(Size.class).set("max", 24))));
  }

  @Test
  void shouldMatchUrlAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        hasUrlAnnotation("urlAnnotation", AnnotationMap.from(URL.class).set("protocol", "quix")));
  }

  @Test
  void shouldMatchUrlAnnotationGivenProtocol() {
    assertThat(ValidationMatchersTest.class, hasUrlAnnotation("urlAnnotation", "quix"));
  }

  @Test
  void shouldMatchUrlAnnotationGivenProtocolAndHost() {
    assertThat(
        ValidationMatchersTest.class,
        hasUrlAnnotation("urlAnnotationWithProtocolAndHost", "quix", "foo"));
  }

  @Test
  void shouldMatchUrlAnnotationGivenProtocolHostAndPort() {
    assertThat(
        ValidationMatchersTest.class,
        hasUrlAnnotation("urlAnnotationWithProtocolHostAndPort", "quix", "foo", 42));
  }

  @Test
  void shouldNotMatchUrlAnnotation() {
    assertThat(
        ValidationMatchersTest.class,
        not(
            hasUrlAnnotation(
                "urlAnnotation", AnnotationMap.from(URL.class).set("protocol", "foo"))));
  }
}
