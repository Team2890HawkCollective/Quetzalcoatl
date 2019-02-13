/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

public class goToUpperHatchPositionCommand extends Command 
{
  public goToUpperHatchPositionCommand() 
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(RobotMap.manipulatorSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    RobotMap.manipulatorSubsystem.goToUpperHatchPosition();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    return RobotMap.upperPositionHatchHolderLimitSwitch.get();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
    RobotMap.hatchHolderTalon.set(RobotMap.HATCH_HOLDER_STOP_SPEED);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
