package com.ahmetmatematikci.ikincibolum;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by a on 3/8/17.
 */

public class ThreadlerBeklemeli extends Activity  implements DialogInterface.OnCancelListener{

    private ProgressDialog mDialog;
    private Thread mThread;
     private  boolean mCancelThread = false;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mDialog.incrementProgressBy(1);
                    break;

                case 1:
                    mDialog.dismiss();
                    Toast.makeText(ThreadlerBeklemeli.this, "Bundua Bitirdik",Toast.LENGTH_LONG).show();
            }

            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threadbeklemei);
    }

    public void onButtonClickThreadBeklemeli(View view) {
        switch (view.getId()) {
            case R.id.button:
                islemYapThread();
                Toast.makeText(this,"Bitti", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void islemYapThread() {
        mDialog = new ProgressDialog(this);
        mDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mDialog.setOnCancelListener(this);
        mDialog.setCancelable(true);
        mDialog.setMessage("Biraz bekelyelim.....");
        mDialog.setMax(100);
        mDialog.show();

        mThread = new Thread() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e) {
                    e.printStackTrace();

                }
                for (int i = 100; --i>=0; ) {
                    if (mCancelThread) {
                        break;
                    }
                    mHandler.sendMessage(mHandler.obtainMessage(0, "" + i));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mHandler.sendEmptyMessage(1);
                }
               // super.run();
            }
        };
        mThread.start();
    }


    @Override
    public void onCancel(DialogInterface dialogInterface) {
        mCancelThread = true;


    }
}
