//---------------------------------------------------
//|	Created By XmlMessagesGenerator Version: 5.5
//---------------------------------------------------

package src.optitrack.api;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

   public class Position {
	// fields

       // local fields
       private static final int SIZE = 12;
       protected int structureOffset = 0;
       protected ByteBuffer msgByteBuffer = null;

       // constructor
       public Position() {
			    initLocalMembers();
       }
      public int getSize() {
           return SIZE;
      }

      protected void initLocalStructures() {

      }


     /**
     * create (new) all the arrays & structs
     */
     protected void initLocalMembers() {
     }

      /**
      * Internal Use
      * @param byteBuffer
      * @param structureOffset
      */
      public void setMessageByteBuffer(ByteBuffer byteBuffer, int structureOffset) {

      	this.msgByteBuffer = byteBuffer;
      	this.structureOffset = structureOffset;
      }


	/**
	* This method will set field: x & update message byteBuffer
	* @param value - value to set
	*/
	public void set_x(int value) {
		this.msgByteBuffer.putInt(structureOffset+0, value);
	}

	/**
	* This method will set field: y & update message byteBuffer
	* @param value - value to set
	*/
	public void set_y(int value) {
		this.msgByteBuffer.putInt(structureOffset+4, value);
	}

	/**
	* This method will set field: z & update message byteBuffer
	* @param value - value to set
	*/
	public void set_z(int value) {
		this.msgByteBuffer.putInt(structureOffset+8, value);
	}


	/**
	* This method will Get field: x value
	* @return field value
	*/
	public int get_x() {
		return (this.msgByteBuffer.getInt(structureOffset+0));
	}

	/**
	* This method will Get field: y value
	* @return field value
	*/
	public int get_y() {
		return (this.msgByteBuffer.getInt(structureOffset+4));
	}

	/**
	* This method will Get field: z value
	* @return field value
	*/
	public int get_z() {
		return (this.msgByteBuffer.getInt(structureOffset+8));
	}


       /**
       * @return field byte offset in the structure: Position
       */
       public int get_x_Offset_In_Position() {
       		return 0;
       }

       /**
       * @return field byte offset in the structure: Position
       */
       public int get_y_Offset_In_Position() {
       		return 4;
       }

       /**
       * @return field byte offset in the structure: Position
       */
       public int get_z_Offset_In_Position() {
       		return 8;
       }



       /**
       * @return field byte offset in the message
       */
       public int get_x_Offset_InMessage() {
       		return (structureOffset + 0);
       }



       /**
       * @return field byte offset in the message
       */
       public int get_y_Offset_InMessage() {
       		return (structureOffset + 4);
       }



       /**
       * @return field byte offset in the message
       */
       public int get_z_Offset_InMessage() {
       		return (structureOffset + 8);
       }



   /**
   * This function will set the ByteBuffer of the msg
   * @param bf - Bytebuffer to set
   */
   public void setMessageByteBuffer(ByteBuffer bf) {

		this.msgByteBuffer = bf;
   }

}

