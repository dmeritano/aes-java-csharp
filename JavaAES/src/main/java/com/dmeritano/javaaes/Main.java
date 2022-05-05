package com.dmeritano.javaaes;

public class Main {


    public static void main(String[] args) {
              
       String key = getSecret();
       System.out.println("key:" + key);
               
       String encTest = AESChiper.encrypt("Hello World!", key);
       System.out.println("'Hello World!' ---> '" + encTest + "'");
       
       String desencTest = AESChiper.decrypt(encTest, key);
       System.out.println("'" + encTest + "' ---> '" + desencTest + "'");
        
    }
    
    //Get secret key from some resoruce file or environment variable
    private static String getSecret(){
        
        String env = System.getenv("SECRET_PHRASE");
        return (env==null || env.isEmpty()) ? "s3cr3t$007" : env;
    }
    
}
