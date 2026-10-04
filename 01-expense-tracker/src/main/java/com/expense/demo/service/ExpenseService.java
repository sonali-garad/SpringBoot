package com.expense.demo.service;

import com.expense.demo.dtos.ExpenseRequest;
import com.expense.demo.dtos.ExpenseResponse;
import com.expense.demo.entity.Expense;
import com.expense.demo.exception.ExpenseNotFoundException;
import com.expense.demo.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    public ExpenseResponse createExpense(ExpenseRequest expenseRequest) {
        Expense expense = new Expense();
        expense.setAmount(expenseRequest.getAmount());
        expense.setCategory(expenseRequest.getCategory());
        expense.setDescription(expenseRequest.getDescription());
        expense.setTitle(expenseRequest.getTitle());
        expense.setDate(expenseRequest.getDate());
        // we could do directly repo.save - but in that case we couldn't access the id field as it's persistence
        Expense savedExpense = expenseRepository.save(expense);

        ExpenseResponse expenseResponse = new ExpenseResponse();
        expenseResponse.setAmount(savedExpense.getAmount());
        expenseResponse.setCategory(savedExpense.getCategory());
        expenseResponse.setDate(savedExpense.getDate());
        expenseResponse.setDescription(savedExpense.getDescription());
        expenseResponse.setId(savedExpense.getId());

        return expenseResponse;
    }

    public ExpenseResponse getResponseById(int id) {

  Expense expense = expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Record not found for ID: "+id));

   ExpenseResponse expenseResponse = new ExpenseResponse();
   expenseResponse.setAmount(expense.getAmount());
   expenseResponse.setDate(expense.getDate());
   expenseResponse.setDescription(expense.getDescription());
   expenseResponse.setTitle(expense.getTitle());
   expenseResponse.setCategory(expense.getCategory());
   expenseResponse.setId(expense.getId());

   return expenseResponse;
    }

    public ExpenseResponse updateExpense (int id, ExpenseRequest expense)
    {
        Expense existingResponse = expenseRepository.findById(id).orElseThrow(()-> new ExpenseNotFoundException("Record not found for this ID "+id));

        ExpenseResponse expenseResponseEntity = new ExpenseResponse();
        expenseResponseEntity.setCategory(expense.getCategory());
        expenseResponseEntity.setDate(expense.getDate());
        expenseResponseEntity.setDescription(expense.getDescription());
        expenseResponseEntity.setAmount(expense.getAmount());
        expenseResponseEntity.setTitle(expense.getTitle());
        expenseResponseEntity.setId(id);

        existingResponse.setDate(expense.getDate());
        existingResponse.setAmount(expense.getAmount());
        existingResponse.setTitle(expense.getTitle());
        existingResponse.setDescription(expense.getDescription());
        existingResponse.setCategory(expense.getCategory());
        expenseRepository.save(existingResponse);

        return expenseResponseEntity;
    }

    public String deleteById (int id )
    {
       Expense expense =  expenseRepository.findById(id).orElseThrow(()-> new ExpenseNotFoundException("Id not found "+id));
       expenseRepository.delete(expense);
        return "Record has been deleted for ID "+id;

    }


}
