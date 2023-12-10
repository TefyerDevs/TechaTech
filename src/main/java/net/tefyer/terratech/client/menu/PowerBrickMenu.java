package net.tefyer.terratech.client.menu;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.tefyer.terratech.blockentity.PowerBrickBlockEntity;
import net.tefyer.terratech.register.client.MenuTypesRegister;


public class PowerBrickMenu extends AbstractContainerMenu {
    public final PowerBrickBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    public PowerBrickMenu(int id, Inventory inv, FriendlyByteBuf extraData){
        this(id,inv,inv.player.level.getBlockEntity(extraData.readBlockPos()),new SimpleContainerData(0));
    }
    public PowerBrickMenu(int id, Inventory inv, BlockEntity blockEntity, ContainerData data){
        super(MenuTypesRegister.POWER_BRICK_MENU.get(), id);
        this.blockEntity = (PowerBrickBlockEntity) blockEntity;
        this.level = inv.player.level;
        this.data = data;
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }

    public boolean hasEnergy() {
        return getEnergy() != 0;
    }

    public int getEnergy() {
        return blockEntity.getEnergy();
    }
}
