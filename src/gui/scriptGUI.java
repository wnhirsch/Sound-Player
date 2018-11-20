package gui;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class scriptGUI {

    public static void lala() throws ScriptException, NoSuchMethodException, FileNotFoundException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");

        // Read script
        Invocable funcJS = (Invocable) nashorn;
        nashorn.eval(new FileReader("web\\script.js"));

//        Object result = 0;
//        while (true){
//            result = funcJS.invokeFunction("inc", result);
//            System.out.println(result);
//        }


    }

}
