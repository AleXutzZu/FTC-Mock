package ro.eminescusm.pm.mock.external.util;

import java.nio.ByteOrder;

public class TypeConversion {
    /**
     * Converts a byte array to an int.
     * Big endian is assumed.
     *
     * @param byteArray the byte array to convert
     * @return the converted int
     */
    static int byteArrayToInt(byte[] byteArray) {
        int value = 0;
        for (byte b : byteArray) {
            value = (value << 8) + (b & 0xff);
        }
        return value;
    }

    /**
     * Converts a byte array to an int.
     *
     * @param byteArray the byte array to convert
     * @param byteOrder the byte order to use
     * @return the converted int
     */
    static int byteArrayToInt(byte[] byteArray, ByteOrder byteOrder) {
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            return byteArrayToInt(byteArray);
        } else {
            byte[] reversed = getReversed(byteArray);
            return byteArrayToInt(reversed);
        }
    }

    private static byte[] getReversed(byte[] byteArray) {
        byte[] reversed = new byte[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            reversed[i] = byteArray[byteArray.length - i - 1];
        }
        return reversed;
    }

    /**
     * Convert a byte array into a long value. Big endian is assumed.
     *
     * @param byteArray the byte array to convert
     * @return the converted long value
     */
    static long byteArrayToLong(byte[] byteArray) {
        long value = 0;
        for (byte b : byteArray) {
            value = (value << 8) + (b & 0xff);
        }
        return value;
    }

    /**
     * Convert a byte array into a long value.
     *
     * @param byteArray the byte array to convert
     * @param byteOrder the byte order to use
     * @return the converted long value
     */
    static long byteArrayToLong(byte[] byteArray, ByteOrder byteOrder) {
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            return byteArrayToLong(byteArray);
        } else {
            byte[] reversed = getReversed(byteArray);
            return byteArrayToLong(reversed);
        }
    }

    /**
     * Convert a byte array into a short value. Big endian is assumed.
     *
     * @param byteArray the byte array to convert
     * @return the converted short value
     */
    static short byteArrayToShort(byte[] byteArray) {
        short value = 0;
        for (byte b : byteArray) {
            value = (short) ((value << 8) + (b & 0xff));
        }
        return value;
    }

    /**
     * Convert a byte array into a short value.
     *
     * @param byteArray the byte array to convert
     * @param byteOrder the byte order to use
     * @return the converted short value
     */
    static short byteArrayToShort(byte[] byteArray, ByteOrder byteOrder) {
        if (byteOrder == ByteOrder.BIG_ENDIAN) {
            return byteArrayToShort(byteArray);
        } else {
            byte[] reversed = getReversed(byteArray);
            return byteArrayToShort(reversed);
        }
    }

    static boolean toBoolean(Boolean value) {
        return value != null && value;
    }

    static boolean toBoolean(Boolean value, boolean defaultValue) {
        return value != null ? value : defaultValue;
    }

    static String utf8ToString(byte[] ut8String) {
        return new String(ut8String);
    }

    static byte[] stringToUtf8(String string) {
        return string.getBytes();
    }
}
