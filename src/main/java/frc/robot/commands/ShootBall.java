package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;

public class ShootBall extends SequentialCommandGroup {
    public ShootBall(Shooter shooter){
        //run flywheel until flywheel at setpoint, 
        //then run pusher for seconds, or until the button is pressed 
        // then 
        super(
            
            shooter.setFlywheelCommand(ShooterConstants.Flywheel.shooterSpeed)
            .raceWith(
                Commands.sequence(
                    Commands.race(
                        shooter.setPusherCommand(ShooterConstants.Pusher.pushSpeed),
                        Commands.waitSeconds(3)
                    ).until(shooter::ballReadyLimitSwitchPressed)
                    
                )
            )
        );
    }
}
