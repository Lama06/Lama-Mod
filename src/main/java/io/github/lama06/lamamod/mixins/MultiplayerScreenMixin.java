package io.github.lama06.lamamod.mixins;

import io.github.lama06.lamamod.gui.JoinLamaServerButton;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiplayerScreen.class)
public abstract class MultiplayerScreenMixin extends Screen {
    protected MultiplayerScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("HEAD"), method = "init")
    public void addJoinLamaServerButton(CallbackInfo info) {
        addButton(new JoinLamaServerButton(this));
    }
}
