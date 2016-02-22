package src.optitrack.tcpclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import src.handler.IOptitrackHandler;
import src.handler.LadyBirdHandler;
import src.optitrack.api.RigidBodyData;

/**
 * 
 * @author Eli Hundia
 * This is an opti track client that processes only rigid bodies
 * allowing you to set a call back for a rigid body frame.
 * 
 * -- TODO, Add get descriptions from the server
 */
public class OptiTrackClient {

	//	Interface of optitrack handler, should be created
	//	with concrete implementation
	IOptitrackHandler handler = null;
	
	private RigidBodyData rigidBody = new RigidBodyData();
	
	static final int MAX_PACKETSIZE = 100000;
	ByteBuffer incommingMessage = ByteBuffer.allocate(MAX_PACKETSIZE);
	static final int MOCKUP_DATA_MSG_ID = 7;
	static final int SIZE_OF_MARKER_SET_DATA = 264;
	static final int SIZE_OF_UNKOWN_MARKER_SET_DATA = 12;
	//	Static known strings
	static final String mockUpDataRcvMsg = "Received Mock up data from server..!";
	private TcpClient tcp;
	
	public OptiTrackClient() {
		//	Were working for now only with the lady bird
		handler = new LadyBirdHandler();
		tcp = new TcpClient(this);
		tcp.makeConnection();
		tcp.receiveMessage();
	}
	
	/*
	 * Known structure of mock up data from server
	 * typedef struct
		{
		    int iFrame;                                     // host defined frame number
		    int nMarkerSets;                                // # of marker sets in this frame of data
		    sMarkerSetData MocapData[MAX_MODELS];           // MarkerSet data
		    int nOtherMarkers;                              // # of undefined markers
		    MarkerData* OtherMarkers;                       // undefined marker data
		    int nRigidBodies;                               // # of rigid bodies
		    sRigidBodyData RigidBodies[MAX_RIGIDBODIES];    // Rigid body data
		    int nSkeletons;                                 // # of Skeletons
		    sSkeletonData Skeletons[MAX_SKELETONS];         // Skeleton data
		    int nLabeledMarkers;                            // # of Labeled Markers
		    sMarker LabeledMarkers[MAX_LABELED_MARKERS];    // Labeled Marker data (labeled markers not associated with a "MarkerSet")
		    int nForcePlates;                               // # of force plates
		    sForcePlateData ForcePlates[MAX_FORCEPLATES];   // Force plate data
		    float fLatency;                                 // host defined time delta between capture and send
		    unsigned int Timecode;                          // SMPTE timecode (if available)
		    unsigned int TimecodeSubframe;                  // timecode sub-frame data
		    double fTimestamp;                              // FrameGroup timestamp
		    short params;                                   // host defined parameters
		} sFrameOfMocapData;
	 * 
	 */
	public void handleIncommingMessage(ByteBuffer msg) {
		
		//	Index in the byte buffer that will
		//	Follow us as we parse the buffer
		int currentIndex = 0;
		
		//	First off lets get the message id and the number
		//	of bytes received
		short messageId = msg.getShort(currentIndex);
		short numOfBytesReceived = msg.getShort(2);
		currentIndex += 4;
		
		//	If the message is a mock up data message
		if(MOCKUP_DATA_MSG_ID == messageId) {
			//	We do not care about skeletons and markers frames,
			//	so we will ignore them by skipping them
			
			//	There is a frame number in the API, we can use this
			//	to make sure we did not loose any packets over
			//	the tcp connection for some reason
			int frameNumber = msg.getInt(4);
			currentIndex += 4;
			
			//	So first off we need to skip the known markers
			//	and other markers onto the rigid bodies
			int numberOfMarkersToSkip = msg.getInt(8);
			currentIndex += 4;

			//	Calculate the number of bytes to skip
			int markerDataSizeToSkip = SIZE_OF_MARKER_SET_DATA*numberOfMarkersToSkip;
			
			//	Promote the index to pass the bytes of the markers
			currentIndex += markerDataSizeToSkip;
			
			//	Get the number of unknown markers to skip
			int numberOfUknownMarkersToSkip = msg.getInt(currentIndex);
			
			int unkownMarkersDataToSkip = SIZE_OF_UNKOWN_MARKER_SET_DATA*numberOfUknownMarkersToSkip;
			
			//	Promote the index to pass the bytes of the unknown makers
			currentIndex += unkownMarkersDataToSkip;
			
			//	Now we have arrived to the rigid bodies section
			//	Lets read how many rigid bodies we have and handle
			//	each one separately
			int numberOfRigidBodiesToHandle = msg.getInt(currentIndex);
			currentIndex += 4;
			
			for (int i = 0; i < numberOfRigidBodiesToHandle; i++) {
				//	Lets prepare the byte buffer for putting
				//	it inside the rigid body structure
				msg.position(currentIndex);
				msg.limit(currentIndex + rigidBody.getMessageSize());
				currentIndex += rigidBody.getMessageSize();
				
				//	Rewind the buffer for the putting process 
				//	(for second iteration and on)
				rigidBody.getMessageByteBuffer().rewind();
				rigidBody.getMessageByteBuffer().put(msg);
				
				//	Handle the rigid body using the data handler
				handler.handleRigidBodyData(rigidBody);
			}
		}
	}
	public static void main(String[] args) throws IOException {
		//OptiTrackClient client = new OptiTrackClient();
		MulticastSocket socket;
		DatagramPacket packet;
		InetAddress address; 
		
		address = InetAddress.getByName("239.255.42.99");        
		socket = new MulticastSocket(1511);
		
		//join a Multicast group and send the group salutations
		
		socket.joinGroup(address);
		byte[] arr = new byte[10];
		DatagramPacket dp = new DatagramPacket(arr, 10);
		socket.receive(new DatagramPacket(arr, 0));
//		sleep(10);
	}
}
