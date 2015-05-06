import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Day_2_Birds extends PApplet {

// Singin Birds

Birds vogel;
Birds vogel1;



public void setup() {
	size(512, 512);



	vogel = new Birds("Daniel", 200, 200);
	vogel1 = new Birds("Jan", 100, 100);

	//Vogel.isSinging = true;
}

public void draw() {
	background(155);

	vogel.animate();
	vogel.draw();
	vogel.tap(mouseX, mouseY);

	vogel1.animate();
	vogel1.draw();
	vogel1.tap(mouseX, mouseY);
}
class Birds {

	String name;
	boolean isSleeping;
	boolean isSinging;
	int posX;
	int posY;
	int birdWidth = 50;
	int birdHeight = 70;
	int growth = 15;

	Birds(String _name, int _posX, int _posY) {
		name = _name;
		posX = _posX;
		posY = _posY;
		isSleeping = false;
		isSinging = false;
	}


	public void draw() {
		if (isSinging == true) {
			fill(100, 0, 50);
			ellipse(posX, posY, birdWidth+growth, birdHeight+growth);
		} else {
			fill(50, 50, 50);
			ellipse(posX, posY, birdWidth, birdHeight);
		}	
	}

	public void animate() {}


	public void tap( int _topX, int _topY) {
		if (mousePressed && _topX < posX+birdWidth/2 && _topX > posX-birdWidth/2 && _topY < posY+birdHeight/2 && posY > _topY-birdHeight/2) {
			posX = _topX;
			posY = _topY;
			println("jeah");
			isSinging = true;
		} else {
			isSinging = false;
			println("ustadfi");
		}
	}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Day_2_Birds" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
