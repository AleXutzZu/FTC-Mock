package ro.eminescusm.pm.mock.internal.telemetry;

public interface Telemetry {
    interface Item {

    }

    interface Line {

    }

    interface Log {

    }

    Object addAction(Runnable action);

    <T> Item addData(String caption, Func<T> valueProducer);

    Item addData(String caption, Object value);

    <T> Item addData(String caption, String format, Func<T> valueProducer);

    Item addData(String caption, String format, Object... args);

    Line addLine();

    Line addLine(String caption);

    void clear();

    void clearAll();

    String getCaptionValueSeparator();

    String getItemSeparator();

    int getMsTransmissionInterval();

    boolean isAutoClear();

    Log log();

    boolean removeAction(Object token);

    boolean removeItem(Item item);

    boolean removeLine(Line line);

    void setAutoClear(boolean autoClear);

    void setCaptionValueSeparator(String captionValueSeparator);

    void setItemSeparator(String itemSeparator);

    void setMsTransmissionInterval(int msTransmissionInterval);

    boolean update();
}
