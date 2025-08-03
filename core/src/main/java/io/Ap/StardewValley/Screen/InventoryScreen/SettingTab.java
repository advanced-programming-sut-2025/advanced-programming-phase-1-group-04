package io.Ap.StardewValley.Screen.InventoryScreen;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;

public class SettingTab extends Window {
    private final Skin skin;
    private final TextButton exitButton;
    public SettingTab(Skin skin) {
        super("", skin);
        this.skin = skin;

        exitButton = new TextButton("Exit", skin);

        this.setSize(1050, 650);
        this.setMovable(false);
        this.setResizable(false);
        this.align(Align.topLeft);
        this.defaults().pad(10);

        this.add(exitButton).width(300).center();
    }
}



/*
private final Skin skin;
    private final List<ImageTextButton> inventoryButtons;
    private ImageTextButton selectedButton;
    private Table leftPart;
    private ScrollPane scrollPane;
    private Map<ImageTextButton, String> buttonToItemName = new HashMap<>();


    public InventoryTab(Skin skin) {
        super("", skin);
        this.skin = skin;
        this.inventoryButtons = new ArrayList<>();

        this.setSize(1050, 650);
        this.setMovable(false);
        this.setResizable(false);
        this.align(Align.topLeft);
        this.defaults().pad(10);


        leftPart = new Table();
        scrollPane = new ScrollPane(leftPart, skin, "inventory");
        scrollPane.setFadeScrollBars(false);
        leftPart.top().left();

        updateInventory();


        Table centerPart = new Table();
        ImageButton orderButton = new ImageButton(skin, "order");
        orderButton.setTransform(true);
        orderButton.scaleBy(0.4f);
        orderButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // مثلاً مرتب‌سازی آیتم‌ها
            }
        });
        centerPart.add(orderButton).size(100, 100).left().pad(30, 0, 0, 30);
        centerPart.row();

        ImageButton trashButton = new ImageButton(skin, "trash");
        trashButton.setTransform(true);
        trashButton.scaleBy(0.4f);
        trashButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //todo
                PlayerController.inventoryTrashWithoutNumber(getSelectedItemName());
                updateInventory();

            }
        });
        centerPart.add(trashButton).size(100, 100).left().pad(10, 0, 0, 30);


        Table rightPart = new Table();
        rightPart.top();

        Image topImage = new Image(new Texture("etc/menu/daybg.png"));
        topImage.setScaling(Scaling.fit);
        rightPart.add(topImage).width(200).height(350).center().row();

        Label label1 = new Label(App.getCurrentUser().getNickname() , skin);
        Label label2 = new Label("count: " + App.getGame().getCurrentPlayer().getCount(), skin);
        rightPart.add(label1).center().padTop(10).row();
        rightPart.add(label2).center().padTop(5).row();

        this.add(rightPart).width(350).top();
        this.add(centerPart).width(140).top();
        this.add(scrollPane).width(450).top();
    }
 */
