/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

public class RaiseElevatorCommand extends Command 
{
  private double encoderTarget;

  /**
   * PRECONDITION: level is a number between 0 and 3 inclusive
   * @param level The level to lift to. (0 for cargo ship)
   * @param cargo Whether or not we are lifting cargo
   */
  public RaiseElevatorCommand(int level, boolean cargo) 
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(RobotMap.elevatorSubsystem);

    //If we are carrying cargo
    if (cargo)
    {
      switch (level) 
      {
        case 1:
          encoderTarget = RobotMap.ELEVATOR_LEVEL_1_CARGO_VALUE;
          break;
        case 2:
          encoderTarget = RobotMap.ELEVATOR_LEVEL_2_CARGO_VALUE;
          break;
        case 3:
          encoderTarget = RobotMap.ELEVATOR_LEVEL_3_CARGO_VALUE;
      }
    }
    else
    {
      if (level == 0 || level == 1)
        encoderTarget = 1.0; //1st level rocket ship encoder value
      else if (level == 2)
        encoderTarget = 2.0; //2nd level rocket ship encoder value;
      else     
        encoderTarget = 3.0; //3rd level rocket ship encoder value;
    }
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    //Lower elevator to minimum
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    RobotMap.elevatorSubsystem.elevatorUp();
    System.out.println(RobotMap.elevatorEncoder.getPosition());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    return RobotMap.elevatorSubsystem.getEncoderPosition() >= encoderTarget;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
    RobotMap.elevatorEncoder.setPosition(encoderTarget);
    RobotMap.elevatorSparkMax.stopMotor();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
  }
}
