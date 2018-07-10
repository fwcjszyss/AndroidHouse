package com.fwc.library.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 圆体的TextView
 * @author plane
 *
 */
@SuppressLint("AppCompatCustomView")
public class FontTextView extends TextView {
	private static Typeface yuanti;
	public static Typeface getYuanti(Context context) {
		if(yuanti == null){
//			yuanti = Typeface.createFromAsset(context.getResources().getAssets(), "Yuanti.ttf");
			yuanti = Typeface.createFromAsset(context.getResources().getAssets(), "fonts/Lantinghei_s.ttf");
		}
		return yuanti;
	}

	public FontTextView(Context context) {
		this(context, null);
	}

	public FontTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FontTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setTypeface(getYuanti(context));
	}
}
