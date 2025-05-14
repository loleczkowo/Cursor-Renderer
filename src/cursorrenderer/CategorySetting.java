package cursorrenderer;

import arc.Core;
import arc.scene.ui.ImageButton;
import arc.scene.ui.layout.Collapser;
import arc.scene.ui.layout.Table;
import mindustry.gen.Icon;
import mindustry.graphics.Pal;
import mindustry.ui.dialogs.SettingsMenuDialog.SettingsTable;
import mindustry.ui.dialogs.SettingsMenuDialog.SettingsTable.Setting;

public class CategorySetting extends Setting {
    private static final ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle() {{
        imageDownColor = Pal.accent;
        imageOverColor = Pal.accent;
        imageUpColor = Pal.accent;
    }};

    public final Table children = new Table();
    private final Collapser collapser;

    public CategorySetting(String name) {
        super(name);
        this.title = Core.bundle.get("client.setting." + name + ".category", name);
        this.collapser = new Collapser(children, Core.settings.getBool("settingscategory-" + name + "-enabled", true));
    }

    @Override
    public void add(SettingsTable table) {
        ImageButton[] arrowButton = {null};

        table.add("").row();

        Runnable onClicked = () -> {
            collapser.toggle();
            Core.settings.put("settingscategory-" + name + "-enabled", collapser.isCollapsed());
            arrowButton[0].getStyle().imageUp = collapser.isCollapsed() ? Icon.downOpen : Icon.upOpen;
        };

        table.table(t -> {
            t.add(title).center().growX().color(Pal.accent).get().clicked(onClicked);
            arrowButton[0] = t.button(Icon.downOpen, style, onClicked).size(10f).right().padRight(10f).get();
            arrowButton[0].getStyle().imageUp = collapser.isCollapsed() ? Icon.downOpen : Icon.upOpen;
        }).growX();
        table.row();

        table.image().color(Pal.accent).growX().height(3f).padTop(4f).padBottom(4f);
        table.row();

        table.add(collapser).left();
        table.row();
    }
}
