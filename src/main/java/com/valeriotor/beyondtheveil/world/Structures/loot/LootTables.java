package com.valeriotor.beyondtheveil.world.Structures.loot;

import com.valeriotor.beyondtheveil.lib.References;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTables {
	
	public static final ResourceLocation hamletStoreHouse1 = LootTableList.register(new ResourceLocation(References.MODID, "storehouse1"));
	
	public static final ResourceLocation hamletLightHouse = LootTableList.register(new ResourceLocation(References.MODID, "light_house"));
	
	public static final ResourceLocation hamletTownHall = LootTableList.register(new ResourceLocation(References.MODID, "town_hall"));
	
	public static final ResourceLocation tablet = LootTableList.register(new ResourceLocation(References.MODID, "inject/tablet"));
	
}
