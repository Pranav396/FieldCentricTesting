// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.commands.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  //Subsystems
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();

  //Commands
  // private final FieldCentric m_FieldCentric = new FieldCentric(m_driveSubsystem, .1);

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final PS4Controller controller= new PS4Controller(0);

  // // Drive triggers
  private final JoystickButton m_R1 = new JoystickButton(controller, Button.kR1.value);
  private final JoystickButton m_L1 = new JoystickButton(controller, Button.kL1.value);

  /** The container for the robot. Contains subsystems, OI devices, and commfands. */
  public RobotContainer() {
    // Configure the trigger bindings
    m_driveSubsystem.setDefaultCommand(new FieldCentric(m_driveSubsystem, .4, controller::getLeftY, controller::getLeftX));
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    
    m_L1.whileTrue(new TurnCommand(m_driveSubsystem, 0.5));
    m_R1.whileTrue(new TurnCommand(m_driveSubsystem, -0.5));

  }

  public Command getAutonomousCommand() {
    return null;
  }
}
