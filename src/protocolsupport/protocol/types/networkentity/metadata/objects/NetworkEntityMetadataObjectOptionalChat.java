package protocolsupport.protocol.types.networkentity.metadata.objects;

import io.netty.buffer.ByteBuf;
import protocolsupport.api.ProtocolVersion;
import protocolsupport.api.chat.ChatAPI;
import protocolsupport.api.chat.components.BaseComponent;
import protocolsupport.protocol.serializer.StringSerializer;
import protocolsupport.protocol.types.networkentity.metadata.ReadableNetworkEntityMetadataObject;

public class NetworkEntityMetadataObjectOptionalChat extends ReadableNetworkEntityMetadataObject<BaseComponent> {

	@Override
	public void readFromStream(ByteBuf from) {
		if (from.readBoolean()) {
			value = ChatAPI.fromJSON(StringSerializer.readVarIntUTF8String(from));
		}
	}

	@Override
	public void writeToStream(ByteBuf to, ProtocolVersion version, String locale) {
		to.writeBoolean(value != null);
		if (value != null) {
			StringSerializer.writeString(to, version, ChatAPI.toJSON(value));
		}
	}

}
