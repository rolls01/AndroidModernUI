package com.odysseyDesigns.googlePhotos;


import com.odysseyDesigns.googlePhotos.model.AlbumFeedResponse;
import com.odysseyDesigns.googlePhotos.model.UserFeedResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface PicasaService {

    @GET("default")
    Observable<UserFeedResponse> getUserFeedResponse();

    @GET("default/albumid/{albumId}")
    Observable<AlbumFeedResponse> getAlbumFeedResponse(@Path("albumId") long albumId);

}
