package com.skull_prevention_reminder;

import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Varbits;
import lombok.extern.slf4j.Slf4j;
import com.google.inject.Provides;
import net.runelite.api.events.VarbitChanged;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Skull Prevention Reminder"
)
public class SkullPreventionReminderPlugin extends net.runelite.client.plugins.Plugin
{
	private boolean skullPreventionEnabled = false;
	private final int SKULL_PREVENTION_VARBIT_ID = 13131;

	@Inject
	private Client client;

	@Inject
	private SkullPreventionReminderConfig config;

	@Inject
	private SkullPreventionReminderOverlay overlay;

	@Inject
	private OverlayManager overlayManager;

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
	}

	@Subscribe
	void onVarbitChanged(VarbitChanged varbitChanged) {
		if (varbitChanged.getVarbitId() == SKULL_PREVENTION_VARBIT_ID) { // SKULL_PREVENTION_VARBIT_ID =
			skullPreventionEnabled =  (varbitChanged.getValue() != 0);
		}
	}

	@Subscribe
	void onConfigChanged(ConfigChanged configChanged) {
		if (configChanged.getKey().equals(SkullPreventionReminderConfig.SIZE_KEY)) {
			overlay.reloadImages(config.scale());
		}
	}

	@Provides
	SkullPreventionReminderConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SkullPreventionReminderConfig.class);
	}

	public boolean skullPreventionEnabled() {
		return skullPreventionEnabled;
	}

	public boolean isInPVP() {
		return client.getVarbitValue(Varbits.IN_WILDERNESS) == 1 ||
				client.getVarbitValue(Varbits.PVP_SPEC_ORB) == 1;
	}

}
