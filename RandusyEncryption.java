import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RandusyEncryption {
	private String password;
	
	/**
	 * Constructs the Object
	 * @param password the password to encrypt
	 */
	public RandusyEncryption() {
		this.password = "";
	}
	/**
	 * Constructs the Object
	 * @param password the password to encrypt
	 */
	public RandusyEncryption(String password) {
		this.password = password;
		  
	}
	/**
	 * setPassword method, set the password of the Object
	 * @param password the password to encrypt
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	/**
	 * getPassword method, gets the password of the Object
	 * @return password the password to encrypt
	 */
	public String getPassword()
	{
		return password;
	}
	/**
	 * verifyPassword method, Verifies the password
	 * @param password, password to verify
	 * @return
	 */
	public boolean verifyPassword(String password)
	{
		RandusyEncryption re = new RandusyEncryption(password);
		return re.toString().equals(toString());
	}
	/**
	 * encrypt method, Encrypts the password
	 * @param password string holding the password
	 * @return String representation of the encryption
	 */
	private  String encrypt()
	{
		List<BigInteger> passwordFactors = getFactors();
		return getEncryption(passwordFactors);
	}
	/**
	 * getFactors method, Gets the prime factors of the password
	 * @param password
	 * @return list of bigIngeter with the factors
	 */
	private List<BigInteger> getFactors()
	{
		List<BigInteger> numbers = new ArrayList<>();
		char chars[] = password.toCharArray();
		 for (int i = 0; i < chars.length; i++) {
			 Integer num = (int) chars[i];
			numbers.addAll(factors(new BigInteger(num.toString())));
		} 
		return numbers;
	}
	/**
	 * Encrypts the number given
	 * @param number to encrypt
	 * @return string representing the encryption
	 */
	private String encryptNumber(Long number, int offset)
	{
		int OFFSET = password.toCharArray().length;
		List<String> code = new ArrayList<>();
		long n = number + offset + OFFSET;  
		if(n < 33)
		{
			Long num = n;
			code.add(num.toString()); 
		}else if( n < 127)
		{
			Character c = (char) n;
			code.add(c.toString());  
		}else
		{
			Long num = n;
			String nm = num.toString();
			int j;
			for (j = 0; j < nm.toCharArray().length/2; j++)
			{
				String twoChars = nm.substring(j*2,j*2+2);   
				long ourNum = Long.parseLong(twoChars);
				if(ourNum < 33)
				{
					Long numOurs = ourNum;
					code.add(numOurs.toString()); 
				}else if( ourNum < 127)
				{
					Character c = (char) ourNum;
					code.add(c.toString());  
				}
			}
			if(nm.toCharArray().length % 2 == 1)
			{ 
				
				String leftover = nm.substring(j*2,j*2+1);  
				Long numLeftOver = Long.parseLong(leftover);
				code.add(numLeftOver.toString()); 
			}
		}
		StringBuilder encrypted = new StringBuilder();
		for (String string : code) {
			encrypted.append(string);
		}
		return encrypted.toString();
	}
	/**
	 * Creates an encryption based on the given prime factors
	 * @param factors prime factors of a number i.e password
	 * @return string representing the encryption
	 */
	private String getEncryption(List<BigInteger> factors)
	{
		int groups = 3;
		List<String> code = new ArrayList<>(); 
		BigInteger prod = new BigInteger("1"); 
		int i;
		 for (i = 0; i < factors.size()/groups; i++) { 
			prod = factors.get(i*groups).multiply(factors.get(i*groups+1));
			prod = prod.multiply(factors.get(i*groups+2));  
			code.add(encryptNumber(prod.longValue(),i*groups+3));
		}
		 if((factors.size() % groups) == 1 )
		 {
			 prod = factors.get(i*groups);
			 code.add(encryptNumber(prod.longValue(),i*groups));   
		 }else if((factors.size() % groups) == 2 )
		 {
			 prod = factors.get(i*groups).multiply(factors.get(i*groups+1));
			 code.add(encryptNumber(prod.longValue(),i*groups+3));  
		 } 
		 StringBuilder fullEncryption = new StringBuilder();
		 for (String string : code) {
			fullEncryption.append(string);
		}
		return fullEncryption.toString();
	}
	/**
	 * Factors out the number given to the respective prime numbers
	 * @param number bigInteger to factor out
	 * @return list containing the factors
	 */
	private List<BigInteger> factors(BigInteger number)
	{ 
		List<BigInteger> factors = new ArrayList<>();
		BigInteger i = new BigInteger("2");
		while(!i.equals(number))
		{
			if((number.mod(i)).equals(new BigInteger("0")))
			{  
				factors.add(i);
				number = number.divide(i);
				i = new BigInteger("1");
			}
			
			i = i.add(new BigInteger("1"));
		} 
		factors.add(number); 
		return factors;
	}
	/**
	 * Responsible for how to print the object
	 @return string representation of the object
	 */
	@Override
	public String toString() {
		return encrypt();
	}
}
