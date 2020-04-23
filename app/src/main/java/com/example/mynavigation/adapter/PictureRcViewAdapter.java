package com.example.mynavigation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynavigation.R;

public class VideoRcViewAdapter extends RecyclerView.Adapter<VideoRcViewAdapter.rvHolder> {
    //    获取视频的id
    int[] videoID = new int[]{R.raw.mov, R.raw.mov2, R.raw.mov3, R.raw.mov4};

    //    设置视频缩略图下的名字
    String[] videoName = new String[]{"mov.mp4", "mov2.mp4", "mov3.mp4", "mov4.mp4"};


//    设置每一个item的监听事件
    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener){
        this.clickListener = clickListener;
    }

//    回调的接口
    public interface OnItemClickListener{
        void onItemClick(View v,int videoId);
    }

    @NonNull
    @Override
    public rvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        item_rview_adapter_video是每个创建的子类视图的模板
        return new rvHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_rcview_ftvadapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull rvHolder holder, final int position) {
        holder.ivVideo.setImageResource(R.drawable.pic);
        holder.tvName.setText(videoName[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null){
                    clickListener.onItemClick(v,videoID[position]);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoID.length;
    }

    class rvHolder extends RecyclerView.ViewHolder {
        //        设置全局变量，在上面的onBindViewHolder方法中可以直接调用
        ImageView ivVideo;
        TextView tvName;


        public rvHolder(@NonNull View itemView) {
            super(itemView);
            ivVideo = itemView.findViewById(R.id.iv_video_pic);
            tvName = itemView.findViewById(R.id.tv_video_name);
        }
    }
}
