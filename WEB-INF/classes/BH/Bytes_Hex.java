package BH;
import java.util.Arrays;
import java.io.*;

public class Bytes_Hex {
    public static void main(String[] args){
        new Bytes_Hex().debug();
    }

    public void debug(){
        String test="Code it.";
        System.out.println("The Test String: "+test);
        byte[] theBytes = String2ByteArray(test);
        System.out.println("As a byte Array: "+Arrays.toString(theBytes));
        String hexstring=ByteArray2HexString(theBytes);
        System.out.println("As a Hex String: "+hexstring);
        char[] theHexes= hexstring.toCharArray();
        System.out.println("As a Hex Array: "+Arrays.toString(theHexes));
        System.out.println("Back to the String: "+ ByteArray2String(HexArray2ByteArray(theHexes)));
        System.out.println("\nIn One Step");
        String ashex=String2HexString(test);
        System.out.println("String to Hex String: "+ashex);
        System.out.println("Hex String to String: "+HexString2String(ashex));
    }

    public Bytes_Hex(){ }




    public static byte[] String2ByteArray(String s){
        return s.getBytes();
    }

    public static String ByteArray2String(byte[] barray){
        try {
            return  new String(barray, "UTF-8");
        } catch (Exception e) {
            System.out.println(e);
        }
        return "Error";
    }


    public static String ByteArray2HexString(byte[] barray){
        StringBuilder result = new StringBuilder();
        for (byte b : barray)
            result.append(String.format("%02X", b));  
        return result.toString();
    }


    public static byte[] HexArray2ByteArray(char[] buf){
        byte[] buf1=new byte[(buf.length)/2];
        for (int i=0;i<buf.length;i=i+2) {
            buf1[i/2] = (byte) Integer.parseInt(new String(new char [] {buf[i],buf[i+1]}), 16);
        }
        return buf1;
    }

    public static byte[] HexString2ByteArray(String str){
        byte[] buf1=new byte[str.length()/2];
        for (int i=0;i<str.length();i=i+2) {
            buf1[i/2] = (byte) Integer.parseInt(str.substring(i, i+2), 16);
        }
        return buf1;
    }
    public static String String2HexString(String s){
        return            ByteArray2HexString(String2ByteArray(s));
    }


    public static String HexString2String(String hex){
        if ((hex==null)||hex.length()==0) {
            return "Empty String.";
        }
        try {
            byte [] byte_hex=new byte[(hex.length())/2];
            byte_hex= HexString2ByteArray(hex);
            return  new String(byte_hex, "UTF-8");
        } catch (Exception e) {
            System.out.println(e);
        }
        return "Error";
    }

    public static byte[] makeByteArray(String thepath){
        byte [] thetextfile ;
        try {
            File thefile = new File(thepath);
            FileInputStream fintext = new FileInputStream(thefile);
            thetextfile  = new byte[(int)thefile.length()];
            fintext.read(thetextfile);
            return thetextfile;

        } catch (Exception e) {
            System.out.println(e);
        }
        return new byte[0];

    }
}





