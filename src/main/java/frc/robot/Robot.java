// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.TimedRobot;
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
   private TalonFX mDownLeft = new TalonFX(2, "rio");

   private TalonFX mUpRight = new TalonFX(1, "rio");
   private TalonFX mDownRight = new TalonFX(3, "rio");

   private TunableNumber topLeftTuner;
   private TunableNumber bottomLeftTuner;
   private TunableNumber topRightTuner;
   private TunableNumber bottomRightTuner;

  @Override
  public void robotInit() {
    mUpLeft.config_kP(0, 0.4);
    mUpLeft.config_kI(0, 0.0);
    mUpLeft.config_kD(0, 0.02);
    mUpLeft.config_kF(0, 0.0);
    mUpLeft.setNeutralMode(NeutralMode.Coast);
    mUpLeft.configClosedloopRamp(0.1);
    mUpLeft.setInverted(false);
    
    mUpRight.config_kP(0, 0.4);
    mUpRight.config_kI(0, 0.0);
    mUpRight.config_kD(0, 0.03);
    mUpRight.config_kF(0, 0.0);
    mUpRight.setNeutralMode(NeutralMode.Coast);
    mUpRight.configClosedloopRamp(0.1);
    mUpRight.setInverted(true);

    mDownLeft.config_kP(0, 0.4);
    mDownLeft.config_kI(0, 0.0);
    mDownLeft.config_kD(0, 0.02);
    mDownLeft.config_kF(0, 0.0);
    mDownLeft.setNeutralMode(NeutralMode.Coast);
    mDownLeft.configClosedloopRamp(0.1);
    mDownLeft.setInverted(true);

    mDownRight.config_kP(0, 0.4);
    mDownRight.config_kI(0, 0.0);
    mDownRight.config_kD(0, 0.03);
    mDownRight.config_kF(0, 0.0);
    mDownRight.setNeutralMode(NeutralMode.Coast);
    mDownRight.configClosedloopRamp(0.1);
    mDownRight.setInverted(false);
    
    
    topLeftTuner = new TunableNumber("Top Left Falcon RPS", 0.0, true);
    topRightTuner = new TunableNumber("Top Right Falcon RPS", 0.0, true);
    bottomLeftTuner = new TunableNumber("Bottom Left Falcon RPS", 0.0, true);
    bottomRightTuner = new TunableNumber("Bottom Right Falcon RPS", 0.0, true);

    mUpLeft.set(ControlMode.Velocity, 0.0);
    mUpRight.set(ControlMode.Velocity, 0.0);
    mDownLeft.set(ControlMode.Velocity, 0.0);
    mDownRight.set(ControlMode.Velocity, 0.0);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {

    if(topLeftTuner.get() > 0.0) {
      mUpLeft.set(ControlMode.Velocity, topLeftTuner.get() / (10.0 / 2048.0));
    } else {
      mUpLeft.set(ControlMode.Velocity, 0.0);
    }

    if (topRightTuner.get() > 0.0) {
      mUpRight.set(ControlMode.Velocity, topRightTuner.get() / (10.0 / 2048.0));
    } else { 
      mUpRight.set(ControlMode.Velocity, 0.0);
    }

    if(bottomLeftTuner.get() > 0.0) {
      mDownLeft.set(ControlMode.Velocity, bottomLeftTuner.get() / (10.0 / 2048.0));
    } else {
      mDownLeft.set(ControlMode.Velocity, 0.0);
    }

    if(bottomRightTuner.get() > 0.0) {
      mDownRight.set(ControlMode.Velocity, bottomRightTuner.get() / (10.0 / 2048.0));
    } else {
      mDownRight.set(ControlMode.Velocity, 0.0);
    }

    // getSelectedSensorVelocity is in ticks/100ms, need to multiply by rot/2048 ticks to get rot/100ms, then 1000ms/1s to get rot/s
    SmartDashboard.putNumber("Top Left ACTUAL Speed RPS", Math.abs(mUpLeft.getSelectedSensorVelocity() * (10.0 / 2048.0)));
    SmartDashboard.putNumber("Bottom Left ACTUAL Speed RPS", Math.abs(mDownLeft.getSelectedSensorVelocity() * (10.0 / 2048.0)));

    SmartDashboard.putNumber("Top Right ACTUAL Speed RPS", Math.abs(mUpRight.getSelectedSensorVelocity() * (10.0 / 2048.0)));
    SmartDashboard.putNumber("Bottom Right ACTUAL Speed RPS", Math.abs(mDownRight.getSelectedSensorVelocity() * (10.0 / 2048.0)));
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
