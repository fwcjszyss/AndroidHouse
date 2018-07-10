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
public class FontBoldTextView extends TextView {
	private static Typeface yuanti;
	public static Typeface getYuanti(Context context) {
		if(yuanti == null){
//			yuanti = Typeface.createFromAsset(context.getResources().getAssets(), "Yuanti.ttf");
			yuanti = Typeface.createFromAsset(context.getResources().getAssets(), "fonts/Lantinghei_b.ttf");
		}
		return yuanti;
	}

	public FontBoldTextView(Context context) {
		this(context, null);
	}

	public FontBoldTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FontBoldTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setTypeface(getYuanti(context));
	}
}
