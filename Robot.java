// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot;

// import com.revrobotics.CANSparkLowLevel.MotorType;
// import com.revrobotics.CANSparkMax;

// import edu.wpi.first.util.sendable.Sendable;
// import edu.wpi.first.wpilibj.Joystick;
// import edu.wpi.first.wpilibj.TimedRobot;
// import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
// import com.revrobotics.CANSparkBase;
// import com.revrobotics.CANSparkBase.IdleMode;

// import edu.wpi.first.wpilibj.Timer;



// /**
//  * The VM is configured to automatically run this class, and to call the functions corresponding to
//  * each mode, as described in the TimedRobot documentation. If you change the name of this class or
//  * the package after creating this project, you must also update the build.gradle file in the
//  * project.
//  */
// public class Robot extends TimedRobot {
//   /**
//    * This function is run when the robot is first started up and should be used for any
//    * initialization code.
//    */
//     // right motors
//    private final CANSparkMax rightMotor1 = new CANSparkMax(5, MotorType.kBrushed);
//    private final CANSparkMax rightMotor2 = new CANSparkMax(6, MotorType.kBrushed);
   
//    private final CANSparkMax leftMotor1 = new CANSparkMax(3, MotorType.kBrushed);
//    private final CANSparkMax leftMotor2 = new CANSparkMax(4, MotorType.kBrushed);
//     // this is left motors

//    Joystick m_driverController = new Joystick(0);
//    Joystick m_manipController = new Joystick(1); // delete if joystick dont work

//    DifferentialDrive m_drivetrain; // if robot dont move delete this
 
//   CANSparkMax m_launchWheel = new CANSparkMax(7, MotorType.kBrushed); // for the outtake need to id
//   CANSparkMax m_feedwheel = new CANSparkMax(8, MotorType.kBrushed); // intake need to id
  
  
//   private static final String kNothingAuto = "do nothing";        // autonomous selection options
//   private static final String kLaunchAndDrive = "launch drive"; // autonomous selection options
//   private static final String kLaunch = "launch";
//   private static final String kDrive = "drive";                         // autonomous selection options
//   private String m_autoSelected;  // autonomous selection options
//   private final SendableChooser<String> m_chooser = new SendableChooser<>(); // autonomous selection options


//   // Below will be code for how much AMPS SYSTEMS CAN USE

//   static final int DRIVE_CURRENT_LIMIT_A = 60;  // drive train motor limit

//   static final int FEEDER_CURRENT_LIMIT_A =80; // feeder current limit

//   static final double FEEDER_OUT_SPEED = 1.0; // percentage of feeder expelling note

//   static final double FEEDER_IN_SPEED = -.4; // percentage of speed feeder taking in note

//   static final double FEEDER_AMP_SPEED = .4; // Percent output for amp or drop note, configure based on polycarb bend

//   static final int LAUNCHER_CURRENT_LIMIT_A = 80; //How many amps the launcher motor can use. lower to 60 if using neo

//   static final double LAUNCHER_SPEED = 1.0; // percentage output for when lauchers is intaking and expelling note

//   static final double LAUNCHER_AMP_SPEED = .17; // percentage output for scoring in amp or dropping note




// // below if robot does not work try changing the initallizing code 

//    @Override
//   public void robotInit() {
//     m_chooser.setDefaultOption("do nothing", kNothingAuto);         // set chooser to sendable
//     m_chooser.addOption("launch note and drive", kLaunchAndDrive);
//     m_chooser.addOption("launch",kLaunch);
//     m_chooser.addOption("drive", kDrive);
//     SmartDashboard.putData("Auto choices", (Sendable) m_chooser);

//     rightMotor1.setSmartCurrentLimit(DRIVE_CURRENT_LIMIT_A);    // drive train motor amp limits
//     rightMotor2.setSmartCurrentLimit(DRIVE_CURRENT_LIMIT_A);
//     leftMotor1.setSmartCurrentLimit(DRIVE_CURRENT_LIMIT_A);
//     leftMotor2.setSmartCurrentLimit(DRIVE_CURRENT_LIMIT_A);
    
