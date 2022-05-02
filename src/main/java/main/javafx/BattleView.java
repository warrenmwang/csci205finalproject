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

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.Pokemon;

public class BattleView {

    private static final int NAMETEXTBARWIDTH = 150;
    private VBox root;
    private GridPane gridPane;
    private HBox bottomHBox;
    private StackPane topStackPane;
    private String playerPokemonImageURL;
    private String botPokemonImageURL;
    private final String backgroundImageURL = "http://eg.bucknell.edu/~wmw015/code/csci205-final/backgroundforpokemon.jfif";

    private Rectangle playerBaseHp;
    private Rectangle playerHpBar;
    private Rectangle BotBaseHp;
    private Rectangle BotHpBar;
    private Button Move1;
    private Button Move2;
    private Button Move3;
    private Button Move4;
    private Button Switch;
    private Button Attack;
    private Button Forfeit;
    private Button Poke0Btn;
    private Button Poke1Btn;
    private HBox temp1;
    private HBox temp2;
    private HBox temp3;
    private HBox poke01;
    private VBox MoveHolderBox; // a vbox holding a hbox
    private VBox ThreeChoiceHolderBox; // a vbox holding a hbox
    private VBox ChoosePokemonHolderBox;

    private TextArea bottomLeftTextBox; // on the left side of the bottom hbox

    private ImageView playerPokemonImageView;
    private ImageView botPokemonImageView;

    private Pokemon currPokemon;
    private Pokemon botCurrPokemon;
    private Rectangle botNameBackground;
    private Rectangle playerNameBackground;
    private Text playerName;
    private Text botName;


    // -------------- getter methods -------------
    public VBox getRoot(){return root;}
    public Rectangle getPlayerHpBar() {return playerHpBar;}
    public Rectangle getBotHpBar() {return BotHpBar;}
    public Button getMove1(){return Move1;}
    public Button getMove2(){return Move2;}
    public Button getMove3(){return Move3;}
    public Button getMove4(){return Move4;}
    public Button getSwitch(){return Switch;}
    public Button getAttack(){return Attack;}
    public Button getForfeit(){return Forfeit;}
    public Button getPoke0Btn(){return Poke0Btn;}
    public Button getPoke1Btn(){return Poke1Btn;}
    public int getNAMETEXTBARWIDTH(){return NAMETEXTBARWIDTH;}



    // ---------------- setter methods ---------------
    public void setPlayerPokemonImageURL(String newString){this.playerPokemonImageURL = newString; }
    public void setBotPokemonImageURL(String newString){this.botPokemonImageURL = newString; }
    public void setCurrPokemon(Pokemon poke){currPokemon = poke;}
    public void setBotCurrPokemon(Pokemon poke){botCurrPokemon = poke;}



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

        // ----- GridPane within top StackPane ------
        try {
            // initialize sprites
            initPokemonSprites();

            // player's poke name
            playerNameBackground = new Rectangle(NAMETEXTBARWIDTH, 20);
            playerNameBackground.setFill(Color.BEIGE);
            playerName = new Text("PLAYER CURR NAME");
            // player's poke hp
            playerBaseHp = new Rectangle(NAMETEXTBARWIDTH,20);
            playerBaseHp.setFill(Color.GRAY);
            playerHpBar = new Rectangle(NAMETEXTBARWIDTH,20);
            playerHpBar.setFill(Color.RED);
            playerHpBar.setWidth(120); // setting smaller width to make hp less than full

            // bot's poke name
            botNameBackground = new Rectangle(NAMETEXTBARWIDTH, 20);
            botNameBackground.setFill(Color.BEIGE);
            botName = new Text("BOT CURR NAME");
            // bot's poke hp
            BotBaseHp = new Rectangle(NAMETEXTBARWIDTH,20);
            BotBaseHp.setFill(Color.GRAY);
            BotHpBar = new Rectangle(NAMETEXTBARWIDTH,20);
            BotHpBar.setFill(Color.RED);
            BotHpBar.setWidth(120); // setting smaller width to make hp less than full


            gridPane.add(playerNameBackground, 1, 2);
            gridPane.add(playerName, 1, 2); // add name on top of the name background
            GridPane.setValignment(playerNameBackground, VPos.BOTTOM);
            GridPane.setValignment(playerName, VPos.BOTTOM);

            gridPane.add(playerBaseHp,2,4);
            gridPane.add(playerHpBar, 2, 4);
            GridPane.setHalignment(playerBaseHp, HPos.LEFT);
            GridPane.setHalignment(playerHpBar, HPos.LEFT);


            gridPane.add(botNameBackground, 3,2);
            gridPane.add(botName, 3, 2); // add name on top of the name background
            GridPane.setValignment(botNameBackground, VPos.BOTTOM);
            GridPane.setValignment(botName, VPos.BOTTOM);

            gridPane.add(BotBaseHp,2,1);
            gridPane.add(BotHpBar, 2, 1);
            GridPane.setHalignment(BotBaseHp, HPos.RIGHT);
            GridPane.setHalignment(BotHpBar, HPos.RIGHT);
            gridPane.setHgap(10);

            gridPane.add(playerPokemonImageView, 1, 3, 1, 2);
            GridPane.setHalignment(playerPokemonImageView,HPos.CENTER);
            gridPane.add(botPokemonImageView, 3, 1, 1, 1);
        } catch (Exception e) {}

