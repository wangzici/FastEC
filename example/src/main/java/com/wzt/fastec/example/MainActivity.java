package com.wzt.fastec.example;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import wzt.latte_core.app.ConfigType;
import wzt.latte_core.app.Latte;

/**
 * @author Tao
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText((Context) Latte.getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name()), "传入了Context啦", Toast.LENGTH_SHORT).show();
    }
}
