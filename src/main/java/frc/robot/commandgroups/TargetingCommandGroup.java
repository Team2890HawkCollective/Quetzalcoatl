/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandgroups;

import frc.robot.RobotMap;
import frc.robot.commands.*;
import frc.robot.subsystems.AutomatedSubsytem;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TargetingCommandGroup extends CommandGroup 
{
  /**
   * @param level The level to which the elevator should raise
   * @param cargo Whether or not we are depositing cargo. True if yes, false if doing hatches
   */
  public TargetingCommandGroup(AutomatedSubsytem.RocketLevel level, boolean cargo) 
  {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.

    addParallel(new MoveElevatorCommand(level, cargo));
    addParallel(new TargetingStage1RotationCommand());
    addSequential(new TargetingStage2StrafeCommand());
    addSequential(new TargetingStage3RangefinderCommand());
    /*addSequential(new LowerElevatorCommand());*/

    /*
    if (cargo)
      addSequential(new ReleaseBallCommand(RobotMap.MANIPULATOR_TIME_TO_RELEASE_CARGO));
    else
      addSequential(new ReleaseHatchCommand());*/

    //addSequential(new LowerElevatorCommand());
  }
}
