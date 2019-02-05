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

  public void elevatorUp()
  {
    elevatorUp(RobotMap.ELEVATOR_FULL_SPEED * RobotMap.ELEVATOR_SPEED_MODIFIER);
  }

  public void elevatorUp(double speed)
  {
    RobotMap.elevatorSparkMax.set(speed * RobotMap.ELEVATOR_SPEED_MODIFIER);
  }

  public void elevatorDown()
  {
    elevatorUp(-RobotMap.ELEVATOR_FULL_SPEED * RobotMap.ELEVATOR_SPEED_MODIFIER);
  }

  public void elevatorDown(double speed)
  {
    elevatorUp(-speed);
  }

  public double getEncoderPosition()
  {
    return RobotMap.elevatorEncoder.getPosition();
  }
}
