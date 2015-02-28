package com.android.alaa.financeapp.activities;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.alaa.financeapp.R;
import com.android.alaa.financeapp.controllers.InputController;
import com.android.alaa.financeapp.controllers.QueryController;
import com.android.alaa.financeapp.models.Category;
import com.android.alaa.financeapp.models.Expense;
import com.android.alaa.financeapp.viewAdapters.QueryExpandAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alaa on 2/22/2015.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ExpenseEditFragmentDialog extends DialogFragment {

    InputController iController;
    QueryController qController;
    Expense mExpense;
    QueryExpandAdapter mAdapter;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        iController = new InputController(getActivity());
        qController = new QueryController(getActivity());

        final View dialogView = getActivity().getLayoutInflater().inflate(R.layout.activity_expense_input, null);

        ((Button) dialogView.findViewById(R.id.parse_qif)).setVisibility(View.GONE);
        ((Button) dialogView.findViewById(R.id.submit)).setVisibility(View.GONE);

        final EditText amount = (EditText) dialogView.findViewById(R.id.amount_value);
        final DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.date_picker);
        final EditText payee = (EditText) dialogView.findViewById(R.id.payee_value);
        final EditText method = (EditText) dialogView.findViewById(R.id.payment_value);
        final EditText note = (EditText) dialogView.findViewById(R.id.description_value);


        final Spinner spinner = (Spinner) dialogView.findViewById(R.id.category_value);
        List<Category> categories = qController.getCategories();
        List<String> categoriesName = new ArrayList<String>();
        for (int i = 0; i < categories.size(); i++)
            categoriesName.add(categories.get(i).getName());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, categoriesName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        amount.setText(mExpense.getAmount() + "");
        payee.setText(mExpense.getPayee());
        method.setText(mExpense.getPayMethod());
        note.setText(mExpense.getNote());
        datePicker.getCalendarView().setDate(mExpense.getDate());
        spinner.setSelection(categoriesName.indexOf(mExpense.getCategory()));

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Expense expense = new Expense(mExpense.getID(), Double.parseDouble(amount.getText().toString()),
                                        datePicker.getCalendarView().getDate(), spinner.getSelectedItem().toString(),
                                        payee.getText().toString(), note.getText().toString(), method.getText().toString());
                                iController.updateExpense(expense);
                                mAdapter.setExpense(expense);
                                mAdapter.notifyDataSetChanged();
                            }
                        })
                .setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                            }
                        });
        AlertDialog dialog = builder.create();

        return dialog;
    }

    public void setExpense(Expense expense) {
        mExpense = expense;
    }

    public void setExpenseAdapter(QueryExpandAdapter adapter) {
        mAdapter = adapter;
    }

}
