package ua.nure.tur.utils;

public final class ClosingUtils {
    private ClosingUtils() {
    }

    public static void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                //TODO: log exceptions
            }
        }
    }


}
