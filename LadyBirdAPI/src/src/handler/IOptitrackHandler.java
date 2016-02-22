package src.handler;

import src.optitrack.api.RigidBodyData;

public interface IOptitrackHandler {

	void handleRigidBodyData(RigidBodyData rigidBody);
}
