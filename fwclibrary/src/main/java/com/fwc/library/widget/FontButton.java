package com.fwc.library.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * 圆体的FontButton
 * @author plane
 *
 */
@SuppressLint("AppCompatCustomView")
public class FontButton extends Button {

	
	public FontButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public FontButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public FontButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	void init(){
		this.setTypeface(FontTextView.getYuanti(getContext()));
	}
}
