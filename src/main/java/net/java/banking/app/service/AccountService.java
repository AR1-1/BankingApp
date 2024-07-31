package net.java.banking.app.service;

import net.java.banking.app.dto.AccountDto;

import java.util.List;


public interface AccountService {

    //creating account method
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountByID(Long id);

    AccountDto deposit(Long id, double amount);

    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);
}
