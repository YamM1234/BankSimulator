/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

/**
 *
 * @author Yam
 */
public class Account {
    private String name;
    private String password;
    private int checkingBalance;
    private int savingBalance;
    
    public Account(String a, String b)
    {
        this(a, b, 0, 0);
    }
    public Account(String a, String b, int c, int d)
    {
        this.name = a;
        this.password = b;
        this.checkingBalance = c;
        this.savingBalance = d;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
    
    public String getEncryptedPassword() {
        char[] pass = this.password.toCharArray();
        for (int i = 0; i < pass.length; i++) {
            pass[i]++;
        }
        return new String(pass);
    }
    
    public static String decryptPassword(String password) {
        char[] pass = password.toCharArray();
        for (int i = 0; i < pass.length; i++) {
            pass[i]--;
        }
        return new String(pass);
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the checkingBalance
     */
    public int getCheckingBalance() {
        return checkingBalance;
    }

    /**
     * @param checkingBalance the checkingBalance to set
     */
    public void setCheckingBalance(int checkingBalance) {
        this.checkingBalance = checkingBalance;
    }

    /**
     * @return the savingBalance
     */
    public int getSavingBalance() {
        return savingBalance;
    }

    /**
     * @param savingBalance the savingBalance to set
     */
    public void setSavingBalance(int savingBalance) {
        this.savingBalance = savingBalance;
    }
    public void addCheckingBalance(int a){
        setCheckingBalance(getCheckingBalance() + a);
         
    }
    public void subtractCheckingBalance(int s ){
        setCheckingBalance(getCheckingBalance() - s);
    }
    public void addSavingBalance(int a){
        setSavingBalance(getSavingBalance() + a);
         
    }
    public void subtractSavingBalance(int s ){
        setSavingBalance(getSavingBalance() - s);
    }
    
}