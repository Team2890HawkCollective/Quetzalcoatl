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

  public void xboxElevatorControl()
  {
    //Ensure we don't go too low or too high
    if (RobotMap.elevatorEncoder.getPosition() >= RobotMap.ELEVATOR_LOWER_ENCODER_LIMIT)
    {
      //Left Trigger goes down
      if (RobotMap.assistantDriverController.getTriggerAxis(Hand.kLeft) > RobotMap.ELEVATOR_CONTROLLER_DEADZONE)
        moveElevator(-RobotMap.assistantDriverController.getTriggerAxis(Hand.kLeft));
      //Right Trigger goes up
      else if (RobotMap.assistantDriverController.getTriggerAxis(Hand.kRight) > RobotMap.ELEVATOR_CONTROLLER_DEADZONE)
        moveElevator(RobotMap.assistantDriverController.getTriggerAxis(Hand.kRight));
      //If we aren't pressing anything, stop
      else 
        moveElevator(RobotMap.ELEVATOR_STOP_SPEED);
    }
    else
      moveElevator(RobotMap.ELEVATOR_STOP_SPEED);  
  }

  public double getEncoderPosition()
  {
    return RobotMap.elevatorEncoder.getPosition();
  }
}
