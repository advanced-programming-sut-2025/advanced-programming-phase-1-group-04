package io.Ap.StardewValley.Model;

import com.badlogic.gdx.Input;

public class KeyManager {
    private int moveUp = Input.Keys.W;
    private int moveDown = Input.Keys.S;
    private int moveLeft = Input.Keys.A;
    private int moveRight = Input.Keys.D;

    private int zoom = Input.Keys.CONTROL_LEFT;
    private int pauseGame = Input.Keys.ESCAPE;

    private int cheatTime = Input.Keys.T;
    private int cheatLevel = Input.Keys.L;
    private int cheatLife = Input.Keys.J;
    private int cheatHp = Input.Keys.H;
    private int cheatBossFight = Input.Keys.B;

    //inventory:
    private int openInventory = Input.Keys.I;

    //cooking:
    private int openRefrigerator = Input.Keys.C;

    private int aynazCheat = Input.Keys.Z;
    private int nafisehCheat = Input.Keys.ENTER;

    public int getMoveUp() {
        return moveUp;
    }

    public void setMoveUp(int moveUp) {
        this.moveUp = moveUp;
    }

    public int getMoveDown() {
        return moveDown;
    }

    public void setMoveDown(int moveDown) {
        this.moveDown = moveDown;
    }

    public int getMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(int moveLeft) {
        this.moveLeft = moveLeft;
    }

    public int getMoveRight() {
        return moveRight;
    }

    public void setMoveRight(int moveRight) {
        this.moveRight = moveRight;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public int getCheatTime() {
        return cheatTime;
    }

    public void setCheatTime(int cheatTime) {
        this.cheatTime = cheatTime;
    }

    public int getCheatLevel() {
        return cheatLevel;
    }

    public void setCheatLevel(int cheatLevel) {
        this.cheatLevel = cheatLevel;
    }

    public int getCheatLife() {
        return cheatLife;
    }

    public void setCheatLife(int cheatLife) {
        this.cheatLife = cheatLife;
    }

    public int getCheatHp() {
        return cheatHp;
    }

    public void setCheatHp(int cheatHp) {
        this.cheatHp = cheatHp;
    }

    public int getPauseGame() {
        return pauseGame;
    }

    public void setPauseGame(int pauseGame) {
        this.pauseGame = pauseGame;
    }

    public int getCheatBossFight() {
        return cheatBossFight;
    }

    public void setCheatBossFight(int cheatBossFight) {
        this.cheatBossFight = cheatBossFight;
    }

    public int getOpenInventory() {
        return openInventory;
    }

    public void setOpenInventory(int openInventory) {
        this.openInventory = openInventory;
    }

    public int getAynazCheat() {
        return aynazCheat;
    }

    public void setAynazCheat(int aynazCheat) {
        this.aynazCheat = aynazCheat;
    }

    public int getOpenRefrigerator() {
        return openRefrigerator;
    }

    public void setOpenRefrigerator(int openRefrigerator) {
        this.openRefrigerator = openRefrigerator;
    }

    public int getNafisehCheat() {
        return nafisehCheat;
    }

    public void setNafisehCheat(int nafisehCheat) {
        this.nafisehCheat = nafisehCheat;
    }
}

