package com.example.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;

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

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