//    rightMotor1.follow(rightMotor2);   //tells the back wheels to follow same command as first 
//    leftMotor1.follow(leftMotor2);      // if robot drives weird change this

//    rightMotor2.setInverted(true);
//    leftMotor2.setInverted(false);  //One side of the drivetrain must be inverted, motors opposite

//     m_drivetrain = new DifferentialDrive(rightMotor2, leftMotor2);

//     // if launcher wheel spins wrong direction change it to true here you can add white tape to determine wheel direction

//     m_feedwheel.setInverted(true);
//     m_launchWheel.setInverted(true);

//     m_feedwheel.setSmartCurrentLimit(FEEDER_CURRENT_LIMIT_A); // applies current limit to launching mechanism
//     m_launchWheel.setSmartCurrentLimit(LAUNCHER_CURRENT_LIMIT_A);

//   }

//   @Override
//   public void robotPeriodic() {}

//   @Override
//   public void autonomousInit() {}

//   @Override
//   public void autonomousPeriodic() {}

//   @Override
//   public void teleopInit() {
//     rightMotor1.setIdleMode(IdleMode.kCoast);
//     rightMotor2.setIdleMode(IdleMode.kCoast);
//     leftMotor1.setIdleMode(IdleMode.kCoast);             
//     leftMotor2.setIdleMode(IdleMode.kCoast);
    
//   }

//   @Override
//   public void teleopPeriodic() {
//     if (m_manipController.getRawButton(1)) {
//       m_launchWheel.set(LAUNCHER_SPEED);
//     }
//       else if(m_manipController.getRawButtonReleased(1))    // spins up the launcher wheel
//       {
//         m_launchWheel.set(0);
//       }

//     if (m_manipController.getRawButton(6))   // spin feeder wheel wait to spin up to full speed
//     {
//       m_feedwheel.set(FEEDER_OUT_SPEED);
//     }
//     else if(m_manipController.getRawButtonReleased(6))
//     {
//       m_feedwheel.set(0);
//     }

//     if(m_manipController.getRawButton(5))
//     {
//       m_launchWheel.set(-LAUNCHER_SPEED);
//       m_feedwheel.set(FEEDER_IN_SPEED);
//     }
//     else if(m_manipController.getRawButtonReleased(5)) // while button is held spin both motor to intake note
//     {
//       m_launchWheel.set(0);
//       m_feedwheel.set(0);
//     }

//     if (m_manipController.getRawButton(2))
//     {
//       m_feedwheel.set(FEEDER_AMP_SPEED);
//       m_launchWheel.set(LAUNCHER_AMP_SPEED);

//     }                       //While the amp button is being held, spin both motors to "spit" the note

//       else if(m_manipController.getRawButtonReleased(2))  
                                                      
//       {
//         m_feedwheel.set(0);
//         m_launchWheel.set(0);


//       }
    
//       m_drivetrain.arcadeDrive(-m_driverController.getY(), -m_driverController.getZ());


//     }   // code is for only feeder and shooter as of now 2/23/2024


  

//   @Override
//   public void disabledInit() {}

//   @Override
//   public void disabledPeriodic() {}

//   @Override
//   public void testInit() {}

//   @Override
//   public void testPeriodic() {}

//   @Override
//   public void simulationInit() {}

//   @Override
//   public void simulationPeriodic() {}
// }

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;


