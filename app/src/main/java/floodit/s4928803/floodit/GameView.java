package floodit.s4928803.floodit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import static floodit.s4928803.floodit.Game.GRID_SIZE;
import static floodit.s4928803.floodit.Game.maxRounds;
import static java.lang.Math.ceil;


public class GameView extends View {

    float availableWidth, availableHeight;
    int rowCount = AbstractGame.DEFAULT_ROWS;
    int columnCount = AbstractGame.DEFAULT_COLUMNS;
    private GestureDetector mGestureDetector;
    Game game = new Game(15, 15);

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context);
        init();
        mGestureDetector = new GestureDetector(context, new MyGestureListener());
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        public boolean onDown(MotionEvent ev) {
            return true;
        }

        public boolean onSingleTapUp(MotionEvent ev) {

            /*
            Gets the X & Y coordinates of a click
             */
            double x = ev.getX();
            double y = ev.getY();

            /*
            Calculates the row/column that have been clicked in
             */
            int row = (int) ceil(y / (availableHeight / rowCount));
            int column = (int) ceil(x / (availableWidth / columnCount));

            /*
            Prevents out of bounds clicks (outside of the grid)
             */
            if (x > availableWidth || y > availableHeight) {
                System.out.println("Out of bounds!");
                System.out.println("x =" + x);
                System.out.println("x =" + y);
            } else {
                game.playColour(game.getColor(column - 1, row - 1));
                game.getRound();
                game.isWon();
                invalidate();
            }
            return true;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent ev) {
        boolean r = this.mGestureDetector.onTouchEvent(ev);
        return super.onTouchEvent(ev) || r;
    }

    private Paint squarePaint;
    private Paint backgroundPaint;
    private Paint textPaint;
    int[] boxColours = new int[6];


    private void init() {
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setColor(Color.argb(255, 25, 25, 25));
        squarePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Typeface tf = Typeface.create("Helvetica",Typeface.BOLD);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.argb(255, 255, 255, 255));
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTypeface(tf);

        boxColours[0] = Color.argb(255, 255, 0, 0);
        boxColours[1] = Color.argb(255, 0, 255, 0);
        boxColours[2] = Color.argb(255, 0, 0, 255);
        boxColours[3] = Color.argb(255, 255, 255, 0);
        boxColours[4] = Color.argb(255, 0, 255, 255);
        boxColours[5] = Color.argb(255, 255, 0, 255);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int colourNum;
        int roundCount = game.getRound();
        int GRID_WIDTH = rowCount;
        int GRID_HEIGHT = columnCount;

        availableWidth = (GRID_WIDTH * (GRID_SIZE));
        availableHeight = (GRID_HEIGHT * (GRID_SIZE));

        /*
        Assigns each point in the array a random colour number reference
         */
        for (int i = 0; i < game.getWidth(); i++) {
            for (int j = 0; j < game.getHeight(); j++) {
                Paint paint = null;
                Paint paint2;
                paint2 = textPaint;
                paint2.setTextSize(65);
                colourNum = game.GridCoord(i, j);
                if ((colourNum <= 5) || (colourNum >= 0)) {
                    paint = squarePaint;
                    squarePaint.setStyle(Paint.Style.FILL);
                    squarePaint.setColor(boxColours[colourNum]);
                }

                int left = i * (GRID_SIZE);
                int top = j * (GRID_SIZE);
                int right = left + GRID_SIZE;
                int bottom = top + GRID_SIZE;

                /*
                Turns loss text red when the user has lost
                 */
                if (roundCount > maxRounds) {
                    textPaint.setColor(Color.argb(255, 255, 0, 0));
                }
                canvas.drawRect(left, top, right, bottom, paint);
                canvas.drawText("Round: " + (Integer.toString(roundCount) + "/" + maxRounds), 360, 1550, paint2);

                if (roundCount > maxRounds) {
                    canvas.drawText("You lose!", 420, 1150, paint2);
                }

                if (game.isWon()) {
                    canvas.drawText("You win!", 420, 1150, paint2);
                }

            }
        }
    }
}