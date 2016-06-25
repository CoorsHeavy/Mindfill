package com.hudson.mindfill;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class Chart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        LineChart chart = (LineChart) findViewById(R.id.chart);
        ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
        Entry c2e1 = new Entry(120.000f, 0);
        valsComp1.add(c2e1);
        Entry c2e2 = new Entry(110.000f, 1);
        valsComp1.add(c2e2);
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        LineDataSet setComp1 = new LineDataSet(valsComp1, "Company 1");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        dataSets.add(setComp1);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("1.Q"); xVals.add("2.Q"); xVals.add("3.Q"); xVals.add("4.Q");

        LineData data = new LineData(xVals, dataSets);
        chart.setData(data);
        chart.invalidate(); // refresh
    }
}
