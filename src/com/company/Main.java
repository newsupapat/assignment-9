package com.company;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Main {

    public static final String UTF8_BOM = "\uFEFF";
    public static String filename = "/Users/supapat/IdeaProjects/assignment 9/src/com/company/utf8lexitron copy 2.csv";
    static HashMap<String, ArrayList<String>> dict = new HashMap <String, ArrayList<String>>();
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
    readfile(filename);

        SortedSet<String> key = new TreeSet<>(dict.keySet());
        key.addAll(dict.keySet());
        //System.out.println(key);
        for(String x:key)
            System.out.println(x);
        Mostcommon();
        System.out.println("Total Read "+dict.size());
        boolean end = false;
        do{
            System.out.print("Enter Word : ");
            Scanner in = new Scanner(System.in);
            String str = in.nextLine();
            String temp = new String();
            temp = str.trim().toLowerCase();
            if(!temp.equalsIgnoreCase("end")){
                StringTokenizer stok = new StringTokenizer(temp," ");
                String[] token = new String[stok.countTokens()];
                for(int i=0;i<token.length;i++) {
                    token[i] = stok.nextToken();
                }
                String s = tokentostr(token);
                System.out.print(s+" ");
                if(dict.containsKey(s))
                    System.out.println(dict.get(s));
                else
                    System.out.println("Not Found!");
            }
            else{
                System.out.println(dict.get(temp));
                end = true;
            }
        }while(!end);
        System.out.println("End Program Written by Supapat srion 60070501074");

    }

    public static void readfile(String filename) throws FileNotFoundException, UnsupportedEncodingException {
        FileInputStream input = null;
        InputStreamReader fileread = null;
        String str = null;
        input = new FileInputStream(filename);
        fileread = new InputStreamReader(input, "UTF-8");
        Scanner sc = new Scanner(fileread);
        int j = 0;
        while (sc.hasNext()) {
            str = sc.nextLine();
            if (str.startsWith(UTF8_BOM))
                str = str.substring(1);
            str = str.trim().replaceAll("\\s+"," ").toLowerCase();
            String []temp = str.split(",");
            if(temp.length==3) {
                String word = temp[0];
                ArrayList<String> mean = new ArrayList<String>();
                mean.add(temp[1]+" "+"("+temp[2]+")");
                if (dict.containsKey(word)) {
                    if (!dict.get(word).contains(mean.get(0)))
                    {
                        dict.get(word).addAll(mean);

                    }
                    else{
                        j++;
                    }
                } else
                    dict.put(word,mean);
            }
        }
        sc.close();
        System.out.println("Duplicate "+j);
        System.out.println(dict.size());
    }
    public static String tokentostr(String[] token){
        String space = " ";
        if(token.length==2)
            return token[0].concat(space).concat(token[1]);
        else if(token.length==3)
            return token[0].concat(space).concat(token[1].concat(space).concat(token[2]));
        else
            return token[0];
    }
    public static void Mostcommon(){
        String prev = null;
        int max=0;
        for(String x:dict.keySet()){
            if(dict.get(x).size()>max){
                max=dict.get(x).size();
                prev = x;
            }
        }
        System.out.println("Maximun Meaning word "+prev+" have "+max+" meaning.");
        System.out.println(dict.get(prev));
    }
}