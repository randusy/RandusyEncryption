
public class EncryptionDriver {

	public static void main(String[] args) {
		 System.out.println("========== Randusy Encryption Algorithm ==========");
		 RandusyEncryption e = new RandusyEncryption("myStrong@Password!@");
		 System.out.println("Encryption : "+e);
		 System.out.println("Verify :"+e.verifyPassword("myStrong@Password!@."));
		 System.out.println();
		 RandusyEncryption e2 = new RandusyEncryption("password");
		 System.out.println("Encryption : "+e2);
		 System.out.println("Verify :"+e2.verifyPassword("password"));
		 System.out.println();
		 RandusyEncryption e3 = new RandusyEncryption("randu");
		 System.out.println("Encryption : "+e3);
		 System.out.println("Verify :"+e3.verifyPassword("randu"));
		 System.out.println("========== End Encryption Algorithm ==========");
	}

}

