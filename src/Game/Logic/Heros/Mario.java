package Game.Logic.Heros;

import DataManager.PlayersData.Player;
import Game.Game.CharacterKeyListener;
import Game.Game.SectionFrame;
import Game.Game.SectionPanel;
import Game.Logic.Character;
import Game.Logic.Coin.Coin;
import Game.Logic.Enemy.Planet;
import Game.Logic.Flag;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Mario extends Character {
    private int Heart = 3;
    private final double grovicty=0.5;
    protected int code = 0;
    private int coins=0;
    private final int xCo =x;
    private final int yCo =y;
    private Player player;

    public Mario(int x, int y, int Width, int Height, SectionPanel sectionPanel, CharacterKeyListener characterKeyListener) {
        super(x, y, Width, Height, sectionPanel, characterKeyListener);
        direction = "right";
        this.getImage();
    }


    @Override
    public void Update(SectionPanel sectionPanel) {
        if(characterKeyListener.up){
            this.setY(this.getY()-this.getSpeedY());
            direction = "up";
        }
        else if(characterKeyListener.down) {
            this.setY(this.getY()+this.getSpeedY());
            direction = "down";
        }
        else if(characterKeyListener.right) {
            this.setX(this.getX()+this.getSpeedX());
            direction = "right";
        }
        else if(characterKeyListener.left){
            this.setX(this.getX()-this.getSpeedX());
            direction = "left";
        }
        sectionPanel.collisionChecker.checkTile(this);
      for(Character character : sectionPanel.map.characters){
          touchWithEnemy(character);
          touchCoin(character);
          touchFlag(character);
      }
      deadJumping(sectionPanel);

    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.drawImage(main,x,y,sectionPanel.getTileSize(),sectionPanel.getTileSize(),null);
        g2.drawString("HEART :" + Heart, 350,100);
    }

    @Override
    protected void getImage() {
        try {
            File Mario=new File("D:/JavaProjects/ApProjectSpring2023/src/Game/Logic/Characters/Mario.png");
            main = ImageIO.read(Mario);
        } catch (Exception o) {
            System.out.println("NO");
        }
    }
    protected Character touchCoin(Character character){
        Character character1 = null;
        if(character instanceof Coin && this.isTouch(character)){
            coins++;
            character.setVisible(false);
            character1 = character;
        }
        return character1;
    }
    protected void touchWithEnemy(Character character){
     touchPlant(character);
    }
    protected void touchPlant(Character character){
        if(character instanceof Planet && this.isTouch(character)){
            Heart--;
            x = xCo;
            y = yCo;
        }
    }
    protected void deadJumping(SectionPanel sectionPanel){
        if(y>680){
            Heart--;
            x = xCo;
            y = yCo-16;
        }
    }
    protected void touchFlag(Character character){
        if(character instanceof Flag && character.isTouch(this)) {
            x = 0;
            y = 635;
            sectionPanel.STOP();
           if(sectionPanel.getMapNumb() == 4){
               sectionPanel.setMapNumb(0);
               sectionPanel.setPartNumb(sectionPanel.getPartNumb()+1);
               new SectionFrame(0,sectionPanel.getTimer(),coins,Heart,player,sectionPanel.getPartNumb());
           }

           else new SectionFrame(sectionPanel.getMapNumb()+1,sectionPanel.getTimer(),coins,Heart,player,sectionPanel.getPartNumb());
           if (sectionPanel.getPartNumb() == 2 && sectionPanel.getMapNumb() == 4){
               sectionPanel.getFc().setWin(true);
               sectionPanel.getFc().setFinish(true);
           }
        };

    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getHeart() {
        return Heart;
    }

    public void setHeart(int heart) {
        Heart = heart;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getGrovicty() {
        return grovicty;
    }

    public int getxCo() {
        return xCo;
    }

    public int getyCo() {
        return yCo;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
