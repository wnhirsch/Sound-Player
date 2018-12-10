package cli;
//import java.io.File;
import java.io.*;
import java.io.File;

import java.nio.charset.Charset;
//import controls.ControlManager;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import commands.AddNoteCommand;
import commands.CommandInterface;

public class cli {
	
	private static String inputKey;
	private cliToPlayer myPlayer;

	static String readFile(String path, Charset encoding)throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);	
	}

	
	public static void main(String[] args) throws IOException{
		
		cliToPlayer myPlayer = new cliToPlayer();
		String buffer;
   		
		buffer= "";
		
		if(args.length ==0) {
			buffer= "ABCDEFG";
				

		}else {
			String filename= args[0];

			buffer = readFile(filename, Charset.defaultCharset());
			
			//myPlayer.playSong(buffer);	
			
		}
        
        System.out.println(buffer);
        Scanner sc1 = new Scanner(System.in);
        while(true){
	        inputKey= sc1.next();
	        if(inputKey.equalsIgnoreCase("q")) {
	           break;
	        }
	        callFromMenu(myPlayer, inputKey, buffer);
	       // String val=Integer.parseInt(inputKey);
	        System.out.println("Token: "+inputKey);
	    }
        
        System.out.println("THE END.");

        
    }



	private static void callFromMenu(cliToPlayer myPlayer, String str, String buffer) {
		// TODO Auto-generated method stub
		switch (str) {
		case "start":
			System.out.println("calling start");
			myPlayer.playSong(buffer);
			break;
		case "pause":
			System.out.println("calling pause");
			myPlayer.pauseSong();
			break;
		case "resume":
			System.out.println("calling resume");
			myPlayer.resumeSong();
			break;
		case "stop":
			System.out.println("calling stop");
			myPlayer.stopSong();
			break;
			
		case "saveFile":
			System.out.println("callling saveFile");
			myPlayer.saveFile(buffer);
		default:
			System.out.println("Unknown command");
			break;
		}
	}

	
}
