package com.bankease.controller;

import com.bankease.dto.ApiResponse;
import com.bankease.dto.TransferRequest;
import com.bankease.model.Transaction;
import com.bankease.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService txService;

    public TransactionController(TransactionService txService) {
        this.txService = txService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestParam String accountNumber, @RequestParam Double amount) {
        Transaction tx = txService.deposit(accountNumber, amount, "Deposit");
        return ResponseEntity.ok(new ApiResponse(true, "Deposit successful", tx));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestParam String accountNumber, @RequestParam Double amount) {
        Transaction tx = txService.withdraw(accountNumber, amount, "Withdraw");
        return ResponseEntity.ok(new ApiResponse(true, "Withdraw successful", tx));
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@Valid @RequestBody TransferRequest req) {
        Transaction tx = txService.transfer(req.getFromAccount(), req.getToAccount(), req.getAmount(), "Transfer");
        return ResponseEntity.ok(new ApiResponse(true, "Transfer successful", tx));
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<?> history(@PathVariable Long accountId) {
        List<Transaction> history = txService.getByAccountId(accountId);
        return ResponseEntity.ok(new ApiResponse(true, "Transaction history", history));
    }
}