public class Robot extends TimedRobot {
  /*
   * Autonomous selection options.
   */
  private static final String kNothingAuto = "do nothing";
  private static final String kLaunchAndDrive = "launch drive";
  private static final String kLaunch = "launch";
  private static final String kDrive = "drive";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  
  /*
   * Drive motor controller instances.
   *
   * Change the id's to match your robot.
   * Change kBrushed to kBrushless if you are uisng NEOs.
   * The rookie kit comes with CIMs which are brushed motors.
   * Use the appropriate other class if you are using different controllers.
   */
  CANSparkMax leftRear = new CANSparkMax(3, MotorType.kBrushed);
  CANSparkMax leftFront = new CANSparkMax(4, MotorType.kBrushed);
  CANSparkMax rightRear = new CANSparkMax(5, MotorType.kBrushed);
  CANSparkMax rightFront = new CANSparkMax(6, MotorType.kBrushed);

  /*
   * A class provided to control your drivetrain. Different drive styles can be passed to differential drive:
   * https://github.com/wpilibsuite/allwpilib/blob/main/wpilibj/src/main/java/edu/wpi/first/wpilibj/drive/DifferentialDrive.java
   */
  DifferentialDrive m_drivetrain; 

  /*
   * Launcher motor controller instances.
   *
   * Like the drive motors, set the CAN id's to match your robot or use different
   * motor controller classses (VictorSPX) to match your robot as necessary.
   *
   * Both of the motors used on the KitBot launcher are CIMs which are brushed motors
   */
  CANSparkMax m_launchWheel = new CANSparkMax(7, MotorType.kBrushless); // this if for shooting dont forget to label and give id 
  CANSparkMax m_feedWheel = new CANSparkMax(8, MotorType.kBrushed); // this if for intake dont for get to label and give id

  /**
   * Roller Claw motor controller instance.
  */
  CANSparkMax m_rollerClaw = new CANSparkMax(9, MotorType.kBrushed); // this if for roller claw 
  /**
   * Climber motor controller instance. In the stock Everybot configuration a
   * NEO is used, replace with kBrushed if using a brushed motor.
   */
  CANSparkMax m_climber = new CANSparkMax(10, MotorType.kBrushless); // dont know what motor 3004 is using for its climber

    /**
   * The starter code uses the most generic joystick class.
   *
   * To determine which button on your controller corresponds to which number, open the FRC
   * driver station, go to the USB tab, plug in a controller and see which button lights up
   * when pressed down
   *
   * Buttons index from 0
   */
  Joystick m_driverController = new Joystick(0);      // Need help with this part button mapping confusion


  Joystick m_manipController = new Joystick(1); // two drivers this season


  // --------------- Magic numbers. Use these to adjust settings. ---------------

 /**
   * How many amps can an individual drivetrain motor use.
   */
  static final int DRIVE_CURRENT_LIMIT_A = 60; // 

  /**
   * How many amps the feeder motor can use.
   */
  static final int FEEDER_CURRENT_LIMIT_A = 60;

  /**
   * Percent output to run the feeder when expelling note
   */
  static final double FEEDER_OUT_SPEED = 1.0;

  /**
   * Percent output to run the feeder when intaking note
   */
  static final double FEEDER_IN_SPEED = -.4;

  /**
   * Percent output for amp or drop note, configure based on polycarb bend
   */
  static final double FEEDER_AMP_SPEED = .4;

  /**
   * How many amps the launcher motor can use.
   *
   * In our testing we favored the CIM over NEO, if using a NEO lower this to 60 !!!
   */
  static final int LAUNCHER_CURRENT_LIMIT_A = 60;

  /**
   * Percent output to run the launcher when intaking AND expelling note
   */
  static final double LAUNCHER_SPEED = 1.0;

  /**
   * Percent output for scoring in amp or dropping note, configure based on polycarb bend
   * .14 works well with no bend from our testing
   */
  static final double LAUNCHER_AMP_SPEED = .17; // .17 is default
  /**
   * Percent output for the roller claw // THIS IS FOR CLAW WILL DO WHEN CLAW IS DONE
   */
  static final double CLAW_OUTPUT_POWER = .5;
  /**
   * Percent output to help retain notes in the claw
   */
  static final double CLAW_STALL_POWER = .1;
  /**
   * Percent output to power the climber
   */
  static final double CLIMER_OUTPUT_POWER = 1;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("do nothing", kNothingAuto);
    m_chooser.addOption("launch note and drive", kLaunchAndDrive);
    m_chooser.addOption("launch", kLaunch);
    m_chooser.addOption("drive", kDrive);
    SmartDashboard.putData("Auto choices", m_chooser);    // might add to main code need to see what it does



