// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.DriveConstants;
import frc.robot.subsystems.Drive;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class MoveForDistance extends Command {
  /** Creates a new MoveForDistance. */
  private Drive m_drive;
  private double m_distanceInFeet;
  private double m_speed;
  private double m_ticksNeeded = 0;
  private double m_ticksChanged = 0;
  private double m_ticksStarted = 0;
  public MoveForDistance(Drive drive, double distance, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = drive;
    m_distanceInFeet = distance;
    m_speed = speed;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_ticksNeeded = (int)((m_distanceInFeet / DriveConstants.kCircumferenceOfWheelFeet) * DriveConstants.kTicksInRotation);
    m_ticksStarted = m_drive.getRightTicks();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.setLeftSpeed(m_speed);
    m_drive.setRightSpeed(m_speed);
    m_ticksChanged = m_drive.getRightTicks() - m_ticksStarted;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.setLeftSpeed(0);
    m_drive.setRightSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_ticksNeeded <= m_ticksChanged;
  }
}
