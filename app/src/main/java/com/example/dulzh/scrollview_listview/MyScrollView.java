package com.example.dulzh.scrollview_listview;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
	private OnScrollViewScrollListener scrollViewScrollListener;
	public boolean isUserTouch=false;
	public boolean isClick=true;

	public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		if (scrollViewScrollListener!=null) {
			scrollViewScrollListener.onScrollChanged(l, t, oldl, oldt);
		}
		super.onScrollChanged(l, t, oldl, oldt);
	}

	public void setOnScrollViewScrollListener(OnScrollViewScrollListener onScrollViewScrollListener){
		this.scrollViewScrollListener=onScrollViewScrollListener;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
		return super.onTouchEvent(ev);
	}
}