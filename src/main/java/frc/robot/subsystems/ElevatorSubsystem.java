/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

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
  //private int printCounter = 0;
  @Override
  /**
   * Does NADA
   */
  public void initDefaultCommand() 
  {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  
  public ElevatorSubsystem()
  {
    /*super("Elevator", RobotMap.ELEVATOR_P, RobotMap.ELEVATOR_I, RobotMap.ELEVATOR_D);
    setAbsoluteTolerance(RobotMap.ELEVATOR_PID_ABSOLUTE_TOLERANCE);
    getPIDController().setContinuous(false);*/
  }

  /**
   * Moves the elevator up at *full* speed
   */
  public void elevatorUp()
  {

    moveElevator(RobotMap.ELEVATOR_SPEED_BASE_VALUE_UP);
    //if (getUpperLimitSwitchState())
    //{
      //moveElevator(RobotMap.MOTOR_FULL_STOP);
    //}
  }

  /**
   * Moves the elevator down at *full* speed
   */
  public void elevatorDown()
  {
    moveElevator(-RobotMap.ELEVATOR_SPEED_BASE_VALUE_DOWN);
    if (getLowerLimitSwitchState())
    {
      moveElevator(RobotMap.MOTOR_FULL_STOP);
    }
  }

  /**
   * Moves the elevator up/down at the given speed. Positive for up, Negative for down
   * @param speed
   */
  public void moveElevator(double speed)
  {
    RobotMap.elevatorTalon.set(speed);
  }

  /**
   * Uses the triggers on the assistant controller to move the elevator up and down
   */
  public void xboxElevatorControl()
  {
    /*if(++printCounter % 8 == 0)
    {
      System.out.println(getEncoderPosition());
    }
    /*
    if (getEncoderPosition() <= 20.0 && getEncoderPosition() > 0.0)
    {
      RobotMap.elevatorSpeedModifier = RobotMap.elevatorEncoder.getPosition() * 
      RobotMap.ELEVATOR_APPROACHING_LOWER_LIMIT_SPEED_MODIFIER + RobotMap.ELEVATOR_SPEED_BASE_VALUE;
    }
    else
      RobotMap.elevatorSpeedModifier = RobotMap.MOTOR_FULL_SPEED - 0.2;*/

    //Left Trigger goes down ONLY if we are above the lower limit
    if (RobotMap.assistantDriverController.getTriggerAxis(Hand.kLeft) > RobotMap.ELEVATOR_CONTROLLER_DEADZONE && elevatorCanGoDown())
    {
      moveElevator(-RobotMap.assistantDriverController.getTriggerAxis(Hand.kLeft) * RobotMap.elevatorSpeedModifier);
      //System.out.println("Moving elevator down");
    }
    //Right Trigger goes up
    else if (RobotMap.assistantDriverController.getTriggerAxis(Hand.kRight) > RobotMap.ELEVATOR_CONTROLLER_DEADZONE && elevatorCanGoUp())
    {
      moveElevator(RobotMap.assistantDriverController.getTriggerAxis(Hand.kRight) * RobotMap.elevatorSpeedModifier);
      //System.out.println("Moving elevator up");
    }
    //If we aren't pressing anything, stop
    else 
      moveElevator(RobotMap.MOTOR_FULL_STOP);

    //Make sure the encoder is reset when we reach the bottom
    /*if (getLowerLimitSwitchState())
      RobotMap.elevatorEncoder.setPosition(0.0);
      */
  }

  /**
   * Whether or not the elevator can move down. 
   * @return True if the encoder is above the limit AND the limit switch is not triggered
   */
  private boolean elevatorCanGoDown()
  {
    return /*getEncoderPosition() >= RobotMap.ELEVATOR_LOWER_ENCODER_LIMIT; */!getLowerLimitSwitchState();
  }

  /**
   * Whether or not the elevator can go up
   * @return True if the encoder is less than the limit AND the limit switch is not triggered
   */
  private boolean elevatorCanGoUp()
  {
    return /*getEncoderPosition() <= RobotMap.ELEVATOR_UPPER_ENCODER_LIMIT;*/ /*!getUpperLimitSwitchState();*/ true;
  }

  /**
   * @return The current position of the encoder
   */
  /*
  public double getEncoderPosition()
  {
    return RobotMap.elevatorEncoder.getPosition();
  }
  */
  /**
   * @return True if the limit switch is triggered, false if not
   */
  public boolean getUpperLimitSwitchState()
  {
    return RobotMap.upperElevatorLimitSwitch.get();
  }

  /**
   * @return True if the limit switch is triggered, false if not
   */
  public boolean getLowerLimitSwitchState()
  {
    return false;//RobotMap.lowerElevatorLimitSwitch.get();
  }

  /*
  protected double returnPIDInput()
  {
    return getEncoderPosition();
  }
  

  protected void usePIDOutput(double output)
  {
    RobotMap.elevatorSparkMax.pidWrite(output);
  }
  */
}
