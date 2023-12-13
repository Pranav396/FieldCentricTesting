// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;;

public class FieldCentric extends CommandBase {
  /** Creates a new TurnToAngle. */

  private DriveSubsystem m_driveSubsystem;
  private double motorSpeed;
  private DoubleSupplier leftYValue;
  private DoubleSupplier leftXValue;

  public FieldCentric(DriveSubsystem m_driveSubsystem, double motorSpeed, DoubleSupplier leftYValue,
      DoubleSupplier leftXValue) {
    this.m_driveSubsystem = m_driveSubsystem;
    this.motorSpeed = motorSpeed;
    this.leftXValue = leftXValue;
    this.leftYValue = leftYValue;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    SmartDashboard.putNumber("xval: ", leftXValue.getAsDouble());
    SmartDashboard.putNumber("yval: ", leftYValue.getAsDouble() * -1);
    // todo: get the initial angle
    // todo: calculate the controller angle w/ atan2
    // todo: remap the angle to fit gyro mapping
    // todo: find the quadrant
    // todo: determine the direction
    // todo: turn !!!
    double currentAngle = m_driveSubsystem.getCurrentAngle();
    // double convertedAngle = 0;

    double controllerAngle = 90;

    if (leftYValue.getAsDouble() >= .1 || leftYValue.getAsDouble() <= -.1 && leftXValue.getAsDouble() >= .1 || leftXValue.getAsDouble() <= -.1) {
      controllerAngle = Math.atan2(leftYValue.getAsDouble() * -1, leftXValue.getAsDouble());
      controllerAngle = (controllerAngle * 180) / Math.PI;
    
      // atan2 to gyro angle conversion
      if(controllerAngle <= -90 && controllerAngle >= -180){
      controllerAngle = (-controllerAngle - 270);
      }
      else{
      controllerAngle = (-controllerAngle + 90);
      }

      SmartDashboard.putNumber("controller angle 2: ", controllerAngle);

      int currentAngleQuadrant = m_driveSubsystem.getQuadrant(currentAngle);
      int controllerAngleQuadrant = m_driveSubsystem.getQuadrant(controllerAngle);

      SmartDashboard.putNumber("Current Angle Quadrant", currentAngleQuadrant);
      SmartDashboard.putNumber("Controller Angle Quadrant", controllerAngleQuadrant);
      //Checks to see if the quadrant is 1 or 4, moves accordingly & depending on
      // which one is which
      if(currentAngleQuadrant == 1 && controllerAngleQuadrant == 4){
      m_driveSubsystem.turn(motorSpeed);
      }
      else if(currentAngleQuadrant == 4 && controllerAngleQuadrant == 1){
      m_driveSubsystem.turn(-motorSpeed);
      }
      else{
      if (currentAngleQuadrant < controllerAngleQuadrant)
      m_driveSubsystem.turn(-motorSpeed);
      else
      m_driveSubsystem.turn(motorSpeed);
      }



      if(controllerAngle + 15 == currentAngle || controllerAngle == currentAngle - 15){
        m_driveSubsystem.setOutput(0, 0);
      }



      // if (Math.abs(currentAngleQuadrant - controllerAngle) == 3){
      //   return !direction;
      // }
      // else{
      //   return direction;
      // }

    } else {
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveSubsystem.setOutput(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
