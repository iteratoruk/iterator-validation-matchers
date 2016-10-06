
package iterator.test.matchers.validation;

import static iterator.test.matchers.validation.ValidationMatchers.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.validation.constraints.*;
import javax.validation.constraints.Pattern.Flag;

import org.hibernate.validator.constraints.*;
import org.hibernate.validator.constraints.EAN.Type;
import org.hibernate.validator.constraints.Mod11Check.ProcessingDirection;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.junit.Test;

import iterator.test.matchers.type.annotation.AnnotationMap;

@ScriptAssert(lang = "foo", script = "bar")
public class ValidationMatchersTest {

    @ScriptAssert(script = "bar", lang = "foo", alias = "baz")
    public static class AnotherType {}

    @AssertFalse(message = "foo")
    private String assertFalseAnnotation;

    @AssertFalse
    private String assertFalseAnnotationWithDefaults;

    @AssertTrue(message = "bar")
    private String assertTrueAnnotation;

    @AssertTrue
    private String assertTrueAnnotationWithDefaults;

    @CreditCardNumber(message = "baz")
    private String creditCardNumberAnnotation;

    @CreditCardNumber
    private String creditCardNumberAnnotationWithDefaults;

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

    @Email
    private String emailAnnotationWithDefaults;

    @Future(message = "bar")
    private String futureAnnotation;

    @Future
    private String futureAnnotationWithDefaults;

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

    @Mod10Check(startIndex = 24, endIndex = 42, checkDigitIndex = 32, ignoreNonDigitCharacters = false)
    private String mod10CheckAnnotationWithStartEndCheckDigitAndIgnore;

    @Mod10Check(startIndex = 24, endIndex = 42, checkDigitIndex = 32, ignoreNonDigitCharacters = false, multiplier = 4)
    private String mod10CheckAnnotationWithStartEndCheckDigitIgnoreAndMultiplier;

    @Mod10Check(startIndex = 24, endIndex = 42, checkDigitIndex = 32, ignoreNonDigitCharacters = false, multiplier = 4, weight = 2)
    private String mod10CheckAnnotationWithStartEndCheckDigitIgnoreMultiplierAndWeight;

    @Mod11Check(checkDigitIndex = 24)
    private String mod11CheckAnnotation;

    @Mod11Check
    private String mod11CheckAnnotationWithDefaults;

    @NotBlank(message = "baz")
    private String notBlankAnnotation;

    @NotBlank
    private String notBlankAnnotationWithDefaults;

    @NotEmpty(message = "quix")
    private String notEmptyAnnotation;

    @NotEmpty
    private String notEmptyAnnotationWithDefaults;

    @NotNull(message = "foo")
    private String notNullAnnotation;

    @NotNull
    private String notNullAnnotationWithDefaults;

    @Past(message = "bar")
    private String pastAnnotation;

    @Past
    private String pastAnnotationWithDefaults;

    @Pattern(regexp = "baz")
    private String patternAnnotation;

    @Pattern(regexp = "baz", flags = { Flag.CASE_INSENSITIVE, Flag.MULTILINE })
    private String patternAnnotationWithFlags;

    @Range(min = 24, max = 42)
    private String rangeAnnotation;

    @Range(min = 24)
    private String rangeAnnotationWithMin;

    @SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
    private String safeHtmlAnnotation;

