/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import components.utilities.FormatChecker;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

/**
 * Makes the bot strafe by reading the arduino until it's told to stop
 */
public class TargetingStage2StrafeCommand extends Command 
{
  private String data;

  /**
   * Claims use of driveTrainSubsystem preventing other commands from using it
   */
  public TargetingStage2StrafeCommand() 
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(RobotMap.driveTrainSubsystem);

    data = "";
  }

  //Called just before this Command runs the first time
  /**
   * reads arduino and stores it in data
   */
  @Override
  protected void initialize() 
  {
    //data = RobotMap.arduino.readString();
  }

  //Called repeatedly when this Command is scheduled to run
  /**
   * strafes until told to stop
   */
  @Override
  protected void execute() 
  {
    /*if (data.length() > 0)
      if (FormatChecker.canParseDouble(data))
        RobotMap.driveTrainSubsystem.arcadeDrive(
          RobotMap.MOTOR_FULL_STOP, RobotMap.MOTOR_FULL_STOP,
           -Double.parseDouble(data) / RobotMap.DRIVETRAIN_CAMERA_TARGETING_STRAFE_SPEED_MODIFIER);
           */
  }

  //Make this return true when this Command no longer needs to run execute()
  /**
   *  returns true if ardiuno says Done otherwise return false
   */
  @Override
  protected boolean isFinished() 
  {
    /*data = RobotMap.arduino.readString();
    if (data.length() > 0)
      if (data.substring(0, 1).equals("D"))
        return true;
        */
    return false;
  }

  //Called once after isFinished returns true
  /**
   * stops motor
   */
  @Override
  protected void end() 
  {
    RobotMap.driveTrainSubsystem.arcadeDrive(RobotMap.MOTOR_FULL_STOP, RobotMap.MOTOR_FULL_STOP, RobotMap.MOTOR_FULL_STOP);
  }

  //Called when another command which requires one or more of the same subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
  }
}
