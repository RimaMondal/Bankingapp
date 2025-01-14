package net.javaguides.banking.service.impl;

import net.javaguides.banking.Mapper.AccountMapper;
import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.entity.Account;
import net.javaguides.banking.repository.AccountRepository;
import net.javaguides.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {


    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        if (accountDto.getAccountHolderName() == null || accountDto.getAccountHolderName().isEmpty()) {
            throw new IllegalArgumentException("Account holder name cannot be null or empty");
        }

        // Map DTO to Entity
        Account account =  AccountMapper.mapToAccount(accountDto);
        // Save Entity
        Account saveAccount=accountRepository.save(account);
        // Map Entity to DTO
        return  AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto getAccountById(long id) {
      Account account=
              accountRepository.
                      findById(id).
                      orElseThrow(()->new RuntimeException("account does not exist"));

      return  AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(long id, double amount) {
        Account account=
                accountRepository.
                        findById(id).
                        orElseThrow(()->new RuntimeException("account does not exist"));

        double total = account.getBalance()+amount;
        account.setBalance(total);
        Account saveAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);


    }

    @Override
    public AccountDto withdraw(long id, double amount) {
        Account account=
                accountRepository.
                        findById(id).
                        orElseThrow(()->new RuntimeException("account does not exist"));

        if(account.getBalance()<amount)
            throw new RuntimeException("insuffiecient balance");

        double balance= account.getBalance()-amount;
        account.setBalance(balance);

        Account saveAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {

        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().
                map((account)-> AccountMapper.mapToAccountDto(account)).
                collect(Collectors.toList());
    }

    @Override
    public void deleteById(long id) {
        Account account=
                accountRepository.
                        findById(id).
                        orElseThrow(()->new RuntimeException("account does not exist"));

        accountRepository.deleteById(id);
    }


}
