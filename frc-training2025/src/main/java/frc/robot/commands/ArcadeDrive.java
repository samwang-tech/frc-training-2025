// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.MotorConstants;
import frc.robot.subsystems.Drive;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ArcadeDrive extends Command {
  /** Creates a new ArcadeDrive. */

  private Drive m_drive; //Declared drive train
  private Joystick m_joystick; //Declared joystick for input
  private double m_speed;
  private double m_turn;
  private double m_left;
  private double m_right;
  // Variables needed to turn joystick input into drivetrain motion

  public ArcadeDrive(Drive drive, Joystick joystick) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = drive;
    m_joystick = joystick;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_speed = m_joystick.getY() * MotorConstants.kMotorMultiplier;
    m_turn = m_joystick.getX() * MotorConstants.kMotorMultiplier;
    // Get inputs from joystick controller
    if (m_speed>=0){
      m_left = m_speed + m_turn;
      m_right = m_speed - m_turn;
    }
    else 
    if (m_speed<0){
      m_left = m_speed - m_turn;
      m_right = m_speed + m_turn;
    } // So the drive train will move correctly based on input from 
    m_drive.setLeftSpeed(m_left);
    m_drive.setRightSpeed(m_right);
    // Setting the drive train wheel speeds with subsystem methods

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
