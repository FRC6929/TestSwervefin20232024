// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveModule;

public class SwerveCommand extends Command {
  private final SwerveModule module1;
  private final SwerveModule module2;
  private final SwerveModule module3;
  private final SwerveModule module4;
  /** Creates a new SwerveCommand. */
  public SwerveCommand(SwerveModule mod1, SwerveModule mod2, SwerveModule mod3, SwerveModule mod4, Joystick joystick) {
    module1 = mod1;
    module2 = mod2;
    module3 = mod3;
    module4 = mod4;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(mod1,mod2,mod3,mod4);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

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
