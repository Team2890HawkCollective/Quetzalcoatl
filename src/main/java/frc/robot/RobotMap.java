/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.subsystems.AutomatedSubsytem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ManipulatorSubsystem;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

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
    /**
     * The number assigned to the driver xbox controller when it's plugged in
     */
    public static final int DRIVER_CONTROLLER_PORT = 2;
    /**
     * The number assigned to the assistant driver xbox controller when it's plugged into the computer
     */
    public static final int ASSISTANT_DRIVER_CONTROLLER_PORT = 3;
    /**
     * The number assigned to the driver's left joystick when using joystick driver
     */
    public static final int LEFT_DRIVER_JOYSTICK_PORT = 0;
    /**
     * The number assigned to the driver's right joystick when using joystick driver
     */
    public static final int RIGHT_DRIVER_JOYSTICK_PORT = 1;

    //Drivetrain//
    /**
     * The CAN ID assigned to the left front talon
     */
    public static final int LEFT_FRONT_TALON_ID = 1;
    /**
     * The CAN ID assigned to the right front talon
     */
    public static final int RIGHT_FRONT_TALON_ID = 2;
    /**
     * The CAN ID assigned to the left back talon
     */
    public static final int LEFT_BACK_TALON_ID = 3;
    /**
     * The CAN ID assigned to the right back talon
     */
    public static final int RIGHT_BACK_TALON_ID = 4;
    /**
     * The CAN ID assigned to the center talon
     */
    public static final int CENTER_TALON_ID = 5;
    /**
     * The CAN ID assigned to move to center motor up and down
     */
    public static final int CENTER_TALON_MOVE_ID = 10;
    //Elevator//
    /**
     * Talon ID assigned to lift the elevator
     */
    public static final int ELEVATOR_TALON_ID = 7;

    //Manipulator//
      //Cargo Intake
        //Manipulator Talon
        /**
         * The CAN ID assigned to the intake
         */
        public static final int INATKE_TALON_ID = 5;

        //Manipulator Micro Switch
        /**
         * The DIO port on the roborio which the ball sucky wucky is plugged into
         */
        public static final int BALL_INTAKE_STOP_PORT = 2;

      //Hatch Holder
        //Hatch Holder Talon
        /**
         * The CAN ID assigned to the hatch holder talon
         */
        public static final int HATCH_HOLDER_TALON_ID = 8;


        //Hatch Holder Micro Switches
        /**
         * The DIO port on the roborio into which the hatch holder's upper position limit is to be plugged into
         */
        public static final int HATCH_HOLDER_GRABBING_LIMIT_SWITCH_PORT = 3;
        /**
         * The DIO port on the roborio into which the hatch holder's middle position limit is to be plugged into
         */
        public static final int HATCH_HOLDER_RELEASING_LIMIT_SWITCH_PORT = 4;

      //Elevator Limit switches
      /**
       * The DIO port on the roborio into which the Lower Elevator limit switch is to be plugged into
       */
      public static final int LOWER_ELEVATOR_LIMIT_SWTICH_PORT = 0;
      /**
       * The DIO port on the roborio into which the Upper Elevator limit switch is to be plugged into
       */
      public static final int UPPER_ELEVATOR_LIMIT_SWTICH_PORT = 1;

  //Constants//
    //Controllers//
    /**
     * The POV ID of the dpad on the xbox controller
     */
    public static final int DPAD_ID = 0;
    /**
     * The value returned of the getPov() method if the dpad isn't pressed
     */
    public static final int DPAD_NOT_PRESSED = -1;
    /**
     * The value returned of the getPov() method if up on the dpad is pressed
     */
    public static final int DPAD_UP = 0;
    /**
     * The value returned of the getPov() method if right on the dpad is pressed
     */
    public static final int DPAD_RIGHT = 90;
    /**
     * The value returned of the getPov() method if bottom on the dpad is pressed
     */
    public static final int DPAD_BOTTOM = 180;
    /**
     * The value returned of the getPov() method if left on the dpad is pressed
     */
    public static final int DPAD_LEFT = 270;

    //Drivetrain Speed Constants//
    /**
     * The value by which any speed being sent to the drivetrain will be multiplied by
     */
    public static final double DRIVETRAIN_SPEED_MODIFIER = 1.0;
    /**
     * The value by which any speed being sent to the center wheel will be multiplied by
     */
    public static final double DRIVETRAIN_STRAFE_SPEED_MODIFIER = 0.5;
    /**
     * @deprecated replaced by general full speed modifier |
     * full speed value assigned to drive train
     */
    public static final double DRIVETRAIN_FULL_SPEED = 1.0;
    /**
     * @deprecated replaced by general stop speed modifier |
     * stop speed assigned to drive train
     */
    public static final double DRIVETRAIN_STOP_SPEED = 0.0;
    
    //Elevator Constants//
    /**
     * @deprecated replaced by general full speed modifier |
     * full speed assigned to elevator
     */
    public static final double ELEVATOR_FULL_SPEED = 1.0;
    /**
     * @deprecated replaced by general stop speed modifier |
     * stop speed assigned to elevator
     */
    public static final double ELEVATOR_STOP_SPEED = 0.0;
    /**
     * Time elevator take to go from 0 speed to full speed
     */
    public static final double ELEVATOR_RAMP_TIME = 0.6;
    /**
     * Value that makes the elevator not move
     */
    public static final double ELEVATOR_CONTROLLER_DEADZONE = 0.05;

    /**
     * Slows the elevator when it is close to the limit
     */
    public static final double ELEVATOR_APPROACHING_LOWER_LIMIT_SPEED_MODIFIER = 0.025;
    /**
     * Base speed value for the elevator lift
     */
    public static final double ELEVATOR_SPEED_BASE_VALUE = 1;
    /**
     * Base speed value to move the values up
     */
    public static final double ELEVATOR_SPEED_BASE_VALUE_UP = 1;
    /**
     * Base speed value to move down
     */
    public static final double ELEVATOR_SPEED_BASE_VALUE_DOWN = 0.5;

    //Manipulator Constants//
      //Cargo Intake
      /**
       * @deprecated replaced by general full speed modifier |
       * full speed assigned to manipulator
       */
      public static final double MANIPULATOR_FULL_SPEED = 1.0;
      /**
       * default speed assigned to manipulator
       */
      public static final double MANIPULATOR_DEFAULT_SPEED = 1.0; 
      /**
       * Value assigned to the time to release cargo
       */
      public static final double MANIPULATOR_TIME_TO_RELEASE_CARGO = 1.0;
      /**
       * @deprecated replaced by general stop speed modifier |
       * stop speed assigned to manipulator
       */
      public static final double MANIPULATOR_STOP_SPEED = 0.0;

      /**
       * The slow speed for intake
       */
      public static final double MANIPULATOR_SLOW_SPEED = 0.4;

      //Hatch Holder
      /**
       * @deprecated replaced by general stop speed modifier |
       * full speed assigned to hatch holder
       */
      public static final double HATCH_HOLDER_FULL_SPEED = 1.0;
      /**
       * @deprecated replaced by general stop speed modifier |
       * full speed assigned to hatch holder
       */
      public static final double HATCH_HOLDER_STOP_SPEED = 0.0;
      /**
       * The value by which any speed being sent to the hatch holder will be multiplied by
       */
      public static final double HATCH_HOLDER_SPEED_MODIFIER = 1.0;

    //Misc Constants//
    /**
     * @deprecated Replaced by subsystem-specific values
     * maximum velocity to be applied to any motor
     */
    public static final double MAX_VELOCITY = 17.5;
    /**
     * @deprecated Replaced by subsystem-specific values
     * The inverse of the maximum velocity to be applied to any motor
     */
    public static final double kV = 1.0 / MAX_VELOCITY;
    /**
     * General Full Speed for motors
     */
    public static final double MOTOR_FULL_SPEED = 1.0;
    /**
     * General Full Stop value for motors
     */
    public static final double MOTOR_FULL_STOP = 0.0;

  //Flags
  /**
   * triggers true when the ball is in the intake
   */
  public static boolean ballInIntake = true;

  public static boolean hatchHolderHasHatch = false;

  //Non-constant speed modifiers//
  /**
   * The value which the speed sent to the elevator is multiplied by
   */
  public static double elevatorSpeedModifier = 1;
  /**
   * The value the the speed is divided by in manipulator
   */
  public static double manipulatorSpeedModifier = 0.40;

  //Objects//
    //Drivetrain//
    /**
     * Motor for the left front wheel
     */
    public static WPI_TalonSRX leftFrontTalon;
    /**
     * Motor for the right front wheel
     */
    public static WPI_TalonSRX rightFrontTalon;
    /**
     * Motor for the left back wheel
     */
    public static WPI_TalonSRX leftBackTalon;
    /**
     * Motor for the right back wheel
     */
    public static WPI_TalonSRX rightBackTalon;
    
    //Manipulator//
      //Cargo Intake
      /**
       * Motor for the intake
       */
      public static WPI_TalonSRX intakeTalon;

      //Hatch holder
      /**
       * Motor for the hatch
       */
      public static WPI_TalonSRX hatchHolderTalon;

    //Elevator//
    /**
     * Talon for elevator lift
     */
    public static WPI_TalonSRX elevatorTalon;

    //Servos//

    //Micro Switches//
      //Manipulator//
        //Ball Intake
        /**
         * Stops the ball intake
         */
        public static DigitalInput ballIntakeStopSwitch;

        //Hatch Holder
        /**
         * Limit for the hatch when grabbing a hatch
         */
        public static DigitalInput grabbedHatchLimitSwitch;
        /**
         * Limit for the hatch when releasing a hatch
         */
        public static DigitalInput releasedHatchLimitSwitch;

      //Elevator//
      /**
       * Limit of the lower position of the elevator
       */
      public static DigitalInput lowerElevatorLimitSwitch;
      /**
       * Limit of the lower position of the elevator
       */
      public static DigitalInput upperElevatorLimitSwitch;

    //Joysticks//
    /**
     * The driver's left joystick used for joystick drive
     */
    public static Joystick leftDriverJoystick;
    /**
     * The driver's right joystick used for joystick drive
     */
    public static Joystick rightDriverJoystick;

    //Cameras//
    /**
     * The camera mounted on the manipulator
     */
    public static UsbCamera manipulatorCamera;
    public static UsbCamera driveCamera;

    //Controllers//
    /**
     * The xbox controller used for the primary driver
     */
    public static XboxController driverController;
    /**
     * The xbox controller used for the assistant driver
     */
    public static XboxController assistantDriverController;

    //Subsystems//
    /**
     * declaration of driveTrainSubsystem
     */
    public static DriveTrainSubsystem driveTrainSubsystem;
    /**
     * declaration of elevatorSubsystem
     */
    public static ElevatorSubsystem elevatorSubsystem;
    /**
     * declaration of manipulatorSubsystem
     */
    public static ManipulatorSubsystem manipulatorSubsystem;

  public static void init()
  {
    
    //Instantiating subsystems
    driveTrainSubsystem = new DriveTrainSubsystem();
    elevatorSubsystem = new ElevatorSubsystem();
    manipulatorSubsystem = new ManipulatorSubsystem();

    //Instantiate controllers
    driverController = new XboxController(DRIVER_CONTROLLER_PORT);
		assistantDriverController = new XboxController(ASSISTANT_DRIVER_CONTROLLER_PORT);

    //Instantiating motors
    leftFrontTalon = new WPI_TalonSRX(LEFT_FRONT_TALON_ID);
    rightFrontTalon = new WPI_TalonSRX(RIGHT_FRONT_TALON_ID);
    leftBackTalon = new WPI_TalonSRX(LEFT_BACK_TALON_ID);
    rightBackTalon = new WPI_TalonSRX(RIGHT_BACK_TALON_ID);

    intakeTalon = new WPI_TalonSRX(INATKE_TALON_ID);

    hatchHolderTalon = new WPI_TalonSRX(HATCH_HOLDER_TALON_ID);

    elevatorTalon = new WPI_TalonSRX(ELEVATOR_TALON_ID);

    //Instantiate intake stop switch
    ballIntakeStopSwitch = new DigitalInput(BALL_INTAKE_STOP_PORT);

    //Instantiating hatch position limit switches
    grabbedHatchLimitSwitch = new DigitalInput(HATCH_HOLDER_GRABBING_LIMIT_SWITCH_PORT);
    releasedHatchLimitSwitch = new DigitalInput(HATCH_HOLDER_RELEASING_LIMIT_SWITCH_PORT);
    lowerElevatorLimitSwitch = new DigitalInput(LOWER_ELEVATOR_LIMIT_SWTICH_PORT);
    upperElevatorLimitSwitch = new DigitalInput(UPPER_ELEVATOR_LIMIT_SWTICH_PORT);

    //Instantiating joysticks for joystick drive
    leftDriverJoystick = new Joystick(LEFT_DRIVER_JOYSTICK_PORT);
    rightDriverJoystick = new Joystick(RIGHT_DRIVER_JOYSTICK_PORT);

    //Instantiates Cameras
    manipulatorCamera =  CameraServer.getInstance().startAutomaticCapture();
    driveCamera = CameraServer.getInstance().startAutomaticCapture();

    //Sets motors to inverted
    leftFrontTalon.setInverted(true);
    leftBackTalon.setInverted(true);
    //centralTalon.setInverted(true);
    intakeTalon.setInverted(true);
    //elevatorSparkMax.setInverted(true);
    hatchHolderTalon.setInverted(true);

    leftBackTalon.follow(leftFrontTalon);
    rightBackTalon.follow(rightFrontTalon);
    
    //Set names and subsystems
    //Subsystems
    driveTrainSubsystem.setName(driveTrainSubsystem.getSubsystem(), "DriveTrainSubsystem");
    manipulatorSubsystem.setName(manipulatorSubsystem.getSubsystem(), "ManipulatorSubsystem");
    elevatorSubsystem.setName(elevatorSubsystem.getSubsystem(), "ElevatorSubsystem");

    //Talons
    leftFrontTalon.setName(driveTrainSubsystem.getSubsystem(), "LeftFrontTalon");
    rightFrontTalon.setName(driveTrainSubsystem.getSubsystem(), "RightFrontTalon");
    leftBackTalon.setName(driveTrainSubsystem.getSubsystem(), "LeftBackTalon");
    rightBackTalon.setName(driveTrainSubsystem.getSubsystem(), "RightBackTalon");

    //Manipulator
    intakeTalon.setName(manipulatorSubsystem.getSubsystem(), "IntakeTalon");
    hatchHolderTalon.setName(manipulatorSubsystem.getSubsystem(), "HatchHolderTalon");
  }
}
