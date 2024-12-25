package org.example.project3.Service;

import lombok.RequiredArgsConstructor;
import org.example.project3.ApiResponse.ApiException;
import org.example.project3.Model.Account;
import org.example.project3.Model.User;
import org.example.project3.Repository.AccountRepository;
import org.example.project3.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public void createAccount(Integer customerId, Account account) {
        User user = userRepository.findUserById(customerId);
        if (user == null) {
            throw new ApiException("Customer not found!");
        }
        account.setCustomer(user.getCustomer());
        accountRepository.save(account);
    }

    public void activeAccount(Integer customerId, Integer accountId){

        Account account = accountRepository.findAccountById(accountId);

        User user = userRepository.findUserById(customerId);

        if (user == null) {
            throw new ApiException("Customer not found!");
        }

        if (account== null) {
            throw new ApiException("Account not found to active it!");
        }
        account.setIsActive(true);
        accountRepository.save(account);

    }

    //by customer himself
    public List<Account> viewAccountDetails(Integer customerId){

        User user = userRepository.findUserById(customerId);
        if(user==null)
            throw new ApiException("Customer not found!");

        return accountRepository.findAccountByCustomer(user.getCustomer());

    }


    //by admin
    public List<Account> listUsersAccounts(){
        return accountRepository.findAll();
    }

    public void deleteAccount(Integer accountId, Integer customerId){

        User user =userRepository.findUserById(customerId);
        if(user==null)
            throw new ApiException("Customer not found!");

        Account account = accountRepository.findAccountById(accountId);
        if(account==null)
            throw new ApiException("Account not found!");

        accountRepository.delete(account);

    }


    public void depositMoney( Integer customerId,Integer accountId, Double amount) {
        Account account = accountRepository.findAccountById(accountId);
        User user = userRepository.findUserById(customerId);

        if (account == null) {
            throw new ApiException("Account not found!");
        }

        if(user==null) //optional
            throw new ApiException("User not found!");

        if (!account.getIsActive()) {
            throw new ApiException("Cannot deposit money into an inactive account!");
        }

        if (amount <= 0) {
            throw new ApiException("Deposit amount must be positive!");
        }

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }


    public void withdrawMoney(Integer accountId, Double amount, Integer userId) {

        Account account = accountRepository.findAccountById(accountId);

        if (account == null) {
            throw new ApiException("Account not found!");
        }

        User user = userRepository.findUserById(userId);
        if(user==null) //optional
            throw new ApiException("User not found!");


        if (!account.getIsActive()) {
            throw new ApiException("This account is not active!");
        }

        if (account.getBalance() < amount) {
            throw new ApiException("Insufficient money to complete this withdrawal !");
        }

        // Perform and save
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }




    public void transferFunds(Integer fromAccountId, Integer toAccountId, Double amount, Integer userID) {

        User user = userRepository.findUserById(userID);
        if(user==null) //optional
            throw new ApiException("User not found!");

        Account fromAccount = accountRepository.findAccountById(fromAccountId);
        if (fromAccount == null) {
            throw new ApiException("Source account not found!");
        }

        Account toAccount = accountRepository.findAccountById(toAccountId);
        if (toAccount == null) {
            throw new ApiException("Destination account not found!");
        }



        // Ensure both accounts are active
        if (!fromAccount.getIsActive()) {
            throw new ApiException("Source account is not active!");
        }
        if (!toAccount.getIsActive()) {
            throw new ApiException("Destination account is not active!");
        }

        // must be sufficient funds in the source
        if (fromAccount.getBalance() < amount) {
            throw new ApiException("Insufficient funds in the source account!");
        }

        // Perform the operation
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        // Save both accounts
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    public void blockAccount(Integer accountId, Integer userId) {

        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found!");
        }

        User user = userRepository.findUserById(userId);
        if(user==null) //optional
            throw new ApiException("User not found!");

        account.setIsActive(false);

        accountRepository.save(account);
    }


















}
