package protocolsupport.protocol.packet.middleimpl.clientbound.play.noop;

import protocolsupport.protocol.ConnectionImpl;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleBlockOpenSignEditor;
import protocolsupport.protocol.packet.middleimpl.IPacketData;
import protocolsupport.utils.recyclable.RecyclableCollection;
import protocolsupport.utils.recyclable.RecyclableEmptyList;

public class NoopBlockOpenSignEditor extends MiddleBlockOpenSignEditor {

	public NoopBlockOpenSignEditor(ConnectionImpl connection) {
		super(connection);
	}

	@Override
	public boolean postFromServerRead() {
		return false;
	}

	@Override
	public RecyclableCollection<? extends IPacketData> toData() {
		return RecyclableEmptyList.get();
	}

}
