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
 * Add your docs here.
 */
public class ManipulatorSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void xboxLift()
  {
    if (RobotMap.assistantDriverController.getTriggerAxis(Hand.kLeft) > 0.05)
      liftUp();
    else if (RobotMap.assistantDriverController.getTriggerAxis(Hand.kRight) > 0.05)
      liftDown();
    else
      RobotMap.liftTalon.set(0);
  }

  public void liftUp()
  {
    RobotMap.liftTalon.set(RobotMap.assistantDriverController.getTriggerAxis(Hand.kLeft));
  }

  public void liftDown()
  {
    RobotMap.liftTalon.set(RobotMap.assistantDriverController.getTriggerAxis(Hand.kRight));
  }
}
