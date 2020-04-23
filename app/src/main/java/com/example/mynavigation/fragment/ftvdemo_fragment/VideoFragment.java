package com.example.mynavigation.fragment.ftvdemo_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynavigation.R;
import com.example.mynavigation.fragment.ftvdemo_fragment.demo_activity.VideoActivity;
import com.example.mynavigation.adapter.VideoRcViewAdapter;

import me.yokeyword.fragmentation.SupportFragment;

public class VideoFragment extends SupportFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fra_video,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rcView = view.findViewById(R.id.rcview_video);

        GridLayoutManager manager = new GridLayoutManager(getContext(),4,GridLayoutManager.VERTICAL,false);
        rcView.setLayoutManager(manager);
       VideoRcViewAdapter rvAdapter =new VideoRcViewAdapter();
        rcView.setAdapter(rvAdapter);
        rvAdapter.setClickListener(new VideoRcViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int videoId) {
                Intent intent = new Intent(getContext(),VideoActivity.class);
                intent.putExtra("videoId",videoId);
                startActivity(intent);

            }
        });

    }
}
