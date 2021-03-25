package io.github.lama06.lamamod.widgets.biome;

import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.widgets.AbstractTextWidget;
import net.minecraft.util.registry.Registry;

public class BiomeWidget extends AbstractTextWidget<BiomeWidgetOptions> {
    @Override
    protected String getText(BiomeWidgetOptions options) {
        return client.world.getRegistryManager().get(Registry.BIOME_KEY).getId(client.world.getBiome(client.player.getBlockPos())).toString();
    }

    @Override
    protected String getPrefix() {
        return "Biom: ";
    }

    @Override
    protected String getName() {
        return "BiomeWidget";
    }

    @Override
    protected BiomeWidgetOptions getWidgetOptions(Options options) {
        return options.biomeWidget;
    }

    @Override
    protected void setWidgetOptions(BiomeWidgetOptions widgetOptions) {
        Options options = Options.getOptions();
        options.biomeWidget = widgetOptions;
        Options.setOptions(options);
    }
}
