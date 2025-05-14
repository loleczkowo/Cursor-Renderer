package cursorrenderer;

import mindustry.ui.dialogs.SettingsMenuDialog.SettingsTable;
import mindustry.ui.dialogs.SettingsMenuDialog.SettingsTable.Setting;
import arc.scene.ui.Label;
import arc.Core;

public class CursorRendererLabelSetting extends Setting {
    public CursorRendererLabelSetting(String name) {
        super(name);
    }

    @Override
    public void add(SettingsTable table){
        Label label = new Label(title);
        label.setWrap(true);
        table.add(label).left().padTop(6f).width(460f).row();
    }
}
