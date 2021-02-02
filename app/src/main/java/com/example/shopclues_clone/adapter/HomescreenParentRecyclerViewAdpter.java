package com.example.shopclues_clone.adapter;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopclues_clone.R;
import com.example.shopclues_clone.models.HomescreenModel;

import java.util.List;

public class HomescreenParentRecyclerViewAdpter extends RecyclerView.Adapter<HomescreenParentRecyclerViewAdpter.HomescreenViewholder> {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<HomescreenModel> list_parent;
    private String type;

    public HomescreenParentRecyclerViewAdpter(List<HomescreenModel> list_parent) {
        this.list_parent = list_parent;

    }

    @NonNull
    @Override
    public HomescreenViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(
                        R.layout.homescreen_parent_layout,
                        parent, false);
        return new HomescreenViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomescreenViewholder holder, int position) {
        HomescreenModel parentItem = list_parent.get(position);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.ChildRecyclerView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.ChildRecyclerView.getContext(),2);

        linearLayoutManager.setInitialPrefetchItemCount(parentItem.getChildernModels().size());

        HomescreenChildernAdapter homescreenChildernAdapter = new HomescreenChildernAdapter(parentItem.getChildernModels(),parentItem.getType());
        holder.ChildRecyclerView.setAdapter(homescreenChildernAdapter);
        if (HomescreenModel.BEST_BUY.equals(parentItem.getType())||HomescreenModel.GADGET_STORE.equals(parentItem.getType())){
            holder.ChildRecyclerView.setLayoutManager(gridLayoutManager);
            holder.setTitle(parentItem.getTitle());

        }else {
            holder.ChildRecyclerView.setLayoutManager(linearLayoutManager);

        }

        holder.ChildRecyclerView.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return list_parent.size();
    }

    public class HomescreenViewholder extends RecyclerView.ViewHolder{
        public TextView ParentItemTitle;
        public RecyclerView ChildRecyclerView;
        public HomescreenViewholder(@NonNull View itemView) {
            super(itemView);
            ChildRecyclerView = itemView.findViewById(R.id.child_recyclerview);
            ParentItemTitle = itemView.findViewById(R.id.title);
        }
        public void setTitle(String title){
            ParentItemTitle.setVisibility(View.VISIBLE);
            ParentItemTitle.setText(title);
        }
    }
}
