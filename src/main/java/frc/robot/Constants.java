// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import frc.lib.config.SwerveModuleConstants;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class ConsSwerve{
    //mod1
    public static final class Mod1{
      public static final int motorR_1 = 1;
      public static final int motorT_1 = 2;
      public static final int cancoder_1 = 1;
      public static final Rotation2d angleOffset = Rotation2d.fromDegrees(0);
      public static final SwerveModuleConstants constants =
        new SwerveModuleConstants(motorR_1, motorT_1, cancoder_1, angleOffset);
    }
    //mod2
    public static final class Mod2{
      public static final int motorR_2 = 3;
      public static final int motorT_2 = 4;
      public static final int cancoder_2 = 2;
      public static final Rotation2d angleOffset = Rotation2d.fromDegrees(0);
      public static final SwerveModuleConstants constants =
        new SwerveModuleConstants(motorR_2, motorT_2, cancoder_2, angleOffset);
    }
    //mod3
    public static final class Mod3{
      public static final int motorR_3 = 5;
      public static final int motorT_3 = 6;
      public static final int cancoder_3 = 3;
      public static final Rotation2d angleOffset = Rotation2d.fromDegrees(0);
      public static final SwerveModuleConstants constants =
        new SwerveModuleConstants(motorR_3, motorT_3, cancoder_3, angleOffset);
    }
    //mod4
    public static final class Mod4{
      public static final int motorR_4 = 7;
      public static final int motorT_4 = 8;
      public static final int cancoder_4 = 4;
      public static final Rotation2d angleOffset = Rotation2d.fromDegrees(0);
      public static final SwerveModuleConstants constants =
        new SwerveModuleConstants(motorR_4, motorT_4, cancoder_4, angleOffset);
    }    
  }
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
}