    /*
     * Apply the current limit to the drivetrain motors
     */
    leftRear.setSmartCurrentLimit(DRIVE_CURRENT_LIMIT_A);         // this would be left and right motor for us dont know what current limit means
    leftFront.setSmartCurrentLimit(DRIVE_CURRENT_LIMIT_A);
    rightRear.setSmartCurrentLimit(DRIVE_CURRENT_LIMIT_A);
    rightFront.setSmartCurrentLimit(DRIVE_CURRENT_LIMIT_A);

    /*
     * Tells the rear wheels to follow the same commands as the front wheels
     */
    leftRear.follow(leftFront);                                 // potentially add this to the main code
    rightRear.follow(rightFront);

    /*
     * One side of the drivetrain must be inverted, as the motors are facing opposite directions
     */
    leftFront.setInverted(true);
    rightFront.setInverted(false);                                        // see if I need to add this

    m_drivetrain = new DifferentialDrive(leftFront, rightFront);

    /*
     * Launcher wheel(s) spinning the wrong direction? Change to true here.     // incase its wrong direction
     *
     * Add white tape to wheel to help determine spin direction.
     */
    m_feedWheel.setInverted(true);                                      
    m_launchWheel.setInverted(true);

    /*
     * Apply the current limit to the launching mechanism
     */
    m_feedWheel.setSmartCurrentLimit(FEEDER_CURRENT_LIMIT_A);
    m_launchWheel.setSmartCurrentLimit(LAUNCHER_CURRENT_LIMIT_A);

    /*
     * Inverting and current limiting for roller claw and climber
     */
    m_rollerClaw.setInverted(false);
    m_climber.setInverted(false);

    m_rollerClaw.setSmartCurrentLimit(60);
    m_climber.setSmartCurrentLimit(60);               // do this when climber and roller claw work

