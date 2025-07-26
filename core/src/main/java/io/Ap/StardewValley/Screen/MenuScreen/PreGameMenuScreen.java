package io.Ap.StardewValley.Screen.MenuScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.Ap.StardewValley.StardewValley;


public class PreGameMenuScreen implements Screen {
    private final Stage stage;
    private final Skin skin;
    private final TextButton backButton, startButton;
    private final Image backgroundImage, characterBackground;
    private final TextureRegion[][] shirtSheet, hairSheet, pantSheet;

    private String hairColor, pantColor;
    private int pantIndex, shirtIndex, hairIndex;

    private final Image bodyImage, handImage;
    private Stack characterStack;

    public PreGameMenuScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = StardewValley.getSkin();

        backgroundImage = new Image(new Texture(Gdx.files.internal("etc/menu/background_start.png")));
        characterBackground = new Image(new Texture(Gdx.files.internal("etc/menu/daybg.png")));

        Texture bodySheetTexture = new Texture("player/body_boy.png");
        TextureRegion bodyRegion = new TextureRegion(bodySheetTexture, 0, 0, 16, 32);
        Texture handSheetTexture = new Texture("player/hand_01.png");
        TextureRegion handRegion = new TextureRegion(handSheetTexture, 0, 0, 16, 32);

        bodyImage = new Image(bodyRegion);
        handImage = new Image(handRegion);

        shirtSheet = TextureRegion.split(new Texture("player/clothes/shirts.png"), 8, 8);
        hairSheet = TextureRegion.split(new Texture("player/clothes/hairstyles.png"), 16, 32);
        pantSheet = TextureRegion.split(new Texture("player/clothes/pants.png"), 16, 32);

        backButton = new TextButton("Back", skin);
        startButton = new TextButton("Start", skin);

