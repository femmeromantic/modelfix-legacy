package net.mehvahdjukaar.modelfix.hook;

import net.mehvahdjukaar.modelfix.ModelFix;
import net.minecraft.client.renderer.block.model.BlockPart;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.util.vector.Vector3f;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Set;

// func_178382_a = expand
// func_178383_a = getFacing
// func_178385_b = getMin
// func_178384_c = getMax
// func_178381_d = getAnchor

public class ModelHook {
    private static final ResourceLocation BLOCK_ATLAS = new ResourceLocation("textures/atlas/blocks.png");

    public static float getShrinkRatio(String atlas, float defaultValue, float returnValue) {
        if(atlas.equals(BLOCK_ATLAS.getResourcePath()) && defaultValue == returnValue) {
            return 0.0f;
        }
        return -1;
    }

    public static void createOrExpandSpan(List<ItemModelGenerator.Span> listSpans, ItemModelGenerator.SpanFacing spanFacing,
                                          int pixelX, int pixelY) {
        int length;
        ItemModelGenerator.Span existingSpan = null;
        for (ItemModelGenerator.Span span2 : listSpans) {
            if (span2.func_178383_a() == spanFacing) {
                int i = !spanFacing.func_178369_d() ? pixelY : pixelX;
                if (span2.func_178381_d() != i) continue;
                //skips faces with transparent pixels so we can enlarge safely
                if (ModelFix.config.getEXPANSION() != 0 && span2.func_178384_c() != (spanFacing.func_178369_d() ? pixelY : pixelX) - 1)
                    continue;
                existingSpan = span2;
                break;
            }
        }


        length = spanFacing.func_178369_d() ? pixelX : pixelY;
        if (existingSpan == null) {
            int newStart = !spanFacing.func_178369_d() ? pixelY : pixelX;
            listSpans.add(new ItemModelGenerator.Span(spanFacing, length, newStart));
        } else {
            existingSpan.func_178382_a(length);
        }
    }

    public static void enlargeFaces(CallbackInfoReturnable<List<BlockPart>> cir) {
        float inc = ModelFix.config.getRECESS();
        float inc2 = ModelFix.config.getEXPANSION();
        for (BlockPart e : cir.getReturnValue()) {
            Vector3f from = e.positionFrom;
            Vector3f to = e.positionTo;

            Set<EnumFacing> set = e.mapFaces.keySet();
            if (set.size() == 1) {
                EnumFacing dir = set.stream().findAny().get();
                switch (dir) {
                    case UP: {
                        from.set(from.x - inc2, from.y - inc, from.z - inc2);
                        to.set(to.x + inc2, to.y - inc, to.z + inc2);
                        break;
                    }
                    case DOWN: {
                        from.set(from.x - inc2, from.y + inc, from.z - inc2);
                        to.set(to.x + inc2, to.y + inc, to.z + inc2);
                        break;
                    }
                    case WEST: {
                        from.set(from.x - inc, from.y + inc2, from.z - inc2);
                        to.set(to.x - inc, to.y - inc2, to.z + inc2);
                        break;
                    }
                    case EAST: {
                        from.set(from.x + inc, from.y + inc2, from.z - inc2);
                        to.set(to.x + inc, to.y - inc2, to.z + inc2);
                        break;
                    }
                }
            }
        }
    }
}
