package com.odysseydesigns.photosintegrationcourse.ui.googlePhotos;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.odysseyDesigns.googlePhotos.PicasaClient;
import com.odysseyDesigns.googlePhotos.model.AlbumFeed;
import com.odysseyDesigns.googlePhotos.model.UserFeed;
import com.odysseydesigns.photosintegrationcourse.R;

import rx.Completable;
import rx.CompletableSubscriber;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class GooglePhotosFragment extends Fragment {

    private static final String TAG = GooglePhotosFragment.class.getSimpleName();
    private static final String PREF_ACCOUNT = TAG + ".PREF_ACCOUNT";
    private SwipeRefreshLayout refreshLayout;

    public static GooglePhotosFragment newInstance(){
        GooglePhotosFragment googlePhotosFragment = new GooglePhotosFragment();
        Bundle bundle = new Bundle();
        googlePhotosFragment.setArguments(bundle);
        return googlePhotosFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_google_photos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        refreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.colorAccent));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(PicasaClient.get().isInitialized()){
                    loadAlbum();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PicasaClient.get().onActivityResult(requestCode, resultCode, data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableSubscriber() {
                    @Override
                    public void onCompleted() {
                        updateAccount();
                        loadAlbum();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "failed to get account" + e.getMessage());
                        if(e != null)
                            e.printStackTrace();
                    }

                    @Override
                    public void onSubscribe(Subscription d) {

                    }
                });
    }

    private void updateAccount() {
        Account account = PicasaClient.get().getAccount();
        if(account != null){
            PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString(PREF_ACCOUNT, account.name)
                    .apply();
        }
    }

    private void checkAccountOrLoadAlbums(){
        if(PicasaClient.get().isInitialized()){
            loadAlbum();
        }else{
            String savedAccount = PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(PREF_ACCOUNT, null);
            if(!TextUtils.isEmpty(savedAccount)){
                Account account = new Account(savedAccount, PicasaClient.ACCOUNT_TYPE_GOOGLE);
                PicasaClient.get().setAccount(account)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CompletableSubscriber() {
                            @Override
                            public void onCompleted() {
                                loadAlbum();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onSubscribe(Subscription d) {

                            }
                        });

            }
        }
    }
    private void loadPhotos(long albunId){
        refreshLayout.setRefreshing(true);
        PicasaClient.get().getAlbumFeed(albunId)
                .toObservable()
                .retry(3)
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<AlbumFeed>() {
                    @Override
                    public void onSuccess(AlbumFeed albumFeed) {
                        onLoadPhotosFinished(albumFeed);
                    }

                    @Override
                    public void onError(Throwable error) {
                        handleError(error);
                    }
                });
    }

    private void onLoadPhotosFinished(AlbumFeed albumFeed) {
        refreshLayout.setRefreshing(false);
    }

    private void loadAlbum() {
        refreshLayout.setRefreshing(true);
        PicasaClient.get().getUserFeed()
                .toObservable()
                .retry()
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new rx.SingleSubscriber<UserFeed>() {
                    @Override
                    public void onSuccess(UserFeed userFeed) {
                        onLoadAlbumFinished(userFeed);
                    }

                    @Override
                    public void onError(Throwable error) {
                        handleError(error);
                    }
                });
    }

    private void handleError(Throwable error) {
        error.printStackTrace();
        refreshLayout.setRefreshing(false);
        Toast.makeText(getActivity(), "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void onLoadAlbumFinished(UserFeed userFeed) {
        refreshLayout.setRefreshing(false);
        
    }
}