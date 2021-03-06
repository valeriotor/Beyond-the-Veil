package com.valeriotor.beyondtheveil.gui;

import java.io.IOException;
import java.util.List;

import com.google.common.collect.Lists;
import com.valeriotor.beyondtheveil.blocks.BlockFumeSpreader;
import com.valeriotor.beyondtheveil.blocks.BlockRegistry;
import com.valeriotor.beyondtheveil.blocks.BlockSleepChamber;
import com.valeriotor.beyondtheveil.capabilities.PlayerDataProvider;
import com.valeriotor.beyondtheveil.dreaming.DreamHandler;
import com.valeriotor.beyondtheveil.lib.PlayerDataLib;
import com.valeriotor.beyondtheveil.lib.References;
import com.valeriotor.beyondtheveil.network.BTVPacketHandler;
import com.valeriotor.beyondtheveil.network.MessageSleepChamber;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSleepingChamber extends GuiChat{
	private int timePassed = 0;
	
	public void initGui()
    {
        super.initGui();
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height - 40, I18n.format("gui.sleep_chamber.wake")));
    }
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawRect(0, 0, this.width, this.height, (int)(((float)this.timePassed)/100 * 255) << 24);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void updateScreen() {
		if(this.timePassed < 100) this.timePassed++;
		if(this.timePassed == 100) {
			BTVPacketHandler.INSTANCE.sendToServer(new MessageSleepChamber(true));
			this.timePassed++;
			return;
		}
		super.updateScreen();
	}
	
	private Block searchChamber() {
		for(int x = -1; x <= 1; x++) {
			for(int z = -1; z <= 1; z++) {
				Block b = this.mc.player.world.getBlockState(this.mc.player.getPosition().add(x, 0, z)).getBlock();
				if(b instanceof BlockSleepChamber) return b;
			}
		}
		return null;
	}
	
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button.id == 1) {
			BTVPacketHandler.INSTANCE.sendToServer(new MessageSleepChamber(false));
			this.mc.displayGuiScreen((GuiScreen)null);
		}
		super.actionPerformed(button);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if(keyCode == 1) {
			BTVPacketHandler.INSTANCE.sendToServer(new MessageSleepChamber(false));
			this.mc.displayGuiScreen((GuiScreen)null);
		}else if (keyCode != 28 && keyCode != 156)
        {
            super.keyTyped(typedChar, keyCode);
        }
        else
        {
            String s = this.inputField.getText().trim();

            if (!s.isEmpty())
            {
                this.sendChatMessage(s);
            }

            this.inputField.setText("");
            this.mc.ingameGUI.getChatGUI().resetScroll();
        }
	}
}
