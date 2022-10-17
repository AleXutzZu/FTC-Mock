package ro.eminescusm.pm.mock.internal.impl;

import ro.eminescusm.pm.mock.internal.telemetry.Func;
import ro.eminescusm.pm.mock.internal.telemetry.Telemetry;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TelemetryImpl implements Telemetry {
    private String itemSeparator = " | ";
    private String captionValueSeparator = ": ";
    private int msTransmissionInterval = 250;

    private boolean autoClear = true;

    private final List<Line> lines = new LinkedList<>();
    private final Log log = new LogImpl();
    private final Map<Object, Runnable> actions = new HashMap<>();

    private final List<String> serializedLines = new ArrayList<>();

    private long lastTransmission = 0;

    static class ItemImpl implements Item {

        private String caption = null;
        private boolean retained = false;
        private Object value = null;
        private Func<?> valueProducer = null;

        private String format = null;
        private Object[] args = null;

        private final Line line;

        ItemImpl(Line line) {
            this.line = line;
        }

        @Override
        public <T> Item addData(String caption, Func<T> valueProducer) {
            line.addData(caption, valueProducer);
            return this;
        }

        @Override
        public Item addData(String caption, Object value) {
            line.addData(caption, value);
            return this;
        }

        @Override
        public <T> Item addData(String caption, String format, Func<T> valueProducer) {
            line.addData(caption, format, valueProducer);
            return this;
        }

        @Override
        public Item addData(String caption, String format, Object... args) {
            line.addData(caption, format, args);
            return this;
        }

        @Override
        public String getCaption() {
            return caption;
        }

        @Override
        public boolean isRetained() {
            return retained;
        }

        @Override
        public Item setCaption(String caption) {
            this.caption = caption;
            return this;
        }

        @Override
        public Item setRetained(boolean retained) {
            this.retained = retained;
            return this;
        }

        @Override
        public <T> Item setValue(Func<T> valueProducer) {
            this.valueProducer = valueProducer;
            this.format = null;
            this.args = null;
            return this;
        }

        @Override
        public Item setValue(Object value) {
            this.value = value;
            this.valueProducer = null;
            this.args = null;
            return this;
        }

        @Override
        public <T> Item setValue(String format, Func<T> valueProducer) {
            this.format = format;
            this.valueProducer = valueProducer;
            this.args = null;
            return this;
        }

        @Override
        public Item setValue(String format, Object... args) {
            this.format = format;
            this.args = args;
            this.valueProducer = null;
            this.value = null;
            return this;
        }

        private String getFormattedItem() {
            String computedString = "";

            if (format != null) {
                if (valueProducer != null) {
                    computedString = String.format(format, valueProducer.value());
                } else if (args != null) {
                    computedString = String.format(format, args);
                }
            } else {
                if (valueProducer != null) {
                    computedString = valueProducer.value().toString();
                } else if (value != null) {
                    computedString = value.toString();
                }
            }

            return computedString;
        }
    }

    static class LogImpl implements Log {

        private DisplayOrder displayOrder;
        private final List<String> logs;

        private int capacity;

        LogImpl() {
            logs = new LinkedList<>();
            displayOrder = DisplayOrder.NEWEST_FIRST;
            capacity = 15;
        }

        @Override
        public void add(String entry) {
            logs.add(entry);
        }

        @Override
        public void add(String format, Object... args) {
            logs.add(String.format(format, args));
        }

        @Override
        public void clear() {
            logs.clear();
        }

        @Override
        public int getCapacity() {
            return capacity;
        }

        @Override
        public DisplayOrder getDisplayOrder() {
            return displayOrder;
        }

        @Override
        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        @Override
        public void setDisplayOrder(DisplayOrder displayOrder) {
            this.displayOrder = displayOrder;
        }

        private List<String> getLogs() {
            return logs;
        }
    }

    static class LineImpl implements Line {
        private final List<Item> items;
        private final String caption;

        LineImpl(String caption) {
            items = new LinkedList<>();
            this.caption = caption;
        }

        @Override
        public <T> Item addData(String caption, Func<T> valueProducer) {
            Item item = new ItemImpl(this);
            item.setCaption(caption);
            item.setValue(valueProducer);
            items.add(item);
            return item;
        }

        @Override
        public Item addData(String caption, Object value) {
            Item item = new ItemImpl(this);
            item.setCaption(caption);
            item.setValue(value);
            items.add(item);
            return item;
        }

        @Override
        public <T> Item addData(String caption, String format, Func<T> valueProducer) {
            Item item = new ItemImpl(this);
            item.setCaption(caption);
            item.setValue(format, valueProducer);
            items.add(item);
            return item;
        }

        @Override
        public Item addData(String caption, String format, Object... args) {
            Item item = new ItemImpl(this);
            item.setCaption(caption);
            item.setValue(format, args);
            items.add(item);
            return item;
        }

        private List<Item> getItems() {
            return items;
        }

        private String getCaption() {
            return caption;
        }
    }

    @Override
    public Object addAction(Runnable action) {
        actions.put(action, action);
        return action;
    }

    @Override
    public <T> Item addData(String caption, Func<T> valueProducer) {
        lines.add(new LineImpl(null));
        return lines.get(lines.size() - 1).addData(caption, valueProducer);
    }

    @Override
    public Item addData(String caption, Object value) {
        lines.add(new LineImpl(null));
        return lines.get(lines.size() - 1).addData(caption, value);
    }

    @Override
    public <T> Item addData(String caption, String format, Func<T> valueProducer) {
        lines.add(new LineImpl(null));
        return lines.get(lines.size() - 1).addData(caption, format, valueProducer);
    }

    @Override
    public Item addData(String caption, String format, Object... args) {
        lines.add(new LineImpl(null));
        return lines.get(lines.size() - 1).addData(caption, format, args);
    }

    @Override
    public Line addLine() {
        lines.add(new LineImpl(null));
        //return last element from list
        return lines.get(lines.size() - 1);
    }

    @Override
    public Line addLine(String caption) {
        lines.add(new LineImpl(caption));
        //return last element from list
        return lines.get(lines.size() - 1);
    }

    @Override
    public void clear() {
        for (Line line : lines) {
            //Remove item from list
            ((LineImpl) line).getItems().removeIf(item -> !item.isRetained());
        }
    }

    @Override
    public void clearAll() {
        lines.clear();
    }

    @Override
    public String getCaptionValueSeparator() {
        return captionValueSeparator;
    }

    @Override
    public String getItemSeparator() {
        return itemSeparator;
    }

    @Override
    public int getMsTransmissionInterval() {
        return msTransmissionInterval;
    }

    @Override
    public boolean isAutoClear() {
        return autoClear;
    }

    @Override
    public Log log() {
        return log;
    }

    @Override
    public boolean removeAction(Object token) {
        if (actions.containsKey(token)) {
            actions.remove(token);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeItem(Item item) {
        for (Line line : lines) {
            if (((LineImpl) line).getItems().remove(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeLine(Line line) {
        return lines.remove(line);
    }

    @Override
    public void setAutoClear(boolean autoClear) {
        this.autoClear = autoClear;
    }

    @Override
    public void setCaptionValueSeparator(String captionValueSeparator) {
        this.captionValueSeparator = captionValueSeparator;
    }

    @Override
    public void setItemSeparator(String itemSeparator) {
        this.itemSeparator = itemSeparator;
    }

    @Override
    public void setMsTransmissionInterval(int msTransmissionInterval) {
        this.msTransmissionInterval = msTransmissionInterval;
    }

    @Override
    public boolean update() {
        if (lastTransmission + msTransmissionInterval < System.currentTimeMillis()) {
            lastTransmission = System.currentTimeMillis();
            ExecutorService executors = Executors.newSingleThreadExecutor();
            for (Runnable action : actions.values()) {
                executors.execute(action);
            }
            //Await termination of all threads
            executors.shutdown();
            while (true) {
                boolean terminated;
                try {
                    terminated = executors.awaitTermination(1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    terminated = true;
                }
                if (terminated) break;
            }

            //Serialize data
            serializedLines.clear();
            for (Line line : lines) {
                StringBuilder sb = new StringBuilder();
                LineImpl lineImpl = (LineImpl) line;
                if (lineImpl.getCaption() != null) sb.append(lineImpl.getCaption()).append(getCaptionValueSeparator());
                for (Item item : lineImpl.getItems()) {
                    ItemImpl itemImpl = (ItemImpl) item;
                    sb.append(itemImpl.getCaption()).append(getCaptionValueSeparator()).append(itemImpl.getFormattedItem());
                    //append item separator if not last item
                    if (lineImpl.getItems().indexOf(item) != lineImpl.getItems().size() - 1) {
                        sb.append(getItemSeparator());
                    }
                }
                serializedLines.add(sb.toString());
            }

            List<String> logs = ((LogImpl) log).getLogs();


            int fromIndex = 0;
            int toIndex = fromIndex + log.getCapacity();

            if (toIndex > logs.size()) {
                toIndex = logs.size();
            }

            //Reverse logs if DisplayOrder is NEWEST_FIRST
            if (log.getDisplayOrder() == Log.DisplayOrder.NEWEST_FIRST) {
                Collections.reverse(logs);
            }

            serializedLines.addAll(((LogImpl) log).getLogs().subList(fromIndex, toIndex));

            if (autoClear) {
                clear();
            }

            return true;
        }
        return false;
    }

    private List<String> getSerializedLines() {
        return serializedLines;
    }
}
