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

#define PIN 6

Adafruit_VL6180X vl = Adafruit_VL6180X();
Adafruit_NeoPixel driverNotifier = Adafruit_NeoPixel(12, 6);
Adafruit_NeoPixel cameraLight = Adafruit_NeoPixel(12, 5);

// This is the main Pixy object
Pixy2 pixy;
int count = 0;
bool stage1Done = false;
bool stage2Done = false;
bool stage3Done = false;

int middlePoint = 150;
int rangefinder = 150;

uint32_t pink = driverNotifier.Color(255, 20, 147);
uint32_t teal = driverNotifier.Color(0, 128, 128);
uint32_t white = cameraLight.Color(255, 255, 255);

void setup()
{
  Serial.begin(115200);

  pixy.init();

  vl.begin();

  driverNotifier.begin();
  cameraLight.begin();
  
  driverNotifier.show();
  cameraLight.show();

  for (int i = 0; i < 12; i++)
    driverNotifier.setPixelColor(i, pink);
  }

  for (int i = 0; i < 12; i++)
    cameraLight.setPixelColor(i, white);
  cameraLight.show();
}

void loop()
{
  driverNotifier.show();

  float lux = vl.readLux(VL6180X_ALS_GAIN_5);

  uint8_t range = vl.readRange();
  uint8_t status = vl.readRangeStatus();

  // grab blocks!
  pixy.ccc.getBlocks();

  // If there are detect blocks, print them!
  if (pixy.ccc.numBlocks >= 2)
  {
    for (int i = 0; i < 12; i++)
      driverNotifier.setPixelColor(i, teal);
    driverNotifier.show();
    
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
  if ((int)pixy.ccc.blocks[0].m_height - (int)pixy.ccc.blocks[1].m_height == 0)
  {
    stage1Done == true;
    Serial.println("Done");
  }
  else if ((int)pixy.ccc.blocks[0].m_x > (int)pixy.ccc.blocks[1].m_x
    Serial.println((int)pixy.ccc.blocks[0].m_height - (int)pixy.ccc.blocks[1].m_height);
  else
    Serial.println((int)pixy.ccc.blocks[1].m_height - (int)pixy.ccc.blocks[0].m_height);
}

void stage2()
{
  if (average() - middlePoint) == 0)
  {
    stage2Done == true;
    Serial.println("Done");
  }
  else
    Serial.println(average() - middlePoint);
}

void stage3()
{
  if (range - rangefinder <= 0)
  {
    stage3Done = true;
    Serial.println("Done");
  }
  else
    Serial.println(range - rangfinder);
}

int average()
{
  return ((int)pixy.ccc.blocks[0].m_x + (int)pixy.ccc.blocks[1].m_x) / 2;
}
