package io.github.lama06.lamamod.client.widgets;

import io.github.lama06.lamamod.common.events.EventResult;
import io.github.lama06.lamamod.common.util.ChatMessage;
import io.github.lama06.lamamod.client.util.Color;
import io.github.lama06.lamamod.client.util.ClientUtil;

import java.util.Locale;

public abstract class ColoredWidget<T extends ColoredWidgetOptions> extends AbstractWidget<T> {
    @Override
    public EventResult onChatMessage(ChatMessage msg) {
        if (msg.getText().startsWith(getName().toLowerCase(Locale.ROOT))) {
            String[] args = msg.getArgs();
            T options = getWidgetOptions();

            if ((args.length == 1 || args.length == 2 || args.length == 4) && args[0].equals("color")) {
                if (args.length == 1) {
                    ClientUtil.sendMsgToPlayer("Die Farbe ist aktuell: " + options.color.toString());
                } else if (args.length == 2 && Color.colors.containsKey(args[1])) {
                    options.color = Color.colors.get(args[1]);
                    ClientUtil.sendMsgToPlayer("Die Farbe ist jetzt: " + options.color.toString());
                } else if (args.length == 4) {
                    try {
                        int red = Integer.parseInt(args[1]);
                        int green = Integer.parseInt(args[2]);
                        int blue = Integer.parseInt(args[3]);

                        options.color = new Color(red, green, blue);
                        ClientUtil.sendMsgToPlayer("Die Farbe ist jetzt: " + options.color.toString());
                    } catch (NumberFormatException e) {
                        ClientUtil.sendMsgToPlayer("Das ist keine Zahl");
                    }
                }
                setWidgetOptions(options);
            }
        }
        return super.onChatMessage(msg);
    }
}
