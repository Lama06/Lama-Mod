package io.github.lama06.lamamod.client.widgets.targetedblock;

import io.github.lama06.lamamod.client.options.ClientOptions;
import io.github.lama06.lamamod.client.util.ClientUtil;
import io.github.lama06.lamamod.client.widgets.AbstractTextWidget;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

public class TargetedBlockWidget extends AbstractTextWidget<TargetedBlockWidgetOptions> {
    @Override
    protected String getText(TargetedBlockWidgetOptions options) {
        BlockHitResult hitResult = (BlockHitResult) ClientUtil.getTargetedBlock();

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
    protected TargetedBlockWidgetOptions getWidgetOptions(ClientOptions options) {
        return options.targetedBlockWidget;
    }

    @Override
    protected void setWidgetOptions(TargetedBlockWidgetOptions widgetOptions) {
        ClientOptions options = ClientOptions.getOptions();
        options.targetedBlockWidget = widgetOptions;
        ClientOptions.setOptions(options);
    }
}
