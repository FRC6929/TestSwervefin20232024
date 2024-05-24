// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.config.SwerveModuleConstants;

public class SwerveModule extends SubsystemBase {
  private final CANSparkMax motorRotation;
  private final CANSparkMax motorTranslation;
  private CANcoder canCoder;
  private double currentAngle;
  public int moduleNumber;

  //constante de PID
  double kP = 0.05;
  double kI = 0.000;
  double kD = 0;

  //Variable PID
  private double previousError = 0;
  private double integral = 0;

  /** Creates a new SwerveModule. */
  public SwerveModule(int moduleNumber, SwerveModuleConstants moduleConstants) {
    this.moduleNumber = moduleNumber;
    
    canCoder = new CANcoder(moduleConstants.cancoderID);
    motorRotation = new CANSparkMax(moduleConstants.angleMotorID, MotorType.kBrushless);
    motorTranslation = new CANSparkMax(moduleConstants.driveMotorID, MotorType.kBrushless);
    motorRotation.setInverted(false);
    motorTranslation.setInverted(false);
  }
  public void setModuleSpeed(double speed, double targetAngle){
    /**
     * setModuleSpeed controls the module of the swerve
     * -Speed is the speed of the traction motor
     * -targetAngle is the angle the of the angle motor needs to be.
     * the targetAngle need to be in radians & it is relative to the 0 of the CANCoder
     */
    SmartDashboard.putNumber("angle current" + moduleNumber, currentAngle);
    double tspeed = 1;
    //Calcul de la différence angulaire
    double angleDifference = targetAngle - currentAngle;
    SmartDashboard.putNumber("angleDifference", angleDifference);
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

    SmartDashboard.putNumber("PIDoutput" + moduleNumber, PIDoutput);
    SmartDashboard.putNumber("speed"+ moduleNumber, speed*tspeed);

    motorRotation.set(PIDoutput);
    motorTranslation.set(speed*tspeed);
  }
  public double getMotorAngle(){
    /**
     * The return angle is in Radians
     */
    double x = Math.toRadians(canCoder.getPosition().getValue());
    return x;
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    currentAngle = getMotorAngle();
  }
}
