// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
    /** Creates a new DriveSubsystems. */
  private AHRS m_gyro = new AHRS(SPI.Port.kMXP);
  CANSparkMax m_left = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushed);
  CANSparkMax m_right = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushed);

  public DriveSubsystem() {
    m_left.setInverted(false);
    m_right.setInverted(true);
  }

  public void drive(double speed) {
    m_left.set(speed);
    m_right.set(speed);
  }
  
  public void turn(double speed) {
    m_left.set(speed);
    m_right.set(-speed);
  }

  public double getCurrentAngle() {
    return m_gyro.getYaw();
  }

  public void curveDrive(double xSpeed, double rotation, boolean turn){
    var speeds = DifferentialDrive.curvatureDriveIK(xSpeed, -rotation, turn);

    setOutput(speeds.left, speeds.right);
  }

  public void setOutput(double leftSpeed, double rightSpeed) {
    m_left.set(leftSpeed);
    m_right.set(rightSpeed);
  }

  public int getQuadrant(double angle) {

    int quadrant = 0;

    if(angle > 0 && angle < 90){
      quadrant = 1;
    }
    else if(angle < 0 && angle > -90){
      quadrant = 2;
    }
    else if(angle < -90 && angle > -180){
      quadrant = 3;
    }
    else if(angle > 90 && angle < 180){
      quadrant = 4;
    }

    return quadrant;
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
