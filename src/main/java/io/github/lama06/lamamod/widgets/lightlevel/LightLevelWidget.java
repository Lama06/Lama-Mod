package io.github.lama06.lamamod.widgets.lightlevel;

import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.util.Util;
import io.github.lama06.lamamod.widgets.AbstractTextWidget;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

public class LightLevelWidget extends AbstractTextWidget<LightLevelWidgetOptions> {
    @Override
    protected String getText(LightLevelWidgetOptions options) {
        HitResult hitResult = Util.getTargetedBlock();

        if(hitResult.getType() == HitResult.Type.MISS || hitResult.getType() == HitResult.Type.ENTITY) {
            return "";
        } else {
            return Integer.toString(Util.getLightLevel(new BlockPos(hitResult.getPos())));
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
    protected LightLevelWidgetOptions getWidgetOptions(Options options) {
        return options.lightLevelWidget;
    }

    @Override
    protected void setWidgetOptions(LightLevelWidgetOptions widgetOptions) {
        Options options = Options.getOptions();
        options.lightLevelWidget = widgetOptions;
        Options.setOptions(options);
    }
}
