package com.example.dulzh.scrollview_listview;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnScrollViewScrollListener {
    MyListView listview_gaoshou_home;
    LinearLayout mLin_other;
    ImageView cat_avatar;
    RelativeLayout mRl_top;
    MyScrollView scrollview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        scrollview.setOnScrollViewScrollListener(this);
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            String str = "dlzh" + i;
            arrayList.add(str);
        }
        MyAdapter adapter = new MyAdapter(this, arrayList);
        setListViewHeightBasedOnChildren(listview_gaoshou_home);
        listview_gaoshou_home.setAdapter(adapter);


    }

    public void initView() {
        mLin_other = (LinearLayout) findViewById(R.id.other);
        cat_avatar = (ImageView) findViewById(R.id.cat_avatar);
        mRl_top = (RelativeLayout) findViewById(R.id.hightup_rl_top);
        scrollview = (MyScrollView) findViewById(R.id.gaoshou_scroll);
        listview_gaoshou_home = (MyListView) findViewById(R.id.listview_gaoshou_home);

    }

    public static void setListViewHeightBasedOnChildren(ListView listviewGaoshouHome) {
        MyAdapter listAdapter = (MyAdapter) listviewGaoshouHome.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listviewGaoshouHome);
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(listviewGaoshouHome.getWidth(), View.MeasureSpec.AT_MOST);
            listItem.measure(desiredWidth, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listviewGaoshouHome.getLayoutParams();
        params.height = totalHeight + (listviewGaoshouHome.getDividerHeight() * (listAdapter.getCount() - 1)) + 10;
        listviewGaoshouHome.setLayoutParams(params);
    }


    FrameLayout.LayoutParams params = null;
    RelativeLayout.LayoutParams params1 = null;

    public int dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public void onScrollChanged(int x, int y, int oldx, int oldy) {

        int currHight = dp2px(90) - y;
        float curral = (float) (currHight - dp2px(50)) / (float) dp2px(40);
        Log.d("MainActivity", "-----currHight-------->" + currHight + "-----curral-------->" + curral);
        if (currHight > dp2px(50)) {
            params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, currHight);
            params1 = new RelativeLayout.LayoutParams(currHight - 20, currHight - 20);
            mRl_top.setLayoutParams(params);
            mLin_other.setAlpha(curral);
            cat_avatar.setLayoutParams(params1);
            mLin_other.setVisibility(View.VISIBLE);
        } else {
            params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, dp2px(50));
            params1 = new RelativeLayout.LayoutParams(dp2px(50) - 20, dp2px(50) - 20);
            mLin_other.setVisibility(View.GONE);
            mRl_top.setLayoutParams(params);
        }

    }

    public static class MyAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<String> arrayList;

        public MyAdapter(Context context, ArrayList<String> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }


        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = null;
            ViewHolder viewHolder = null;
            if (convertView == null) {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.layout_listview_content, null);
                viewHolder = new ViewHolder();
                viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_content);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.textView.setText(arrayList.get(position));
            return convertView;
        }

        static class ViewHolder {
            TextView textView;
        }
    }


}
