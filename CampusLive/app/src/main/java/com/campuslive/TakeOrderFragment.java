package com.campuslive;

import android.annotation.SuppressLint;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TakeOrderFragment extends Fragment {

    private OkHttpClient client = new OkHttpClient();

    private Handler handler;

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_take_order, container, false);

        getOrderList();

        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                try {
                    addOrderList((String) msg.obj);
                    addAllButtonOnClickListener((String) msg.obj);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };


//        return inflater.inflate(R.layout.card_order_list, container, false);
        return view;
    }

    /**
     * @param
     * @return void
     * @description 获取所有未接单订单
     * @author 董金达
     * @date 21:48 2020/5/29
     **/
    private void getOrderList() {

        String url = "http://littleeyes.cn:8080/get-all-missed-order";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String orders = response.body().string();
                    System.out.println("orders" + orders);
                    Message msg = handler.obtainMessage();
                    msg.obj = orders;
                    handler.sendMessage(msg);
                }
            }
        });
    }


    /**
     * @param orders
     * @return void
     * @description 动态生成组件
     * @author 董金达
     * @date 21:56 2020/5/29
     **/
    private void addOrderList(String orders) throws JSONException {

        JSONObject getAllJson = new JSONObject(orders);
        JSONArray orderJsonArray = getAllJson.getJSONArray("data");

        final LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        // 需要被添加的布局
        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.order_list_linear_layout);

        for (int i = 0; i < orderJsonArray.length(); i++) {

            LinearLayout tempLinearLayout = (LinearLayout) layoutInflater.inflate(R.layout.card_order_list, null).findViewById(R.id.add_one_order);
            linearLayout.addView(tempLinearLayout);
        }

        for (int i = 0; i < linearLayout.getChildCount(); i++) {

            if (linearLayout.getChildAt(i) instanceof LinearLayout) {
                LinearLayout oneOrder = (LinearLayout) linearLayout.getChildAt(i);

                for (int j = 0; j < oneOrder.getChildCount(); j++) {

                    if (oneOrder.getChildAt(j) instanceof CardView) {
                        CardView oneOrderCard = (CardView) oneOrder.getChildAt(j);
                        JSONObject oneOrderJson = orderJsonArray.getJSONObject(i);

                        for (int k = 0; k < oneOrderCard.getChildCount(); k++) {

                            if (oneOrderCard.getChildAt(k) instanceof LinearLayout) {
                                LinearLayout insideOrderCard = (LinearLayout) oneOrderCard.getChildAt(k);

                                TextView orderTime = (TextView) insideOrderCard.getChildAt(0);
                                orderTime.setText(oneOrderJson.getString("orderTime"));
                                System.out.println("orderTime = " + oneOrderJson.getString("orderTime"));

                                TextView orderAddress = (TextView) insideOrderCard.getChildAt(1);
                                orderAddress.setText(oneOrderJson.getString("orderAddress"));
                                System.out.println("orderAddress = " + oneOrderJson.getString("orderAddress"));

                                TextView orderContent = (TextView) insideOrderCard.getChildAt(2);
                                orderContent.setText(oneOrderJson.getString("orderContent"));
                                System.out.println("orderContent = " + oneOrderJson.getString("orderContent"));

                                TextView orderMoney = (TextView) insideOrderCard.getChildAt(3);
                                orderMoney.setText(oneOrderJson.getString("orderMoney"));
                                System.out.println("orderMoney = " + oneOrderJson.getString("orderMoney"));
                            }// if
                        }// for
                    }// if
                }// for
            }// if
        }// for
    }

    /**
     * @param
     * @return void
     * @description 为动态生成 card 中的 button 添加 onClickListener
     * @author 董金达
     * @date 22:14 2020/5/29
     **/
    private void addAllButtonOnClickListener(String orders) throws JSONException {

        final String url = "http://littleeyes.cn:8080/change-order-state";

        JSONObject orderJson = new JSONObject(orders);
        JSONArray dataJsonArray = orderJson.getJSONArray("data");

        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.order_list_linear_layout);

        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            System.out.println("------------------------------------------i = " + i);

            if (linearLayout.getChildAt(i) instanceof LinearLayout) {
                final LinearLayout oneOrder = (LinearLayout) linearLayout.getChildAt(i);

                for (int j = 0; j < oneOrder.getChildCount(); j++) {
                    System.out.println("--------------------------------------j = " + j);

                    if (oneOrder.getChildAt(j) instanceof CardView) {
                        CardView oneOrderCard = (CardView) oneOrder.getChildAt(j);
                        final JSONObject oneOrderJson = dataJsonArray.getJSONObject(i);

                        for (int k = 0; k < oneOrderCard.getChildCount(); k++) {
                            System.out.println("-----------------------------k = " + k);

                            if (oneOrderCard.getChildAt(k) instanceof LinearLayout) {

                                LinearLayout insideOrderCard = (LinearLayout) oneOrderCard.getChildAt(k);

                                Button button = (Button) insideOrderCard.getChildAt(4);

                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        JSONObject dataJson = new JSONObject();
                                        JSONObject postJson = new JSONObject();

                                        try {
                                            dataJson.put("orderID", oneOrderJson.getString("orderID"));
                                            // 订单状态： 1 已下单，未付款 / 2 已付款，未接单 -> 3 已接单，未完成
                                            dataJson.put("orderState", "3");

                                            postJson.put("data", dataJson);
                                            postJson.put("check", "0");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        Request request = new Request.Builder()
                                                .url(url)
                                                .post(RequestBody.create(Objects.requireNonNull(MediaType.parse("application/json; charset=utf-8")), postJson.toString()))
                                                .build();

                                        client.newCall(request).enqueue(new Callback() {
                                            @Override
                                            public void onFailure(Call call, IOException e) {
                                                Looper.prepare();
                                                Toast.makeText(getActivity(), "修改订单状态失败", Toast.LENGTH_LONG).show();
                                                Looper.loop();
                                                e.printStackTrace();
                                            }
                                            @Override
                                            public void onResponse(Call call, Response response) throws IOException {
                                                Looper.prepare();
                                                Toast.makeText(getActivity(), "修改订单状态成功", Toast.LENGTH_LONG).show();
                                                Looper.loop();
                                                onResume();
                                            }
                                        });
                                    }
                                });
                            }// if
                        }// for
                    }// if
                }// for
            }// if
        }// for
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
