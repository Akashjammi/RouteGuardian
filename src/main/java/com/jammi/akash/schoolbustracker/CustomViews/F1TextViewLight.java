package com.jammi.akash.schoolbustracker.CustomViews;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.jammi.akash.schoolbustracker.SupportFiles.Constants;


/**
 * Created by Azr on 22/2/17.
 */
public class F1TextViewLight extends TextView{

    public F1TextViewLight(Context context) {
        super(context);
        init();
    }

    public F1TextViewLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public F1TextViewLight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setTypeface(Constants.getFont1Light(getContext()));
    }
}
