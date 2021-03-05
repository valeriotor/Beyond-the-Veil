package com.valeriotor.beyondtheveil.animations;

import java.util.ArrayList;
import java.util.List;

public class AnimationRegistry {
	
	public static AnimationTemplate deep_one_roar;
	public static AnimationTemplate shoggoth_open_mouth;
	public static AnimationTemplate shoggoth_eye_tentacle;
	public static AnimationTemplate blood_zombie_idle;
	public static AnimationTemplate blood_skeleton_idle;
	public static AnimationTemplate blood_skeleton_spook;
	public static AnimationTemplate blood_skeleton_left_swing;
	public static AnimationTemplate blood_skeleton_right_swing;
	public static AnimationTemplate blood_zombie_left_swing;
	public static AnimationTemplate blood_zombie_right_swing;
	public static AnimationTemplate deep_one_right_swing;
	public static AnimationTemplate deep_one_left_swing;
	public static AnimationTemplate crazed_weeper_transform;
	public static AnimationTemplate surgeon_surgery;
	public static AnimationTemplate surgeon_attack;
	public static AnimationTemplate muray_bite;
	public static AnimationTemplate deep_one_brute_left_swing;
	public static AnimationTemplate deep_one_brute_right_swing;
	public static AnimationTemplate deep_one_brute_right_followup_swing;
	public static AnimationTemplate deep_one_brute_left_followup_swing;
	public static AnimationTemplate deep_one_brute_smash;
	public static AnimationTemplate deep_one_brute_roar_followup;
	public static AnimationTemplate deep_one_myrmidon_spear_impale;
	public static AnimationTemplate deep_one_myrmidon_sword_swing;
	public static AnimationTemplate deep_one_myrmidon_spear_impale_followup_spear_impale;
	public static AnimationTemplate deep_one_myrmidon_sword_swing_followup_sword_impale;
	private static List<AnimationTemplate> anims = new ArrayList<>();
	
	public static void loadAnimations() {
		blood_skeleton_left_swing = new AnimationTemplate("blood_skeleton_left_swing");
		blood_skeleton_right_swing = new AnimationTemplate("blood_skeleton_right_swing");
		blood_skeleton_idle = new AnimationTemplate("blood_skeleton_idle");
		blood_skeleton_spook = new AnimationTemplate("blood_skeleton_spook");
		blood_zombie_idle = new AnimationTemplate("blood_zombie_idle");
		deep_one_roar = new AnimationTemplate("deep_one_roar");
		shoggoth_open_mouth = new AnimationTemplate("shoggoth_open_mouth");
		shoggoth_eye_tentacle = new AnimationTemplate("shoggoth_eye_tentacle");
		deep_one_left_swing = new AnimationTemplate("deep_one_left_swing");
		deep_one_right_swing = new AnimationTemplate("deep_one_right_swing");
		crazed_weeper_transform = new AnimationTemplate("crazed_weeper_transform");
		blood_zombie_left_swing = new AnimationTemplate("blood_zombie_left_swing");
		blood_zombie_right_swing = new AnimationTemplate("blood_zombie_right_swing");
		surgeon_surgery = new AnimationTemplate("surgeon_surgery");
		surgeon_attack = new AnimationTemplate("surgeon_attack");
		muray_bite = new AnimationTemplate("muray_bite");
		deep_one_brute_left_swing = new AnimationTemplate("deep_one_brute/left_swing");
		deep_one_brute_right_swing = new AnimationTemplate("deep_one_brute/right_swing");
		deep_one_brute_right_followup_swing = new AnimationTemplate("deep_one_brute/right_followup_swing");
		deep_one_brute_left_followup_swing = new AnimationTemplate("deep_one_brute/left_followup_swing");
		deep_one_brute_smash = new AnimationTemplate("deep_one_brute/smash");
		deep_one_brute_roar_followup = new AnimationTemplate("deep_one_brute/roar_followup");
		deep_one_myrmidon_spear_impale = new AnimationTemplate("deep_one_myrmidon/spear_impale");
		deep_one_myrmidon_sword_swing = new AnimationTemplate("deep_one_myrmidon/sword_swing");
		deep_one_myrmidon_spear_impale_followup_spear_impale = new AnimationTemplate("deep_one_myrmidon/spear_impale_followup_spear_impale");
		deep_one_myrmidon_sword_swing_followup_sword_impale = new AnimationTemplate("deep_one_myrmidon/sword_swing_followup_sword_impale");
		anims.clear();
		anims.add(deep_one_roar);
		anims.add(shoggoth_open_mouth);
		anims.add(shoggoth_eye_tentacle);
		anims.add(blood_zombie_idle);
		anims.add(blood_skeleton_idle);
		anims.add(blood_skeleton_spook);
		anims.add(deep_one_left_swing);
		anims.add(deep_one_right_swing);
		anims.add(blood_skeleton_left_swing);
		anims.add(blood_skeleton_right_swing);
		anims.add(crazed_weeper_transform);
		anims.add(blood_zombie_left_swing);
		anims.add(blood_zombie_right_swing);
		anims.add(surgeon_surgery);
		anims.add(surgeon_attack);
		anims.add(muray_bite);
		anims.add(deep_one_brute_left_swing);
		anims.add(deep_one_brute_right_swing);
		anims.add(deep_one_brute_right_followup_swing);
		anims.add(deep_one_brute_left_followup_swing);
		anims.add(deep_one_brute_smash);
		anims.add(deep_one_brute_roar_followup);
		anims.add(deep_one_myrmidon_spear_impale);
		anims.add(deep_one_myrmidon_sword_swing);
		anims.add(deep_one_myrmidon_spear_impale_followup_spear_impale);
		anims.add(deep_one_myrmidon_sword_swing_followup_sword_impale);
	}
	
	public static int getIdFromAnimation(AnimationTemplate anim) {
		for(int i = 0; i < anims.size(); i++) {
			if(anims.get(i) == anim)
				return i;
		}
		return -1;
	}
	
	public static AnimationTemplate getAnimationFromId(int id) {
		if(id >= 0 && id < anims.size())
			return anims.get(id);
		return null;
	}
	
}
