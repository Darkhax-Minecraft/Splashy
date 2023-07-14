package net.darkhax.splashy.platform;

import java.nio.file.Path;
import java.util.List;

public interface IHelper {

    List<String> getLoadedMods();

    Path getConfigDir();
}
