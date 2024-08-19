package com.nineteen.talesofvarmithore.setup.common;

import com.nineteen.talesofvarmithore.setup.common.registries.RegistryLoader;
import com.nineteen.talesofvarmithore.setup.common.registries.TVEntities;
import com.nineteen.talesofvarmithore.setup.common.registries.TVItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import software.bernie.geckolib.GeckoLib;

public final class CommonSetup {

    public static void onModCreate(IEventBus forgeBus, IEventBus modBus) {
        GeckoLib.initialize();
        RegistryLoader.load(forgeBus, modBus);
        modBus.addListener(TVEntities::createEntityAttributes);
        modBus.addListener(CommonSetup::addCreativeTab);
    }

    public static void setup(final FMLCommonSetupEvent event) {
    }

    private static void addCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) event.accept(TVItems.WILKOR_SPAWN_EGG);
    }

    private CommonSetup(){}
}
