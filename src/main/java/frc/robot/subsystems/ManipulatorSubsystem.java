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

  /**
   * Rotates the servo that tensions the net on the manipulator mechanism. Determines if ball will be shot out.
   * True to tension, false to detension
   * @param tension
   */
  public void tensionBallIntake(boolean tension)
  {
    if (tension)
      RobotMap.ballIntakeTensioner.set(RobotMap.BALL_INTAKE_TENSION_ON);
    else
      RobotMap.ballIntakeTensioner.set(RobotMap.BALL_INTAKE_TENSION_OFF);
  }

  /**
   * Controls the manipulator using an xbox controller. X-button intakes and outtakes
   */
  public void xboxIntakeOuttake()
  {
    //Spins the motors when the button is pressed. THIS IS ONLY RUN ONCE UNTIL THE BUTTON IS RELEASED AND PRESSED AGAIN
    if (RobotMap.assistantDriverController.getXButtonPressed())
    {
      tensionBallIntake(RobotMap.ballOuttake);
      spinIntake(RobotMap.MANIPULATOR_FULL_SPEED * RobotMap.MANIPULATOR_SPEED_MODIFER); //Spin the intake
      RobotMap.ballOuttake = !RobotMap.ballOuttake; //Change to either intake mode or not intake mode
    }
    //Only stop the motors when the button is released
    else if (RobotMap.assistantDriverController.getXButtonReleased())
      spinIntake(RobotMap.MANIPULATOR_STOP_SPEED); //Stop the intake from spinning
  }
}
