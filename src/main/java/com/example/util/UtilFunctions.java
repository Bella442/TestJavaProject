package com.example.util;

import java.lang.reflect.Field;

public class UtilFunctions {

public static String[] getNullPropertyNames(Object source) {
    return java.util.Arrays.stream(source.getClass().getDeclaredFields())
            .filter(field -> {
                field.setAccessible(true);
                try {
                    return field.get(source) == null;
                } catch (IllegalAccessException e) {
                    return false;
                }
            })
            .map(Field::getName)
            .toArray(String[]::new);
}
}
