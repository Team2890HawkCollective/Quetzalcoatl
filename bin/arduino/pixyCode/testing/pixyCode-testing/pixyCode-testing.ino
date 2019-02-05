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
//
  
#include <Pixy2.h>
#include "Adafruit_VL6180X.h"

//Adafruit_VL6180X vl = Adafruit_VL6180X();

// This is the main Pixy object 
//Pixy2 pixy;
int count = 0;
int range = 1000;

int width1 = 700;
int width2 = 300;

int x1 = 0;
int x2 = 300;

bool stage1Done = false;
bool stage2Done = false;
bool stage3Done = false;

void setup()
{
  Serial.begin(115200);
  //Serial.print("Starting...\n");

  while(!Serial)
  {
    ;
  }
  
  //pixy.init();
}

void loop()
{ 
  //float lux = vl.readLux(VL6180X_ALS_GAIN_5);

  //range -= 10;
  
  // grab blocks!
  //pixy.ccc.getBlocks();
  
  //Don't flood the serial with data
  if (count % 100 == 0 && count >= 10000)
  {
    // If there are detect blocks, print them!
    //if (pixy.ccc.numBlocks >= 2)
    //{
      if (width2 == width1)
      {
        if (stage1Done == false)
        {
          Serial.println("Done");
          stage1Done = true;
        }
        else if (((x1 + x2) / 2) == 200)
        {
          if (stage2Done == false)
          {
            Serial.println("Done");
            stage2Done = true;
          }
          else if (range >= 5)
          {
            Serial.println(range);
            range -= 10;
          }
          else
          {
            if (stage3Done == false)
            {
              Serial.println("Done");
              stage3Done = true;
            }
          }
        }
        else
        {
          Serial.println((x1 + x2) / 2);
          x1 += 5;
          x2 += 5;
        }
      }
      else if (x1 > x2)
        Serial.println(width1 - width2);
      else if (x2 > x1)
      {
        Serial.println(width2 - width1);
        width2 += 20;
        width1 -= 20;
      }
    }
    count += 1;
  }
