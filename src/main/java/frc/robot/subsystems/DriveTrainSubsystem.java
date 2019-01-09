/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class DriveTrainSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() 
  {
    // Set the default command for a subsystem here.
       setDefaultCommand(new XboxDriveCommand());
  }
  
  public void xboxArcadeDrive()
	{
    //RobotMap.driveTrainSubsystem.tankDrive(RobotMap.fron.getY(Hand.kLeft), RobotMap.driverController.getY(Hand.kRight));
    //forwards
    //all motors go forward ()getY(Hand.kLeft)

    //forwards
    if(RobotMap.driverController.getY(Hand.kLeft) > 10)
    {
      RobotMap.leftFrontTalon.set(RobotMap.driverController.getY(Hand.kLeft));
      RobotMap.rightFrontTalon.set(RobotMap.driverController.getY(Hand.kLeft));
      RobotMap.leftBackTalon.set(RobotMap.driverController.getY(Hand.kLeft));
      RobotMap.rightBackTalon.set(RobotMap.driverController.getY(Hand.kLeft));
      
    }

    //backwards
    if(RobotMap.driverController.getY(Hand.kLeft) < -10)
    {
      RobotMap.leftFrontTalon.set(-RobotMap.driverController.getY(Hand.kLeft));
      RobotMap.rightFrontTalon.set(-RobotMap.driverController.getY(Hand.kLeft));
      RobotMap.leftBackTalon.set(-RobotMap.driverController.getY(Hand.kLeft));
      RobotMap.rightBackTalon.set(-RobotMap.driverController.getY(Hand.kLeft));
      
    }

      //rotate left
      if(RobotMap.driverController.getTriggerAxis(Hand.kLeft) > 10)
      {
        RobotMap.leftFrontTalon.set(-RobotMap.driverController.getTriggerAxis(Hand.kLeft));
        RobotMap.rightFrontTalon.set(RobotMap.driverController.getTriggerAxis(Hand.kLeft));
        RobotMap.leftBackTalon.set(-RobotMap.driverController.getTriggerAxis(Hand.kLeft));
        RobotMap.rightBackTalon.set(RobotMap.driverController.getTriggerAxis(Hand.kLeft));
      
      }
      
      //rotate right
      if(RobotMap.driverController.getTriggerAxis(Hand.kRight) > 10)
      {
        RobotMap.leftFrontTalon.set(RobotMap.driverController.getTriggerAxis(Hand.kRight));
        RobotMap.rightFrontTalon.set(-RobotMap.driverController.getTriggerAxis(Hand.kRight));
        RobotMap.leftBackTalon.set(RobotMap.driverController.getTriggerAxis(Hand.kRight));
        RobotMap.rightBackTalon.set(-RobotMap.driverController.getTriggerAxis(Hand.kRight));
      
      }

      //Strafe left
      if(RobotMap.driverController.getX(Hand.kRight) < -10)
      {
        RobotMap.leftFrontTalon.set(RobotMap.driverController.getX(Hand.kRight));
        RobotMap.rightFrontTalon.set(RobotMap.driverController.getX(Hand.kRight));
        RobotMap.leftBackTalon.set(RobotMap.driverController.getX(Hand.kRight));
        RobotMap.rightBackTalon.set(RobotMap.driverController.getX(Hand.kRight));
      
      }

      //strafe right
      if(RobotMap.driverController.getX(Hand.kRight) > 10)
      {
        RobotMap.leftFrontTalon.set(-RobotMap.driverController.getX(Hand.kRight));
        RobotMap.rightFrontTalon.set(-RobotMap.driverController.getX(Hand.kRight));
        RobotMap.leftBackTalon.set(-RobotMap.driverController.getX(Hand.kRight));
        RobotMap.rightBackTalon.set(-RobotMap.driverController.getX(Hand.kRight));
        RobotMap.centralTalon.set(-RobotMap.driverController.getX(Hand.kRight));
      }
  


      
	}
	
}
