package com.example.bahador.costumlistview;

import android.graphics.drawable.Drawable;

/**
 * Created by bahador on 12/9/17.
 */

public class Item {
    boolean checked;
    Drawable ItemDrawable;
    String ItemString;

    Item(Drawable drawable, String t, boolean b) {
        ItemDrawable = drawable;
        ItemString = t;
        checked = b;
    }

    public boolean isChecked() {
        return checked;
    }
}
