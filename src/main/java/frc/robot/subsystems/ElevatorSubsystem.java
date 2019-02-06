/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ElevatorSubsystem extends Subsystem 
{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() 
  {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  /**
   * Moves the elevator up at *full* speed
   */
  public void elevatorUp()
  {
    moveElevator(RobotMap.ELEVATOR_FULL_SPEED * RobotMap.ELEVATOR_SPEED_MODIFIER);
  }

  /**
   * Moves the elevator down at *full* speed
   */
  public void elevatorDown()
  {
    moveElevator(-RobotMap.ELEVATOR_FULL_SPEED * RobotMap.ELEVATOR_SPEED_MODIFIER);
  }

  /**
   * Moves the elevator up/down at the given speed. Positive for up, Negative for down
   * @param speed
   */
  public void moveElevator(double speed)
  {
    RobotMap.elevatorSparkMax.set(speed * RobotMap.ELEVATOR_SPEED_MODIFIER);
  }

  public double getEncoderPosition()
  {
    return RobotMap.elevatorEncoder.getPosition();
  }
}
