package com.example.mohdshahzad.daretomakegame;

/**
 * Created by mohd.shahzad on 06-12-2016.
 */

public class CellIndex {

    private int row;
    private int col;

    public CellIndex(int row ,int col)
    {
        this.row = row;
        this.col = col;

    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
