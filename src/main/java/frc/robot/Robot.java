/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.IOException;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.JoystickDriveCommand;
import jaci.pathfinder.PathfinderFRC;

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
  SendableChooser<String> startingPositionChooser = new SendableChooser<>();
  SendableChooser<String> gamePieceChooser = new SendableChooser<>();
  SendableChooser<String> targetChooser = new SendableChooser<>();
  SendableChooser<String> gamePiecePosition = new SendableChooser<>();
  SendableChooser<String> gamePiecePositionPart2 = new SendableChooser<>();

  private String autonomousPath;
  
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    //m_oi = new OI();
    // chooser.addOption("My Auto", new MyAutoCommand());
    //SmartDashboard.putData("Auto mode", m_chooser);

    autonomousPath = "";

    startingPositionChooser.addOption("Left", "Left-side ");
    startingPositionChooser.addOption("Center", "Center ");
    startingPositionChooser.addOption("Right", "Right-side ");

    gamePieceChooser.addOption("Cargo", "Cargo ");
    gamePieceChooser.addOption("Hatch", "hatch ");

    gamePiecePosition.addOption("Close", "Close-");
    gamePiecePosition.addOption("Middle", "Middle-");
    gamePiecePosition.addOption("Far", "Far-");
    gamePiecePosition.addOption("Center", "Center-");
    gamePiecePositionPart2.addOption("Left", "left ");
    gamePiecePositionPart2.addOption("Right", "right ");
    
    targetChooser.addOption("Left Rocket", "Left-rocket");
    targetChooser.addOption("Right Rocket", "Right-rocket");
    targetChooser.addOption("Cargo Ship", "Cargo-ship");

    Shuffleboard.getTab("Main").add("Robot Starting Position", startingPositionChooser);
    Shuffleboard.getTab("Main").add("Game Piece", gamePieceChooser);
    Shuffleboard.getTab("Main").add("Target", targetChooser);
    Shuffleboard.getTab("Main").add("Game Piece Position", gamePiecePosition);
    Shuffleboard.getTab("Main").add("Cargo Ship Side", gamePiecePositionPart2);

    RobotMap.init();
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

    autonomousPath += startingPositionChooser.getSelected();

    //Because the middle portion on the rocket is Cargo only, it has a different path name.
    if (gamePieceChooser.getSelected().equals("Cargo ") 
        && (targetChooser.getSelected().equals("Left-Rocket") || targetChooser.getSelected().equals("Right-Rocket")) 
        && (gamePiecePosition.getSelected().equals("Middle-") || gamePiecePosition.getSelected().equals("Center-")))
    {
      autonomousPath += gamePieceChooser.getSelected() + targetChooser.getSelected();
    }
    else if (targetChooser.getSelected().equals("Cargo-ship"))
    {
      autonomousPath += gamePiecePosition.getSelected() + gamePiecePositionPart2.getSelected();
    }
    else 
    {
      autonomousPath += gamePiecePosition.getSelected() + gamePieceChooser.getSelected() + targetChooser.getSelected();
    }

    Shuffleboard.getTab("Main").add("autonomous Path", autonomousPath);

    /*try
    {
      RobotMap.leftDrivetrainTrajectory = PathfinderFRC.getTrajectory(RobotMap.autonomousPath + ".right");
      RobotMap.rightDrivetrainTrajectory = PathfinderFRC.getTrajectory(RobotMap.autonomousPath + ".left");
    }
    catch (IOException e)
    {
      System.out.println("Path not found. Check Spelling.");
    }*/
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() 
  {
    Scheduler.getInstance().run();
    System.out.println(autonomousPath);
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
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() 
  {  
    System.out.println(RobotMap.elevatorEncoder.getPosition());
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() 
  {
  }
}
