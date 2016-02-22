//---------------------------------------------------
//|	Created By XmlMessagesGenerator Version: 5.5
//---------------------------------------------------

package src.optitrack.api;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import src.optitrack.api.Position;
import src.optitrack.api.Orientation;

public class RigidBodyData extends Message {

   public RigidBodyData(){
          init(true, ByteBufferType.NonDirectByteBuffer, MSG_SIZE);

   } // constructor

   /**
    * @param createOwnMessageByteBuffer - if true - allocate new ByteBuffer
    *									 otherwise - user will use setMessageByteBuffer()  
    */
   public RigidBodyData(boolean createOwnMessageByteBuffer){
   		init(createOwnMessageByteBuffer, ByteBufferType.NonDirectByteBuffer, MSG_SIZE);
   }

   public RigidBodyData(ByteBufferType byteBufferType){
		init(true, byteBufferType, MSG_SIZE);
   } // constructor
    protected final int MSG_SIZE = 32;
    protected final int MSG_OPCODE = 1;

 /**
  * @return messages length in bytes (payload + header)
  */
  public int getMessageSize() {
      return this.MSG_SIZE;
  }

 /**
  * @return messages opcode
  */
  public int getMessageOpcode() {
      return MSG_OPCODE;
  }

     /**
     * create (new) all the arrays & structs
     */
     protected void initLocalMembers() {
     }

  //local message properties
	// fields
    public Position position = new Position();
    public Orientation orientation = new Orientation();


       /**
       * @return field byte offset in the message
       */
       public int get_rigidBodyID_Offset_InMessage() {
       		return  (msgOffset + 0);
       }



       /**
       * @return field byte offset in the message
       */
       public int get_position_Offset_InMessage() {
       		return  (msgOffset + 4);
       }



       /**
       * @return field byte offset in the message
       */
       public int get_orientation_Offset_InMessage() {
       		return  (msgOffset + 16);
       }



	/**
	* This method will set field: rigidBodyID & update message byteBuffer
	* @param value - value to set
	*/
	public void set_rigidBodyID(int value) {
		this.msgByteBuffer.putInt(msgOffset+0, value);
	}


	/**
	* This method will Get field: rigidBodyID value
	* @return field value
	*/
	public int get_rigidBodyID() {
		return (this.msgByteBuffer.getInt(msgOffset+0));
	}


   /**
   * This function will set the ByteBuffer of the msg
   * @param bf - Bytebuffer to set
   */
   public void setMessageByteBuffer(ByteBuffer bf) {

		this.msgByteBuffer = bf;
		this.header.setMessageByteBuffer(bf);
		position.setMessageByteBuffer(bf,  get_position_Offset_InMessage());
		orientation.setMessageByteBuffer(bf,  get_orientation_Offset_InMessage());
   }

   public void setMessageByteBuffer(ByteBuffer bf, int messageOffset) {
   	this.msgOffset = messageOffset;
   	this.msgByteBuffer = bf;
   	this.header.setMessageByteBuffer(bf, msgOffset);
		position.setMessageByteBuffer(bf,  msgOffset + get_position_Offset_InMessage());
		orientation.setMessageByteBuffer(bf,  msgOffset + get_orientation_Offset_InMessage());
	}


}
