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

public class recursion extends PApplet {

public void setup() {
  noLoop();
}
int input = 80;

public void draw() {
  divideNumber(input);
}

public void divideNumber(int number) {
  int newnumber = number;
  if(newnumber % 2 == 0){
    newnumber = number / 2;
    divideNumber(newnumber);
    println("newnumber: "+newnumber);
  } else {
    println("Zahl nicht gerade");
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "recursion" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
