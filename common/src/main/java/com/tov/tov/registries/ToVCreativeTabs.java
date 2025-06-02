package com.tov.tov.registries;

import net.minecraft.world.item.CreativeModeTab;
import java.util.function.Supplier;
import static com.tov.tov.CommonClass.REGISTRY;
import static software.bluelib.api.registry.builders.tabs.CreativeTabBuilder.*;

public class ToVCreativeTabs {
    public static void init() {}

    public static final Supplier<CreativeModeTab> BDD_MAIN = REGISTRY
            .tab("tales_of_varmithore")
            .icon(useSpawnEgg(ToVEntities.TANTANIUM))
            .displayItems((pParameters, populator) -> {
                addAllSpawnEggs(populator);
            }).register();
}
