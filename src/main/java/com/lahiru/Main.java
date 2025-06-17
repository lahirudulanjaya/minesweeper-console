package com.lahiru;

import com.lahiru.controller.GameController;
import com.lahiru.controller.GameSessionRunner;
import com.lahiru.controller.RandomMinePlacer;
import com.lahiru.view.CLIView;

public class Main {
    public static void main(String[] args){
        while (true) {
            CLIView cliView = new CLIView();
            int size = cliView.promptGridSize();
            int mines = cliView.promptMineCount();
            GameController ctrl = new GameController(size, mines, new RandomMinePlacer());
            new GameSessionRunner(ctrl, cliView).run();
            if (!cliView.promptPlayAgain()) {
                break;
            }
        }

    }
}