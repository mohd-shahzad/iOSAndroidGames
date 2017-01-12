package cipl.com.tic_tac_toe;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    CardView card_layout;
    ImageButton b11, b12, b13, b21, b22, b23, b31, b32, b33, btnReset;
    String a11, a12, a13, a21, a22, a23, a31, a32, a33;
    int turn = 0;

    Bitmap bitmap;
    Canvas canvas;
    Paint paint;

    ImageView ivDemo;
    int ivHeight;
    int ivWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


    }

    public void init(){
        card_layout = (CardView) findViewById(R.id.card_layout);
        card_layout.setBackgroundResource(R.drawable.rectangle_shape);

        b11 = (ImageButton)findViewById(R.id.b11);
        b11.setOnClickListener(this);

        b12 = (ImageButton)findViewById(R.id.b12);
        b12.setOnClickListener(this);

        b13 = (ImageButton)findViewById(R.id.b13);
        b13.setOnClickListener(this);

        b21 = (ImageButton)findViewById(R.id.b21);
        b21.setOnClickListener(this);

        b22 = (ImageButton)findViewById(R.id.b22);
        b22.setOnClickListener(this);

        b23 = (ImageButton)findViewById(R.id.b23);
        b23.setOnClickListener(this);

        b31 = (ImageButton)findViewById(R.id.b31);
        b31.setOnClickListener(this);

        b32 = (ImageButton)findViewById(R.id.b32);
        b32.setOnClickListener(this);

        b33 = (ImageButton)findViewById(R.id.b33);
        b33.setOnClickListener(this);

        turn = 1;

        btnReset = (ImageButton) findViewById(R.id.btnReset);
        btnReset.setActivated(false);
        btnReset.setEnabled(false);
        btnReset.setOnClickListener(this);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        ivDemo = (ImageView) findViewById(R.id.ivDemo);

        ivHeight = ivDemo.getMaxHeight();
        ivWidth = ivDemo.getMaxWidth();

        bitmap = Bitmap.createBitmap(width , height, Bitmap.Config.ARGB_8888);

      //  bitmap = Bitmap.createBitmap(500, 800, Bitmap.Config.ARGB_8888);

        canvas = new Canvas(bitmap);

        paint = new Paint();
        paint.setColor(Color.rgb(255, 183, 36));
        paint.setStrokeWidth(40);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.b11:
                if(b11.getTag().toString().equals(""))
                {
                    if(turn == 1){
                        turn = 2;
                        b11.setTag("X");
                        b11.setBackgroundResource(R.drawable.skull);
                    }
                    else if(turn == 2){
                        turn = 1;
                        b11.setTag("O");
                        b11.setBackgroundResource(R.drawable.logo_positive);
                    }
                }
                endGame();
                checkDraw();

                break;

            case R.id.b12:

                if(b12.getTag().toString().equals(""))
                {
                    if(turn == 1){
                        turn = 2;
                        b12.setTag("X");
                        b12.setBackgroundResource(R.drawable.skull);
                    }
                    else if(turn == 2){
                        turn = 1;
                        b12.setTag("O");
                        b12.setBackgroundResource(R.drawable.logo_positive);
                    }
                }
                endGame();
                checkDraw();

                break;

            case R.id.b13:
                if(b13.getTag().toString().equals(""))
                {
                    if(turn == 1){
                        turn = 2;
                        b13.setTag("X");
                        b13.setBackgroundResource(R.drawable.skull);
                    }
                    else if(turn == 2){
                        turn = 1;
                        b13.setTag("O");
                        b13.setBackgroundResource(R.drawable.logo_positive);
                    }
                }
                endGame();
                checkDraw();

                break;

            case R.id.b21:
                if(b21.getTag().toString().equals(""))
                {
                    if(turn == 1){
                        turn = 2;
                        b21.setTag("X");
                        b21.setBackgroundResource(R.drawable.skull);
                    }
                    else if(turn == 2){
                        turn = 1;
                        b21.setTag("O");
                        b21.setBackgroundResource(R.drawable.logo_positive);
                    }
                }
                endGame();
                checkDraw();

                break;

            case R.id.b22:
                if(b22.getTag().toString().equals(""))
                {
                    if(turn == 1){
                        turn = 2;
                        b22.setTag("X");
                        b22.setBackgroundResource(R.drawable.skull);
                    }
                    else if(turn == 2){
                        turn = 1;
                        b22.setTag("O");
                        b22.setBackgroundResource(R.drawable.logo_positive);
                    }
                }
                endGame();
                checkDraw();

                break;

            case R.id.b23:
                if(b23.getTag().toString().equals(""))
                {
                    if(turn == 1){
                        turn = 2;
                        b23.setTag("X");
                        b23.setBackgroundResource(R.drawable.skull);
                    }
                    else if(turn == 2){
                        turn = 1;
                        b23.setTag("O");
                        b23.setBackgroundResource(R.drawable.logo_positive);
                    }
                }
                endGame();
                checkDraw();

                break;

            case R.id.b31:
                if(b31.getTag().toString().equals(""))
                {
                    if(turn == 1){
                        turn = 2;
                        b31.setTag("X");
                        b31.setBackgroundResource(R.drawable.skull);
                    }
                    else if(turn == 2){
                        turn = 1;
                        b31.setTag("O");
                        b31.setBackgroundResource(R.drawable.logo_positive);
                    }
                }
                endGame();
                checkDraw();

                break;

            case R.id.b32:
                if(b32.getTag().toString().equals(""))
                {
                    if(turn == 1){
                        turn = 2;
                        b32.setTag("X");
                        b32.setBackgroundResource(R.drawable.skull);
                    }
                    else if(turn == 2){
                        turn = 1;
                        b32.setTag("O");
                        b32.setBackgroundResource(R.drawable.logo_positive);
                    }
                }
                endGame();
                checkDraw();

                break;

            case R.id.b33:
                if(b33.getTag().toString().equals(""))
                {
                    if(turn == 1){
                        turn = 2;
                        b33.setTag("X");
                        b33.setBackgroundResource(R.drawable.skull);
                    }
                    else if(turn == 2){
                        turn = 1;
                        b33.setTag("O");
                        b33.setBackgroundResource(R.drawable.logo_positive);
                    }
                }
                endGame();
                checkDraw();

                break;

            case R.id.btnReset:
                turn = 1;
                draw = true;
                resetButtons();

        }
    }

    boolean draw = true;
    private void checkDraw() {
        if(!a11.equals("") && !a12.equals("") && !a13.equals("") && !a21.equals("") && !a22.equals("") && !a23.equals("") && !a31.equals("") && !a32.equals("") && !a33.equals("") && draw == true){
            Toast.makeText(MainActivity.this, "Match has been draw", Toast.LENGTH_LONG).show();
        }
    }

    private void resetButtons() {
        b11.setBackgroundResource(android.R.drawable.btn_default);
        b11.setActivated(true);
        b11.setEnabled(true);
        b11.setTag("");

        b12.setBackgroundResource(android.R.drawable.btn_default);
        b12.setActivated(true);
        b12.setEnabled(true);
        b12.setTag("");

        b13.setBackgroundResource(android.R.drawable.btn_default);
        b13.setActivated(true);
        b13.setEnabled(true);
        b13.setTag("");

        b21.setBackgroundResource(android.R.drawable.btn_default);
        b21.setActivated(true);
        b21.setEnabled(true);
        b21.setTag("");

        b22.setBackgroundResource(android.R.drawable.btn_default);
        b22.setActivated(true);
        b22.setEnabled(true);
        b22.setTag("");

        b23.setBackgroundResource(android.R.drawable.btn_default);
        b23.setActivated(true);
        b23.setEnabled(true);
        b23.setTag("");

        b31.setBackgroundResource(android.R.drawable.btn_default);
        b31.setActivated(true);
        b31.setEnabled(true);
        b31.setTag("");

        b32.setBackgroundResource(android.R.drawable.btn_default);
        b32.setActivated(true);
        b32.setEnabled(true);
        b32.setTag("");

        b33.setBackgroundResource(android.R.drawable.btn_default);
        b33.setActivated(true);
        b33.setEnabled(true);
        b33.setTag("");

        ivDemo.setBackground(null);


    }

    public void endGame(){
        
        a11 = b11.getTag().toString();
        a12 = b12.getTag().toString();
        a13 = b13.getTag().toString();

        a21 = b21.getTag().toString();
        a22 = b22.getTag().toString();
        a23 = b23.getTag().toString();

        a31 = b31.getTag().toString();
        a32 = b32.getTag().toString();
        a33 = b33.getTag().toString();

        if(a11.equals(a12) && a11.equals(a13) && a11.equals("X")){
            float startx = 50;
            float starty = 100;
            float endx = 400;
            float endy = 500;

            /*DrawView drawView = new DrawView(MainActivity.this);
            drawView.setBackgroundColor(Color.BLUE);
            setContentView(drawView);*/

            // canvas.drawPath(path, paint);
            canvas.drawLine(0, 300, ivWidth, 300, paint);
            ivDemo.setImageBitmap(bitmap);

            Toast.makeText(MainActivity.this, "Winner is player X!", Toast.LENGTH_SHORT).show();
            disableButtons();
            draw = false;

        }

        if(a11.equals(a22) && a11.equals(a33) && a11.equals("X")){

            canvas.drawLine(0, 300, ivWidth, ivHeight, paint);
            ivDemo.setImageBitmap(bitmap);

            Toast.makeText(MainActivity.this, "Winner is player X!", Toast.LENGTH_SHORT).show();
            disableButtons();
            draw = false;
        }

        if(a11.equals(a21) && a11.equals(a31) && a11.equals("X")){

            canvas.drawLine(0, 0, 0, ivHeight, paint);
            ivDemo.setImageBitmap(bitmap);

            Toast.makeText(MainActivity.this, "Winner is player X!", Toast.LENGTH_SHORT).show();
            disableButtons();
            draw = false;
        }



        if(a12.equals(a22) && a12.equals(a32) && a12.equals("X")){

            canvas.drawLine(360, 0, 360, ivHeight, paint);
            ivDemo.setImageBitmap(bitmap);

            Toast.makeText(MainActivity.this, "Winner is player X!", Toast.LENGTH_SHORT).show();
            disableButtons();
            draw = false;
        }

        if(a13.equals(a23) && a13.equals(a33) && a13.equals("X")){

            canvas.drawLine(700, 0, 700, ivHeight, paint);
            ivDemo.setImageBitmap(bitmap);

            Toast.makeText(MainActivity.this, "Winner is player X!", Toast.LENGTH_SHORT).show();
            disableButtons();
            draw = false;
        }


        if(a21.equals(a22) && a21.equals(a23) && a21.equals("X")){

            canvas.drawLine(0, 660, ivWidth, 660, paint);
            ivDemo.setImageBitmap(bitmap);

            Toast.makeText(MainActivity.this, "Winner is player X!", Toast.LENGTH_SHORT).show();
            disableButtons();
            draw = false;
        }
        if(a31.equals(a32) && a31.equals(a33) && a31.equals("X")){

            canvas.drawLine(0, 1100, ivWidth, 1100, paint);
            ivDemo.setImageBitmap(bitmap);

            Toast.makeText(MainActivity.this, "Winner is player X!", Toast.LENGTH_SHORT).show();
            disableButtons();
            draw = false;
        }

        if(a13.equals(a22) && a13.equals(a31) && a13.equals("X")){

            canvas.drawLine(ivWidth, 300, 0, ivHeight, paint);
            ivDemo.setImageBitmap(bitmap);

            Toast.makeText(MainActivity.this, "Winner is player X!", Toast.LENGTH_SHORT).show();
            disableButtons();
            draw = false;
        }

        //-----------------
        if(a11.equals(a12) && a11.equals(a13) && a11.equals("O")){
            Toast.makeText(MainActivity.this, "Winner is player O!", Toast.LENGTH_SHORT).show();
            disableButtons();
            draw = false;
        }

        if(a11.equals(a22) && a11.equals(a33) && a11.equals("O")){
            Toast.makeText(MainActivity.this, "Winner is player O!", Toast.LENGTH_SHORT).show();
            disableButtons();
            draw = false;
        }

        if(a11.equals(a21) && a11.equals(a31) && a11.equals("O")){
            Toast.makeText(MainActivity.this, "Winner is player O!", Toast.LENGTH_SHORT).show();
            disableButtons();
            draw = false;
        }



        if(a12.equals(a22) && a12.equals(a32) && a12.equals("O")){
            Toast.makeText(MainActivity.this, "Winner is player O!", Toast.LENGTH_SHORT).show();
            disableButtons();
            draw = false;
        }

        if(a13.equals(a23) && a13.equals(a33) && a13.equals("O")){
            Toast.makeText(MainActivity.this, "Winner is player O!", Toast.LENGTH_SHORT).show();
            disableButtons();
            draw = false;
        }


        if(a21.equals(a22) && a21.equals(a23) && a21.equals("O")){
            Toast.makeText(MainActivity.this, "Winner is player O!", Toast.LENGTH_SHORT).show();
            disableButtons();
            draw = false;
        }
        if(a31.equals(a32) && a31.equals(a33) && a31.equals("O")){
            Toast.makeText(MainActivity.this, "Winner is player O!", Toast.LENGTH_SHORT).show();
            disableButtons();
            draw = false;

        }
        if(a13.equals(a22) && a13.equals(a31) && a13.equals("O")){
            Toast.makeText(MainActivity.this, "Winner is player O!", Toast.LENGTH_SHORT).show();
            disableButtons();
            draw = false;
        }



    }


    public void disableButtons(){
        b11.setEnabled(false);
        b11.setActivated(false);

        b12.setEnabled(false);
        b12.setActivated(false);

        b13.setEnabled(false);
        b13.setActivated(false);

        b21.setEnabled(false);
        b21.setActivated(false);

        b22.setEnabled(false);
        b22.setActivated(false);

        b23.setEnabled(false);
        b23.setActivated(false);

        b31.setEnabled(false);
        b31.setActivated(false);

        b32.setEnabled(false);
        b32.setActivated(false);

        b33.setEnabled(false);
        b33.setActivated(false);

        btnReset.setActivated(true);
        btnReset.setEnabled(true);

    }
}
