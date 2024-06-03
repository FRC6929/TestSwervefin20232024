// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Swerve;

public class SwerveCommand extends Command {
  private final Swerve swerve;
  private final Joystick m_joystick;
  double[] angles= {0,0,0,0};
  double[] magnitudes = {0,0,0,0};
  double[] inputs = {0,0,0};//0 = x, 1 = y, 2 = z
  double[] outputs = {0,0,0,0,0,0,0,0}; // composants of vectors
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
    inputs[0] = 25*m_joystick.getRawAxis(0); 
    inputs[1] = -25*m_joystick.getRawAxis(1);
    inputs[2] = -m_joystick.getRawAxis(4);
    for(int i = 0; i<8; i++){
      double now = 0;
      for(int j = 0; j<3; j++){
        now += inputs[j]*Constants.MatrixA[i][j];
      }
      SmartDashboard.putNumber("output" + i, now);
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
