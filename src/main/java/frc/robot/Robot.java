// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

   private TalonFX mUpLeft = new TalonFX(0, "rio");
   private TalonFX mDownLeft = new TalonFX(1, "rio");

   private TalonFX mUpRight = new TalonFX(2, "rio");
   private TalonFX mDownRight = new TalonFX(3, "rio");

   private TunableNumber topLeftTuner;
   private TunableNumber bottomLeftTuner;
   private TunableNumber topRightTuner;
   private TunableNumber bottomRightTuner;

   private GenericEntry apply;

  @Override
  public void robotInit() {
    mUpLeft.config_kP(0, 0.2);
    mUpLeft.config_kI(0, 0.0);
    mUpLeft.config_kD(0, 0.01);
    mUpLeft.config_kF(0, 0.0);
    mUpLeft.setNeutralMode(NeutralMode.Coast);
    mUpLeft.configClosedloopRamp(0.5);

    mDownLeft.config_kP(0, 0.2);
    mDownLeft.config_kI(0, 0.0);
    mDownLeft.config_kD(0, 0.01);
    mDownLeft.config_kF(0, 0.0);
    mDownLeft.setNeutralMode(NeutralMode.Coast);
    mDownLeft.configClosedloopRamp(0.5);
    
    mUpRight.config_kP(0, 0.2);
    mUpRight.config_kI(0, 0.0);
    mUpRight.config_kD(0, 0.01);
    mUpRight.config_kF(0, 0.0);
    mUpRight.setNeutralMode(NeutralMode.Coast);
    mUpRight.configClosedloopRamp(0.5);

    mDownRight.config_kP(0, 0.2);
    mDownRight.config_kI(0, 0.0);
    mDownRight.config_kD(0, 0.01);
    mDownRight.config_kF(0, 0.0);
    mDownRight.setNeutralMode(NeutralMode.Coast);
    mDownRight.configClosedloopRamp(0.5);
    
    
    topLeftTuner = new TunableNumber("Top Left Falcon RPM", 0.0, true);
    bottomLeftTuner = new TunableNumber("Bottom Left Falcon RPM", 0.0, true);
    topRightTuner = new TunableNumber("Top Right Falcon RPM", 0.0, true);
    bottomRightTuner = new TunableNumber("Bottom Right Falcon RPM", 0.0, true);
    apply = Shuffleboard.getTab("Button").add("Button", false).withWidget(BuiltInWidgets.kToggleButton).getEntry();

    mUpLeft.set(ControlMode.PercentOutput, 0.0);
    mDownLeft.set(ControlMode.PercentOutput, 0.0);

    mUpRight.set(ControlMode.PercentOutput, 0.0);
    mDownRight.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    if (true) {
      if(topLeftTuner.get() > 0.0) {
        mUpLeft.set(ControlMode.Velocity, -topLeftTuner.get() / (10.0 / 2048.0));
      }

      if(bottomLeftTuner.get() > 0.0) {
        mDownLeft.set(ControlMode.Velocity, bottomLeftTuner.get() / (10.0 / 2048.0));
      }

      if (topRightTuner.get() > 0.0) {
        mUpRight.set(ControlMode.Velocity, topRightTuner.get() / (10.0 / 2048.0));
      }

      if(bottomRightTuner.get() > 0.0) {
        mDownRight.set(ControlMode.Velocity, -bottomRightTuner.get() / (10.0 / 2048.0));
      }
    } else {
      mUpLeft.set(ControlMode.PercentOutput, 0.0);
      mDownLeft.set(ControlMode.PercentOutput, 0.0);
      mUpRight.set(ControlMode.PercentOutput, 0.0);
      mDownRight.set(ControlMode.PercentOutput, 0.0);
    }

    SmartDashboard.putNumber("Top Left ACTUAL Speed", mUpLeft.getSelectedSensorVelocity() * (10.0 / 2048.0));
    SmartDashboard.putNumber("Bottom Left ACTUAL Speed", mDownLeft.getSelectedSensorVelocity() * (10.0 / 2048.0));

    SmartDashboard.putNumber("Top Right ACTUAL Speed", mUpRight.getSelectedSensorVelocity() * (10.0 / 2048.0));
    SmartDashboard.putNumber("Bottom Right ACTUAL Speed", mDownRight.getSelectedSensorVelocity() * (10.0 / 2048.0));
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
