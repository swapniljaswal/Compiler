/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Swapnil
 */
public class Compiler {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception
    {
        List<String> keywords = Arrays.asList("break","else","long","switch","case","enum","register","typedef","char","extern","return","int","double",
                "union","const","float","short","unsigned","continue","for","signed","void","default","goto","sizeof","volatile","do","if","static","while");
        
        List<Token> tokens = new ArrayList<Token>();
         
        int errno=0;
           try {
               //FileReader in = new FileReader("input.txt");
               FileInputStream in = new FileInputStream("input.txt");
               //System.out.println("File Read");
                
                //BufferedReader br = new BufferedReader(in);
                int s;
                char b;
                int flag=0;
                String str="";
                String chrstr="";
                int lno=1,te=0;
                
                int a=0;
                while ((s = in.read()) != -1)
                {
                    b=(char)s;
                    //System.out.println(str);
                    
                    if(b=='\"')
                    {
                        if(flag==0)
                        {
                        flag=1;
                        continue;
                        //System.out.println("\"");
                        }
                        else
                        {
                            flag=0;
                            b=' ';
                            Token temp= new Token("String",chrstr,lno);
                            tokens.add(temp);
                            chrstr="";
                        }
                    }
                    if(b=='/')
                    {
                        te=in.read();
                        //in-=1;
                        if((char)te=='/')
                        {
                            while(true)
                            {
                                te=in.read();
                                if((char)te=='\n')
                                    break;
                            }  
                            continue;
                        }
                        else
                        {
                            //vhbjn
                        }
                    }
                    if(flag==1) 
                    {
                        chrstr+=b;
                        continue;
                    }
                    str+=b;
                    if(b=='\n')
                    {
                        lno++;
                        str="";
                        continue;
                    }
                    if(b==' '||b==';'||b=='\t'||b=='('||b==')'||b=='\n'||b=='+'||b=='-'||b=='='||b=='*'||b=='{'||b=='}'||b=='['||b==']'||b=='/')
                    {
                        
                        str=str.substring(0,str.length()-1);
                        //System.out.println(str+" ");
                        
                        
                        if(keywords.contains(str))
                        {
                            Token temp= new Token(str,"Keyword",lno);
                            tokens.add(temp);
                        }
                        else if(str.matches("([A-Z]|[a-z]|_)+([A-Z]|[a-z]|_|[0-9])*\\w*"))
                        {
                            Token temp= new Token(str,"Identifier",lno);
                            tokens.add(temp);
                        }
                        else if(str.matches("(\\d+)|(\\d+.\\d+)"))
                        {
                            Token temp= new Token(str,"Number",lno);
                            tokens.add(temp);
                        }
                        else if(!str.equals(""))
                        {
                            System.err.println("Unidentified token : " + str + " in line number "+lno);
                            errno+=1;
                        }
                        if(b==' '||b==';'||b=='\t'||b=='('||b==')'||b=='\n'||b=='{'||b=='}'||b=='['||b==']')
                        {
                            Token temp= new Token(b+"","Separator",lno);
                            tokens.add(temp);
                        }
                        if(b=='+'||b=='-'||b=='='||b=='*'||b=='/')
                        {
                            Token temp= new Token(b+"","Operator",lno);
                            tokens.add(temp);
                        }
                        
                        
                        str="";
                        //System.out.println("\nSeparator "+b);
                        
                        
                        //continue;
                    }
                    
                    
                }

                if (in != null) {
            
                 in.close();
                 
                 //System.out.println(errno + " errors Found");
                    for(int i=0;i<tokens.size();i++)
                    {
                        //if(tokens.get(i).tokenName.equals("Separator"))
                            //continue;
                        System.out.println(tokens.get(i).lexemeName+"  --->  "+tokens.get(i).tokenName );
                    }
            
            }
                
            } 
           catch (FileNotFoundException ex) 
           {
                Logger.getLogger(Compiler.class.getName()).log(Level.SEVERE, null, ex);
           }
         
       
    }
}

    

