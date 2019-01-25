package org.usfirst.frc.team2500.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Chassis extends Subsystem{

	private Talon leftMotor;
	private Talon rightMotor;


	public Chassis(leftMotor, rightMotor){
		leftChassis = new Talon(leftMotor);
		rightChassis = new Talon(rightMotor);
	}

	@Override
	protected void initDefaultCommand() {
	}

	public void tankDrive(double left,double right){
		leftMotor.set(left);
		rightMotor.set(right);
	}

	public void arcadeDrive(double throttle, double turn) {
	    arcadeDrive(throttle, turn, true);
	}

	public void arcadeDrive(double throttle,double turn, boolean square){
		if(square){
			throttle = Math.pow(throttle, 2);
			turn = Math.pow(turn, 2);
		}

    double leftMotorOutput;
    double rightMotorOutput;

		double max = fabs(throttle);
		if (fabs(turn)>max){
			max = fabs(turn);
		}
		double sum = throttle + turn;
		double dif = throttle - turn;

		//Top Half
	    if(throttle>=0)
	    {
			//Righ Half
		    if(turn>=0) {
				leftMotorOutput = max;
				rightMotorOutput = dif;
			}
			//Left Half
		    else {
				leftMotorOutput = sum;
				rightMotorOutput = max;
		    }
		}
		//Bottom Half
	    else
	    {
			//Righ Half
		    if(turn>=0) {
				leftMotorOutput = sum;
				rightMotorOutput = -max;
			}
			//Left Half
		    else {
				leftMotorOutput = -max;
				rightMotorOutput = dif;
		    }
	    }

	    tankDrive(leftMotorOutput,rightMotorOutput);
	}
}
