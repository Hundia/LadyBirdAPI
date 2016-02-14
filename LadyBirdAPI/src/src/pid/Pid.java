package src.pid;

public class Pid {
	private static final double MILIS_PER_SEC = 1000;
	double kp, ki, kd;
	double errSum, lastErr, lastTime;
	
	public Pid(double kp, double ki, double kd) {
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
		errSum = 0;
		lastErr = 0;
		lastTime = 0;
	}
	
	public float compute(double input, double set_point){

		double time_change = 1;// 1.0 / 60 / 60 / 24;
		double now = System.currentTimeMillis( );//time(0)*100000.0 / 60 / 60 / 24; // convert to days

		if (lastTime != 0){
			time_change = (now - lastTime) / MILIS_PER_SEC;
		}
		// TODO: Need to check why they used double instead of float here..
		double error = set_point - input;
		errSum += error * time_change;
		double dErr = (error - lastErr) / time_change;

		lastErr = error;
		lastTime = now;

		//cout << kp << ' ' << ki << ' ' << kd << ' ' << error << ' ' << errSum << ' ' << dErr << endl;
		return (float) (kp*error + ki*errSum + kd*dErr);
	}
}
