package koks.utilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.annotation.Resource;
import java.awt.*;

/**
 * @author avox | lmao | kroko
 * @created on 02.09.2020 : 23:28
 */
public class RenderUtils {

    public void drawOutlineRect(float left, float top, float right, float bottom, int lineStrength, Color color) {
        GL11.glPushMatrix();
        GL11.glLineWidth(lineStrength);
        drawRect(GL11.GL_LINE_LOOP, left, top, right, bottom, color);
        GL11.glPopMatrix();
    }

    public void drawRect(int mode, float left, float top, float right, float bottom, Color color) {
        if (left < right) {
            float i = left;
            left = right;
            right = i;
        }

        if (top < bottom) {
            float j = top;
            top = bottom;
            bottom = j;
        }
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, color.getAlpha() / 255F);
        worldrenderer.begin(mode, DefaultVertexFormats.POSITION);
        worldrenderer.pos((double) left, (double) bottom, 0.0D).endVertex();
        worldrenderer.pos((double) right, (double) bottom, 0.0D).endVertex();
        worldrenderer.pos((double) right, (double) top, 0.0D).endVertex();
        worldrenderer.pos((double) left, (double) top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public void drawImage(ResourceLocation resourceLocation, float x, float y, int pictureWidth, int pictureHeight, boolean grayedOut) {
        GL11.glPushMatrix();
        if(grayedOut)
        GL11.glColor4f(0.5F,0.5F,0.5F,1);
        else
            GL11.glColor4f(1F,1F,1F,1);
        Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, pictureWidth, pictureHeight, pictureWidth, pictureHeight);
        GlStateManager.color(1,1,1,1);
        GL11.glPopMatrix();
    }

}