package net.darkhax.splashy;

import net.darkhax.splashy.platform.IHelper;
import net.fabricmc.loader.api.FabricLoader;

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