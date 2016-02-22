//---------------------------------------------------
//|	Created By XmlMessagesGenerator Version: 5.5
//---------------------------------------------------

package src.optitrack.api;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

   public class Header {
	// fields

       // local fields
       private static final int SIZE = 0;
       protected int structureOffset = 0;
       protected ByteBuffer msgByteBuffer = null;

       // constructor
       public Header() {
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
   * This function will set the ByteBuffer of the msg
   * @param bf - Bytebuffer to set
   */
   public void setMessageByteBuffer(ByteBuffer bf) {

		this.msgByteBuffer = bf;
   }

}

