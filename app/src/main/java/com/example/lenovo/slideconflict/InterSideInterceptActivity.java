package com.example.lenovo.slideconflict;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Lenovo on 2018/1/7.
 */

public class InterSideInterceptActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.innerside_intercept);

        HorizontalViewGroup2 horizontalViewGroup2 = (HorizontalViewGroup2) findViewById(R.id.horizontal);

        MyListView list1 = (MyListView) findViewById(R.id.list1);
        list1.setParentView(horizontalViewGroup2);
        String[] string1 = new String[50];
        String[] string2 = new String[50];
        String[] string3 = new String[50];
        for (int i  = 0; i < string1.length; i++) {
            string1[i] = "list1---" + (i + 1);
            string2[i] = "list2---" + (i + 1);
            string3[i] = "list3---" + (i + 1);
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, string1);
        list1.setAdapter(adapter1);

        MyListView list2 = (MyListView) findViewById(R.id.list2);
        list2.setParentView(horizontalViewGroup2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, string2);
        list2.setAdapter(adapter2);

        MyListView list3 = (MyListView) findViewById(R.id.list3);
        list3.setParentView(horizontalViewGroup2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, string3);
        list3.setAdapter(adapter3);
    }
}
