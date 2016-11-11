package com.hudson.mindfill;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hudson.mindfill.lib.CustomTile;
import com.hudson.mindfill.lib.CustomTiles;
import com.hudson.mindfill.lib.StaticClass;

import java.util.List;

public class CustomActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private CustomRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        getSupportActionBar().show();
        mRecyclerView = (RecyclerView) findViewById(R.id.CustomRecyclerVIew);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CustomRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.custom_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_add:
                StaticClass.getIntstnace().addCustomTile(new CustomTile("Title", "Description"));

                mAdapter.notifyDataSetChanged();
            break;
        }
        return true;
    }

    public class CustomRecyclerAdapter extends RecyclerView.Adapter<ViewHolder>{
        List<CustomTile> customTiles;
        CustomRecyclerAdapter(){
            customTiles = StaticClass.getIntstnace().loadCustomTiles();
            customTiles.add(new CustomTile("Jumping Jacks", "Hop up and down"));

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom_item, parent, false);
            ViewHolder vh = new ViewHolder(v);


            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder vh, int position) {
            vh.title.setText(customTiles.get(0).Title);
            vh.message.setText(customTiles.get(0).Detail);
            Log.d("Hudson", customTiles.get(0).Title);
        }

        @Override
        public int getItemCount() {
            return customTiles.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView message;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.CustomTitle);
            message = (TextView) itemView.findViewById(R.id.CustomDescription);
        }
    }
}
