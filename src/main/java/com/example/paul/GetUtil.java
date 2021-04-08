package com.example.paul;

import java.lang.reflect.Field;

public class GetUtil {

    /**
     * Meant to mimic the behavior of the Lodash get() function. (https://lodash.com/docs/4.15.0#get)
     * <p>
     * Conventional Java 7 assignment of nested variable with null checking:
     * <p>
     * Object o = null;
     * if (a != null && a.b != null && a.b.c != null && a.b.c.d != null) {
     * o = a.b.c;
     * }
     * ...
     * <p>
     * With this method:
     * Object o = get(a, "b.c.d");
     *
     * Todo... Add support for arrays
     *
     * @param object object that contains indirect or direct reference to a variable we want to retrieve
     * @param path   the path of the variable within the object
     * @return value
     */
    public static <T> T get(Object object, String path) {
        String[] arr = path.split("\\.");
        for (String property : arr) {
            try {
                Field f = object.getClass().getDeclaredField(property);
                var b = f.canAccess(object);
                if (!b) f.setAccessible(true);
                object = f.get(object);
                if (!b) f.setAccessible(false);
            } catch (Exception e) {
                return null;
            }
        }

        if (object != null)
            // noinspection unchecked
            return (T) object;
        else
            return null;
    }
}
