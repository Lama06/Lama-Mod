package io.github.lama06.lamamod.client.widgets.biome;

import io.github.lama06.lamamod.client.options.ClientOptions;
import io.github.lama06.lamamod.client.widgets.AbstractTextWidget;
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
    protected BiomeWidgetOptions getWidgetOptions(ClientOptions options) {
        return options.biomeWidget;
    }

    @Override
    protected void setWidgetOptions(BiomeWidgetOptions widgetOptions) {
        ClientOptions options = ClientOptions.getOptions();
        options.biomeWidget = widgetOptions;
        ClientOptions.setOptions(options);
    }
}
