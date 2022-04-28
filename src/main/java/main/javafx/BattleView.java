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
    private StackPane topStackPane;
    private String playerPokemonImageURL;
    private String botPokemonImageURL;
    private final String backgroundImageURL = "http://eg.bucknell.edu/~wmw015/code/backgroundforpokemon.jfif";

    private Button SwitchSceneBtn;
    private Button Move1;
    private Button Move2;
    private Button Move3;
    private Button Move4;
    private Button Switch;
    private Button Attack;
    private Button Forfeit;
    private StackPane bottomRightChoiceStackPane; // holds the 2 vbox's to swap places when necessary

    private VBox MoveHolderBox; // a vbox holding a hbox
    private VBox ChoiceHolderBox; // a vbox holding a hbox

    private Label bottomLeftTextBox; // on the left side of the bottom hbox


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
     * Top half is a stackpane with a gridbox
     * Bottom half is a HBox
     */
    public void initSceneGraph() {
        // --------- main containers and root ----------
        root = new VBox();
        gridPane = new GridPane();
        bottomHBox = new HBox();
        topStackPane = new StackPane();
        bottomRightChoiceStackPane = new StackPane();

        // ----- GridPane within top StackPane ------
        try {
            // add pictures to gridpane
//            Image playerPokemonImage = new Image(playerPokemonImageURL);
//            ImageView playerPokemonImageView = new ImageView(playerPokemonImage);
//            Image botPokemonImage = new Image(botPokemonImageURL);
//            ImageView botPokemonImageView = new ImageView(botPokemonImage);
//
//            gridPane.add(new Text("Player Pokemon HEALTHBAR"), 1, 0);
//            gridPane.add(new Text("Bot Pokemon HEALTHBAR"), 1, 3);
//
//            gridPane.add(playerPokemonImageView, 0, 0, 1, 1);
//            gridPane.add(botPokemonImageView, 2, 0, 1, 2);
////
            Image playerPokemonImage = new Image(playerPokemonImageURL);
            ImageView playerPokemonImageView = new ImageView(playerPokemonImage);
            playerPokemonImageView.setFitHeight(200);
            playerPokemonImageView.setFitWidth(200);
            Image botPokemonImage = new Image(botPokemonImageURL);
            ImageView botPokemonImageView = new ImageView(botPokemonImage);
            botPokemonImageView.setFitWidth(250);
            botPokemonImageView.setFitHeight(250);

            playerBaseHp = new Rectangle(150,20);
            playerBaseHp.setFill(Color.GRAY);
            playerHpBar = new Rectangle(150,20);
            playerHpBar.setFill(Color.RED);
            playerHpBar.setWidth(120);


            BotBaseHp = new Rectangle(150,20);
            BotBaseHp.setFill(Color.GRAY);
            BotHpBar = new Rectangle(150,20);
            BotHpBar.setFill(Color.RED);
            BotHpBar.setWidth(120);






            gridPane.add(playerBaseHp,2,4);
            gridPane.add(playerHpBar, 2, 4);
            GridPane.setHalignment(playerBaseHp, HPos.LEFT);
            GridPane.setHalignment(playerHpBar, HPos.LEFT);

            gridPane.add(BotBaseHp,2,1);
            gridPane.add(BotHpBar, 2, 1);
            GridPane.setHalignment(BotBaseHp, HPos.RIGHT);
            GridPane.setHalignment(BotHpBar, HPos.RIGHT);


            //gridPane.add(new Text("Bot Pokemon HEALTHBAR"), 2, 1);

            gridPane.add(playerPokemonImageView, 1, 3, 1, 2);
            GridPane.setHalignment(playerPokemonImageView,HPos.CENTER);


            gridPane.add(botPokemonImageView, 3, 1, 1, 1);

            //gridPane.setAlignment(Pos.BASELINE_RIGHT);




        } catch (Exception e) {

        }

        // -------- Bottom HBox contents (Moves or Choices) --------
        bottomLeftTextBox = new Label("BOTTOM LEFT TEXT BOX");
        bottomHBox.getChildren().add(bottomLeftTextBox);

        // create moves btns, put moves btns into moves holder, put into bottom right stackpane
        Move1 = new Button("123");
        Move2 = new Button("123");
        Move3 = new Button("123");
        Move4 = new Button("123");
        MoveHolderBox = new VBox();
        HBox temp1 = new HBox();
        HBox temp2 = new HBox();
        temp1.getChildren().add(Move1);
        temp1.getChildren().add(Move2);
        temp2.getChildren().add(Move3);
        temp2.getChildren().add(Move4);
        MoveHolderBox.getChildren().add(temp1);
        MoveHolderBox.getChildren().add(temp2);
        bottomRightChoiceStackPane.getChildren().add(MoveHolderBox);

        // create attack, switch, forfeit btns and put into its holder, put into bottom right stackpane
        Attack = new Button("Attack");
        Switch = new Button("Switch");
        Forfeit = new Button("Forfeit");
        ChoiceHolderBox = new VBox();
        HBox temp3 = new HBox();
        HBox temp4 = new HBox();
        temp3.getChildren().add(Attack);
        temp3.getChildren().add(Switch);
        temp4.getChildren().add(Forfeit);
        ChoiceHolderBox.getChildren().add(temp3);
        ChoiceHolderBox.getChildren().add(temp4);
        // bottom right box of the choiceholderbox will be blank
        bottomRightChoiceStackPane.getChildren().add(ChoiceHolderBox);
        // add stackpane with both 4moves and 3choice vboxes to bottomhbox, will be in btmright corner
        bottomHBox.getChildren().add(bottomRightChoiceStackPane);

        // ----- putting containers and on screen control into root -----
        topStackPane.getChildren().add(gridPane);
        root.getChildren().add(topStackPane);
        root.getChildren().add(bottomHBox);
        // TODO remove switch scenebtn below
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

        topStackPane.setBackground(bGround);

        // set stackPane's pref size just right for background image
        topStackPane.setPrefSize(1050, 540);

        // ------------- GridPane --------------

        // TODO style the gridpane so pokemone are in the right places

        // set gridpane's pref size
        gridPane.setPrefSize(1050, 540);

        // set fixed column sizes
        gridPane.getColumnConstraints().add(new ColumnConstraints(200));
        gridPane.getColumnConstraints().add(new ColumnConstraints(200));
        gridPane.getColumnConstraints().add(new ColumnConstraints(200));

        gridPane.getRowConstraints().add(new RowConstraints(100));
        gridPane.getRowConstraints().add(new RowConstraints(100));
        gridPane.getRowConstraints().add(new RowConstraints(100));

        // ---------- BOTTOM RIGHT STACK PANE -------
        bottomRightChoiceStackPane.setPrefSize(200,200);


        // -------- Bottom HBox -------------
        bottomHBox.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        bottomHBox.setStyle("-fx-background-image: url('http://eg.bucknell.edu/~wmw015/code/csci205-final/bottomhboximage.jpg');" +
                "-fx-background-repeat: stretch;" +
                "-fx-background-size: 1000 700;" +
                "-fx-background-position: center center;");
    }

}