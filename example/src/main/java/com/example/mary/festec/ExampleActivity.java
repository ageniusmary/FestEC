package com.example.mary.festec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mary.mary.activities.ProxyActivity;
import com.example.mary.mary.app.Mary;
import com.example.mary.mary.delegates.MaryDelegate;

public class ExampleActivity extends ProxyActivity {


    @Override
    public MaryDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
