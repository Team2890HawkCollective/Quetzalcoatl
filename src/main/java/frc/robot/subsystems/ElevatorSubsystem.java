/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Implements the methods to move the elevator and get the current position
 * of the elevator.
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
    moveElevator(RobotMap.ELEVATOR_AUTONOMOUS_SPEED);
  }

  /**
   * Moves the elevator down at *full* speed
   */
  public void elevatorDown()
  {
    moveElevator(-RobotMap.ELEVATOR_AUTONOMOUS_SPEED);
  }

  /**
   * Moves the elevator up/down at the given speed. Positive for up, Negative for down
   * @param speed
   */
  public void moveElevator(double speed)
  {
    RobotMap.elevatorSparkMax.set(speed * RobotMap.ELEVATOR_SPEED_MODIFIER);
  }

  public void xboxElevatorControl()
  {
    if (RobotMap.elevatorEncoder.getPosition() <= 20.0)
      RobotMap.ELEVATOR_SPEED_MODIFIER = RobotMap.elevatorEncoder.getPosition() * RobotMap.ELEVATOR_APPROACHING_LOWER_LIMIT_SPEED_MODIFIER;
    else
      RobotMap.ELEVATOR_SPEED_MODIFIER = RobotMap.ELEVATOR_FULL_SPEED;

    //Left Trigger goes down ONLY if we are above the lower limit
    if (RobotMap.assistantDriverController.getTriggerAxis(Hand.kLeft) > RobotMap.ELEVATOR_CONTROLLER_DEADZONE && RobotMap.elevatorEncoder.getPosition() >= RobotMap.ELEVATOR_LOWER_ENCODER_LIMIT)
      moveElevator(-RobotMap.assistantDriverController.getTriggerAxis(Hand.kLeft) * RobotMap.ELEVATOR_SPEED_MODIFIER);
    //Right Trigger goes up
    else if (RobotMap.assistantDriverController.getTriggerAxis(Hand.kRight) > RobotMap.ELEVATOR_CONTROLLER_DEADZONE)
      moveElevator(RobotMap.assistantDriverController.getTriggerAxis(Hand.kRight) * RobotMap.ELEVATOR_SPEED_MODIFIER);
    //If we aren't pressing anything, stop
    else 
      moveElevator(RobotMap.ELEVATOR_STOP_SPEED);
  }

  public double getEncoderPosition()
  {
    return RobotMap.elevatorEncoder.getPosition();
  }
}
