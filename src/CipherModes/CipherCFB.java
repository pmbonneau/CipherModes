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
public class CipherCFB 
{
    Utilities Utils;
    public CipherCFB()
    {
        Utils = new Utilities();
    }
    
    public String Encrypt(String DecryptedMessage, String IV, String Key, int R)
    {
        // Divide the message in blocks
        List<String> MessageBlocks = Utils.DivideBytes(DecryptedMessage, 6);
        
        // lj
        String TopRegister = "";
        
        // Cj
        String MessageXOR = "";
        
        // Oj
        String EncryptedResult = "";
        
        // L(Oj, r)
        String ExtractedLeftBytesFromEncrypted = "";
        
        // mj
        String ExtractedLeftBytesFromMessage = "";
        
        // C
        StringBuilder CipherText = new StringBuilder();
        
        // For each blocks
        for (int i = 0; i < MessageBlocks.size(); i++)
        {
            // Using the IV
            if (i == 0)
            {
                TopRegister = IV;
                EncryptedResult = Utils.CryptoSystem(IV, Key, 6);
                ExtractedLeftBytesFromEncrypted = Utils.ByteExtractorLeft(EncryptedResult, R);
                ExtractedLeftBytesFromMessage = Utils.ByteExtractorLeft(IV, R);
            }
            // Other iterations, without IV
            else
            {
                EncryptedResult = Utils.CryptoSystem(TopRegister, Key, 6);
                ExtractedLeftBytesFromEncrypted = Utils.ByteExtractorLeft(EncryptedResult, R);
                ExtractedLeftBytesFromMessage = Utils.ByteExtractorLeft(TopRegister, R);
            }
            
            // XOR encrypted R bytes with R bytes from top register
            MessageXOR = Utils.XOR(ExtractedLeftBytesFromEncrypted,ExtractedLeftBytesFromMessage);
            
            // Append top register content to cipher message
            CipherText.append(TopRegister);
            
            // Shift top register from R positions
            TopRegister = Utils.LeftShift(TopRegister, R);
            
            // Append the XORed result to top register
            TopRegister = TopRegister + MessageXOR;
        }
        return CipherText.toString();
    }
    
    public String Decrypt(String EncryptedMessage, String IV, String DecryptionKey, int R)
    {
        // Divide the message in blocks
        List<String> MessageBlocks = Utils.DivideBytes(EncryptedMessage, 6);
        
        // lj
        String TopRegister = "";
        
        // Mj
        String MessageXOR = "";
        
        // Oj
        String DecryptedResult = "";
        
        // L(Oj, r)
        String ExtractedLeftBytesFromDecrypted = "";
        
        // Cj
        String ExtractedLeftBytesFromMessage = "";
        
        // M
        StringBuilder PlainText = new StringBuilder();
        
        // For each blocks
        for (int i = 0; i < MessageBlocks.size(); i++)
        {
            // Using the IV
            if (i == 0)
            {
                TopRegister = IV;
                DecryptedResult = Utils.CryptoSystem(IV, DecryptionKey, 6);
                ExtractedLeftBytesFromDecrypted = Utils.ByteExtractorLeft(DecryptedResult, R);
                ExtractedLeftBytesFromMessage = Utils.ByteExtractorLeft(IV, R);
            }
            // Other iterations, without IV
            else
            {
                DecryptedResult = Utils.CryptoSystem(TopRegister, DecryptionKey, 6);
                ExtractedLeftBytesFromDecrypted = Utils.ByteExtractorLeft(DecryptedResult, R);
                ExtractedLeftBytesFromMessage = Utils.ByteExtractorLeft(TopRegister, R);
            }
            
            // XOR encrypted R bytes with R bytes from top register
            MessageXOR = Utils.XOR(ExtractedLeftBytesFromDecrypted,ExtractedLeftBytesFromMessage);
            
            // Append top register content to cipher message
            PlainText.append(TopRegister);
            
            // Shift top register from R positions
            TopRegister = Utils.LeftShift(TopRegister, R);
            
            // Append the XORed result to top register
            TopRegister = TopRegister + MessageXOR;
        }
        return PlainText.toString();
    }
}
