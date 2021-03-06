package protocolsupport.protocol.packet.middleimpl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.Recycler;
import io.netty.util.Recycler.Handle;
import protocolsupport.protocol.packet.PacketType;
import protocolsupport.utils.netty.WrappingBuffer;
import protocolsupport.utils.recyclable.Recyclable;

public class ServerBoundPacketData extends WrappingBuffer implements Recyclable, IPacketData {

	protected static final Recycler<ServerBoundPacketData> recycler = new Recycler<ServerBoundPacketData>() {
		@Override
		protected ServerBoundPacketData newObject(Handle<ServerBoundPacketData> handle) {
			return new ServerBoundPacketData(handle);
		}
	};

	public static ServerBoundPacketData create(PacketType packet) {
		ServerBoundPacketData packetdata = recycler.get();
		packetdata.packet = packet;
		return packetdata;
	}

	private final Handle<ServerBoundPacketData> handle;
	private ServerBoundPacketData(Handle<ServerBoundPacketData> handle) {
		super(Unpooled.buffer());
		this.handle = handle;
	}

	private PacketType packet;

	@Override
	public void recycle() {
		clear();
		packet = null;
		handle.recycle(this);
	}

	@Override
	public void setBuf(ByteBuf buf) {
	}

	@Override
	public PacketType getPacketType() {
		return packet;
	}

	@Override
	public int getDataLength() {
		return buf.readableBytes();
	}

	@Override
	public void writeData(ByteBuf to) {
		to.writeBytes(buf);
	}

}
