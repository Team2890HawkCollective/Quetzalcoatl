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
 * Actions the bot can do other than driving. 
 * Implements methods for the ball intake and the hatch.
 * Intake and Outtake will roll a ball up from below and release it 
 * to the docking.
 * 
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

  /**
   * Controls the manipulator using an xbox controller. X-button intakes and outtakes
   */
  public void xboxIntakeOuttake()
  {
    //Whether or not we are intaking from the bottom or front. If from front, value is negative.
    double intakeDirection = 1.0;
    if (RobotMap.assistantDriverController.getBumperPressed(Hand.kLeft))
      intakeDirection = -1.0;

    //Spins the motors when the button is pressed. THIS IS ONLY RUN ONCE UNTIL THE BUTTON IS RELEASED AND PRESSED AGAIN
    if (RobotMap.assistantDriverController.getXButtonPressed())
    {
      spinIntake(RobotMap.MANIPULATOR_FULL_SPEED * RobotMap.MANIPULATOR_SPEED_MODIFER *intakeDirection); //Spin the intake
      RobotMap.ballInIntake = !RobotMap.ballInIntake;
    }
    //Only stop the motors when the button is released
    else if (RobotMap.assistantDriverController.getXButtonReleased())
      spinIntake(RobotMap.MANIPULATOR_STOP_SPEED); //Stop the intake from spinning
    else if (RobotMap.ballIntakeStopSwitch.get() && RobotMap.ballInIntake)
      spinIntake(RobotMap.MANIPULATOR_STOP_SPEED); //Stop the intake from spinning when the ball is inside
  }
}
