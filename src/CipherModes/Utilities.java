/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CipherModes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pierre-Marc Bonneau
 */
public class Utilities 
{
    private static boolean bitOf(char in) {
        return (in == '1');
    }

    private static char charOf(boolean in) {
        return (in) ? '1' : '0';
    }

    public String XOR(String P, String Q) 
    {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < P.length(); i++) {
            sb.append(charOf(bitOf(P.charAt(i)) ^ bitOf(Q.charAt(i))));
        }

        String result = sb.toString();
        return result;
    }
    
    public String CryptoSystem(String InputMessage, String Key, int Size) 
    {
        List<String> MessageBlocks = new ArrayList<String>();
        List<String> KeyBlocks = new ArrayList<String>();
        int BlockSize = InputMessage.length() / 6;
        MessageBlocks = DivideBytes(InputMessage, BlockSize);
        for (int i = 0; i < Key.length(); i++)
        {
            KeyBlocks.add(KeyPadding(Key.charAt(i), BlockSize));
        }
        
        StringBuilder CipherMessage = new StringBuilder();
        for (int i = 0; i < MessageBlocks.size(); i++)
        {
            CipherMessage.append(XOR(MessageBlocks.get(i), KeyBlocks.get(i)));
        }
        return CipherMessage.toString();
    }
    
    private String KeyPadding(char KeyValue, int BlockSize)
    {
        StringBuilder KeyPadded = new StringBuilder();
        for (int i = 0; i < BlockSize - 1; i++)
        {
            KeyPadded.append('0');
        }
        KeyPadded.append(KeyValue);
        return KeyPadded.toString();
    }
    
    public List<String> DivideBytes(String InputMessage, int Size) 
    {
        List<String> result = new ArrayList<String>((InputMessage.length() + Size - 1) / Size);
        if(InputMessage.length() % 6 == 0) 
        {
            for (int i = 0; i < InputMessage.length(); i += Size) 
            {
                result.add(InputMessage.substring(i, Math.min(InputMessage.length(), i + Size)));
            }
            return result;
        }
        return result;
    }
    
    public String ByteSwitcher(String InputMessage, int R)
    {
        StringBuilder Result = new StringBuilder();
        for (int i = R - 1; i < InputMessage.length(); i++)
        {
            Result.append(InputMessage.charAt(i));
        }
        for (int i = 0; i < R - 1; i++)
        {
            Result.append(InputMessage.charAt(i));
        }
        return Result.toString();
    }
    
    public String ByteExtractorLeft(String InputMessage, int R)
    {
        StringBuilder Result = new StringBuilder();
        for (int i = 0; i < R; i++)
        {
            Result.append(InputMessage.charAt(i));
        }
        return Result.toString();
    }
    
    public String LeftShift(String s, int k)
    {
        String result = "";  
        for(int i=0;i<k;i++)
        {
            result = s.substring(1, s.length() - 1) +s.charAt(0);
            s=result;
         }
         return s;
    }
}
