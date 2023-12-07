// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import java.util.function.DoubleSupplier;

public class FieldCentric extends CommandBase {
  /** Creates a new TurnToAngle. */

  private DriveSubsystem m_driveSubsystem;
  private double motorSpeed;
  private DoubleSupplier leftYValue;
  private DoubleSupplier leftXValue;


  public FieldCentric(DriveSubsystem m_driveSubsystem, double motorSpeed, DoubleSupplier leftYValue, DoubleSupplier leftXValue){
    this.m_driveSubsystem = m_driveSubsystem;
    this.motorSpeed = motorSpeed;
    this.leftXValue = leftXValue;
    this.leftYValue = leftYValue;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //todo: get the initial angle
    //todo: calculate the controller angle w/ atan2
    //todo: remap the angle to fit gyro mapping
    //todo: find the quadrant
    //todo: determine the direction
    //todo: turn !!!
    double currentAngle = m_driveSubsystem.getCurrentAngle();
    double convertedAngle = 0;

    double controllerAngle = Math.atan2(leftYValue.getAsDouble(), leftXValue.getAsDouble());

    //atan2 to gyro angle conversion
    if(controllerAngle <= -90 && controllerAngle >= -180){
      controllerAngle = (-controllerAngle - 270);
    }
    else{
      controllerAngle = (-controllerAngle + 90);
    }

    int currentAngleQuadrant = m_driveSubsystem.getQuadrant(currentAngle);
    int controllerAngleQuadrant = m_driveSubsystem.getQuadrant(controllerAngle);

    //Checks to see if the quadrant is 1 or 4, moves accordingly & depending on which one is which
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

        
    // if (Math.abs(currentAngleQuadrant - controllerAngle) == 3)
    //     return !direction;
    // else
    //     return direction;



    
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
