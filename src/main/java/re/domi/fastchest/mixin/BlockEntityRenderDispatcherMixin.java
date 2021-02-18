package re.domi.fastchest.mixin;

import com.google.common.collect.Maps;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.block.ChestAnimationProgress;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import re.domi.fastchest.config.Configs;

import java.util.Map;

@Mixin(BlockEntityRenderDispatcher.class)
public class BlockEntityRenderDispatcherMixin {
    @Final
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @Shadow
    private final Map<BlockEntityType<?>, BlockEntityRenderer<?>> renderers = Maps.newHashMap();

    @Inject(method = "get(Ljava/lang/Class;)Lnet/minecraft/client/render/block/entity/BlockEntityRenderer;", at = @At("HEAD"), cancellable = true)
    private <E extends BlockEntity> void onGetRenderer(Class<? extends BlockEntity> class_, CallbackInfoReturnable<BlockEntityRenderer<E>> cir) {
        if (Configs.simplifiedChestRendering && class_.isAssignableFrom(ChestAnimationProgress.class)) {
            cir.setReturnValue(null);
        }
    }

    @Inject(method = "get(Lnet/minecraft/block/entity/BlockEntity;)Lnet/minecraft/client/render/block/entity/BlockEntityRenderer;", at = @At("HEAD"), cancellable = true)
    private <E extends BlockEntity> void onGetRenderer(E blockEntity, CallbackInfoReturnable<@Nullable BlockEntityRenderer<E>> cir) {
        if (Configs.simplifiedChestRendering && blockEntity instanceof ChestAnimationProgress) {
            cir.setReturnValue(null);
        }
    }
}
