/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * Add your docs here.
 */
public class DriveTrainSubsystem extends Subsystem 
{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() 
  {
    // Set the default command for a subsystem here.
       //setDefaultCommand(new XboxDriveCommand());
  }
  
  /**
   * Runs the drivetrain using the ArcadeMode drive-scheme using Xbox Controllers
   */
  public void xboxArcadeDrive()
	{
    double strafeSpeed = 0.0;

    //A negative strafe speed value means that we want to strafe left.
    if (RobotMap.driverController.getTriggerAxis(Hand.kLeft) > 0.05)
      strafeSpeed = -RobotMap.driverController.getTriggerAxis(Hand.kLeft) * RobotMap.DRIVETRAIN_SPEED_MODIFIER;
    else if (RobotMap.driverController.getTriggerAxis(Hand.kRight) > 0.05)
      strafeSpeed = RobotMap.driverController.getTriggerAxis(Hand.kRight) * RobotMap.DRIVETRAIN_SPEED_MODIFIER;

    arcadeDrive(RobotMap.driverController.getY(Hand.kLeft) * RobotMap.DRIVETRAIN_SPEED_MODIFIER, RobotMap.driverController.getX(Hand.kRight) * RobotMap.DRIVETRAIN_SPEED_MODIFIER, strafeSpeed);
  }

  /**
   * Runs the drivetrain using the TankMode drive-scheme using Xbox Controllers
   */
  public void xboxTankDrive()
  {
    double strafeSpeed = 0.0;

    //A negative strafe speed means we want to strafe left
    if (RobotMap.driverController.getTriggerAxis(Hand.kLeft) > 0.05)
      strafeSpeed = -RobotMap.driverController.getTriggerAxis(Hand.kLeft) * RobotMap.DRIVETRAIN_SPEED_MODIFIER;
    else if (RobotMap.driverController.getTriggerAxis(Hand.kRight) > 0.05)
      strafeSpeed = RobotMap.driverController.getTriggerAxis(Hand.kRight) * RobotMap.DRIVETRAIN_SPEED_MODIFIER;

    tankDrive(RobotMap.driverController.getY(Hand.kLeft), RobotMap.driverController.getY(Hand.kLeft), strafeSpeed);
  }

  /**
   * Runs the drivetrain using the ArcadeMode drive-scheme using Joysticks
   */
  public void joystickArcadeDrive()
  {
    double strafeSpeed = 0.0;

    //A negative turning speed value means that we want to rotate left.
    if (RobotMap.leftDriverJoystick.getTrigger())
      strafeSpeed = -RobotMap.DRIVETRAIN_FULL_SPEED * RobotMap.DRIVETRAIN_SPEED_MODIFIER;
    else if (RobotMap.rightDriverJoystick.getTrigger())
      strafeSpeed = RobotMap.DRIVETRAIN_FULL_SPEED * RobotMap.DRIVETRAIN_SPEED_MODIFIER;

    arcadeDrive(RobotMap.leftDriverJoystick.getY(),  RobotMap.rightDriverJoystick.getX(), strafeSpeed);
  }

  /**
   * Runs the drivetrain using the TankMode drive-scheme using Joysticks
   */
  public void joystickTankDrive()
  {
    double strafeSpeed = 0.0;

    //A negative strafe speed means we want to strafe left
    if (RobotMap.leftDriverJoystick.getTrigger())
      strafeSpeed = -RobotMap.DRIVETRAIN_FULL_SPEED * RobotMap.DRIVETRAIN_SPEED_MODIFIER;
    else if (RobotMap.rightDriverJoystick.getTrigger())
      strafeSpeed = RobotMap.DRIVETRAIN_FULL_SPEED * RobotMap.DRIVETRAIN_SPEED_MODIFIER;

    tankDrive(RobotMap.driverController.getY(Hand.kLeft), RobotMap.driverController.getY(Hand.kLeft), strafeSpeed);
  }

  /**
   * Runs the robot using a tank drive scheme
   * @param leftSpeed Speed of left-side of robot. Positive for forward, negative for reverse
   * @param rightSpeed Speed of right-side of robot. Positive for forward, negative for reverse
   * @param strafeSpeed The speed at which the robot will strafe. Positive for right strafe, negative for left strafe
   */
  public void tankDrive(double leftSpeed, double rightSpeed, double strafeSpeed)
  {
    RobotMap.leftFrontTalon.set(leftSpeed);
    RobotMap.rightFrontTalon.set(rightSpeed);
    RobotMap.centralTalon.set(strafeSpeed);
  }

  /**
   * Runs the robot using an arcade drive scheme
   * @param forwardsSpeed The speed the robot will go in the forwards direction. Positive for forward, negative for reverse
   * @param turningSpeed The speed at which the robot will rotate. Positive for right-rotation, negative for left-rotation
   * @param strafeSpeed The speed at which the robot will strafe. Positive for right strafe, negative for left strafe
   */
  public void arcadeDrive(double forwardsSpeed, double turningSpeed, double strafeSpeed)
  {
    tankDrive(forwardsSpeed, forwardsSpeed, strafeSpeed);
    if (turningSpeed >= 0.05)
      tankDrive(-turningSpeed, turningSpeed, strafeSpeed);
    else if (turningSpeed <= -0.05)
      tankDrive(turningSpeed, -turningSpeed, strafeSpeed);
  }
}

