package net.java.banking.app.service.impl;

import net.java.banking.app.dto.AccountDto;
import net.java.banking.app.entity.Account;
import net.java.banking.app.mapper.AccountMapper;
import net.java.banking.app.repository.AccountRepository;
import net.java.banking.app.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class AccountServiceImpl implements AccountService {

    //injecting the dependencey
    private AccountRepository accountRepository;


    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
     Account account = AccountMapper.mapToAccount(accountDto);
     Account savedaccount = accountRepository.save(account);//return saved entity
        return AccountMapper.mapToAccount(savedaccount);

    }

    @Override
    public AccountDto getAccountByID(Long id) {
   Account account =   accountRepository
           .findById(id)
                   .orElseThrow(()-> new RuntimeException("Account doest not exist"));
        return AccountMapper.mapToAccount(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account =   accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account doest not exist"));
        double total =account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccount(savedAccount);

    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account =   accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account doest not exist"));

        if(account.getBalance() < amount){
            throw new RuntimeException("insufficient amount");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return  AccountMapper.mapToAccount(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(AccountMapper::mapToAccount).collect(Collectors.toList());


    }

    @Override
    public void deleteAccount(Long id) {
        Account account =   accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account doest not exist"));
        accountRepository.deleteById(id);
    }


}
