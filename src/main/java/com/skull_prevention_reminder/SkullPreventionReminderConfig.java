package com.skull_prevention_reminder;

import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("Skull Prevention Reminder")
public interface SkullPreventionReminderConfig extends net.runelite.client.config.Config
{
	@ConfigItem(
			keyName = "pvpOnly",
			name = "PVP only",
			description = "Only display overlay icon in pvp"
	)
	default boolean pvpOnly() { return false; }

	String SIZE_KEY = "scale";
	@ConfigItem(
			keyName = SIZE_KEY,
			name = "Scale",
			description = "The scale of the protect item icon.")
	default int scale() { return 69; }

}
