package input;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.input.KeyCode;

public class InputHandler {
    private Map<KeyCode, Command> keyBindings = new HashMap<>();

    public void bindKey(KeyCode key, Command command) {
        keyBindings.put(key, command);
    }

    public void handleInput(KeyCode key) {
        Command command = keyBindings.get(key);
        if (command != null) {
            command.execute();
        }
    }
}
