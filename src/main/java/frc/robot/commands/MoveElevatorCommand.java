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
    
    if (RobotMap.elevatorSubsystem.getEncoderPosition() < encoderTarget)
    {
      goingDown = false;
    }
  }

  /**
   *  Called just before this Command runs the first time
   */
  @Override
  protected void initialize() {}

  /**
   * Moves elevator according to whether or not the encoder is higher or lower than the target.
   */
  @Override
  protected void execute() 
  {
    if (RobotMap.elevatorSubsystem.getEncoderPosition() > encoderTarget)
    {
      RobotMap.elevatorSubsystem.elevatorDown();
    }
    else if (RobotMap.elevatorSubsystem.getEncoderPosition() < encoderTarget)
    {
      RobotMap.elevatorSubsystem.elevatorUp();
    }

    System.out.println(RobotMap.elevatorEncoder.getPosition());
  }

  /**
   * @return true if elevator has reached the target
   */
  @Override
  protected boolean isFinished() 
  {
    if (goingDown)
    {
      return RobotMap.elevatorSubsystem.getEncoderPosition() <= encoderTarget;
    }
    else
    {
      return RobotMap.elevatorSubsystem.getEncoderPosition() >= encoderTarget;
    }
  }

  // Called once after isFinished returns true
  /**
   * When command ends, stops the motor
   */
  @Override
  protected void end() 
  {
    //RobotMap.elevatorEncoder.setPosition(encoderTarget);
    RobotMap.elevatorSparkMax.stopMotor();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
  }
}
