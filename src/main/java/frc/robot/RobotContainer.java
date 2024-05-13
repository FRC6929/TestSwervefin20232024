// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.SwerveModule;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Joystick m_joystick = new Joystick(0);
// Module 1
  CANSparkMax motorRotation1 = new CANSparkMax(Constants.ConsSwerve.motorR_1, MotorType.kBrushless);
  CANSparkMax motorTranslation1 = new CANSparkMax(Constants.ConsSwerve.motorT_1, MotorType.kBrushless);
  private final SwerveModule module1 = new SwerveModule(motorRotation1, motorTranslation1);
// Module 2
  CANSparkMax motorRotation2 = new CANSparkMax(Constants.ConsSwerve.motorR_2, MotorType.kBrushless);
  CANSparkMax motorTranslation2 = new CANSparkMax(Constants.ConsSwerve.motorT_2, MotorType.kBrushless);
  private final SwerveModule module2 = new SwerveModule(motorRotation2, motorTranslation2);
// Module 3
  CANSparkMax motorRotation3 = new CANSparkMax(Constants.ConsSwerve.motorR_3, MotorType.kBrushless);
  CANSparkMax motorTranslation3 = new CANSparkMax(Constants.ConsSwerve.motorT_3, MotorType.kBrushless);
  private final SwerveModule module3 = new SwerveModule(motorRotation3, motorTranslation3);  
// Module 4
  CANSparkMax motorRotation4 = new CANSparkMax(Constants.ConsSwerve.motorR_4, MotorType.kBrushless);
  CANSparkMax motorTranslation4 = new CANSparkMax(Constants.ConsSwerve.motorT_4, MotorType.kBrushless);
  private final SwerveModule module4 = new SwerveModule(motorRotation4, motorTranslation4);





  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
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
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
