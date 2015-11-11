package com.mugen.myteam.View.my_layouts;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;

/**
 * Created by dadaoros on 23/07/15.
 */
public class MyTableLayout extends TableLayout{

    private ArrayAdapter adapter;

    public MyTableLayout(Context context,AttributeSet attributeSet) {
        super(context,attributeSet);
    }

    public void setAdapter(ArrayAdapter adapter) {
        this.adapter = adapter;
        updateTable();
    }
    public void updateTable(){
        this.removeAllViews();
        for(int i=0;i<adapter.getCount();i++){
            this.addView(adapter.getView(i,null,this));
        }
    }
}
