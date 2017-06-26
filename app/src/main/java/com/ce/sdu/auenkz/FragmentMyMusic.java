package com.ce.sdu.auenkz;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.io.IOException;

import static android.content.Context.AUDIO_SERVICE;

public class FragmentMyMusic extends Fragment implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, View.OnClickListener {
    final String LOG_TAG = "myLogs";
    final String DATA_HTTP = "http://a1.spacebro.net//2/a/gt/ki/8b/kf/gtki8bkf.mp3?id=gtki8bkf";
    MediaPlayer mediaPlayer;
    AudioManager am;
    CheckBox chbLoop;
    public FragmentMyMusic() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_music, container, false);
        Button b = (Button) rootView.findViewById(R.id.btnStartHttp);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStart(v);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        am = (AudioManager) getActivity().getSystemService(AUDIO_SERVICE);
//        chbLoop = (CheckBox) getActivity().findViewById(R.id.chbLoop);
//        chbLoop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView,
//                                         boolean isChecked) {
//                if (mediaPlayer != null)
//                    mediaPlayer.setLooping(isChecked);
//            }
//        });
  }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void onClickStart(View view) {
        releaseMP();
        try {
            switch (view.getId()) {
                case R.id.btnStartHttp:
                    Log.d(LOG_TAG, "start HTTP");
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(DATA_HTTP);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    Log.d(LOG_TAG, "prepareAsync");
                    mediaPlayer.setOnPreparedListener(this);
                    mediaPlayer.prepareAsync();
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mediaPlayer == null)
            return;

//        mediaPlayer.setLooping(chbLoop.isChecked());
        mediaPlayer.setOnCompletionListener(this);
    }

    private void releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

   public void onClick(View view) {
        if (mediaPlayer == null)
            return;
        switch (view.getId()) {
//            case R.id.btnPause:
//                if (mediaPlayer.isPlaying())
//                    mediaPlayer.pause();
//                break;
//            case R.id.btnResume:
//                if (!mediaPlayer.isPlaying())
//                    mediaPlayer.start();
//                break;
//            case R.id.btnStop:
//                mediaPlayer.stop();
//                break;
//            case R.id.btnBackward:
//                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 3000);
//                break;
//            case R.id.btnForward:
//                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 3000);
//                break;
//            case R.id.btnInfo:
//                Log.d(LOG_TAG, "Playing " + mediaPlayer.isPlaying());
//                Log.d(LOG_TAG, "Time " + mediaPlayer.getCurrentPosition() + " / "
//                        + mediaPlayer.getDuration());
//                mediaPlayer.seekTo(200000);
//                Log.d(LOG_TAG, "Looping " + mediaPlayer.isLooping());
//                Log.d(LOG_TAG,
//                        "Volume " + am.getStreamVolume(AudioManager.STREAM_MUSIC));
//                break;
       }
   }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.d(LOG_TAG, "onPrepared");
        mp.start();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.d(LOG_TAG, "onCompletion");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseMP();
    }
}