package net.gookieg.dying_world.common.registry;

import net.gookieg.dying_world.DyingWorldMod;
import net.gookieg.dying_world.common.item.biomarker_item;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DW_Items {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DyingWorldMod.MOD_ID);

    // Register Items
    public static final RegistryObject<Item> BIOMARKER = ITEMS.register("biomarker", biomarker_item::new);


    // Misc register (only here to hava an icon for the creative tab)
    public static final RegistryObject<Item> MOD_ICON = ITEMS.register("mod_icon", () -> new Item(new Item.Properties().stacksTo(1).durability(0)));


    public static void init(IEventBus modEventBus) {
        ITEMS.register(modEventBus);

    }
}
