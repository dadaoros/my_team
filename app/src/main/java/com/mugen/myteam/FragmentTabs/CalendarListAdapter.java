package com.mugen.myteam.FragmentTabs;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mugen.myteam.Models.CalendarItem;
import com.mugen.myteam.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

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
        CalendarItem item = (CalendarItem)calendarList.get(position);
        TextView equipoLocal=(TextView)view.findViewById(R.id.calendar_localteam);
        TextView equipoVisitante=(TextView)view.findViewById(R.id.calendar_visitorteam);
        TextView goles=(TextView)view.findViewById(R.id.calendar_goals_hour);
        TextView hora=(TextView)view.findViewById(R.id.match_hour);
        TextView numeroFecha=(TextView)view.findViewById(R.id.date_number);
        TextView fecha=(TextView)view.findViewById(R.id.match_date);

        equipoLocal.setText(item.getLocalTeamName());
        equipoVisitante.setText(item.getVisitorTeamName());
        numeroFecha.setText("Fecha "+item.getFechaPartido());
        if(item.getGolesVisitante()!=null)
            goles.setText(item.getGolesLocal()+" - "+item.getGolesVisitante());
        else {
            SimpleDateFormat formato=new SimpleDateFormat("hh:mm aa");
            hora.setText(formato.format(item.getDateTimePartido().getTime()));
        }
        SimpleDateFormat f_fecha=new SimpleDateFormat("EEEE, dd 'de' MMMM",new Locale("es","CO"));
        fecha.setText(f_fecha.format(item.getDateTimePartido()));
        return view;
    }
    @Override
    public int getCount(){
        if(calendarList!=null)
            return calendarList.size();
        else
            return 0;
    }

}
