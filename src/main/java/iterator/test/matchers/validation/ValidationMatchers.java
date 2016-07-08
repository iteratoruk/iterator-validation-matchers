/**
 * Copyright (C) 2016 Iterator Ltd. (iteratoruk@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package iterator.test.matchers.validation;

import java.lang.annotation.Annotation;

import javax.validation.constraints.*;

import org.hamcrest.Matcher;
import org.hibernate.validator.constraints.*;

import iterator.test.matchers.type.annotation.AnnotationMap;
import iterator.test.matchers.type.annotation.FieldAnnotationMatcher;
import iterator.test.matchers.type.annotation.TypeAnnotationMatcher;

public final class ValidationMatchers {

    public static <T> Matcher<Class<T>> hasAssertFalseAnnotation(String fieldName, AnnotationMap<AssertFalse> assertFalseAnnotation) {
        return hasFieldAnnotation(fieldName, assertFalseAnnotation);
    }

    public static <T> Matcher<Class<T>> hasAssertTrueAnnotation(String fieldName, AnnotationMap<AssertTrue> assertTrueAnnotation) {
        return hasFieldAnnotation(fieldName, assertTrueAnnotation);
    }

    public static <T> Matcher<Class<T>> hasCreditCardNumberAnnotation(String fieldName, AnnotationMap<CreditCardNumber> creditCardNumberAnnotation) {
        return hasFieldAnnotation(fieldName, creditCardNumberAnnotation);
    }

    public static <T> Matcher<Class<T>> hasDecimalMaxAnnotation(String fieldName, AnnotationMap<DecimalMax> decimalMaxAnnotation) {
        return hasFieldAnnotation(fieldName, decimalMaxAnnotation);
    }

    public static <T> Matcher<Class<T>> hasDecimalMinAnnotation(String fieldName, AnnotationMap<DecimalMin> decimalMinAnnotation) {
        return hasFieldAnnotation(fieldName, decimalMinAnnotation);
    }

    public static <T> Matcher<Class<T>> hasDigitsAnnotation(String fieldName, AnnotationMap<Digits> digitsAnnotation) {
        return hasFieldAnnotation(fieldName, digitsAnnotation);
    }

    public static <T> Matcher<Class<T>> hasEanAnnotation(String fieldName, AnnotationMap<EAN> eanAnnotation) {
        return hasFieldAnnotation(fieldName, eanAnnotation);
    }

    public static <T> Matcher<Class<T>> hasEmailAnnotation(String fieldName, AnnotationMap<Email> emailAnnotation) {
        return hasFieldAnnotation(fieldName, emailAnnotation);
    }

    public static <T> Matcher<Class<T>> hasFutureAnnotation(String fieldName, AnnotationMap<Future> futureAnnotation) {
        return hasFieldAnnotation(fieldName, futureAnnotation);
    }

    public static <T> Matcher<Class<T>> hasLengthAnnotation(String fieldName, AnnotationMap<Length> lengthAnnotation) {
        return hasFieldAnnotation(fieldName, lengthAnnotation);
    }

    public static <T> Matcher<Class<T>> hasLuhnCheckAnnotation(String fieldName, AnnotationMap<LuhnCheck> luhnCheckAnnotation) {
        return hasFieldAnnotation(fieldName, luhnCheckAnnotation);
    }

    public static <T> Matcher<Class<T>> hasMaxAnnotation(String fieldName, AnnotationMap<Max> lengthAnnotation) {
        return hasFieldAnnotation(fieldName, lengthAnnotation);
    }

    public static <T> Matcher<Class<T>> hasMinAnnotation(String fieldName, AnnotationMap<Min> minAnnotation) {
        return hasFieldAnnotation(fieldName, minAnnotation);
    }

    public static <T> Matcher<Class<T>> hasMod10CheckAnnotation(String fieldName, AnnotationMap<Mod10Check> mod10CheckAnnotation) {
        return hasFieldAnnotation(fieldName, mod10CheckAnnotation);
    }

    public static <T> Matcher<Class<T>> hasMod11CheckAnnotation(String fieldName, AnnotationMap<Mod11Check> mod11CheckAnnotation) {
        return hasFieldAnnotation(fieldName, mod11CheckAnnotation);
    }

    public static <T> Matcher<Class<T>> hasNotBlankAnnotation(String fieldName, AnnotationMap<NotBlank> notBlankAnnotation) {
        return hasFieldAnnotation(fieldName, notBlankAnnotation);
    }

    public static <T> Matcher<Class<T>> hasNotEmptyAnnotation(String fieldName, AnnotationMap<NotEmpty> notEmptyAnnotation) {
        return hasFieldAnnotation(fieldName, notEmptyAnnotation);
    }

    public static <T> Matcher<Class<T>> hasNotNullAnnotation(String fieldName, AnnotationMap<NotNull> notNullAnnotation) {
        return hasFieldAnnotation(fieldName, notNullAnnotation);
    }

    public static <T> Matcher<Class<T>> hasPastAnnotation(String fieldName, AnnotationMap<Past> pastAnnotation) {
        return hasFieldAnnotation(fieldName, pastAnnotation);
    }

    public static <T> Matcher<Class<T>> hasPatternAnnotation(String fieldName, AnnotationMap<Pattern> patternAnnotation) {
        return hasFieldAnnotation(fieldName, patternAnnotation);
    }

    public static <T> Matcher<Class<T>> hasRangeAnnotation(String fieldName, AnnotationMap<Range> rangeAnnotation) {
        return hasFieldAnnotation(fieldName, rangeAnnotation);
    }

    public static <T> Matcher<Class<T>> hasSafeHtmlAnnotation(String fieldName, AnnotationMap<SafeHtml> safeHtmlAnnotation) {
        return hasFieldAnnotation(fieldName, safeHtmlAnnotation);
    }

    public static <T> Matcher<Class<T>> hasScriptAssertAnnotation(AnnotationMap<ScriptAssert> scriptAssertAnnotation) {
        return hasTypeAnnotation(scriptAssertAnnotation);
    }

    public static <T> Matcher<Class<T>> hasSizeAnnotation(String fieldName, AnnotationMap<Size> sizeAnnotation) {
        return hasFieldAnnotation(fieldName, sizeAnnotation);
    }

    public static <T> Matcher<Class<T>> hasUrlAnnotation(String fieldName, AnnotationMap<URL> urlAnnotation) {
        return hasFieldAnnotation(fieldName, urlAnnotation);
    }

    private static <A extends Annotation, T> Matcher<Class<T>> hasFieldAnnotation(String fieldName, AnnotationMap<A> annotationMap) {
        return new FieldAnnotationMatcher<>(fieldName, annotationMap);
    }

    private static <A extends Annotation, T> Matcher<Class<T>> hasTypeAnnotation(AnnotationMap<A> annotationMap) {
        return new TypeAnnotationMatcher<>(annotationMap);
    }

    private ValidationMatchers() {
        throw new IllegalStateException();
    }

}
