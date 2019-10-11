/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

/**
 * @deprecated replaced by MoveElevatorCommand 18/02/2019 |
 * lowers the elevator
 */
public class LowerElevatorCommand extends Command 
{
  /**
   * claims elevatorSubsystem so other classes can't use it
   */
  public LowerElevatorCommand() 
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(RobotMap.elevatorSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
  }

  // Called repeatedly when this Command is scheduled to run
  /**
   * Moves elevator down
   */
  @Override
  protected void execute() 
  {
    RobotMap.elevatorSubsystem.elevatorDown();
  }

  // Make this return true when this Command no longer needs to run execute()
  /**
   *@return true if elevator has reached the target
   */
  @Override
  protected boolean isFinished() 
  {
    return false; 
  }

  // Called once after isFinished returns true
  /**
   * stops the motor once target is reached
   */
  @Override
  protected void end() 
  {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
  }
}
