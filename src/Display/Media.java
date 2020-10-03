package Display;

import java.awt.image.BufferedImage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

import Game.Utils;

public class Media {

	public static BufferedImage topten1, topten, topten4, topten2, name,logo,Simple1,Simple2,Medium1,Medium2,Hard1,Hard2, Undo, menu1, menu2, bgOver, Continue, new_game, exit, Continue2, new_game2, exit2, help2, help, battery3,battery2,battery1,battery0,score,planet1, planet2, moon1, moon2, star1, star2, heart, astronaut, alien, bg, bgMenu, ufo,rocket;
	public static Clip alienOrStack, chooseUndo, game, gameOver, heartSound, menu, shapes, three, undo;
	public Media()
	{
		//DOWNLOAD IMAGES
		planet1 = Utils.loadImage("/res/planet_1.png");
		planet2 = Utils.loadImage("/res/planet_2.png");
		moon1 = Utils.loadImage("/res/moon_1.png");
		moon2 = Utils.loadImage("/res/moon_2.png");
		star1 = Utils.loadImage("/res/star_1.png");
		star2 = Utils.loadImage("/res/star_2.png");
		heart = Utils.loadImage("/res/heart.png");
		astronaut = Utils.loadImage("/res/astro.png");
		alien = Utils.loadImage("/res/Alien.png");
		bg = Utils.loadImage("/res/background.jpg");
		bgMenu = Utils.loadImage("/res/background menu.jpg");
		ufo = Utils.loadImage("/res/ufo.png");
		rocket = Utils.loadImage("/res/rocket.png");
		score = Utils.loadImage("/res/score.png");
		battery3 = Utils.loadImage("/res/3.png");
		battery2 = Utils.loadImage("/res/2.png");
		battery1 = Utils.loadImage("/res/1.png");
		battery0 = Utils.loadImage("/res/0.png");
		bgOver = Utils.loadImage("/res/gameOver.jpg");
		Continue = Utils.loadImage("/res/continue.png");
		new_game = Utils.loadImage("/res/newGame.png");
		exit = Utils.loadImage("/res/exit11.png");
		help = Utils.loadImage("/res/help.png");
		Continue2 = Utils.loadImage("/res/continue2.png");
		new_game2 = Utils.loadImage("/res/newGame2.png");
		exit2 = Utils.loadImage("/res/exit22.png");
		help2 = Utils.loadImage("/res/help2.png");
		menu1 = Utils.loadImage("/res/menu.png");
		menu2 = Utils.loadImage("/res/menu2.png");
		Undo = Utils.loadImage("/res/undo.png");
		Simple1 =  Utils.loadImage("/res/simple1.png");
		Simple2 =  Utils.loadImage("/res/simple2.png");
		Medium1 =  Utils.loadImage("/res/medium1.png");
		Medium2 =  Utils.loadImage("/res/medium2.png");
		Hard1 =  Utils.loadImage("/res/hard1.png");
		Hard2 =  Utils.loadImage("/res/hard2.png");
		logo = Utils.loadImage("/res/logo.png");
		name = Utils.loadImage("/res/enterName.png");
		topten4 = Utils.loadImage("/res/topten4.png");
		topten2 = Utils.loadImage("/res/topten2.png");
		topten = Utils.loadImage("/res/topten.png");
		topten1 = Utils.loadImage("/res/topten1.png");
	}
}
