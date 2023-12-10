package net.tefyer.terratech.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.tefyer.terratech.TerraTechMod;
import net.tefyer.terratech.client.menu.PowerBrickMenu;

public class PowerBrickScreen extends AbstractContainerScreen<PowerBrickMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(TerraTechMod.MODID,"textures/gui/power_brick_gui.png");

    @Override
    protected void init() {
        super.init();
    }

    public PowerBrickScreen(PowerBrickMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);

        drawEnergy(pPoseStack,x,y);

    }
    public void drawEnergy(PoseStack pPoseStack,int x,int y){
        if(menu.hasEnergy())
            blit(pPoseStack,x+17,y+13,11,176,134, menu.getEnergy()/6000);
    }



    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}
