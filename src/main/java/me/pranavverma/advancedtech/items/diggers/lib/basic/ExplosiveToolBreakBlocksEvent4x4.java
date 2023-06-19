package me.pranavverma.advancedtech.items.diggers.lib.basic;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.apache.commons.lang.Validate;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

/**
 * This {@link Event} is called when an {@link me.pranavverma.advancedtech.items.diggers.lib.basic.ExplosiveTool4x4} is used to break blocks.
 *
 * @author GallowsDove
 *
 * @see me.pranavverma.advancedtech.items.diggers.lib.basic.ExplosiveTool4x4
 *
 */
public class ExplosiveToolBreakBlocksEvent4x4 extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private final ItemStack itemInHand;
    private final me.pranavverma.advancedtech.items.diggers.lib.basic.ExplosiveTool4x4 explosiveTool4x4;
    private final Block mainBlock;
    private final List<Block> additionalBlocks;
    private boolean cancelled;

    @ParametersAreNonnullByDefault
    public ExplosiveToolBreakBlocksEvent4x4(Player player, Block block, List<Block> blocks, ItemStack item, me.pranavverma.advancedtech.items.diggers.lib.basic.ExplosiveTool4x4 explosiveTool4x4) {
        super(player);

        Validate.notNull(block, "The center block cannot be null!");
        Validate.notNull(blocks, "Blocks cannot be null");
        Validate.notNull(item, "Item cannot be null");
        Validate.notNull(explosiveTool4x4, "ExplosiveTool4x4 cannot be null");

        this.mainBlock = block;
        this.additionalBlocks = blocks;
        this.itemInHand = item;
        this.explosiveTool4x4 = explosiveTool4x4;
    }

    /**
     * This returns the primary {@link Block} that was broken.
     * This {@link Block} triggered this {@link Event} and is not included
     * in {@link #getAdditionalBlocks()}.
     *
     * @return The primary broken {@link Block}
     */
    @Nonnull
    public Block getPrimaryBlock() {
        return this.mainBlock;
    }

    /**
     * Gets the {@link Block} {@link List} of blocks destroyed in this event.
     *
     * @return The broken blocks
     */
    @Nonnull
    public List<Block> getAdditionalBlocks() {
        return this.additionalBlocks;
    }

    /**
     * Gets the {@link me.pranavverma.advancedtech.items.diggers.lib.basic.ExplosiveTool4x4} which triggered this event
     *
     * @return the {@link me.pranavverma.advancedtech.items.diggers.lib.basic.ExplosiveTool4x4} that was involved
     */
    @Nonnull
    public me.pranavverma.advancedtech.items.diggers.lib.basic.ExplosiveTool4x4 getExplosiveTool() {
        return this.explosiveTool4x4;
    }

    /**
     * Gets the {@link ItemStack} of the tool used to destroy this block
     *
     * @return The {@link ItemStack} in the hand of the {@link Player}
     */
    @Nonnull
    public ItemStack getItemInHand() {
        return this.itemInHand;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Nonnull
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Nonnull
    @Override
    public HandlerList getHandlers() {
        return getHandlerList();
    }
}