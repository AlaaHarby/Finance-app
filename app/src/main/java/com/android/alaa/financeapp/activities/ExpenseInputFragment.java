package com.android.alaa.financeapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.alaa.financeapp.R;
import com.android.alaa.financeapp.controllers.InputController;
import com.android.alaa.financeapp.models.Expense;

public class ExpenseInputFragment extends Fragment {
    InputController iController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_expense_input, container, false);

        iController = new InputController(getActivity());

        Button submit = (Button) view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    double amount = Double.parseDouble(((TextView) view.findViewById(R.id.amount_value)).getText().toString());
                    String category = ((TextView) view.findViewById(R.id.category_value)).getText().toString();
                    String date = ((EditText) view.findViewById(R.id.date_value)).getText().toString();
                    Expense expense = new Expense(amount, date, category, "", "", "");

                    iController.insertNewExpense(expense);

                    Toast.makeText(getActivity().getApplicationContext(), R.string.expense_success, Toast.LENGTH_SHORT).show();

                    // Clear all the texts.
                    ((TextView) view.findViewById(R.id.amount_value)).setText("");
                    ((EditText) view.findViewById(R.id.category_value)).setText("");
                    ((TextView) view.findViewById(R.id.date_value)).setText("");
                } catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), R.string.validation_msg, Toast.LENGTH_LONG).show();
                }

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
