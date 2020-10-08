package pixelmoba.shared;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import pixelmoba.shared.dto.PlayerConnectionDto;
import pixelmoba.shared.dto.PlayerDisconnectDto;
import pixelmoba.shared.dto.PlayerJoinedDto;

import java.util.concurrent.atomic.AtomicLong;


public class Network {

    public static final int TIMEOUT = 15000;
    public static final int TCP_PORT = 5455;
    public static final int UDP_PORT = 5477;
    public static final String HOST = "127.0.0.1";

    public static final float TICK_RATE = 1f / 30f; // 1s / 30 frames = 30fps
    public static final float SERVER_FPS = 1f / 120f;

    public static final AtomicLong COUNTER = new AtomicLong(0);

    public static void register(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(PlayerConnectionDto.class);
        kryo.register(PlayerJoinedDto.class);
        kryo.register(PlayerDisconnectDto.class);
    }

}

