package ecc.elliptic;

import ecc.*;
import ecc.io.*;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class TestECCrypto {
    public static void main(String[] args) {
	try {
	    EllipticCurve ec = new EllipticCurve(new secp160r1());

	    CryptoSystem cs = new ECCryptoSystem(ec);
		CryptoSystem cs1 = new ECCryptoSystem(ec);

		String SKpath="sKey.txt";
		String PKpath="pKey.txt";

		long start1=System.currentTimeMillis();
	    Key sk = (ECKey)cs.generateKey();
		long end1=System.currentTimeMillis();
	    Key pk = sk.getPublic();

		System.out.println("生成密钥时延："+(end1-start1)+"ms");


		//多密钥测试
		Key sk1 = (ECKey)cs1.generateKey();
		Key pk1 = sk1.getPublic();

		String ss=sk.toString();
		String sp=pk.toString();

		String ss1=sk1.toString();
		String sp1=pk1.toString();

		System.out.println(ss);
		System.out.println(sp);

		System.out.println(ss1);
		System.out.println(sp1);
		/*
		//测试将两个密钥相加
		//Key skadd=((ECKey) sk).SKadd((ECKey) sk1);
		//String ssadd=skadd.toString();
		//System.out.println("组合后密钥为：");
		//Key pkadd=skadd.getPublic();
		//System.out.println(ssadd);
		//ECPoint tttt=ec.getGenerator().multiply(skadd.getSK());
		//System.out.println("计算结果为： "+tttt);
		//String spadd=pkadd.toString();
		//System.out.println(spadd);

		((ECKey) sk).justWriteKey(SKpath);
		((ECKey) sk1).justWriteKey(SKpath);

		((ECKey) pk).justWriteKey(PKpath);
		((ECKey) pk1).justWriteKey(PKpath);*/

		KeyOperate kp=new KeyOperate();

		Key readsk1=kp.justReadKey(SKpath,11) ;
		String rsk1=readsk1.toString();
		System.out.println("rsk1:== "+rsk1);

		Key readsk2=kp.justReadKey(SKpath,5) ;
		String rsk2=readsk2.toString();
		System.out.println("rsk2:== "+rsk2);

		Key readpk1=kp.justReadKey(PKpath,11);
		String rpk1=readpk1.toString();
		System.out.println("rpk1:== "+rpk1);

		Key readpk2=kp.justReadKey(PKpath,5);
		String rpk2=readpk2.toString();
		System.out.println("rpk2:== "+rpk2);

		//long start1=System.currentTimeMillis();
		Key pkadd=kp.KeyCombin(readpk1,readpk2);
		//long end1=System.currentTimeMillis();
		//long start2=System.currentTimeMillis();
		Key skadd=kp.KeyCombin(readsk1,readsk2);
		//long end2=System.currentTimeMillis();
		//System.out.println("公钥组合时延："+(end1-start1)+"ms");
		//System.out.println("私钥组合时延："+(end2-start2)+"ms");

		String cpk=pkadd.toString();
		System.out.println("cpk:== "+cpk);
		String csk=skadd.toString();
		System.out.println("csk:== "+csk);


	    byte[] test1 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		byte[] test2 = cs.decrypt(cs.encrypt(test1, 20, pkadd), skadd);



	    String string="hello worldx";
	    byte[] test=string.getBytes("GBK");
	    String t1=new String(test,"GBK");
	    String ten=new String(cs.encrypt(test1,test.length,skadd),"GBK");
	    //System.out.println("hhh: "+ten);
	    String tde=new String(cs.decrypt(cs.encrypt(test, test.length, pkadd), skadd),"GBK");


	    if(Arrays.equals(test1, test1)) System.out.println("Testing...");
	    if(Arrays.equals(test1, test2)) {
			System.out.println("Succes");
			System.out.println("test1plain : "+t1);

			System.out.println("testencrypt: "+ten);
			System.out.println("testdecrypt: "+tde);
	    } else {
		System.out.print("Fail\n{");
		for(int i = 0; i < 20; i++) {
		    System.out.print(test2[i]+",");
		}
		System.out.println("}");
	    }
	} catch (InsecureCurveException | UnsupportedEncodingException | NoCommonMotherException e) {
	    System.out.println("TestCryptoStreams: "+e);
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	}
}