    @SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT, additionalTags = { "foo", "bar", "baz" })
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
    public void shouldNotBeAbleToInstantiateViaReflection() throws Exception {
        Constructor<ValidationMatchers> constructor = ValidationMatchers.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        try {
            constructor.newInstance(new Object[0]);
        } catch (InvocationTargetException e) {
            assertThat(e.getCause(), instanceOf(IllegalStateException.class));
        }
    }

    @Test
    public void shouldMatchAssertFalseAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasAssertFalseAnnotation("assertFalseAnnotation", AnnotationMap.from(AssertFalse.class).set("message", "foo")));
    }

    @Test
    public void shouldMatchAssertFalseAnnotationWithDefaults() throws Exception {
        assertThat(ValidationMatchersTest.class, hasAssertFalseAnnotation("assertFalseAnnotationWithDefaults"));
    }

    @Test
    public void shouldNotMatchAssertFalseAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasAssertFalseAnnotation("assertFalseAnnotation", AnnotationMap.from(AssertFalse.class).set("message", "bar"))));
    }

    @Test
    public void shouldMatchAssertTrueAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasAssertTrueAnnotation("assertTrueAnnotation", AnnotationMap.from(AssertTrue.class).set("message", "bar")));
    }

    @Test
    public void shouldMatchAssertTrueAnnotationWithDefaults() throws Exception {
        assertThat(ValidationMatchersTest.class, hasAssertTrueAnnotation("assertTrueAnnotationWithDefaults"));
    }

    @Test
    public void shouldNotMatchAssertTrueAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasAssertTrueAnnotation("assertTrueAnnotation", AnnotationMap.from(AssertTrue.class).set("message", "baz"))));
    }

    @Test
    public void shouldMatchCreditCardNumberAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasCreditCardNumberAnnotation("creditCardNumberAnnotation", AnnotationMap.from(CreditCardNumber.class).set("message", "baz")));
    }

    @Test
    public void shouldMatchCreditCardNumberAnnotationWithDefaults() throws Exception {
        assertThat(ValidationMatchersTest.class, hasCreditCardNumberAnnotation("creditCardNumberAnnotationWithDefaults"));
    }

    @Test
    public void shouldNotMatchCreditCardNumberAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasCreditCardNumberAnnotation("creditCardNumberAnnotation", AnnotationMap.from(CreditCardNumber.class).set("message", "quix"))));
    }

    @Test
    public void shouldMatchDecimalMaxAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasDecimalMaxAnnotation("decimalMaxAnnotation", AnnotationMap.from(DecimalMax.class).set("value", "42.24")));
    }

    @Test
    public void shouldMatchDecimalMaxAnnotationGivenValue() throws Exception {
        assertThat(ValidationMatchersTest.class, hasDecimalMaxAnnotation("decimalMaxAnnotation", "42.24"));
    }

    @Test
    public void shouldMatchDecimalMaxAnnotationNonInclusive() throws Exception {
        assertThat(ValidationMatchersTest.class, hasDecimalMaxAnnotation("decimalMaxAnnotationNonInclusive", "42.24", false));
    }

    @Test
    public void shouldNotMatchDecimalMaxAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasDecimalMaxAnnotation("decimalMaxAnnotation", AnnotationMap.from(DecimalMax.class).set("value", "24.42"))));
    }

    @Test
    public void shouldMatchDecimalMinAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasDecimalMinAnnotation("decimalMinAnnotation", AnnotationMap.from(DecimalMin.class).set("value", "24.42")));
    }

    @Test
    public void shouldMatchDecimalMinAnnotationGivenValue() throws Exception {
        assertThat(ValidationMatchersTest.class, hasDecimalMinAnnotation("decimalMinAnnotation", "24.42"));
    }

    @Test
    public void shouldMatchDecimalMinAnnotationNonInclusive() throws Exception {
        assertThat(ValidationMatchersTest.class, hasDecimalMinAnnotation("decimalMinAnnotationNonInclusive", "24.42", false));
    }

    @Test
    public void shouldNotMatchDecimalMinAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasDecimalMinAnnotation("decimalMinAnnotation", AnnotationMap.from(DecimalMin.class).set("value", "42.24"))));
    }

    @Test
    public void shouldMatchDigitsAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasDigitsAnnotation("digitsAnnotation", AnnotationMap.from(Digits.class).set("integer", 42).set("fraction", 24)));
    }

    @Test
    public void shouldMatchDigitsAnnotationGivenIntegerAndFraction() throws Exception {
        assertThat(ValidationMatchersTest.class, hasDigitsAnnotation("digitsAnnotation", 42, 24));
    }

    @Test
    public void shouldNotMatchDigitsAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasDigitsAnnotation("digitsAnnotation", AnnotationMap.from(Digits.class).set("integer", 24).set("fraction", 42))));
    }

    @Test
    public void shouldMatchEanAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasEanAnnotation("eanAnnotation", AnnotationMap.from(EAN.class).set("message", "quix")));
    }

    @Test
    public void shouldMatchEanAnnotationGivenType() throws Exception {
        assertThat(ValidationMatchersTest.class, hasEanAnnotation("eanAnnotationWithType", Type.EAN8));
    }

    @Test
    public void shouldNotMatchEanAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasEanAnnotation("eanAnnotation", AnnotationMap.from(EAN.class).set("message", "foo"))));
    }

    @Test
    public void shouldMatchEmailAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasEmailAnnotation("emailAnnotation", AnnotationMap.from(Email.class).set("message", "foo")));
    }

    @Test
    public void shouldMatchEmailAnnotationWithDefaults() throws Exception {
        assertThat(ValidationMatchersTest.class, hasEmailAnnotation("emailAnnotationWithDefaults"));
    }

    @Test
    public void shouldNotMatchEmailAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasEmailAnnotation("emailAnnotation", AnnotationMap.from(Email.class).set("message", "bar"))));
    }

    @Test
    public void shouldMatchFutureAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasFutureAnnotation("futureAnnotation", AnnotationMap.from(Future.class).set("message", "bar")));
    }

    @Test
    public void shouldMatchFutureAnnotationWithDefaults() throws Exception {
        assertThat(ValidationMatchersTest.class, hasFutureAnnotation("futureAnnotationWithDefaults"));
    }

    @Test
    public void shouldNotMatchFutureAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasFutureAnnotation("futureAnnotation", AnnotationMap.from(Future.class).set("message", "baz"))));
    }

    @Test
    public void shouldMatchLengthAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasLengthAnnotation("lengthAnnotation", AnnotationMap.from(Length.class).set("max", 42)));
    }

    @Test
    public void shouldMatchLengthAnnotationGivenMin() throws Exception {
        assertThat(ValidationMatchersTest.class, hasLengthAnnotation("lengthAnnotationWithMin", 24));
    }

    @Test
    public void shouldMatchLengthAnnotationGivenMinAndMax() throws Exception {
        assertThat(ValidationMatchersTest.class, hasLengthAnnotation("lengthAnnotationWithMinAndMax", 24, 42));
    }

    @Test
    public void shouldNotMatchLengthAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasLengthAnnotation("lengthAnnotation", AnnotationMap.from(Length.class).set("max", 24))));
    }

    @Test
    public void shouldMatchLuhnCheckAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasLuhnCheckAnnotation("luhnCheckAnnotation", AnnotationMap.from(LuhnCheck.class).set("checkDigitIndex", 24)));
    }

    @Test
    public void shouldMatchLuhnCheckAnnotationWithStart() throws Exception {
        assertThat(ValidationMatchersTest.class, hasLuhnCheckAnnotation("luhnCheckAnnotationWithStart", 24));
    }

    @Test
    public void shouldMatchLuhnCheckAnnotationWithStartAndEnd() throws Exception {
        assertThat(ValidationMatchersTest.class, hasLuhnCheckAnnotation("luhnCheckAnnotationWithStartAndEnd", 24, 42));
    }

    @Test
    public void shouldMatchLuhnCheckAnnotationWithStartEndAndCheckDigit() throws Exception {
        assertThat(ValidationMatchersTest.class, hasLuhnCheckAnnotation("luhnCheckAnnotationWithStartEndAndCheckDigit", 24, 42, 32));
    }

    @Test
    public void shouldMatchLuhnCheckAnnotationWithStartEndCheckDigitAndIgnore() throws Exception {
        assertThat(ValidationMatchersTest.class, hasLuhnCheckAnnotation("luhnCheckAnnotationWithStartEndCheckDigitAndIgnore", 24, 42, 32, false));
    }

    @Test
    public void shouldNotMatchLuhnCheckAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasLuhnCheckAnnotation("luhnCheckAnnotation", AnnotationMap.from(LuhnCheck.class).set("checkDigitIndex", 42))));
    }

    @Test
    public void shouldMatchMaxAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasMaxAnnotation("maxAnnotation", AnnotationMap.from(Max.class).set("value", 42L)));
    }

    @Test
    public void shouldNotMatchMaxAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasMaxAnnotation("maxAnnotation", AnnotationMap.from(Max.class).set("value", 24L))));
    }

    @Test
    public void shouldNotMatchMaxAnnotationGivenValue() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasMaxAnnotation("maxAnnotation", 24L)));
    }

    @Test
    public void shouldMatchMinAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasMinAnnotation("minAnnotation", AnnotationMap.from(Min.class).set("value", 24L)));
    }

    @Test
    public void shouldMatchMinAnnotationGivenValue() throws Exception {
        assertThat(ValidationMatchersTest.class, hasMinAnnotation("minAnnotation", 24L));
    }

    @Test
    public void shouldNotMatchMinAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasMinAnnotation("minAnnotation", AnnotationMap.from(Min.class).set("value", 42L))));
    }

    @Test
    public void shouldMatchMod10CheckAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasMod10CheckAnnotation("mod10CheckAnnotation", AnnotationMap.from(Mod10Check.class).set("checkDigitIndex", 42)));
    }

    @Test
    public void shouldMatchMod10CheckAnnotationGivenStart() throws Exception {
        assertThat(ValidationMatchersTest.class, hasMod10CheckAnnotation("mod10CheckAnnotationWithStart", 24));
    }

    @Test
    public void shouldMatchMod10CheckAnnotationGivenStartAndEnd() throws Exception {
        assertThat(ValidationMatchersTest.class, hasMod10CheckAnnotation("mod10CheckAnnotationWithStartAndEnd", 24, 42));
    }

    @Test
    public void shouldMatchMod10CheckAnnotationGivenStartEndAndCheckDigit() throws Exception {
        assertThat(ValidationMatchersTest.class, hasMod10CheckAnnotation("mod10CheckAnnotationWithStartEndAndCheckDigit", 24, 42, 32));
    }

    @Test
    public void shouldMatchMod10CheckAnnotationGivenStartEndCheckDigitAndIgnore() throws Exception {
        assertThat(ValidationMatchersTest.class, hasMod10CheckAnnotation("mod10CheckAnnotationWithStartEndCheckDigitAndIgnore", 24, 42, 32, false));
    }

    @Test
    public void shouldMatchMod10CheckAnnotationGivenStartEndCheckDigitIgnoreAndMultiplier() throws Exception {
        assertThat(ValidationMatchersTest.class, hasMod10CheckAnnotation("mod10CheckAnnotationWithStartEndCheckDigitIgnoreAndMultiplier", 24, 42, 32, false, 4));
    }

    @Test
    public void shouldMatchMod10CheckAnnotationGivenStartEndCheckDigitIgnoreMultiplierAndWeight() throws Exception {
        assertThat(ValidationMatchersTest.class, hasMod10CheckAnnotation("mod10CheckAnnotationWithStartEndCheckDigitIgnoreMultiplierAndWeight", 24, 42, 32, false, 4, 2));
    }

    @Test
    public void shouldNotMatchMod10CheckAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasMod10CheckAnnotation("mod10CheckAnnotation", AnnotationMap.from(Mod10Check.class).set("checkDigitIndex", 24))));
    }

    @Test
    public void shouldMatchMod11CheckAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasMod11CheckAnnotation("mod11CheckAnnotation", AnnotationMap.from(Mod11Check.class).set("checkDigitIndex", 24)));
    }

    @Test
    public void shouldMatchMod11CheckAnnotationGivenStart() throws Exception {
        assertThat(ValidationMatchersTest.class, hasMod11CheckAnnotation("mod11CheckAnnotationWithDefaults", 0));
    }

    @Test
    public void shouldMatchMod11CheckAnnotationGivenStartAndEnd() throws Exception {
        assertThat(ValidationMatchersTest.class, hasMod11CheckAnnotation("mod11CheckAnnotationWithDefaults", 0, Integer.MAX_VALUE));
    }

    @Test
    public void shouldMatchMod11CheckAnnotationGivenStartEndAndCheckDigit() throws Exception {
        assertThat(ValidationMatchersTest.class, hasMod11CheckAnnotation("mod11CheckAnnotation", 0, Integer.MAX_VALUE, 24));
    }

    @Test
    public void shouldMatchMod11CheckAnnotationGivenStartEndCheckDigitAndIgnore() throws Exception {
        assertThat(ValidationMatchersTest.class, hasMod11CheckAnnotation("mod11CheckAnnotation", 0, Integer.MAX_VALUE, 24, false));
    }

    @Test
    public void shouldMatchMod11CheckAnnotationGivenStartEndCheckDigitIgnoreAndThreshold() throws Exception {
        assertThat(ValidationMatchersTest.class, hasMod11CheckAnnotation("mod11CheckAnnotation", 0, Integer.MAX_VALUE, 24, false, Integer.MAX_VALUE));
    }

    @Test
    public void shouldMatchMod11CheckAnnotationGivenStartEndCheckDigitIgnoreThresholdAnd10As() throws Exception {
        assertThat(ValidationMatchersTest.class, hasMod11CheckAnnotation("mod11CheckAnnotation", 0, Integer.MAX_VALUE, 24, false, Integer.MAX_VALUE, 'X'));
    }

    @Test
    public void shouldMatchMod11CheckAnnotationGivenStartEndCheckDigitIgnoreThreshold10AsAnd11As() throws Exception {
        assertThat(ValidationMatchersTest.class, hasMod11CheckAnnotation("mod11CheckAnnotation", 0, Integer.MAX_VALUE, 24, false, Integer.MAX_VALUE, 'X', '0'));
    }

    @Test
    public void shouldMatchMod11CheckAnnotationGivenStartEndCheckDigitIgnoreThreshold10As11AsAndDirection() throws Exception {
        assertThat(ValidationMatchersTest.class, hasMod11CheckAnnotation("mod11CheckAnnotation", 0, Integer.MAX_VALUE, 24, false, Integer.MAX_VALUE, 'X', '0', ProcessingDirection.RIGHT_TO_LEFT));
    }

    @Test
    public void shouldNotMatchMod11CheckAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasMod11CheckAnnotation("mod11CheckAnnotation", AnnotationMap.from(Mod11Check.class).set("checkDigitIndex", 42))));
    }

    @Test
    public void shouldMatchNotBlankAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasNotBlankAnnotation("notBlankAnnotation", AnnotationMap.from(NotBlank.class).set("message", "baz")));
    }

    @Test
    public void shouldMatchNotBlankAnnotationWithDefaults() throws Exception {
        assertThat(ValidationMatchersTest.class, hasNotBlankAnnotation("notBlankAnnotationWithDefaults"));
    }

    @Test
    public void shouldNotMatchNotBlankAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasNotBlankAnnotation("notBlankAnnotation", AnnotationMap.from(NotBlank.class).set("message", "quix"))));
    }

    @Test
    public void shouldMatchNotEmptyAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasNotEmptyAnnotation("notEmptyAnnotation", AnnotationMap.from(NotEmpty.class).set("message", "quix")));
    }

    @Test
    public void shouldMatchNotEmptyAnnotationWithDefaults() throws Exception {
        assertThat(ValidationMatchersTest.class, hasNotEmptyAnnotation("notEmptyAnnotationWithDefaults"));
    }

    @Test
    public void shouldNotMatchNotEmptyAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasNotEmptyAnnotation("notEmptyAnnotation", AnnotationMap.from(NotEmpty.class).set("message", "foo"))));
    }

    @Test
    public void shouldMatchNotNullAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasNotNullAnnotation("notNullAnnotation", AnnotationMap.from(NotNull.class).set("message", "foo")));
    }

    @Test
    public void shouldMatchNotNullAnnotationWithDefaults() throws Exception {
        assertThat(ValidationMatchersTest.class, hasNotNullAnnotation("notNullAnnotationWithDefaults"));
    }

    @Test
    public void shouldNotMatchNotNullAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasNotNullAnnotation("notNullAnnotation", AnnotationMap.from(NotNull.class).set("message", "bar"))));
    }

    @Test
    public void shouldMatchPastAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasPastAnnotation("pastAnnotation", AnnotationMap.from(Past.class).set("message", "bar")));
    }

    @Test
    public void shouldMatchPastAnnotationWithDefaults() throws Exception {
        assertThat(ValidationMatchersTest.class, hasPastAnnotation("pastAnnotationWithDefaults"));
    }

    @Test
    public void shouldNotMatchPastAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasPastAnnotation("pastAnnotation", AnnotationMap.from(Past.class).set("message", "baz"))));
    }

    @Test
    public void shouldMatchPatternAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasPatternAnnotation("patternAnnotation", AnnotationMap.from(Pattern.class).set("regexp", "baz")));
    }

    @Test
    public void shouldMatchPatternAnnotationGivenRegexp() throws Exception {
        assertThat(ValidationMatchersTest.class, hasPatternAnnotation("patternAnnotation", "baz"));
    }

    @Test
    public void shouldMatchPatternAnnotationGivenRegexpAndFlags() throws Exception {
        assertThat(ValidationMatchersTest.class, hasPatternAnnotation("patternAnnotationWithFlags", "baz", Flag.CASE_INSENSITIVE, Flag.MULTILINE));
    }

    @Test
    public void shouldNotMatchPatternAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasPatternAnnotation("patternAnnotation", AnnotationMap.from(Pattern.class).set("message", "quix"))));
    }

    @Test
    public void shouldMatchRangeAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasRangeAnnotation("rangeAnnotation", AnnotationMap.from(Range.class).set("min", 24L).set("max", 42L)));
    }

    @Test
    public void shouldMatchRangeAnnotationGivenMin() throws Exception {
        assertThat(ValidationMatchersTest.class, hasRangeAnnotation("rangeAnnotationWithMin", 24));
    }

    @Test
    public void shouldMatchRangeAnnotationGivenMinAndMax() throws Exception {
        assertThat(ValidationMatchersTest.class, hasRangeAnnotation("rangeAnnotation", 24L, 42L));
    }

    @Test
    public void shouldNotMatchRangeAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasRangeAnnotation("rangeAnnotation", AnnotationMap.from(Range.class).set("min", 42L).set("max", 24L))));
    }

    @Test
    public void shouldMatchSafeHtmlAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasSafeHtmlAnnotation("safeHtmlAnnotation", AnnotationMap.from(SafeHtml.class).set("whitelistType", WhiteListType.SIMPLE_TEXT)));
    }

    @Test
    public void shouldMatchSafeHtmlAnnotationGivenWhitelistType() throws Exception {
        assertThat(ValidationMatchersTest.class, hasSafeHtmlAnnotation("safeHtmlAnnotation", WhiteListType.SIMPLE_TEXT));
    }

    @Test
    public void shouldMatchSafeHtmlAnnotationGivenWhitelistTypeAndTags() throws Exception {
        assertThat(ValidationMatchersTest.class, hasSafeHtmlAnnotation("safeHtmlAnnotationWithAdditionalTags", WhiteListType.SIMPLE_TEXT, "foo", "bar", "baz"));
    }

    @Test
    public void shouldNotMatchSafeHtmlAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasSafeHtmlAnnotation("safeHtmlAnnotation", AnnotationMap.from(SafeHtml.class).set("whitelistType", WhiteListType.NONE))));
    }

    @Test
    public void shouldMatchScriptAssertAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasScriptAssertAnnotation(AnnotationMap.from(ScriptAssert.class).set("lang", "foo").set("script", "bar")));
    }

    @Test
    public void shouldMatchScriptAssertAnnotationGivenScriptAndLang() throws Exception {
        assertThat(ValidationMatchersTest.class, hasScriptAssertAnnotation("bar", "foo"));
    }

    @Test
    public void shouldMatchScriptAssertAnnotationGivenScriptLangAndAlias() throws Exception {
        assertThat(AnotherType.class, hasScriptAssertAnnotation("bar", "foo", "baz"));
    }

    @Test
    public void shouldNotMatchScriptAssertAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasScriptAssertAnnotation(AnnotationMap.from(ScriptAssert.class).set("lang", "baz").set("script", "quix"))));
    }

    @Test
    public void shouldMatchSizeAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasSizeAnnotation("sizeAnnotation", AnnotationMap.from(Size.class).set("max", 42)));
    }

    @Test
    public void shouldMatchSizeAnnotationWithMin() throws Exception {
        assertThat(ValidationMatchersTest.class, hasSizeAnnotation("sizeAnnotationWithMin", 24));
    }

    @Test
    public void shouldMatchSizeAnnotationWithMinAndMax() throws Exception {
        assertThat(ValidationMatchersTest.class, hasSizeAnnotation("sizeAnnotationWithMinAndMax", 24, 42));
    }

    @Test
    public void shouldNotMatchSizeAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasSizeAnnotation("sizeAnnotation", AnnotationMap.from(Size.class).set("max", 24))));
    }

    @Test
    public void shouldMatchUrlAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasUrlAnnotation("urlAnnotation", AnnotationMap.from(URL.class).set("protocol", "quix")));
    }

    @Test
    public void shouldMatchUrlAnnotationGivenProtocol() throws Exception {
        assertThat(ValidationMatchersTest.class, hasUrlAnnotation("urlAnnotation", "quix"));
    }

    @Test
    public void shouldMatchUrlAnnotationGivenProtocolAndHost() throws Exception {
        assertThat(ValidationMatchersTest.class, hasUrlAnnotation("urlAnnotationWithProtocolAndHost", "quix", "foo"));
    }

    @Test
    public void shouldMatchUrlAnnotationGivenProtocolHostAndPort() throws Exception {
        assertThat(ValidationMatchersTest.class, hasUrlAnnotation("urlAnnotationWithProtocolHostAndPort", "quix", "foo", 42));
    }

    @Test
    public void shouldNotMatchUrlAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasUrlAnnotation("urlAnnotation", AnnotationMap.from(URL.class).set("protocol", "foo"))));
    }

}
