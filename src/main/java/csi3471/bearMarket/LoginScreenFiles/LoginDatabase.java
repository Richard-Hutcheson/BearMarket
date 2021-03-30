package csi3471.bearMarket.LoginScreenFiles;

import csi3471.bearMarket.Account;

import java.util.ArrayList;

//Possibly will be removed, because only need to access 1 account at a time.
class LoginDatabase{
    ArrayList<Account> accounts;
    public LoginDatabase() { accounts = new ArrayList<>(); }
    public void addAccount(Account account) { accounts.add(account); }
    public void readAccounts(){
        //Implement reading capabilities from a csv file to load the accounts
    }
}
