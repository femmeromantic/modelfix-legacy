package net.mehvahdjukaar.modelfix.mixins;

import net.mehvahdjukaar.modelfix.hook.ModelHook;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TextureAtlasSprite.class)
public abstract class TextureAtlasSpriteMixin {
    @Shadow @Final private String iconName;

    @Shadow protected int width, height;

    @Shadow private float minU, maxU, maxV, minV;

    @Inject(method = "uvShrinkRatio", at = @At("RETURN"), cancellable = true)
    public void cancelShrink(CallbackInfoReturnable<Float> cir) {
        float newS = ModelHook.getShrinkRatio(iconName, 4.0F / this.atlasSize(), cir.getReturnValueF());
        if(newS != -1) cir.setReturnValue(newS);
    }

    @Unique
    private float atlasSize() {
        float var1 = width / (maxU - minU);
        float var2 = height / (maxV - minV);
        return Math.max(var2, var1);
    }
}
