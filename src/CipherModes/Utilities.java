/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CipherModes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    // This method does the XOR
    public String XOR(String P, String Q) 
    {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < P.length(); i++) {
            sb.append(charOf(bitOf(P.charAt(i)) ^ bitOf(Q.charAt(i))));
        }

        String result = sb.toString();
        return result;
    }
    
    public String GeneratorIV(int Length)
    {
        StringBuilder IV = new StringBuilder();
        for (int i = 0; i < Length; i++)
        {
            Random Randomizer = new Random();
            int bin = Randomizer.nextInt(2);
            IV.append(String.valueOf(bin));
        }
        return IV.toString();
    }
    
    // This is the encryption system
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
    
    // Used to pad the key in the encryption system
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
    
    // Used to do some bytes splitting
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
    
    // Extract R bytes from left
    public String ByteExtractorLeft(String InputMessage, int R)
    {
        StringBuilder Result = new StringBuilder();
        for (int i = 0; i < R; i++)
        {
            Result.append(InputMessage.charAt(i));
        }
        return Result.toString();
    }
    
    // Shift bytes from left
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
    
    // Binary addition
    // https://stackoverflow.com/questions/8548586/adding-binary-numbers
    public String BinaryAdd(String s1, String s2) 
    {
        if (s1 == null || s2 == null) return "";
        int first = s1.length() - 1;
        int second = s2.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (first >= 0 || second >= 0) {
            int sum = carry;
            if (first >= 0) {
                sum += s1.charAt(first) - '0';
                first--;
            }
            if (second >= 0) {
                sum += s2.charAt(second) - '0';
                second--;
            }
            carry = sum >> 1;
            sum = sum & 1;
            sb.append(sum == 0 ? '0' : '1');
        }
        if (carry > 0)
            sb.append('1');

        sb.reverse();
        return String.valueOf(sb);
    }   
}
