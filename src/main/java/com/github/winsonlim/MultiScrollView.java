package com.github.winsonlim;

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

	/**
	 * Initialises ScrollView's content
	 *
	 * @param childView The immediate (single) child Layout/View of this ScrollView
	 */
	public void init(View childView) throws NullPointerException {
		if (childView == null) {
			throw new NullPointerException("Child View cannot be NULL");
		}

		this.childView = childView;

		overrideChildView();
	}

	private void overrideChildView() {

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

						// do not consume MotionEvent
						// return true;
					}
					case MotionEvent.ACTION_MOVE: {
						upY = event.getY();

						float deltaY = downY - upY;

						// detect down scrolling
						if (deltaY > 0) {
							if (childView.getMeasuredHeight() <= MultiScrollView.this.getScrollY() + MultiScrollView.this.getHeight()) {
								//bottom reached!
								viewParent.requestDisallowInterceptTouchEvent(false);

								return true;
							}
						}

						// detect up scrolling
						if (deltaY < 0) {
							if (MultiScrollView.this.getScrollY() == 0) {
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
