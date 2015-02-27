/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author ruth
 */
public interface Transaction {
 
    public String getType();
    public String getAmount();
    public String getSCode();
    public String getLanguage();
    public String getPss();
    public String getUser();
}
