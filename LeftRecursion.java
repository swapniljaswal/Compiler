/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Swapnil
 */
public class LeftRecursion {
    
    public static void main(String[] args) {
        
        List<Character> terminals = new ArrayList<>();
        List<Character> non_terminals = new ArrayList<>();
        String[] productions = new String[50];
        char startSymbol ;
        int production_counter = 0;
        int flag=1;
        char rec_aplha='P';
        
        try{
            
            FileInputStream in = new FileInputStream("source.txt");
            int s;
            char c;
            
            startSymbol=(char)in.read();
            in.read();
            while((s = in.read())!='\n')
            {
                c = (char)s;
                if(c == ' ')
                    continue;
                else
                    non_terminals.add(c);
                    
            }
            
            while((s = in.read())!='\n')
            {
                c = (char)s;
                if(c == ' ')
                    continue;
                else
                    terminals.add(c);
                    
            }
            System.out.println(startSymbol);
            System.out.println(non_terminals);
            System.out.println(terminals);
            
            while((s = in.read())!=-1)
            {
                c = (char)s;
                if(c=='\n'||c=='\r')
                {
                    production_counter++;
                    flag=1;
                    continue;
                }
                else if(c=='|')
                {
                    production_counter++;
                    //flag=1;
                    productions[production_counter]=productions[production_counter-1].substring(0, 3);
                    continue;
                }
                if(flag==1)
                {
                    productions[production_counter]=String.valueOf(c);
                    flag=0;
                }
                else
                    productions[production_counter]+=c;
                
                
            }
            System.out.println(Arrays.toString(productions));
            
            for(int i=0;i<=production_counter;i++)
            {
                System.out.println("compiler.LeftRecursion.main()");
                if(productions[i].substring(0, 1).equals(productions[i].substring(3, 4)))
                {
                    System.out.println("Left recurrsion detected in "+"production "+(i+1));
                    String alpha = productions[i].substring(4);
                    productions[++production_counter]=rec_aplha+"->"+alpha+rec_aplha;
                    productions[++production_counter]=rec_aplha+"->ε";
                    //System.out.println("Hi 1");
                    for(int j=0;j<(production_counter-1);j++)
                    {
                        if((productions[j].substring(0, 1).equals(productions[i].substring(0, 1)))&&(productions[j].substring(0,1)!=productions[j].substring(3, 4)))
                        {
                            productions[j]+=rec_aplha;
                            //System.out.println("Hi 2");
                            
                        }
                        
                    }
                    //System.out.println("Hi 3");
                    for(int j=i;j<production_counter;j++)
                        productions[j]=productions[j+1];
                    productions[production_counter--]=null;
                    rec_aplha++;
                    //System.arraycopy(productions,i+1,productions,i,productions.length-1-i);
//                    String newProduction = productions[i].substring(0,3) + rec_aplha;
//                    for(int j = 0; j<=production_counter;j++)
//                    {
//                        if(productions[j].substring(0, 1).equals((productions[i].substring(0, 1))))
//                                {
//                                    if(productions[i].substring(0, 1).equals(productions[j].substring(0, 1))&&productions[j].substring(3,4)!=productions[j].substring(0,1))
//                                    {
//                                        productions[j]+=rec_aplha;
//                                    }
//                                    
//                                    productions[++production_counter]=rec_aplha+"->"+productions[j].substring(4)+rec_aplha;
//                                    productions[++production_counter]=rec_aplha+"->"+"e";
//                                    //rec_aplha++;
//                                }
//                        
//                    }
//                    rec_aplha++;
                    
                    
                }
            }
            
//            for(int i=0;i<non_terminals.size();i++)
//            {
//                for(int j=0;j<(i-1);j++)
//                {
//                    for(int k=0;k<=production_counter;k++)
//                    {
//                        if(productions[k].substring(0,1).equals(non_terminals.get(i))&&productions[k].substring(3, 4).equals(non_terminals.get(j)))
//                        {
//                            for(int l=0;l<=production_counter;l++)
//                            {
//                                if(productions[k].substring(0,1).equals(non_terminals.get(j)))
//                                {
//                                    String temp1=productions[l].substring(3);
//                                    String temp2=productions[k].substring(0,3);
//                                    temp2 = temp2 + temp1;
//                                    temp2 = temp2 + productions[k].substring(4);
//                                    productions[++production_counter]=temp2;
//                                    productions[j]="";
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            System.err.println("HI");
            System.out.println("New set of production rules is : ");
            for(int j=0;j<=production_counter;j++)
            {
                System.out.println(productions[j]);
            }
            
        }
        
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
}
