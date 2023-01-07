package ecc.elliptic;

import ecc.CryptoSystem;
import ecc.Key;

public class KeyGenerateTime {


    public static void main(String[] args) throws InsecureCurveException, NoCommonMotherException {

        for(int i=0;i<51;i++)
        {
            System.out.println(testTime(128));
            //System.out.println(testTime2(64));
        }

    }
    public static long testTime(int times) throws InsecureCurveException, NoCommonMotherException {
        EllipticCurve ec = new EllipticCurve(new secp112r1());
        CryptoSystem cs = new ECCryptoSystem(ec);
        CryptoSystem cs1 = new ECCryptoSystem(ec);

        long start=System.currentTimeMillis();

        Key skori=(ECKey)cs.generateKey();
        Key pkori=skori.getPublic();

        Key sk1 = (ECKey)cs.generateKey();
        Key pk1 = sk1.getPublic();

        KeyOperate kp=new KeyOperate();
        Key skadd=kp.KeyCombin(skori,sk1);
        long start1=System.currentTimeMillis();


        for(int i=0;i<times;i++){
            skadd=kp.KeyCombin(skadd,sk1);
            //System.out.println(sk.toString());
        }
        long end1=System.currentTimeMillis();
        return end1-start1;
    }

    public static long testTime2(int times) throws InsecureCurveException, NoCommonMotherException {
        EllipticCurve ec = new EllipticCurve(new secp256r1());
        CryptoSystem cs = new ECCryptoSystem(ec);
        CryptoSystem cs1 = new ECCryptoSystem(ec);

        long start=System.currentTimeMillis();

        while(times>0) {
            Key skori = (ECKey) cs.generateKey();
            Key pkori = skori.getPublic();
            times--;
        }

        long end1=System.currentTimeMillis();
        return end1-start;
    }


}



