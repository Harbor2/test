package com.example.nine.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nine.R;
import com.example.nine.mine.mybean.Dcteam_Recoed_Item;

import java.util.ArrayList;
import java.util.List;

public class Dcteam_RecycleView_Adapter extends RecyclerView.Adapter<Dcteam_RecycleView_Adapter.DcteamItemHolder> {

    private Context context;
    private List<Dcteam_Recoed_Item> record_list = new ArrayList<>();
    public Dcteam_RecycleView_Adapter(Context context, List<Dcteam_Recoed_Item> record_list) {
        this.context = context;
        this.record_list = record_list;
    }

    @NonNull
    @Override
    public Dcteam_RecycleView_Adapter.DcteamItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.dcteam_record_item, null);
        return new Dcteam_RecycleView_Adapter.DcteamItemHolder(view);
    }

    /**
     * 将每个item做的事情展示出来
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull Dcteam_RecycleView_Adapter.DcteamItemHolder holder, final int position) {
        holder.dcteam_naem.setText(record_list.get(position).getDcteam_name()+position);
        holder.record_time.setText(record_list.get(position).getRecord_time());
    }

    @Override
    public int getItemCount() {
        return record_list.size();
    }

    /**
     * 下面是集中需要加载的viewHolder
     */
    public class DcteamItemHolder extends RecyclerView.ViewHolder {
        public TextView dcteam_naem;
        public TextView record_time;
        public DcteamItemHolder(View itemView) {
            super(itemView);
            dcteam_naem = itemView.findViewById(R.id.team_name);
            record_time = itemView.findViewById(R.id.record_time);
        }
    }

}
