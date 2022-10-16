package ro.eminescusm.pm.mock.internal.telemetry;

/**
 * Instances of {@link Telemetry} provide a means by which data can be transmitted from the robot
 * controller to the driver station and displayed on the driver station screen.
 */
public interface Telemetry {
    /**
     * Instances of {@link Telemetry.Item} represent an item of data on the driver station telemetry display.
     */
    interface Item {
        /**
         * Adds a mew data item in the associated {@link Telemetry} immediately following the receiver.
         *
         * @param caption       the caption to be displayed with the data item
         * @param valueProducer function which produces the value to be displayed
         * @param <T>           the type of the value to be displayed
         * @return the newly created data item
         */
        <T> Item addData(String caption, Func<T> valueProducer);

        /**
         * Adds a mew data item in the associated {@link Telemetry} immediately following the receiver.
         *
         * @param caption the caption to be displayed with the data item
         * @param value   the value to be displayed
         * @return the newly created data item
         */
        Item addData(String caption, Object value);

        /**
         * Adds a mew data item in the associated {@link Telemetry} immediately following the receiver.
         *
         * @param caption       the caption to be displayed with the data item
         * @param format        the format string to be used to format the value
         * @param valueProducer function which produces the value to be displayed
         * @param <T>           the type of the value to be displayed
         * @return the newly created data item
         */
        <T> Item addData(String caption, String format, Func<T> valueProducer);

        /**
         * Adds a mew data item in the associated {@link Telemetry} immediately following the receiver.
         *
         * @param caption the caption to be displayed with the data item
         * @param format  the format string to be used to format the value
         * @param args    the arguments to be used to format the value
         * @return the newly created data item
         */
        Item addData(String caption, String format, Object... args);

        /**
         * Returns the caption associated with the receiver.
         *
         * @return the caption associated with the receiver
         */
        String getCaption();

        /**
         * Returns whether the receiver is retained when the associated {@link Telemetry} is cleared.
         *
         * @return whether the receiver is retained when the associated {@link Telemetry} is cleared
         */
        boolean isRetained();

        /**
         * Sets the caption associated with the receiver.
         *
         * @param caption the caption to be associated with the receiver
         * @return the receiver
         */
        Item setCaption(String caption);

        /**
         * Sets whether the receiver is retained when the associated {@link Telemetry} is cleared.
         *
         * @param retained whether the receiver is retained when the associated {@link Telemetry} is cleared
         * @return the receiver
         */
        Item setRetained(boolean retained);

        /**
         * Sets the value associated with the receiver.
         *
         * @param valueProducer function which produces the value to be associated with the receiver
         * @param <T>           the type of the value to be associated with the receiver
         * @return the receiver
         */
        <T> Item setValue(Func<T> valueProducer);

        /**
         * Sets the value associated with the receiver.
         *
         * @param value the value to be associated with the receiver
         * @return the receiver
         */
        Item setValue(Object value);

        /**
         * Sets the value associated with the receiver.
         *
         * @param format        the format string to be used to format the value
         * @param valueProducer function which produces the value to be associated with the receiver
         * @param <T>           the type of the value to be associated with the receiver
         * @return the receiver
         */
        <T> Item setValue(String format, Func<T> valueProducer);

        /**
         * Sets the value associated with the receiver.
         *
         * @param format the format string to be used to format the value
         * @param args   the arguments to be used to format the value
         * @return the receiver
         */
        Item setValue(String format, Object... args);
    }

    /**
     * Instances of {@link Telemetry.Line} build lines of data on the driver station telemetry display.
     */
    interface Line {
        /**
         * Adds a new data item at the end of the line which is the receiver
         *
         * @param caption       the caption to be displayed with the data item
         * @param valueProducer function which produces the value to be displayed
         * @param <T>           the type of the value to be displayed
         * @return the newly created data item
         */
        <T> Item addData(String caption, Func<T> valueProducer);

        /**
         * Adds a new data item at the end of the line which is the receiver
         *
         * @param caption the caption to be displayed with the data item
         * @param value   the value to be displayed
         * @return the newly created data item
         */
        Item addData(String caption, Object value);

        /**
         * Adds a new data item at the end of the line which is the receiver
         *
         * @param caption       the caption to be displayed with the data item
         * @param format        the format string to be used to format the value
         * @param valueProducer function which produces the value to be displayed
         * @param <T>           the type of the value to be displayed
         * @return the newly created data item
         */
        <T> Item addData(String caption, String format, Func<T> valueProducer);

        /**
         * Adds a new data item at the end of the line which is the receiver
         *
         * @param caption the caption to be displayed with the data item
         * @param format  the format string to be used to format the value
         * @param args    the arguments to be used to format the value
         * @return the newly created data item
         */
        Item addData(String caption, String format, Object... args);
    }

    /**
     * The {@link Telemetry.Log} in a {@link Telemetry} instance provides an append-only list of messages that appear on the driver station
     * below the {@link Telemetry.Item}s of the {@link Telemetry}
     */
    interface Log {
        /**
         * {@link Telemetry.Log.DisplayOrder} instances indicate the desired ordering of a {@link Telemetry#log()}
         */
        enum DisplayOrder {
            NEWEST_FIRST,
            OLDEST_FIRST
        }

        /**
         * Adds a new entry to the log
         *
         * @param entry the entry to be added
         */
        void add(String entry);

        /**
         * Adds a new entry to the log
         *
         * @param format the format string to be used to format the entry
         * @param args   the arguments to be used to format the entry
         */
        void add(String format, Object... args);

