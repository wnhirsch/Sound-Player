package gui;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class webViewGUI extends Application {

    @Override
    public void start(final Stage stage){
        // Inicializa os objetos motores da página em HTML
        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();

        // Define diretório dos arquivos necessarios
        String dirURL = System.getProperty("user.dir");
        String iconURL = dirURL + "/web/icon.png";
        String htmlURL = dirURL + "/web/index.html";

        // Informa/Inicializa no HTML as classes Java que ele pode chamar
        webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("app", new SoundPlayer(stage));
            }
        });

        // Inicializa uma janela para visualizarmos o conteúdo
        VBox root = new VBox();
        root.setPadding(new Insets(5));
        root.setSpacing(5);
        root.getChildren().addAll(browser);

        Scene scene = new Scene(root);
        stage.setTitle("Wellington's Amazing Player");
        stage.setScene(scene);
        stage.setWidth(600);
        stage.setHeight(400);
        stage.getIcons().add(new Image("file:" + iconURL));
        stage.show();

        // Informa o diretório do código HTML e tenta abri-lo
        try {
            File file = new File(htmlURL);
            URL url = file.toURI().toURL();
            webEngine.load(url.toString());
        }
        // Caso não consiga, informa erro e encerra o programa
        catch(MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}

