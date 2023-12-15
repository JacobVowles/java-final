package comp31.javafinal.services;

import org.springframework.stereotype.Service;

import comp31.javafinal.model.repos.AccountsRepo;

@Service
public class AccountService {
    AccountsRepo accountRepo;
    // ALL JACOB
    public AccountService(AccountsRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public void deleteById(Integer id) {
        accountRepo.deleteById(id);
    }
}
