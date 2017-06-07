package com.example.emotionslearninggame.Level3;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatDrawableManager;
import android.widget.GridLayout;

import com.example.emotionslearninggame.R;

public class Tiles extends AppCompatButton {
    protected int row, column, frontImageId;
    protected boolean isFlip = false;
    protected boolean isPaired = false;
    protected Drawable front, back;

    public Tiles(Context context, int r, int c, int frontImageID) {
        super(context);

        row = r;
        column = c;
        frontImageId = frontImageID;

        front = AppCompatDrawableManager.get().getDrawable(context,frontImageID);
        back = AppCompatDrawableManager.get().getDrawable(context, R.drawable.button_question_mark);

        setBackground(back);

        GridLayout.LayoutParams tmpParams = new GridLayout.LayoutParams(GridLayout.spec(r), GridLayout.spec(c));
        tmpParams.width = (int)getResources().getDisplayMetrics().density * 110;
        tmpParams.height = (int)getResources().getDisplayMetrics().density * 110;
        setLayoutParams(tmpParams);
    }

    public void setPaired(boolean paired) {
        isPaired = paired;
    }

    public int getFrontImageId() {
        return frontImageId;
    }

    public void flipCard() {
        if (isPaired) return;

        if (isFlip) {
            setBackground(back);
            isFlip = false;
        }else {
            setBackground(front);
            isFlip = true;
        }
    }
}
