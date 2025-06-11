package com.example.teamproject_roubithome.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamproject_roubithome.R;
import com.example.teamproject_roubithome.model.QuestItem;

import java.util.List;

public class QuestAdapter extends RecyclerView.Adapter<QuestAdapter.QuestViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(QuestItem item);
    }

    private List<QuestItem> questList;
    private OnItemClickListener listener;
    private Context context;

    public QuestAdapter(Context context, List<QuestItem> questList, OnItemClickListener listener) {
        this.context = context;
        this.questList = questList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quest, parent, false);
        return new QuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestViewHolder holder, int position) {
        QuestItem quest = questList.get(position);
        holder.icon.setImageResource(R.drawable.ic_launcher_foreground);
        holder.title.setText(quest.getTitle());
        String rewardText = "🥕 당근 " + quest.getReward() + "개";
        holder.button.setText(quest.isCompleted() ? "완료됨" : "하러가기");

        holder.button.setOnClickListener(v -> {
            if (!quest.isCompleted()) {
                quest.setCompleted(true);
                notifyItemChanged(position);
                // TODO: 당근 획득 처리 및 레벨 상승 로직 추가
            }

            if (listener != null) {
                listener.onItemClick(quest);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questList.size();
    }

    static class QuestViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title, reward;
        Button button;

        public QuestViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.iv_quest_icon);
            title = itemView.findViewById(R.id.tv_quest_title);
            reward = itemView.findViewById(R.id.tv_quest_reward);
            button = itemView.findViewById(R.id.btn_quest_action);
        }
    }
}