package net.darkhax.splashy.mixin;

import net.darkhax.splashy.Config;
import net.darkhax.splashy.platform.Services;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.SplashRenderer;
import net.minecraft.client.resources.SplashManager;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Mixin(value = SplashManager.class)
public class MixinSplashManager {

    @Unique
    private static final SplashRenderer JARED_BIRTHDAY = new SplashRenderer("Happy Birthday Jared <3");

    @Final
    @Shadow
    private List<String> splashes;

    @Inject(method = "apply(Ljava/util/List;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At("RETURN"))
    private void apply(List<String> vanillaValues, ResourceManager resources, ProfilerFiller profiler, CallbackInfo ci) {

        final Config config = Config.load();

        // Config option to remove vanilla entries.
        if (!config.includeVanillaSplashes) {

            splashes.removeAll(vanillaValues);
        }

        // Include the names of installed mods.
        if (config.includeModNames) {

            splashes.addAll(Services.HELPER.getLoadedMods().stream().filter(s -> !s.contains("Fabric") && !s.contains("API")).toList());
        }

        // Variables for custom splashes.
        final String username = Minecraft.getInstance().getUser().getName();
        final String version = Minecraft.getInstance().getLaunchedVersion();

        // Load custom splashes from config file.
        for (String customSplash : config.customSplashes) {

            // Attempt to localize the tip.
            if (I18n.exists(customSplash)) {

                customSplash = I18n.get(customSplash, username, version);
            }

            splashes.add(customSplash);
        }
    }

    @Inject(method = "getSplash()Lnet/minecraft/client/gui/components/SplashRenderer;", at = @At("RETURN"), cancellable = true)
    private void getSplash(CallbackInfoReturnable<SplashRenderer> cir) {

        // Special Days
        final LocalDateTime now = LocalDateTime.now();

        if (now.getMonth() == Month.JUNE && now.getDayOfMonth() == 29) {

            cir.setReturnValue(JARED_BIRTHDAY);
        }
    }
}