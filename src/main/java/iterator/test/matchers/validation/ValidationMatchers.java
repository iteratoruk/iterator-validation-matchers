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

import iterator.test.matchers.type.annotation.AnnotationMap;
import iterator.test.matchers.type.annotation.FieldAnnotationMatcher;
import iterator.test.matchers.type.annotation.TypeAnnotationMatcher;
import java.lang.annotation.Annotation;
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
import org.hamcrest.Matcher;
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

public final class ValidationMatchers {

  private static final String VALUE = "value";
  private static final String START_INDEX = "startIndex";
  private static final String END_INDEX = "endIndex";
  private static final String CHECK_DIGIT_INDEX = "checkDigitIndex";
  private static final String IGNORE_NON_DIGIT_CHARACTERS = "ignoreNonDigitCharacters";
  private static final String THRESHOLD = "threshold";
  private static final String TREAT_CHECK_10_AS = "treatCheck10As";
  private static final String PROTOCOL = "protocol";

  public static <T> Matcher<Class<T>> hasAssertFalseAnnotation(String fieldName) {
    return hasAssertFalseAnnotation(fieldName, AnnotationMap.from(AssertFalse.class));
  }

  public static <T> Matcher<Class<T>> hasAssertFalseAnnotation(
      String fieldName, AnnotationMap<AssertFalse> assertFalseAnnotation) {
    return hasFieldAnnotation(fieldName, assertFalseAnnotation);
  }

  public static <T> Matcher<Class<T>> hasAssertTrueAnnotation(String fieldName) {
    return hasAssertTrueAnnotation(fieldName, AnnotationMap.from(AssertTrue.class));
  }

  public static <T> Matcher<Class<T>> hasAssertTrueAnnotation(
      String fieldName, AnnotationMap<AssertTrue> assertTrueAnnotation) {
    return hasFieldAnnotation(fieldName, assertTrueAnnotation);
  }

  public static <T> Matcher<Class<T>> hasCreditCardNumberAnnotation(String fieldName) {
    return hasCreditCardNumberAnnotation(fieldName, AnnotationMap.from(CreditCardNumber.class));
  }

  public static <T> Matcher<Class<T>> hasCreditCardNumberAnnotation(
      String fieldName, AnnotationMap<CreditCardNumber> creditCardNumberAnnotation) {
    return hasFieldAnnotation(fieldName, creditCardNumberAnnotation);
  }

  public static <T> Matcher<Class<T>> hasDecimalMaxAnnotation(String fieldName, String value) {
    return hasDecimalMaxAnnotation(
        fieldName, AnnotationMap.from(DecimalMax.class).set(VALUE, value));
  }

  public static <T> Matcher<Class<T>> hasDecimalMaxAnnotation(
      String fieldName, String value, boolean inclusive) {
    return hasDecimalMaxAnnotation(
        fieldName,
        AnnotationMap.from(DecimalMax.class).set(VALUE, value).set("inclusive", inclusive));
  }

  public static <T> Matcher<Class<T>> hasDecimalMaxAnnotation(
      String fieldName, AnnotationMap<DecimalMax> decimalMaxAnnotation) {
    return hasFieldAnnotation(fieldName, decimalMaxAnnotation);
  }

  public static <T> Matcher<Class<T>> hasDecimalMinAnnotation(String fieldName, String value) {
    return hasDecimalMinAnnotation(
        fieldName, AnnotationMap.from(DecimalMin.class).set(VALUE, value));
  }

  public static <T> Matcher<Class<T>> hasDecimalMinAnnotation(
      String fieldName, String value, boolean inclusive) {
    return hasDecimalMinAnnotation(
        fieldName,
        AnnotationMap.from(DecimalMin.class).set(VALUE, value).set("inclusive", inclusive));
  }

  public static <T> Matcher<Class<T>> hasDecimalMinAnnotation(
      String fieldName, AnnotationMap<DecimalMin> decimalMinAnnotation) {
    return hasFieldAnnotation(fieldName, decimalMinAnnotation);
  }

