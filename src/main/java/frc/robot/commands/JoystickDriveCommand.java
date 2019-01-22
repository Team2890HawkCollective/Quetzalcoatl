package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class JoystickDriveCommand extends Command 
{
    public JoystickDriveCommand() 
    {
        requires(RobotMap.driveTrainSubsystem);
    }

    protected boolean isFinished() 
    {
            return false;
    }

    protected void initialize() 
    {

    }

    protected void execute() 
    {
       
    }
}