package org.example.project3.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.project3.ApiResponse.ApiResponse;
import org.example.project3.Model.Account;
import org.example.project3.Model.User;
import org.example.project3.Service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/bank-system/account")
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @PostMapping("/create-account")
    public ResponseEntity createAccount(@AuthenticationPrincipal User user, @RequestBody @Valid Account account){

        accountService.createAccount(user.getId(), account);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Account created successfully!"));
    }

    @PutMapping("/active-account/{accountId}")
    public ResponseEntity activeAccount(@AuthenticationPrincipal User user, @PathVariable Integer accountId) {

        accountService.activeAccount(user.getId(), accountId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Account activated successfully!"));
    }

    @GetMapping("/view/account-details")
    public ResponseEntity viewAccountDetails(@AuthenticationPrincipal User user){

        List<Account> accounts = accountService.viewAccountDetails(user.getId());
        return ResponseEntity.status(200).body(accounts);
    }

    @GetMapping("/view/users-accounts")
    public ResponseEntity listUsersAccounts(){
        List<Account> accounts = accountService.listUsersAccounts();
        return ResponseEntity.status(200).body(accounts);
    }

    @DeleteMapping("/delete/account/{accountId}")
    public ResponseEntity deleteAccount(@AuthenticationPrincipal User user, @PathVariable Integer accountId){
        accountService.deleteAccount(user.getId(), accountId);

        return ResponseEntity.status(200).body(new ApiResponse("Account deleted successfully!"));

    }

    @PutMapping("/deposit/{accountId}/{amount}")
    public ResponseEntity depositMoney(
            @AuthenticationPrincipal User user,
            @PathVariable Integer accountId,
            @PathVariable Double amount) {

        accountService.depositMoney(user.getId(), accountId, amount);
        return ResponseEntity.ok(new ApiResponse("Deposit done successful!"));
    }

    @PutMapping("/withdraw/{accountId}/{amount}")
    public ResponseEntity withdrawMoney(@PathVariable Integer accountId,
                                        @PathVariable Double amount,
                                        @AuthenticationPrincipal User user) {

        accountService.withdrawMoney(accountId, amount, user.getId());

        return ResponseEntity.ok(new ApiResponse("Withdrawal done successfully!"));
    }

    @PutMapping("/transfer/{fromAccountId}/{toAccountId}/{amount}")
    public ResponseEntity transferFunds(@PathVariable Integer fromAccountId,
                                        @PathVariable Integer toAccountId,
                                        @PathVariable Double amount,
                                        @AuthenticationPrincipal User user) {

        accountService.transferFunds(fromAccountId, toAccountId, amount, user.getId());

        return ResponseEntity.status(200).body(new ApiResponse("Transfer money operation done successfully!"));
    }

    @PutMapping("/block-account/{accountId}")
    public ResponseEntity blockAccount(@PathVariable Integer accountId, @AuthenticationPrincipal User user) {

        accountService.blockAccount(accountId, user.getId());

        return ResponseEntity.ok(new ApiResponse("Account blocked successfully!"));
    }



}
