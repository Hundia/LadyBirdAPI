//---------------------------------------------------
//|	Created By XmlMessagesGenerator Version: 5.5
//---------------------------------------------------

package src.optitrack.api;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

   public class Orientation {
	// fields

       // local fields
       private static final int SIZE = 16;
       protected int structureOffset = 0;
       protected ByteBuffer msgByteBuffer = null;

       // constructor
       public Orientation() {
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
	* This method will set field: qx & update message byteBuffer
	* @param value - value to set
	*/
	public void set_qx(int value) {
		this.msgByteBuffer.putInt(structureOffset+0, value);
	}

	/**
	* This method will set field: qy & update message byteBuffer
	* @param value - value to set
	*/
	public void set_qy(int value) {
		this.msgByteBuffer.putInt(structureOffset+4, value);
	}

	/**
	* This method will set field: qz & update message byteBuffer
	* @param value - value to set
	*/
	public void set_qz(int value) {
		this.msgByteBuffer.putInt(structureOffset+8, value);
	}

	/**
	* This method will set field: qw & update message byteBuffer
	* @param value - value to set
	*/
	public void set_qw(int value) {
		this.msgByteBuffer.putInt(structureOffset+12, value);
	}


	/**
	* This method will Get field: qx value
	* @return field value
	*/
	public int get_qx() {
		return (this.msgByteBuffer.getInt(structureOffset+0));
	}

	/**
	* This method will Get field: qy value
	* @return field value
	*/
	public int get_qy() {
		return (this.msgByteBuffer.getInt(structureOffset+4));
	}

	/**
	* This method will Get field: qz value
	* @return field value
	*/
	public int get_qz() {
		return (this.msgByteBuffer.getInt(structureOffset+8));
	}

	/**
	* This method will Get field: qw value
	* @return field value
	*/
	public int get_qw() {
		return (this.msgByteBuffer.getInt(structureOffset+12));
	}


       /**
       * @return field byte offset in the structure: Orientation
       */
       public int get_qx_Offset_In_Orientation() {
       		return 0;
       }

       /**
       * @return field byte offset in the structure: Orientation
       */
       public int get_qy_Offset_In_Orientation() {
       		return 4;
       }

       /**
       * @return field byte offset in the structure: Orientation
       */
       public int get_qz_Offset_In_Orientation() {
       		return 8;
       }

       /**
       * @return field byte offset in the structure: Orientation
       */
       public int get_qw_Offset_In_Orientation() {
       		return 12;
       }



       /**
       * @return field byte offset in the message
       */
       public int get_qx_Offset_InMessage() {
       		return (structureOffset + 0);
       }



       /**
       * @return field byte offset in the message
       */
       public int get_qy_Offset_InMessage() {
       		return (structureOffset + 4);
       }



       /**
       * @return field byte offset in the message
       */
       public int get_qz_Offset_InMessage() {
       		return (structureOffset + 8);
       }



       /**
       * @return field byte offset in the message
       */
       public int get_qw_Offset_InMessage() {
       		return (structureOffset + 12);
       }



   /**
   * This function will set the ByteBuffer of the msg
   * @param bf - Bytebuffer to set
   */
   public void setMessageByteBuffer(ByteBuffer bf) {

		this.msgByteBuffer = bf;
   }

}

