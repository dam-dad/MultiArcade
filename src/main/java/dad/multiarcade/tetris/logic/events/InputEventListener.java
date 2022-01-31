package dad.multiarcade.tetris.logic.events;

import dad.multiarcade.tetris.logic.ViewData;
import dad.multiarcade.tetris.logic.DownData;

public interface InputEventListener {

    DownData onDownEvent(MoveEvent event);

    ViewData onLeftEvent(MoveEvent event);

    ViewData onRightEvent(MoveEvent event);

    ViewData onRotateEvent(MoveEvent event);

    void createNewGame();
}
