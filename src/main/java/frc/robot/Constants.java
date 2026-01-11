package frc.robot;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public final class Constants {

    public static final class DriveConstants{

        public static final int MOTOR_ID_FRONT_LEFT =1;
        public static final int MOTOR_ID_REAR_LEFT =2;
        
        public static final int MOTOR_ID_FRONT_RIGHT =3;
        public static final int MOTOR_ID_REAR_RIGHT =4;

        public static final boolean LEFT_INVERTED = true;
        public static final boolean RIGHT_INVERTED = false;

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
                config.idleMode(IdleMode.kBrake);
                config.inverted(false);
                config.smartCurrentLimit(25,25);
            }
        }

    }

}
