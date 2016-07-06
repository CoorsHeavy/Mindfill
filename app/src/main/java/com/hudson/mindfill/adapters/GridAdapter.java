package com.hudson.mindfill.adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hudson.mindfill.MapSettings;
import com.hudson.mindfill.R;
import com.hudson.mindfill.lib.helper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import me.grantland.widget.AutofitHelper;

public class GridAdapter extends BaseAdapter{
    private Context mContext;
    private final ArrayList<Integer> myList;

    public GridAdapter(Context c, ArrayList<Integer> myList) {
        mContext = c;
        this.myList = helper.getList(c);
        for(int a: myList){
            Log.d("Hudson", String.valueOf(a) + " " + helper.getTreatmentName(c, a));
        }
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return myList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.tile, null);
            TextView textView = (TextView) grid.findViewById(R.id.button_text);
            final RelativeLayout ll = (RelativeLayout) grid.findViewById(R.id.srl);
            ImageView img = (ImageView) grid.findViewById(R.id.img);
            final ImageView check = (ImageView) grid.findViewById(R.id.check);
            final RelativeLayout front = (RelativeLayout) grid.findViewById(R.id.front);
            final LinearLayout back = (LinearLayout) grid.findViewById(R.id.back);
            Button info = (Button) grid.findViewById(R.id.infoButon);
            Button web = (Button) grid.findViewById(R.id.webButon);
            Button yelp = (Button) grid.findViewById(R.id.yelpButton);
            Button backButton = (Button) grid.findViewById(R.id.backButton);
            Button moreButton = (Button) grid.findViewById(R.id.moreButton);
            AutofitHelper.create(info);
            AutofitHelper.create(web);
            AutofitHelper.create(backButton);

            if(new MapSettings(mContext).retrieve(position, new Date())){
                check.setVisibility(View.VISIBLE);
            }else{
                check.setVisibility(View.GONE);
            }

            yelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.launchSearch(mContext, myList.get(position));
                //v.animate().rotationYBy()
//                ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(mContext, R.animator.flipping);
//                anim.setTarget(v);
//                anim.setDuration(3000);
//                anim.start();
            }});

            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    helper.launchDialog(mContext, myList.get(position));
                }
            });
            web.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String query = helper.getTreatmentName(mContext, myList.get(position));
                    Uri uri = Uri.parse("http://www.google.com/#q=" + query);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    mContext.startActivity(intent);
                }
            });
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ll.animate()
                            .rotationYBy(90)
                            .setDuration(300)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    front.setVisibility(View.VISIBLE);
                                    ll.animate()
                                            .rotationYBy(90)
                                            .setDuration(300)
                                            .setListener(new AnimatorListenerAdapter() {
                                                @Override
                                                public void onAnimationEnd(Animator animation) {
                                                    back.setVisibility(View.GONE);
                                                }
                                            });
                                }
                            });
                }
            });
            front.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ll.animate()
                            .rotationYBy(90)
                            .setDuration(300)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    front.setVisibility(View.GONE);
                                    ll.animate()
                                            .rotationYBy(90)
                                            .setDuration(300)
                                            .setListener(new AnimatorListenerAdapter() {
                                                @Override
                                                public void onAnimationEnd(Animator animation) {
                                                    back.setVisibility(View.VISIBLE);
                                                }
                                            });
                                }
                            });

                }
            });
            front.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    if(new MapSettings(mContext).retrieve(position, new Date())){
                        new MapSettings(mContext).minus(position, new Date());
                        check.setVisibility(View.GONE);
                    }else{
                        new MapSettings(mContext).add(position, new Date());
                        check.setVisibility(View.VISIBLE);
                    }

                    notifyDataSetChanged();
                    return false;
                }
            });
            moreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] options = {"Schedule in Calender", "Remove form list"
                            //, "Mark as done for today"
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setItems(options, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // The 'which' argument contains the index position
                            // of the selected item
                            switch (which) {
                                case 0:
                                    Intent intent = new Intent(Intent.ACTION_EDIT);
                                    intent.setType("vnd.android.cursor.item/event");
                                    intent.putExtra(CalendarContract.Events.TITLE, helper.getTreatmentName(mContext, myList.get(position)));
                                    try {
                                        mContext.startActivity(intent);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Toast.makeText(mContext, "Either your calendar apps are incompatible or nonexistent", Toast.LENGTH_LONG).show();
                                    }
                                    break;
                                case 1:
                                    Log.d("Hudson", "Removing " + String.valueOf(position));
                                    myList.remove(position);
                                    helper.putList(myList, mContext);
                                    notifyDataSetChanged();
                                    break;
                            }
                        }
                    }).show();
                }
            });
            if(new MapSettings(mContext).retrieve(position, new Date())){
                ll.setBackgroundResource(R.drawable.roundtile);
            }else{
                ll.setBackgroundResource(R.drawable.roundtile1);
            }

            textView.setText(helper.getTreatmentName(mContext, myList.get(position)));
            // get input stream


            Log.d("Hudson", helper.getTreatmentName(mContext, myList.get(position)));

        return grid;
    }
    public static Bitmap getBitmapFromAsset(Context context, String filePath) throws Exception {
        AssetManager assetManager = context.getAssets();

        InputStream istr;
        Bitmap bitmap = null;
        istr = assetManager.open(filePath);
        bitmap = BitmapFactory.decodeStream(istr);
        return bitmap;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = Color.BLUE;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));

        PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.argb(180, 0, 182, 142), PorterDuff.Mode.SRC_ATOP);
        paint.setColorFilter(filter);

        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
}
