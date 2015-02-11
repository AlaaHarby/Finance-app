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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.alaa.financeapp.R;
import com.android.alaa.financeapp.controllers.InputController;
import com.android.alaa.financeapp.controllers.QueryController;
import com.android.alaa.financeapp.models.Category;
import com.android.alaa.financeapp.models.Expense;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpenseInputFragment extends Fragment {
    InputController iController;
    QueryController qController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_expense_input, container, false);

        iController = new InputController(getActivity());
        qController = new QueryController(getActivity());

        Spinner spinner = (Spinner) view.findViewById(R.id.category_value);
        List<Category> categories = qController.getCategories();
        String[] categoriesName = new String[categories.size()];
        for (int i = 0; i < categories.size(); i++)
            categoriesName[i] = categories.get(i).getName();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, categoriesName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button submit = (Button) view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    double amount = Double.parseDouble(((TextView) view.findViewById(R.id.amount_value)).getText().toString());
                    String category = ((Spinner) view.findViewById(R.id.category_value)).getSelectedItem().toString();
                    long date = ((DatePicker) view.findViewById(R.id.date_picker)).getCalendarView().getDate();
                    Expense expense = new Expense(amount, date, category, "", "", "");

                    iController.insertNewExpense(expense);

                    Toast.makeText(getActivity().getApplicationContext(), R.string.expense_success, Toast.LENGTH_SHORT).show();

                    // Clear all the texts.
                    ((TextView) view.findViewById(R.id.amount_value)).setText("");
                    //((EditText) view.findViewById(R.id.category_value)).setText("");
                    //((TextView) view.findViewById(R.id.date_value)).setText("");
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
