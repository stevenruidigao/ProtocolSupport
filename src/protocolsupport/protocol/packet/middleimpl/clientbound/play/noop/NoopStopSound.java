package protocolsupport.protocol.packet.middleimpl.clientbound.play.noop;

import protocolsupport.protocol.ConnectionImpl;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleStopSound;
import protocolsupport.protocol.packet.middleimpl.IPacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableEmptyList;

public class NoopStopSound extends MiddleStopSound {

	public NoopStopSound(ConnectionImpl connection) {
		super(connection);
	}

	@Override
	public RecyclableCollection<? extends IPacketData> toData() {
		return RecyclableEmptyList.get();
	}

}
