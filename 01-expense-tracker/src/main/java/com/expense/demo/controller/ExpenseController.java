package com.expense.demo.controller;

import com.expense.demo.dtos.ExpenseRequest;
import com.expense.demo.dtos.ExpenseResponse;
import com.expense.demo.entity.Expense;
import com.expense.demo.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/expenses")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @PostMapping("/create")
    public ResponseEntity<ExpenseResponse> createExpense(@RequestBody ExpenseRequest expenseRequest) {
        System.out.println("expreq " + expenseRequest);
        ExpenseResponse response = expenseService.createExpense(expenseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/id")
    public ResponseEntity<ExpenseResponse> getExpenseById(@RequestParam int id) {
        ExpenseResponse response = expenseService.getResponseById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
