package io.github.lama06.lamamod.client.widgets.time;

import io.github.lama06.lamamod.common.events.EventResult;
import io.github.lama06.lamamod.client.options.ClientOptions;
import io.github.lama06.lamamod.common.util.ChatMessage;
import io.github.lama06.lamamod.client.util.ClientUtil;
import io.github.lama06.lamamod.client.widgets.AbstractTextWidget;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeWidget extends AbstractTextWidget<TimeWidgetOptions> {
    @Override
    protected String getText(TimeWidgetOptions options) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern((options.date ? "dd.MM " : "") + "HH:mm" + (options.seconds ? ":ss" : ""));
        LocalDateTime now = LocalDateTime.now();
        return formatter.format(now);
    }

    @Override
    protected String getPrefix() {
        return "Zeit: ";
    }

    @Override
    public EventResult onChatMessage(ChatMessage msg) {
        if (msg.getText().startsWith(getName().toLowerCase(Locale.ROOT))) {
            TimeWidgetOptions options = getWidgetOptions(ClientOptions.getOptions());
            String[] args = msg.getArgs();

            if (args.length == 1) {
                if (args[0].equals("date")) {
                    if (options.date) {
                        options.date = false;
                        ClientUtil.sendMsgToPlayer("Das Datum wird nun ausgeblendet");
                    } else {
                        options.date = true;
                        ClientUtil.sendMsgToPlayer("Das Datum wird jetzt angezeigt");
                    }
                } else if (args[0].equals("seconds")) {
                    if (options.seconds) {
                        options.seconds = false;
                        ClientUtil.sendMsgToPlayer("Die Sekunden werden nun ausgeblendet");
                    } else {
                        options.seconds = true;
                        ClientUtil.sendMsgToPlayer("Die Sekunden werden nun angezeigt");
                    }
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
    protected TimeWidgetOptions getWidgetOptions(ClientOptions options) {
        return options.timeWidget;
    }

    @Override
    protected void setWidgetOptions(TimeWidgetOptions widgetOptions) {
        ClientOptions options = ClientOptions.getOptions();
        options.timeWidget = widgetOptions;
        ClientOptions.setOptions(options);
    }
}
