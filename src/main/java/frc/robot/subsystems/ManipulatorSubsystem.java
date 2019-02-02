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
   * @param speed Negative for close, positive for open
   */
  public void actuateGrabber(double speed)
  {
    RobotMap.grabberTalon.set(speed);
  }

  public void xboxControl()
  {
    if (RobotMap.assistantDriverController.getAButton())
      actuateGrabber(RobotMap.MANIPULATOR_GRABBER_CLOSE);

    if (RobotMap.assistantDriverController.getBButton())
      actuateGrabber(RobotMap.MANIPULATOR_GRABBER_OPEN);

    if (!RobotMap.assistantDriverController.getAButton() && !RobotMap.assistantDriverController.getBButton())
      actuateGrabber(RobotMap.DRIVETRAIN_FULL_STOP);
  }
}
