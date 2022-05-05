using System;


namespace CSharpAES
{
    public class Program
    {
        static void Main(string[] args)
        {

            try
            {
                string key = getSecret();
                Console.WriteLine("key:" + key);
                key = null;

                string encTest = AESChiper.Encrypt("Hello World!", key);
                Console.WriteLine("'Hello World!' ---> '" + encTest + "'");

                string desencTest = AESChiper.Decrypt(encTest, key);
                Console.WriteLine("'" + encTest + "' ---> '" + desencTest + "'");

            }
            catch (Exception ex)
            {
                Console.WriteLine("Error: " + ex.Message);                    
            }
            Console.WriteLine("Press any key to terminate program");
            Console.ReadKey();
        }

        //Get secret key from some resoruce file or environment variable
        private static string getSecret()
        {
            var env = Environment.GetEnvironmentVariable("SECRET_PHRASE");
            return (env == null || env.Equals(string.Empty)) ? "s3cr3t$007" : env;
        }
    }
}
