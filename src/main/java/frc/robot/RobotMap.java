/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ManipulatorSubsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

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
  public static final int DRIVER_CONTROLLER_PORT = 2;
  public static final int ASSISTANT_DRIVER_CONTROLLER_PORT = 2;
  public static final int LEFT_DRIVER_JOYSTICK_PORT = 0;
  public static final int RIGHT_DRIVER_JOYSTICK_PORT = 1;
  public static final int LEFT_FRONT_TALON_ID = 1;
  public static final int RIGHT_FRONT_TALON_ID = 2;
  public static final int LEFT_BACK_TALON_ID = 3;
  public static final int RIGHT_BACK_TALON_ID = 4;
  public static final int CENTER_TALON_ID = 5;
<<<<<<< HEAD
  public static final int LIFT_TALON_ID = 6;
=======
  public static final int GRABBER_TALON_ID = 6;
>>>>>>> GrabberTesting

  public static final double DRIVETRAIN_SPEED_MODIFIER = 1.0;
  public static final double DRIVETRAIN_FULL_SPEED = 1.0;
  public static final double DRIVETRAIN_FULL_STOP = 0.0;
  public static final double DRIVETRAIN_REVERSE_MODIFIER = -1.0;
  public static final double DRIVETRAIN_STRAFE_SPEED_MODIFIER = 0.3;

  public static final double DRIVETRAIN_RAMP_TIME = 0.0;
  public static final double DRIVETRAIN_STRAFE_RAMP_TIME = 1.0;

  public static final double MANIPULATOR_GRABBER_OPEN = 1.0;
  public static final double MANIPULATOR_GRABBER_CLOSE = -1.0;

  public static boolean grabberFlag = true; //True if open, false if closed

  //Talons
  public static WPI_TalonSRX leftFrontTalon;
  public static WPI_TalonSRX rightFrontTalon;
  public static WPI_TalonSRX leftBackTalon;
  public static WPI_TalonSRX rightBackTalon;
<<<<<<< HEAD
  public static WPI_TalonSRX centralTalon;
  public static CANSparkMax liftTalon;
=======
  public static CANSparkMax centralTalon;

  public static WPI_TalonSRX grabberTalon;

  public static WPI_TalonSRX leftGrabberTalon;
  public static WPI_TalonSRX rightGrabberTalon;
>>>>>>> GrabberTesting

  public static Joystick leftDriverJoystick;
  public static Joystick rightDriverJoystick;

  public static JoystickButton triggerLeft;
  public static JoystickButton triggerRight;

  //Controllers
  public static XboxController driverController;
  public static XboxController assistantDriverController;

  //Subsystems
  public static DriveTrainSubsystem driveTrainSubsystem;
  public static ManipulatorSubsystem manipulatorSubsystem;
<<<<<<< HEAD

=======
>>>>>>> GrabberTesting

  public static void init()
  {
    driveTrainSubsystem = new DriveTrainSubsystem();
    manipulatorSubsystem = new ManipulatorSubsystem();
<<<<<<< HEAD
=======

>>>>>>> GrabberTesting
    driverController = new XboxController(DRIVER_CONTROLLER_PORT);
		assistantDriverController = new XboxController(ASSISTANT_DRIVER_CONTROLLER_PORT);

    leftFrontTalon = new WPI_TalonSRX(LEFT_FRONT_TALON_ID);
    rightFrontTalon = new WPI_TalonSRX(RIGHT_FRONT_TALON_ID);
    leftBackTalon = new WPI_TalonSRX(LEFT_BACK_TALON_ID);
    rightBackTalon = new WPI_TalonSRX(RIGHT_BACK_TALON_ID);
<<<<<<< HEAD
    centralTalon = new WPI_TalonSRX(CENTER_TALON_ID);
    liftTalon = new CANSparkMax(LIFT_TALON_ID, MotorType.kBrushless);
=======
    centralTalon = new CANSparkMax(CENTER_TALON_ID, MotorType.kBrushless);

    grabberTalon = new WPI_TalonSRX(GRABBER_TALON_ID);
>>>>>>> GrabberTesting

    leftDriverJoystick = new Joystick(LEFT_DRIVER_JOYSTICK_PORT);
    rightDriverJoystick = new Joystick(RIGHT_DRIVER_JOYSTICK_PORT);

    leftFrontTalon.setInverted(true);
    leftBackTalon.setInverted(true);

    leftBackTalon.follow(leftFrontTalon);
    rightBackTalon.follow(rightFrontTalon);

    leftFrontTalon.configOpenloopRamp(DRIVETRAIN_RAMP_TIME);
    rightFrontTalon.configOpenloopRamp(DRIVETRAIN_RAMP_TIME);

    centralTalon.setRampRate(DRIVETRAIN_STRAFE_RAMP_TIME);
  }
}
