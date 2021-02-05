package com.example.shopclues_clone.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.bumptech.glide.Glide;
import com.example.shopclues_clone.R;
import com.example.shopclues_clone.interfaces.CancelOrder;
import com.example.shopclues_clone.models.OrderModel;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyOrderViewHolder> {
    List<OrderModel> list;
    private CancelOrder cancelOrder;

    public MyOrderAdapter(List<OrderModel> list,CancelOrder cancelOrder) {
        this.list = list;
        this.cancelOrder = cancelOrder;
    }

    @NonNull
    @Override
    public MyOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_layout,parent,false);

        return new MyOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderViewHolder holder, int position) {
        holder.SetData(list.get(position),position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyOrderViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView name,quantity,time,price,status,payment_type;
        private Button cancel_order;
        private ConstraintLayout constraintLayout;

        public MyOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            init(itemView);
        }

        private void init(View itemView) {
            image = itemView.findViewById(R.id.imageView10);
            price = itemView.findViewById(R.id.textView6);
            name = itemView.findViewById(R.id.textView7);
            time = itemView.findViewById(R.id.textView8);
            payment_type = itemView.findViewById(R.id.textView12);
            status = itemView.findViewById(R.id.textView9);
            cancel_order = itemView.findViewById(R.id.button15);
            constraintLayout = itemView.findViewById(R.id.orderConstaintLayout);
        }
        private void SetData(OrderModel orderModel,int pos){
            Glide.with(image).load(orderModel.getCartItemModel().getImage()).into(image);
            name.setText(orderModel.getCartItemModel().getTitle());
            price.setText(orderModel.getCartItemModel().getPrice());
            payment_type.setText(orderModel.getPayment_type());
            if (orderModel.getTime_of_order()!=null){
                time.setText(orderModel.getTime_of_order());
            }
            if (orderModel.getOrder_status()!=null&&orderModel.getOrder_status().equals(OrderModel.STATUS_CANCELED)){
                cancel_order.setText("Canceled");
                cancel_order.setBackground(cancel_order.getContext().getDrawable(R.color.gray));
                status.setText("Canceled");
            }else{
                cancel_order.setText("Cancel");
                status.setText("Preparing for dispatch");

                cancel_order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cancelOrder.cancelOrder(orderModel.getOrder_id(),pos);
                    }
                });
            }

        }
    }
}