  public static <T> Matcher<Class<T>> hasDigitsAnnotation(
      String fieldName, int integer, int fraction) {
    return hasDigitsAnnotation(
        fieldName,
        AnnotationMap.from(Digits.class).set("integer", integer).set("fraction", fraction));
  }

  public static <T> Matcher<Class<T>> hasDigitsAnnotation(
      String fieldName, AnnotationMap<Digits> digitsAnnotation) {
    return hasFieldAnnotation(fieldName, digitsAnnotation);
  }

  public static <T> Matcher<Class<T>> hasEanAnnotation(String fieldName, Type type) {
    return hasEanAnnotation(fieldName, AnnotationMap.from(EAN.class).set("type", type));
  }

  public static <T> Matcher<Class<T>> hasEanAnnotation(
      String fieldName, AnnotationMap<EAN> eanAnnotation) {
    return hasFieldAnnotation(fieldName, eanAnnotation);
  }

  public static <T> Matcher<Class<T>> hasEmailAnnotation(String fieldName) {
    return hasEmailAnnotation(fieldName, AnnotationMap.from(Email.class));
  }

  public static <T> Matcher<Class<T>> hasEmailAnnotation(
      String fieldName, AnnotationMap<Email> emailAnnotation) {
    return hasFieldAnnotation(fieldName, emailAnnotation);
  }

  public static <T> Matcher<Class<T>> hasFutureAnnotation(String fieldName) {
    return hasFutureAnnotation(fieldName, AnnotationMap.from(Future.class));
  }

  public static <T> Matcher<Class<T>> hasFutureAnnotation(
      String fieldName, AnnotationMap<Future> futureAnnotation) {
    return hasFieldAnnotation(fieldName, futureAnnotation);
  }

  public static <T> Matcher<Class<T>> hasLengthAnnotation(String fieldName, int min) {
    return hasLengthAnnotation(fieldName, AnnotationMap.from(Length.class).set("min", min));
  }

  public static <T> Matcher<Class<T>> hasLengthAnnotation(String fieldName, int min, int max) {
    return hasLengthAnnotation(
        fieldName, AnnotationMap.from(Length.class).set("min", min).set("max", max));
  }

  public static <T> Matcher<Class<T>> hasLengthAnnotation(
      String fieldName, AnnotationMap<Length> lengthAnnotation) {
    return hasFieldAnnotation(fieldName, lengthAnnotation);
  }

  public static <T> Matcher<Class<T>> hasLuhnCheckAnnotation(String fieldName, int startIndex) {
    return hasLuhnCheckAnnotation(
        fieldName, AnnotationMap.from(LuhnCheck.class).set(START_INDEX, startIndex));
  }

  public static <T> Matcher<Class<T>> hasLuhnCheckAnnotation(
      String fieldName, int startIndex, int endIndex) {
    return hasLuhnCheckAnnotation(
        fieldName,
        AnnotationMap.from(LuhnCheck.class).set(START_INDEX, startIndex).set(END_INDEX, endIndex));
  }

  public static <T> Matcher<Class<T>> hasLuhnCheckAnnotation(
      String fieldName, int startIndex, int endIndex, int checkDigitIndex) {
    return hasLuhnCheckAnnotation(
        fieldName,
        AnnotationMap.from(LuhnCheck.class)
            .set(START_INDEX, startIndex)
            .set(END_INDEX, endIndex)
            .set(CHECK_DIGIT_INDEX, checkDigitIndex));
  }

  public static <T> Matcher<Class<T>> hasLuhnCheckAnnotation(
      String fieldName,
      int startIndex,
      int endIndex,
      int checkDigitIndex,
      boolean ignoreNonDigitCharacters) {
    return hasLuhnCheckAnnotation(
        fieldName,
        AnnotationMap.from(LuhnCheck.class)
            .set(START_INDEX, startIndex)
            .set(END_INDEX, endIndex)
            .set(CHECK_DIGIT_INDEX, checkDigitIndex)
            .set(IGNORE_NON_DIGIT_CHARACTERS, ignoreNonDigitCharacters));
  }

