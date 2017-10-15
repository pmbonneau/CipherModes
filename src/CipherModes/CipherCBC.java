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
        //Utils.CryptoSystem(DecryptedMessage, Key, 6);
        List<String> MessageBlocks = Utils.DivideBytes(DecryptedMessage, 6);
        String MessageXOR = "";
        String EncryptedResult = "";
        StringBuilder CipherText = new StringBuilder();
        for (int i = 0; i < MessageBlocks.size(); i++)
        {
            if (i == 0)
            {
                MessageXOR = Utils.XOR(IV,MessageBlocks.get(0));
            }
            else
            {
                MessageXOR = Utils.XOR(EncryptedResult,MessageBlocks.get(i));
            }
            EncryptedResult = Utils.CryptoSystem(MessageXOR, Key, 6);
            CipherText.append(EncryptedResult);
        }
        return CipherText.toString();
    }
}
