interface UIFactory {
    Button createButton();

    Window createWindow();
}

class WindowsFactory implements GUIFactory {
    public Button createButton() {
        return new WindowsButton();
    }

    public Window createWindow() {
        return new WindowsWindow();
    }
}

class MacFactory implements GUIFactory {
    public Button createButton() {
        return new MacButton();
    }

    public Window createWindow() {
        return new MacWindow();
    }
}
