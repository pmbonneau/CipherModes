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
public class CipherCTR 
{
    Utilities Utils;
    public CipherCTR()
    {
        Utils = new Utilities();
    }
    
    public String Encrypt(String DecryptedMessage, String IV, String Key)
    {
        // Mx
        List<String> MessageBlocks = Utils.DivideBytes(DecryptedMessage, 6);
        
        // Counter (starts with IV)
        String Counter = IV;
        
        String MessageXOR = "";
        
        // Ci
        String EncryptedResult = "";
        
        // C
        StringBuilder CipherText = new StringBuilder();
        
        // Adding IV to cipher message at beginning (cipher message starts with IV)
        CipherText.append(IV);

        for (int i = 0; i < MessageBlocks.size(); i++)
        {
            // Encrypting counter
            EncryptedResult = Utils.CryptoSystem(Counter, Key, 6);
            
            // XORed encrypted counter value with message block
            MessageXOR = Utils.XOR(EncryptedResult,MessageBlocks.get(i));
            
            CipherText.append(MessageXOR);
            
            // Increment the counter
            Counter = Utils.BinaryAdd(Counter, "1");
        }
        return CipherText.toString();
    }
    
    public String Decrypt(String EncryptedMessage, String DecryptionKey, String IV)
    {
        // Cx
        List<String> MessageBlocks = Utils.DivideBytes(EncryptedMessage, 6);
        
        // Counter (starts with IV)
        String Counter = IV;
        
        String MessageXOR = "";
        
        // Mi
        String DecryptedResult = "";
        
        // M
        StringBuilder PlainText = new StringBuilder();
        
        // Adding IV to decrypted message at beginning
        PlainText.append(IV);

        for (int i = 0; i < MessageBlocks.size(); i++)
        {
            // Decrypting counter
            DecryptedResult = Utils.CryptoSystem(Counter, DecryptionKey, 6);
            
            // XORed decrypted counter value with message block
            MessageXOR = Utils.XOR(DecryptedResult,MessageBlocks.get(i));
            
            PlainText.append(MessageXOR);
            
            // Increment the counter
            Counter = Utils.BinaryAdd(Counter, "1");
        }
        return PlainText.toString();
    }
    
}
