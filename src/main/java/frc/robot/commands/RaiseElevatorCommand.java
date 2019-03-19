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

/**
 * @deprecated replaced by MoveElevatorCommand 18/02/2019 |
 * Raises the elevator
 */
public class RaiseElevatorCommand extends Command 
{
  /**
   * The value which when the motor reaches, will stop
   */
  private double encoderTarget;

  /**
   * Claims elevator subsystem so other classes can't use it
   * Sets the encoder target based on the level given and whether or not cargo is true
   * @param level The level to lift to. 
   * @param cargo Whether or not we are lifting cargo
   */
  public RaiseElevatorCommand(AutomatedSubsytem.RocketLevel level, boolean cargo) 
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(RobotMap.elevatorSubsystem);

    //If we are carrying cargo
    if (cargo)
    {
      switch (level) 
      {
        case LEVEL1:
          encoderTarget = RobotMap.ELEVATOR_LEVEL_1_CARGO_VALUE;
          break;
        case LEVEL2:
          encoderTarget = RobotMap.ELEVATOR_LEVEL_2_CARGO_VALUE;
          break;
        case LEVEL3:
          encoderTarget = RobotMap.ELEVATOR_LEVEL_3_CARGO_VALUE;
      }
    }
    else
    {
      switch (level)
      {
        case LEVEL1:
          encoderTarget = RobotMap.ELEVATOR_LEVEL_1_HATCH_VALUE;
          break;
        case LEVEL2:
          encoderTarget = RobotMap.ELEVATOR_LEVEL_2_HATCH_VALUE;
          break;
        case LEVEL3:
          encoderTarget = RobotMap.ELEVATOR_LEVEL_3_HATCH_VALUE;
      }
    }
  }

  /**
   *  Called just before this Command runs the first time
   */
  @Override
  protected void initialize() {}

  /**
   * Moves elevator up.
   */
  @Override
  protected void execute() 
  {
    RobotMap.elevatorSubsystem.elevatorUp();
    System.out.println(RobotMap.elevatorEncoder.getPosition());
  }

  /**
   * @return true if elevator has reached the target
   */
  @Override
  protected boolean isFinished() 
  {
    return RobotMap.elevatorSubsystem.getEncoderPosition() >= encoderTarget;
  }

  // Called once after isFinished returns true
  /**
   * When command ends, stops the motor
   */
  @Override
  protected void end() 
  {
    //RobotMap.elevatorEncoder.setPosition(encoderTarget);
    RobotMap.leftElevatorTalon.stopMotor();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
  }
}
