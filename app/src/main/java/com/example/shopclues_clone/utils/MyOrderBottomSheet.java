package com.example.shopclues_clone.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.shopclues_clone.R;
import com.example.shopclues_clone.models.OrderModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MyOrderBottomSheet extends BottomSheetDialogFragment {
    OrderModel orderModel ;

    public MyOrderBottomSheet(OrderModel orderModel) {
        this.orderModel = orderModel;
    }
    ImageView imageView;
    TextView title,address,phone,price;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.show_more_bottom_sheet, container, false);
        imageView = v.findViewById(R.id.imageView16);
        price = v.findViewById(R.id.textView14);
        title = v.findViewById(R.id.textView13);

       address = v.findViewById(R.id.textView15);
       phone = v.findViewById(R.id.textView16);
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Glide.with(imageView).load(orderModel.getCartItemModel().getImage()).into(imageView);
        price.setText(orderModel.getCartItemModel().getPrice());
        title.setText(orderModel.getCartItemModel().getTitle());
        phone.setText(orderModel.getAddressModel().getPhone_address());
        address.setText(orderModel.getAddressModel().getFlat() + " "+orderModel.getAddressModel().getHno()+ " "+orderModel.getAddressModel().getPin());


    }


}