        // -------- Bottom HBox contents (Moves or Choices) --------
        bottomLeftTextBox = new TextArea("Press the buttons on the right to play!");
        bottomHBox.getChildren().add(bottomLeftTextBox);

        // create moves btns, put moves btns into moves holder, put into bottom right stackpane
        Move1 = new Button("123");
        Move2 = new Button("123");
        Move3 = new Button("123");
        Move4 = new Button("123");
        MoveHolderBox = new VBox();
        temp1 = new HBox();
        temp2 = new HBox();
        temp1.getChildren().add(Move1);
        temp1.getChildren().add(Move2);
        temp2.getChildren().add(Move3);
        temp2.getChildren().add(Move4);
        MoveHolderBox.getChildren().add(temp1);
        MoveHolderBox.getChildren().add(temp2);

        // create attack, switch, forfeit btns and put into its holder, put into bottom right stackpane
        Attack = new Button("Attack");
        Switch = new Button("Switch");
        Forfeit = new Button("Forfeit");
        ThreeChoiceHolderBox = new VBox();
        temp3 = new HBox();
        temp3.getChildren().add(Attack);
        temp3.getChildren().add(Switch);
        temp3.getChildren().add(Forfeit);
        ThreeChoiceHolderBox.getChildren().add(temp3);

        //create switch Pokemon button
        ChoosePokemonHolderBox = new VBox();
        Poke0Btn = new Button("0");
        Poke1Btn = new Button("1");

        poke01 = new HBox();
        poke01.getChildren().add(Poke1Btn);
        poke01.getChildren().add(Poke0Btn);

        ChoosePokemonHolderBox.getChildren().add(poke01);


        // initialize the right bottom side to be the 3 choice box
        bottomHBox.getChildren().add(ThreeChoiceHolderBox);

        // ----- putting containers and on screen control into root -----
        topStackPane.getChildren().add(gridPane);
        root.getChildren().add(topStackPane);
        root.getChildren().add(bottomHBox);
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

        // set gridpane's pref size
        gridPane.setPrefSize(1050, 540);

        // set fixed column sizes
        gridPane.getColumnConstraints().add(new ColumnConstraints(200));
        gridPane.getColumnConstraints().add(new ColumnConstraints(200));
        gridPane.getColumnConstraints().add(new ColumnConstraints(200));

        gridPane.getRowConstraints().add(new RowConstraints(100));
        gridPane.getRowConstraints().add(new RowConstraints(100));
        gridPane.getRowConstraints().add(new RowConstraints(100));

        // bottom right box

        bottomHBox.setAlignment(Pos.CENTER_LEFT);
        bottomHBox.setSpacing(10);

