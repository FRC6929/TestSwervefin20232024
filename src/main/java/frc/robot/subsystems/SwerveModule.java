// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveModule extends SubsystemBase {
  private final CANSparkMax motorRotation;
  private final CANSparkMax motorTranslation;
  private CANcoder canCoder;
  private double currentAngle;
  public int moduleNumber;

  //constante de PID
  double kP = 0;
  double kI = 0;
  double kD = 0;

  //Variable PID
  private double previousError = 0;
  private double integral = 0;

  /** Creates a new SwerveModule. */
  public SwerveModule(int moduleNumber, SwerveModuleConstants moduleConstants) {
    this.moduleNumber = moduleNumber;
    
    canCoder = new CANcoder(moduleConstants.cancoderID)
  }
  public void setModuleSpeed(double speed, double targetAngle){
    double tspeed = speed;
    //Calcul de la différence angulaire
    double angleDifference = targetAngle - currentAngle;
    if(angleDifference > Math.PI){
      angleDifference -= 2*Math.PI;
    }else if(angleDifference < -Math.PI){
      angleDifference += 2*Math.PI;
    }
    double angleForMotor = angleDifference;
    //Si le sens de rotation le plus court est inversé, inverse le moteur de traction et le moteur de rotation
    if(Math.abs(angleDifference)> Math.PI/2){
      angleForMotor = 2*Math.PI - angleDifference;
      if(angleForMotor > Math.PI){
        angleForMotor -= 2*Math.PI;
      }else if(angleForMotor < -Math.PI){
        angleForMotor += 2*Math.PI;
      }
      tspeed *= -1;
    }
    //calcul de l'erreur
    double error = angleForMotor;
    //calcul de la partie proportionnelle
    double proportional = kP * error;
    //calcul de la partie intégrale
    integral += error;
    double intergralTerm = kI * integral;
    //calcul de la partie dérivé
    double derivative = kD * (error - previousError);
    previousError = error;
    //somme des PID
    double PIDoutput = proportional + intergralTerm + derivative;

    motorRotation.set(PIDoutput);
    motorTranslation.set(tspeed);

  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
