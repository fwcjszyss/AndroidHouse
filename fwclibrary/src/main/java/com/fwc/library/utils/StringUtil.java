package com.fwc.library.utils;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/7/10.
 */

public class StringUtil {

    public static final String AT =  "@[a-zA-Z0-9_\\u4e00-\\u9fa5\\S]+ ";

    /**
     * 格式化@显示。。。
     * @param content
     * @return
     */
    public static SpannableString foramtAt(String content){

        Pattern pattern = Pattern.compile(StringUtil.AT);

        SpannableString result = new SpannableString(content);
        Matcher matcher = pattern.matcher(result);

        while (matcher.find()){

            int start = matcher.start();
            int end = matcher.end();
            result.setSpan(new ForegroundColorSpan(Color.parseColor("#555555")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return result;

    }

    public static boolean checkEmail(String email)
    {// 验证邮箱的正则表达式
//      String format = "\\p{Alpha}\\w{2,15}[@][a-z0-9]{3,}[.]\\p{Lower}{2,}"; ---正则有误...
        String format = "^([a-z0-9A-Z_]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        //p{Alpha}:内容是必选的，和字母字符[\p{Lower}\p{Upper}]等价。如：200896@163.com不是合法的。
        //w{2,15}: 2~15个[a-zA-Z_0-9]字符；w{}内容是必选的。 如：dyh@152.com是合法的。
        //[a-z0-9]{3,}：至少三个[a-z0-9]字符,[]内的是必选的；如：dyh200896@16.com是不合法的。
        //[.]:'.'号时必选的； 如：dyh200896@163com是不合法的。
        //p{Lower}{2,}小写字母，两个以上。如：dyh200896@163.c是不合法的。
        if (email.matches(format))
        {
            return true;// 邮箱名合法，返回true
        }
        else
        {
            return false;// 邮箱名不合法，返回false
        }
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
		/*
		移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		联通：130、131、132、152、155、156、176、185、186
		电信：133、153、180、189、（1349卫通）
		总结起来就是第一位必定为1，第二位必定为3或5或7或8，其他位置的可以为0-9
		*/
        String telRegex = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }


    /**
     * 验证用户名
     * @param uname
     * @return
     */
    public static boolean isUsername(String uname){

        String unameRegex = "^(?![0-9]*$)[a-zA-Z0-9_\\u4e00-\\u9fa5]{4,20}$";

        return uname.matches(unameRegex);

    }
}
