package net.darkhax.splashy;

import net.darkhax.splashy.platform.IHelper;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforgespi.language.IModInfo;

import java.nio.file.Path;
import java.util.List;

public class NeoForgeHelper implements IHelper {

    @Override
    public List<String> getLoadedMods() {

        return ModList.get().getMods().stream().map(IModInfo::getDisplayName).toList();
    }

    @Override
    public Path getConfigDir() {

        return FMLPaths.CONFIGDIR.get();
    }
}