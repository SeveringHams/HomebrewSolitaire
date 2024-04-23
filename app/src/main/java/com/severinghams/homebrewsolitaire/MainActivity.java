package com.severinghams.homebrewsolitaire;

import static com.severinghams.homebrewsolitaire.R.drawable.background;

import android.content.Context;
import android.content.ContextParams;
import android.content.ContextWrapper;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import com.google.androidgamesdk.GameActivity;

import java.util.Objects;

public class MainActivity extends GameActivity {
    //public static final int screenHeight;
    //public static final int screenWidth;
    static {
        System.loadLibrary("homebrewsolitaire");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            hideSystemUi();
        }
    }

    private void hideSystemUi() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        mSurfaceView.setBackground(AppCompatResources.getDrawable(this,background));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}