package com.example.hubertr.simplelistview;

import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ImageView;

/**
 * Created by hubertr on 2015-01-20.
 */
public class ImageHolder {
    private String imageDate;
    private String imageUrl;
    private int icon;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public ImageHolder(String imageDate, String imageUrl, StringBuffer imageDescription, String imageTitle, int icon) {
        this.imageDate = imageDate;
        this.imageUrl = imageUrl;
        this.imageDescription = imageDescription;
        this.imageTitle = imageTitle;
        this.icon = icon;


    }

    public ImageHolder(){}

    public StringBuffer getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(StringBuffer imageDescription) {
        this.imageDescription = imageDescription;
    }

    private StringBuffer imageDescription;

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }

    private String imageTitle;


   // @Override
   // public String toString() {
   //     return imageDescription.toString();
   // }

    public String getImageDate() {
        return imageDate;
    }

    public void setImageDate(String imageDate) {
        this.imageDate = imageDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



}
