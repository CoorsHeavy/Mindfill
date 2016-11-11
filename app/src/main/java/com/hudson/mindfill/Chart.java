package com.hudson.mindfill;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.hudson.mindfill.lib.StaticClass;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

class RecipeCompare implements Comparator<Entry> {

    @Override
    public int compare(Entry o1, Entry o2) {
        // write comparison logic here like below , it's just a sample
        return Integer.compare( o1.getXIndex(), o2.getXIndex() );
    }
}

public class Chart extends AppCompatActivity {
    LineChart chart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarChart);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Mood Chart");

        setTitle("Your Mood Chart");
        chart = (LineChart) findViewById(R.id.chart);
        chart.setNoDataText("Mood data hasn't been reported");
        ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
        int max = 1;
        Iterator it = StaticClass.getIntstnace().getMapInt().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
            if(max < (int) pair.getKey()) max = (int) pair.getKey();
            valsComp1.add(new Entry((int) pair.getValue(), (int) pair.getKey()));
        }
        Collections.sort(valsComp1, new RecipeCompare());
        {
            Iterator iI = valsComp1.iterator();
            while (iI.hasNext()) {
                Entry pair = (Entry) iI.next();
                System.out.println(pair.getXIndex() + " = " + pair.getVal());

            }
        }
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        LineDataSet setComp1 = new LineDataSet(valsComp1, "");
        setComp1.setLineWidth(7f);
        setComp1.setCircleRadius(10f);
        dataSets.add(setComp1);

        XAxis xAxis = chart.getXAxis();

        LineData data = new LineData(ChartData.generateXVals(0, max + 2), dataSets);
        chart.setScaleEnabled(false);
        chart.setTouchEnabled(true);
        chart.setData(data);
        data.setValueTextSize(20.0f);
        data.setValueTextColor(Color.argb(0, 1, 1, 1));
        chart.setPinchZoom(true);
        chart.invalidate(); // refresh
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                Log.d( "Hudson", String.valueOf(e.getXIndex()) );
                final AlertDialog alertDialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(Chart.this);
                String bundle = "";



                Logger.d(e.getXIndex());
                HashSet<String> hashSet = StaticClass.getIntstnace().getStringsForDay(e.getXIndex() - 1);
                if(hashSet == null || hashSet.isEmpty()){
                    bundle = "Nothing";
                }else{
                    Logger.d(hashSet);
                    for(String string: hashSet){
                        bundle += string + "\n";
                    }
                }
                builder.setTitle("Treatments done on " + StaticClass.getIntstnace().dateToString1(StaticClass.getIntstnace().intToDate(e.getXIndex() - 1)));
                builder.setMessage(bundle);
                // Create the AlertDialog object and return it
                alertDialog = builder.create();
                alertDialog.show();
                builder.setMessage(bundle)
                        .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                alertDialog.dismiss();
                            }
                        });
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }
}
