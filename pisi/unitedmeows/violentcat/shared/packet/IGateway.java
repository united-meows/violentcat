package pisi.unitedmeows.violentcat.shared.packet;

public interface IGateway {

    VPacketData queue(VPacketData data);
    VPacketData await(VPacketData data);
}
