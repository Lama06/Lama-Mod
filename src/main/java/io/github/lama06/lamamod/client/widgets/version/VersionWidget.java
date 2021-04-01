package io.github.lama06.lamamod.client.widgets.version;

import io.github.lama06.lamamod.common.LamaMod;
import io.github.lama06.lamamod.client.options.ClientOptions;
import io.github.lama06.lamamod.common.version.GithubRelease;
import io.github.lama06.lamamod.client.widgets.AbstractTextWidget;

public class VersionWidget extends AbstractTextWidget<VersionWidgetOptions> {
    private final GithubRelease newestRelease = GithubRelease.fetchNewestRelease();
    private final boolean newerVersionAvailable = GithubRelease.isNewerModVersionAvailable();

    @Override
    protected String getText(VersionWidgetOptions options) {
        return LamaMod.currentModVersion.toVersionTag() +
                (newerVersionAvailable ? " (neuere Version verfübgar: " + newestRelease.versionTag + ")" : " (neuste Version)");
    }

    @Override
    protected String getPrefix() {
        return "Version: ";
    }

    @Override
    protected String getName() {
        return "VersionWidget";
    }

    @Override
    protected VersionWidgetOptions getWidgetOptions(ClientOptions options) {
        return options.versionWidget;
    }

    @Override
    protected void setWidgetOptions(VersionWidgetOptions widgetOptions) {
        ClientOptions options = ClientOptions.getOptions();
        options.versionWidget = widgetOptions;
        ClientOptions.setOptions(options);
    }
}