  public static <T> Matcher<Class<T>> hasLuhnCheckAnnotation(
      String fieldName, AnnotationMap<LuhnCheck> luhnCheckAnnotation) {
    return hasFieldAnnotation(fieldName, luhnCheckAnnotation);
  }

  public static <T> Matcher<Class<T>> hasMaxAnnotation(String fieldName, long value) {
    return hasMaxAnnotation(fieldName, AnnotationMap.from(Max.class).set(VALUE, value));
  }

  public static <T> Matcher<Class<T>> hasMaxAnnotation(
      String fieldName, AnnotationMap<Max> lengthAnnotation) {
    return hasFieldAnnotation(fieldName, lengthAnnotation);
  }

  public static <T> Matcher<Class<T>> hasMinAnnotation(String fieldName, long value) {
    return hasMinAnnotation(fieldName, AnnotationMap.from(Min.class).set(VALUE, value));
  }

  public static <T> Matcher<Class<T>> hasMinAnnotation(
      String fieldName, AnnotationMap<Min> minAnnotation) {
    return hasFieldAnnotation(fieldName, minAnnotation);
  }

  public static <T> Matcher<Class<T>> hasMod10CheckAnnotation(String fieldName, int startIndex) {
    return hasMod10CheckAnnotation(
        fieldName, AnnotationMap.from(Mod10Check.class).set(START_INDEX, startIndex));
  }

  public static <T> Matcher<Class<T>> hasMod10CheckAnnotation(
      String fieldName, int startIndex, int endIndex) {
    return hasMod10CheckAnnotation(
        fieldName,
        AnnotationMap.from(Mod10Check.class).set(START_INDEX, startIndex).set(END_INDEX, endIndex));
  }

  public static <T> Matcher<Class<T>> hasMod10CheckAnnotation(
      String fieldName, int startIndex, int endIndex, int checkDigitIndex) {
    return hasMod10CheckAnnotation(
        fieldName,
        AnnotationMap.from(Mod10Check.class)
            .set(START_INDEX, startIndex)
            .set(END_INDEX, endIndex)
            .set(CHECK_DIGIT_INDEX, checkDigitIndex));
  }

  public static <T> Matcher<Class<T>> hasMod10CheckAnnotation(
      String fieldName,
      int startIndex,
      int endIndex,
      int checkDigitIndex,
      boolean ignoreNonDigitCharacters) {
    return hasMod10CheckAnnotation(
        fieldName,
        AnnotationMap.from(Mod10Check.class)
            .set(START_INDEX, startIndex)
            .set(END_INDEX, endIndex)
            .set(CHECK_DIGIT_INDEX, checkDigitIndex)
            .set(IGNORE_NON_DIGIT_CHARACTERS, ignoreNonDigitCharacters));
  }

  public static <T> Matcher<Class<T>> hasMod10CheckAnnotation(
      String fieldName,
      int startIndex,
      int endIndex,
      int checkDigitIndex,
      boolean ignoreNonDigitCharacters,
      int multiplier) {
    return hasMod10CheckAnnotation(
        fieldName,
        AnnotationMap.from(Mod10Check.class)
            .set(START_INDEX, startIndex)
            .set(END_INDEX, endIndex)
            .set(CHECK_DIGIT_INDEX, checkDigitIndex)
            .set(IGNORE_NON_DIGIT_CHARACTERS, ignoreNonDigitCharacters)
            .set("multiplier", multiplier));
  }

  public static <T> Matcher<Class<T>> hasMod10CheckAnnotation(
      String fieldName,
      int startIndex,
      int endIndex,
      int checkDigitIndex,
      boolean ignoreNonDigitCharacters,
      int multiplier,
      int weight) {
    return hasMod10CheckAnnotation(
        fieldName,
        AnnotationMap.from(Mod10Check.class)
            .set(START_INDEX, startIndex)
            .set(END_INDEX, endIndex)
            .set(CHECK_DIGIT_INDEX, checkDigitIndex)
            .set(IGNORE_NON_DIGIT_CHARACTERS, ignoreNonDigitCharacters)
            .set("multiplier", multiplier)
            .set("weight", weight));
  }

