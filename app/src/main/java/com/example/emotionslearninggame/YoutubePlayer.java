package com.example.emotionslearninggame;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class YoutubePlayer extends YouTubeBaseActivity {
    public static final String YOUTUBE_API_KEY = "AIzaSyCvh-eaBvBoQDskEKsd8iHhASpz4HarGxA";
    public static final String VIDEO_ID = "UsISd1AMNYU";
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);

        Bundle getVideo = getIntent().getExtras();
        int getVdeo = getVideo.getInt("video");
        i = getVdeo;

        initialize();
    }

    private void initialize() {
        FragmentManager fm = getFragmentManager();
        String tag = YouTubePlayerFragment.class.getSimpleName();
        YouTubePlayerFragment playerFragment = (YouTubePlayerFragment) fm.findFragmentByTag(tag);

        if (playerFragment == null) {
            FragmentTransaction ft = fm.beginTransaction();
            playerFragment = YouTubePlayerFragment.newInstance();
            ft.add(android.R.id.content, playerFragment, tag);
            ft.commit();
        }

        playerFragment.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if (i == 1) {
                    youTubePlayer.cueVideo("zEk48QQSPo4"); //UsISd1AMNYU
                }else if (i == 2) {
                    youTubePlayer.cueVideo("UsISd1AMNYU"); //Hr5IOEQI7eg
                }else if (i == 3) {
                    youTubePlayer.cueVideo("ca8SUuG8vdA");
                }else if (i == 4) {
                    youTubePlayer.cueVideo("a1NIWCr0R-k");
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(YoutubePlayer.this, "Error while initializing YouTubePlayer.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}