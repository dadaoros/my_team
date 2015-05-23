package com.mugen.myteam;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by ORTEGON on 22/05/2015.
 */
public class TeamListAdapter extends ArrayAdapter {
    public TeamListAdapter(Object[] objects,Activity context) {
        super(context, R.layout.pos_table, objects);
    }
    @Override
    public View getView(int position,View view, ViewGroup parent){
        if(view==null){
            view=((Activity)parent.getContext()).getLayoutInflater().inflate(R.layout.tabla_item,null);
        }
        TextView nombreEquipo=(TextView)view.findViewById(R.id.teamname_item);
        return view;
    }

}
