package net.dice7000.spawn_suppressor;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(SpawnSuppressor.MOD_ID) public class SpawnSuppressor {
    public static final String MOD_ID = "spawn_suppressor";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SpawnSuppressor(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        ItemRegistry.ITEMS.register(modEventBus);
        modEventBus.addListener(this::commonSetup); modEventBus.addListener(this::addCreative);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("SpawnSuppressor has been loaded. suppressSpawn is set to False by default.");}
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) event.accept(ItemRegistry.SPAWN_SUPPRESSOR);
    }
}
