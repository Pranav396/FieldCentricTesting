// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class TurnCommand extends CommandBase {
  /** Creates a new TurnCommand. */
  private DriveSubsystem m_DriveSubsystem = new DriveSubsystem();
  private double motorSpeed;

  public TurnCommand(DriveSubsystem m_DriveSubsystem, double motorSpeed) {
    this.m_DriveSubsystem = m_DriveSubsystem;
    this.motorSpeed = motorSpeed;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_DriveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_DriveSubsystem.turn(motorSpeed);
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
