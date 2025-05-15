package net.mehvahdjukaar.modelfix;

import net.mehvahdjukaar.modelfix.config.ModelFixConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(
        modid = ModelFix.ID,
        name = ModelFix.NAME,
        version = ModelFix.VERSION
)
public class ModelFix {
    public static final String ID = "@MOD_ID@";
    public static final String NAME = "@MOD_NAME@";
    public static final String VERSION = "@MOD_VERSION@";

    public static ModelFixConfig config;

    @Mod.EventHandler
    public void initialize(FMLInitializationEvent event) {
        config = new ModelFixConfig();
    }
}
