package com.esstudio.gallery.gridview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.esstudio.gallery.MainActivity;
import com.esstudio.gallery.gridview.ElementItem;
import com.esstudio.gallery.gridview.MyImageView;
import com.esstudio.gallery.util.log;
import com.fedorvlasov.lazylist.ImageLoader;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.ArrayList;

public class ImageAdaptor extends BaseAdapter {

    private ArrayList<ElementItem> mItems;
    private Context context;
    private Paint p;
    private int width;
    private ImageLoader loader;

    public ImageAdaptor(Context context, ArrayList<ElementItem> items) {
        this.mItems = items;
        this.context = context;
        loader = new ImageLoader(context);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mItems.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        com.nostra13.universalimageloader.core.ImageLoader unvLoader = MainActivity
                .getInstance().getUnvLoader();
        DisplayImageOptions options = MainActivity.getInstance()
                .getUnvOptions();

        DisplayImageOptions options2 = new DisplayImageOptions.Builder()
                // .showImageOnLoading(R.drawable.ic_action_play)
                // .showImageForEmptyUri(R.drawable.ic_action_search)
                // .showImageOnFail(R.drawable.ic_action_cancel)
                .resetViewBeforeLoading(true)
                .cacheInMemory(true).cacheOnDisc(true).considerExifParams(true)
//		.displayer(new FadeInBitmapDisplayer(1000))
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();


        MyImageView imageView = null;
        ElementItem item = mItems.get(position);
        width = MainActivity.getInstance().getThumbnailWidth();

        if (convertView == null) { // if it's not recycled, initialize some

            imageView = new MyImageView(context, item);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);


        } else {
            imageView = (MyImageView) convertView;
            imageView.setElementItem(item);
            imageView.setImageResource(0);


        }

        unvLoader.displayImage(item.getImageUrl(), imageView, options);
        log.out("ImageDisplay = " + item.getNum() + " " + item.getImageUrl());
        imageView.setBackgroundColor(Color.LTGRAY);
        imageView.setLayoutParams(new GridView.LayoutParams(width, width));


        return imageView;
    }

}
