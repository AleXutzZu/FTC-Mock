package ro.eminescusm.pm.mock.internal.impl;

import ro.eminescusm.pm.mock.internal.telemetry.Func;
import ro.eminescusm.pm.mock.internal.telemetry.Telemetry;

public class TelemetryImpl implements Telemetry {

    static class ItemImpl implements Item {

    }

    static class LogImpl implements Log {

    }

    static class LineImpl implements Line {

    }

    @Override
    public Object addAction(Runnable action) {
        return null;
    }

    @Override
    public <T> ItemImpl addData(String caption, Func<T> valueProducer) {
        return null;
    }

    @Override
    public ItemImpl addData(String caption, Object value) {
        return null;
    }

    @Override
    public <T> ItemImpl addData(String caption, String format, Func<T> valueProducer) {
        return null;
    }

    @Override
    public ItemImpl addData(String caption, String format, Object... args) {
        return null;
    }

    @Override
    public LineImpl addLine() {
        return null;
    }

    @Override
    public LineImpl addLine(String caption) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public void clearAll() {

    }

    @Override
    public String getCaptionValueSeparator() {
        return null;
    }

    @Override
    public String getItemSeparator() {
        return null;
    }

    @Override
    public int getMsTransmissionInterval() {
        return 0;
    }

    @Override
    public boolean isAutoClear() {
        return false;
    }

    @Override
    public LogImpl log() {
        return null;
    }

    @Override
    public boolean removeAction(Object token) {
        return false;
    }

    @Override
    public boolean removeItem(Item item) {
        return false;
    }

    @Override
    public boolean removeLine(Line line) {
        return false;
    }

    @Override
    public void setAutoClear(boolean autoClear) {

    }

    @Override
    public void setCaptionValueSeparator(String captionValueSeparator) {

    }

    @Override
    public void setItemSeparator(String itemSeparator) {

    }

    @Override
    public void setMsTransmissionInterval(int msTransmissionInterval) {

    }

    @Override
    public boolean update() {
        return false;
    }
}
