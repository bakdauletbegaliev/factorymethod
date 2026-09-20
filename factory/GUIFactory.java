package factory;

import ui.Button;
import ui.Checkbox;

public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}