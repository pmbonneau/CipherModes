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
        
        // Ci - 1 XOR Mi
        String MessageXOR = "";
        
        // Ci, (becomes Ci - 1 for other iterations after 0).
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
    
    public String Decrypt(String EncryptedMessage, String DecryptionKey, String IV)
    {
        // Cx
        List<String> MessageBlocks = Utils.DivideBytes(EncryptedMessage, 6);
        
        // Ci - 1 XOR Mi
        String MessageXOR = "";
        
        // Mi
        String DecryptedResult = "";
        
        // M
        StringBuilder PlainText = new StringBuilder();
        for (int i = 0; i < MessageBlocks.size(); i++)
        {
            // Decrypt cipher message bloc
            DecryptedResult = Utils.CryptoSystem(MessageBlocks.get(i), DecryptionKey, 6);
                       
            //Using the IV in the first iteration
            if (i == 0)
            {   
                MessageXOR = Utils.XOR(IV,DecryptedResult);
                // Addind result to decrypted message
                PlainText.append(MessageXOR);
            }
            // For other iterations, using previous encrypted block
            else
            {
                MessageXOR = Utils.XOR(MessageBlocks.get(i - 1),DecryptedResult);
                PlainText.append(MessageXOR);
            }
        }
        return PlainText.toString();
    }
}
