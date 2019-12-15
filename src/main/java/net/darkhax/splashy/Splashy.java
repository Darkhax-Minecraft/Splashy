package net.darkhax.splashy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.util.Splashes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.config.ModConfig.Type;

@Mod(Splashy.MOD_ID)
public class Splashy {
    
    public static final String MOD_ID = "splashy";
    private final ConfigurationHandler config = new ConfigurationHandler();
    private final Random rand = new Random();
    
    public Splashy() {
        
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            
            ModLoadingContext.get().registerConfig(Type.CLIENT, this.config.getSpec());
            MinecraftForge.EVENT_BUS.addListener(this::onGuiInit);
        });
    }
    
    @OnlyIn(Dist.CLIENT)
    private void onGuiInit (InitGuiEvent.Post event) {
        
        if (event.getGui() instanceof MainMenuScreen) {
            
            // Build the splash pool from scratch. This is fairly inexpensive and gives us
            // better control over how the splash list is generated and displayed.
            final List<String> splashPool = this.buildSplashPool();
            
            // Generate a random splash. If no splashes are found it will go to a default
            // fallback splash.
            final String randomSplash = !splashPool.isEmpty() ? splashPool.get(this.rand.nextInt(splashPool.size())) : "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
            
            // Set the splash text. Using reflection for this because ATs are still a bit
            // sketchy and performance cost here is negligible.
            ObfuscationReflectionHelper.setPrivateValue(MainMenuScreen.class, (MainMenuScreen) event.getGui(), randomSplash, "field_73975_c");
        }
    }
    
    @OnlyIn(Dist.CLIENT)
    private List<String> buildSplashPool () {
        
        final List<String> splashPool = new ArrayList<>();
        
        // Grabs splashes from the vanilla splash pool and adds them to the current pool.
        if (this.config.getIncludeVanillaSplashes()) {
            
            final Splashes splashes = Minecraft.getInstance().getSplashes();
            final List<String> vanillaSplahse = ObfuscationReflectionHelper.getPrivateValue(Splashes.class, splashes, "field_215280_c");
            
            if (splashes != null) {
                
                splashPool.addAll(vanillaSplahse);
            }
        }
        
        // Grabs display names from the mod list and adds them to the splash pool.
        if (this.config.getIncludeModNameSplashes()) {
            
            ModList.get().forEachModContainer( (id, meta) -> splashPool.add(meta.getModInfo().getDisplayName()));
        }
        
        // Adds any custom splashes to the splash pool.
        splashPool.addAll(this.config.getCustomSplashes());
        
        return splashPool;
    }
}