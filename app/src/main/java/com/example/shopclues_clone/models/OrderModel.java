package com.example.shopclues_clone.models;

public class OrderModel {
    public final static String STATUS_PENDING = "pending";
    public final static String STATUS_CANCELED = "canceled";
    public final static String STATUS_DELIVERED = "delivered";
    String order_id;
    String quantity;
    String order_status;
    String user;
    String payment_type;
    String time_of_order;
    AddressModel addressModel;
    CartItemModel cartItemModel;

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getTime_of_order() {
        return time_of_order;
    }

    public OrderModel(String order_id, String quantity, String user, String order_status, String payment_type, String time_of_order, AddressModel addressModel, CartItemModel cartItemModel) {
        this.order_id = order_id;
        this.quantity = quantity;
        this.user = user;
        this.addressModel = addressModel;
        this.cartItemModel = cartItemModel;
        this.order_status =order_status;
        this.payment_type = payment_type;
        this.time_of_order = time_of_order;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public String getOrder_status() {
        return order_status;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getUser() {
        return user;
    }

    public AddressModel getAddressModel() {
        return addressModel;
    }

    public CartItemModel getCartItemModel() {
        return cartItemModel;
    }
}
