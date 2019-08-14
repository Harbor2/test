package com.example.nine.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nine.R;
import com.example.nine.mine.activity.FeedBack_ReplyActivity;
import com.example.nine.mine.mybean.Dcteam_Recoed_Item;
import com.example.nine.mine.mybean.FeedBack_Item;

import java.util.ArrayList;
import java.util.List;

public class FeedBack_RecycleView_Adapter extends RecyclerView.Adapter<FeedBack_RecycleView_Adapter.DcteamItemHolder> {

    private Context context;
    private List<FeedBack_Item> feedback_list = new ArrayList<>();
    public FeedBack_RecycleView_Adapter(Context context, List<FeedBack_Item> feedback_list) {
        this.context = context;
        this.feedback_list = feedback_list;
    }

    @NonNull
    @Override
    public FeedBack_RecycleView_Adapter.DcteamItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.feedback_item, null);
        return new FeedBack_RecycleView_Adapter.DcteamItemHolder(view);
    }

    /**
     * 将每个item做的事情展示出来
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull FeedBack_RecycleView_Adapter.DcteamItemHolder holder, final int position) {
        int textposition = position+1;
        holder.title.setText("工单"+textposition);
        holder.content.setText(feedback_list.get(position).getContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FeedBack_ReplyActivity.class);
                intent.putExtra("content", feedback_list.get(position).getContent());
                intent.putExtra("reply",feedback_list.get(position).getReply());
                intent.putExtra("title","工单"+textposition);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedback_list.size();
    }

    /**
     * 下面是集中需要加载的viewHolder
     */
    public class DcteamItemHolder extends RecyclerView.ViewHolder {
        public TextView content;
        public TextView title;
        public DcteamItemHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.tv_content_feedback);
            title = itemView.findViewById(R.id.tv_title_feedback);
        }
    }

}
