package frc.robot.subsystems;

import static edu.wpi.first.units.Units.RadiansPerSecond;

import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
    
    final TalonFX flywheel = new TalonFX(ShooterConstants.Flywheel.MOTOR_ID);
    final VelocityVoltage velocityVoltage = new VelocityVoltage(RadiansPerSecond.of(0)).withSlot(0);

    final SparkMax hoodSpinner = new SparkMax(ShooterConstants.HoodSpinner.MOTOR_ID, MotorType.kBrushless);
    final SparkClosedLoopController hoodSpinnerController = hoodSpinner.getClosedLoopController();
    final RelativeEncoder hoodEncoder = hoodSpinner.getEncoder();

    final SparkMax pusher = new SparkMax(ShooterConstants.Pusher.MOTOR_ID, MotorType.kBrushless);
    final SparkClosedLoopController pusherController = pusher.getClosedLoopController();

    final DigitalInput hoodLimitSwitch = new DigitalInput(ShooterConstants.HoodSpinner.DIO_PORT_LIMIT_SWITCH);
    final DigitalInput ballReadyLimitSwitch = new DigitalInput(ShooterConstants.Pusher.DIO_PORT_LIMIT_SWITCH);

    public Shooter() {

        flywheel.getConfigurator().apply(ShooterConstants.Flywheel.config);
        pusher.configure(ShooterConstants.Pusher.config,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
        hoodSpinner.configure(ShooterConstants.HoodSpinner.config,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
    }

    public boolean hoodLimitSwitchPressed(){
        return hoodLimitSwitch.get();
    }

    public boolean ballReadyLimitSwitchPressed(){
        return ballReadyLimitSwitch.get();
    }

    public void setFlywheel(AngularVelocity velocity){
        flywheel.setControl(velocityVoltage.withVelocity(velocity));
    }

    public Command setFlywheelCommand(AngularVelocity velocity){
        return Commands.run(()->setFlywheel(velocity))
        .andThen(Commands.runOnce(this::stopFlywheel));
    }

    public void stopFlywheel(){
        flywheel.stopMotor();
    }

    public void setPusher(double speed){
        pusher.set(speed);
    }

    public Command setPusherCommand(double speed){
        return Commands.run(()->setPusher(speed))
        .andThen(Commands.runOnce(this::stopPusher));
    }

    public void stopPusher(){
        pusher.stopMotor();
    }

    public void setHood( Rotation2d position){
        hoodSpinnerController.setSetpoint(position.getRadians(),ControlType.kPosition);
    }

    public boolean hoodAtSetpoint(){
        return hoodSpinnerController.isAtSetpoint();
    }

    public void resetHoodEncoder(){
        hoodEncoder.setPosition(0);
    }

    public Rotation2d getHoodEncoderPosition(){
        return Rotation2d.fromRotations(hoodEncoder.getPosition());
    }

}
