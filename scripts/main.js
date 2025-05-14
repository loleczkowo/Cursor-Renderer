Events.on(ClientLoadEvent, () => {
    Vars.ui.settings.graphics.pref("test123");

    Vars.ui.settings.graphics.sliderPref(
        "cursor-renderer-text-alpha",
        50, 0, 100, 5,
        v => v + "%"
    );

    Vars.ui.settings.graphics.sliderPref(
        "cursor-renderer-line-alpha",
        50, 0, 100, 5,
        v => v + "%"
    );

    Vars.renderer.addEnvRenderer(Env.none, () => {
        Draw.draw(Layer.overlayUI, () => {
            let font = Fonts.outline;
            let originalScale = font.getData().scaleX;
            let originalColor = font.getColor().cpy();

            font.getData().setScale(0.3);

            let textAlpha = Core.settings.getInt("cursor-renderer-text-alpha", 50) / 100;
            let lineAlpha = Core.settings.getInt("cursor-renderer-line-alpha", 50) / 100;

            let textColor = Color.white.cpy();
            textColor.a = textAlpha;
            font.setColor(textColor);

            Groups.player.each(p => {
                if (p === Vars.player) return;

                let x = p.mouseX;
                let y = p.mouseY;

                if (x == 0) return;  // sometimes custom clients dont upload the mouse. so we wont render it

                font.draw(p.name, x, y, Align.center);

                let lineColor = p.team().color.cpy();
                lineColor.a = lineAlpha;

                Lines.stroke(1);
                Draw.color(lineColor);
                Lines.line(p.x, p.y, x, y);
            });

            font.getData().setScale(originalScale);
            font.setColor(originalColor);
            Draw.reset();
        });
    });
});
