package com.valeriotor.BTV.gui;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.valeriotor.BTV.capabilities.IPlayerData;
import com.valeriotor.BTV.capabilities.PlayerDataProvider;
import com.valeriotor.BTV.capabilities.ResearchProvider;
import com.valeriotor.BTV.lib.References;
import com.valeriotor.BTV.research.Research;
import com.valeriotor.BTV.research.ResearchConnection;
import com.valeriotor.BTV.research.ResearchRegistry;
import com.valeriotor.BTV.research.ResearchStatus;
import com.valeriotor.BTV.util.MathHelperBTV;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiNecronomicon extends GuiScreen{
	
	int topX = -400;
	int topY = -200;
	int factor = 3;
	List<Research> clickables = new ArrayList<>();
	List<Research> visibles = new ArrayList<>();
	List<ResearchConnection> connections = new ArrayList<>();
	int counter = 0;
	
	private static final ResourceLocation RESEARCH_BACKGROUND = new ResourceLocation(References.MODID, "textures/gui/res_background.png");
	
	public GuiNecronomicon() {
		HashMap<String, ResearchStatus> map = Minecraft.getMinecraft().player.getCapability(ResearchProvider.RESEARCH, null).getResearches();
		IPlayerData data = Minecraft.getMinecraft().player.getCapability(PlayerDataProvider.PLAYERDATA, null);
		for(Entry<String, ResearchStatus> entry : map.entrySet()) {
			if(entry.getValue().isKnown(map, data)) {
				clickables.add(entry.getValue().res);
			}
			else if(entry.getValue().isVisible(map, data))
				visibles.add(entry.getValue().res);
		}
		
		for(ResearchConnection rc: ResearchRegistry.connections) {
			if(rc.isVisible(map, data)) {
				connections.add(rc);
			}
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawRect(0, 0, this.width, this.height, 0xFF000000); 
		for(ResearchConnection rc : connections)
			this.drawConnection(rc, partialTicks);
		
		GlStateManager.color(1, 1, 1);
		mc.renderEngine.bindTexture(RESEARCH_BACKGROUND);
		for(Research r : clickables) this.drawResearchBackground(r);
		GlStateManager.color(0.6F, 0.6F, 0.6F);
		for(Research r : visibles) this.drawResearchBackground(r);

		RenderHelper.enableStandardItemLighting();
		for(Research r : clickables) this.drawResearch(r, mouseX, mouseY);
		for(Research r : visibles) this.drawResearch(r, mouseX, mouseY);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void updateScreen() {
		this.counter++;
		super.updateScreen();
	}
	
	@Override
	public void handleMouseInput() throws IOException {
		if(Mouse.isButtonDown(0)) {
			topX = MathHelperBTV.clamp(-700, 3840 - this.width/2, topX - Mouse.getDX());
			topY = MathHelperBTV.clamp(-700, 2160 - this.height/2, topY + Mouse.getDY());
		}
		this.factor = MathHelperBTV.clamp(2, 5, this.factor + (int)Math.signum(Mouse.getDWheel()));
		super.handleMouseInput();
	}
	
	
	private void drawResearch(Research res, int mouseX, int mouseY) {
		int resX = res.getX() * 15 * factor, resY = res.getY() * 15 * factor;
		if(resX > topX - 24 && resX < topX + this.width && resY > topY - 24 && resY < topY + this.height) {		
			ItemStack[] icons = res.getIconStacks();
			this.drawItemStack(icons[counter % 20 % icons.length], resX - topX, resY - topY);
			if(mouseX > resX - topX - 4 && mouseX < resX - topX + 20 && mouseY > resY - topY - 4 && mouseY < resY - topY + 20) {
				//GlStateManager.pushAttrib();
				drawHoveringText(I18n.format(res.getName()), mouseX, mouseY);
				//GlStateManager.popAttrib();
			}
		}
	}
	
	private void drawResearchBackground(Research res) {
		int resX = res.getX() * 15 * factor, resY = res.getY() * 15 * factor;
		if(resX > topX - 24 && resX < topX + this.width && resY > topY - 24 && resY < topY + this.height) {
			drawModalRectWithCustomSizedTexture(resX - topX - 4, resY - topY - 4, 0, 0, 24, 24, 24, 24);	
		}
	}
	
	private void drawConnection(ResearchConnection rc, float partialTicks) {
		if(rc.shouldRender(topX, topY, width, height)) {
			Point left = rc.getLeftPoint(), right = rc.getRightPoint();
			double dist = left.distance(right) * 15 * factor;
			int lx = left.x * 15 * factor, ly = left.y * 15 * factor, rx = right.y * 15 * factor, ry = right.y * 15 * factor;
			GlStateManager.pushMatrix();
			double phi = Math.asin((right.y - left.y)*15*factor/dist);
			GlStateManager.translate(lx - topX + 8, ly - topY + 8, 0);
			GlStateManager.rotate((float)(phi * 180 / Math.PI), 0F, 0F, 1F);
			for(int i = 0; i < dist; i++) {
				int signum = (int) Math.signum(counter % 80 - 40);
				double amplifier = 15 * (signum  * Math.pow((counter % 40 + partialTicks) / 20 - 1, 4) - signum);
				int x = i, y = (int) (amplifier * Math.sin(i * Math.PI / dist));
				//GL11.glBegin(GL11.GL_LINES);
				drawRect(x, y, x + 1, y+1, 0xFF001100);
			}
			GlStateManager.popMatrix();
			
		}
	}
	
	
	// Shamelessly (CTRL+C-CTRL+V)ed from GuiContainer
	private void drawItemStack(ItemStack stack, int x, int y)
    {
        //GlStateManager.translate(0.0F, 0.0F, 32.0F);
        //this.itemRender.renderItemOverlayIntoGUI(font, stack, x, y - (this.draggedStack.isEmpty() ? 0 : 8), altText);
        //GlStateManager.translate(0.0F, 0.0F, 32.0F);
        //this.zLevel = 200.0F;
        //this.itemRender.zLevel = 200.0F;
        this.itemRender.renderItemAndEffectIntoGUI(stack, x, y);
        //this.zLevel = 0.0F;
        //this.itemRender.zLevel = 0.0F;
    }
}
