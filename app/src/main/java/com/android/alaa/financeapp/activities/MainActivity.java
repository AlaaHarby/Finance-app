package com.android.alaa.financeapp.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.alaa.financeapp.R;
import com.android.alaa.financeapp.controllers.InputController;
import com.android.alaa.financeapp.controllers.QueryController;
import com.android.alaa.financeapp.models.Budget;

import java.util.List;

public class MainActivity extends ActionBarActivity {

    InputController iController;
    QueryController qController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        iController = new InputController(this);
        qController = new QueryController(this);
        /*Budget bIn = new Budget(200.0, "food");
        iController.insertNewBudget(bIn);

        List<Budget> bOut = qController.getBudgets();
        String out = "";
        for(Budget b: bOut){
            out += b.toString()+"\n";
        }

        TextView t = (TextView) findViewById(R.id.id1);
        t.setText(out);*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
