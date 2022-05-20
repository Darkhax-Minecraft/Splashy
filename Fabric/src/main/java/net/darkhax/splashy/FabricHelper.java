package net.darkhax.splashy;

import net.darkhax.splashy.platform.IHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FabricHelper implements IHelper {

    @Override
    public List<String> getLoadedMods() {

        return FabricLoader.getInstance().getAllMods().stream().map(mod -> mod.getMetadata().getName()).collect(Collectors.toList());
    }

    @Override
    public Path getConfigDir() {

        return FabricLoader.getInstance().getConfigDir();
    }
}