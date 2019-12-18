package com.laoxu.webviewdemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.laoxu.webviewdemo.R;
import com.laoxu.webviewdemo.entity.ProductEntity;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {


    private Context context;
    private List<ProductEntity.Product> list;

    public ProductAdapter(Context context, List<ProductEntity.Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.product_item_layout,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv.setText(list.get(position).commodityName);
        Glide.with(context).load(list.get(position).masterPic)
                .placeholder(R.mipmap.ic_launcher)//展位图
                .error(R.mipmap.ic_launcher)//错误图
                .circleCrop()//圆形图片
                .centerCrop()//剧中
                .into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * viewholder优化列表的其中方案
     */
    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView tv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
