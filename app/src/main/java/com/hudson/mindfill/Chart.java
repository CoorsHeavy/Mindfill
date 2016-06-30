package com.hudson.mindfill;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Chart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        MoodSettings ms = new MoodSettings(this);

        LineChart chart = (LineChart) findViewById(R.id.chart);
        ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
        int max = 1;
        Iterator it = ms.getMapInt().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
            if(max < (int) pair.getKey()) max = (int) pair.getKey();
            valsComp1.add(new Entry((int) pair.getValue(), (int) pair.getKey()));
        }

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        LineDataSet setComp1 = new LineDataSet(valsComp1, "");
        dataSets.add(setComp1);

        XAxis xAxis = chart.getXAxis();

        LineData data = new LineData(ChartData.generateXVals(0, max + 2), dataSets);
        chart.setScaleEnabled(false);
        chart.setTouchEnabled(true);
        chart.setData(data);
        chart.setPinchZoom(true);
        chart.invalidate(); // refresh
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                Log.d( "Hudson", String.valueOf(e.getXIndex()) );
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }
}
