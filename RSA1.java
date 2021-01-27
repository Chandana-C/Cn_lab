import java.util.*;
import java.math.*;

public class RSA1 {
    static BigInteger p, q, e, d, n, phi;
    static int bitLength = 1024;

    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        do {
            p = BigInteger.probablePrime(bitLength / 2, rand);
            q = BigInteger.probablePrime(bitLength / 2, rand);

            n = p.multiply(q);

        } while (n.bitLength() != bitLength);

        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        do {
            int len = rand.nextInt(phi.bitLength());
            e = BigInteger.probablePrime(len, rand);
        } while ((e.compareTo(BigInteger.TWO) == -1)
                || (e.compareTo(phi) != -1) && (phi.gcd(e).compareTo(BigInteger.ONE) != 0));

        d = e.modInverse(phi);

        System.out.print("Enter The Msg : ");
        String msg = scan.nextLine();

        byte[] msg_arr = msg.getBytes();
        System.out.println("Msg Byte Array : " + display(msg_arr));

        byte[] en = encrypt(msg_arr);
        System.out.println("Encrypted Byte Array : " + display(en));

        byte[] de = decrypt(en);
        System.out.println("Decrypted Byte Array : " + display(de));

        System.out.println("Received Msg : " + new String(de));
    }

    static byte[] encrypt(byte[] a) {
        return (new BigInteger(a).modPow(e, n)).toByteArray();
    }

    static byte[] decrypt(byte[] a) {
        return (new BigInteger(a).modPow(d, n)).toByteArray();
    }

    static String display(byte[] a) {
        StringBuilder s = new StringBuilder();

        for (byte b : a)
            s.append(b);

        return s.toString();
    }
}