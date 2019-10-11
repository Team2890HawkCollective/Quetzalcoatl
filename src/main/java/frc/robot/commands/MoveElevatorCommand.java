/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;
import frc.robot.subsystems.AutomatedSubsytem;

public class MoveElevatorCommand extends Command 
{
  /**
   * The value which when the motor reaches, will stop
   */
  private boolean goingDown = true;
  private double encoderTarget;

  /**
   * Claims elevator subsystem so other classes can't use it
   * Sets the encoder target based on the level given and whether or not cargo is true
   * @param level The level to lift to. 
   * @param cargo Whether or not we are lifting cargo
   */
  public MoveElevatorCommand(AutomatedSubsytem.RocketLevel level, boolean cargo) 
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(RobotMap.elevatorSubsystem);
  }

  /**
   *  Called just before this Command runs the first time
   */
  @Override
  protected void initialize() 
  {

  }

  /**
   * Moves elevator according to whether or not the encoder is higher or lower than the target.
   */
  @Override
  protected void execute() 
  {
  
  }

  /**
   * @return true if elevator has reached the target
   */
  @Override
  protected boolean isFinished() 
  {
    return false;
  }

  // Called once after isFinished returns true
  /**
   * When command ends, stops the motor
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
