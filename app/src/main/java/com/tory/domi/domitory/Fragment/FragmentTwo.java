package com.tory.domi.domitory.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.tory.domi.domitory.R;


/**
 * Created by byungwoo on 2016-08-12.
 */
public class FragmentTwo extends Fragment {

    View view;
    private GridView grid;
    TextView text_result;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState) {
        view = inflater.inflate(R.layout.fragment_two, null);

        grid = (GridView)view.findViewById(R.id.month_event);
        text_result = (TextView)view.findViewById(R.id.frag_two);
        task = new ReceiveMonthEvent(view.getContext(), grid, text_result);
        task.execute();
        setGridView();


        return view;
    }


    private void setGridView() {



        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InfoManager.page = 2;
                Intent intent = new Intent(view.getContext(), StoreActivity.class);
                MonthEvent monthInfo = (MonthEvent)parent.getAdapter().getItem(position);
                intent.putExtra("SELECT", "event");
                intent.putExtra("MONTHINFO", monthInfo);
                startActivity(intent);
            }
        });
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }
}
