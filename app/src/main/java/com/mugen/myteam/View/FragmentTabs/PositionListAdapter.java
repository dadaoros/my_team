package com.mugen.myteam.View.FragmentTabs;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mugen.myteam.Model.TeamRow;
import com.mugen.myteam.R;

import java.util.List;

/**
 * Created by dadaoros on 22/07/15.
 */
public class PositionListAdapter extends ArrayAdapter{
    List rows;
    public PositionListAdapter(List objects,Context context) {
        super(context, R.layout.fragment_pos_table, objects);
        rows=objects;
    }
    @Override
    public View getView(int pos,View view, ViewGroup parent){
        int position=pos-1;
        if(view==null && pos!=0){
            view=((Activity)parent.getContext()).getLayoutInflater().inflate(R.layout.position_table_row,null);
        }else{
            if (view==null){
                view=((Activity)parent.getContext()).getLayoutInflater().inflate(R.layout.table_header,null);
            }
        }
        if(pos!=0) {
            TextView posicion = (TextView) view.findViewById(R.id.pt_pos);
            TextView partidosJugados = (TextView) view.findViewById(R.id.pt_pj);
            TextView nombreEquipo = (TextView) view.findViewById(R.id.pt_name);
            TextView partidosGanados = (TextView) view.findViewById(R.id.pt_pg);
            TextView partidosPerdidos = (TextView) view.findViewById(R.id.pt_pp);
            TextView partidosEmpatados = (TextView) view.findViewById(R.id.pt_pe);
            TextView golesFavor = (TextView) view.findViewById(R.id.pt_gf);
            TextView golesContra = (TextView) view.findViewById(R.id.pt_gc);
            TextView diferenciaGoles = (TextView) view.findViewById(R.id.pt_dg);
            TextView puntos = (TextView) view.findViewById(R.id.pt_pts);

            posicion.setText(String.valueOf(position + 1));
            nombreEquipo.setText(((TeamRow) rows.get(position)).getTeamName());
            partidosJugados.setText(String.valueOf(((TeamRow) rows.get(position)).getGamesPlayed()));
            partidosGanados.setText(String.valueOf(((TeamRow) rows.get(position)).getGamesWon()));
            partidosPerdidos.setText(String.valueOf(((TeamRow) rows.get(position)).getGamesLost()));
            partidosEmpatados.setText(String.valueOf(((TeamRow) rows.get(position)).getGamesDrawn()));
            golesFavor.setText(String.valueOf(((TeamRow) rows.get(position)).getTeamGoals()));
            golesContra.setText(String.valueOf(((TeamRow) rows.get(position)).getOpponentGoals()));
            diferenciaGoles.setText(String.valueOf(((TeamRow) rows.get(position)).getGoalDifference()));
            puntos.setText(String.valueOf(((TeamRow) rows.get(position)).getPoints()));
        }
        return view;
    }
    @Override
    public int getCount(){
        return rows.size()+1;
    }
}
