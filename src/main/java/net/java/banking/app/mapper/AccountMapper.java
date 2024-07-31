package net.java.banking.app.mapper;

import net.java.banking.app.dto.AccountDto;
import net.java.banking.app.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto){

       // converting AccountDto -> accountJPa entity
        Account account = new Account(

                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );

        return  account;
    }
//converting account jpa -> account dto
    public static AccountDto mapToAccount(Account account){

        AccountDto accountDto = new AccountDto(

                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return  accountDto;
    }

}
