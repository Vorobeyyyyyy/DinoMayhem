package com.sosoft.skyfighter.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.sosoft.skyfighter.heroes.PlayersTeams;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class LevelInterfaceTeams{
    private Stage stage;
    private Texture textureGrad;
    private Texture textureLeftPanel;
    private Image imageLeftPanel;
    private Array<Image> imageGrads;
    private Array<Texture> teamsTextureLabel;
    private Array<Image> teamsImageLabel;
    private Array<Texture> teamsTextureIcon;
    private Array<Image> teamsImageIcon;
    public Array<PlayersTeams> teams;

//    LevelInterfaceTeams(Array<PlayersTeams> teams) {
//        stage = new Stage();
//        this.teams = new Array<PlayersTeams>(teams);
//        teamsImageLabel = new Array<Image>();
//        for(int i = 0; i < teams.size; i++)
//            //teamsTextureLabel.add(new Texture("Heroes/CurHealth_" + String.valueOf(i) + ".png"));
//            teamsTextureLabel.add(new Texture("Heroes/CurHealth_.png"));
//        for(int i = 0; i < teams.size; i++)
//            teamsImageLabel.add(new Image(teamsTextureLabel.get(i)));
//        for(int i = 0; i < teams.size; i++) {
//            teamsImageLabel.get(i).setPosition(0, Gdx.graphics.getHeight() - 100 - 100 * i);
//            teamsImageLabel.get(i).setSize(100, 20);
//        }
//        for(Image imageTeam : teamsImageLabel)
//            stage.addActor(imageTeam);
//    }

    LevelInterfaceTeams(int a) {
        stage = new Stage();

        this.teams = new Array<PlayersTeams>();

        teamsTextureLabel = new Array<Texture>();
        teamsImageLabel = new Array<Image>();
        for(int i = 0; i < 8; i++)
            teamsTextureLabel.add(new Texture("TeamsIcon/CurHealth_" + String.valueOf(i) + ".png"));
        for(int i = 0; i < 8; i++)
            teamsImageLabel.add(new Image(teamsTextureLabel.get(i)));
        for(int i = 0; i < 8; i++) {
            teamsImageLabel.get(i).setPosition(0, Gdx.graphics.getHeight() - 100 - 100 * i);
            teamsImageLabel.get(i).setSize(200, 2);
        }

        imageGrads = new Array<Image>();
        textureGrad = new Texture("Menu/BlackRec.png");
        for(int i = 0; i < 8; i++) {
            imageGrads.add(new Image(textureGrad));
        }
        for(int i = 0; i < 8; i++) {
            imageGrads.get(i).addAction(alpha(0.1f));
        }
        for(int i = 0; i < 8; i++) {
            imageGrads.get(i).setPosition(50, Gdx.graphics.getHeight() - 100 - 100 * i - 30);
            imageGrads.get(i).setSize(150, 30);
        }

        teamsTextureIcon = new Array<Texture>();
        teamsImageIcon = new Array<Image>();
        for(int i = 0; i < 8; i++) {
            teamsTextureIcon.add(new Texture("TeamsIcon/team_icon_" + String.valueOf(i) + ".png"));
            teamsTextureIcon.get(i).setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        for(int i = 0; i < 8; i++)
            teamsImageIcon.add(new Image(teamsTextureIcon.get(i)));
        for(int i = 0; i < 8; i++) {
            teamsImageIcon.get(i).setPosition(0, Gdx.graphics.getHeight() - 100 - 100 * i - 50);
            teamsImageIcon.get(i).setSize(70, 90);
        }

        for(Image imageGrad : imageGrads)
            stage.addActor(imageGrad);
        for(Image imageTeam : teamsImageLabel)
            stage.addActor(imageTeam);
        for(Image imageIcon : teamsImageIcon)
            stage.addActor(imageIcon);
    }

    public void update(float delta) {
        stage.act(delta);
    }

    public void draw() {
        update(0);
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
    }
}
