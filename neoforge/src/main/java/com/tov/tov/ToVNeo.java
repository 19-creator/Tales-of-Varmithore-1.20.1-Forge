package com.tov.tov;


import com.tov.tov.registries.VariantProvider;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import software.bluelib.event.ReloadHandler;

@Mod(Constants.MOD_ID)
public class ToVNeo {

    public ToVNeo(IEventBus eventBus) {
        ReloadHandler.registerProvider(new VariantProvider());
        CommonClass.init();
    }
}