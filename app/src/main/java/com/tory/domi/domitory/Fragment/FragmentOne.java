package com.tory.domi.domitory.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.tory.domi.domitory.R;


/**
 * Created by byungwoo on 2016-08-12.
 */
public class FragmentOne extends Fragment {

    View view;
    TextView breakfast, lunch, dinner, today;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState) {
        view =  inflater.inflate(R.layout.fragment_one, null);

        setCall();

        return view;
    }

    void setCall() {
        today = (TextView)view.findViewById(R.id.today);
        breakfast = (TextView)view.findViewById(R.id.breakfast);
        lunch = (TextView)view.findViewById(R.id.lunch);
        dinner = (TextView)view.findViewById(R.id.dinner);
    }

    void setListener() {

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InfoManager.page = 1;
                Intent intent = new Intent(view.getContext(), StoreActivity.class);
                StoreForm storeInfo = (StoreForm) parent.getAdapter().getItem(position);
                intent.putExtra("SELECT", "around");
                intent.putExtra("STOREINFO", storeInfo);
                startActivity(intent);

            }
        });
    }

}
