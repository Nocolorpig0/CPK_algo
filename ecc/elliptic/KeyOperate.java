package ecc.elliptic;

import ecc.Key;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class KeyOperate {

    public Key justReadKey(String path, int index) throws IOException, ClassNotFoundException {
        File file=new File(path);
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(path));
        while(index-1>0)
        {

            index--;
            try{
                Key tmp=(Key)ois.readObject();

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("no such Key of index!!!"+index+1);
                return null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


            //System.out.println("读取后的结果in while： "+tmp.toString());
        }
        //ois=new ObjectInputStream(new FileInputStream(path));
        Key a=(Key) ois.readObject();
        //System.out.println("读取后的结果： "+a.toString());
        ois.close();
        return (Key)a;
    }

    public Key KeyCombin(Key a,Key b) throws NoCommonMotherException {
        ECKey res=((ECKey)a).SKadd((ECKey)b);
        return res;
    }
}
