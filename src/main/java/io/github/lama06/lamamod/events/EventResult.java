package io.github.lama06.lamamod.events;

public enum EventResult {
    /**
     * Es werden keine weiteren EventHandler mehr ausgeführt. Das Event wird nicht ausgeführt.
     */
    CANCEL,
    /**
     * Der nächste EventHandler wird ausgeführt.
     */
    PASS
}
