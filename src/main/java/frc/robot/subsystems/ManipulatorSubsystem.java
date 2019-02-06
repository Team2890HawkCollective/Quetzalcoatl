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
public class ManipulatorSubsystem extends Subsystem 
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
   * Spins the manipulator motors.
   * @param speed Positive for forward, negative for reverse
   */
  public void spinIntake(double speed)
  {
    RobotMap.intakeTalon.set(speed);
  }

  /**
   * Releases any grabbed Hatches
   */
  public void releaseHatch()
  {
    RobotMap.hatchHolder.set(RobotMap.HATCH_HOLDER_SERVO_RELEASE);
  }

  public void xboxIntakeOuttake()
  {
    if (RobotMap.assistantDriverController.getXButton())
      spinIntake(RobotMap.MANIPULATOR_FULL_SPEED * RobotMap.MANIPULATOR_SPEED_MODIFER);
    else
      spinIntake(RobotMap.MANIPULATOR_STOP_SPEED);
  }
}
