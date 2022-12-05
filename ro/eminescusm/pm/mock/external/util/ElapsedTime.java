package ro.eminescusm.pm.mock.external.util;

import java.util.concurrent.TimeUnit;

public class ElapsedTime {

    @Override
    public String toString() {
        //return the elapsed time in seconds
        return String.format("%.2f", this.seconds());
    }

    public enum Resolution {
        MILLISECONDS,
        SECONDS,
    }


    /**
     * The number of milliseconds in a second.
     */
    public static long MILLIS_IN_NANO = 1000000;

    /**
     * The number of nanoseconds in a second.
     */
    public static long SECOND_IN_NANO = 1000000000;

    protected final long nsStartTime;
    protected final double resolution;

    private long lastReset = 0;

    /**
     * Creates a timer with {@link ElapsedTime.Resolution#SECONDS} resolution.
     */
    public ElapsedTime() {
        this(Resolution.SECONDS);
    }

    public ElapsedTime(Resolution resolution) {
        nsStartTime = 0;

        switch (resolution) {
            case MILLISECONDS:
                this.resolution = MILLIS_IN_NANO;
                break;
            case SECONDS:
                this.resolution = SECOND_IN_NANO;
                break;
            default:
                throw new IllegalArgumentException("Invalid resolution");
        }
    }

    public ElapsedTime(long startTime) {
        //convert startTime from seconds to nanoseconds
        nsStartTime = startTime * SECOND_IN_NANO;
        resolution = SECOND_IN_NANO;
    }

    public Resolution getResolution() {
        if (resolution == MILLIS_IN_NANO) {
            return Resolution.MILLISECONDS;
        } else {
            return Resolution.SECONDS;
        }
    }

    /**
     * Resets the internal state of the timer to reflect the current time
     */
    public void reset() {
        lastReset = System.nanoTime();
    }

    /**
     * Log a message stating how long the timer has been running.
     *
     * @param label the message to log
     */
    public void log(String label) {
        System.out.println(label + ": " + this);
    }

    /**
     * Returns the duration that has elapsed since the timer was last reset in the resolution of the timer.
     *
     * @return the duration that has elapsed since the timer was last reset.
     */
    public double time() {
        return (System.nanoTime() - lastReset) / resolution;
    }

    /**
     * Returns the duration that has elapsed since the timer was last reset.
     *
     * @return the duration that has elapsed since the timer was last reset.
     */
    public long time(TimeUnit unit) {
        return unit.convert(System.nanoTime() - lastReset, TimeUnit.NANOSECONDS);
    }

    /**
     * Returns the duration that has elapsed since the timer was last reset (in seconds).
     *
     * @return the duration that has elapsed since the timer was last reset (in seconds).
     */
    public double seconds() {
        //Convert the last reset time to seconds
        return (double) (System.nanoTime() - lastReset) / SECOND_IN_NANO;
    }

    /**
     * Returns the duration that has elapsed since the timer was last reset (in milliseconds).
     *
     * @return the duration that has elapsed since the timer was last reset (in milliseconds).
     */
    public double milliseconds() {
        //Convert the last reset time to milliseconds
        return (double) (System.nanoTime() - lastReset) / MILLIS_IN_NANO;
    }

    /**
     * Returns the duration that has elapsed since the timer was last reset (in nanoseconds).
     *
     * @return the duration that has elapsed since the timer was last reset (in nanoseconds).
     */
    public long nanoseconds() {
        return System.nanoTime() - lastReset;
    }

    /**
     * Returns the current time on the clock used by the timer
     *
     * @param unit the unit of time to return
     * @return the current time on the clock used by the timer
     */
    public long now(TimeUnit unit) {
        return unit.convert(System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    /**
     * Returns, in resolution-dependent units, the time at which this timer was last reset
     *
     * @return the time at which this timer was last reset
     */
    public double startTime() {
        return (double) lastReset / resolution;
    }

    /**
     * Returns the time at which this timer was last reset in nanoseconds
     *
     * @return the time at which this timer was last reset in nanoseconds
     */
    public long startTimeNanoseconds() {
        return lastReset;
    }
}