package com.mugen.myteam.FragmentTabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mugen.myteam.R;

/**
 * Created by root on 13/07/15.
 */
public class PositionListFragment extends Fragment {
    public PositionListFragment(){

    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pos_grid, container, false);

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }


    public static PositionListFragment newInstance() {
        PositionListFragment f = new PositionListFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", 1);
        f.setArguments(args);

        return f;
    }
}
