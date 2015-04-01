package se.miun.paer1301.calendar_lab3;

/**
 * Created by pär on 2015-03-29.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by pär on 2015-03-29.
 */
public class CView extends View {
    private Calendar calendar;
    private Bitmap bitmap;
    String month, day, date;
    Bitmap scaledBM;
    DisplayMetrics metrics;

    private int viewHeigth, viewWidth;

    public CView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.calendar_sheet);
        metrics = getContext().getResources().getDisplayMetrics();

        calendar = Calendar.getInstance();
        month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        day = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        int dateInteger = calendar.get(Calendar.DAY_OF_MONTH);
        date = Integer.toString(dateInteger);
    }

    public void setDate(Calendar calendar)
    {
        this.calendar = calendar;
    }
    public void nextDate()
    {
        calendar.add(Calendar.DAY_OF_YEAR,1);

        month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        day = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
        int dateInteger = calendar.get(Calendar.DAY_OF_MONTH);
        date = Integer.toString(dateInteger);

        //invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Draw the picture
        canvas.drawBitmap(bitmap, 0, 0, null);


        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);

        //write the month
        textPaint.setTextAlign(Paint.Align.CENTER);
        float dp = getResources().getDimensionPixelSize(R.dimen.month_size);
        float fpixels = metrics.density * dp;
        int pixels = (int) (fpixels + 0.5f);
        textPaint.setTextSize(pixels);
        int xPos = (canvas.getWidth() / 2);
        int yPos = (int) (canvas.getHeight() / 3);
        canvas.drawText(month, xPos, yPos, textPaint);

        //write the week-day
        dp = getResources().getDimensionPixelSize(R.dimen.day_of_week_size);
        fpixels = metrics.density * dp;
        pixels = (int) (fpixels + 0.5f);
        textPaint.setTextSize(pixels);
        yPos = (int) (canvas.getHeight()-textPaint.descent());
        canvas.drawText(day, xPos, yPos, textPaint);

        //write the day of the month
        dp = getResources().getDimensionPixelSize(R.dimen.day_of_month_size);
        fpixels = metrics.density * dp;
        pixels = (int) (fpixels + 0.5f);
        textPaint.setTextSize(pixels);
        yPos = (int) (canvas.getHeight() / 2 - ( textPaint.ascent()) / 2);
        canvas.drawText(date, xPos, yPos, textPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(bitmap.getHeight(), bitmap.getWidth());
   }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

       //scaledBM = Bitmap.createScaledBitmap(bitmap,w, h,false);

    }
}
