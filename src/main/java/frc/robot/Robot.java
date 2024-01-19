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

   private TalonFX mLeftMain = new TalonFX(0, "rio");
   private TalonFX mLeftFollower = new TalonFX(1, "rio");

   private TalonFX mRightMain = new TalonFX(2, "rio");
   private TalonFX mRightFollower = new TalonFX(3, "rio");

   private TunableNumber leftTuner;
   private TunableNumber rightTuner;

   private GenericEntry apply;

  @Override
  public void robotInit() {
    mLeftMain.config_kP(0, 0.2);
    mLeftMain.config_kI(0, 0.0);
    mLeftMain.config_kD(0, 0.01);
    mLeftMain.config_kF(0, 0.0);
    mLeftMain.setNeutralMode(NeutralMode.Coast);
    mLeftMain.configClosedloopRamp(0.5);

    mRightMain.config_kP(0, 0.2);
    mRightMain.config_kI(0, 0.0);
    mRightMain.config_kD(0, 0.01);
    mRightMain.config_kF(0, 0.0);
    mRightMain.setNeutralMode(NeutralMode.Coast);
    mRightMain.configClosedloopRamp(0.5);

    mLeftFollower.setInverted(true);
    mRightFollower.setInverted(false);
    
    
    leftTuner = new TunableNumber("Left Falcon RPM", 0.0, true);
    rightTuner = new TunableNumber("Right Falcon RPM", 0.0, true);
    apply = Shuffleboard.getTab("Button").add("Button", false).withWidget(BuiltInWidgets.kToggleButton).getEntry();

    mLeftMain.set(ControlMode.PercentOutput, 0.0);
    mLeftFollower.set(ControlMode.Follower, 0.0);

    mRightMain.set(ControlMode.PercentOutput, 0.0);
    mRightFollower.set(ControlMode.Follower, 0.0);
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
      if(leftTuner.get() > 0.0) {
        mLeftMain.set(ControlMode.Velocity, -leftTuner.get() / (10.0 / 2048.0));
      }


      if (rightTuner.get() > 0.0) {
        mRightMain.set(ControlMode.Velocity, rightTuner.get() / (10.0 / 2048.0));
      }
    } else {
      mLeftMain.set(ControlMode.PercentOutput, 0.0);
      mRightMain.set(ControlMode.PercentOutput, 0.0);
    }

    SmartDashboard.putNumber("Left ACTUAL Speeds", mLeftMain.getSelectedSensorVelocity() * (10.0 / 2048.0));
    // SmartDashboard.putNumber("Left Target", leftTuner.get());

    SmartDashboard.putNumber("Right ACTUAL Speeds", mRightMain.getSelectedSensorVelocity() * (10.0 / 2048.0));
    // SmartDashboard.putNumber("Right Target", rightTuner.get());
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
