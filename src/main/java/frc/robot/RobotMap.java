/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Talon;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  //PORT IDS
  public static final int DRIVER_CONTROLLER_PORT = 0;
	public static final int ASSISTANT_DRIVER_CONTROLLER_PORT = 1;
  public static final int LEFT_FRONT_TALON_ID = 1;
  public static final int RIGHT_FRONT_TALON_ID = 2;
  public static final int LEFT_BACK_TALON_ID = 3;
  public static final int RIGHT_BACK_TALON_ID = 4;
  public static final int CENTRAL_TALON_ID = 5;

  //Talons
  public static WPI_TalonSRX leftFrontTalon;
  public static WPI_TalonSRX rightFrontTalon;
  public static WPI_TalonSRX leftBackTalon;
  public static WPI_TalonSRX rightBackTalon;
  public static WPI_TalonSRX centralTalon;

  public static XboxController driverController;
  public static XboxController assistantDriverController;

  public static DriveTrainSubsystem driveTrainSubsystem;


  public static void init()
  {
  
    driveTrainSubsystem = new DriveTrainSubsystem();
    driverController = new XboxController(DRIVER_CONTROLLER_PORT);
		assistantDriverController = new XboxController(ASSISTANT_DRIVER_CONTROLLER_PORT);

    leftFrontTalon = new WPI_TalonSRX(LEFT_FRONT_TALON_ID);
    rightFrontTalon = new WPI_TalonSRX(RIGHT_FRONT_TALON_ID);
    leftBackTalon = new WPI_TalonSRX(LEFT_BACK_TALON_ID);
    rightBackTalon = new WPI_TalonSRX(RIGHT_BACK_TALON_ID);
    centralTalon = new WPI_TalonSRX(CENTRAL_TALON_ID);

  }


  
}