    /*
     * Motors can be set to idle in brake or coast mode.
     * 
     * Brake mode is best for these mechanisms
     */
    m_rollerClaw.setIdleMode(IdleMode.kBrake);
    m_climber.setIdleMode(IdleMode.kBrake);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test modes.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Time (seconds)", Timer.getFPGATimestamp());
  }


  /*
   * Auto constants, change values below in autonomousInit()for different autonomous behaviour
   *
   * A delayed action starts X seconds into the autonomous period
   *
   * A time action will perform an action for X amount of seconds
   *
   * Speeds can be changed as desired and will be set to 0 when
   * performing an auto that does not require the system
   */
  double AUTO_LAUNCH_DELAY_S;
  double AUTO_DRIVE_DELAY_S;

  double AUTO_DRIVE_TIME_S;

  double AUTO_DRIVE_SPEED;
  double AUTO_LAUNCHER_SPEED;

  double autonomousStartTime;

  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();

    leftRear.setIdleMode(IdleMode.kBrake);
    leftFront.setIdleMode(IdleMode.kBrake);
    rightRear.setIdleMode(IdleMode.kBrake);
    rightFront.setIdleMode(IdleMode.kBrake);

    AUTO_LAUNCH_DELAY_S = 2;
    AUTO_DRIVE_DELAY_S = 3;

    AUTO_DRIVE_TIME_S = 2.0;
    AUTO_DRIVE_SPEED = -0.5;
    AUTO_LAUNCHER_SPEED = 1;
    
    /*
     * Depending on which auton is selected, speeds for the unwanted subsystems are set to 0
     * if they are not used for the selected auton
     *
     * For kDrive you can also change the kAutoDriveBackDelay
     */
    if(m_autoSelected == kLaunch)
    {
      AUTO_DRIVE_SPEED = 0;                   // I guess this changes autonomous speed
    }
    else if(m_autoSelected == kDrive)
    {
      AUTO_LAUNCHER_SPEED = 0;                          // Launcher speed
    }
    else if(m_autoSelected == kNothingAuto)
    {
      AUTO_DRIVE_SPEED = 0;                                   
      AUTO_LAUNCHER_SPEED = 0;
    }

    autonomousStartTime = Timer.getFPGATimestamp();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

    double timeElapsed = Timer.getFPGATimestamp() - autonomousStartTime;

    /*
     * Spins up launcher wheel until time spent in auto is greater than AUTO_LAUNCH_DELAY_S
     *
     * Feeds note to launcher until time is greater than AUTO_DRIVE_DELAY_S
     *
     * Drives until time is greater than AUTO_DRIVE_DELAY_S + AUTO_DRIVE_TIME_S
     *
     * Does not move when time is greater than AUTO_DRIVE_DELAY_S + AUTO_DRIVE_TIME_S
     */
    if(timeElapsed < AUTO_LAUNCH_DELAY_S)
    {
      m_launchWheel.set(AUTO_LAUNCHER_SPEED);
      m_drivetrain.arcadeDrive(0, 0);

    }
    else if(timeElapsed < AUTO_DRIVE_DELAY_S)
    {
      m_feedWheel.set(AUTO_LAUNCHER_SPEED);
      m_drivetrain.arcadeDrive(0, 0);
    }
    else if(timeElapsed < AUTO_DRIVE_DELAY_S + AUTO_DRIVE_TIME_S)
    {
      m_launchWheel.set(0);
      m_feedWheel.set(0);
      m_drivetrain.arcadeDrive(AUTO_DRIVE_SPEED, 0);
    }
    else
    {
      m_drivetrain.arcadeDrive(0, 0);
    }
    /* For an explanation on differintial drive, squaredInputs, arcade drive and tank drive see the bottom of this file */
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    /*
     * Motors can be set to idle in brake or coast mode.
     *
     * Brake mode effectively shorts the leads of the motor when not running, making it more
     * difficult to turn when not running.
     *
     * Coast doesn't apply any brake and allows the motor to spin down naturally with the robot's momentum.
     *
     * (touch the leads of a motor together and then spin the shaft with your fingers to feel the difference)
     *
     * This setting is driver preference. Try setting the idle modes below to kBrake to see the difference.
     */
    leftRear.setIdleMode(IdleMode.kBrake);
    leftFront.setIdleMode(IdleMode.kBrake);
    rightRear.setIdleMode(IdleMode.kBrake);             // dont know if we need coast
    rightFront.setIdleMode(IdleMode.kBrake);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    /*
     * Spins up the launcher wheel
     */
    if (m_manipController.getRawButton(1)) {
      m_launchWheel.set(LAUNCHER_SPEED);                    // need to see what rawbutton 1 is 
    }
    else if(m_manipController.getRawButtonReleased(1)) // spins up launcher wheel which is x
    {
      m_launchWheel.set(0);
    }

    /*
     * Spins feeder wheel, wait for launch wheel to spin up to full speed for best results
     */
    if (m_manipController.getRawButton(6))
    {
      m_feedWheel.set(FEEDER_OUT_SPEED);                        // need to see what raw button 6 is
    }
    else if(m_manipController.getRawButtonReleased(6))  //rb should outtake which is bottom wheel
    {
      m_feedWheel.set(0);
    }

    /*
     * While the button is being held spin both motors to intake note
     */
    if(m_manipController.getRawButton(5))   
    {
      m_launchWheel.set(-LAUNCHER_SPEED);                                 // intake is 2 buttons
      m_feedWheel.set(FEEDER_IN_SPEED);
    }
    else if(m_manipController.getRawButtonReleased(5)) //LB for both motors
    {
      m_launchWheel.set(0);
      m_feedWheel.set(0);
    }

    /*
     * While the amp button is being held, spin both motors to "spit" the note
     * out at a lower speed into the amp
     *
     * (this may take some driver practice to get working reliably)
     */
    if(m_manipController.getRawButton(2))         // A spins both motor to spit note out lower speed                           
    {
      m_feedWheel.set(FEEDER_AMP_SPEED);
      m_launchWheel.set(LAUNCHER_AMP_SPEED);
    }
    else if(m_manipController.getRawButtonReleased(2))
    {
      m_feedWheel.set(0);
      m_launchWheel.set(0);
    }

    /**
     * Hold one of the two buttons to either intake or exjest note from roller claw
     * 
     * One button is positive claw power and the other is negative
     * 
     * It may be best to have the roller claw passively on throughout the match to 
     * better retain notes but we did not test this
     */ 
    if(m_manipController.getRawButton(3))                               
    {
      m_rollerClaw.set(CLAW_OUTPUT_POWER);                                // roller claw button 4
    }
    else if(m_manipController.getRawButton(4))
    {
      m_rollerClaw.set(-CLAW_OUTPUT_POWER);
    }
    else
    {
      m_rollerClaw.set(0);
    }

    /**
     * POV is the D-PAD (directional pad) on your controller, 0 == UP and 180 == DOWN
     * 
     * After a match re-enable your robot and unspool the climb
     */
    if(m_manipController.getPOV() == 0)
    {
      m_climber.set(1);                                     // need logitech controller for this bot
    }
    else if(m_manipController.getPOV() == 180)
    {
      m_climber.set(-1);
    }
    else
    {
      m_climber.set(0);
    }
  
    /*
     * Negative signs are here because the values from the analog sticks are backwards
     * from what we want. Pushing the stick forward returns a negative when we want a
     * positive value sent to the wheels.
     *
     * If you want to change the joystick axis used, open the driver station, go to the
     * USB tab, and push the sticks determine their axis numbers
     *
     * This was setup with a logitech controller, note there is a switch on the back of the
     * controller that changes how it functions
     */
    m_drivetrain.arcadeDrive(-m_driverController.getRawAxis(1), -m_driverController.getRawAxis(0), false);
  }
}