        hairColor = "Black";
        pantColor = "Black";
        pantIndex = 0;
        shirtIndex = 0;
        hairIndex = 0;
    }

    @Override
    public void show() {
        Stack stack = new Stack();
        stack.setFillParent(true);
        stage.addActor(stack);
        stack.add(backgroundImage);

        Window window = new Window("", skin);
        Label titleLabel = new Label("Pre Game", skin, "Bold");
        titleLabel.setAlignment(Align.center);
        window.getTitleTable().clear();
        window.getTitleTable().add(titleLabel).expandX().center().padTop(5).padBottom(10);

        window.setMovable(false);
        window.setResizable(false);
        window.setSize(1200, 800);
        window.setPosition(
                (stage.getWidth() - window.getWidth()) / 2,
                (stage.getHeight() - window.getHeight()) / 2
        );

        Table contentTable = new Table();

        //  left column(character):
        Table leftColumn = new Table();
        leftColumn.pad(20);
        Group characterGroup = getCharacterGroup();

        characterStack = new Stack();
        characterStack.add(characterBackground);
        characterStack.add(characterGroup);

        leftColumn.add(characterStack).size(
                characterBackground.getWidth() * 1.5f,
                characterBackground.getHeight() * 1.5f
        ).padBottom(30);

        leftColumn.row();

        //  right column (button Hair, Shirt, Pant)
        Table rightColumn = new Table();
        rightColumn.pad(20);

        SelectorGroup hairSelector = new SelectorGroup("Hair");
        SelectorGroup shirtSelector = new SelectorGroup("Shirt");
        SelectorGroup pantSelector = new SelectorGroup("Pant");

        rightColumn.add(hairSelector.table).pad(10).row();
        rightColumn.add(shirtSelector.table).pad(10).row();
        rightColumn.add(pantSelector.table).pad(10).row();

        contentTable.add(leftColumn).top().pad(10);
        contentTable.add(rightColumn).top().pad(10);

        String[] colors = {
                "Black", "Brown", "Blonde", "Red", "Blue", "Cyan", "Green", "Magenta",
                "Orange", "Pink", "Yellow", "Gray", "White"
        };

        SelectBox<String> hairColorSelectBox = new SelectBox<>(skin);
        hairColorSelectBox.setItems(colors);

        SelectBox<String> pantColorSelectBox = new SelectBox<>(skin);
        pantColorSelectBox.setItems(colors);

        Table colorSelectTable = new Table();
        colorSelectTable.align(Align.center);

        colorSelectTable.add(new Label("Hair Color:", skin)).padRight(10);
        colorSelectTable.add(hairColorSelectBox).width(200).row();
        colorSelectTable.add(new Label("Pant Color:", skin)).padRight(10).padTop(40);
        colorSelectTable.add(pantColorSelectBox).width(200).padTop(40);

        contentTable.row();
        contentTable.add(colorSelectTable).padBottom(20).row();

        Table buttonRow = new Table();
        buttonRow.add(startButton).width(200).height(90).pad(10);
        buttonRow.add(backButton).width(200).height(90).pad(10);


        window.add(contentTable).expand().center().row();
        window.add(buttonRow).padTop(20);
        stage.addActor(window);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //StardewValley.getGame().setScreen(new StartMenuScreen());
            }
        });

        hairColorSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                hairColor = hairColorSelectBox.getSelected();
                refreshCharacter();
            }
        });

        pantColorSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pantColor = pantColorSelectBox.getSelected();
                refreshCharacter();
            }
        });

        hairSelector.leftBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hairIndex--;
                if (hairIndex < 0) hairIndex = hairSheet.length - 1;
                refreshCharacter();
            }
        });

        hairSelector.rightBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hairIndex++;
                if (hairIndex >= hairSheet.length) hairIndex = 0;
                refreshCharacter();
            }
        });

        shirtSelector.leftBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                shirtIndex--;
                if (shirtIndex < 0) shirtIndex = shirtSheet[0].length - 1;
                refreshCharacter();
            }
        });

        shirtSelector.rightBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                shirtIndex++;
                if (shirtIndex >= shirtSheet[0].length) shirtIndex = 0;
                refreshCharacter();
            }
        });

        pantSelector.leftBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pantIndex--;
                if (pantIndex < 0) pantIndex = pantSheet[0].length - 1;
                refreshCharacter();
            }
        });

        pantSelector.rightBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pantIndex++;
                if (pantIndex >= pantSheet[0].length) pantIndex = 0;
                refreshCharacter();
            }
        });

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }

    private Group getCharacterGroup() {
        Group characterGroup = new Group();

        float scale = 8f;

        int x = 32;
        int y = 32;

        bodyImage.setSize(16 * scale, 32 * scale);
        bodyImage.setPosition(x, y);
        characterGroup.addActor(bodyImage);

        handImage.setSize(16 * scale, 32 * scale);
        handImage.setPosition(x, y);
        characterGroup.addActor(handImage);

        // selected:
        Image hairImage = new Image(hairSheet[0][0]);
        hairImage.setSize(16 * scale, 32 * scale);
        hairImage.setPosition(x, y - 1 * scale);
        hairImage.setColor(getColor(hairColor));
        characterGroup.addActor(hairImage);

        Image pantImage = new Image(pantSheet[0][0]);
        pantImage.setSize(16 * scale, 32 * scale);
        pantImage.setPosition(x, y);
        pantImage.setColor(getColor(pantColor));
        characterGroup.addActor(pantImage);

        Image shirtImage = new Image(shirtSheet[0][0]);
        shirtImage.setSize(8 * scale, 8 * scale);
        shirtImage.setPosition(x + 4 * scale, y + 9 * scale);
        characterGroup.addActor(shirtImage);
        return characterGroup;
    }

    public Color getColor(String hairColor) {
        return switch (hairColor) {
            case "Black" -> Color.BLACK;
            case "Brown" -> new Color(0.36f, 0.25f, 0.20f, 1f);
            case "Blonde" -> new Color(0.98f, 0.94f, 0.55f, 1f);
            case "Red" -> Color.RED;
            case "Blue" -> Color.BLUE;
            case "Cyan" -> Color.CYAN;
            case "Green" -> Color.GREEN;
            case "Magenta" -> Color.MAGENTA;
            case "Orange" -> Color.ORANGE;
            case "Pink" -> Color.PINK;
            case "Yellow" -> Color.YELLOW;
            case "Gray" -> Color.GRAY;
            case "White" -> Color.WHITE;
            default -> Color.WHITE;
        };
    }

    private void refreshCharacter() {
        characterStack.clear();
        characterStack.add(characterBackground);
        characterStack.add(getCharacterGroup());
    }
}

class SelectorGroup {
    public Table table;
    public ImageButton leftBtn, rightBtn;
    public Label label;

    public SelectorGroup(String labelText) {
        table = new Table();
        Skin skin = StardewValley.getSkin();

        leftBtn = new ImageButton(skin, "Left");
        rightBtn = new ImageButton(skin, "Right");
        label = new Label(labelText, skin);
        label.setAlignment(Align.center);

        table.add(leftBtn).size(60, 60).padRight(10);
        table.add(label).width(100).center().padRight(10);
        table.add(rightBtn).size(60, 60);
    }
}
