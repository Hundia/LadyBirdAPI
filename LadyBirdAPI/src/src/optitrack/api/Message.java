//---------------------------------------------------
//|	Created By XmlMessagesGenerator Version: 5.5
//---------------------------------------------------

package src.optitrack.api;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import src.optitrack.api.Header;

public abstract class Message {

    public Header header = new Header();
	protected ByteBuffer msgByteBuffer = null;

  //local message properties
  private final int m_nHeaderlength = 0;
  protected int msgOffset = 0;

	public enum ByteBufferType {
		DirectByteBuffer, NonDirectByteBuffer
	}

  /**
  * @return header length in bytes
  */
  public int getHeaderLength() {
      return header.getSize();
  }

 /**
  * @return ByteBuffer of the message
  */
  public ByteBuffer getMessageByteBuffer() {
      return msgByteBuffer;
  }

 /**
  * @return message offset in byteBuffer
  */
  public int getMessageOffset() {
      return msgOffset;
  }

 /**
  * Set message offset in byteBuffer
  */
  public void setMessageOffset(int offset) {
      msgOffset = offset;
  }

	protected abstract void initLocalMembers();


	protected void init(boolean createOwnMessageByteBuffer, ByteBufferType byteBufferType, int msgSize) {
		initLocalMembers();
		if (false == createOwnMessageByteBuffer) {
			return;
		}
		if (byteBufferType == ByteBufferType.DirectByteBuffer) {
			this.msgByteBuffer = ByteBuffer.allocateDirect(msgSize);
		}
		else {
			this.msgByteBuffer = ByteBuffer.allocate(msgSize);
		}
	this.msgByteBuffer.order(ByteOrder.LITTLE_ENDIAN);
	this.setMessageByteBuffer(msgByteBuffer);
	}

	public abstract void setMessageByteBuffer(ByteBuffer bf);
	public abstract void setMessageByteBuffer(ByteBuffer byteBuffer, int structureOffset);
	public abstract int getMessageOpcode();
	public abstract int getMessageSize();

}
