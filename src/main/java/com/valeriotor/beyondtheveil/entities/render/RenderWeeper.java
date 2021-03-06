package com.valeriotor.beyondtheveil.entities.render;

import com.valeriotor.beyondtheveil.entities.EntityWeeper;
import com.valeriotor.beyondtheveil.entities.models.ModelRegistry;
import com.valeriotor.beyondtheveil.entities.models.ModelWeeper;
import com.valeriotor.beyondtheveil.lib.References;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderWeeper extends RenderLiving<EntityWeeper>{

	public RenderWeeper(RenderManager rendermanagerIn) {
		super(rendermanagerIn, ModelRegistry.weeper, 0.5F);
	}

	private static final ResourceLocation texture = new ResourceLocation(References.MODID, "textures/entity/weeper.png");
	
	@Override
	protected ResourceLocation getEntityTexture(EntityWeeper entity) {
		return texture;
	}

}
