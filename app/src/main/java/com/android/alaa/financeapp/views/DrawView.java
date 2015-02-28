package com.android.alaa.financeapp.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.android.alaa.financeapp.controllers.InputController;
import com.android.alaa.financeapp.controllers.QueryController;
import com.android.alaa.financeapp.models.Expense;

import java.util.List;

/**
 * Created by Ahmed on 2/22/2015.
 */
public class DrawView extends View {

    QueryController qController;
    InputController iController;

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);

        qController = new QueryController(context);
        iController = new InputController(context);
    }

    public void onDraw(Canvas canvas) {
//        List<Expense> expenses = qController.getExpenses();

        Paint paint = new Paint();
        paint.setColor(Color.RED);

        canvas.drawLine(0, 0, 100, 100, paint);
    }
}
