package src.handler;

import src.optitrack.api.RigidBodyData;

public class LadyBirdHandler implements IOptitrackHandler {

	@Override
	public void handleRigidBodyData(RigidBodyData rigidBody) {
		System.out.println("Rigid Body: ID: " + rigidBody.get_rigidBodyID()
							+ " X: " + rigidBody.position.get_x() 
							+ " Y: " + rigidBody.position.get_y()
							+ " Z: " + rigidBody.position.get_z());
		
	}

}
