package pisi.unitedmeows.violentcat.shared.packet;

import pisi.unitedmeows.violentcat.shared.packet.impl.server.VHeartbeatPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterProcessor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class NetworkHandler {
    private Map<PacketHeaders, Method> processorMap = new HashMap<>();

    public NetworkHandler(Class<? extends NetworkHandler> type) {
        for (Method method : type.getDeclaredMethods()) {
            final RegisterProcessor registerProcessor = method.getAnnotation(RegisterProcessor.class);
            if (registerProcessor != null) {
                System.out.println("Registered Processor " + registerProcessor.value());
                processorMap.put(registerProcessor.value(), method);
            }
        }
    }

    public void process(PacketHeaders header, VPacket packet) {
        if (packet == null)
            return;
        try {
            Method processor = processorMap.getOrDefault(header, null);
            if (processor != null) {
                if (!processor.isAccessible())
                    processor.setAccessible(true);

                processor.invoke(this, packet);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
