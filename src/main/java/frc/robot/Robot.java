/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.IOException;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.commands.JoystickDriveCommand;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.followers.EncoderFollower;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot 
{
  /*public static OI m_oi;

  Command m_autonomousCommand;*/
  
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    //m_oi = new OI();
    // chooser.addOption("My Auto", new MyAutoCommand());
    //SmartDashboard.putData("Auto mode", m_chooser);

    RobotMap.init();

    shuffleboardInit();
  }

  /**
   * Add all choosers, values, subsystems, and tabs to shuffleboard here.
   */
  private void shuffleboardInit()
  {
    //Pathweaver
    RobotMap.startingPositionChooser.addOption("Left", "Left-side ");
    RobotMap.startingPositionChooser.addOption("Center", "Center ");
    RobotMap.startingPositionChooser.addOption("Right", "Right-side ");

    RobotMap.gamePieceChooser.addOption("Cargo", "Cargo ");
    RobotMap.gamePieceChooser.addOption("Hatch", "hatch ");

    RobotMap.gamePiecePosition.addOption("Close", "Close-");
    RobotMap.gamePiecePosition.addOption("Middle", "Middle-");
    RobotMap.gamePiecePosition.addOption("Far", "Far-");
    RobotMap.gamePiecePosition.addOption("Center", "Center-");
    RobotMap.gamePiecePositionPart2.addOption("Left", "left ");
    RobotMap.gamePiecePositionPart2.addOption("Right", "right ");
    
    RobotMap.targetChooser.addOption("Left Rocket", "Left-rocket");
    RobotMap.targetChooser.addOption("Right Rocket", "Right-rocket");
    RobotMap.targetChooser.addOption("Cargo Ship", "Cargo-ship");

    Shuffleboard.getTab("Robot Configuration").add("Robot Starting Position", RobotMap.startingPositionChooser);
    Shuffleboard.getTab("Robot Configuration").add("Game Piece", RobotMap.gamePieceChooser);
    Shuffleboard.getTab("Robot Configuration").add("Target", RobotMap.targetChooser);
    Shuffleboard.getTab("Robot Configuration").add("Game Piece Position", RobotMap.gamePiecePosition);
    Shuffleboard.getTab("Robot Configuration").add("Cargo Ship Side", RobotMap.gamePiecePositionPart2);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  
  @Override
  public void autonomousInit()
  {
    //m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    /*if (m_autonomousCommand != null) 
    {
      m_autonomousCommand.start();
    }*/

    //RobotMap.arduino = new SerialPort(115200, SerialPort.Port.kUSB);

    //RobotMap.arduino.enableTermination();

    //RobotMap.elevatorEncoder.setPosition(RobotMap.ELEVATOR_LOWER_ENCODER_LIMIT);

    //new TargetingCommandGroup(1, true).start();
    //new TargetingCommandGroup(2, true).start();

    //Sets the flags for the hatch holder and ball intake.
    if (RobotMap.gamePieceChooser.getSelected().equals("hatch "))
    {
      RobotMap.ballInIntake = false;
      RobotMap.hatchHolderHasHatch = true;
    }

    determinePath();

    try
    {
      //Error in current vers of pathweaver where paths need to be flipped. Will be fixed next season.
      RobotMap.leftDrivetrainTrajectory = PathfinderFRC.getTrajectory(RobotMap.autonomousPath + ".right");
      RobotMap.rightDrivetrainTrajectory = PathfinderFRC.getTrajectory(RobotMap.autonomousPath + ".left");
    }
    catch (IOException e)
    {
      System.out.println(e.getMessage());
    }

    configureMotorsForPathWeaver();
  }

  private void determinePath()
  {
    RobotMap.autonomousPath = RobotMap.startingPositionChooser.getSelected();

    //Because the middle portion on the rocket is Cargo only, it has a different path name.
    if (RobotMap.gamePieceChooser.getSelected().equals("Cargo ") 
        && (RobotMap.targetChooser.getSelected().equals("Left-Rocket") || RobotMap.targetChooser.getSelected().equals("Right-Rocket")))
    {
      RobotMap.autonomousPath += RobotMap.gamePieceChooser.getSelected() + RobotMap.targetChooser.getSelected();
    }
    else if (RobotMap.targetChooser.getSelected().equals("Cargo-ship"))
    {
      RobotMap.autonomousPath += RobotMap.gamePiecePosition.getSelected() + RobotMap.gamePiecePositionPart2.getSelected() + RobotMap.targetChooser.getSelected();
    }
    else 
    {
      RobotMap.autonomousPath += RobotMap.gamePiecePosition.getSelected() + RobotMap.gamePieceChooser.getSelected() + RobotMap.targetChooser.getSelected();
    }
  }

  private void configureMotorsForPathWeaver()
  {
    RobotMap.leftSideDrivetrainPathFollower = new EncoderFollower(RobotMap.leftDrivetrainTrajectory);
    RobotMap.rightSideDrivetrainPathFollower = new EncoderFollower(RobotMap.rightDrivetrainTrajectory);

    RobotMap.leftSideDrivetrainPathFollower.configureEncoder(RobotMap.leftFrontTalon.getSelectedSensorPosition(), RobotMap.DRIVETRAIN_ENCODER_TICKS_PER_REVOLUTION, RobotMap.DRIVETRAIN_WHEEL_DIAMETER);
    RobotMap.leftSideDrivetrainPathFollower.configureEncoder(RobotMap.rightFrontTalon.getSelectedSensorPosition(), RobotMap.DRIVETRAIN_ENCODER_TICKS_PER_REVOLUTION, RobotMap.DRIVETRAIN_WHEEL_DIAMETER);

    RobotMap.leftSideDrivetrainPathFollower.configurePIDVA(RobotMap.DRIVETRAIN_P, RobotMap.DRIVETRAIN_I, RobotMap.DRIVETRAIN_D, RobotMap.DRIVETRAIN_V, RobotMap.DRIVETRAIN_A);
    RobotMap.rightSideDrivetrainPathFollower.configurePIDVA(RobotMap.DRIVETRAIN_P, RobotMap.DRIVETRAIN_I, RobotMap.DRIVETRAIN_D, RobotMap.DRIVETRAIN_V, RobotMap.DRIVETRAIN_A);

    RobotMap.drivetrainNotifier = new Notifier(this::followPath);
    RobotMap.drivetrainNotifier.startPeriodic(RobotMap.leftDrivetrainTrajectory.get(0).dt);
  }

  private void followPath()
  {
    if (RobotMap.leftSideDrivetrainPathFollower.isFinished() || RobotMap.rightSideDrivetrainPathFollower.isFinished())
    {
      RobotMap.drivetrainNotifier.stop();
    }
    else
    {
      double leftSpeed = RobotMap.leftSideDrivetrainPathFollower.calculate(RobotMap.leftFrontTalon.getSelectedSensorPosition());
      double rightSpeed = RobotMap.rightSideDrivetrainPathFollower.calculate(RobotMap.rightFrontTalon.getSelectedSensorPosition());
      double heading = RobotMap.navX.getAngle();
      double desiredHeading = Pathfinder.r2d(RobotMap.leftSideDrivetrainPathFollower.getHeading());
      double headingDifference = Pathfinder.boundHalfDegrees(desiredHeading - heading);
      double turn = 0.8 * (-1.0/80.0) * headingDifference;

      RobotMap.leftFrontTalon.set(leftSpeed + turn);
      RobotMap.rightFrontTalon.set(rightSpeed - turn);
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() 
  {
    Scheduler.getInstance().run();
    //System.out.println(RobotMap.autonomousPath);
  }

  @Override
  public void teleopInit() 
  {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    //if (m_autonomousCommand != null) {
     // m_autonomousCommand.cancel();
    //}
    Scheduler.getInstance().removeAll();

    RobotMap.elevatorEncoder.setPosition(RobotMap.ELEVATOR_ENCODER_DEFAULT_POSITION);
    new JoystickDriveCommand().start();

    

    //RobotMap.drivetrainNotifier.stop();
    //RobotMap.leftFrontTalon.set(RobotMap.MOTOR_FULL_STOP);
    //RobotMap.rightFrontTalon.set(RobotMap.MOTOR_FULL_STOP);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() 
  {  
    //System.out.println(RobotMap.elevatorEncoder.getPosition());
    Scheduler.getInstance().run();

    //System.out.println("Grabbed: " + RobotMap.grabbedHatchLimitSwitch.get());
    System.out.println("Released: " + RobotMap.releasedHatchLimitSwitch.get());  // works after re-wire limit switch.
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() 
  {
  }
}
