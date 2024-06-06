package com.SkullPreventionReminder;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.inject.Inject;
import net.runelite.client.util.ColorUtil;
import net.runelite.client.util.ImageUtil;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.ImageComponent;
import net.runelite.client.ui.overlay.components.LineComponent;

class SkullPreventionReminderOverlay extends OverlayPanel {
    private BufferedImage redSkull = null; // off
    private BufferedImage normalSkull = null; // on

    @Inject
    private SkullPreventionReminderPlugin plugin;

    @Inject
    private SkullPreventionReminderConfig config;

    @Inject
    private SkullPreventionReminderOverlay() {
        this.setPosition(OverlayPosition.BOTTOM_LEFT);
        this.setResizable(false);
        this.setMovable(true);
        this.setSnappable(true);
    }

    private void loadImages(int size) {
        redSkull = loadSkullImage(true, size);
        normalSkull = loadSkullImage(false, size);
    }

    private BufferedImage loadSkullImage(boolean redEyes, int size) {
        var path = "/skull" + (redEyes ? "-red" : "") + ".png";
        var img =  ImageUtil.loadImageResource(SkullPreventionReminderPlugin.class, path);
        return ImageUtil.resizeImage(img, size, size, true);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        if (config.pvpOnly() && !plugin.isInPVP()) {
            return null;
        }

        boolean skullPreventionOn = plugin.skullPreventionEnabled();

        panelComponent.setBackgroundColor(ColorUtil.colorWithAlpha(Color.BLACK, 0));
        panelComponent.setWrap(true);

        var lineComponentBuilder = LineComponent
                .builder()
                .left("SKULL PREVENTION")
                .leftColor(Color.WHITE);

        if (skullPreventionOn) {
            lineComponentBuilder.right("ON").rightColor(Color.GREEN);
            if (config.displayIcon()){
                loadImages(config.scale());
                panelComponent.getChildren().add(new ImageComponent(normalSkull));
            }
        } else {
            lineComponentBuilder.right("OFF").rightColor(Color.RED);
            if (config.displayIcon()){
                loadImages(config.scale());
                panelComponent.getChildren().add(new ImageComponent(redSkull));
            }
        }

        panelComponent.getChildren().add(lineComponentBuilder.build());
        return super.render(graphics);
    }
}