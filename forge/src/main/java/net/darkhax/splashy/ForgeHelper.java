package net.darkhax.splashy;

import net.darkhax.splashy.platform.IHelper;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.forgespi.language.IModInfo;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class ForgeHelper implements IHelper {

    @Override
    public List<String> getLoadedMods() {

        return ModList.get().getMods().stream().map(IModInfo::getDisplayName).collect(Collectors.toList());
    }

    @Override
    public Path getConfigDir() {

        return FMLPaths.CONFIGDIR.get();
    }
}