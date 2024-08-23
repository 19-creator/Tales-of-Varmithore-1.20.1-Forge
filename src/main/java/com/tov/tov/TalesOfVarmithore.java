package com.tov.tov;

import com.tov.tov.setup.client.ClientSetup;
import com.tov.tov.setup.common.CommonSetup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;


@Mod(TVModInfo.MOD_ID)
public class TalesOfVarmithore {

    public TalesOfVarmithore() {
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        CommonSetup.onModCreate(forgeBus, modBus);
        if(FMLEnvironment.dist.isClient()) ClientSetup.onModCreate(forgeBus, modBus);

        modBus.addListener(CommonSetup::setup);
        if(FMLEnvironment.dist.isClient()) modBus.addListener(ClientSetup::setup);
    }
}
