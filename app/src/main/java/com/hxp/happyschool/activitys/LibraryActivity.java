package com.hxp.happyschool.activitys;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxp.happyschool.R;

/**书馆Activity
 * Created by hxp on 16-1-20.
 */
public class LibraryActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library);
    }
}