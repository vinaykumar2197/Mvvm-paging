package com.vinaykumar.myapplication.model;


import java.util.ArrayList;
import java.util.List;
import android.databinding.BindingAdapter;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vinaykumar.myapplication.R;

public class Movies  implements Parcelable
{

    @SerializedName("popularity")
    @Expose
    private double popularity;
    @SerializedName("vote_count")
    @Expose
    private int voteCount;
    @SerializedName("video")
    @Expose
    private boolean video;
//    @SerializedName("poster_path")
//    @Expose
//    private String posterPath;
//    @SerializedName("id")

    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @BindingAdapter({"posterPath"})
    public static void loadImage(ImageView imageView, String imageURL){

        String imagePath="https://image.tmdb.org/t/p/w500"+imageURL;

        Glide.with(imageView.getContext())
                .load(imagePath)
                .placeholder(R.drawable.loading)
                .into(imageView);
    }


    @Expose
    private int id;

    @SerializedName("adult")
    @Expose
    private boolean adult;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = new ArrayList<>();
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("vote_average")
    @Expose
    private double voteAverage;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    public final static Parcelable.Creator<Movies> CREATOR = new Creator<Movies>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        public Movies[] newArray(int size) {
            return (new Movies[size]);
        }

    }
            ;

    protected Movies(Parcel in) {
        this.popularity = ((double) in.readValue((double.class.getClassLoader())));
        this.voteCount = ((int) in.readValue((int.class.getClassLoader())));
        this.video = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.posterPath = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.adult = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.backdropPath = ((String) in.readValue((String.class.getClassLoader())));
        this.originalLanguage = ((String) in.readValue((String.class.getClassLoader())));
        this.originalTitle = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.genreIds, (java.lang.Integer.class.getClassLoader()));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.voteAverage = ((double) in.readValue((double.class.getClassLoader())));
        this.overview = ((String) in.readValue((String.class.getClassLoader())));
        this.releaseDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Movies() {
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(popularity);
        dest.writeValue(voteCount);
        dest.writeValue(video);
        dest.writeValue(posterPath);
        dest.writeValue(id);
        dest.writeValue(adult);
        dest.writeValue(backdropPath);
        dest.writeValue(originalLanguage);
        dest.writeValue(originalTitle);
        dest.writeList(genreIds);
        dest.writeValue(title);
        dest.writeValue(voteAverage);
        dest.writeValue(overview);
        dest.writeValue(releaseDate);
    }

    public int describeContents() {
        return 0;
    }


    public static final DiffUtil.ItemCallback<Movies> CALLBACK = new DiffUtil.ItemCallback<Movies>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movies oldItem, @NonNull Movies newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movies oldItem, @NonNull Movies newItem) {
            return true;
        }
    };
}
