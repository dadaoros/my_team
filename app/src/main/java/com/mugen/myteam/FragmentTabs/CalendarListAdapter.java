package com.mugen.myteam.FragmentTabs;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mugen.myteam.Models.CalendarItem;
import com.mugen.myteam.R;

import java.util.List;

/**
 * Created by ORTEGON on 22/05/2015.
 */
public class CalendarListAdapter extends ArrayAdapter {
    List calendarList;
    public CalendarListAdapter(List objects, Context context) {
        super(context, R.layout.fragment_calendar, objects);
        calendarList=objects;
    }
    @Override
    public View getView(int position,View view, ViewGroup parent){
        if(view==null){
            view=((Activity)parent.getContext()).getLayoutInflater().inflate(R.layout.calendar_item,null);
        }
        TextView equipoLocal=(TextView)view.findViewById(R.id.calendar_localteam);
        equipoLocal.setText(((CalendarItem)calendarList.get(position)).toString());

        TextView equipoVisitante=(TextView)view.findViewById(R.id.calendar_visitorteam);
        equipoVisitante.setText(((CalendarItem)calendarList.get(position)).toString());
        return view;
    }

}
