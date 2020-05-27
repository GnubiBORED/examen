package sample;
import javafx.application.Platform;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.control.*;

import javafx.scene.layout.VBox;

import javafx.stage.*;

import java.io.*;
import java.util.*;
import sample.conexionSqlite;


public class Controller {
    private File Fichero;

    private final List<String> languageCodes = new ArrayList<>();
    @FXML
    private ChoiceBox tolng;
    @FXML
    private ChoiceBox fromlng;


    @FXML
    private MenuItem exit;

    @FXML
    private MenuItem nuevo;

    @FXML
    private MenuItem cut;

    @FXML
    private MenuItem saveas;

    @FXML
    private MenuItem save;

    @FXML
    private MenuItem about;

    @FXML
    private TextArea texteditor;

    @FXML
    private MenuItem paste;

    @FXML
    private MenuItem copy;

    @FXML
    private MenuItem open;

    @FXML
    private VBox VBoxText;
    @FXML
    private ChoiceBox seleccionBox;


    @FXML
    void onClickExit(ActionEvent event) {
        Platform.exit();

    }

    @FXML
    private void OnClickCopy() {
        texteditor.copy();

    }

    @FXML
    private void onClickPaste() {
        texteditor.paste();
    }

    @FXML
    private void onClickCut() {
        texteditor.cut();
    }

    @FXML
    private void onClickNew() {
        texteditor.clear();

    }

    @FXML
    private void setFile(File file) {
        this.Fichero = file;

    }

    @FXML
    private void onClickOpen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open resource File");
        File Fichero = fileChooser.showOpenDialog(VBoxText.getScene().getWindow());
        setFile(Fichero);
        openFile(Fichero);

    }

    @FXML
    public void onClickSave() {
        save();
    }

    private void save() {
        SaveFile(texteditor.getText(), this.Fichero);
    }

    @FXML
    public void onClickSaveAs() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open resource File");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File absolutePath = fileChooser.showSaveDialog(VBoxText.getScene().getWindow());
        setFile(absolutePath);
        save();


    }

    private void SaveFile(String content, File file) {
        try {
            FileWriter fileWriter;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException | NullPointerException ex) {
            String string = "";

        }
    }

    @FXML
    public void initialize() {
        this.fromlng.getSelectionModel().selectFirst();
        this.fromlng.getSelectionModel().selectLast();

        Hashtable<String, String> my_dict = new Hashtable<String, String>();
        for (Locale locale : Locale.getAvailableLocales()) {

            if (locale.getDisplayName().contains("(")) {
                this.languageCodes.add(locale.getDisplayCountry() + "// " + locale.getLanguage());

            } else if (!locale.getDisplayName().contains("(")) {
                this.languageCodes.add(locale.getDisplayLanguage() + "// " + locale.getLanguage());

            }
            my_dict.put(locale.getLanguage(), locale.getDisplayName());

        }


        ObservableList<String> oListStavaka = FXCollections.observableArrayList(languageCodes);
        oListStavaka.remove(0);
        tolng.setItems(oListStavaka);
        fromlng.setItems(oListStavaka);


    }


    private void openFile(File filepath) {
        Utils utils = new Utils();

        texteditor.setText(utils.readFile(filepath));


    }

    @FXML
    public void onClickTranslate() {
        String fromlng = "null";
        String tolng = "null";
        try {
            fromlng = this.fromlng.getValue().toString();
            tolng = this.tolng.getValue().toString();
        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerta");
            alert.setHeaderText("ERROR: ");
            alert.setContentText("Tienes que seleccionar idiomas, no puedes dejar sin elegir ninguno de las 2 cajas");
            alert.show();


        }
        if (!(texteditor.getText().length() > 0)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerta");
            alert.setHeaderText("ERROR: ");
            alert.setContentText("Tienes que introducir algun texto que traducir, no puede estar vacio");
            alert.show();

        }
        String[] code_from = fromlng.split("//");
        String code_from_code = code_from[1];
        String[] code_to = tolng.split("//");
        String code_to_code = code_to[1];
        code_from_code = code_from_code.replaceAll("\\s+", "");

        code_to_code = code_to_code.replaceAll("\\s+", "");


        GoogleTranslate translator = new GoogleTranslate("AIzaSyAZQlsl9md2qnP4QdpvBgnLcp4tuDFlXcM");
        String text = translator.translte(texteditor.getText(), code_from_code, code_to_code);
        texteditor.setText(text);
    }

    @FXML
    public void onClickTema1() {
        removeallCss();
        if (!this.texteditor.getScene().getStylesheets().contains("/css/Tema1.css"))
            this.texteditor.getScene().getStylesheets().add("/css/Tema1.css");
    }

    @FXML
    public void onClickTema2() {
        removeallCss();
        if (!this.texteditor.getScene().getStylesheets().contains("/css/Tema1.css"))
            this.texteditor.getScene().getStylesheets().add("/css/Tema2.css");
    }

    @FXML
    public void onClickTema3() {
        removeallCss();
        if (!this.texteditor.getScene().getStylesheets().contains("/css/Tema1.css"))
            this.texteditor.getScene().getStylesheets().add("/css/Tema3.css");
    }

    @FXML
    public void onClickTema4() {
        removeallCss();
        if (!this.texteditor.getScene().getStylesheets().contains("/css/Tema1.css"))
            this.texteditor.getScene().getStylesheets().add("/css/Tema4.css");
    }

    private void removeallCss() {
        this.texteditor.getScene().getStylesheets().remove("/css/Tema1.css");
        this.texteditor.getScene().getStylesheets().remove("/css/Tema2.css");
        this.texteditor.getScene().getStylesheets().remove("/css/Tema3.css");
        this.texteditor.getScene().getStylesheets().remove("/css/Tema4.css");
        ///css/Test1.css
        this.texteditor.getScene().getStylesheets().remove("/css/Test1.css");


    }



    //Examen
    @FXML
    /**

     *
     @see conexionSqlite clase que maneja todas las funciones de la base de datos

     */
    private void onClickAñadir() {
        String texto = this.texteditor.getText();
        conexionSqlite baseDatos = new conexionSqlite();
        baseDatos.INSERTAR(texto);

    }

    @FXML
    /**

     *

     @param id id de el texto que vamos a borrar en la base de datos
     @see conexionSqlite clase que maneja todas las funciones de la base de datos
     */
    private void onClickEliminar(int id) {
        conexionSqlite baseDatos = new conexionSqlite();
        baseDatos.Borrar(id);

    }

    @FXML
    /**

     *
        @param text texto guardado en la textarea para guardar a la base de datos
        @param id id de el texto que vamos a CAMBIAR  en la base de datos
        @see conexionSqlite clase que maneja todas las funciones de la base de datos
     */
    private void onClickUpdate(String text, String id) {

        String idvalue = "";
        try {
            idvalue = this.seleccionBox.getValue().toString();

        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerta");
            alert.setHeaderText("ERROR: ");
            alert.setContentText("Tienes que seleccionar un id a borra, no puedes dejar sin elegir ninguno ");
            alert.show();
            String texto = this.texteditor.getText();
            conexionSqlite baseDatos = new conexionSqlite();
            baseDatos.Update(texto, idvalue);

        }


    }

}
