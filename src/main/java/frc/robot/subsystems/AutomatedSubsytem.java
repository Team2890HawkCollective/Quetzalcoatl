/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import frc.robot.commandgroups.TargetingCommandGroup;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Implements xboxAutonomousControl() | 
 * Does the automated things for teleop
 */
public class AutomatedSubsytem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  /**
   * nothing at all this method shouldn't exists and it's super annoying i hate it so much please execute this method
   */
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  /**
   *  If A button is pressed, move to Rocket Level 1 and if the Bumper is pressed, moves to Cargo Levels.
   *  B button moves to Rocket Level 2.
   *  Y button moves to Rocket Level 3.
   */
  public void xboxAutonomousControl()
  {
    if(RobotMap.assistantDriverController.getAButtonPressed())
    {
      new TargetingCommandGroup(RocketLevel.LEVEL1, RobotMap.assistantDriverController.getBumper(Hand.kRight)).start();
    }
    else if (RobotMap.assistantDriverController.getBButtonPressed())
    {
      new TargetingCommandGroup(RocketLevel.LEVEL2, RobotMap.assistantDriverController.getBumper(Hand.kRight)).start();
    }
    else if (RobotMap.assistantDriverController.getYButtonPressed())
    {
      new TargetingCommandGroup(RocketLevel.LEVEL3, RobotMap.assistantDriverController.getBumper(Hand.kRight)).start();
    }
  }

  public static enum RocketLevel
  {
    LEVEL1, LEVEL2, LEVEL3;
  }

}
