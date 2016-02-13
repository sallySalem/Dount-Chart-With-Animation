package com.graphics.dountchart;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout v = (LinearLayout)findViewById(R.id.v_dount);

        ArrayList<Float> values = new ArrayList<Float>();
        values.add(200f);
        values.add(100f);
        values.add(200f);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(ContextCompat.getColor(this, R.color.color_1));
        colors.add(ContextCompat.getColor(this, R.color.color_2));
        colors.add(ContextCompat.getColor(this, R.color.color_3));

        v.addView(new DountChart(this, ((ColorDrawable) v.getBackground()).getColor(), calculateData(values), colors));
    }

    private ArrayList<Float> calculateData(ArrayList<Float> data) {
        float total = 0;
        for (int i = 0; i < data.size(); i++) {
            total += data.get(i);
        }
        for (int i = 0; i < data.size(); i++) {
            data.set(i, 360 * (data.get(i) / total));
        }
        return data;
    }
}
