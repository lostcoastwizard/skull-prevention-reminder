package com.SkullPreventionReminder;

import org.junit.Test;
import static org.junit.Assert.*;

public class SkullPreventionReminderPluginTest
{
	@Test
	public void testPluginInitialization()
	{
		SkullPreventionReminderPlugin plugin = new SkullPreventionReminderPlugin();
		assertNotNull(plugin);
	}
}
