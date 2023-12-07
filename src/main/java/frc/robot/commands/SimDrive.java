// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;


public class SimDrive extends CommandBase {
  /** Creates a new SimDrive. */
  private DriveSubsystem  m_DriveSubsystem;
  private double speed;
  private DoubleSupplier m_rightSupplier;

  public SimDrive(DriveSubsystem m_DriveSubsystem, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_DriveSubsystem = m_DriveSubsystem;
    this.speed = speed;

    addRequirements(m_DriveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_DriveSubsystem.curveDrive(speed, speed, isFinished());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
