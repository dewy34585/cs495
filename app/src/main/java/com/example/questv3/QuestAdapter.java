package com.example.questv3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestAdapter extends RecyclerView.Adapter<QuestAdapter.QuestViewHolder> {

    Context mContext;
    private List<QuestItem> mData;
    private OnQuestListener mOnQuestListener;

    public QuestAdapter(Context mContext, List<QuestItem> mData, OnQuestListener onQuestListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.mOnQuestListener = onQuestListener;
    }

    @NonNull
    @Override
    public QuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.item_quest,parent,false);

        return new QuestViewHolder(layout, mOnQuestListener);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestViewHolder holder, int position) {

        // Apply animations here
        holder.imgUser.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_trans_anim));
        holder.container.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_scale_anim));

        // Bind data here
        holder.tv_title.setText(mData.get(position).getTitle());
        holder.tv_content.setText(mData.get(position).getContent());
        holder.tv_date.setText(mData.get(position).getDate());
        holder.imgUser.setImageResource(mData.get(position).getIcon());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class QuestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_title,tv_content,tv_date;
        ImageView imgUser;
        RelativeLayout container;
        OnQuestListener onQuestListener;

        public QuestViewHolder(@NonNull View itemView, OnQuestListener onQuestListener) {
            super(itemView);
            container = itemView.findViewById(R.id.questContainerRL);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_description);
            tv_date = itemView.findViewById(R.id.tv_date_create);
            imgUser = itemView.findViewById(R.id.imgUser);
            this.onQuestListener = onQuestListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onQuestListener.onQuestClick(getAdapterPosition());
        }
    }

    public interface OnQuestListener{
        void onQuestClick(int position);
    }
}
