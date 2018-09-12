package ml.wonwoo.util;

public abstract class Assert {

    private Assert() {
        throw new UnsupportedOperationException();
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
