package protocolsupport.protocol.typeremapper.entity.metadata.types.living.horse;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.packet.middleimpl.clientbound.play.v_pe.EntityMetadata.PeMetaBase;
import protocolsupport.protocol.typeremapper.entity.metadata.FirstNetworkEntityMetadataUpdateObjectAddRemapper;
import protocolsupport.protocol.types.networkentity.metadata.objects.NetworkEntityMetadataObjectByte;
import protocolsupport.protocol.types.networkentity.metadata.objects.NetworkEntityMetadataObjectVarInt;
import protocolsupport.protocol.utils.ProtocolVersionsHelper;
import protocolsupport.protocol.types.networkentity.metadata.objects.NetworkEntityMetadataObjectSVarInt;

public class LegacyDonkeyEntityMetadataRemapper extends CargoHorseEntityMetadataRemapper {

	public LegacyDonkeyEntityMetadataRemapper() {
		addRemap(new FirstNetworkEntityMetadataUpdateObjectAddRemapper(PeMetaBase.STRENGTH, new NetworkEntityMetadataObjectSVarInt(5)), ProtocolVersionsHelper.ALL_PE); //Fake strength for when chested.
		addRemap(new FirstNetworkEntityMetadataUpdateObjectAddRemapper(14, new NetworkEntityMetadataObjectVarInt(1)), ProtocolVersion.MINECRAFT_1_10);
		addRemap(new FirstNetworkEntityMetadataUpdateObjectAddRemapper(13, new NetworkEntityMetadataObjectVarInt(1)), ProtocolVersionsHelper.ALL_1_9);
		addRemap(new FirstNetworkEntityMetadataUpdateObjectAddRemapper(19, new NetworkEntityMetadataObjectByte((byte) 1)), ProtocolVersion.getAllBetween(ProtocolVersion.MINECRAFT_1_6_1, ProtocolVersion.MINECRAFT_1_8));
	}

}
