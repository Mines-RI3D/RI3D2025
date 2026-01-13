package frc.robot.subsystems;

import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveBase extends SubsystemBase{

    
    SparkMax leftLeader = new SparkMax(DriveConstants.MOTOR_ID_FRONT_LEFT, MotorType.kBrushless),
        rightLeader = new SparkMax(DriveConstants.MOTOR_ID_FRONT_RIGHT, MotorType.kBrushless),
        leftFollower = new SparkMax(DriveConstants.MOTOR_ID_REAR_LEFT, MotorType.kBrushless),
        rightFollower = new SparkMax(DriveConstants.MOTOR_ID_REAR_RIGHT, MotorType.kBrushless);

    DifferentialDrive drive = new DifferentialDrive(leftLeader, rightLeader);

    AHRS ahrs = new AHRS(NavXComType.kMXP_UART);

    
    public DriveBase(){

        SparkMaxConfig baseConfig = new SparkMaxConfig();
        SparkMaxConfig leftLeaderConfig = new SparkMaxConfig();
        SparkMaxConfig rightLeaderConfig = new SparkMaxConfig();
        SparkMaxConfig leftFollowerConfig = new SparkMaxConfig();
        SparkMaxConfig rightFollowerConfig = new SparkMaxConfig();
    

        baseConfig.smartCurrentLimit(40)
        .idleMode(IdleMode.kCoast);

        leftLeaderConfig
        .apply(baseConfig)
        .inverted(DriveConstants.LEFT_INVERTED);

        leftFollowerConfig
        .apply(baseConfig)
        .follow(leftLeader);

        rightLeaderConfig
        .apply(baseConfig)
        .inverted(DriveConstants.RIGHT_INVERTED);

        rightFollowerConfig
        .apply(baseConfig)
        .follow(rightLeader);

        leftLeader.configure(leftLeaderConfig, ResetMode.kNoResetSafeParameters,PersistMode.kPersistParameters);
        rightLeader.configure(rightLeaderConfig, ResetMode.kNoResetSafeParameters,PersistMode.kPersistParameters);
        leftFollower.configure(leftFollowerConfig, ResetMode.kNoResetSafeParameters,PersistMode.kPersistParameters);
        rightFollower.configure(rightFollowerConfig, ResetMode.kNoResetSafeParameters,PersistMode.kPersistParameters);

        SmartDashboard.putData(driveOneSec());

    }

    public void driveDifferential(double speed, double rotation){
        drive.curvatureDrive(speed, rotation,true);
    }

    public void driveTank(double left, double right){
        drive.tankDrive(left, right);
    }

    public void stop(){
        drive.stopMotor();
    }

    public Command driveTimed(double speed,double time){
        return Commands.race(
            Commands.run(() -> driveDifferential(speed, 0), this ),
            Commands.waitSeconds(time)
        )
        .andThen(Commands.runOnce(this::stop));
    } 

    public Command driveOneSec(){
        return driveTimed(0.1,1.0);
    }

    // public Rotation2d getRotation(){
    //     return Rotation2d.fromDegrees(ahrs.getYaw());
    // }

    public void periodic(){
        // SmartDashboard.putNumber("rotation degs", getRotation().getDegrees());
    }
}
