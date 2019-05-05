package com.valeriotor.BTV.events;

import com.valeriotor.BTV.capabilities.PlayerDataProvider;
import com.valeriotor.BTV.lib.PlayerDataLib;
import com.valeriotor.BTV.network.BTVPacketHandler;
import com.valeriotor.BTV.network.MessageSyncDataToClient;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.capabilities.IPlayerKnowledge.EnumKnowledgeType;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchEvent.Research;

public class ResearchEvents {
	
	@SubscribeEvent
	public void wakeUpEvent(PlayerWakeUpEvent event) {
		EntityPlayer p = event.getEntityPlayer();
		IPlayerKnowledge k = ThaumcraftCapabilities.getKnowledge(p);
		if(p.world.getWorldTime() < 23900) return;
		if(!k.isResearchComplete("FIRSTDREAMS")) {
			ThaumcraftApi.internalMethods.progressResearch(p, "didDream");
		}
	}
	
	@SubscribeEvent
	public void researchEvent(Research event) {
		System.out.println(event.getResearchKey());
		switch(event.getResearchKey()) {
		case "!minecraft:water_bucket0":
			ThaumcraftApi.internalMethods.addKnowledge(event.getPlayer(), EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("BEYOND_THE_VEIL"), 16);
			break;
		case "f_AlienisDream":
			event.getPlayer().getCapability(PlayerDataProvider.PLAYERDATA, null).addString(PlayerDataLib.SEEKSKNOWLEDGE, false);
			BTVPacketHandler.INSTANCE.sendTo(new MessageSyncDataToClient(PlayerDataLib.SEEKSKNOWLEDGE), (EntityPlayerMP)event.getPlayer());
			break;
		case "FISHINGHAMLET":
			if(ThaumcraftCapabilities.knowsResearchStrict(event.getPlayer(), "FISHINGHAMLET@1")) {
				if(event.getPlayer().getCapability(PlayerDataProvider.PLAYERDATA, null).getString("dialoguegratitude")) {
					event.getPlayer().getCapability(PlayerDataProvider.PLAYERDATA, null).addString("dialoguedreamer", false);
					BTVPacketHandler.INSTANCE.sendTo(new MessageSyncDataToClient("dialoguedreamer"), (EntityPlayerMP)event.getPlayer());
				}	
			}
		}
	}
	
}
