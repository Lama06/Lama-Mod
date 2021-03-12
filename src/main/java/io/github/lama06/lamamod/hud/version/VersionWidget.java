package io.github.lama06.lamamod.hud.version;

import io.github.lama06.lamamod.LamaMod;
import io.github.lama06.lamamod.hud.AbstractTextWidget;
import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.version.GithubRelease;

public class VersionWidget extends AbstractTextWidget<VersionWidgetOptions> {
    private GithubRelease newestRelease = GithubRelease.fetchNewestRelease();
    private boolean newerVersionAvailable = GithubRelease.isNewerModVersionAvailable();

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
    protected VersionWidgetOptions getWidgetOptions(Options options) {
        return options.versionWidget;
    }

    @Override
    protected void setWidgetOptions(VersionWidgetOptions widgetOptions) {
        Options options = Options.getOptions();
        options.versionWidget = widgetOptions;
        Options.setOptions(options);
    }
}
