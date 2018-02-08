package com.example.mary.festec;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mary.mary.activities.ProxyActivity;
import com.example.mary.mary.app.Mary;
import com.example.mary.mary.delegates.MaryDelegate;
import com.example.mary.mary.ec.launcher.LauncherScrollDelegate;
import com.example.mary.mary.ec.launcher.launcherDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public MaryDelegate setRootDelegate() {
        return new launcherDelegate();
    }
}
