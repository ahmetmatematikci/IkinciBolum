package com.ahmetmatematikci.ikincibolum;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by a on 3/8/17.
 */

public class Threadler extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threadler);
    }

    public  void onButtonClickThread(View view) {
        switch (view.getId()) {
            case  R.id.sf_thread:
                islemYap();
                Toast.makeText(this,"Bitti",Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void islemYap() {
        Long zaman = System.currentTimeMillis() + 6000;
        while (zaman > System.currentTimeMillis()) {}
    }
}
