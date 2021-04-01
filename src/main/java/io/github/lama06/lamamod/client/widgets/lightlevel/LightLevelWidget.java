package io.github.lama06.lamamod.client.widgets.lightlevel;

import io.github.lama06.lamamod.client.options.ClientOptions;
import io.github.lama06.lamamod.client.util.ClientUtil;
import io.github.lama06.lamamod.client.widgets.AbstractTextWidget;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

public class LightLevelWidget extends AbstractTextWidget<LightLevelWidgetOptions> {
    @Override
    protected String getText(LightLevelWidgetOptions options) {
        HitResult hitResult = ClientUtil.getTargetedBlock();

        if (hitResult.getType() == HitResult.Type.MISS || hitResult.getType() == HitResult.Type.ENTITY) {
            return "";
        } else {
            return Integer.toString(ClientUtil.getLightLevel(new BlockPos(hitResult.getPos())));
        }
    }

    @Override
    protected String getPrefix() {
        return "Licht: ";
    }

    @Override
    protected String getName() {
        return "LightLevelWidget";
    }

    @Override
    protected LightLevelWidgetOptions getWidgetOptions(ClientOptions options) {
        return options.lightLevelWidget;
    }

    @Override
    protected void setWidgetOptions(LightLevelWidgetOptions widgetOptions) {
        ClientOptions options = ClientOptions.getOptions();
        options.lightLevelWidget = widgetOptions;
        ClientOptions.setOptions(options);
    }
}
