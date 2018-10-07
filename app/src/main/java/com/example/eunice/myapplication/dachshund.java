package com.example.eunice.myapplication;



import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class dachshund extends AppCompatActivity
        implements View.OnClickListener {
    private TextView textview;
    private final int REQ_PERMISSION_AUDIO = 0x01;
    private Button mRecode, mPlay;
    private TextView view_state;
    private File mAudioFile = null;
    private Thread mCaptureThread = null;
    private boolean mIsRecording,mIsPlaying;
    // private int mFrequence = 44100;
    private int mFrequence = 48000;
    private int mChannelConfig = AudioFormat.CHANNEL_IN_MONO;
    private int mPlayChannelConfig = AudioFormat.CHANNEL_IN_STEREO;
    private int mAudioEncoding = AudioFormat.ENCODING_PCM_16BIT;
    private PlayTask mPlayer;
    private RecordTask mRecorder;
    Button mButton;

    int nnumberofFilters = 24;
    int nlifteringCoefficient = 22;
    boolean oisLifteringEnabled = true;
    boolean oisZeroThCepstralCoefficientCalculated = false;
    int nnumberOfMFCCParameters = 12; //without considering 0-th
    //double dsamplingFrequency = 8000.0;
    int nFFTLength = 512;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homedachshund);

        mButton = findViewById(R.id.button3);
        mButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent();
                intent4.setClass(dachshund.this,loginin.class);
                startActivity(intent4);
            }
        });

        mRecode = (Button) findViewById(R.id.audio_recode);
        mPlay = (Button) findViewById(R.id.audio_paly);

        mRecode.setText("record");
        mPlay.setText("Check");

        mPlay.setEnabled(false);

        mRecode.setOnClickListener(this);
        mPlay.setOnClickListener(this);

