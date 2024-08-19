package com.nineteen.talesofvarmithore.setup.common.registries;

import com.nineteen.talesofvarmithore.TVModInfo;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public final class TVItems {

    static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TVModInfo.MOD_ID);

    public static final RegistryObject<Item> WILKOR_SPAWN_EGG = registerItem("wilkor_spawn_egg", () -> new ForgeSpawnEggItem(TVEntities.WILKOR, 0x948e8d, 0x3b3635, new Item.Properties()));

    static RegistryObject<Item> registerItem(String name, Supplier<? extends Item> sup){
        return ITEMS.register(name, sup);
    }

}