        bottomLeftTextBox.setMinSize(600,200);
        bottomLeftTextBox.setMaxSize(600,200);
        bottomLeftTextBox.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));

        // fonts of all buttons
        Attack.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        Switch.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        Forfeit.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        Move1.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        Move2.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        Move3.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        Move4.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        Poke0Btn.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        Poke1Btn.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));

        // Attack, Switch, Forfeit sizes
        int ASFButtonWidth = 150;
        int ASFButtonHeight = 80;
        int ASFButtonHorizontalSpacing = 20;
        int ASFButtonVerticalSpacing = 20;

        //Attack, switch setting
        ThreeChoiceHolderBox.setAlignment(Pos.CENTER);
        ThreeChoiceHolderBox.setSpacing(ASFButtonVerticalSpacing);
        ThreeChoiceHolderBox.setPadding(new Insets(0,10,0,0));

        temp3.setSpacing(ASFButtonHorizontalSpacing);

        Attack.setPrefSize(ASFButtonWidth,ASFButtonHeight);
        Switch.setPrefSize(ASFButtonWidth,ASFButtonHeight);
        Forfeit.setPrefSize(ASFButtonWidth,ASFButtonHeight);


        // 4 moves sizes
        int MovesButtonWidth = 200;
        int MovesButtonHeight = 80;
        int MovesButtonHorizontalSpacing = 20;
        int MovesButtonVerticalSpacing = 20;

        // 4 moves
        MoveHolderBox.setAlignment(Pos.CENTER);
        MoveHolderBox.setSpacing(MovesButtonVerticalSpacing);
        MoveHolderBox.setPadding(new Insets(0,0,0,7));
        temp1.setSpacing(MovesButtonHorizontalSpacing);
        temp2.setSpacing(MovesButtonHorizontalSpacing);
        Move1.setPrefSize(MovesButtonWidth, MovesButtonHeight);
        Move1.setWrapText(true);
        Move2.setPrefSize(MovesButtonWidth, MovesButtonHeight);
        Move2.setWrapText(true);
        Move3.setPrefSize(MovesButtonWidth, MovesButtonHeight);
        Move3.setWrapText(true);
        Move4.setPrefSize(MovesButtonWidth, MovesButtonHeight);
        Move4.setWrapText(true);

        // 2 poke buttons sizes
        int PokeButtonWidth = 200;
        int PokeButtonHeight = 100;
        int PokeButtonHorizontalSpacing = 20;
        int PokeButtonVerticalSpacing = 20;

        // switch pokemon options
        ChoosePokemonHolderBox.setAlignment(Pos.CENTER);
        ChoosePokemonHolderBox.setSpacing(PokeButtonVerticalSpacing);
        ChoosePokemonHolderBox.setPadding(new Insets(0,0,0,5));
        poke01.setSpacing(PokeButtonHorizontalSpacing);
        Poke0Btn.setPrefSize(PokeButtonWidth, PokeButtonHeight);
        Poke1Btn.setPrefSize(PokeButtonWidth, PokeButtonHeight);
    }

    /**
     * Toggles whether to show 3 choices or 4 moves
     */
    public void bottomRightBoxToggleChoices(int ind){
        // remove current box stuff
        bottomHBox.getChildren().remove(1);
        // put new one in
        switch(ind){
            // switch to Moves
            case(0):{
                bottomHBox.getChildren().add(MoveHolderBox);
                break;
            }
            // switch to 3 choices
            case(1):{
                bottomHBox.getChildren().add(ThreeChoiceHolderBox);
                break;
            }
            // switch to Switch pokemon buttons
            case(2):{
                bottomHBox.getChildren().add(ChoosePokemonHolderBox);
                break;
            }
        }
    }


    /**
     * Initialize pokemon sprites
     */
    public void initPokemonSprites(){
        playerPokemonImageView = new ImageView(new Image(playerPokemonImageURL));
        playerPokemonImageView.setFitHeight(200);
        playerPokemonImageView.setFitWidth(200);

        botPokemonImageView = new ImageView(new Image(botPokemonImageURL));
        botPokemonImageView.setFitWidth(250);
        botPokemonImageView.setFitHeight(250);
    }

    /**
     * Function to update pokemon sprites on battlescene
     */
    public void updatePokemonSprites(){
        playerPokemonImageView.setImage(new Image(playerPokemonImageURL));
        botPokemonImageView.setImage(new Image(botPokemonImageURL));
        playerName.setText(currPokemon.getName());
        botName.setText(botCurrPokemon.getName());
    }

    // guicontroller will call this box to update the 4 moves buttons with the names
    //   of the current pokemon's moves
    public void updateMovesBox(){
        Move1.setText(currPokemon.getMove(0));
        Move2.setText(currPokemon.getMove(1));
        Move3.setText(currPokemon.getMove(2));
        Move4.setText(currPokemon.getMove(3));
    }

    /**
     * Updates the content in the bottom left text box with the given input
     * @param content
     */
    public void updateBottomLeftTextBox(String content){
        bottomLeftTextBox.setText(content);
        bottomLeftTextBox.setScrollTop(Double.MAX_VALUE);
    }

    /**
     * Update ChoosePokemon buttons to display alive Pokemon
     * @param Poke0Name
     * @param isalive0
     * @param Poke1Name
     * @param isalive1
     */
    public void updateSwitchPoke(String Poke0Name,boolean isalive0,String Poke1Name,boolean isalive1){
        if(isalive0){Poke0Btn.setText(Poke0Name);}else{Poke0Btn.setText("DEAD");}
        if(isalive1){Poke1Btn.setText(Poke1Name);}else{Poke1Btn.setText("DEAD");}
    }

}