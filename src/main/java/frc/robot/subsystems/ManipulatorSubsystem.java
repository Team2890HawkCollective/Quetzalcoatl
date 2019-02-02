/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

<<<<<<< HEAD
import edu.wpi.first.wpilibj.GenericHID.Hand;
=======
>>>>>>> GrabberTesting
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
<<<<<<< HEAD
public class ManipulatorSubsystem extends Subsystem {
=======
public class ManipulatorSubsystem extends Subsystem 
{
>>>>>>> GrabberTesting
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
<<<<<<< HEAD
  public void initDefaultCommand() {
=======
  public void initDefaultCommand() 
  {
>>>>>>> GrabberTesting
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

<<<<<<< HEAD
  public void xboxLift()
  {
    if (RobotMap.assistantDriverController.getTriggerAxis(Hand.kLeft) > 0.05)
      liftUp();
    else if (RobotMap.assistantDriverController.getTriggerAxis(Hand.kRight) > 0.05)
      liftDown();
    else
      RobotMap.liftTalon.set(0);
  }

  public void liftUp()
  {
    RobotMap.liftTalon.set(RobotMap.assistantDriverController.getTriggerAxis(Hand.kLeft) - 0.5);
  }

  public void liftDown()
  {
    RobotMap.liftTalon.set(-RobotMap.assistantDriverController.getTriggerAxis(Hand.kRight) + 0.5);
=======
  /**
   * @param speed Negative for close, positive for open
   */
  public void actuateGrabber(double speed)
  {
    RobotMap.grabberTalon.set(speed);
  }

  public void xboxControl()
  {
    if (RobotMap.assistantDriverController.getAButton())
      actuateGrabber(RobotMap.MANIPULATOR_GRABBER_CLOSE);

    if (RobotMap.assistantDriverController.getBButton())
      actuateGrabber(RobotMap.MANIPULATOR_GRABBER_OPEN);

    if (!RobotMap.assistantDriverController.getAButton() && !RobotMap.assistantDriverController.getBButton())
      actuateGrabber(RobotMap.DRIVETRAIN_FULL_STOP);
>>>>>>> GrabberTesting
  }
}
