package com.android.alaa.financeapp.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.alaa.financeapp.R;
import com.android.alaa.financeapp.controllers.InputController;
import com.android.alaa.financeapp.models.Expense;

import java.util.ArrayList;

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
                double amount = Double.parseDouble(((TextView)view.findViewById(R.id.amount_value)).getText().toString());
                String category = ((TextView)view.findViewById(R.id.category_value)).getText().toString();
                String date = ((TextView)view.findViewById(R.id.date_value)).getText().toString();
                Expense expense = new Expense(amount, date, category, "", "", "");

                iController.insertNewExpense(expense);

                Toast.makeText(getActivity().getApplicationContext(), R.string.expense_success, Toast.LENGTH_SHORT).show();

                // Clear all the texts.
                ((TextView)view.findViewById(R.id.amount_value)).setText("");
                ((TextView)view.findViewById(R.id.category_value)).setText("");
                ((TextView)view.findViewById(R.id.date_value)).setText("");

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

    class QueryAdapter extends ArrayAdapter<Expense> {
        public QueryAdapter(Context context) {
            super(context, 0, new ArrayList<Expense>());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Expense expense = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.query_row, parent, false);
            }

            // Lookup view for data population
            TextView category = (TextView) convertView.findViewById(R.id.query_row_category);
            TextView date = (TextView) convertView.findViewById(R.id.query_row_date);
            TextView amount = (TextView) convertView.findViewById(R.id.query_row_amount);

            // Populate the data into the template view using the data object
            category.setText(expense.getCategory());
            amount.setText(expense.getAmount() + "");
            date.setText(expense.getDate());

            // Return the completed view to render on screen
            return convertView;
        }
    }
}
