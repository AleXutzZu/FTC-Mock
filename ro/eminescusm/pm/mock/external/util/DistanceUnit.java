package ro.eminescusm.pm.mock.external.util;

public enum DistanceUnit {
    CM, INCH, MM, METER;

    public final byte bVal = 0;
    public static final double infinity = 1.7976931348623157E308;
    /**
     * Millimeters per inch
     */
    public static final double mmPerInch = 25.4;

    /**
     * Meter per inch
     */
    public static final double mPerInch = 0.0254;

    public double fromMeters(double meters) {
        switch (this) {
            case CM:
                return meters * 100;
            case INCH:
                return meters / mPerInch;
            case MM:
                return meters * 1000;
            case METER:
                return meters;
            default:
                return infinity;
        }
    }

    public double fromInches(double inches) {
        switch (this) {
            case CM:
                return inches * mmPerInch;
            case INCH:
                return inches;
            case MM:
                return inches * mmPerInch * 10;
            case METER:
                return inches * mPerInch;
            default:
                return infinity;
        }
    }

    public double fromCm(double cm) {
        //From cm to the unit represented by this
        switch (this) {
            case CM:
                return cm;
            case INCH:
                return cm / 2.54;
            case MM:
                return cm * 10;
            case METER:
                return cm / 100;
            default:
                return infinity;
        }
    }

    public double fromMm(double mm) {
        //From mm to the unit represented by this
        switch (this) {
            case CM:
                return mm / 10;
            case INCH:
                return mm / 25.4;
            case MM:
                return mm;
            case METER:
                return mm / 1000;
            default:
                return infinity;
        }
    }

    public double fromUnit(DistanceUnit him, double his) {
        //From his unit to the unit represented by this
        switch (him) {
            case CM:
                return fromCm(his);
            case INCH:
                return fromInches(his);
            case MM:
                return fromMm(his);
            case METER:
                return fromMeters(his);
            default:
                return infinity;
        }
    }

    public double toCm(double inOurUnits) {
        //From the unit represented by this to cm
        switch (this) {
            case CM:
                return inOurUnits;
            case INCH:
                return inOurUnits * 2.54;
            case MM:
                return inOurUnits / 10;
            case METER:
                return inOurUnits * 100;
            default:
                return infinity;
        }
    }

    public double toMm(double inOurUnits) {
        //From the unit represented by this to mm
        switch (this) {
            case CM:
                return inOurUnits * 10;
            case INCH:
                return inOurUnits * 25.4;
            case MM:
                return inOurUnits;
            case METER:
                return inOurUnits * 1000;
            default:
                return infinity;
        }
    }

    public double toInches(double inOurUnits) {
        //From the unit represented by this to inches
        switch (this) {
            case CM:
                return inOurUnits / 2.54;
            case INCH:
                return inOurUnits;
            case MM:
                return inOurUnits / 25.4;
            case METER:
                return inOurUnits / mPerInch;
            default:
                return infinity;
        }
    }

    public double toMeters(double inOurUnits) {
        //From the unit represented by this to meters
        switch (this) {
            case CM:
                return inOurUnits / 100;
            case INCH:
                return inOurUnits * mPerInch;
            case MM:
                return inOurUnits / 1000;
            case METER:
                return inOurUnits;
            default:
                return infinity;
        }
    }

    public String toString(double inOurUnits) {
        return inOurUnits + this.toString();
    }

    public String toString() {
        switch (this) {
            case CM:
                return "cm";
            case INCH:
                return "in";
            case MM:
                return "mm";
            case METER:
                return "m";
            default:
                return "?";
        }
    }
}
