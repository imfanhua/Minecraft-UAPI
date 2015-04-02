package me.fanhua.uapi.utils;

import java.util.Random;

import org.bukkit.Color;

public class ColorUtils {
	
	private static final Random random = new Random();
	
	public static Color randomColor() {
		return Color.fromBGR(ColorUtils.random.nextInt(256), ColorUtils.random.nextInt(256), ColorUtils.random.nextInt(256));
	}
	
}
