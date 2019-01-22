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
public class DriveTrainSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() 
  {
    // Set the default command for a subsystem here.
       //setDefaultCommand(new XboxDriveCommand());
  }
  
  public void xboxArcadeDrive()
	{
    //Forwards/Backwards
    xboxArcadeForwardBackwards();

    //Strafe left/right
    RobotMap.centralTalon.set(RobotMap.driverController.getX(Hand.kRight) * RobotMap.DRIVETRAIN_SPEED_MODIFIER);

    //Rotate right
    if(RobotMap.driverController.getTriggerAxis(Hand.kRight) > 0.05)
    {
      leftTalonsNegative();
    }
      
    //Rotate left
    if(RobotMap.driverController.getTriggerAxis(Hand.kLeft) > 0.05)
    {
      rightTalonsNegative();
    }
  }

  public void xboxTankDrive()
  {
    //Left side
    xboxTank(RobotMap.driverController, RobotMap.leftFrontTalon, RobotMap.leftBackTalon, Hand.kLeft);
    //Right side
    xboxTank(RobotMap.driverController, RobotMap.rightFrontTalon, RobotMap.rightBackTalon, Hand.kRight);

    //Strafe left/right
    if (RobotMap.driverController.getTriggerAxis(Hand.kLeft) > 0.05)
      RobotMap.centralTalon.set(RobotMap.driverController.getTriggerAxis(Hand.kLeft) * RobotMap.DRIVETRAIN_SPEED_MODIFIER);
    else if (RobotMap.driverController.getTriggerAxis(Hand.kRight) > 0.05)
      RobotMap.centralTalon.set(RobotMap.driverController.getTriggerAxis(Hand.kRight) * RobotMap.DRIVETRAIN_SPEED_MODIFIER * RobotMap.DRIVETRAIN_REVERSE_MODIFIER);
    else
      RobotMap.centralTalon.set(0);
  }


  
  public void JoystickArcadeDrive()
  {
    //Forwards/backwards
    joystickForwardsBackwards(RobotMap.leftDriverJoystick);

    //Strafe left/right
    RobotMap.centralTalon.set(RobotMap.rightDriverJoystick.getX() * RobotMap.DRIVETRAIN_SPEED_MODIFIER);

    //Rotate left
    if (RobotMap.leftDriverJoystick.getTrigger())
    {
      leftTalonsNegative();
    }

    //Rotate right
    if (RobotMap.rightDriverJoystick.getTrigger())
    {
      rightTalonsNegative();
    }
  }

  public void joystickTankDrive()
  {
    //Left side
    joyTank(RobotMap.leftDriverJoystick, RobotMap.leftFrontTalon, RobotMap.leftBackTalon);
    //Right side
    joyTank(RobotMap.rightDriverJoystick, RobotMap.rightFrontTalon, RobotMap.rightBackTalon);

    //Strafe right/left
    if (RobotMap.rightDriverJoystick.getTrigger())
      RobotMap.centralTalon.set(RobotMap.DRIVETRAIN_FULL_SPEED * RobotMap.DRIVETRAIN_SPEED_MODIFIER);
    else if (RobotMap.leftDriverJoystick.getTrigger())
      RobotMap.centralTalon.set(RobotMap.DRIVETRAIN_FULL_SPEED * RobotMap.DRIVETRAIN_SPEED_MODIFIER * RobotMap.DRIVETRAIN_REVERSE_MODIFIER);
    else
      RobotMap.centralTalon.set(0);
  }

  //Sets right talons negative
  public void rightTalonsNegative()
  {
    RobotMap.leftFrontTalon.set(RobotMap.DRIVETRAIN_FULL_SPEED * RobotMap.DRIVETRAIN_SPEED_MODIFIER);
    RobotMap.rightFrontTalon.set(RobotMap.DRIVETRAIN_FULL_SPEED * RobotMap.DRIVETRAIN_SPEED_MODIFIER * RobotMap.DRIVETRAIN_REVERSE_MODIFIER);
    RobotMap.leftBackTalon.set(RobotMap.DRIVETRAIN_FULL_SPEED * RobotMap.DRIVETRAIN_SPEED_MODIFIER);
    RobotMap.rightBackTalon.set(RobotMap.DRIVETRAIN_FULL_SPEED * RobotMap.DRIVETRAIN_SPEED_MODIFIER * RobotMap.DRIVETRAIN_REVERSE_MODIFIER);
  }

  //sets left talons negative
  public void leftTalonsNegative()
  {
    RobotMap.leftFrontTalon.set(RobotMap.DRIVETRAIN_FULL_SPEED * RobotMap.DRIVETRAIN_SPEED_MODIFIER * RobotMap.DRIVETRAIN_REVERSE_MODIFIER);
    RobotMap.rightFrontTalon.set(RobotMap.DRIVETRAIN_FULL_SPEED * RobotMap.DRIVETRAIN_SPEED_MODIFIER);
    RobotMap.leftBackTalon.set(RobotMap.DRIVETRAIN_FULL_SPEED * RobotMap.DRIVETRAIN_SPEED_MODIFIER * RobotMap.DRIVETRAIN_REVERSE_MODIFIER);
    RobotMap.rightBackTalon.set(RobotMap.DRIVETRAIN_FULL_SPEED * RobotMap.DRIVETRAIN_SPEED_MODIFIER);
  }

  //tank drive for joysticks
  public void joyTank(Joystick x, WPI_TalonSRX y, WPI_TalonSRX z)
  {
    y.set(x.getY() * RobotMap.DRIVETRAIN_SPEED_MODIFIER);
    z.set(x.getY() * RobotMap.DRIVETRAIN_SPEED_MODIFIER);
  }

  //tank drive for xbox
  public void xboxTank(XboxController x, WPI_TalonSRX y, WPI_TalonSRX z, Hand h)
  {
    y.set(x.getY(h) * RobotMap.DRIVETRAIN_SPEED_MODIFIER);
    z.set(x.getY(h) * RobotMap.DRIVETRAIN_SPEED_MODIFIER);
  }

  //forwards && backwards for arcade xbox
  public void xboxArcadeForwardBackwards()
  {
    RobotMap.leftFrontTalon.set(RobotMap.driverController.getY(Hand.kLeft) * RobotMap.DRIVETRAIN_SPEED_MODIFIER);
    RobotMap.rightFrontTalon.set(RobotMap.driverController.getY(Hand.kLeft) * RobotMap.DRIVETRAIN_SPEED_MODIFIER);
    RobotMap.leftBackTalon.set(RobotMap.driverController.getY(Hand.kLeft) * RobotMap.DRIVETRAIN_SPEED_MODIFIER);
    RobotMap.rightBackTalon.set(RobotMap.driverController.getY(Hand.kLeft) * RobotMap.DRIVETRAIN_SPEED_MODIFIER);
  }

  //forwards & backwards
  public void joystickForwardsBackwards(Joystick stick)
  {
    RobotMap.leftFrontTalon.set(stick.getY() * RobotMap.DRIVETRAIN_SPEED_MODIFIER);
    RobotMap.rightFrontTalon.set(stick.getY() * RobotMap.DRIVETRAIN_SPEED_MODIFIER);
    RobotMap.leftBackTalon.set(stick.getY() * RobotMap.DRIVETRAIN_SPEED_MODIFIER);
    RobotMap.rightBackTalon.set(stick.getY() * RobotMap.DRIVETRAIN_SPEED_MODIFIER);
  }
}

