package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.robot.Constants.ShooterConstants;

public class Shooter {
    
    final TalonFX flywheel = new TalonFX(ShooterConstants.Flywheel.MOTOR_ID);
    final SparkMax hoodSpinner = new SparkMax(
        ShooterConstants.HoodSpinner.MOTOR_ID, MotorType.kBrushless);

    public Shooter() {
        

        flywheel.getConfigurator().apply(ShooterConstants.Flywheel.config);

        hoodSpinner.configure(ShooterConstants.HoodSpinner.config,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
    }

}
