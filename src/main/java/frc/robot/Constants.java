// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.math.geometry.Rotation2d;
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
    public static final double wheeltrack = 50; //cm
    public static final double wheelbase = 50; //cm
    public static final double wheelradius = 5;//cm
    public static final double[][] MatrixA ={
      {1, 0, wheelbase/2},//v FLx
      {0, 1, wheeltrack/2},//v FLy
      {1, 0, -wheelbase/2},//v FRx
      {0, 1, wheeltrack/2},//v FRy
      {1, 0, wheelbase/2},//v RLx
      {0, 1, -wheeltrack/2},//v RLy
      {1, 0, -wheelbase/2},//v RRx
      {0, 1, -wheeltrack/2}//v RRy
    };



  public static final class ConsSwerve{
    //mod0 Front Left
    public static final class Mod0{
      public static final int motorR_0 = 1;
      public static final int motorT_0 = 2;
      public static final int cancoder_0 = 0;
      public static final Rotation2d angleOffset = Rotation2d.fromDegrees(0);
      public static final SwerveModuleConstants constants =
        new SwerveModuleConstants(motorT_0, motorR_0, cancoder_0, angleOffset);
    }
    //mod1 Front Right
    public static final class Mod1{
      public static final int motorR_1 = 3;
      public static final int motorT_1 = 4;
      public static final int cancoder_1 = 1;
      public static final Rotation2d angleOffset = Rotation2d.fromDegrees(0);
      public static final SwerveModuleConstants constants =
        new SwerveModuleConstants(motorT_1, motorR_1, cancoder_1, angleOffset);
    }
    //mod2 Rear Left
    public static final class Mod2{
      public static final int motorR_2 = 5;
      public static final int motorT_2 = 6;
      public static final int cancoder_2 = 2;
      public static final Rotation2d angleOffset = Rotation2d.fromDegrees(0);
      public static final SwerveModuleConstants constants =
        new SwerveModuleConstants(motorT_2, motorR_2, cancoder_2, angleOffset);
    }
    //mod3 Rear Right
    public static final class Mod3{
      public static final int motorR_3 = 7;
      public static final int motorT_3 = 8;
      public static final int cancoder_3 = 3;
      public static final Rotation2d angleOffset = Rotation2d.fromDegrees(0);
      public static final SwerveModuleConstants constants =
        new SwerveModuleConstants(motorT_3, motorR_3, cancoder_3, angleOffset);
    }    
  }
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
}
