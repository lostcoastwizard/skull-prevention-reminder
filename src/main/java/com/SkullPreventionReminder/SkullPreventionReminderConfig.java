package com.SkullPreventionReminder;

import net.runelite.client.config.Range;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("Skull Prevention Reminder")
public interface SkullPreventionReminderConfig extends Config
{
	@ConfigItem(
			keyName = "pvpOnly",
			name = "PVP only",
			description = "Only display overlay icon in pvp."
	)
	default boolean pvpOnly() { return false; }

	@ConfigItem(
			keyName = "displayIcon",
			name = "Display icon",
			description = "Display skull icon."
	)
	default boolean displayIcon() { return true; }

	@ConfigItem(
			keyName = "scale",
			name = "Scale",
			description = "The scale of the protect item icon."
	)
	@Range(
			min = 30,
			max = 125
	)
	default int scale() { return 30; }

	@ConfigItem(
			keyName = "displayMode",
			name = "Display Mode",
			description = "Choose when to display the skull prevention reminder."
	)
	default DisplayMode displayMode() { return DisplayMode.ALWAYS; }

	enum DisplayMode
	{
		ALWAYS,
		ONLY_WHEN_ON,
		ONLY_WHEN_OFF
	}
}