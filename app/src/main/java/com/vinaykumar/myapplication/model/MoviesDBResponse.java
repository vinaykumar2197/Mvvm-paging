package com.vinaykumar.myapplication.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoviesDBResponse implements Parcelable
{

    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("total_results")
    @Expose
    private int totalMoviess;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("results")
    @Expose
    private List<Movies> results = null;
    public final static Parcelable.Creator<MoviesDBResponse> CREATOR = new Creator<MoviesDBResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MoviesDBResponse createFromParcel(Parcel in) {
            return new MoviesDBResponse(in);
        }

        public MoviesDBResponse[] newArray(int size) {
            return (new MoviesDBResponse[size]);
        }

    }
            ;

    protected MoviesDBResponse(Parcel in) {
        this.page = ((int) in.readValue((int.class.getClassLoader())));
        this.totalMoviess = ((int) in.readValue((int.class.getClassLoader())));
        this.totalPages = ((int) in.readValue((int.class.getClassLoader())));
        in.readList(this.results, (Movies.class.getClassLoader()));
    }

    public MoviesDBResponse() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalMoviess() {
        return totalMoviess;
    }

    public void setTotalMoviess(int totalMoviess) {
        this.totalMoviess = totalMoviess;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Movies> getMoviess() {
        return results;
    }

    public void setMoviess(List<Movies> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeValue(totalMoviess);
        dest.writeValue(totalPages);
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }

}