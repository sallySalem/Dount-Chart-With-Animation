package com.graphics.dountchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Sally_Salem on 11/15/2015.
 */

public class DountChart extends View{
    private ArrayList<Integer> colors = new ArrayList<>();
    private ArrayList<Float> value_degree = new ArrayList<>();
    private RectF rectf;
    private final Paint paint = new Paint(1);
    private final Paint paintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);


    private float degree = 0.0F;
    private float temp = 0.0F;
    private int i = 0;
    private int rectSize = 0;
    private int backgroundColor = 0;

    private int width = 0;


    public DountChart(Context context) {
        super(context);
    }

    public DountChart(Context context, int backgroundColor, ArrayList<Float> values, ArrayList<Integer> colors) {
        super(context);
        this.value_degree = values;
        this.colors = colors;
        this.temp = this.value_degree.get(0);
        this.backgroundColor = backgroundColor;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //init the rectf and display dount chart on 60% of canvas
        if ((rectf == null) || (width != canvas.getWidth())) {
            paintCircle.setStyle(Paint.Style.FILL);
            paintCircle.setColor(backgroundColor);

            canvas.drawColor(backgroundColor);
            width = canvas.getWidth();
            int height = canvas.getHeight();
            this.rectSize = ((int) (width * 0.6));
            int xPoint = width / 2 - rectSize / 2;
            int yPoint = height / 2 - rectSize / 2;
            if (width > height) {
                width = height;
                height = canvas.getWidth();
                this.rectSize = ((int) (width * 0.6));
                xPoint = height / 2 - rectSize / 2;
                yPoint = width / 2 - rectSize / 2;
            }
            rectf = new RectF(xPoint, yPoint, rectSize + xPoint, rectSize + yPoint);
        }
        if (i == 0) {
            paint.setColor(colors.get(i));
            canvas.drawArc(rectf, temp - value_degree.get(i), degree, true, paint);
        } else {
            paint.setColor(colors.get(i));
            canvas.drawArc(rectf, temp - value_degree.get(i), degree - (temp - value_degree.get(i)), true, paint);
        }

        for (int j = 0; j < i; j++) {
            paint.setColor(colors.get(j));
            float v = 0;
            for (int x = 0; x < j; x++) {
                v += value_degree.get(x);
            }
            canvas.drawArc(rectf, v, value_degree.get(j), true, paint);
        }
        canvas.drawCircle(rectf.centerX(), rectf.centerY(), (float) (this.rectSize * 0.3), paintCircle);
        degree = degree + 5;
        if (degree >= temp) {
            i = i + 1;
            if (i >= value_degree.size()) {
                i = i - 1;
            } else {
                temp += value_degree.get(i);
            }
        }
        //>360 stop drawing
        if ((degree <= 360)) {
            invalidate();
        }
    }

}