  public static <T> Matcher<Class<T>> hasMod10CheckAnnotation(
      String fieldName, AnnotationMap<Mod10Check> mod10CheckAnnotation) {
    return hasFieldAnnotation(fieldName, mod10CheckAnnotation);
  }

  public static <T> Matcher<Class<T>> hasMod11CheckAnnotation(String fieldName, int startIndex) {
    return hasMod11CheckAnnotation(
        fieldName, AnnotationMap.from(Mod11Check.class).set(START_INDEX, startIndex));
  }

  public static <T> Matcher<Class<T>> hasMod11CheckAnnotation(
      String fieldName, int startIndex, int endIndex) {
    return hasMod11CheckAnnotation(
        fieldName,
        AnnotationMap.from(Mod11Check.class).set(START_INDEX, startIndex).set(END_INDEX, endIndex));
  }

  public static <T> Matcher<Class<T>> hasMod11CheckAnnotation(
      String fieldName, int startIndex, int endIndex, int checkDigitIndex) {
    return hasMod11CheckAnnotation(
        fieldName,
        AnnotationMap.from(Mod11Check.class)
            .set(START_INDEX, startIndex)
            .set(END_INDEX, endIndex)
            .set(CHECK_DIGIT_INDEX, checkDigitIndex));
  }

  public static <T> Matcher<Class<T>> hasMod11CheckAnnotation(
      String fieldName,
      int startIndex,
      int endIndex,
      int checkDigitIndex,
      boolean ignoreNonDigitCharacters) {
    return hasMod11CheckAnnotation(
        fieldName,
        AnnotationMap.from(Mod11Check.class)
            .set(START_INDEX, startIndex)
            .set(END_INDEX, endIndex)
            .set(CHECK_DIGIT_INDEX, checkDigitIndex)
            .set(IGNORE_NON_DIGIT_CHARACTERS, ignoreNonDigitCharacters));
  }

  public static <T> Matcher<Class<T>> hasMod11CheckAnnotation(
      String fieldName,
      int startIndex,
      int endIndex,
      int checkDigitIndex,
      boolean ignoreNonDigitCharacters,
      int threshold) {
    return hasMod11CheckAnnotation(
        fieldName,
        AnnotationMap.from(Mod11Check.class)
            .set(START_INDEX, startIndex)
            .set(END_INDEX, endIndex)
            .set(CHECK_DIGIT_INDEX, checkDigitIndex)
            .set(IGNORE_NON_DIGIT_CHARACTERS, ignoreNonDigitCharacters)
            .set(THRESHOLD, threshold));
  }

  public static <T> Matcher<Class<T>> hasMod11CheckAnnotation(
      String fieldName,
      int startIndex,
      int endIndex,
      int checkDigitIndex,
      boolean ignoreNonDigitCharacters,
      int threshold,
      char treatCheck10As) {
    return hasMod11CheckAnnotation(
        fieldName,
        AnnotationMap.from(Mod11Check.class)
            .set(START_INDEX, startIndex)
            .set(END_INDEX, endIndex)
            .set(CHECK_DIGIT_INDEX, checkDigitIndex)
            .set(IGNORE_NON_DIGIT_CHARACTERS, ignoreNonDigitCharacters)
            .set(THRESHOLD, threshold)
            .set(TREAT_CHECK_10_AS, treatCheck10As));
  }

  public static <T> Matcher<Class<T>> hasMod11CheckAnnotation(
      String fieldName,
      int startIndex,
      int endIndex,
      int checkDigitIndex,
      boolean ignoreNonDigitCharacters,
      int threshold,
      char treatCheck10As,
      char treatCheck11As) {
    return hasMod11CheckAnnotation(
        fieldName,
        AnnotationMap.from(Mod11Check.class)
            .set(START_INDEX, startIndex)
            .set(END_INDEX, endIndex)
            .set(CHECK_DIGIT_INDEX, checkDigitIndex)
            .set(IGNORE_NON_DIGIT_CHARACTERS, ignoreNonDigitCharacters)
            .set(THRESHOLD, threshold)
            .set(TREAT_CHECK_10_AS, treatCheck10As)
            .set("treatCheck11As", treatCheck11As));
  }

