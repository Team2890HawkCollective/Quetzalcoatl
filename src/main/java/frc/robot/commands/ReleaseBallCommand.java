/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.RobotMap;

/**
 * releases ball
 */
public class ReleaseBallCommand extends TimedCommand 
{
  /**
   * Claims use of manipulatorSubsystem preventing other commands from using it |
   * @param timeout how long the command will run
   */
  public ReleaseBallCommand(double timeout) 
  {
    super(timeout);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(RobotMap.manipulatorSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
  }

  /**
   * sets the motor to default speed
   */
  @Override
  protected void execute() 
  {
    RobotMap.manipulatorSubsystem.spinIntake(RobotMap.MANIPULATOR_DEFAULT_SPEED);
  }

  //Called once after timeout
  @Override
  protected void end() 
  {
  }

  //Called when another command which requires one or more of the same subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
  }
}
