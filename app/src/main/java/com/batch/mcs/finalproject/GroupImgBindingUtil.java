package com.batch.mcs.finalproject;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

public class GroupImgBindingUtil {
    @BindingAdapter({"groupSrc"})
    public static void setImageViewResource(ImageView imageView, Bitmap source){
        imageView.setImageBitmap(source);
    }

}
