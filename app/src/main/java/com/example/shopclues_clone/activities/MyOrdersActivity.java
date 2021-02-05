package com.example.shopclues_clone.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.shopclues_clone.R;
import com.example.shopclues_clone.adapter.MyOrderAdapter;
import com.example.shopclues_clone.interfaces.CancelOrder;
import com.example.shopclues_clone.models.CartItemModel;
import com.example.shopclues_clone.models.OrderModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class MyOrdersActivity extends AppCompatActivity implements CancelOrder {
    private RecyclerView recyclerView;
    private List<OrderModel> list = new ArrayList<>();
    private MyOrderAdapter adapter;
    private Button backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        init();
        setRecyclerViewAdapter();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File folder = new File(getFilesDir()+File.separator,"data");
                    if (!folder.exists()){
                        folder.mkdir();
                    }
                    File file = new File(folder,"orders.txt");
                    if (!file.exists()){
                        file.createNewFile();
                    }

                    FileInputStream inputStream = new FileInputStream(file);
                    InputStreamReader inputStreamReader =new InputStreamReader(inputStream);
                    int data = inputStreamReader.read();
                    StringBuffer stringBuffer = new StringBuffer();
                    while(data!=-1){
                        char c = (char)data;
                        stringBuffer.append(c);
                        data = inputStreamReader.read();
                    }
                    JSONArray jsonArray = new JSONArray(stringBuffer.toString());
                    Gson gson = new Gson();

                    // Converting json to object
                    // first parameter should be prpreocessed json
                    // and second should be mapping class
//                    Organisation organisation
//                            = gson.fromJson(OrganisationJson,
//                            Organisation.class);

                    OrderModel[] orders = gson.fromJson(stringBuffer.toString(),OrderModel[].class);
                    Log.d("Sidd",orders.length + " orders");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            list.addAll(Arrays.asList(orders));
                            adapter.notifyDataSetChanged();
                        }
                    });
                }catch ( Exception e){

                }
            }
        });
        thread.start();
    }

    private void setRecyclerViewAdapter() {
        adapter = new MyOrderAdapter(list,this);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void init() {
        recyclerView = findViewById(R.id.rv);
        backBtn = findViewById(R.id.button16);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            try{
                File folder = new File(getFilesDir()+File.separator,"data");
                if (!folder.exists()){
                    folder.mkdir();
                }
                File file = new File(folder,"orders.txt");
                if (!file.exists()){
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file, false);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String g = gson.toJson(list);
                outputStreamWriter.append(g);
                outputStreamWriter.close();
            }catch (Exception e){

            }
        }
    };
    @Override
    public void cancelOrder(String order_id,int position) {
       list.get(position).setOrder_status(OrderModel.STATUS_CANCELED);
       adapter.notifyItemChanged(position);
       Thread thread = new Thread(runnable1);
       thread.start();
    }
}