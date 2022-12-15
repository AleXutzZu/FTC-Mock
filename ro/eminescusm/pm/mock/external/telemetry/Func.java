package ro.eminescusm.pm.mock.external.telemetry;

@FunctionalInterface
public interface Func<T> {
    /**
     * Returns a value.
     *
     * @return the value
     */
    T value();
}
