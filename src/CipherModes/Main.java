/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CipherModes;
import CommandLineArgsParser.*;

/**
 *
 * @author Pierre-Marc Bonneau
 */
public class Main {

    /**
     * @param args the command line arguments
     */
public static void main(String[] args) 
    {
        // Command line arguments parser usage is based from:
        // https://stackoverflow.com/questions/367706/how-to-parse-command-line-arguments-in-java
        Options options = new Options();

        Option message = new Option("msg", "message", true, "input message");
        message.setRequired(true);
        options.addOption(message);

        Option key = new Option("key", "key", true, "encryption key");
        key.setRequired(true);
        options.addOption(key);
        
        Option operation = new Option("op", "operation", true, "operation encrypt or decrypt");
        operation.setRequired(true);
        options.addOption(operation);
        
        Option mode = new Option("mode", "mode", true, "cipher mode");
        mode.setRequired(true);
        options.addOption(mode);
        
        Option iv = new Option("iv", "iv", true, "encryption iv");
        iv.setRequired(true);
        options.addOption(iv);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try 
        {
            cmd = parser.parse(options, args);
        } 
        catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
            return;
        }

        String string_message = cmd.getOptionValue("message");
        String string_key = cmd.getOptionValue("key");
        String string_operation = cmd.getOptionValue("operation");
        String string_mode = cmd.getOptionValue("mode");
        String string_iv = cmd.getOptionValue("iv");
        

        System.out.println(string_message);
        System.out.println(string_key);
        System.out.println(string_operation);
        System.out.println(string_mode);
        System.out.println(string_iv);
        
        if (string_mode.equals("ECB"))
        {
            CipherECB ECBmode = new CipherECB();
            if (string_operation.equals("enc"))
            {
                ECBmode.Encrypt(string_message, string_key, string_iv);
            }
            else
            if (string_operation.equals("dec"))
            {
                
            }
        }
        else if (string_mode.equals("CBC"))
        {
            if (string_operation.equals("enc"))
            {
                
            }
            else
            if (string_operation.equals("dec"))
            {
                
            }
        }
        else if (string_mode.equals("CFB"))
        {
            if (string_operation.equals("enc"))
            {
                
            }
            else
            if (string_operation.equals("dec"))
            {
                
            }
        }
        else if (string_mode.equals("OFB"))
        {
            if (string_operation.equals("enc"))
            {
                
            }
            else
            if (string_operation.equals("dec"))
            {
                
            }
        }
        else if (string_mode.equals("CTR"))
        {
            if (string_operation.equals("enc"))
            {
                
            }
            else
            if (string_operation.equals("dec"))
            {
                
            }
        }
        
        
    }
    
}
