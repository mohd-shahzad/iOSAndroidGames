package cipl.com.tic_tac_toe;

/**
 * Created by kanhaiya.chaurasiya on 10-01-2017.
 */
import android.content.Context;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.view.View;

public class DrawView extends View {
    Paint paint = new Paint();

    public DrawView(Context context) {
        super(context);
        paint.setColor(Color.BLACK);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawLine(0, 0, 20, 20, paint);
        canvas.drawLine(20, 0, 0, 20, paint);
    }

}
