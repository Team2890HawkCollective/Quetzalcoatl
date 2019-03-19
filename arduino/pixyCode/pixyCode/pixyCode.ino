//
// begin license header
//
// This file is part of Pixy CMUcam5 or "Pixy" for short
//
// All Pixy source code is provided under the terms of the
// GNU General Public License v2 (http://www.gnu.org/licenses/gpl-2.0.html).
// Those wishing to use Pixy source code, software and/or
// technologies under different licensing terms should contact us at
// cmucam@cs.cmu.edu. Such licensing terms are available for
// all portions of the Pixy codebase presented here.
//
// end license header
//
// This sketch is a good place to start if you're just getting started with
// Pixy and Arduino.  This program simply prints the detected object blocks
// (including color codes) through the serial console.  It uses the Arduino's
// ICSP SPI port.  For more information go here:
//
// https://docs.pixycam.com/wiki/doku.php?id=wiki:v2:hooking_up_pixy_to_a_microcontroller_-28like_an_arduino-29
// //

#include <Pixy2.h>
#include <Wire.h>
#include "Adafruit_VL6180X.h"
#include "Adafruit_NeoPixel.h"

#define driverNotifierPin 5
#define cameraLightPin 4

#define numberOfDriverNotifierLeds 150
#define numberOfCameraLeds 12


Adafruit_VL6180X vl = Adafruit_VL6180X();
Adafruit_NeoPixel driverNotifier = Adafruit_NeoPixel(numberOfDriverNotifierLeds, driverNotifierPin);
Adafruit_NeoPixel cameraLight = Adafruit_NeoPixel(numberOfCameraLeds, cameraLightPin);

// This is the main Pixy object
Pixy2 pixy;

//Booleans for when each stage is done
bool stage1Done = false;
bool stage2Done = false;
bool stage3Done = false;

int cameraMiddlePoint = 400; //The middle value of the Pixy cam video feed. Used for strafing
int rangefinderStopPoint = 150; //The stopping point for the rangefinder. This is when the robot will stop

uint8_t rangefinderValue; //The distance returned by the rangefinder

uint32_t pink = driverNotifier.Color(255, 20, 147); //The color pink
uint32_t teal = driverNotifier.Color(0, 128, 128); //The color teal
uint32_t white = cameraLight.Color(255, 255, 255); //The color white

void setup()
{
  Serial.begin(115200); //Begin serial comms
  
  //initialise objects
  pixy.init();
  vl.begin();
  driverNotifier.begin();
  cameraLight.begin();

  //Set the lights to their initial colors
  for (int i = 0; i < 12; i++)
    driverNotifier.setPixelColor(i, pink);
  for (int i = 0; i < 12; i++)
    cameraLight.setPixelColor(i, white);
  
  //Set the lights to show. This must be done whenever the colour is changed
  driverNotifier.show();
  cameraLight.show();
}

void loop()
{
  //Grab the value from the rangefinder
  rangefinderValue = vl.readRange();

  // grab blocks!
  pixy.ccc.getBlocks();

  // If there are 2 detected blocks, we're good to go
  if (pixy.ccc.numBlocks >= 2)
  {
    //Set the driverNotifierCamera to teal to notify the driver we are ready
    for (int i = 0; i < numberOfDriverNotifierLeds; i++)
      driverNotifier.setPixelColor(i, teal);
    driverNotifier.show();
    
    //Add a stop so this only starts running when a button is pushed by the driver.
    //If we haven't done stage1, lets do it
    if (!stage1Done)
      stage1();
    else if (!stage2Done)
      stage2();
    else if (!stage3Done)
      stage3();
  }
}

void stage1()
{
  //If the heights are equals, we don't need to rotate
  if ((int)pixy.ccc.blocks[0].m_height - (int)pixy.ccc.blocks[1].m_height == 0)
  {
    //Set this stage as done and print "Done" to let the robot code know
    stage1Done == true;
    Serial.println("Done");
  }
  //This checks to see which Block is on the left as the pixy doesn't order them
  else if ((int)pixy.ccc.blocks[0].m_x > (int)pixy.ccc.blocks[1].m_x)
    Serial.println((int)pixy.ccc.blocks[0].m_height - (int)pixy.ccc.blocks[1].m_height);
  else
    Serial.println((int)pixy.ccc.blocks[1].m_height - (int)pixy.ccc.blocks[0].m_height);
}
           
void stage2()
{
  //If the average of the x-values is equal to the mid-point, we dont need to strafe more
  if (average() == cameraMiddlePoint)
  {
    //Set this stage as done and print "Done" to let the robot code know
    stage2Done == true;
    Serial.println("Done");
  }
  else
    Serial.println(average() - cameraMiddlePoint); //Print out how far we have to go until we are in the middle
}

void stage3()
{
  //If We are <= the target value, we don't need to move forward
  if (rangefinderValue <= rangefinderStopPoint)
  {
    //Set this stage as done and print "Done" to let the robot code know
    stage3Done = true;
    Serial.println("Done");
  }
  else
    Serial.println(rangefinderValue - rangefinderStopPoint); //Print out how far we have to go until we are at the stop point
}
           
//Returns the average of the x-values of the two blocks
int average()
{
  return ((int)pixy.ccc.blocks[0].m_x + (int)pixy.ccc.blocks[1].m_x) / 2;
}
