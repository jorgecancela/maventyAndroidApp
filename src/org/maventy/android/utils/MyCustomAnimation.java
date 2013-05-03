package org.maventy.android.utils;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class MyCustomAnimation extends Animation {

    int targetHeight;
    View view;
    boolean down;

    public MyCustomAnimation(View view, int targetHeight, boolean down) {
	this.view = view;
	this.targetHeight = targetHeight;
	this.down = down;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
	int newHeight;
	if (down) {
	    newHeight = (int) (targetHeight * interpolatedTime);
	} else {
	    newHeight = (int) (targetHeight * (1 - interpolatedTime));
	}

	if (newHeight == 0) newHeight = 1;

	view.getLayoutParams().height = newHeight;
	view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth,
	    int parentHeight) {

	super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
	return true;
    }


    public static void expand(final View v) {
	v.measure(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
	final int targtetHeight = v.getMeasuredHeight();

	v.getLayoutParams().height = 0;
	v.setVisibility(View.VISIBLE);
	Animation a = new Animation()
	{
	    @Override
	    protected void applyTransformation(float interpolatedTime, Transformation t) {		            
		v.getLayoutParams().height = interpolatedTime == 1
		? LayoutParams.WRAP_CONTENT
			: (int)(targtetHeight * interpolatedTime);
		v.requestLayout();
	    }

	    @Override
	    public boolean willChangeBounds() {
		return true;
	    }
	};

	//a.setInterpolator(new AccelerateInterpolator(5.0f));
	// 1dp/ms
	a.setDuration((int)(targtetHeight / v.getContext().getResources().getDisplayMetrics().density));
	v.startAnimation(a);
    }

    public static void collapse(final View v) {
	final int initialHeight = v.getMeasuredHeight();

	Animation a = new Animation()
	{
	    @Override
	    protected void applyTransformation(float interpolatedTime, Transformation t) {
		if(interpolatedTime == 1){
		    v.setVisibility(View.GONE);
		}else{
		    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
		    v.requestLayout();
		}
	    }

	    @Override
	    public boolean willChangeBounds() {
		return true;
	    }
	};

	//
	//a.setInterpolator(new AccelerateInterpolator(1.5f));
	// 1dp/ms
	a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
	v.startAnimation(a);
    }
}