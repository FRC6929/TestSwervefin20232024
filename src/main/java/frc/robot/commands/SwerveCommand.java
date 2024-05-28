// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Swerve;

public class SwerveCommand extends Command {
  private final Swerve swerve;
  private final Joystick m_joystick;

  /** Creates a new SwerveCommand. */
  public SwerveCommand(Swerve swerve, Joystick joystick) {
    this.swerve = swerve;
    m_joystick = joystick;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(swerve);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double angle = -Math.atan2(m_joystick.getY(),m_joystick.getX());
    double magnitude = m_joystick.getMagnitude();
    if(magnitude< 0.01){
      magnitude = 0;
    }
    swerve.drive(angle, magnitude);
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
