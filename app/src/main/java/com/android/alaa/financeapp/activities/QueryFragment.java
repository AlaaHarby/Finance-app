package com.android.alaa.financeapp.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.alaa.financeapp.R;
import com.android.alaa.financeapp.controllers.InputController;
import com.android.alaa.financeapp.controllers.QueryController;
import com.android.alaa.financeapp.models.Expense;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QueryFragment extends Fragment {
    QueryController qController;
    InputController iController;

    QueryExpandAdapter queryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_query, container, false);

        qController = new QueryController(getActivity());
        iController = new InputController(getActivity());

        List<Expense> expenses = qController.getExpenses();

        queryAdapter = new QueryExpandAdapter(getActivity(), iController, expenses);

        ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.query_list);
        listView.setAdapter(queryAdapter);

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
        public QueryAdapter(Context mContext) {
            super(mContext, 0, new ArrayList<Expense>());
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
            date.setText(new SimpleDateFormat("dd/MM/yy").format(new Date(expense.getDate())));

            // Return the completed view to render on screen
            return convertView;
        }
    }

    class QueryExpandAdapter extends BaseExpandableListAdapter {

        Context mContext;
        InputController mIController;
        List<Expense> mExpenses;

        public QueryExpandAdapter(Context mContext, InputController mIController, List<Expense> mExpenses) {
            this.mContext = mContext;
            this.mIController = mIController;
            this.mExpenses = mExpenses;
        }

        @Override
        public int getGroupCount() {
            return mExpenses.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 1;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return mExpenses.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return mExpenses.get(groupPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            Expense expense = (Expense) getGroup(groupPosition);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.query_row, parent, false);
            }

            // Lookup view for data population
            TextView category = (TextView) convertView.findViewById(R.id.query_row_category);
            TextView date = (TextView) convertView.findViewById(R.id.query_row_date);
            TextView amount = (TextView) convertView.findViewById(R.id.query_row_amount);

            // Populate the data into the template view using the data object
            category.setText(expense.getCategory());
            amount.setText(expense.getAmount() + "");
            date.setText(new SimpleDateFormat("dd/MM/yy").format(new Date(expense.getDate())));

            // Return the completed view to render on screen
            return convertView;
        }

        @Override
        public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            final Expense expense = (Expense) getChild(groupPosition, childPosition);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.query_child, parent, false);
            }

            TextView description = (TextView) convertView.findViewById(R.id.description_txt);
            TextView payee = (TextView) convertView.findViewById(R.id.payee_txt);
            TextView payment = (TextView) convertView.findViewById(R.id.payment_txt);

            ImageButton delete = (ImageButton) convertView.findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {


                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage(mContext.getString(R.string.delete_warning));
                    builder.setCancelable(false);
                    builder.setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    mExpenses.remove(groupPosition);
                                    mIController.removeExpense(expense);
                                    notifyDataSetChanged();
                                }
                            });
                    builder.setNegativeButton(android.R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            });

            description.setText(expense.getNote());
            payee.setText(expense.getPayee());
            payment.setText(expense.getPayMethod());

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }
}
