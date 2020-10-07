package pixelmoba.shared;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class Network {

    public static final int TCP_PORT = 5455;
    public static final int UDP_PORT = 5477;

    public static final float CLIENT_TICK_TIME = 0.5f; //Seconds

    public static void register(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(PlayerId.class);
        kryo.register(Position.class);
        kryo.register(PositionPacket.class);
        kryo.register(ExampleMessage.class);
    }

    public static class PlayerId {
        public int id;
    }

    public static class PositionPacket {
        /*public List<Vec> entities = new ArrayList<>();*/
    }

    public static class Position {
        public int id;
        public float x, y;
    }

    public static class ExampleMessage {
        public String message;
    }

}

