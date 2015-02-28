package com.android.alaa.financeapp.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.alaa.financeapp.R;
import com.android.alaa.financeapp.controllers.InputController;
import com.android.alaa.financeapp.models.Income;

/**
 * Created by Alaa on 2/2/2015.
 */
public class IncomeInputFragment extends Fragment {
    InputController iController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_income, container, false);

        iController = new InputController(getActivity());

        Button submit = (Button) view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double amount = Double.parseDouble(((TextView) view.findViewById(R.id.amount_value)).getText().toString());
                String source = ((TextView) view.findViewById(R.id.src_value)).getText().toString();
                Income income = new Income(amount, source);

                iController.insertNewIncome(income);

                Toast.makeText(getActivity().getApplicationContext(), R.string.income_success, Toast.LENGTH_SHORT).show();

                // Clear all the texts.
                ((TextView) view.findViewById(R.id.amount_value)).setText("");
                ((TextView) view.findViewById(R.id.src_value)).setText("");

            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_query, menu);
        super.onCreateOptionsMenu(menu, inflater);
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
