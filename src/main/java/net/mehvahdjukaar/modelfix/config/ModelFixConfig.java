package net.mehvahdjukaar.modelfix.config;

//import cc.polyfrost.oneconfig.config.Config;
//import cc.polyfrost.oneconfig.config.annotations.Button;
//import cc.polyfrost.oneconfig.config.annotations.Info;
//import cc.polyfrost.oneconfig.config.annotations.Slider;
//import cc.polyfrost.oneconfig.config.data.InfoType;
//import cc.polyfrost.oneconfig.config.data.Mod;
//import cc.polyfrost.oneconfig.config.data.ModType;

public class ModelFixConfig /*extends Config*/ {
//    public ModelFixConfig() {
//        super(new Mod("PLACEHOLDER", ModType.UTIL_QOL), "PLACEHOLDER.json");
//        initialize();
//    }
//
//    @Info(
//            text = "You shouldn't need to change anything here!",
//            type = InfoType.WARNING
//    )
//    public boolean WARNING = true;
//
//    @Button(
//            name = "Reset to default.",
//            text = "Reset",
//            description = "Resets the Recess and Expansion back to the default."
//    )
//    public Runnable BUTTON = () -> {
//        RECESS = 0.7f;
//        EXPANSION = 0.8f;
//    };
//
//    @Slider(
//            name = "Recess",
//            description = "Quad x/y offset.",
//            min = -10f,
//            max = 10f
//    )
//    public float RECESS = 0.7f;
//
//    @Slider(
//            name = "Expansion",
//            description = "Quad expansion increment.",
//            min = -10f,
//            max = 10f
//    )
//    public float EXPANSION = 0.8f;

    public float getRECESS() {
        return 0.007f;
    }

    public float getEXPANSION() {
        return 0.008f;
    }
}
