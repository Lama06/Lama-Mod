package io.github.lama06.lamamod.mixins;

import io.github.lama06.lamamod.events.EventResult;
import io.github.lama06.lamamod.events.MessageSentCallback;
import io.github.lama06.lamamod.util.ChatMessage;
import io.github.lama06.lamamod.util.Util;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(ChatScreen.class)
public abstract class ChatScreenMixin extends Screen {
    @Shadow protected TextFieldWidget chatField;

    protected ChatScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("HEAD"), method = "keyPressed", cancellable = true)
    public void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if(keyCode == 257) {
            ChatMessage msg = new ChatMessage(chatField.getText());
            EventResult result = MessageSentCallback.BEFORE_SENT.invoker().onChatMessage(msg);

            if(result == EventResult.PASS) {
                if(client.player.getUuid().equals(Util.Hepux06UUID) && !msg.getText().startsWith("/") && !msg.getText().endsWith("...")) {
                    Util.sendMsgToChat(msg.getPlainText() + (new Random().nextBoolean() ? "..." : ".."));
                } else {
                    Util.sendMsgToChat(msg.getPlainText());
                }
            }

            Util.addToChatHistory(msg.getPlainText());
            client.openScreen(null);
            cir.setReturnValue(true);
        }
    }
}
