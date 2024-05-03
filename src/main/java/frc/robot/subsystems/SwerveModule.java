// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveModule extends SubsystemBase {
  private final CANSparkMax motorRotation;
  private final CANSparkMax motorTranslation;
  private double currentAngle;

  //constante de PID
  double kP = 0;
  double kI = 0;
  double kD = 0;

  /** Creates a new SwerveModule. */
  public SwerveModule(CANSparkMax motorRotation, CANSparkMax motorTranslation) {
    this.motorRotation = motorRotation;
    this.motorTranslation = motorTranslation;
  }
  public void setModuleSpeed(double speed, double targetAngle){
    //Calcul de la différence angulaire
    double angleDifference = targetAngle - currentAngle;
    if(angleDifference > Math.PI){
      angleDifference -= 2*Math.PI;
    }else if(angleDifference < Math.PI){
      angleDifference += 2*Math.PI;
    }

    //Calcul du sens de rotation le plus court
    double rotationDirection = Math.signum(angleDifference);
    //Si le sens de rotation le plus court est inversé, inverse le moteur de traction

  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
