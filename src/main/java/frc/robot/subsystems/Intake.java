package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;

import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.RadiansPerSecond;

import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
    
    public SparkMax slapdown = new SparkMax(IntakeConstants.SlapDown.MOTOR_ID, MotorType.kBrushless);
    public SparkClosedLoopController slapdownController = slapdown.getClosedLoopController();
    public RelativeEncoder slapdownEncoder = slapdown.getEncoder();

    public SparkMax intake = new SparkMax(IntakeConstants.Intake.MOTOR_ID, MotorType.kBrushless);
    public SparkClosedLoopController intakeController = intake.getClosedLoopController();

    public Intake() {
        slapdown.configure(IntakeConstants.SlapDown.config,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
        intake.configure(IntakeConstants.Intake.config,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
    }

    public void setIntakeSpeed(AngularVelocity velocity){
        intakeController.setSetpoint(velocity.in(RadiansPerSecond),ControlType.kVelocity);
    }

    public void setSlapdownSetpoint(Angle angle){
        slapdownController.setSetpoint(angle.in(Radians),ControlType.kPosition);
    }

    public void runIntake(){
        intakeController.setSetpoint(0,ControlType.kVelocity);
    }

}
