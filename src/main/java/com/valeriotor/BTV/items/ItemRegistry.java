package com.valeriotor.BTV.items;

import com.valeriotor.BTV.proxy.ClientProxy;
import com.valeriotor.BTV.blocks.BlockRegistry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@ObjectHolder("beyondtheveil")
public class ItemRegistry {
	
	
	public static final TestItem testItem = new TestItem();
	public static final ItemOniricIncense oniricIncense = new ItemOniricIncense();
	public static final SlugCatcher slugCatcher = new SlugCatcher();
	public static final ItemSlug slug = new ItemSlug();
	public static final ItemCanoe canoe = new ItemCanoe();
	public static final ItemDrink rum = new ItemDrink("drink_rum", 6, 4.0F, false);
	public static final ItemDrink wine = new ItemDrink("drink_wine", 6, 4.0F, false);
	public static final ItemDrink ale = new ItemDrink("drink_ale", 6, 4.0F, false);
	public static final ItemDrink vodka = new ItemDrink("drink_vodka", 6, 4.0F, false);
	public static final ItemDrink mead = new ItemDrink("drink_mead", 6, 4.0F, false);
	
	
    public static void initModels() {
        ClientProxy.registerItemRenderer(testItem, 0, "inventory");
        ClientProxy.registerItemRenderer(oniricIncense, 0, "inventory");
        ClientProxy.registerItemRenderer(slugCatcher, 0, "inventory");
        ClientProxy.registerItemRenderer(slug, 0, "inventory");
        ClientProxy.registerItemRenderer(canoe, 0, "inventory");
        ClientProxy.registerItemRenderer(rum, 0, "inventory");
        ClientProxy.registerItemRenderer(wine, 0, "inventory");
        ClientProxy.registerItemRenderer(ale, 0, "inventory");
        ClientProxy.registerItemRenderer(vodka, 0, "inventory");
        ClientProxy.registerItemRenderer(mead, 0, "inventory");
        
        ClientProxy.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.DampWood), 0, "inventory");
        ClientProxy.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.FumeSpreader), 0, "inventory");
        ClientProxy.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.DarkSand), 0, "inventory");
        ClientProxy.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.DampStone), 0, "inventory");
        ClientProxy.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.DampLog), 0, "inventory");
        ClientProxy.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.DampWoodStairs), 0, "inventory");
        ClientProxy.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.DampCanopy), 0, "inventory");
        ClientProxy.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.DampCanopyWood), 0, "inventory");
        ClientProxy.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.WornBricks), 0, "inventory");
        ClientProxy.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.BlockIdol), 0, "inventory");
        ClientProxy.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.BlockBarrel), 0, "inventory");
        ClientProxy.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.BlockSlugBait), 0, "inventory");
        ClientProxy.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.BlockLamp), 0, "inventory");
        ClientProxy.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.BricksBlue), 0, "inventory");
        ClientProxy.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.WornBrickStairs), 0, "inventory");
        ClientProxy.registerItemRenderer(Item.getItemFromBlock(BlockRegistry.DampWoodFence), 0, "inventory");
        
        
    }
    
    public static void registerItems(RegistryEvent.Register<Item> event) {
    	event.getRegistry().register(new ItemBlock(BlockRegistry.DampWood).setRegistryName(BlockRegistry.DampWood.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(BlockRegistry.FumeSpreader).setRegistryName(BlockRegistry.FumeSpreader.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(BlockRegistry.DarkSand).setRegistryName(BlockRegistry.DarkSand.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(BlockRegistry.DampStone).setRegistryName(BlockRegistry.DampStone.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(BlockRegistry.DampLog).setRegistryName(BlockRegistry.DampLog.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(BlockRegistry.DampWoodStairs).setRegistryName(BlockRegistry.DampWoodStairs.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(BlockRegistry.DampCanopy).setRegistryName(BlockRegistry.DampCanopy.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(BlockRegistry.DampCanopyWood).setRegistryName(BlockRegistry.DampCanopyWood.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(BlockRegistry.WornBricks).setRegistryName(BlockRegistry.WornBricks.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(BlockRegistry.BlockIdol).setRegistryName(BlockRegistry.BlockIdol.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(BlockRegistry.BlockBarrel).setRegistryName(BlockRegistry.BlockBarrel.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(BlockRegistry.BlockSlugBait).setRegistryName(BlockRegistry.BlockSlugBait.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(BlockRegistry.BlockLamp).setRegistryName(BlockRegistry.BlockLamp.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(BlockRegistry.BricksBlue).setRegistryName(BlockRegistry.BricksBlue.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(BlockRegistry.WornBrickStairs).setRegistryName(BlockRegistry.WornBrickStairs.getRegistryName()));
    	event.getRegistry().register(new ItemBlock(BlockRegistry.DampWoodFence).setRegistryName(BlockRegistry.DampWoodFence.getRegistryName()));
    	event.getRegistry().register(ItemRegistry.testItem);
    	event.getRegistry().register(ItemRegistry.oniricIncense);
    	event.getRegistry().register(ItemRegistry.slugCatcher);
    	event.getRegistry().register(ItemRegistry.slug);
    	event.getRegistry().register(ItemRegistry.canoe);
    	event.getRegistry().register(ItemRegistry.rum);
    	event.getRegistry().register(ItemRegistry.wine);
    	event.getRegistry().register(ItemRegistry.ale);
    	event.getRegistry().register(ItemRegistry.vodka);
    	event.getRegistry().register(ItemRegistry.mead);
    }
}
