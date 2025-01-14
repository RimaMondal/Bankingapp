package net.javaguides.banking.controller;


import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Add account REST API
    @PostMapping
    public ResponseEntity<AccountDto>  addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //Get Account by Id
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }


    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable long id, @RequestBody Map<String, Double> request){
        Double amount=request.get("amount");
       AccountDto accountDto = accountService.deposit(id,amount);
       return  ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping
    public  ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts=accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }


    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteById(@PathVariable long id){
        accountService.deleteById(id);
        return ResponseEntity.ok("Account Deleted Successfully");
    }
}
