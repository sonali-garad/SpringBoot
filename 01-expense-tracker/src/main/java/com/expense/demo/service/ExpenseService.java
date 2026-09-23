package com.expense.demo.service;

import com.expense.demo.dtos.ExpenseRequest;
import com.expense.demo.dtos.ExpenseResponse;
import com.expense.demo.entity.Expense;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    public ExpenseResponse createExpense (ExpenseRequest expenseRequest)
    {
        Expense expense = new Expense();
        expense.setAmount(expenseRequest.getAmount());
        expense.setCategory(expenseRequest.getCategory());
        expense.setDescription(expenseRequest.getDescription());
        expense.setTitle(expenseRequest.getTitle());
        expense.setDate(expenseRequest.getDate());
        return null;
    }
}
