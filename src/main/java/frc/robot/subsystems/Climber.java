package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class Climber extends SubsystemBase {
    

    SparkMax motor = new SparkMax(ClimberConstants.motorID,MotorType.kBrushless);

    public Climber(){
        motor.configure(ClimberConstants.motorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        
        setDefaultCommand(Commands.run(this::stop));
    }

    public void runForwards(){
        motor.set(1);
    }

    public void stop(){
        motor.stopMotor();
    }

    public void runReverse(){
        motor.set(-1);
    }

    

}
