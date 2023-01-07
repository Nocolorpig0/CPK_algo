package ecc.elliptic;


import ecc.Rand;

import java.math.BigInteger;
import java.io.*;
import java.util.*;
import java.util.zip.*;

public class Ecdh {
    public static void main(String[] args) throws InsecureCurveException, NotOnMotherException, NoCommonMotherException {

            Random r1=new Random(90);
            BigInteger a=new BigInteger(60,r1);  //Alice 生成的随机整数（私钥）
            System.out.println("Alice: "+a);
            Random r2=new Random(60);
            BigInteger b=new BigInteger(50,r2);  //Bob 生成随机整数（私钥）
            System.out.println("Bob: "+b);

            //生成椭圆曲线
            EllipticCurve e= new EllipticCurve(new BigInteger("1"),new BigInteger("6"),new BigInteger("11"));
            System.out.println("生成的椭圆曲线为："+e);

            //生成基点G
            ECPoint G=new ECPoint(e,new BigInteger("2"),new BigInteger("7"));
            ECPoint A,B;
            A=G.multiply(a);  //计算A=a*G；
            System.out.println("A=a*G:"+a+" * "+G+" = "+A);
            B=G.multiply(b);  //计算B=b*G；
            System.out.println("B=b*G:"+b+" * "+G+" = "+B);

            ECPoint Q1,Q2,Q3;
            Q1=A.multiply(b);
            Q2=B.multiply(a);
            Q3=G.add(G.add(G));

            //Q3.add(A);
            //Q3.add(B);
            System.out.println("Q1: "+Q1);
            System.out.println("Q2: "+Q2);
            System.out.println("Q3: "+Q3);

            ECPoint Q4=G.multiply(new BigInteger("3"));
            System.out.println("Q4: "+Q4);
            System.out.println(Q1.equals(Q2));
    }
}
