/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring2022
 * Instructor: Brian King
 * Section: 1 - 10 am
 *
 * Name: Warren Wang
 * Date: 01/26/2022
 *
 * Lab / Assignment:
 *
 * Description:
 *
 * *****************************************/


package main.javafx;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class BattleView {

    private VBox root;
    private GridPane gridPane;
    private HBox bottomHBox;
    private StackPane stackPane;
    private String playerPokemonImageURL;
    private String botPokemonImageURL;
    private final String backgroundImageURL = "http://eg.bucknell.edu/~wmw015/code/backgroundforpokemon.jfif";

    private Button SwitchSceneBtn;
    private Button Move1;
    private Button Move2;
    private Button Move3;
    private Button Move4;

    private Label textBox;


    // -------------- getter methods -------------
    public VBox getRoot(){return root;}
    public Button getSwitchSceneBtn(){ return SwitchSceneBtn; }
    public String getPlayerPokemonImageURL(){return this.playerPokemonImageURL;}
    public String getBotPokemonImageURL(){return this.botPokemonImageURL;}

    // ---------------- setter methods ---------------
    public void setPlayerPokemonImageURL(String newString){this.playerPokemonImageURL = newString; }
    public void setBotPokemonImageURL(String newString){this.botPokemonImageURL = newString; }

    /**
     * Constructor
     * @param playerStartPokeURL
     * @param botStartPokeURL
     */
    public BattleView(String playerStartPokeURL, String botStartPokeURL) {
        this.playerPokemonImageURL = playerStartPokeURL;
        this.botPokemonImageURL = botStartPokeURL;

        initSceneGraph();
        initSceneStyling();
    }

    /**
     * Creating stuff in scene
     */
    public void initSceneGraph() {
        // --------- containers, on screen control, and root ----------
        root = new VBox();
        gridPane = new GridPane();
        bottomHBox = new HBox();
        stackPane = new StackPane();

        // ----- images ------
        try {
            // add pictures to gridpane
//            FileInputStream input = new FileInputStream(url1);
            Image playerPokemonImage = new Image(playerPokemonImageURL);
            ImageView playerPokemonImageView = new ImageView(playerPokemonImage);
            Image botPokemonImage = new Image(botPokemonImageURL);
            ImageView botPokemonImageView = new ImageView(botPokemonImage);

            gridPane.add(new Text("Player Pokemon HEALTHBAR"), 1, 0);
            gridPane.add(new Text("Bot Pokemon HEALTHBAR"), 1, 3);

            gridPane.add(playerPokemonImageView, 0, 0, 1, 1);
            gridPane.add(botPokemonImageView, 2, 0, 1, 2);
//
        } catch (Exception e) {

        }

        // ----- putting containers and on screen control into root -----

        stackPane.getChildren().add(gridPane);

        root.getChildren().add(stackPane);
        root.getChildren().add(bottomHBox);
        SwitchSceneBtn = new Button("switch scene");
        root.getChildren().add(SwitchSceneBtn);
    }

    /**
     * Styling stuff in scene
     */
    public void initSceneStyling() {
        // -------------------- root --------------------
        Image img = new Image(backgroundImageURL);

        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        Background bGround = new Background(bImg);

        // set vbox size for entire graphical view
        root.setPrefSize(1050, 700);

        // ------------ Stack Pane containing gridpane ------------

        stackPane.setBackground(bGround);

        // set stackPane's pref size just right for background image
        stackPane.setPrefSize(1050, 540);

        // ------------- GridPane --------------

        // set gridpane's pref size
        gridPane.setPrefSize(1050, 540);

        // set fixed column sizes
        gridPane.getColumnConstraints().add(new ColumnConstraints(200));
        gridPane.getColumnConstraints().add(new ColumnConstraints(200));
        gridPane.getColumnConstraints().add(new ColumnConstraints(200));

        gridPane.getRowConstraints().add(new RowConstraints(100));
        gridPane.getRowConstraints().add(new RowConstraints(100));
        gridPane.getRowConstraints().add(new RowConstraints(100));



    }

}