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

public class arrays extends PApplet {

float[] grey;

public void setup() {
  size(1280, 720);
  background(51);
  noStroke();
  noLoop();
}

public void draw() {
  drawTarget(width*0.5f, height*0.5f, 800, 10);
  drawTarget(width*0.25f, height*0.4f, 200, 4);
  drawTarget(width*0.75f, height*0.3f, 120, 6);
}


public void drawTarget(float xloc, float yloc, int size, int num) {
  float grayvalues = 255/num;
  float steps = size/num;
  for (int arraypos = 0; arraypos < 30; arraypos++) {
    grey = new float[30];
    for (int i = 0; i < num; i++) {
      float grayvalue = (i*grayvalues);
      grey[arraypos] = grayvalue;
      fill(grayvalue);
      //println("grayvalue: "+grayvalue);
      ellipse(xloc, yloc, size - i*steps, size + i*steps);
    }
  }
  printArray(grey);
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "arrays" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
