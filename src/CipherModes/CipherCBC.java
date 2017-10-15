/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CipherModes;

import java.util.List;

/**
 *
 * @author Pierre-Marc Bonneau
 */
public class CipherCBC 
{
    Utilities Utils;
    public CipherCBC()
    {
        Utils = new Utilities();
    }
    
    public String Encrypt(String DecryptedMessage, String IV, String Key)
    {
        // Mx
        List<String> MessageBlocks = Utils.DivideBytes(DecryptedMessage, 6);
        
        // Mi XOR Ci - 1
        String MessageXOR = "";
        
        // Ci
        String EncryptedResult = "";
        
        // C
        StringBuilder CipherText = new StringBuilder();
        for (int i = 0; i < MessageBlocks.size(); i++)
        {
            // Using the IV in the first iteration
            if (i == 0)
            {
                MessageXOR = Utils.XOR(IV,MessageBlocks.get(0));
            }
            // For other iterations, using previous encrypted block
            else
            {
                MessageXOR = Utils.XOR(EncryptedResult,MessageBlocks.get(i));
            }
            EncryptedResult = Utils.CryptoSystem(MessageXOR, Key, 6);
            
            // Append encrypted block to cipher message
            CipherText.append(EncryptedResult);
        }
        return CipherText.toString();
    }
}
