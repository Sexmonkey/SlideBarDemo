package com.sexymonkey.slidebardemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.DemoViewHolder> {

    private Context mContext;
    private List<ItemBean> mDatas;

    public RecyclerAdapter(Context context,List<ItemBean> datas) {
        mContext = context;
        mDatas = datas;
    }

    @NonNull
    @Override
    public RecyclerAdapter.DemoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item, viewGroup,false);
        return new DemoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DemoViewHolder viewHolder, int i) {

        viewHolder.name.setText(mDatas.get(i).name);
        if(mDatas.get(i).isShowFirstLetter){
            viewHolder.section.setVisibility(View.VISIBLE);
            viewHolder.section.setText(String.valueOf(mDatas.get(i).name.charAt(0)));

        }else{
            viewHolder.section.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class DemoViewHolder extends RecyclerView.ViewHolder{

        public TextView name ;
        public TextView section ;

        public DemoViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
//            section = (TextView)itemView.findViewById(R.id.item_section);

        }
    }
}
