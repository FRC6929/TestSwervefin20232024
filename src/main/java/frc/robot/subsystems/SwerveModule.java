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
   /*plus la différence angulaire est grande plus il va a sa position rapidement */
   //permet de bouger de facon normale
  double kP = 0.1;
  /*la partie intégrale augmente lorsque l'erreur est répété*/
  //utile dans les cas qu'il manque un petit quelque chose pour atteindre la cible
  double kI = 0.000;
  /*différence entre l'erreur actuelle et l'erreur d'avant*/
  //utile quand il y a des grands changement dans les positions
  double kD = 0;

  //Variable PID
  private double previousError = 0;
  private double integral = 0;

  /** Creates a new SwerveModule. */
  /*ceci est comme une boucle qui crée les modules avec leurs moteurs respectif */
  public SwerveModule(int moduleNumber, SwerveModuleConstants moduleConstants) {
    this.moduleNumber = moduleNumber; 
    canCoder = new CANcoder(moduleConstants.cancoderID);
    motorRotation = new CANSparkMax(moduleConstants.angleMotorID, MotorType.kBrushless);
    motorTranslation = new CANSparkMax(moduleConstants.driveMotorID, MotorType.kBrushless);
    //il est important de set le sens des moteurs
    //parce que s'il était inversé auparavant il vont toujours l'etre si on les set pas
    motorRotation.setInverted(false);
    motorTranslation.setInverted(false);
  }
  public void setModuleSpeed(double speed, double targetAngle){
    /**
     * setModuleSpeed controls the module of the swerve
     * -Speed is the speed of the traction motor
     * -targetAngle is the angle the motor needs to be.
     * the targetAngle needs to be in radians & it is relative to the 0 of the CANCoder
     */
    SmartDashboard.putNumber("angle current" + moduleNumber, currentAngle);
    double tspeed = 1;
    //Calcul de la différence angulaire
    double angleDifference = targetAngle - currentAngle;
    /*change la différence angulaire pour qu'elle soit entre [-PI, PI] */
    if(angleDifference > Math.PI){
      angleDifference -= 2*Math.PI;
    }else if(angleDifference < -Math.PI){
      angleDifference += 2*Math.PI;
    }

    double angleForMotor = angleDifference;
    /*
     * Si le moteur de rotation doit faire plus de Pi/2 
     * -> Moteur de traction *-1
     * -> Moteur de Rotation => on change le sens sans changer la direction (+ PI)
     * En gros c'est comme changer le sens d'un vecteur (Math 5SN)
     */
    if(Math.abs(angleDifference)> Math.PI/2){
      angleForMotor = Math.PI + angleDifference;
      tspeed *= -1;
      /*change la différence angulaire pour qu'elle soit entre [-PI, PI] */
      if(angleForMotor > Math.PI){
        angleForMotor -= 2*Math.PI;
      }else if(angleForMotor < -Math.PI){
        angleForMotor += 2*Math.PI;
      }
    }
    
    SmartDashboard.putNumber("angleForMotor" + moduleNumber, angleForMotor);
    //calcul de l'erreur
    double error = -angleForMotor;
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
    if(speed*tspeed == 0){
      motorRotation.set(0);
    }else{
    motorRotation.set(PIDoutput);
    }
    motorTranslation.set(speed*tspeed);
  }
  public double getMotorAngle(){
    /**
     * The return angle is in Radians
     */
    double x = canCoder.getAbsolutePosition().getValueAsDouble()*2*Math.PI;
    return x;
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    currentAngle = getMotorAngle();
  }
}
