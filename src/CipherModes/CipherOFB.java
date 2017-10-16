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
public class CipherOFB 
{
    Utilities Utils;
    public CipherOFB()
    {
        Utils = new Utilities();
    }
    
    public String Encrypt(String DecryptedMessage, String IV, String Key, int R)
    {
        // Divide the message in blocks
        List<String> MessageBlocks = Utils.DivideBytes(DecryptedMessage, 6);
        
        // lj
        String TopRegister = "";
        
        // Oj
        String MessageXOR = "";
        
        // Cj
        String EncryptedResult = "";
        
        // L(Oj,R)
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
                
                // Append the IV to the cipher message
                CipherText.append(TopRegister);
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
            
            // Append XORed message to cipher message
            CipherText.append(MessageXOR);
            
            
            // Append the encrypted result to top register
            TopRegister = EncryptedResult;
        }
        return CipherText.toString();
    }
    
    public String Decrypt(String EncryptedMessage, String IV, String Key, int R)
    {
        // Divide the message in blocks
        List<String> MessageBlocks = Utils.DivideBytes(EncryptedMessage, 6);
        
        // lj
        String TopRegister = "";
        
        // Oj
        String MessageXOR = "";
        
        // Mj
        String DecryptedResult = "";
        
        // L(Oj,R)
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
                DecryptedResult = Utils.CryptoSystem(IV, Key, 6);
                ExtractedLeftBytesFromDecrypted = Utils.ByteExtractorLeft(DecryptedResult, R);
                ExtractedLeftBytesFromMessage = Utils.ByteExtractorLeft(IV, R);
                
                // Append the IV to the cipher message
                PlainText.append(TopRegister);
            }
            // Other iterations, without IV
            else
            {
                DecryptedResult = Utils.CryptoSystem(TopRegister, Key, 6);
                ExtractedLeftBytesFromDecrypted = Utils.ByteExtractorLeft(DecryptedResult, R);
                ExtractedLeftBytesFromMessage = Utils.ByteExtractorLeft(TopRegister, R);
            }
            
            // XOR encrypted R bytes with R bytes from top register
            MessageXOR = Utils.XOR(ExtractedLeftBytesFromDecrypted,ExtractedLeftBytesFromMessage);
            
            // Append XORed message to cipher message
            PlainText.append(MessageXOR);
            
            
            // Append the encrypted result to top register
            TopRegister = DecryptedResult;
        }
        return PlainText.toString();
    }
}
