/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.*;
import com.revrobotics.CANDigitalInput.LimitSwitch;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.*;
import frc.robot.subsystems.*;

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

  //Hardware IDs//
    //Controllers
    public static final int DRIVER_CONTROLLER_PORT = 0; 
    public static final int ASSISTANT_DRIVER_CONTROLLER_PORT = 2;
    public static final int LEFT_DRIVER_JOYSTICK_PORT = 0;
    public static final int RIGHT_DRIVER_JOYSTICK_PORT = 1;

    //Drivetrain//
    public static final int LEFT_FRONT_TALON_ID = 1;
    public static final int RIGHT_FRONT_TALON_ID = 2;
    public static final int LEFT_BACK_TALON_ID = 3;
    public static final int RIGHT_BACK_TALON_ID = 4;
    public static final int CENTER_TALON_ID = 5;

    //Elevator//
    public static final int ELEVATOR_SPARK_MAX_ID = 6;
	
	

    //Manipulator//
      //Manipulator Talon
      public static final int INATKE_TALON_ID = 7;

      //Manipulator Servos
      public static final int HATCH_HOLDER_PORT = 0;

      //Manipulator Micro Switch
      public static final int BALL_INTAKE_STOP_PORT = 0;

      //Elevator Limit switches
      public static final int LOWER_ELEVATOR_LIMIT_SWTICH_PORT = 0;
      public static final int UPPER_ELEVATOR_LIMIT_SWTICH_PORT = 0;

  //Constants//
    //Drivetrain Misc Constants//
    public static final int DRIVETRAIN_ENCODER_TICKS_PER_REVOLUTION = 42;
    public static final int DRIVETRAIN_WHEEL_DIAMETER = 4;

    //Drivetrain Speed Constants//
    public static final double DRIVETRAIN_SPEED_MODIFIER = 1.0;
    public static final double DRIVETRAIN_FULL_SPEED = 1.0;
    public static final double DRIVETRAIN_FULL_STOP = 0.0;
    public static final double DRIVETRAIN_STRAFE_SPEED_MODIFIER = 0.5;
    public static final double DRIVETRAIN_CAMERA_TARGETING_SPEED_MODIFIER = 10.0; //What the value returned by the arduino will be divided by to determine speed
    public static final double DRIVETRAIN_CAMERA_TARGETING_STRAFE_SPEED_MODIFIER = 100.0;
    public static final double DRIVETRAIN_RANGEFINDER_TARGETING_SPEED_MODIFIER = 100.0; //The modifier that the rangefinder value will be divided by to determine speed

    //Elevator Constants//
      //Speed modifers
      public static final double ELEVATOR_APPROACHING_LOWER_LIMIT_SPEED_MODIFIER = 0.05;
      public static final double ELEVATOR_FULL_SPEED = 1.0;
      public static final double ELEVATOR_ENCODER_TARGET_SPEED_MODIFIER = 100.0; //What the difference between the elevator target and current elevator position will be divided by to determine speed
      public static final double ELEVATOR_STOP_SPEED = 0.0;
      public static final double ELEVATOR_CONTROLLER_DEADZONE = 0.01;
      public static final double ELEVATOR_RAMP_TIME = 0.2;
      public static final double ELEVATOR_AUTONOMOUS_SPEED = 0.75; 
	
	

      //Elevator limits
      public static final double ELEVATOR_LOWER_ENCODER_LIMIT = 5.0; //The lower limit of the elevator in encoder ticks
      public static final double ELEVATOR_UPPER_ENCODER_LIMIT = 10000.0; //The upper limit of the elevator in encoder ticks

      //Encoder values for the different rocket levels
      public static final double ELEVATOR_LEVEL_1_HATCH_VALUE = 0.0;
      public static final double ELEVATOR_LEVEL_2_HATCH_VALUE = 0.0;
      public static final double ELEVATOR_LEVEL_3_HATCH_VALUE = 0.0;
      public static final double ELEVATOR_LEVEL_1_CARGO_VALUE = 54.0;
      public static final double ELEVATOR_LEVEL_2_CARGO_VALUE = 126.0;
      public static final double ELEVATOR_LEVEL_3_CARGO_VALUE = 0.0;

    //Manipulator Constants//
    public static final double MANIPULATOR_FULL_SPEED = 1.0;
    public static final double MANIPULATOR_DEFAULT_SPEED = 1.0; 
    public static final double MANIPULATOR_TIME_TO_RELEASE_CARGO = 1.0;
    public static final double MANIPULATOR_STOP_SPEED = 0.0;
    public static final double MANIPULATOR_SPEED_MODIFER = 0.375;

    //Hatch Holder Constants//
    public static final double HATCH_HOLDER_SERVO_RELEASE = 0.5;
    public static final double HATCH_FOLDER_SERVO_GRAB = 0.0;

    //Misc Constants//
    public static final double MAX_VELOCITY = 17.5;
    public static final double kV = 1.0 / MAX_VELOCITY;

  //Flags
  public static boolean ballInIntake = true;

  //Non-constant speed modifiers//
  public static double ELEVATOR_SPEED_MODIFIER = 1.0;

  //Objects//
    //Drivetrain//
    public static WPI_TalonSRX leftFrontTalon;
    public static WPI_TalonSRX rightFrontTalon;
    public static WPI_TalonSRX leftBackTalon;
    public static WPI_TalonSRX rightBackTalon;
    public static WPI_TalonSRX centralTalon;

    //Intake//
    public static WPI_TalonSRX intakeTalon;

    //Elevator//
    public static CANSparkMax elevatorSparkMax;
    public static CANEncoder elevatorEncoder;

    //Servos//
    public static Servo hatchHolder;

    //Micro Switches
      //Manipulator
      public static DigitalInput ballIntakeStopSwitch;

      //Elevator
      public static DigitalInput lowerElevatorLimitSwitch;
      public static DigitalInput upperElevatorLimitSwitch;

    //Sensors//
    public static AHRS navX; //Gyro. The purple thingy on the rio

    //Joysticks//
    public static Joystick leftDriverJoystick;
    public static Joystick rightDriverJoystick;

    //Controllers//
    public static XboxController driverController;
    public static XboxController assistantDriverController;

    //Subsystems//
    public static DriveTrainSubsystem driveTrainSubsystem;
    public static ElevatorSubsystem elevatorSubsystem;
    public static ManipulatorSubsystem manipulatorSubsystem;

    //Arduino//
    public static SerialPort arduino;

  public static void init()
  {
    driveTrainSubsystem = new DriveTrainSubsystem();
    elevatorSubsystem = new ElevatorSubsystem();
    manipulatorSubsystem = new ManipulatorSubsystem();

    driverController = new XboxController(DRIVER_CONTROLLER_PORT);
		assistantDriverController = new XboxController(ASSISTANT_DRIVER_CONTROLLER_PORT);

    leftFrontTalon = new WPI_TalonSRX(LEFT_FRONT_TALON_ID);
    rightFrontTalon = new WPI_TalonSRX(RIGHT_FRONT_TALON_ID);
    leftBackTalon = new WPI_TalonSRX(LEFT_BACK_TALON_ID);
    rightBackTalon = new WPI_TalonSRX(RIGHT_BACK_TALON_ID);
    centralTalon = new WPI_TalonSRX(CENTER_TALON_ID);

    intakeTalon = new WPI_TalonSRX(INATKE_TALON_ID);

    elevatorSparkMax = new CANSparkMax(ELEVATOR_SPARK_MAX_ID, MotorType.kBrushless);
    elevatorEncoder = elevatorSparkMax.getEncoder();

    hatchHolder = new Servo(HATCH_HOLDER_PORT);

    ballIntakeStopSwitch = new DigitalInput(BALL_INTAKE_STOP_PORT);

    lowerElevatorLimitSwitch = new DigitalInput(LOWER_ELEVATOR_LIMIT_SWTICH_PORT);
    upperElevatorLimitSwitch = new DigitalInput(UPPER_ELEVATOR_LIMIT_SWTICH_PORT);

    leftDriverJoystick = new Joystick(LEFT_DRIVER_JOYSTICK_PORT);
    rightDriverJoystick = new Joystick(RIGHT_DRIVER_JOYSTICK_PORT);

    navX = new AHRS(SPI.Port.kMXP);

    leftFrontTalon.setInverted(true);
    leftBackTalon.setInverted(true);
    centralTalon.setInverted(true);
    intakeTalon.setInverted(true);
    elevatorSparkMax.setInverted(true);

    leftBackTalon.follow(leftFrontTalon);
    rightBackTalon.follow(rightFrontTalon);

    elevatorSparkMax.setClosedLoopRampRate(ELEVATOR_RAMP_TIME);
    elevatorSparkMax.setIdleMode(IdleMode.kBrake);
    
    //Set names and subsystems
    driveTrainSubsystem.setName(driveTrainSubsystem.getSubsystem(), "DriveTrainSubsystem");
    manipulatorSubsystem.setName(manipulatorSubsystem.getSubsystem(), "ManipulatorSubsystem");
    elevatorSubsystem.setName(elevatorSubsystem.getSubsystem(), "ElevatorSubsystem");

    leftFrontTalon.setName(driveTrainSubsystem.getSubsystem(), "LeftFrontTalon");
    rightFrontTalon.setName(driveTrainSubsystem.getSubsystem(), "RightFrontTalon");
    leftBackTalon.setName(driveTrainSubsystem.getSubsystem(), "LeftBackTalon");
    rightBackTalon.setName(driveTrainSubsystem.getSubsystem(), "RightBackTalon");
    centralTalon.setName(driveTrainSubsystem.getSubsystem(), "CentralTalon");

    intakeTalon.setName(manipulatorSubsystem.getSubsystem(), "IntakeTalon");
    hatchHolder.setName(manipulatorSubsystem.getSubsystem(), "HatchHolder");
  }
}
