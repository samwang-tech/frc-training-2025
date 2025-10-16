// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.MotorConstants;

public class Drive extends SubsystemBase {
  /** Creates a new Drive. */
  
  private TalonSRX m_rightPrimaryMotor = new TalonSRX(MotorConstants.kTalonRight);
  private TalonSRX m_leftPrimaryMotor = new TalonSRX(MotorConstants.kTalonLeft);
  private VictorSPX m_rightSubMotor = new VictorSPX(MotorConstants.kVictorRight);
  private VictorSPX m_leftSubMotor = new VictorSPX(MotorConstants.kVictorLeft);
  
  /* Have initiated all motor objects and defined them as right or left and primary or follewer/sub */
  
  public Drive() {
    m_rightPrimaryMotor.setNeutralMode(NeutralMode.Brake); // Have motors always prevent movement without input
    m_leftPrimaryMotor.setNeutralMode(NeutralMode.Brake);
    m_rightSubMotor.follow(m_rightPrimaryMotor); // Supporting motors
    m_leftSubMotor.follow(m_leftPrimaryMotor);
    m_rightPrimaryMotor.setInverted(true); // Right side is flipped compared to left
  }

  public void setRightSpeed(double speed){
    m_rightPrimaryMotor.set(ControlMode.PercentOutput, speed);
  } // set right motor speed in percentage to full power
  public void setLeftSpeed(double speed){
    m_leftPrimaryMotor.set(ControlMode.PercentOutput, speed);
  } // set left motor speed in percentage to full power
  public double getRightSpeed(){
    return m_rightPrimaryMotor.getMotorOutputPercent();
  } // get the output percent from the motor
  public double getLeftSpeed(){
    return m_leftPrimaryMotor.getMotorOutputPercent();
  } // get the output percent from the motor
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Right Motors", getRightSpeed());
    SmartDashboard.putNumber("Left Motor", getLeftSpeed());
    // Update speeds to the dashboard for testing purposes
  }
}
