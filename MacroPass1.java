import java.util.*;
import java.io.*;

public class MacroPass1{
    public static void main(String[] args){
        BufferedReader br;
        String line;
        String token;
        String arg;
        
        String[] MNT=new String[20];
        String[] MDT=new String[50];
        String[] ALA=new String[20];
        
        int macroCount=0;
        int argCount=0;
        boolean insideMacro = false;
        
        try{
        br=new BufferedReader(new FileReader("input.txt"));
        PrintWriter mntFile=new PrintWriter("mnt.txt");
        PrintWriter mdtFile=new PrintWriter("mdt.txt");
        PrintWriter alaFile=new PrintWriter("ala.txt");
        
        
        while((line = br.readLine())!=null){
            String[] parts = line.trim().split("[,\\s]+");
            token = parts[0];
            
            if(token.equalsIgnoreCase("MACRO")){
                insideMacro = true;
                argCount=0;
                
                line=br.readLine();
                String[] macroParts=line.trim().split("[,\\s]+");
                String macroName=macroParts[0];
                
                MNT[macroCount]=macroName;
                mntFile.println(macroName+"\tMDT#"+macroCount);
                
                for(int i=1;i<macroParts.length;i++){
                    arg=macroParts[i];
                    if(arg.startsWith("&")){
                        ALA[argCount]=arg;
                        alaFile.println(arg);
                        argCount++;
                    }
                }
                MDT[macroCount]=macroName;
                mdtFile.println(macroName);
                continue;
            }
            if(insideMacro){
                if(line.equalsIgnoreCase("MEND")){
                    insideMacro=false;
                    mdtFile.println("MEND");
                    macroCount++;
                    continue;
                }
            
            String[] bodypart=line.trim().split("[,\\s]+");
            for(int i=0;i<bodypart.length;i++){
                String bodyToken=bodypart[i];
                boolean replaced = false;
                
                for(int j=0;j<argCount;j++){
                    if(bodyToken.equalsIgnoreCase(ALA[j])){
                        mdtFile.print("#"+j+" ");
                        replaced = true;
                        break;
                    }
                }
                if(!replaced){
                    mdtFile.print(bodyToken+" ");
                }
               
            }
             mdtFile.println();
        }
        }
        br.close();
        mntFile.close();
        mdtFile.close();
        alaFile.close();
        System.out.println("pass1 executed succesfully");
        }
        catch(Exception e){
            System.out.println("Error"+e);
        }
    }
}
