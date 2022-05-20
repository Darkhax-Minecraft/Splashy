package net.darkhax.splashy.platform;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.nio.file.Path;
import java.util.List;

public interface IHelper {

    List<String> getLoadedMods();

    Path getConfigDir();
}
