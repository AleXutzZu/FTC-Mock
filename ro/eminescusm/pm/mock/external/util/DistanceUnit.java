package ro.eminescusm.pm.mock.external.util;

public enum DistanceUnit {
    CM, INCH, MM, METER;

    public final byte bVal = 0;
    public static final double infinity = 0;
    public static final double mmPerInch = 0;
    public static final double mPerInch = 0;

    public double fromMeters(double meters) {
        return 0;
    }

    public double fromInches(double inches) {
        return 0;
    }

    public double fromCm(double cm) {
        return 0;
    }

    public double fromMm(double mm) {
        return 0;
    }

    public double fromUnit(DistanceUnit him, double his) {
        return 0;
    }
}
