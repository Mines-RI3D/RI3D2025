package frc.robot;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Radian;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.units.measure.Angle;

public final class Constants {

    public static final class DriveConstants{

        public static final int MOTOR_ID_FRONT_LEFT =3;
        public static final int MOTOR_ID_REAR_LEFT =4;
        
        public static final int MOTOR_ID_FRONT_RIGHT =1;
        public static final int MOTOR_ID_REAR_RIGHT =2;

        public static final boolean LEFT_INVERTED = false;
        public static final boolean RIGHT_INVERTED = true;

    }

    public static final class ShooterConstants{

        public static final class Flywheel{
            public static final int MOTOR_ID = 5;

            public static final TalonFXConfiguration config = new TalonFXConfiguration();
            static{
                // config.Slot0.kP = 0.1;
                // config.Slot0
            }
            
        }

        public static final class Pusher{
            public static final int MOTOR_ID = 6;

            public static final int DIO_PORT_LIMIT_SWITCH = 1;

            public static final SparkMaxConfig config = new SparkMaxConfig();
            static{
                config.closedLoop.pid(0.1, 0, 0);
                config.idleMode(IdleMode.kBrake);
                config.inverted(false);
                config.smartCurrentLimit(25,25);
            }
        }

        public static final class HoodSpinner{
            public static final int MOTOR_ID = 7;

            public static final int DIO_PORT_LIMIT_SWITCH = 0;

            public static final SparkMaxConfig config = new SparkMaxConfig();
            static{
                config.closedLoop.pid(0.1, 0, 0);
                config.closedLoop.allowedClosedLoopError(0.1, ClosedLoopSlot.kSlot0); //radians
                config.idleMode(IdleMode.kBrake);
                config.inverted(false);
                config.smartCurrentLimit(25,25);
            }
        }

    }

    public static final class IntakeConstants{

        public static final int bottomSlapdownDIOPort = 2;
        public static final int topSlapdownDIOPort = 3;

        public static final class SlapDown{
            public static final int MOTOR_ID = 8;

            public static final Angle topAngle = Angle.ofBaseUnits(79.192, Degrees);

            public static final Angle bottomAngle = Angle.ofBaseUnits(0, Radian);

            public static final SparkMaxConfig config = new SparkMaxConfig();
            static{
                config.closedLoop.pid(0.1, 0, 0,ClosedLoopSlot.kSlot0);
                config.closedLoop.allowedClosedLoopError(0.1, ClosedLoopSlot.kSlot0);
                config.idleMode(IdleMode.kCoast);
                config.inverted(false);
                config.smartCurrentLimit(25,25);
            }
        }

        public static final class Intake{
            public static final int MOTOR_ID = 9;

            public static final SparkMaxConfig config = new SparkMaxConfig();
            static{
                config.closedLoop.pid(0.1, 0, 0, ClosedLoopSlot.kSlot0);
                config.closedLoop.allowedClosedLoopError(0.1, ClosedLoopSlot.kSlot0);
                config.idleMode(IdleMode.kCoast);
                config.inverted(false);
                config.smartCurrentLimit(25,25);
            }
        }
    }

}
