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
    
    public String Encrypt(String DecryptedMessage, String Key)
    {
        // Mx
        List<String> MessageBlocks = Utils.DivideBytes(DecryptedMessage, 6);
        
        // Ci
        String EncryptedResult = "";
        
        // C
        StringBuilder CipherText = new StringBuilder();
        
        // For each blocks
        for (int i = 0; i < MessageBlocks.size(); i++)
        {
            // Encrypt the block with the key
            EncryptedResult = Utils.CryptoSystem(MessageBlocks.get(i), Key, 6);
            
            // Add encrypted block to cipher text
            CipherText.append(EncryptedResult);
        }
        return CipherText.toString();
    }
    
    public String Decrypt(String EncryptedMessage, String DecryptionKey)
    {
        // Cx
        List<String> MessageBlocks = Utils.DivideBytes(EncryptedMessage, 6);
        
        // Mi
        String DecryptedResult = "";
        
        // M
        StringBuilder PlainText = new StringBuilder();
        
        // For each blocks
        for (int i = 0; i < MessageBlocks.size(); i++)
        {
            // Decrypt the block with the key
            DecryptedResult = Utils.CryptoSystem(MessageBlocks.get(i), DecryptionKey, 6);
            
            // Add decrypted block to cipher text
            PlainText.append(DecryptedResult);
        }
        return PlainText.toString();
    }
}
