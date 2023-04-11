package Game.Game.GameManger;

import DataManager.IndexChecker.IndexChecker;
import DataManager.PlayersData.Player;
import Game.Game.SectionFrame;
import Game.Game.SectionPanel;
import Game.Logic.Heros.Mario;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private SectionPanel sectionPanel;
    private int coins =0;
    private long time =0;
    private int mapNumb =0;
    private int heart =0;
    private int score =0;
    private int heroCode =0;
    private int heroX =0;
    private int heroY =0;
    private int partNumb = 1;


    private long Id;
    private File[] savedGames = new File[3];
    public Game(SectionPanel sectionPanel){
        this.sectionPanel = sectionPanel;
        Id = System.nanoTime();
        initMaps();
        getData(0);
    }
    public void getData(int index){
        try {
            Scanner reader = new Scanner(savedGames[index]);
            String line = reader.nextLine();
            String[] x = line.split(" ");

            time = Long.parseLong(x[1]);
            mapNumb = Integer.parseInt(x[3]);
            heart = Integer.parseInt(x[5]);
            coins = Integer.parseInt(x[7]);
            score = Integer.parseInt(x[9]);
            heroCode = Integer.parseInt(x[11]);
            heroX = Integer.parseInt(x[13]);
            heroY = Integer.parseInt(x[15]);
            partNumb = Integer.parseInt(x[17]);
            Id = Long.parseLong(x[17]);

        } catch (Exception e) {
            System.out.println(0);
        }
    }
    private void appendData(int index){
        try {
            FileWriter fw = new FileWriter(savedGames[index],false);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Time: "+time+" mapNumb: "+mapNumb+" Heart: "+heart+" coins: "+coins+" Score: "+score+" Hero: "+heroCode+" HeroX: "+heroX+" HeroY: "+heroY+" Id: "+Id+" partNumb: "+partNumb);
            bw.close();
            fw.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setData(){
        Mario mario = sectionPanel.getMario();
        coins = mario.getCoins();
        heart = mario.getHeart();
        heroCode = mario.getCode();
        heroY = mario.getY();
        heroX = mario.getX();
        time = sectionPanel.getTimer();
        mapNumb = sectionPanel.getMapNumb();
        partNumb = sectionPanel.getMapNumb();
        score = sectionPanel.getSm().score;
    }
    private void initMaps(){
        savedGames[0] = new File("D:/JavaProjects/ApProjectSpring2023/src/Game/Game/GameManger/00.txt");
        savedGames[1] = new File("D:/JavaProjects/ApProjectSpring2023/src/Game/Game/GameManger/01.txt");
        savedGames[2] = new File("D:/JavaProjects/ApProjectSpring2023/src/Game/Game/GameManger/02.txt");
    }
    public void saveGame(){
        setData();
        appendData(IndexChecker.Index);

    }
    public void NewGame(Player player){
        coins = 0 ;
        heart = 0;
        mapNumb = 0;
        time = 0 ;
        score =0;
        heroCode = player.getHeroCode();
        heroX = 0;
        heroY = 638;
        IndexChecker.indexCall();
        appendData(IndexChecker.Index);
    }

    public SectionPanel getSectionPanel() {
        return sectionPanel;
    }

    public int getCoins() {
        return coins;
    }

    public long getTime() {
        return time;
    }

    public int getMapNumb() {
        return mapNumb;
    }

    public int getHeart() {
        return heart;
    }

    public int getScore() {
        return score;
    }

    public int getHeroCode() {
        return heroCode;
    }

    public int getHeroX() {
        return heroX;
    }

    public int getHeroY() {
        return heroY;
    }

    public long getId() {
        return Id;
    }

    public File[] getSavedGames() {
        return savedGames;
    }

    public int getPartNumb() {
        return partNumb;
    }
}
