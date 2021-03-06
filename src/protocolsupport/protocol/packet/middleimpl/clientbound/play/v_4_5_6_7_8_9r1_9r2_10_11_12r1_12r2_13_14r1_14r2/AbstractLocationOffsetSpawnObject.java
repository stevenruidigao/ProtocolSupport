package protocolsupport.protocol.packet.middleimpl.clientbound.play.v_4_5_6_7_8_9r1_9r2_10_11_12r1_12r2_13_14r1_14r2;

import protocolsupport.protocol.ConnectionImpl;
import protocolsupport.protocol.packet.middle.clientbound.play.MiddleSpawnObject;
import protocolsupport.protocol.typeremapper.entity.EntityLocationOffset;

public abstract class AbstractLocationOffsetSpawnObject extends MiddleSpawnObject {

	protected final EntityLocationOffset entityOffsetRemapper = EntityLocationOffset.get(version);

	public AbstractLocationOffsetSpawnObject(ConnectionImpl connection) {
		super(connection);
	}

	@Override
	public boolean postFromServerRead() {
		boolean allow = super.postFromServerRead();
		if (!allow) {
			return false;
		}
		EntityLocationOffset.Offset offset = entityOffsetRemapper.get(entityRemapper.getRemappedEntityType());
		if (offset != null) {
			x += offset.getX();
			y += offset.getY();
			z += offset.getZ();
			yaw += offset.getYaw();
			pitch += offset.getPitch();
		}
		return true;
	}

}
