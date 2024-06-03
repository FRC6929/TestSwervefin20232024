// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Swerve;

public class SwerveCommand extends Command {
  private final Swerve swerve;
  private final Joystick m_joystick;
  double[] angles;
  double[] magnitudes;
  double[] inputs;//0 = x, 1 = y, 2 = z
  double[] outputs; // composants of vectors
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
    inputs[0] = m_joystick.getX(); 
    inputs[1] = -m_joystick.getY();
    inputs[2] = m_joystick.getZ();
    for(int i = 0; i<8; i++){
      double now = 0;
      for(int j = 0; j<3; j++){
        now += inputs[j]*Constants.MatrixA[i][j];
      }
      outputs[i] = now;
    }
    for(int i =0; i<4; i++){
      angles[i]= Math.atan2(outputs[2*i + 1],outputs[2*i]);
      magnitudes[i]= Math.sqrt(Math.pow(outputs[2*i + 1],2) + Math.pow(outputs[2*i],2))/Constants.wheelradius;
    }
    swerve.drive(angles, magnitudes);
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
