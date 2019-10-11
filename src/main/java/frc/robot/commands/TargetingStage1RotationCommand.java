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
 * Continues to rotate the bot by reading the ardiuno until it's told to stop
 */
public class TargetingStage1RotationCommand extends Command 
{
  private String data;

  /**
   * Claims use of driveTrainSubsystem preventing other commands from using it
   */
  public TargetingStage1RotationCommand() 
  {
    // Use requires() here to declare subsystem dependencies eg. requires(chassis);
    requires(RobotMap.driveTrainSubsystem);

    data = "";
  }

  //  Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
  }

  //Called repeatedly when this Command is scheduled to run
  /**
   * rotates the bot until told to stop
   */
  @Override
  protected void execute() 
  {
    
  }

  /**
   *  @return true if arduino says done otherwise returns false
   */
  @Override
  protected boolean isFinished() 
  {
    return false;
  }

  //Called once after isFinished returns true
  /**
   *  stops motors
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
