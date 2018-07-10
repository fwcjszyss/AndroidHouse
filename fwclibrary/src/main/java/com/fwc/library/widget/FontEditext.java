package com.fwc.library.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * 圆体的EditText
 * @author plane
 *
 */
@SuppressLint("AppCompatCustomView")
public class FontEditext extends EditText {

	public FontEditext(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
		
	}

	public FontEditext(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public FontEditext(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	void init(){
		this.setTypeface(FontTextView.getYuanti(getContext()));
	}
}
