package net.mehvahdjukaar.modelfix.mixins;

import net.mehvahdjukaar.modelfix.ModelFix;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

@Mixin(TextureAtlasSprite.class)
public abstract class TextureAtlasSpriteMixin {

    @Shadow public abstract TextureAtlas atlas();

    @Shadow protected abstract float atlasSize();

    @Inject(method = "uvShrinkRatio", at = @At("RETURN"), cancellable = true)
    public void cancelShrink(CallbackInfoReturnable<Float> cir) {
        var newS = ModelFix.getShrinkRatio(this.atlas(), 4.0F / this.atlasSize(), cir.getReturnValueF());
        if(newS != -1) cir.setReturnValue(newS);
    }
}