        /**
         * Clears the log
         */
        void clear();

        /**
         * Returns the maximum number of lines which will be retained in a {@link Telemetry#log()} and shown on the driver station display
         *
         * @return the maximum number of lines which will be retained in a {@link Telemetry#log()} and shown on the driver station display
         */
        int getCapacity();

        /**
         * Returns the order in which data in log is to be displayed on the driver station
         *
         * @return the order in which data in log is to be displayed on the driver station
         */
        DisplayOrder getDisplayOrder();

        /**
         * Sets the maximum number of lines which will be retained in a {@link Telemetry#log()} and shown on the driver station display
         *
         * @param capacity the maximum number of lines which will be retained in a {@link Telemetry#log()} and shown on the driver station display
         */
        void setCapacity(int capacity);

        /**
         * Sets the order in which data in log is to be displayed on the driver station
         *
         * @param displayOrder the order in which data in log is to be displayed on the driver station
         */
        void setDisplayOrder(DisplayOrder displayOrder);
    }

    /**
     * In addition to items and lines, a telemetry may also contain a list of actions
     *
     * @param action the action to be added
     * @return a token by which the action may be removed
     */
    Object addAction(Runnable action);

    /**
     * Adds an item to the end of the telemetry being built for the driver station display
     *
     * @param caption       the caption to be displayed with the data item
     * @param valueProducer function which produces the value to be displayed
     * @param <T>           the type of the value to be displayed
     * @return the newly created data item
     */
    <T> Item addData(String caption, Func<T> valueProducer);

    /**
     * Adds an item to the end of the telemetry being built for the driver station display
     *
     * @param caption the caption to be displayed with the data item
     * @param value   the value to be displayed
     */
    Item addData(String caption, Object value);

    /**
     * Adds an item to the end of the telemetry being built for the driver station display
     *
     * @param caption       the caption to be displayed with the data item
     * @param format        the format string to be used to format the value
     * @param valueProducer function which produces the value to be displayed
     * @param <T>           the type of the value to be displayed
     * @return the newly created data item
     */
    <T> Item addData(String caption, String format, Func<T> valueProducer);

    /**
     * Adds an item to the end of the telemetry being built for the driver station display
     *
     * @param caption the caption to be displayed with the data item
     * @param format  the format string to be used to format the value
     * @param args    the arguments to be used to format the value
     * @return the newly created data item
     */
    Item addData(String caption, String format, Object... args);

    /**
     * Creates and returns a new line in the receiver {@link Telemetry}
     *
     * @return the newly created line
     */
    Line addLine();

    /**
     * Creates and returns a new line in the receiver {@link Telemetry}
     *
     * @param caption the caption to be displayed with the line
     * @return the newly created line
     */
    Line addLine(String caption);

    /**
     * Removes all items from the receiver whose value is not to be retained
     */
    void clear();

    /**
     * Removes all items from the receiver, including those whose value is to be retained
     */
    void clearAll();

    /**
     * Gets the separator used to separate the caption from the value in a {@link Telemetry.Item}
     * @return the separator (default is ": ")
     */
    String getCaptionValueSeparator();

    /**
     * Getts the separator used to separate the items in a {@link Telemetry.Line}
     * @return the separator (default is " | ")
     */
    String getItemSeparator();

    /**
     * Gets the minimum interval between transmissions of telemetry to the driver station
     * @return the minimum interval between transmissions of telemetry to the driver station
     */
    int getMsTransmissionInterval();

    /**
     * Answers whether {@link Telemetry#clear()} is automatically called after each call to {@link Telemetry#update()}
     * @return whether {@link Telemetry#clear()} is automatically called after each call to {@link Telemetry#update()}
     */
    boolean isAutoClear();

    /**
     * Returns the log of this {@link Telemetry} to which log entries may be appended
     * @return the log of this {@link Telemetry}
     */
    Log log();

    /**
     * Removes the action with the given token from the receiver
     *
     * @param token the token of the action to be removed
     * @return whether the action was removed
     */
    boolean removeAction(Object token);

    /**
     * Removes the item with the given token from the receiver
     *
     * @param item the item to be removed
     * @return whether the item was removed
     */
    boolean removeItem(Item item);

    /**
     * Removes the line with the given token from the receiver
     *
     * @param line the line to be removed
     * @return whether the line was removed
     */
    boolean removeLine(Line line);

    /**
     * Sets whether {@link Telemetry#clear()} should be called automatically after each call to {@link Telemetry#update()}
     * @param autoClear whether {@link Telemetry#clear()} should be called automatically after each call to {@link Telemetry#update()}
     */
    void setAutoClear(boolean autoClear);

    /**
     * Sets the separator to be used to separate the caption from the value in a {@link Telemetry.Item}
     * @param captionValueSeparator the separator to be used to separate the caption from the value in a {@link Telemetry.Item}
     */
    void setCaptionValueSeparator(String captionValueSeparator);

    /**
     * Sets the separator to be used to separate the items in a {@link Telemetry.Line}
     * @param itemSeparator the separator to be used to separate the items in a {@link Telemetry.Line}
     */
    void setItemSeparator(String itemSeparator);

    /**
     * Sets the minimum interval between transmissions of telemetry to the driver station
     * @param msTransmissionInterval the minimum interval between transmissions of telemetry to the driver station
     */
    void setMsTransmissionInterval(int msTransmissionInterval);

    boolean update();
}
