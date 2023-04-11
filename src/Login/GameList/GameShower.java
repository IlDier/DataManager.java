package Login.GameList;

import Game.Game.GameManger.Game;
import Profile.ProfilePanel;

import javax.swing.*;
import java.awt.*;

public class GameShower  {
    private JLabel TimeLabel = new JLabel("Fuck");

    private  Game game ;
    private ListFrame listFrame;
    public GameShower(ListFrame listFrame){
        this.listFrame = listFrame;

        setLabels();

    }

    private void setLabels(){

        setTimeLabel();
    }
    private void setTimeLabel(){

        TimeLabel.setSize(100,100);
        TimeLabel.setLocation(0,0);
        listFrame.getListPanel().add(TimeLabel);
    }

}
