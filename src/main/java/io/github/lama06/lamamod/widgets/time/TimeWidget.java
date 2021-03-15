package io.github.lama06.lamamod.widgets.time;

import io.github.lama06.lamamod.events.EventResult;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.widgets.AbstractTextWidget;
import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.util.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeWidget extends AbstractTextWidget<TimeWidgetOptions> {
    @Override
    protected String getText(TimeWidgetOptions options) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern((options.date ? "dd.MM ": "") + "HH:mm" + (options.seconds ? ":ss" : ""));
        LocalDateTime now = LocalDateTime.now();
        return formatter.format(now);
    }

    @Override
    protected String getPrefix() {
        return "Zeit: ";
    }

    @Override
    public EventResult onChatMessage(ChatMessage msg) {
        if(msg.getText().startsWith(getName().toLowerCase(Locale.ROOT))) {
            TimeWidgetOptions options = getWidgetOptions(Options.getOptions());
            String[] args = msg.getArgs();

            if(args.length == 1) {
                if(args[0].equals("date")) {
                    if(options.date) {
                        options.date = false;
                        Util.sendMsgToPlayer("Das Datum wird nun ausgeblendet");
                    } else {
                        options.date = true;
                        Util.sendMsgToPlayer("Das Datum wird jetzt angezeigt");
                    }
                    return EventResult.CANCEL;
                } else if(args[0].equals("seconds")) {
                    if(options.seconds) {
                        options.seconds = false;
                        Util.sendMsgToPlayer("Die Sekunden werden nun ausgeblendet");
                    } else {
                        options.seconds = true;
                        Util.sendMsgToPlayer("Die Sekunden werden nun angezeigt");
                    }
                    return EventResult.CANCEL;
                }
                setWidgetOptions(options);
            }
        }

        return super.onChatMessage(msg);
    }

    @Override
    protected String getName() {
        return "TimeWidget";
    }

    @Override
    protected TimeWidgetOptions getWidgetOptions(Options options) {
        return options.timeWidget;
    }

    @Override
    protected void setWidgetOptions(TimeWidgetOptions widgetOptions) {
        Options options = Options.getOptions();
        options.timeWidget = widgetOptions;
        Options.setOptions(options);
    }
}
