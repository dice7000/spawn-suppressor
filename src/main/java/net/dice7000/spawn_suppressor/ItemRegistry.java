package net.dice7000.spawn_suppressor;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SpawnSuppressor.MOD_ID);
    public static final RegistryObject<Item> SPAWN_SUPPRESSOR = ITEMS.register("spawn_suppressor", SpawnSuppressorItem::new);

    public static class SpawnSuppressorItem extends Item {
        public SpawnSuppressorItem() {
            super(new Item.Properties());
        }
        @Override public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
            if (!p_41432_.isClientSide && p_41432_ instanceof ServerLevel) {
                ((SpawnSuppressorMethod) p_41432_).spawn_suppressor$toggleSpawn();
                boolean ss = ((SpawnSuppressorMethod) p_41432_).spawn_suppressor$getSuppressSpawn();
                SpawnSuppressor.LOGGER.info("Set suppressSpawn to {}.", ss);
                p_41433_.sendSystemMessage(Component.literal("SpawnSuppressor: Set suppressSpawn to " + ss + "."));
                p_41432_.playSound(null, p_41433_.getX(), p_41433_.getY(), p_41433_.getZ(),
                        SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1.0F, 1.0F); }
            return super.use(p_41432_, p_41433_, p_41434_);
        }

    }
}
