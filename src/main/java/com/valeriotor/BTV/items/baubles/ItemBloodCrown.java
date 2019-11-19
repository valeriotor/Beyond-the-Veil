package com.valeriotor.BTV.items.baubles;

import com.valeriotor.BTV.items.ModItem;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.item.ItemStack;

public class ItemBloodCrown extends ModItem implements IBauble{

	public ItemBloodCrown(String name) {
		super(name);
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.HEAD;
	}

}
