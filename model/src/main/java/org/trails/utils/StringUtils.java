/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.trails.utils;

import lombok.val;

import org.apache.commons.text.WordUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.splitByCharacterTypeCamelCase;

/**
 * String utility.  Provides functions to convert String tokens from one format to another, for use by generators
 * in creating class and field names, comments, etc.
 *
 * @author  Joel Schilling
 * @version 1.0, 01/25/2021
 * @since   1.0
 */
public class StringUtils {
    /**
     * Splits a string by camel case.
     *
     * @param str the string.
     * @return List split by camel case.
     */
    public static List<String> splitByCamelCase(final String str) {
        if (str == null || str.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(splitByCharacterTypeCamelCase(str));
    }

    /**
     * Converts a String token into text to be used in comments:
     *
     * i.e "numberOfBooks" becomes "number of books"
     *
     * @return String converted to comment text.
     */
    public static String toCommentText(String str) {
        String returnValue = null;
        if (str != null) {
            val split = splitByCamelCase(str).stream().
                map(String::toLowerCase).collect(Collectors.toList());

            returnValue = String.join(" ", split);
        }
        return returnValue;
    }

    /**
     * Converts a String token into pascal case:
     *
     * i.e "numberOfBooks" becomes "NumberOfBooks"
     *
     * @return String converted into pascal case.
     */
    public static String toPascalCase(String str) {
        String returnValue = null;
        if (str != null) {
            val split = splitByCamelCase(str).stream().
                map(WordUtils::capitalize).collect(Collectors.toList());

            returnValue = String.join("", split);
        }
        return returnValue;
    }

    /**
     * Converts a String token into camel case:
     *
     * i.e "NumberOfBooks" becomes "numberOfBooks"
     *
     * @return String converted into camel case.
     */
    public static String toCamelCase(String str) {
        return WordUtils.uncapitalize(toPascalCase(str));
    }
}