  public static <T> Matcher<Class<T>> hasMod11CheckAnnotation(
      String fieldName,
      int startIndex,
      int endIndex,
      int checkDigitIndex,
      boolean ignoreNonDigitCharacters,
      int threshold,
      char treatCheck10As,
      char treatCheck11As,
      ProcessingDirection processingDirection) {
    return hasMod11CheckAnnotation(
        fieldName,
        AnnotationMap.from(Mod11Check.class)
            .set(START_INDEX, startIndex)
            .set(END_INDEX, endIndex)
            .set(CHECK_DIGIT_INDEX, checkDigitIndex)
            .set(IGNORE_NON_DIGIT_CHARACTERS, ignoreNonDigitCharacters)
            .set(THRESHOLD, threshold)
            .set(TREAT_CHECK_10_AS, treatCheck10As)
            .set("treatCheck11As", treatCheck11As)
            .set("processingDirection", processingDirection));
  }

  public static <T> Matcher<Class<T>> hasMod11CheckAnnotation(
      String fieldName, AnnotationMap<Mod11Check> mod11CheckAnnotation) {
    return hasFieldAnnotation(fieldName, mod11CheckAnnotation);
  }

  public static <T> Matcher<Class<T>> hasNotBlankAnnotation(String fieldName) {
    return hasNotBlankAnnotation(fieldName, AnnotationMap.from(NotBlank.class));
  }

  public static <T> Matcher<Class<T>> hasNotBlankAnnotation(
      String fieldName, AnnotationMap<NotBlank> notBlankAnnotation) {
    return hasFieldAnnotation(fieldName, notBlankAnnotation);
  }

  public static <T> Matcher<Class<T>> hasNotEmptyAnnotation(String fieldName) {
    return hasNotEmptyAnnotation(fieldName, AnnotationMap.from(NotEmpty.class));
  }

  public static <T> Matcher<Class<T>> hasNotEmptyAnnotation(
      String fieldName, AnnotationMap<NotEmpty> notEmptyAnnotation) {
    return hasFieldAnnotation(fieldName, notEmptyAnnotation);
  }

  public static <T> Matcher<Class<T>> hasNotNullAnnotation(String fieldName) {
    return hasNotNullAnnotation(fieldName, AnnotationMap.from(NotNull.class));
  }

  public static <T> Matcher<Class<T>> hasNotNullAnnotation(
      String fieldName, AnnotationMap<NotNull> notNullAnnotation) {
    return hasFieldAnnotation(fieldName, notNullAnnotation);
  }

  public static <T> Matcher<Class<T>> hasPastAnnotation(String fieldName) {
    return hasPastAnnotation(fieldName, AnnotationMap.from(Past.class));
  }

  public static <T> Matcher<Class<T>> hasPastAnnotation(
      String fieldName, AnnotationMap<Past> pastAnnotation) {
    return hasFieldAnnotation(fieldName, pastAnnotation);
  }

  public static <T> Matcher<Class<T>> hasPatternAnnotation(String fieldName, String regexp) {
    return hasPatternAnnotation(fieldName, AnnotationMap.from(Pattern.class).set("regexp", regexp));
  }

  public static <T> Matcher<Class<T>> hasPatternAnnotation(
      String fieldName, String regexp, Flag... flags) {
    return hasPatternAnnotation(
        fieldName, AnnotationMap.from(Pattern.class).set("regexp", regexp).set("flags", flags));
  }

  public static <T> Matcher<Class<T>> hasPatternAnnotation(
      String fieldName, AnnotationMap<Pattern> patternAnnotation) {
    return hasFieldAnnotation(fieldName, patternAnnotation);
  }

  public static <T> Matcher<Class<T>> hasRangeAnnotation(String fieldName, long min) {
    return hasRangeAnnotation(fieldName, AnnotationMap.from(Range.class).set("min", min));
  }

  public static <T> Matcher<Class<T>> hasRangeAnnotation(String fieldName, long min, long max) {
    return hasRangeAnnotation(
        fieldName, AnnotationMap.from(Range.class).set("min", min).set("max", max));
  }

