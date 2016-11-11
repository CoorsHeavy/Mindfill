package com.hudson.mindfill.lib;

import android.app.Dialog;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.hudson.mindfill.R;

public class InfoDialog extends Dialog {

    public InfoDialog(final Context context, int index) {
        super(context);

        setTitle(StaticClass.getIntstnace().getTreatmentName(index));
        setContentView(R.layout.dialoglayout);
        TabHost tabHost = (TabHost) findViewById(R.id.TabHost01);
        TextView describe = (TextView) findViewById(R.id.TextView01);
        describe.setText(StaticClass.getIntstnace().getTreatmentDescription(index));
        ListView listView = (ListView) findViewById(R.id.TextView02);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                context,
                android.R.layout.simple_list_item_1,
                StaticClass.getIntstnace().getTreatmentEvidence(index));
        listView.setAdapter(arrayAdapter);
        tabHost.setup();
        // create tab 1
        TabHost.TabSpec spec1 = tabHost.newTabSpec("Description");
        spec1.setIndicator("Description");
        spec1.setContent(R.id.TextView01);
        tabHost.addTab(spec1);
        //create tab2
        TabHost.TabSpec spec2 = tabHost.newTabSpec("Evidence");
        spec2.setIndicator("Sources");
        spec2.setContent(R.id.TextView02);
        tabHost.addTab(spec2);
    }
}