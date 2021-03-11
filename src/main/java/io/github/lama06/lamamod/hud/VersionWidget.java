package io.github.lama06.lamamod.hud;

import io.github.lama06.lamamod.LamaMod;
import io.github.lama06.lamamod.options.Options;
import io.github.lama06.lamamod.version.GithubRelease;

public class VersionWidget extends AbstractTextWidget<TextWidgetOptions> {
    private GithubRelease newestRelease = GithubRelease.fetchNewestRelease();
    private boolean newerVersionAvailable = GithubRelease.isNewerModVersionAvailable();

    @Override
    protected String getText(TextWidgetOptions options) {
        return LamaMod.currentModVersion.toVersionTag() +
                (newerVersionAvailable ? " (neuere Version verfübgar: " + newestRelease.versionTag + ")" : " (neuste Version)");
    }

    @Override
    protected String getPrefix() {
        return "Version: ";
    }

    @Override
    protected TextWidgetOptions getWidgetOptions(Options options) {
        return options.versionWidget;
    }

    @Override
    protected void setWidgetOptions(TextWidgetOptions widgetOptions) {
        Options options = Options.getOptions();
        options.versionWidget = widgetOptions;
        Options.setOptions(options);
    }
}
