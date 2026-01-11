// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import frc.robot.subsystems.*;

public class RobotContainer {

  private final CommandPS4Controller controller = new CommandPS4Controller(0);
  private final DriveBase m_driveBase = new DriveBase();

  public RobotContainer() {
    configureBindings();

    m_driveBase.setDefaultCommand(
      Commands.run(
        ()->m_driveBase.driveDifferential(controller.getLeftY(), controller.getLeftX()),
         m_driveBase
      )
    );



  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
