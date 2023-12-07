// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
    /** Creates a new DriveSubsystems. */
  private AHRS m_gyro = new AHRS(SPI.Port.kMXP);
  private CANSparkMax m_left = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushed);
  private CANSparkMax m_right = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushed);

  public DriveSubsystem() {}

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

  public int getQuadrant(double angle) {
    if (angle > 0)
        if (angle > 90)
            return 4;
        else
            return 1;
            
    else 
        if (angle < -90)
            return 2;
        else
            return 3;
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
