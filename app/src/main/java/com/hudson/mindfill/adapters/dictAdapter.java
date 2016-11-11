package com.hudson.mindfill.adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
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
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hudson.mindfill.R;
import com.hudson.mindfill.lib.StaticClass;

import java.io.InputStream;
import java.util.ArrayList;

import me.grantland.widget.AutofitHelper;

public class dictAdapter extends BaseAdapter implements
        GestureDetector.OnDoubleTapListener{
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Toast.makeText(mContext, "double tapped", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    private Context mContext;
    private final ArrayList<Integer> myList;

    public dictAdapter(Context c) {
        mContext = c;
        this.myList = StaticClass.getIntstnace().getArrayIntList();
        for(int a: myList){
            Log.d("Hudson", String.valueOf(a) + " " + StaticClass.getIntstnace().getTreatmentName(a));
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
        Log.d("Hudson", "preGetView " + String.valueOf(position));


            Log.d("Hudson", "getView " + String.valueOf(position));
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.tiledict, null);
            final RelativeLayout ll = (RelativeLayout) grid.findViewById(R.id.srl);
            TextView textView = (TextView) grid.findViewById(R.id.button_text);
            ImageView img = (ImageView) grid.findViewById(R.id.img);
            final RelativeLayout front = (RelativeLayout) grid.findViewById(R.id.front);
            final LinearLayout back = (LinearLayout) grid.findViewById(R.id.back);
            Button info = (Button) grid.findViewById(R.id.infoButon);
            Button web = (Button) grid.findViewById(R.id.webButon);
            Button yelp = (Button) grid.findViewById(R.id.yelpButton);
            Button backButton = (Button) grid.findViewById(R.id.backButton);
            AutofitHelper.create(info);
            AutofitHelper.create(web);
            AutofitHelper.create(backButton);

            yelp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    StaticClass.getIntstnace().launchSearch(mContext, myList.get(position));
                }
            });

            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StaticClass.getIntstnace().launchDialog(mContext, myList.get(position));
                }
            });
            web.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String query = StaticClass.getIntstnace().getTreatmentName(myList.get(position));
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

                    return false;
                }
            });


            ll.setBackgroundResource(R.drawable.roundtile);
            textView.setText(StaticClass.getIntstnace().getTreatmentName(myList.get(position)));


            Log.d("Hudson", StaticClass.getIntstnace().getTreatmentName(myList.get(position)));

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
