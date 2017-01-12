package com.example.mohdshahzad.daretomakegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by mohd.shahzad on 29-11-2016.
 */

public class Board extends View {

    private Paint paintGrid = new Paint();
    private int cellWidth;
    private int cellHeight;
    int NUM_CELLS = 4;
    private Rect rect = new Rect();
    private Paint paintCircles = new Paint();
    private Paint paintPath = new Paint();
    private Path path = new Path();
    boolean isStartVisit = false;
    ArrayList<CellIndex> cellList = new ArrayList<CellIndex>();
    int startRow = 1;
    int startCol = 1;

    int endRow =2;
    int endCol = 3;

    public Board(Context context, AttributeSet attrs)
    {
        super(context ,attrs);

        this.paintGrid.setStyle(Paint.Style.STROKE);
        this.paintGrid.setColor(Color.BLUE);

        this.paintPath.setStyle(Paint.Style.STROKE);
        this.paintPath.setStrokeCap(Paint.Cap.ROUND);
        this.paintPath.setStrokeJoin(Paint.Join.ROUND);
        this.paintPath.setAntiAlias(true);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);





        //draw the grid
        for (int r = 0; r < NUM_CELLS; ++r) {
            for (int c = 0; c < NUM_CELLS; ++c) {
                int x = colToX(c);
                int y = rowToY(r);
                rect.set(x, y, x + cellWidth, y + cellHeight);
                canvas.drawRect(rect, paintGrid);
            }
        }

        paintCircles.setColor(Color.GRAY);
        canvas.drawCircle(colToX(1) + cellWidth / 2, rowToY(1) + cellHeight / 2, cellWidth / 4, paintCircles);
        canvas.drawCircle(colToX(3) + cellWidth / 2, rowToY(2) + cellHeight / 2, cellWidth / 4, paintCircles);


        if(cellList.size()>0)
        {
            path.moveTo(colToX(cellList.get(0).getCol()) + cellWidth / 2,
                    rowToY(cellList.get(0 ).getRow()) + cellHeight / 2);

            for(int index=0;index<cellList.size();index++)
            {

                path.lineTo(colToX(cellList.get(index).getCol()) + cellWidth / 2,
                        rowToY(cellList.get(index).getRow()) + cellHeight / 2);

            }


        }

       /* path.moveTo(colToX(1) + cellWidth / 2,
                rowToY(1) + cellHeight / 2);

        path.lineTo(colToX(1) + cellWidth / 2,
                rowToY(2) + cellHeight / 2);

        path.lineTo(colToX(2) + cellWidth / 2,
                rowToY(2) + cellHeight / 2);

        path.lineTo(colToX(3) + cellWidth / 2,
                rowToY(2) + cellHeight / 2);*/

        canvas.drawPath(path ,paintPath);

    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int oldw, int oldh) {
        super.onSizeChanged(xNew, yNew, oldw, oldh);

        cellWidth = (xNew - getPaddingLeft() - getPaddingRight()) / NUM_CELLS;
        cellHeight = (xNew - getPaddingTop() - getPaddingBottom()) / NUM_CELLS;


        paintPath.setColor(Color.RED);
        paintPath.setStrokeWidth(cellWidth/4);
    }


    private int colToX(int col)
    {
        return col * this.cellWidth + getPaddingLeft();
    }

    private int rowToY(int row)
    {
        return row * this.cellHeight + getPaddingTop();
    }


    private int xToCol(int x)
    {
        return (x - getPaddingLeft()) / this.cellWidth;
    }

    private int yToRow(int y)
    {
        return (y - getPaddingTop()) / this.cellHeight;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int x = (int) event.getX();         // NOTE: event.getHistorical... might be needed.
        int y = (int) event.getY();
        int c = xToCol(x);
        int r = yToRow(y);

        if (c >= NUM_CELLS || r >= NUM_CELLS)
        {
            return true;
        }


        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            if(isStartVisit)
            {
                cellList.clear();
                path.reset();
            //    invalidate();
                isStartVisit = false;
            }

            if(c==1 && r==1)
                isStartVisit = true;

           // return true;
        }
        else if(event.getAction() == MotionEvent.ACTION_MOVE)
        {

            if(isStartVisit)
            {

                if(c == startCol && r == startRow)
                {

                        path.moveTo(colToX(c) + cellWidth / 2,
                                rowToY(r) + cellHeight / 2);

                    cellList.add(new CellIndex(r ,c));


                }
                else if(areNeighbours(c ,r
                        ,cellList.get(cellList.size()-1).getCol() ,cellList.get(cellList.size()-1).getRow())  && cellList.size()>1)
                {
                    if(isInflow(new CellIndex(r,c))) {

                        removeOverlapath(new CellIndex(r,c));

                    } else
                    cellList.add(new CellIndex(r ,c));
                }
                else
                {
                  /*  path.moveTo(colToX(c) + cellWidth / 2,
                            rowToY(r) + cellHeight / 2);
*/

                }



            }

              invalidate();

        }

        return true;
    }

    private boolean isInflow(CellIndex cellIndex)
    {
        for(int index =0;index<cellList.size();index++)
        {
            if (cellList.get(index).getRow() == cellIndex.getRow() && cellList.get(index).getCol() == cellIndex.getCol())
                return true;

        }

        return false;

    }

    private void removeOverlapath(CellIndex cellIndex)
    {
        ArrayList<CellIndex> tempList = new ArrayList<CellIndex>();

        for(int index =0;index<cellList.size();index++)
        {
            if(cellList.get(index).getRow() != cellIndex.getRow() || cellList.get(index).getCol() != cellIndex.getCol())
            {
                tempList.add(cellIndex);
                continue;
            }
                 break;


        }

        cellList.clear();
        cellList.addAll(tempList);
    }


    private boolean areNeighbours(int c1, int r1, int c2, int r2)
    {
        return Math.abs(c1 - c2) + Math.abs(r1 - r2) == 1;
    }
}
