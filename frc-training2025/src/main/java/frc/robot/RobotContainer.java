// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.MoveForDistance;
// import frc.robot.commands.MoveForTime;
import frc.robot.constants.DriveConstants;
import frc.robot.constants.IOConstants;
import frc.robot.subsystems.Drive;


public class RobotContainer {
  private Drive m_drive = new Drive();
  private Joystick m_joystick = new Joystick (IOConstants.kJoystickPort);
  private ArcadeDrive m_arcadeDrive = new ArcadeDrive(m_drive, m_joystick);
  // private MoveForTime m_moveForTime = new MoveForTime(m_drive, DriveConstants.kTimeInSeconds, DriveConstants.kSpeed);
  private MoveForDistance m_moveForDistance = new MoveForDistance(m_drive, DriveConstants.kDistanceInFeet, DriveConstants.kSpeed);
  // Initializing needed classes for the Drive Train;

  public RobotContainer() {
    m_drive.setDefaultCommand(m_arcadeDrive); // Set the default command to Arcade Drive
    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return m_moveForDistance;
  }
}
