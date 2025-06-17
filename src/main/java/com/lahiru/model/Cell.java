package com.lahiru.model;

/**
 * Represents a single cell on the Minesweeper board.
 */
public final class Cell {
    private static final String HIDDEN_SYMBOL = "_";
    private static final String MINE_SYMBOL = "*";
    private boolean mine;
    private int adjacentMines;
    private boolean revealed;

    public boolean isMine(){
        return mine;
    }

    public void setMine(boolean m){
        this.mine = m;
    }

    public int adjacentMines(){
        return adjacentMines;
    }

    public void setAdjacentMines(int n){
        this.adjacentMines = n;
    }

    public boolean isRevealed(){
        return revealed;
    }

    public void reveal(){
        this.revealed = true;
    }

    @Override
    public String toString(){
        if (!revealed) return HIDDEN_SYMBOL;
        return mine ? MINE_SYMBOL : String.valueOf(adjacentMines);
    }
}

