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
public class FragmentThree extends Fragment {

    View view;
    ReceiveEditorRecommend task;
    TextView text_result;
    private GridView grid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState) {
        view = inflater.inflate(R.layout.fragment_three, null);

        grid = (GridView)view.findViewById(R.id.editor_recommend);
        text_result = (TextView)view.findViewById(R.id.frag_three);
        task = new ReceiveEditorRecommend(view.getContext(), grid, text_result);
        task.execute();
        setGridView();


        return view;
    }


    private void setGridView() {


        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InfoManager.page = 3;
                Intent intent = new Intent(view.getContext(), StoreActivity.class);
                EditorRecommend editorInfo = (EditorRecommend)parent.getAdapter().getItem(position);
                intent.putExtra("SELECT", "editor");
                intent.putExtra("EDITORINFO", editorInfo);
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
