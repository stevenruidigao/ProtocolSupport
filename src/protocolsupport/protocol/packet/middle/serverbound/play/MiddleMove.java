package protocolsupport.protocol.packet.middle.serverbound.play;

import protocolsupport.protocol.ConnectionImpl;
import protocolsupport.protocol.packet.PacketType;
import protocolsupport.protocol.packet.middle.ServerBoundMiddlePacket;
import protocolsupport.protocol.packet.middleimpl.IPacketData;
import protocolsupport.protocol.packet.middleimpl.ServerBoundPacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableSingletonList;

public abstract class MiddleMove extends ServerBoundMiddlePacket {

	public MiddleMove(ConnectionImpl connection) {
		super(connection);
	}

	protected double x;
	protected double y;
	protected double z;
	protected boolean onGround;

	@Override
	public RecyclableCollection<? extends IPacketData> toNative() {
		ServerBoundPacketData creator = ServerBoundPacketData.create(PacketType.SERVERBOUND_PLAY_POSITION);
		creator.writeDouble(x);
		creator.writeDouble(y);
		creator.writeDouble(z);
		creator.writeBoolean(onGround);
		return RecyclableSingletonList.create(creator);
	}

}