  public static <T> Matcher<Class<T>> hasRangeAnnotation(
      String fieldName, AnnotationMap<Range> rangeAnnotation) {
    return hasFieldAnnotation(fieldName, rangeAnnotation);
  }

  public static <T> Matcher<Class<T>> hasSafeHtmlAnnotation(
      String fieldName, WhiteListType whitelistType) {
    return hasSafeHtmlAnnotation(
        fieldName, AnnotationMap.from(SafeHtml.class).set("whitelistType", whitelistType));
  }

  public static <T> Matcher<Class<T>> hasSafeHtmlAnnotation(
      String fieldName, WhiteListType whitelistType, String... additionalTags) {
    return hasSafeHtmlAnnotation(
        fieldName,
        AnnotationMap.from(SafeHtml.class)
            .set("whitelistType", whitelistType)
            .set("additionalTags", additionalTags));
  }

  public static <T> Matcher<Class<T>> hasSafeHtmlAnnotation(
      String fieldName, AnnotationMap<SafeHtml> safeHtmlAnnotation) {
    return hasFieldAnnotation(fieldName, safeHtmlAnnotation);
  }

  public static <T> Matcher<Class<T>> hasScriptAssertAnnotation(String script, String lang) {
    return hasScriptAssertAnnotation(
        AnnotationMap.from(ScriptAssert.class).set("script", script).set("lang", lang));
  }

  public static <T> Matcher<Class<T>> hasScriptAssertAnnotation(
      String script, String lang, String alias) {
    return hasScriptAssertAnnotation(
        AnnotationMap.from(ScriptAssert.class)
            .set("script", script)
            .set("lang", lang)
            .set("alias", alias));
  }

  public static <T> Matcher<Class<T>> hasScriptAssertAnnotation(
      AnnotationMap<ScriptAssert> scriptAssertAnnotation) {
    return hasTypeAnnotation(scriptAssertAnnotation);
  }

  public static <T> Matcher<Class<T>> hasSizeAnnotation(String fieldName, int min) {
    return hasSizeAnnotation(fieldName, AnnotationMap.from(Size.class).set("min", min));
  }

  public static <T> Matcher<Class<T>> hasSizeAnnotation(String fieldName, int min, int max) {
    return hasSizeAnnotation(
        fieldName, AnnotationMap.from(Size.class).set("min", min).set("max", max));
  }

  public static <T> Matcher<Class<T>> hasSizeAnnotation(
      String fieldName, AnnotationMap<Size> sizeAnnotation) {
    return hasFieldAnnotation(fieldName, sizeAnnotation);
  }

  public static <T> Matcher<Class<T>> hasUrlAnnotation(String fieldName, String protocol) {
    return hasUrlAnnotation(fieldName, AnnotationMap.from(URL.class).set(PROTOCOL, protocol));
  }

  public static <T> Matcher<Class<T>> hasUrlAnnotation(
      String fieldName, String protocol, String host) {
    return hasUrlAnnotation(
        fieldName, AnnotationMap.from(URL.class).set(PROTOCOL, protocol).set("host", host));
  }

  public static <T> Matcher<Class<T>> hasUrlAnnotation(
      String fieldName, String protocol, String host, int port) {
    return hasUrlAnnotation(
        fieldName,
        AnnotationMap.from(URL.class).set(PROTOCOL, protocol).set("host", host).set("port", port));
  }

  public static <T> Matcher<Class<T>> hasUrlAnnotation(
      String fieldName, AnnotationMap<URL> urlAnnotation) {
    return hasFieldAnnotation(fieldName, urlAnnotation);
  }

  private static <A extends Annotation, T> Matcher<Class<T>> hasFieldAnnotation(
      String fieldName, AnnotationMap<A> annotationMap) {
    return new FieldAnnotationMatcher<>(fieldName, annotationMap);
  }

  private static <A extends Annotation, T> Matcher<Class<T>> hasTypeAnnotation(
      AnnotationMap<A> annotationMap) {
    return new TypeAnnotationMatcher<>(annotationMap);
  }

  private ValidationMatchers() {
    throw new IllegalStateException();
  }
}
