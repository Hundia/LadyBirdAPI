package src.controller;

import src.pid.Pid;

public class ControllerAPI implements IControllerAPI {

	Pid[] PIDs = new Pid[3];
	
	@Override
	public void initPIDs() {
		PIDs[0] = new Pid(0.6, 0.0, 0.3);
		PIDs[1] = new Pid(0.6, 0.0, 0.3);
		PIDs[2] = new Pid(2.0, 0.0, 0.75);

	}

	@Override
	public void convertXYZtoAngles(float[] xyz, float[] to) {
		
		float pitch_val = (float) (xyz[0] / Math.hypot(xyz[1], xyz[2]));
		float roll_val = (float) (xyz[1] / Math.hypot(xyz[0], xyz[2]));
		float yaw_val = Float.MAX_VALUE;


		to[0] = pitch_val;
		to[1] = roll_val;
		to[2] = yaw_val;

	}

	@Override
	public void matrixDiff(float[] m1, float[] m2, float[] m) {
		for (int i = 0; i < 3; ++i){
			m[i] = m1[i] - m2[i];
		}

	}

	@Override
	public void toZeros(float[] m) {
		for (int i = 0; i < 3; ++i){
			m[i] = (float) 0.0;
		}
	}

	@Override
	public void matrixMult(float[][] m1, float[] m2, float[] res) {
		for (int i = 0; i < 3; ++i){
			res[i] = 0;
		}

		for (int i = 0; i < 3; ++i){ // POr cada fila de m1
			for (int j = 0; j < 3; ++j){ // Por cada columna de m1
				res[i] += m1[i][j] * m2[j];
			}
		}
	}

	@Override
	public void angle2dcm(float x, float y, float z, float[][] ret) {
		float[] angles = new float[3];
		float[] cang = new float[3];
		float[] sang = new float[3];

		angles[0] = x;
		angles[1] = y;
		angles[2] = z;
		
		for (int i = 0; i < 3; ++i){
			cang[i] = (float) Math.cos(angles[i]);
			sang[i] = (float) Math.sin(angles[i]);
		}

		ret[0][0] = cang[1] * cang[0];
		ret[0][1] = cang[1] * sang[0];
		ret[0][2] = -sang[1];

		ret[1][0] = sang[2] * sang[1] * cang[0] - cang[2] * sang[0];
		ret[1][1] = sang[2] * sang[1] * sang[0] + cang[2] * cang[0];
		ret[1][2] = sang[2] * cang[1];

		ret[2][0] = cang[2] * sang[1] * cang[0] + sang[2] * sang[0];
		ret[2][1] = cang[2] * sang[1] * sang[0] - sang[2] * cang[0];
		ret[2][2] = cang[2] * cang[1];

	}

	@Override
	public void computeControl(float[] state1, float[] xyz2, float[] ret_value) {
		float[] xyz1 = new float[3];
		float[] rot1 = new float[3];;

		for (int i = 0; i < 3; ++i){
			xyz1[i] = state1[i];
		}

		for (int i = 0; i < 3; ++i){
			rot1[i] = state1[i + 3];
		}

		float[][] R = new float[3][3];
		angle2dcm(rot1[2], 0, 0, R);

		float[] dif = new float[3];
		matrixDiff(xyz2, xyz1, dif);

		float[] e_pos = new float[3];
		matrixMult(R, dif, e_pos);

		//cout << "e_pos: " << e_pos[0] << ' ' << e_pos[1] << ' ' << e_pos[2] << endl;

		float[] new_pos = new float[3];
		toZeros(new_pos);

		for (int i = 0; i < 3; ++i){
			new_pos[i] = PIDs[i].compute(0, e_pos[i]);
		}
		//cout << "new_pos: " << new_pos[0] << ' ' << new_pos[1] << ' ' << new_pos[2] << endl;

		float[][] RR = new float[3][3];
		angle2dcm(rot1[2], rot1[1], rot1[0], RR);
		float throttle_vector_comp = (float) 0.0;

		if (RR[2][2] > 0.1){
			throttle_vector_comp = 1 / RR[2][2];
		}
		else{
			throttle_vector_comp = (float) 10.0;
		}

		new_pos[2] = (float) ((new_pos[2] + GRAVITY_COMP) * throttle_vector_comp);

		float[] vals = new float[3];
		convertXYZtoAngles(new_pos, vals);

		ret_value[0] = RPY_INIT + RPY_FACTOR * vals[0];
		ret_value[1] = RPY_INIT + RPY_FACTOR * vals[1];
		ret_value[2] = T_INIT + T_FACTOR * new_pos[2];
		ret_value[3] = RPY_INIT + RPY_FACTOR * 0;

		for (int i = 0; i < 4; ++i){

			if (i == THROTTLE){

				if (ret_value[i] > T_CHANNEL_MAX){
					ret_value[i] = T_CHANNEL_MAX;
				}
				if (ret_value[i] < T_CHANNEL_MIN){
					ret_value[i] = T_CHANNEL_MIN;
				}

			}
			else{
				if (ret_value[i] < RPY_CHANNEL_MIN){
					ret_value[i] = RPY_CHANNEL_MIN;
				}
				if (ret_value[i] > RPY_CHANNEL_MAX){
					ret_value[i] = RPY_CHANNEL_MAX;
				}
			}

		}

	}

	public static void main(String[] args) {
		System.out.println("Float MAx: " + Float.MAX_VALUE);
	}
}
