package net.darkhax.splashy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import net.darkhax.splashy.platform.Services;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class Config {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    @Expose
    public boolean includeVanillaSplashes = true;

    @Expose
    public boolean includeModNames = true;

    @Expose
    public List<String> customSplashes = List.of(
            "splash.splashy.squirrels",
            "splash.splashy.direwolf",
            "splash.splashy.error_1",
            "splash.splashy.potato_knishes",
            "splash.splashy.snail_facts",
            "splash.splashy.blm",
            "splash.splashy.blame_jared",
            "splash.splashy.tips",
            "splash.splashy.birch_forest",
            "splash.splashy.multiloader"
    );


    public static Config load() {

        final File configFile = Services.HELPER.getConfigDir().resolve(Constants.MOD_ID + ".json").toFile();
        Config config = new Config();

        // Attempt to load existing config file
        if (configFile.exists()) {

            try (FileReader reader = new FileReader(configFile)) {

                config = GSON.fromJson(reader, Config.class);
                Constants.LOG.info("Loaded config file.");
            }

            catch (Exception e) {

                Constants.LOG.error("Could not read config file {}. Defaults will be used.", configFile.getAbsolutePath());
                Constants.LOG.catching(e);
            }
        }

        else {

            Constants.LOG.info("Creating a new config file at {}.", configFile.getAbsolutePath());
            configFile.getParentFile().mkdirs();
        }

        try (FileWriter writer = new FileWriter(configFile)) {

            GSON.toJson(config, writer);
            Constants.LOG.info("Saved config file.");
        }

        catch (Exception e) {

            Constants.LOG.error("Could not write config file '{}'!", configFile.getAbsolutePath());
            Constants.LOG.catching(e);
        }

        return config;
    }
}