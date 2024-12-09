// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import pabeles.concurrency.ConcurrencyOps.Reset;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
// import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.math.proto.Controller;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  // private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  // private final RomiDrivetrain m_drivetrain = new RomiDrivetrain();

  // define the motors
  private Spark left_motor = new Spark(0); // PWM 0 is left motor
  private Spark right_motor = new Spark(1); // PWM 1 is right motorw

  //define Xbox Controller
  private XboxController controller = new XboxController(0); // Xbox Controller is connected to port 0


  // differintial drive object
  // private DifferentialDrive drive = new DifferentialDrive(left_motor, right_motor);
  

 //defining our encoders
  private Encoder leftEncoder = new Encoder(4, 5);
  private Encoder rightEncoder = new Encoder(6, 7);
  double wheelDiameter = 2.6875;
  double trackwidth = 2.75;
  double pulsePerRev = 1440;

  double circumfrence = Math.PI * wheelDiameter;
  double disPerPulse = circumfrence / pulsePerRev;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    // m_autoSelected = m_chooser.getSelected();
    // // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    // System.out.println("Auto selected: " + m_autoSelected);

    // m_drivetrain.resetEncoders();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    // switch (m_autoSelected) {
    //   case kCustomAuto:
    //     // Put custom auto code here
    //     break;
    //   case kDefaultAuto:
    //   default:
    //     // Put default auto code here
    //     break;
    // }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    right_motor.setInverted(true);
    leftEncoder.reset();
    rightEncoder.reset();

    leftEncoder.setDistancePerPulse(disPerPulse);
    rightEncoder.setDistancePerPulse(disPerPulse);

  }


  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
  /// getting vaules from controller
  double forward = -controller.getLeftY(); //gets value for X left motor
  double turn = -controller.getRightX(); //gets value for y right motor
  boolean spinRight = controller.getYButton(); //gets Y button
  boolean spinLeft = controller.getXButton(); //gets X button


  // setting the motor speed using motor values
  if(controller.getAButtonPressed()) {
  left_motor.set(forward);
  right_motor.set(turn);
  }
  else {
  left_motor.set(forward - turn);
  right_motor.set(forward + turn);

  }
  


  // if (spinLeft) {
  //   left_motor.set(.5);
  //   right_motor.set(-.5);
  // }
  // if (spinRight) {
  //   left_motor.set(-.5);
  //   right_motor.set(.5);



  if (controller.getAButton()) {
    leftEncoder.reset();
    rightEncoder.reset();
     while ((leftEncoder.getDistance() + rightEncoder.getDistance() / 2.0 < 5)) { //drive forward
    left_motor.set(-.5);
    right_motor.set(.5);
      
     }






    
  }


      }
    
  
  
 

  








 



  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
