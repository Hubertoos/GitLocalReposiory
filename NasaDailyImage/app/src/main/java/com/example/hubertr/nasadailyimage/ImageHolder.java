package com.example.hubertr.nasadailyimage;

/**
 * Created by hubertr on 2015-01-20.
 */
public class ImageHolder {
    private String imageDate;
    private String imageUrl;

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

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

    private String imageDescription;

    public ImageHolder(String imageDate, String imageUrl, String imageDescription) {
        this.imageDate = imageDate;
        this.imageUrl = imageUrl;
        this.imageDescription = imageDescription;
    }

}
