package com.android.alaa.financeapp.models;

/**
 * Created by Alaa on 1/13/2015.
 */
public class DataStore {

    Comment[] comments;
    Budget[] budgets;
    Expense[] expenses;
    Reminder[] reminders;
    Income[] incomes;

    public Comment[] getComments() {
        return comments;
    }

    public void setComments(Comment[] comments) {
        this.comments = comments;
    }

    public Budget[] getBudgets() {
        return budgets;
    }

    public void setBudgets(Budget[] budgets) {
        this.budgets = budgets;
    }

    public Expense[] getExpenses() {
        return expenses;
    }

    public void setExpenses(Expense[] expenses) {
        this.expenses = expenses;
    }

    public Reminder[] getReminders() {
        return reminders;
    }

    public void setReminders(Reminder[] reminders) {
        this.reminders = reminders;
    }

    public Income[] getIncomes() {
        return incomes;
    }

    public void setIncomes(Income[] incomes) {
        this.incomes = incomes;
    }
}
