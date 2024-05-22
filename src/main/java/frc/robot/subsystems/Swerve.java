// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Swerve extends SubsystemBase {
  private SwerveModule[] mSwervemods;

  /** Creates a new Swerve. */
  public Swerve() {
    mSwervemods =
      new SwerveModule[]{
        new SwerveModule(0, Constants.ConsSwerve.Mod0.constants),
        new SwerveModule(1, Constants.ConsSwerve.Mod1.constants),
        new SwerveModule(2, Constants.ConsSwerve.Mod2.constants),
        new SwerveModule(3, Constants.ConsSwerve.Mod3.constants)
      };
  }
  public void drive(double angle, double magnitude){
    for(int i= 0; i<4; i++){
      mSwervemods[i].setModuleSpeed(magnitude, angle);
      SmartDashboard.putNumber("angle", angle);
      SmartDashboard.putNumber("magnitude", magnitude);
    }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
