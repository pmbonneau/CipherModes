/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CipherModes;

import java.util.List;

/**
 *
 * @author root
 */
public class CipherECB 
{
    Utilities Utils;
    public CipherECB()
    {
        Utils = new Utilities();
    }
    
    public String Encrypt(String DecryptedMessage, String Key, String IV)
    {
        List<String> MessageBlocks = Utils.DivideBytes(DecryptedMessage, 6);
        String EncryptedResult = "";
        StringBuilder CipherText = new StringBuilder();
        for (int i = 0; i < MessageBlocks.size(); i++)
        {
            EncryptedResult = Utils.CryptoSystem(MessageBlocks.get(i), Key, 6);
            CipherText.append(EncryptedResult);
        }
        return CipherText.toString();
    }
    
    public String Decrypt(String EncryptedMessage, String DecryptionKey, String IV)
    {
        return "";
    }
}
