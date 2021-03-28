package io.github.lama06.lamamod.widgets.targetedblock;

import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.util.Util;
import io.github.lama06.lamamod.widgets.AbstractTextWidget;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

public class TargetedBlockWidget extends AbstractTextWidget<TargetedBlockWidgetOptions> {
    @Override
    protected String getText(TargetedBlockWidgetOptions options) {
        BlockHitResult hitResult = (BlockHitResult) Util.getTargetedBlock();

        if (hitResult.getType() != HitResult.Type.BLOCK) {
            return "";
        } else {
            BlockPos pos = hitResult.getBlockPos();
            return pos.getX() + " " + pos.getY() + " " + pos.getZ();
        }
    }

    @Override
    protected String getPrefix() {
        return "Block: ";
    }

    @Override
    protected String getName() {
        return "TargetedBlockWidget";
    }

    @Override
    protected TargetedBlockWidgetOptions getWidgetOptions(Options options) {
        return options.targetedBlockWidget;
    }

    @Override
    protected void setWidgetOptions(TargetedBlockWidgetOptions widgetOptions) {
        Options options = Options.getOptions();
        options.targetedBlockWidget = widgetOptions;
        Options.setOptions(options);
    }
}
