package com.SkullPreventionReminder;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class SkullPreventionReminderPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(SkullPreventionReminderPlugin.class);
		RuneLite.main(args);
	}
}