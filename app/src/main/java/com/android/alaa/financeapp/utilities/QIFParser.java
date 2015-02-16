package com.android.alaa.financeapp.utilities;

import com.android.alaa.financeapp.models.Expense;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ahmed on 2/15/2015.
 */
public class QIFParser {

    private String fileName;

    public QIFParser(String fileName) {
        this.fileName = fileName;
    }

    public List<Expense> parseExpenses() {
        List<Expense> expenses = new LinkedList<Expense>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(fileName));

            String line = null;
            parseType(bf);
            while(true) {
                Expense expense = parseExpense(bf);
                if (expense == null) break;
                if (expense.getAmount() >= 0) continue;

                expenses.add(expense);
            }

            return expenses;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void parseType(BufferedReader bf) throws IOException {
        String line = bf.readLine();
        //if (line.matches("\!True"));
    }

    private Expense parseExpense(BufferedReader bf) throws Exception {
        String line = bf.readLine();
        if (line == null) {//File is done.
            return null;
        }

        Expense expense = new Expense();
        boolean done = false;

        do {
            switch (line.charAt(0)) {
                case 'D':
                    SimpleDateFormat dateParser = new SimpleDateFormat("M/d/y");
                    expense.setDate(dateParser.parse(line.substring(1)));
                    break;
                case 'T':
                    expense.setAmount(NumberFormat.getInstance().parse(line.substring(1)).doubleValue());
                    break;
                case '^':
                    done = true;
                    break;
                case 'C':
                    break;
                case 'P':
                    expense.setNote(line.substring(1));
                    break;
                case 'M':
                    break;
                default:
                    throw new Exception("Unexpected identifier parsing the QIF file.");
            }
        } while(!done && (line = bf.readLine()) != null);

        return expense;
    }

    public static void main(String[] args) {
        QIFParser parser = new QIFParser("C:\\Users\\Ahmed\\Downloads\\HistoryDownload.qif");
        List<Expense> expenses = parser.parseExpenses();
        for(Expense expense : expenses) {
            System.out.println(expense);
        }
    }
}
