package ro.eminescusm.pm.mock.external.util;

/**
 * Utility class for performing range operations.
 */
public class Ranges {
    public static byte clip(byte value, byte min, byte max) {
        return (byte) clip(value, min, (int) max);
    }

    public static short clip(short value, short min, short max) {
        return (short) clip(value, min, (int) max);
    }

    public static int clip(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    public static long clip(long value, long min, long max) {
        return Math.max(min, Math.min(max, value));
    }

    public static float clip(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    public static double clip(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    public static void throwIfRangeIsInvalid(double number, double min, double max) {
        if (min > max) {
            throw new IllegalArgumentException("Min value cannot be greater than max value");
        }
        if (number < min || number > max) {
            throw new IllegalArgumentException("Number is not in range");
        }
    }

    public static void throwIfRangeIsInvalid(int number, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min value cannot be greater than max value");
        }
        if (number < min || number > max) {
            throw new IllegalArgumentException("Number is not in range");
        }
    }

    public static double scale(double n, double x1, double x2, double y1, double y2) {
        return y1 + (n - x1) * (y2 - y1) / (x2 - x1);
    }
}
