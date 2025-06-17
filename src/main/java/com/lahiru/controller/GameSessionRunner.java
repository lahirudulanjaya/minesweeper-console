package com.lahiru.controller;

import com.lahiru.model.Position;
import com.lahiru.view.BoardRenderer;
import com.lahiru.view.CLIView;

public class GameSessionRunner {
    private final GameController controller;
    private final CLIView view;

    public GameSessionRunner(GameController controller, CLIView view){
        this.controller = controller;
        this.view = view;
    }

    public void run(){
        while (controller.state() == GameController.State.ONGOING) {
            view.printBoard(BoardRenderer.renderBoard(controller.board(), false));
            Position move = view.promptForPosition(controller.board().getSize());
            view.printMessage(controller.revealCell(move));
        }
        view.printBoard(BoardRenderer.renderBoard(controller.board(), true));
        view.printEndMessage(controller.state());
    }
}
