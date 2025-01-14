package net.javaguides.banking.Mapper;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto) {
        if (accountDto == null) {
            return null;
        }
        return new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
    }

    //Entity to DTO
    public static AccountDto mapToAccountDto(Account account) {
        if (account == null) {
            return null;
        }
        return new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
    }
}
