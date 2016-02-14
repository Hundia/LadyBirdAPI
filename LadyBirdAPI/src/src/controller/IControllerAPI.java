package src.controller;

public interface IControllerAPI {

	final static int PITCH						=	1;
	final static int ROLL							=	2;
	final static int THROTTLE					= 3;
	final static int YAW							=	4;
	final static double EPSILON				=	0.0001;
	final static int T_CHANNEL_MIN		= 1200;
	final static int T_CHANNEL_MAX		=	1800;
	final static int RPY_CHANNEL_MIN	= 1250;
	final static int RPY_CHANNEL_MAX	= 1750;
	final static int T_INIT						= 1000;
	final static int T_FACTOR					= 1000;
	final static int	RPY_INIT				= 1500;
	final static int	RPY_FACTOR			= 500;
	final static double GRAVITY_COMP	= 0.4;
	
	void initPIDs();
	
	void convertXYZtoAngles(final float xyz[], float to[]);

	void matrixDiff(final float m1[], final float m2[], float m[]);


	void toZeros(float m[]);

	// 3x3 X 3x1
	void matrixMult(final float m1[][], final float m2[], float res[]);

	/*
	*
	*  THIS FUNCTION DOES NOTHING!!
	*
	*/
	void angle2dcm(float x, float y, float z, float ret[][]);

	void computeControl(final float state1[], final float xyz2[], float ret_value[]);
}
