/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Yam
 */
public class AccountController implements AccountWriter, AccountReader {
    private static final String dirString = "accounts";
    private static final Path dirStrPath = Paths.get(dirString);
    public AccountController()
    {
        if (Files.notExists(dirStrPath)){    
            try {
                Files.createDirectories(dirStrPath);
            } catch (IOException ex) {
                Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void writeAccount(Account a) {
        String fileString = a.getName()+".txt";
        Path filePath = Paths.get(dirString, fileString);
        if(Files.notExists(filePath))
        {
            try {
                    Files.createFile(filePath);
                } catch (IOException ex) {
                    Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        File accountFile = filePath.toFile();
        try {
            PrintWriter out = new PrintWriter(
                            new BufferedWriter(
                            new FileWriter(accountFile)));
            out.write(a.getName() + "\n"
                                + a.getEncryptedPassword() + "\n"
                                + a.getCheckingBalance() + "\n"
                                + a.getSavingBalance() + "\n");
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Account readAccount(String username) {
        String fileString = username+".txt";
        Path filePath = Paths.get(dirString, fileString);
        if(Files.notExists(filePath))
        {
            return null;
        }
        else
        {
            File accountFile = filePath.toFile();
            try {
                BufferedReader in = new BufferedReader(
                                    new FileReader(accountFile));
                String nameLine = in.readLine();
                String passLine = Account.decryptPassword(in.readLine());
                int checkingBalance = Integer.parseInt(in.readLine());
                int savingBalance = Integer.parseInt(in.readLine());
                in.close();
                return new Account(nameLine, passLine, checkingBalance, savingBalance);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
    }
}
