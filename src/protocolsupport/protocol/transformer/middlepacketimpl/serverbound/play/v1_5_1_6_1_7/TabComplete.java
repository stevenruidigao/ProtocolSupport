package protocolsupport.protocol.transformer.middlepacketimpl.serverbound.play.v1_5_1_6_1_7;

import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.serverbound.play.MiddleTabComplete;

public class TabComplete extends MiddleTabComplete {

	@Override
	public void readFromClientData(PacketDataSerializer serializer) {
		string = serializer.readString(Short.MAX_VALUE);
	}

}