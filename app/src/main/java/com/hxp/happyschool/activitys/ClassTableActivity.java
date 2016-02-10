package com.hxp.happyschool.activitys;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxp.happyschool.R;
import com.hxp.happyschool.adapters.WifiAdapter;

import java.util.ArrayList;
import java.util.List;

/**课表Activity
 * Created by hxp on 16-1-20.
 */
public class ClassTableActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classtable);
    }

}