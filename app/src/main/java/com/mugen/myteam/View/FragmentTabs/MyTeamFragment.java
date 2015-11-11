package com.mugen.myteam.View.FragmentTabs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mugen.myteam.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyTeamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyTeamFragment extends Fragment {
    public static MyTeamFragment newInstance() {
        MyTeamFragment fragment = new MyTeamFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public MyTeamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_team, container, false);
    }



}
