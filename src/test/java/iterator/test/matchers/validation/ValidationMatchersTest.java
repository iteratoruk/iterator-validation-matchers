
package iterator.test.matchers.validation;

import static iterator.test.matchers.validation.ValidationMatchers.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.junit.Test;

import iterator.test.matchers.type.annotation.AnnotationMap;

@ScriptAssert(lang = "foo", script = "bar")
public class ValidationMatchersTest {

    @AssertFalse(message = "foo")
    private String assertFalseAnnotation;

    @AssertTrue(message = "bar")
    private String assertTrueAnnotation;

    @CreditCardNumber(message = "baz")
    private String creditCardNumberAnnotation;

    @DecimalMax(value = "42.24")
    private String decimalMaxAnnotation;

    @DecimalMin(value = "24.42")
    private String decimalMinAnnotation;

    @Digits(integer = 42, fraction = 24)
    private String digitsAnnotation;

    @EAN(message = "quix")
    private String eanAnnotation;

    @Email(message = "foo")
    private String emailAnnotation;

    @Future(message = "bar")
    private String futureAnnotation;

    @Length(max = 42)
    private String lengthAnnotation;

    @LuhnCheck(checkDigitIndex = 24)
    private String luhnCheckAnnotation;

    @Max(42)
    private String maxAnnotation;

    @Min(24)
    private String minAnnotation;

    @Mod10Check(checkDigitIndex = 42)
    private String mod10CheckAnnotation;

    @Mod11Check(checkDigitIndex = 24)
    private String mod11CheckAnnotation;

    @NotBlank(message = "baz")
    private String notBlankAnnotation;

    @NotEmpty(message = "quix")
    private String notEmptyAnnotation;

    @NotNull(message = "foo")
    private String notNullAnnotation;

    @Past(message = "bar")
    private String pastAnnotation;

    @Pattern(regexp = "baz")
    private String patternAnnotation;

    @Range(min = 24, max = 42)
    private String rangeAnnotation;

    @SafeHtml(whitelistType = WhiteListType.SIMPLE_TEXT)
    private String safeHtmlAnnotation;

    @Size(max = 42)
    private String sizeAnnotation;

    @URL(protocol = "quix")
    private String urlAnnotation;

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
    public void shouldNotMatchAssertFalseAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasAssertFalseAnnotation("assertFalseAnnotation", AnnotationMap.from(AssertFalse.class).set("message", "bar"))));
    }

    @Test
    public void shouldMatchAssertTrueAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasAssertTrueAnnotation("assertTrueAnnotation", AnnotationMap.from(AssertTrue.class).set("message", "bar")));
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
    public void shouldNotMatchCreditCardNumberAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasCreditCardNumberAnnotation("creditCardNumberAnnotation", AnnotationMap.from(CreditCardNumber.class).set("message", "quix"))));
    }

    @Test
    public void shouldMatchDecimalMaxAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasDecimalMaxAnnotation("decimalMaxAnnotation", AnnotationMap.from(DecimalMax.class).set("value", "42.24")));
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
    public void shouldNotMatchDecimalMinAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasDecimalMinAnnotation("decimalMinAnnotation", AnnotationMap.from(DecimalMin.class).set("value", "42.24"))));
    }

    @Test
    public void shouldMatchDigitsAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasDigitsAnnotation("digitsAnnotation", AnnotationMap.from(Digits.class).set("integer", 42).set("fraction", 24)));
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
    public void shouldNotMatchEanAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasEanAnnotation("eanAnnotation", AnnotationMap.from(EAN.class).set("message", "foo"))));
    }

    @Test
    public void shouldMatchEmailAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasEmailAnnotation("emailAnnotation", AnnotationMap.from(Email.class).set("message", "foo")));
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
    public void shouldNotMatchFutureAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasFutureAnnotation("futureAnnotation", AnnotationMap.from(Future.class).set("message", "baz"))));
    }

    @Test
    public void shouldMatchLengthAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasLengthAnnotation("lengthAnnotation", AnnotationMap.from(Length.class).set("max", 42)));
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
    public void shouldMatchMinAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasMinAnnotation("minAnnotation", AnnotationMap.from(Min.class).set("value", 24L)));
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
    public void shouldNotMatchMod10CheckAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasMod10CheckAnnotation("mod10CheckAnnotation", AnnotationMap.from(Mod10Check.class).set("checkDigitIndex", 24))));
    }

    @Test
    public void shouldMatchMod11CheckAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasMod11CheckAnnotation("mod11CheckAnnotation", AnnotationMap.from(Mod11Check.class).set("checkDigitIndex", 24)));
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
    public void shouldNotMatchNotBlankAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasNotBlankAnnotation("notBlankAnnotation", AnnotationMap.from(NotBlank.class).set("message", "quix"))));
    }

    @Test
    public void shouldMatchNotEmptyAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasNotEmptyAnnotation("notEmptyAnnotation", AnnotationMap.from(NotEmpty.class).set("message", "quix")));
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
    public void shouldNotMatchNotNullAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasNotNullAnnotation("notNullAnnotation", AnnotationMap.from(NotNull.class).set("message", "bar"))));
    }

    @Test
    public void shouldMatchPastAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasPastAnnotation("pastAnnotation", AnnotationMap.from(Past.class).set("message", "bar")));
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
    public void shouldNotMatchPatternAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasPatternAnnotation("patternAnnotation", AnnotationMap.from(Pattern.class).set("message", "quix"))));
    }

    @Test
    public void shouldMatchRangeAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasRangeAnnotation("rangeAnnotation", AnnotationMap.from(Range.class).set("min", 24L).set("max", 42L)));
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
    public void shouldNotMatchSafeHtmlAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasSafeHtmlAnnotation("safeHtmlAnnotation", AnnotationMap.from(SafeHtml.class).set("whitelistType", WhiteListType.NONE))));
    }

    @Test
    public void shouldMatchScriptAssertAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasScriptAssertAnnotation(AnnotationMap.from(ScriptAssert.class).set("lang", "foo").set("script", "bar")));
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
    public void shouldNotMatchSizeAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasSizeAnnotation("sizeAnnotation", AnnotationMap.from(Size.class).set("max", 24))));
    }

    @Test
    public void shouldMatchUrlAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, hasUrlAnnotation("urlAnnotation", AnnotationMap.from(URL.class).set("protocol", "quix")));
    }

    @Test
    public void shouldNotMatchUrlAnnotation() throws Exception {
        assertThat(ValidationMatchersTest.class, not(hasUrlAnnotation("urlAnnotation", AnnotationMap.from(URL.class).set("protocol", "foo"))));
    }

}
