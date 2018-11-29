package gui;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ScriptGUI {

    public static void lala() throws ScriptException, NoSuchMethodException, FileNotFoundException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");

        // Read script
        Invocable funcJS = (Invocable) nashorn;
        nashorn.eval(new FileReader("web" + File.separatorChar + "script.js"));
    }

}