/*
 * The kit of parts drivetrain is known as differential drive, tank drive or skid-steer drive.
 *
 * There are two common ways to control this drivetrain: Arcade and Tank
 *
 * Arcade allows one stick to be pressed forward/backwards to power both sides of the drivetrain to move straight forwards/backwards.
 * A second stick (or the second axis of the same stick) can be pushed left/right to turn the robot in place.
 * When one stick is pushed forward and the other is pushed to the side, the robot will power the drivetrain
 * such that it both moves fowards and turns, turning in an arch.
 *
 * Tank drive allows a single stick to control of a single side of the robot.
 * Push the left stick forward to power the left side of the drive train, causing the robot to spin around to the right.
 * Push the right stick to power the motors on the right side.
 * Push both at equal distances to drive forwards/backwards and use at different speeds to turn in different arcs.
 * Push both sticks in opposite directions to spin in place.
 *
 * arcardeDrive can be replaced with tankDrive like so:
 *
 * m_drivetrain.tankDrive(-m_driverController.getRawAxis(1), -m_driverController.getRawAxis(5))
 *
 * Inputs can be squared which decreases the sensitivity of small drive inputs.
 *
 * It literally just takes (your inputs * your inputs), so a 50% (0.5) input from the controller becomes (0.5 * 0.5) -> 0.25
 *
 * This is an option that can be passed into arcade or tank drive:
 * arcadeDrive(double xSpeed, double zRotation, boolean squareInputs)
 *
 *
 * For more information see:
 * https://docs.wpilib.org/en/stable/docs/software/hardware-apis/motors/wpi-drive-classes.html
 *
 * https://github.com/wpilibsuite/allwpilib/blob/main/wpilibj/src/main/java/edu/wpi/first/wpilibj/drive/DifferentialDrive.java
 *
 */