//        mRecorder = new RecordTask();
//        mPlayer = new PlayTask();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.audio_recode:
                if (mRecode.getTag() == null) {
                    startAudioRecode();
                } else {
                    stopAudioRecode();
                }
                break;
            case R.id.audio_paly:
                if (mPlay.getTag() == null) {
                    startAudioPlay();
                } else {
                    stopAudioPlay();
                }
                break;
        }
    }

    private void startAudioRecode() {
        if (checkPermission()) {
            PackageManager packageManager = this.getPackageManager();
            if (!packageManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
                showToast("This device doesn't have a mic!");
                return;
            }
            mRecode.setTag(this);
            mRecode.setText("stop");
            mPlay.setEnabled(false);

            File fpath = new File(Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/slack");
            fpath.mkdirs();// ?建文件?
            try {
                // ?建??文件,注意?里的格式?.pcm
                mAudioFile = File.createTempFile("recording", ".pcm", fpath);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            mRecorder = new RecordTask();
            mRecorder.execute();

            showToast("Recording started");

        } else {
            requestPermission();
        }
    }

    private void stopAudioRecode() {

        mIsRecording = false;

        mRecode.setTag(null);
        mRecode.setText("recode");
        mPlay.setEnabled(true);
        showToast("Recording Completed");
    }

    private void startAudioPlay() {
        mPlay.setTag(this);
        mPlay.setText("Check");

        mPlayer = new PlayTask();
        mPlayer.execute();

        showToast("Recording Checking");
    }

    private void stopAudioPlay() {

        mIsPlaying = false;

        mPlay.setTag(null);
        mPlay.setText("Check");

    }


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, REQ_PERMISSION_AUDIO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQ_PERMISSION_AUDIO:
                if (grantResults.length > 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        showToast("Permission Granted");
                    } else {
                        showToast("Permission  Denied");
                    }
                }
                break;
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    class RecordTask extends AsyncTask<Void,Integer,Void> {
        @Override
        protected Void doInBackground(Void... arg0) {
            mIsRecording = true;
            try {
                // ?通?出流到指定的文件
                DataOutputStream dos = new DataOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(mAudioFile)));
                // 根据定?好的几?配置，??取合适的??大小
                int bufferSize = AudioRecord.getMinBufferSize(mFrequence,
                        mChannelConfig, mAudioEncoding);
                // ?例化AudioRecord
                AudioRecord record = new AudioRecord(
                        MediaRecorder.AudioSource.MIC, mFrequence,
                        mChannelConfig, mAudioEncoding, bufferSize);
                // 定???
                short[] buffer = new short[bufferSize];


                // ?始?制
                record.startRecording();


                int r = 0; // 存??制?度
                // 定?循?，根据isRecording的值?判?是否???制
                while (mIsRecording) {
                    // ?bufferSize中?取字?，返回?取的short??
                    int bufferReadResult = record
                            .read(buffer, 0, buffer.length);
                    // 循??buffer中的音??据?入到OutputStream中
                    for (int i = 0; i < bufferReadResult; i++) {
                        dos.writeShort(buffer[i]);
                    }
                    publishProgress(new Integer(r)); // 向UI?程?告?前?度
                    r++; // 自增?度值
                }
                // ?制?束
                record.stop();
                Log.i("slack", "::" + mAudioFile.length());
                dos.close();
            } catch (Exception e) {
                // TODO: handle exception
                Log.e("slack", "::" + e.getMessage());
            }
            return null;
        }
        // ?在上面方法中?用publishProgress?，?方法触?,?方法在UI?程中被?行
        protected void onProgressUpdate(Integer... progress) {
            //
        }
        protected void onPostExecute(Void result) {
        }
    }
    /**
     * AudioTrack
     */
    class PlayTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... arg0) {
            mIsPlaying = true;
            int bufferSize = AudioTrack.getMinBufferSize(mFrequence,
                    mPlayChannelConfig, mAudioEncoding);
            short[] buffer = new short[bufferSize ];
            try {
                // 定??入流，?音??入到AudioTrack?中，??播放
                DataInputStream dis = new DataInputStream(
                        new BufferedInputStream(new FileInputStream(mAudioFile)));
                // ?例AudioTrack
                AudioTrack track = new AudioTrack(AudioManager.STREAM_MUSIC,
                        mFrequence,
                        mPlayChannelConfig, mAudioEncoding, bufferSize,
                        AudioTrack.MODE_STREAM);
                // ?始播放
                track.play();
                // 由于AudioTrack播放的是流，所以，我?需要一?播放一??取
                while (mIsPlaying && dis.available() > 0) {
                    int i = 0;
                    while (dis.available() > 0 && i < buffer.length) {
                        buffer[i] = dis.readShort();
                        i++;
                    }
                    // 然后??据?入到AudioTrack中
                    track.write(buffer, 0, buffer.length);
                }

                if (oisZeroThCepstralCoefficientCalculated) {
                    //take in account the zero-th MFCC
                    nnumberOfMFCCParameters = nnumberOfMFCCParameters + 1;
                }
                else {
                    nnumberOfMFCCParameters = nnumberOfMFCCParameters;
                }

                MFCC mfcc = new MFCC(nnumberOfMFCCParameters,
                        mFrequence,
                        nnumberofFilters,
                        nFFTLength,
                        oisLifteringEnabled,
                        nlifteringCoefficient,
                        oisZeroThCepstralCoefficientCalculated);

                //simulate a frame of speech

                double[] temp = new double[buffer.length];

                for(int i = 0; i<buffer.length; i++){

                    temp[i] = (double)buffer[i];

                }
                double[] dparameters = mfcc.getParameters(temp);

                int answer = connect.MLCal(dparameters);

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(answer);
                Log.i("answer",stringBuilder.toString());

                if (answer==0){

                    Intent mainIntent = new Intent(dachshund.this, listdachshund.class);
                    startActivity(mainIntent);
                    // 當跳到另一 Activity 時，讓 MainActivity 結束。
                    // 這樣可以避免使用者按 back 後，又回到該 activity。

                }
                else {


                    Intent mainIntent = new Intent(dachshund.this, bodyhappydachshund.class);
                    startActivity(mainIntent);
                    // 當跳到另一 Activity 時，讓 MainActivity 結束。
                    // 這樣可以避免使用者按 back 後，又回到該 activity。

                }
                // 播放?束
                track.stop();
                dis.close();
            } catch (Exception e) {
                // TODO: handle exception
                Log.e("slack","error:" + e.getMessage());
            }
            return null;
        }
        protected void onPostExecute(Void result) {
        }
        protected void onPreExecute() {
        }
    }
}


/*
public class dachshund extends AppCompatActivity {
    @SuppressLint("WrongViewCast")
    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homedachshund);

        mButton = findViewById(R.id.button3);
        mButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent();
                intent5.setClass(dachshund.this, listdachshund.class);
                startActivity(intent5);    }
        });

    }
}
*/