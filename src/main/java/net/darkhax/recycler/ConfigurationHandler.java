package net.darkhax.recycler;

import java.util.Arrays;
import java.util.List;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class ConfigurationHandler {
    
    private final ForgeConfigSpec spec;
    
    private final BooleanValue includeModNameSplashes;
    
    private final BooleanValue includeVanillaSplashes;
    
    private final ConfigValue<List<? extends String>> customSplashes;
    
    public ConfigurationHandler() {
        
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        
        // General Configs
        builder.comment("General settings for the mod.");
        builder.push("general");
        
        builder.comment("Should the names of mods be added to the pool of splashes?");
        this.includeModNameSplashes = builder.define("includeModNames", true);
        
        builder.comment("Should vanilla splash texts still be in the splash pool?");
        this.includeVanillaSplashes = builder.define("includeVanillaSplashes", true);
        
        builder.comment("A list of additional splash texts that can be displayed.");
        this.customSplashes = builder.defineList("customSplashes", Arrays.asList("Powered by Squirrels", "Take it Easy!", "scala.actors.threadpool.Arrays", "Potato Knishes", "Not Backported to 1.7.10", "Runs On Clay", "It's Balanced Because it Takes Effort!", "72 Facts About Snails"), s -> true);
        
        this.spec = builder.build();
    }
    
    public ForgeConfigSpec getSpec () {
        
        return this.spec;
    }
    
    public boolean getIncludeModNameSplashes () {
        
        return this.includeModNameSplashes.get();
    }
    
    public boolean getIncludeVanillaSplashes () {
        
        return this.includeVanillaSplashes.get();
    }
    
    public List<? extends String> getCustomSplashes () {
        
        return this.customSplashes.get();
    }
}