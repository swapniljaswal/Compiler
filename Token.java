/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

/**
 *
 * @author Swapnil
 */
public class Token{
    
    String tokenName, lexemeName;
    int lineNo;

    public Token(String lexemeName, String tokenName, int lineNo) {
        this.tokenName = tokenName;
        this.lexemeName = lexemeName;
        this.lineNo = lineNo;
    }
    
    
}
