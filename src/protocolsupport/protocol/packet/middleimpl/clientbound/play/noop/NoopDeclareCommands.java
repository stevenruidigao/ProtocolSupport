package protocolsupport.protocol.packet.middleimpl.clientbound.play.noop;

import protocolsupport.protocol.ConnectionImpl;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleDeclareCommands;
import protocolsupport.protocol.packet.middleimpl.IPacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableEmptyList;

public class NoopDeclareCommands extends MiddleDeclareCommands {

	public NoopDeclareCommands(ConnectionImpl connection) {
		super(connection);
	}

	@Override
	public RecyclableCollection<? extends IPacketData> toData() {
		return RecyclableEmptyList.get();
	}

}
