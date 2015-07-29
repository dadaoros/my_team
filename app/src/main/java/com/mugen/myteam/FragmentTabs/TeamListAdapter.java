package com.mugen.myteam.FragmentTabs;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mugen.myteam.R;

import java.util.List;

/**
 * Created by ORTEGON on 22/05/2015.
 */
public class TeamListAdapter extends ArrayAdapter {
    List teams;
    public TeamListAdapter(List objects,Context context) {
        super(context, R.layout.teams_list, objects);
        teams=objects;
    }
    @Override
    public View getView(int position,View view, ViewGroup parent){
        if(view==null){
            view=((Activity)parent.getContext()).getLayoutInflater().inflate(R.layout.team_item,null);
        }
        TextView nombreEquipo=(TextView)view.findViewById(R.id.teamname_item);
        nombreEquipo.setText(teams.get(position).toString());
        return view;
    }

}
