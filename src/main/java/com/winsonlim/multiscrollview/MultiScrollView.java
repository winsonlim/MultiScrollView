package com.winsonlim.multiscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.ScrollView;

/**
 * Created by winsonlim on 14/4/14.
 */
public class MultiScrollView extends ScrollView {
	private float downY, upY;

	private View childView;

	public MultiScrollView(Context context) {
		super(context);
	}

	public MultiScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MultiScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void init(final View childView) {
		if (childView == null) return;

		this.setOnTouchListener(new View.OnTouchListener() {
			float downY;
			float upY;

			@Override
			public boolean onTouch(View view, MotionEvent event) {
				ViewParent viewParent = view.getParent();
				if (viewParent == null) return true;

				// Disallow the touch request for parent scroll on touch of child view
				viewParent.requestDisallowInterceptTouchEvent(true);

				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN: {
						downY = event.getY();
						gi
						//   return true;
					}
					case MotionEvent.ACTION_MOVE: {
						upY = event.getY();

						float deltaY = downY - upY;

						// detect down scrolling
						if (deltaY > 0) {
							if (childView.getMeasuredHeight() <= my.com.maxis.hotlink.Utils.MultiScrollView.this.getScrollY() + my.com.maxis.hotlink.Utils.MultiScrollView.this.getHeight()) {
								//bottom reached!
								viewParent.requestDisallowInterceptTouchEvent(false);

								return true;
							}
						}

						// detect up scrolling
						if (deltaY < 0) {
							if (my.com.maxis.hotlink.Utils.MultiScrollView.this.getScrollY() == 0) {
								//top reached!
								viewParent.requestDisallowInterceptTouchEvent(false);

								return true;
							}
						}
					}
				}
				return false;
			}
		});
	}
}
