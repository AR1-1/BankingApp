package net.java.banking.app.controller;

import net.java.banking.app.dto.AccountDto;
import net.java.banking.app.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    //injecting dependecy
    private AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;

    }

    //add account rest api
    //handle post request
    @PostMapping
    public ResponseEntity<AccountDto> addAccount( @RequestBody /*converts json into java objects */AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);

    }

    //get account rest api
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountByID(id);
        return  ResponseEntity.ok((accountDto));
    }

    //Deposit Rest API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){

        Double amount = request.get("amount");
        AccountDto accountDto =accountService.deposit(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    //withdraw restapi
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request){

        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id,amount);
        return  ResponseEntity.ok(accountDto);

    }

    @GetMapping
    //get all account rest api
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
       List<AccountDto> accounts = accountService.getAllAccounts();
       return ResponseEntity.ok(accounts);


    }


    @DeleteMapping("/{id}")
    //delete for rest api
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){

        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully");
    }
}